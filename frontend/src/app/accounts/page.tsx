"use client";

import { FormEvent, useState } from "react";
import { useMutation, useQuery, useQueryClient } from "@tanstack/react-query";
import { AppShell } from "@/components/app-shell";
import { ProtectedView } from "@/components/protected-view";
import { api } from "@/lib/api";
import { useAuthStore } from "@/lib/auth";
import { useUserSettings } from "@/lib/hooks/use-user-settings";

export default function AccountsPage() {
  const queryClient = useQueryClient();
  const profile = useAuthStore((s) => s.profile);
  const { formatCurrency } = useUserSettings();

  const [bankName, setBankName] = useState("");
  const [editingId, setEditingId] = useState<number | null>(null);
  const [editName, setEditName] = useState("");
  const [editBalance, setEditBalance] = useState(0);

  const accountsQuery = useQuery({
    queryKey: ["accounts", profile?.id],
    queryFn: async () => {
      const p = profile ?? (await api.profile());
      return api.listBankAccounts(p.id);
    },
  });

  const createMutation = useMutation({
    mutationFn: async () => {
      const p = profile ?? (await api.profile());
      return api.createBankAccount(p.id, bankName.trim());
    },
    onSuccess: () => {
      setBankName("");
      queryClient.invalidateQueries({ queryKey: ["accounts"] });
    },
  });

  const updateMutation = useMutation({
    mutationFn: async () => {
      if (!editingId) {
        return null;
      }
      return api.updateBankAccount(editingId, editName.trim(), editBalance);
    },
    onSuccess: () => {
      setEditingId(null);
      queryClient.invalidateQueries({ queryKey: ["accounts"] });
    },
  });

  const deleteMutation = useMutation({
    mutationFn: (id: number) => api.deleteBankAccount(id),
    onSuccess: () => queryClient.invalidateQueries({ queryKey: ["accounts"] }),
  });

  const onCreate = async (event: FormEvent) => {
    event.preventDefault();
    if (!bankName.trim()) {
      return;
    }
    await createMutation.mutateAsync();
  };

  return (
    <ProtectedView>
      <AppShell>
        <div className="space-y-4">
          <section className="surface-card rounded-xl p-6">
            <h1 className="text-2xl font-bold">Bank Accounts</h1>
            <p className="mt-1 text-sm text-zinc-600">Create and manage source/destination accounts for transactions.</p>
          </section>

          <section className="surface-card rounded-xl p-4">
            <h2 className="font-semibold">Create account</h2>
            <form onSubmit={onCreate} className="mt-3 flex flex-col gap-2 sm:flex-row">
              <input
                value={bankName}
                onChange={(e) => setBankName(e.target.value)}
                placeholder="Account name"
                className="w-full rounded-md border border-zinc-300 bg-white px-3 py-2 text-sm"
              />
              <button type="submit" className="rounded-md bg-teal-800 px-4 py-2 text-sm text-white disabled:opacity-60" disabled={createMutation.isPending}>
                {createMutation.isPending ? "Creating..." : "Create"}
              </button>
            </form>
          </section>

          <section className="surface-card rounded-xl p-4">
            <h2 className="font-semibold">Existing accounts</h2>
            <div className="mt-3 space-y-2">
              {accountsQuery.data?.map((account) => {
                const isEditing = editingId === account.id;
                return (
                  <article key={account.id} className="rounded-md border border-zinc-200 bg-white p-3">
                    {isEditing ? (
                      <div className="grid gap-2 md:grid-cols-[1fr_160px_auto]">
                        <input
                          value={editName}
                          onChange={(e) => setEditName(e.target.value)}
                          className="rounded-md border border-zinc-300 px-3 py-2 text-sm"
                        />
                        <input
                          type="number"
                          value={editBalance}
                          onChange={(e) => setEditBalance(Number(e.target.value))}
                          className="rounded-md border border-zinc-300 px-3 py-2 text-sm"
                        />
                        <div className="flex gap-2">
                          <button onClick={() => updateMutation.mutate()} className="rounded-md bg-teal-800 px-3 py-2 text-sm text-white" type="button">Save</button>
                          <button onClick={() => setEditingId(null)} className="rounded-md border border-zinc-300 px-3 py-2 text-sm" type="button">Cancel</button>
                        </div>
                      </div>
                    ) : (
                      <div className="flex items-center justify-between gap-3">
                        <div>
                          <p className="font-medium">{account.name}</p>
                          <p className="text-sm text-zinc-600">Balance: {formatCurrency(account.balance || 0)}</p>
                        </div>
                        <div className="flex gap-2">
                          <button
                            type="button"
                            onClick={() => {
                              setEditingId(account.id);
                              setEditName(account.name);
                              setEditBalance(Number(account.balance || 0));
                            }}
                            className="rounded-md border border-zinc-300 px-3 py-1.5 text-sm"
                          >
                            Edit
                          </button>
                          <button
                            type="button"
                            onClick={() => deleteMutation.mutate(account.id)}
                            className="rounded-md border border-red-300 px-3 py-1.5 text-sm text-red-700"
                          >
                            Delete
                          </button>
                        </div>
                      </div>
                    )}
                  </article>
                );
              })}
              {!accountsQuery.data?.length ? <p className="text-sm text-zinc-500">No accounts found.</p> : null}
            </div>
          </section>
        </div>
      </AppShell>
    </ProtectedView>
  );
}

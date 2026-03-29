"use client";

import { FormEvent, useState } from "react";
import { useMutation, useQuery, useQueryClient } from "@tanstack/react-query";
import { AppShell } from "@/components/app-shell";
import { ProtectedView } from "@/components/protected-view";
import { api } from "@/lib/api";
import { useAuthStore } from "@/lib/auth";

export default function TransfersPage() {
  const queryClient = useQueryClient();
  const profile = useAuthStore((s) => s.profile);

  const [amount, setAmount] = useState(0);
  const [description, setDescription] = useState("");
  const [transferDate, setTransferDate] = useState(api.todayString());
  const [fromAccountId, setFromAccountId] = useState<number>(1);
  const [toAccountId, setToAccountId] = useState<number>(1);

  const accountsQuery = useQuery({
    queryKey: ["accounts-for-transfers", profile?.id],
    queryFn: async () => {
      const p = profile ?? (await api.profile());
      return api.listBankAccounts(p.id);
    },
  });

  const transfersQuery = useQuery({
    queryKey: ["transfers", profile?.id],
    queryFn: async () => {
      const p = profile ?? (await api.profile());
      return api.listTransfers(p.id);
    },
  });

  const createMutation = useMutation({
    mutationFn: async () => {
      const p = profile ?? (await api.profile());
      return api.createTransfer({
        amount,
        description: description.trim() || undefined,
        transferDate,
        fromAccountId,
        toAccountId,
        userId: p.id,
      });
    },
    onSuccess: () => {
      setAmount(0);
      setDescription("");
      queryClient.invalidateQueries({ queryKey: ["transfers"] });
    },
  });

  const onCreate = async (event: FormEvent) => {
    event.preventDefault();
    if (amount <= 0 || fromAccountId === toAccountId) {
      return;
    }
    await createMutation.mutateAsync();
  };

  return (
    <ProtectedView>
      <AppShell>
        <div className="space-y-4">
          <section className="surface-card rounded-xl p-6">
            <h1 className="text-2xl font-bold">Transfers</h1>
            <p className="mt-1 text-sm text-zinc-600">Move money between your own bank accounts.</p>
          </section>

          <section className="surface-card rounded-xl p-4">
            <h2 className="font-semibold">Create transfer</h2>
            <form onSubmit={onCreate} className="mt-3 grid gap-2 md:grid-cols-2 lg:grid-cols-3">
              <input type="number" value={amount} onChange={(e) => setAmount(Number(e.target.value))} placeholder="Amount" className="rounded-md border border-zinc-300 px-3 py-2 text-sm" />
              <input value={description} onChange={(e) => setDescription(e.target.value)} placeholder="Description" className="rounded-md border border-zinc-300 px-3 py-2 text-sm" />
              <input type="date" value={transferDate} onChange={(e) => setTransferDate(e.target.value)} className="rounded-md border border-zinc-300 px-3 py-2 text-sm" />
              <select value={fromAccountId} onChange={(e) => setFromAccountId(Number(e.target.value))} className="rounded-md border border-zinc-300 px-3 py-2 text-sm">
                {accountsQuery.data?.map((acc) => (
                  <option key={acc.id} value={acc.id}>From: {acc.name}</option>
                ))}
              </select>
              <select value={toAccountId} onChange={(e) => setToAccountId(Number(e.target.value))} className="rounded-md border border-zinc-300 px-3 py-2 text-sm">
                {accountsQuery.data?.map((acc) => (
                  <option key={acc.id} value={acc.id}>To: {acc.name}</option>
                ))}
              </select>
              <button type="submit" className="rounded-md bg-teal-800 px-4 py-2 text-sm text-white disabled:opacity-60" disabled={createMutation.isPending}>
                {createMutation.isPending ? "Saving..." : "Transfer"}
              </button>
            </form>
            {fromAccountId === toAccountId ? <p className="mt-2 text-xs text-red-700">Source and destination accounts must be different.</p> : null}
          </section>

          <section className="surface-card rounded-xl p-4">
            <h2 className="font-semibold">Transfer history</h2>
            <div className="mt-3 space-y-2">
              {transfersQuery.data?.map((row) => (
                <article key={row.id} className="rounded-md border border-zinc-200 bg-white p-3">
                  <p className="font-medium">${Number(row.amount || 0).toFixed(2)} · {row.transferDate}</p>
                  <p className="text-sm text-zinc-600">{row.fromAccountName} → {row.toAccountName}</p>
                  {row.description ? <p className="text-sm text-zinc-600">{row.description}</p> : null}
                </article>
              ))}
              {!transfersQuery.data?.length ? <p className="text-sm text-zinc-500">No transfers found.</p> : null}
            </div>
          </section>
        </div>
      </AppShell>
    </ProtectedView>
  );
}

"use client";

import { FormEvent, useState } from "react";
import { useMutation, useQuery, useQueryClient } from "@tanstack/react-query";
import { Edit2, X } from "lucide-react";
import { AppShell } from "@/components/app-shell";
import { ProtectedView } from "@/components/protected-view";
import { api } from "@/lib/api";
import { useAuthStore } from "@/lib/auth";

export default function CreditsPage() {
  const queryClient = useQueryClient();
  const profile = useAuthStore((s) => s.profile);

  const [cardName, setCardName] = useState("");
  const [amount, setAmount] = useState("");
  const [dueDate, setDueDate] = useState("");
  const [creditLimit, setCreditLimit] = useState("");
  const [interestRate, setInterestRate] = useState("");
  const [page, setPage] = useState(0);
  
  const [editingId, setEditingId] = useState<number | null>(null);
  const [editAmount, setEditAmount] = useState("");
  const [editDueDate, setEditDueDate] = useState("");

  const creditsQuery = useQuery({
    queryKey: ["credits", profile?.id, page],
    queryFn: async () => {
      const p = profile ?? (await api.profile());
      return api.listCredits(p.id, { page, size: 10 });
    },
  });

  const createMutation = useMutation({
    mutationFn: async () => {
      const p = profile ?? (await api.profile());
      return api.createCredit({
        userId: p.id,
        cardName: cardName.trim(),
        amount: Number(amount),
        dueDate,
        creditLimit: creditLimit ? Number(creditLimit) : undefined,
        interestRate: interestRate ? Number(interestRate) : undefined,
      });
    },
    onSuccess: () => {
      setCardName("");
      setAmount("");
      setDueDate("");
      setCreditLimit("");
      setInterestRate("");
      queryClient.invalidateQueries({ queryKey: ["credits"] });
    },
  });

  const deleteMutation = useMutation({
    mutationFn: (id: number) => api.deleteCredit(id),
    onSuccess: () => queryClient.invalidateQueries({ queryKey: ["credits"] }),
  });

  const updateMutation = useMutation({
    mutationFn: (id: number) =>
      api.updateCredit(id, { amount: Number(editAmount), dueDate: editDueDate }),
    onSuccess: () => {
      setEditingId(null);
      queryClient.invalidateQueries({ queryKey: ["credits"] });
    },
  });

  const onCreate = async (event: FormEvent) => {
    event.preventDefault();
    if (!cardName.trim() || !amount || Number(amount) <= 0 || !dueDate) {
      return;
    }
    await createMutation.mutateAsync();
  };

  return (
    <ProtectedView>
      <AppShell>
        <div className="space-y-4">
          <section className="surface-card rounded-xl p-6">
            <h1 className="text-2xl font-bold">Credits</h1>
            <p className="mt-1 text-sm text-zinc-600">Track credit card bills and payments.</p>
          </section>

          <section className="surface-card rounded-xl p-4">
            <h2 className="font-semibold">Add credit card bill</h2>
            <form onSubmit={onCreate} className="mt-3 grid gap-2 md:grid-cols-2 lg:grid-cols-4">
              <input
                value={cardName}
                onChange={(e) => setCardName(e.target.value)}
                placeholder="Card name"
                className="rounded-md border border-zinc-300 px-3 py-2 text-sm"
              />
              <input
                type="number"
                value={amount}
                onChange={(e) => setAmount(e.target.value)}
                placeholder="Bill amount"
                className="rounded-md border border-zinc-300 px-3 py-2 text-sm"
              />
              <input
                type="date"
                value={dueDate}
                onChange={(e) => setDueDate(e.target.value)}
                className="rounded-md border border-zinc-300 px-3 py-2 text-sm"
              />
              <input
                type="number"
                step="0.1"
                value={creditLimit}
                onChange={(e) => setCreditLimit(e.target.value)}
                placeholder="Credit limit"
                className="rounded-md border border-zinc-300 px-3 py-2 text-sm"
              />
              <input
                type="number"
                step="0.1"
                value={interestRate}
                onChange={(e) => setInterestRate(e.target.value)}
                placeholder="Interest rate (%)"
                className="rounded-md border border-zinc-300 px-3 py-2 text-sm"
              />
              <button
                type="submit"
                className="rounded-md bg-teal-800 px-4 py-2 text-sm text-white disabled:opacity-60"
                disabled={createMutation.isPending}
              >
                {createMutation.isPending ? "Saving..." : "Add bill"}
              </button>
            </form>
          </section>

          <section className="surface-card rounded-xl p-4">
            <h2 className="font-semibold">Credit card bills</h2>
            <div className="mt-3 space-y-2">
              {creditsQuery.data?.content?.map((row) => {
                const isOverdue = new Date(row.dueDate) < new Date();
                const isEditing = editingId === row.id;
                return (
                  <article key={row.id} className="rounded-md border border-zinc-200 bg-white p-3">
                    {isEditing ? (
                      <div className="space-y-2">
                        <input
                          type="number"
                          value={editAmount}
                          onChange={(e) => setEditAmount(e.target.value)}
                          placeholder="Bill amount"
                          className="w-full rounded-md border border-zinc-300 px-3 py-2 text-sm"
                        />
                        <input
                          type="date"
                          value={editDueDate}
                          onChange={(e) => setEditDueDate(e.target.value)}
                          className="w-full rounded-md border border-zinc-300 px-3 py-2 text-sm"
                        />
                        <div className="flex gap-2">
                          <button
                            type="button"
                            onClick={() => updateMutation.mutate(row.id)}
                            className="flex-1 rounded-md bg-teal-800 px-3 py-1.5 text-sm text-white disabled:opacity-60"
                            disabled={updateMutation.isPending}
                          >
                            {updateMutation.isPending ? "Saving..." : "Save"}
                          </button>
                          <button
                            type="button"
                            onClick={() => setEditingId(null)}
                            className="rounded-md border border-zinc-300 px-3 py-1.5"
                          >
                            <X size={16} />
                          </button>
                        </div>
                      </div>
                    ) : (
                      <div className="flex items-center justify-between gap-2">
                        <div>
                          <p className="font-medium">{row.cardName}</p>
                          <p className={`text-sm ${isOverdue ? "text-red-600" : "text-zinc-600"}`}>
                            ${Number(row.amount).toFixed(2)} due {row.dueDate}
                            {row.interestRate ? ` · Interest: ${row.interestRate}%` : ""}
                          </p>
                        </div>
                        <div className="flex gap-1">
                          <button
                            type="button"
                            onClick={() => {
                              setEditingId(row.id);
                              setEditAmount(String(row.amount));
                              setEditDueDate(row.dueDate);
                            }}
                            className="rounded-md border border-zinc-300 px-2 py-1"
                          >
                            <Edit2 size={16} />
                          </button>
                          <button
                            type="button"
                            onClick={() => deleteMutation.mutate(row.id)}
                            className="rounded-md border border-red-300 px-2 py-1 text-red-700"
                          >
                            Paid
                          </button>
                        </div>
                      </div>
                    )}
                  </article>
                );
              })}
              {!creditsQuery.data?.content?.length ? <p className="text-sm text-zinc-500">No credit bills found.</p> : null}
            </div>
            <div className="mt-3 flex items-center gap-2">
              <button
                type="button"
                onClick={() => setPage((p) => Math.max(0, p - 1))}
                className="rounded-md border border-zinc-300 px-3 py-1.5 text-sm"
              >
                Previous
              </button>
              <span className="text-sm text-zinc-700">Page {page + 1} of {Math.max(1, creditsQuery.data?.totalPages || 1)}</span>
              <button
                type="button"
                onClick={() => setPage((p) => p + 1)}
                disabled={Boolean(creditsQuery.data?.last)}
                className="rounded-md border border-zinc-300 px-3 py-1.5 text-sm disabled:opacity-60"
              >
                Next
              </button>
            </div>
          </section>
        </div>
      </AppShell>
    </ProtectedView>
  );
}

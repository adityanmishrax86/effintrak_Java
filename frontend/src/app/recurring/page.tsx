"use client";

import { FormEvent, useState } from "react";
import { useMutation, useQuery, useQueryClient } from "@tanstack/react-query";
import { Edit2, X } from "lucide-react";
import { AppShell } from "@/components/app-shell";
import { ProtectedView } from "@/components/protected-view";
import { api } from "@/lib/api";
import { useAuthStore } from "@/lib/auth";

export default function RecurringTransactionsPage() {
  const queryClient = useQueryClient();
  const profile = useAuthStore((s) => s.profile);

  const [description, setDescription] = useState("");
  const [amount, setAmount] = useState("");
  const [frequency, setFrequency] = useState("");
  const [type, setType] = useState<"INCOME" | "EXPENSE" | "">("");
  const [categoryId, setCategoryId] = useState("");
  const [startDate, setStartDate] = useState("");
  const [endDate, setEndDate] = useState("");
  const [page, setPage] = useState(0);
  
  const [editingId, setEditingId] = useState<number | null>(null);
  const [editAmount, setEditAmount] = useState("");
  const [editEndDate, setEditEndDate] = useState("");

  const categoriesQuery = useQuery({
    queryKey: ["categories"],
    queryFn: api.listCategories,
  });

  const recurringQuery = useQuery({
    queryKey: ["recurring-transactions", profile?.id, page],
    queryFn: async () => {
      const p = profile ?? (await api.profile());
      return api.listRecurringTransactions(p.id, { page, size: 10 });
    },
  });

  const createMutation = useMutation({
    mutationFn: async () => {
      const p = profile ?? (await api.profile());
      return api.createRecurringTransaction({
        userId: p.id,
        description: description.trim(),
        amount: Number(amount),
        frequency,
        type: type as "INCOME" | "EXPENSE",
        categoryId: categoryId ? Number(categoryId) : undefined,
        startDate,
        endDate: endDate || undefined,
      });
    },
    onSuccess: () => {
      setDescription("");
      setAmount("");
      setFrequency("");
      setType("");
      setCategoryId("");
      setStartDate("");
      setEndDate("");
      queryClient.invalidateQueries({ queryKey: ["recurring-transactions"] });
    },
  });

  const deleteMutation = useMutation({
    mutationFn: (id: number) => api.deleteRecurringTransaction(id),
    onSuccess: () => queryClient.invalidateQueries({ queryKey: ["recurring-transactions"] }),
  });

  const updateMutation = useMutation({
    mutationFn: (id: number) =>
      api.updateRecurringTransaction(id, { amount: Number(editAmount), endDate: editEndDate || undefined }),
    onSuccess: () => {
      setEditingId(null);
      queryClient.invalidateQueries({ queryKey: ["recurring-transactions"] });
    },
  });

  const onCreate = async (event: FormEvent) => {
    event.preventDefault();
    if (!description.trim() || !amount || Number(amount) <= 0 || !frequency || !type || !startDate) {
      return;
    }
    await createMutation.mutateAsync();
  };

  return (
    <ProtectedView>
      <AppShell>
        <div className="space-y-4">
          <section className="surface-card rounded-xl p-6">
            <h1 className="text-2xl font-bold">Recurring Transactions</h1>
            <p className="mt-1 text-sm text-zinc-600">Set up recurring expenses and income entries.</p>
          </section>

          <section className="surface-card rounded-xl p-4">
            <h2 className="font-semibold">Create recurring transaction</h2>
            <form onSubmit={onCreate} className="mt-3 grid gap-2 md:grid-cols-2 lg:grid-cols-4">
              <select
                value={type}
                onChange={(e) => setType(e.target.value as "INCOME" | "EXPENSE" | "")}
                className="rounded-md border border-zinc-300 px-3 py-2 text-sm"
              >
                <option value="">Type</option>
                <option value="EXPENSE">Expense</option>
                <option value="INCOME">Income</option>
              </select>
              <input
                value={description}
                onChange={(e) => setDescription(e.target.value)}
                placeholder="Description"
                className="rounded-md border border-zinc-300 px-3 py-2 text-sm"
              />
              <input
                type="number"
                value={amount}
                onChange={(e) => setAmount(e.target.value)}
                placeholder="Amount"
                className="rounded-md border border-zinc-300 px-3 py-2 text-sm"
              />
              <select
                value={frequency}
                onChange={(e) => setFrequency(e.target.value)}
                className="rounded-md border border-zinc-300 px-3 py-2 text-sm"
              >
                <option value="">Frequency</option>
                <option value="daily">Daily</option>
                <option value="weekly">Weekly</option>
                <option value="monthly">Monthly</option>
                <option value="quarterly">Quarterly</option>
                <option value="yearly">Yearly</option>
              </select>
              <select
                value={categoryId}
                onChange={(e) => setCategoryId(e.target.value)}
                className="rounded-md border border-zinc-300 px-3 py-2 text-sm"
              >
                <option value="">Category (optional)</option>
                {categoriesQuery.data?.map((cat) => (
                  <option key={cat.id} value={cat.id}>
                    {cat.name}
                  </option>
                ))}
              </select>
              <input
                type="date"
                value={startDate}
                onChange={(e) => setStartDate(e.target.value)}
                className="rounded-md border border-zinc-300 px-3 py-2 text-sm"
              />
              <input
                type="date"
                value={endDate}
                onChange={(e) => setEndDate(e.target.value)}
                placeholder="End date (optional)"
                className="rounded-md border border-zinc-300 px-3 py-2 text-sm"
              />
              <button
                type="submit"
                className="rounded-md bg-teal-800 px-4 py-2 text-sm text-white disabled:opacity-60"
                disabled={createMutation.isPending}
              >
                {createMutation.isPending ? "Saving..." : "Create"}
              </button>
            </form>
          </section>

          <section className="surface-card rounded-xl p-4">
            <h2 className="font-semibold">Recurring transactions</h2>
            <div className="mt-3 space-y-2">
              {recurringQuery.data?.content?.map((row) => {
                const isEditing = editingId === row.id;
                return (
                  <article key={row.id} className="rounded-md border border-zinc-200 bg-white p-3">
                    {isEditing ? (
                      <div className="space-y-2">
                        <input
                          type="number"
                          value={editAmount}
                          onChange={(e) => setEditAmount(e.target.value)}
                          placeholder="Amount"
                          className="w-full rounded-md border border-zinc-300 px-3 py-2 text-sm"
                        />
                        <input
                          type="date"
                          value={editEndDate}
                          onChange={(e) => setEditEndDate(e.target.value)}
                          placeholder="End date (optional)"
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
                          <p className="font-medium">
                            {row.description} <span className="text-xs text-zinc-500">({row.type})</span>
                          </p>
                          <p className="text-sm text-zinc-600">
                            ${Number(row.amount).toFixed(2)} {row.frequency} · Starting {row.startDate}
                          </p>
                        </div>
                        <div className="flex gap-1">
                          <button
                            type="button"
                            onClick={() => {
                              setEditingId(row.id);
                              setEditAmount(String(row.amount));
                              setEditEndDate(row.endDate || "");
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
                            Delete
                          </button>
                        </div>
                      </div>
                    )}
                  </article>
                );
              })}
              {!recurringQuery.data?.content?.length ? <p className="text-sm text-zinc-500">No recurring transactions found.</p> : null}
            </div>
            <div className="mt-3 flex items-center gap-2">
              <button
                type="button"
                onClick={() => setPage((p) => Math.max(0, p - 1))}
                className="rounded-md border border-zinc-300 px-3 py-1.5 text-sm"
              >
                Previous
              </button>
              <span className="text-sm text-zinc-700">Page {page + 1} of {Math.max(1, recurringQuery.data?.totalPages || 1)}</span>
              <button
                type="button"
                onClick={() => setPage((p) => p + 1)}
                disabled={Boolean(recurringQuery.data?.last)}
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

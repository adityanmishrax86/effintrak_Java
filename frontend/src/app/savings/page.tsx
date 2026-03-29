"use client";

import { FormEvent, useState } from "react";
import { useMutation, useQuery, useQueryClient } from "@tanstack/react-query";
import { Edit2, X } from "lucide-react";
import { AppShell } from "@/components/app-shell";
import { ProtectedView } from "@/components/protected-view";
import { api } from "@/lib/api";
import { useAuthStore } from "@/lib/auth";

export default function SavingsPage() {
  const queryClient = useQueryClient();
  const profile = useAuthStore((s) => s.profile);

  const [goalName, setGoalName] = useState("");
  const [targetAmount, setTargetAmount] = useState("");
  const [targetDate, setTargetDate] = useState("");
  const [frequency, setFrequency] = useState("");
  const [page, setPage] = useState(0);
  
  const [editingId, setEditingId] = useState<number | null>(null);
  const [editAmount, setEditAmount] = useState("");
  const [editTargetDate, setEditTargetDate] = useState("");

  const savingsQuery = useQuery({
    queryKey: ["savings", profile?.id, page],
    queryFn: async () => {
      const p = profile ?? (await api.profile());
      return api.listSavings(p.id, { page, size: 10 });
    },
  });

  const createMutation = useMutation({
    mutationFn: async () => {
      const p = profile ?? (await api.profile());
      return api.createSavings({
        userId: p.id,
        goalName: goalName.trim(),
        targetAmount: Number(targetAmount),
        targetDate,
        frequency: frequency || undefined,
      });
    },
    onSuccess: () => {
      setGoalName("");
      setTargetAmount("");
      setTargetDate("");
      setFrequency("");
      queryClient.invalidateQueries({ queryKey: ["savings"] });
    },
  });

  const updateMutation = useMutation({
    mutationFn: (id: number) =>
      api.updateSavings(id, { currentAmount: Number(editAmount), targetDate: editTargetDate }),
    onSuccess: () => {
      setEditingId(null);
      queryClient.invalidateQueries({ queryKey: ["savings"] });
    },
  });

  const deleteMutation = useMutation({
    mutationFn: (id: number) => api.deleteSavings(id),
    onSuccess: () => queryClient.invalidateQueries({ queryKey: ["savings"] }),
  });

  const onCreate = async (event: FormEvent) => {
    event.preventDefault();
    if (!goalName.trim() || !targetAmount || Number(targetAmount) <= 0 || !targetDate) {
      return;
    }
    await createMutation.mutateAsync();
  };

  return (
    <ProtectedView>
      <AppShell>
        <div className="space-y-4">
          <section className="surface-card rounded-xl p-6">
            <h1 className="text-2xl font-bold">Savings Goals</h1>
            <p className="mt-1 text-sm text-zinc-600">Set and track savings goals for your future.</p>
          </section>

          <section className="surface-card rounded-xl p-4">
            <h2 className="font-semibold">Create savings goal</h2>
            <form onSubmit={onCreate} className="mt-3 grid gap-2 md:grid-cols-2 lg:grid-cols-4">
              <input
                value={goalName}
                onChange={(e) => setGoalName(e.target.value)}
                placeholder="Goal name"
                className="rounded-md border border-zinc-300 px-3 py-2 text-sm"
              />
              <input
                type="number"
                value={targetAmount}
                onChange={(e) => setTargetAmount(e.target.value)}
                placeholder="Target amount"
                className="rounded-md border border-zinc-300 px-3 py-2 text-sm"
              />
              <input
                type="date"
                value={targetDate}
                onChange={(e) => setTargetDate(e.target.value)}
                className="rounded-md border border-zinc-300 px-3 py-2 text-sm"
              />
              <select
                value={frequency}
                onChange={(e) => setFrequency(e.target.value)}
                className="rounded-md border border-zinc-300 px-3 py-2 text-sm"
              >
                <option value="">Frequency (optional)</option>
                <option value="weekly">Weekly</option>
                <option value="monthly">Monthly</option>
                <option value="quarterly">Quarterly</option>
                <option value="yearly">Yearly</option>
              </select>
              <button
                type="submit"
                className="rounded-md bg-teal-800 px-4 py-2 text-sm text-white disabled:opacity-60"
                disabled={createMutation.isPending}
              >
                {createMutation.isPending ? "Saving..." : "Create goal"}
              </button>
            </form>
          </section>

          <section className="surface-card rounded-xl p-4">
            <h2 className="font-semibold">Savings goals list</h2>
            <div className="mt-3 space-y-2">
              {savingsQuery.data?.content?.map((row) => {
                const progress = row.targetAmount ? (row.currentAmount / row.targetAmount) * 100 : 0;
                const isEditing = editingId === row.id;
                return (
                  <article key={row.id} className="rounded-md border border-zinc-200 bg-white p-3">
                    {isEditing ? (
                      <div className="space-y-2">
                        <input
                          type="number"
                          value={editAmount}
                          onChange={(e) => setEditAmount(e.target.value)}
                          placeholder="Current amount"
                          className="w-full rounded-md border border-zinc-300 px-3 py-2 text-sm"
                        />
                        <input
                          type="date"
                          value={editTargetDate}
                          onChange={(e) => setEditTargetDate(e.target.value)}
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
                      <>
                        <div className="flex items-center justify-between gap-2">
                          <div className="flex-1">
                            <p className="font-medium">{row.goalName}</p>
                            <p className="text-sm text-zinc-600">
                              ${Number(row.currentAmount || 0).toFixed(2)} / ${Number(row.targetAmount).toFixed(2)} · Target: {row.targetDate}
                            </p>
                            <div className="mt-2 h-2 w-full rounded-full bg-zinc-200">
                              <div
                                className="h-2 rounded-full bg-teal-600"
                                style={{ width: `${Math.min(progress, 100)}%` }}
                              />
                            </div>
                            <p className="mt-1 text-xs text-zinc-600">{Math.round(progress)}% complete</p>
                          </div>
                          <div className="flex gap-1">
                            <button
                              type="button"
                              onClick={() => {
                                setEditingId(row.id);
                                setEditAmount(String(row.currentAmount));
                                setEditTargetDate(row.targetDate);
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
                      </>
                    )}
                  </article>
                );
              })}
              {!savingsQuery.data?.content?.length ? <p className="text-sm text-zinc-500">No savings goals found.</p> : null}
            </div>
            <div className="mt-3 flex items-center gap-2">
              <button
                type="button"
                onClick={() => setPage((p) => Math.max(0, p - 1))}
                className="rounded-md border border-zinc-300 px-3 py-1.5 text-sm"
              >
                Previous
              </button>
              <span className="text-sm text-zinc-700">Page {page + 1} of {Math.max(1, savingsQuery.data?.totalPages || 1)}</span>
              <button
                type="button"
                onClick={() => setPage((p) => p + 1)}
                disabled={Boolean(savingsQuery.data?.last)}
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

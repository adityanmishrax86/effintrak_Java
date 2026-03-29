"use client";

import { FormEvent, useState } from "react";
import { useMutation, useQuery, useQueryClient } from "@tanstack/react-query";
import { Trash2, Plus, X, Edit2 } from "lucide-react";
import { AppShell } from "@/components/app-shell";
import { ProtectedView } from "@/components/protected-view";
import { Button, Card, Input, Select } from "@/components/ui";
import { api } from "@/lib/api";
import { useAuthStore } from "@/lib/auth";

export default function BudgetsPage() {
  const queryClient = useQueryClient();
  const profile = useAuthStore((s) => s.profile);

  const [name, setName] = useState("");
  const [amount, setAmount] = useState("");
  const [startDate, setStartDate] = useState("");
  const [endDate, setEndDate] = useState("");
  const [categoryId, setCategoryId] = useState("");
  const [page, setPage] = useState(0);
  
  const [editingId, setEditingId] = useState<number | null>(null);
  const [editAmount, setEditAmount] = useState("");
  const [editEndDate, setEditEndDate] = useState("");

  const categoriesQuery = useQuery({
    queryKey: ["categories"],
    queryFn: api.listCategories,
  });

  const budgetsQuery = useQuery({
    queryKey: ["budgets", profile?.id, page],
    queryFn: async () => {
      const p = profile ?? (await api.profile());
      return api.listBudgets(p.id, { page, size: 10 });
    },
  });

  const createMutation = useMutation({
    mutationFn: async () => {
      const p = profile ?? (await api.profile());
      return api.createBudget({
        userId: p.id,
        name: name.trim(),
        amount: Number(amount),
        categoryId: categoryId ? Number(categoryId) : undefined,
        startDate,
        endDate,
      });
    },
    onSuccess: () => {
      setName("");
      setAmount("");
      setStartDate("");
      setEndDate("");
      setCategoryId("");
      queryClient.invalidateQueries({ queryKey: ["budgets"] });
    },
  });

  const updateMutation = useMutation({
    mutationFn: (id: number) =>
      api.updateBudget(id, {
        amount: Number(editAmount),
        endDate: editEndDate,
      }),
    onSuccess: () => {
      setEditingId(null);
      queryClient.invalidateQueries({ queryKey: ["budgets"] });
    },
  });

  const deleteMutation = useMutation({
    mutationFn: (id: number) => api.deleteBudget(id),
    onSuccess: () => queryClient.invalidateQueries({ queryKey: ["budgets"] }),
  });

  const onCreate = async (event: FormEvent) => {
    event.preventDefault();
    if (!name.trim() || !amount || Number(amount) <= 0 || !startDate || !endDate) {
      return;
    }
    await createMutation.mutateAsync();
  };

  const onEdit = async (budget: { id: number; amount: number; endDate: string }) => {
    setEditingId(budget.id);
    setEditAmount(String(budget.amount));
    setEditEndDate(budget.endDate);
  };

  const onSaveEdit = async (event: FormEvent) => {
    event.preventDefault();
    if (editingId && Number(editAmount) > 0) {
      await updateMutation.mutateAsync(editingId);
    }
  };

  return (
    <ProtectedView>
      <AppShell>
        <div className="space-y-6">
          <div>
            <h1 className="text-3xl font-bold text-zinc-900">Budgets</h1>
            <p className="mt-1 text-zinc-600">Create and manage monthly budgets for spending categories.</p>
          </div>

          <Card>
            <form onSubmit={onCreate} className="space-y-4">
              <h2 className="font-semibold text-lg">Create new budget</h2>
              <div className="grid gap-4 grid-cols-1 md:grid-cols-2 lg:grid-cols-4">
                <Input
                  label="Budget name"
                  value={name}
                  onChange={(e) => setName(e.target.value)}
                  placeholder="e.g., Groceries"
                />
                <Input
                  label="Amount"
                  type="number"
                  value={amount}
                  onChange={(e) => setAmount(e.target.value)}
                  placeholder="0.00"
                />
                <Select
                  label="Category"
                  value={categoryId}
                  onChange={(e) => setCategoryId(e.target.value)}
                >
                  <option value="">Select category</option>
                  {categoriesQuery.data?.map((cat) => (
                    <option key={cat.id} value={cat.id}>
                      {cat.name}
                    </option>
                  ))}
                </Select>
                <div className="grid grid-cols-2 gap-2">
                  <div>
                    <label className="text-sm font-medium text-zinc-700">Start date</label>
                    <input
                      type="date"
                      value={startDate}
                      onChange={(e) => setStartDate(e.target.value)}
                      className="mt-1 w-full rounded-lg border border-zinc-300 px-3 py-2 text-sm"
                    />
                  </div>
                  <div>
                    <label className="text-sm font-medium text-zinc-700">End date</label>
                    <input
                      type="date"
                      value={endDate}
                      onChange={(e) => setEndDate(e.target.value)}
                      className="mt-1 w-full rounded-lg border border-zinc-300 px-3 py-2 text-sm"
                    />
                  </div>
                </div>
              </div>
              <Button
                type="submit"
                variant="primary"
                disabled={createMutation.isPending}
                className="w-full md:w-auto"
              >
                <Plus className="h-4 w-4" />
                {createMutation.isPending ? "Creating..." : "Create budget"}
              </Button>
            </form>
          </Card>

          <Card>
            <div className="space-y-4">
              <h2 className="font-semibold text-lg">Budget list</h2>
              <div className="space-y-2">
                {budgetsQuery.data?.content?.map((row) => (
                  <div key={row.id}>
                    {editingId === row.id ? (
                      // Edit form
                      <form
                        onSubmit={onSaveEdit}
                        className="p-4 rounded-lg border border-teal-300 bg-teal-50 space-y-3"
                      >
                        <div className="flex items-center justify-between mb-3">
                          <h3 className="font-semibold text-zinc-900">Edit Budget</h3>
                          <button
                            type="button"
                            onClick={() => setEditingId(null)}
                            className="text-zinc-500 hover:text-zinc-700"
                          >
                            <X className="h-5 w-5" />
                          </button>
                        </div>
                        <div className="grid gap-3 md:grid-cols-3">
                          <Input
                            label="Amount"
                            type="number"
                            value={editAmount}
                            onChange={(e) => setEditAmount(e.target.value)}
                          />
                          <Input
                            label="End date"
                            type="date"
                            value={editEndDate}
                            onChange={(e) => setEditEndDate(e.target.value)}
                          />
                          <div className="flex items-end gap-2">
                            <Button
                              type="submit"
                              variant="primary"
                              disabled={updateMutation.isPending}
                              className="flex-1"
                            >
                              {updateMutation.isPending ? "Saving..." : "Save"}
                            </Button>
                            <Button
                              type="button"
                              variant="ghost"
                              onClick={() => setEditingId(null)}
                              className="flex-1"
                            >
                              Cancel
                            </Button>
                          </div>
                        </div>
                      </form>
                    ) : (
                      // Display form
                      <div className="flex items-center justify-between p-4 rounded-lg border border-zinc-200 hover:border-zinc-300 hover:bg-zinc-50 transition">
                        <div className="flex-1">
                          <p className="font-medium text-zinc-900">{row.name}</p>
                          <p className="text-sm text-zinc-600">
                            Budget: ${Number(row.amount || 0).toFixed(2)} • {row.startDate} to {row.endDate}
                          </p>
                          {row.spent && (
                            <div className="mt-2 h-2 w-full rounded-full bg-zinc-200">
                              <div
                                className={`h-2 rounded-full ${
                                  Number(row.spent) > Number(row.amount) ? "bg-red-600" : "bg-teal-600"
                                }`}
                                style={{ width: `${Math.min((Number(row.spent) / Number(row.amount)) * 100, 100)}%` }}
                              />
                            </div>
                          )}
                        </div>
                        <div className="ml-4 text-right flex-shrink-0">
                          <p className="text-sm font-medium text-zinc-900">
                            Spent: ${Number(row.spent || 0).toFixed(2)}
                          </p>
                          <div className="mt-2 flex gap-2">
                            <Button
                              size="sm"
                              variant="ghost"
                              onClick={() => onEdit(row)}
                            >
                              <Edit2 className="h-3 w-3" />
                            </Button>
                            <Button
                              size="sm"
                              variant="danger"
                              onClick={() => deleteMutation.mutate(row.id)}
                            >
                              <Trash2 className="h-3 w-3" />
                            </Button>
                          </div>
                        </div>
                      </div>
                    )}
                  </div>
                ))}
                {!budgetsQuery.data?.content?.length && (
                  <p className="text-center py-8 text-zinc-500">No budgets created yet. Create one to get started!</p>
                )}
              </div>
            </div>
          </Card>

          {budgetsQuery.data && budgetsQuery.data.totalPages > 1 && (
            <div className="flex items-center justify-between">
              <Button
                variant="secondary"
                onClick={() => setPage((p) => Math.max(0, p - 1))}
                disabled={page === 0}
              >
                Previous
              </Button>
              <span className="text-sm text-zinc-600">
                Page {page + 1} of {budgetsQuery.data.totalPages}
              </span>
              <Button
                variant="secondary"
                onClick={() => setPage((p) => p + 1)}
                disabled={budgetsQuery.data?.last ?? true}
              >
                Next
              </Button>
            </div>
          )}
        </div>
      </AppShell>
    </ProtectedView>
  );
}

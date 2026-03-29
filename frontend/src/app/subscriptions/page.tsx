"use client";

import { FormEvent, useState } from "react";
import { useMutation, useQuery, useQueryClient } from "@tanstack/react-query";
import { Edit2, X } from "lucide-react";
import { AppShell } from "@/components/app-shell";
import { ProtectedView } from "@/components/protected-view";
import { api } from "@/lib/api";
import { useAuthStore } from "@/lib/auth";

export default function SubscriptionsPage() {
  const queryClient = useQueryClient();
  const profile = useAuthStore((s) => s.profile);

  const [serviceName, setServiceName] = useState("");
  const [amount, setAmount] = useState("");
  const [billingCycle, setBillingCycle] = useState("");
  const [startDate, setStartDate] = useState("");
  const [categoryId, setCategoryId] = useState("");
  const [page, setPage] = useState(0);
  
  const [editingId, setEditingId] = useState<number | null>(null);
  const [editAmount, setEditAmount] = useState("");
  const [editBillingCycle, setEditBillingCycle] = useState("");

  const categoriesQuery = useQuery({
    queryKey: ["categories"],
    queryFn: api.listCategories,
  });

  const subscriptionsQuery = useQuery({
    queryKey: ["subscriptions", profile?.id, page],
    queryFn: async () => {
      const p = profile ?? (await api.profile());
      return api.listSubscriptions(p.id, { page, size: 10 });
    },
  });

  const createMutation = useMutation({
    mutationFn: async () => {
      const p = profile ?? (await api.profile());
      return api.createSubscription({
        userId: p.id,
        serviceName: serviceName.trim(),
        amount: Number(amount),
        billingCycle,
        startDate,
        categoryId: categoryId ? Number(categoryId) : undefined,
      });
    },
    onSuccess: () => {
      setServiceName("");
      setAmount("");
      setBillingCycle("");
      setStartDate("");
      setCategoryId("");
      queryClient.invalidateQueries({ queryKey: ["subscriptions"] });
    },
  });

  const deleteMutation = useMutation({
    mutationFn: (id: number) => api.deleteSubscription(id),
    onSuccess: () => queryClient.invalidateQueries({ queryKey: ["subscriptions"] }),
  });

  const updateMutation = useMutation({
    mutationFn: (id: number) =>
      api.updateSubscription(id, { amount: Number(editAmount), billingCycle: editBillingCycle }),
    onSuccess: () => {
      setEditingId(null);
      queryClient.invalidateQueries({ queryKey: ["subscriptions"] });
    },
  });

  const onCreate = async (event: FormEvent) => {
    event.preventDefault();
    if (!serviceName.trim() || !amount || Number(amount) <= 0 || !billingCycle || !startDate) {
      return;
    }
    await createMutation.mutateAsync();
  };

  return (
    <ProtectedView>
      <AppShell>
        <div className="space-y-4">
          <section className="surface-card rounded-xl p-6">
            <h1 className="text-2xl font-bold">Subscriptions</h1>
            <p className="mt-1 text-sm text-zinc-600">Track your recurring subscriptions and memberships.</p>
          </section>

          <section className="surface-card rounded-xl p-4">
            <h2 className="font-semibold">Add subscription</h2>
            <form onSubmit={onCreate} className="mt-3 grid gap-2 md:grid-cols-2 lg:grid-cols-4">
              <input
                value={serviceName}
                onChange={(e) => setServiceName(e.target.value)}
                placeholder="Service name"
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
                value={billingCycle}
                onChange={(e) => setBillingCycle(e.target.value)}
                className="rounded-md border border-zinc-300 px-3 py-2 text-sm"
              >
                <option value="">Billing cycle</option>
                <option value="daily">Daily</option>
                <option value="weekly">Weekly</option>
                <option value="monthly">Monthly</option>
                <option value="quarterly">Quarterly</option>
                <option value="yearly">Yearly</option>
              </select>
              <input
                type="date"
                value={startDate}
                onChange={(e) => setStartDate(e.target.value)}
                className="rounded-md border border-zinc-300 px-3 py-2 text-sm"
              />
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
              <button
                type="submit"
                className="rounded-md bg-teal-800 px-4 py-2 text-sm text-white disabled:opacity-60"
                disabled={createMutation.isPending}
              >
                {createMutation.isPending ? "Saving..." : "Add subscription"}
              </button>
            </form>
          </section>

          <section className="surface-card rounded-xl p-4">
            <h2 className="font-semibold">Active subscriptions</h2>
            <div className="mt-3 space-y-2">
              {subscriptionsQuery.data?.content?.map((row) => {
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
                        <select
                          value={editBillingCycle}
                          onChange={(e) => setEditBillingCycle(e.target.value)}
                          className="w-full rounded-md border border-zinc-300 px-3 py-2 text-sm"
                        >
                          <option value="daily">Daily</option>
                          <option value="weekly">Weekly</option>
                          <option value="monthly">Monthly</option>
                          <option value="quarterly">Quarterly</option>
                          <option value="yearly">Yearly</option>
                        </select>
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
                          <p className="font-medium">{row.serviceName}</p>
                          <p className="text-sm text-zinc-600">
                            ${Number(row.amount).toFixed(2)} {row.billingCycle} · Started {row.startDate}
                          </p>
                        </div>
                        <div className="flex gap-1">
                          <button
                            type="button"
                            onClick={() => {
                              setEditingId(row.id);
                              setEditAmount(String(row.amount));
                              setEditBillingCycle(row.billingCycle);
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
                            Cancel
                          </button>
                        </div>
                      </div>
                    )}
                  </article>
                );
              })}
              {!subscriptionsQuery.data?.content?.length ? <p className="text-sm text-zinc-500">No subscriptions found.</p> : null}
            </div>
            <div className="mt-3 flex items-center gap-2">
              <button
                type="button"
                onClick={() => setPage((p) => Math.max(0, p - 1))}
                className="rounded-md border border-zinc-300 px-3 py-1.5 text-sm"
              >
                Previous
              </button>
              <span className="text-sm text-zinc-700">Page {page + 1} of {Math.max(1, subscriptionsQuery.data?.totalPages || 1)}</span>
              <button
                type="button"
                onClick={() => setPage((p) => p + 1)}
                disabled={Boolean(subscriptionsQuery.data?.last)}
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

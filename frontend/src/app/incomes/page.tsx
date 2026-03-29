"use client";

import { FormEvent, useMemo, useState } from "react";
import { useMutation, useQuery, useQueryClient } from "@tanstack/react-query";
import { AppShell } from "@/components/app-shell";
import { ProtectedView } from "@/components/protected-view";
import { api } from "@/lib/api";
import { useAuthStore } from "@/lib/auth";

export default function IncomesPage() {
  const queryClient = useQueryClient();
  const profile = useAuthStore((s) => s.profile);

  const [start, setStart] = useState("");
  const [end, setEnd] = useState("");
  const [page, setPage] = useState(0);

  const [description, setDescription] = useState("");
  const [amount, setAmount] = useState(0);
  const [date, setDate] = useState(api.todayString());
  const [categoryId, setCategoryId] = useState(1);
  const [source, setSource] = useState("");
  const [note, setNote] = useState("");
  const [bankAccountId, setBankAccountId] = useState<number>(1);

  const accountsQuery = useQuery({
    queryKey: ["accounts-for-incomes", profile?.id],
    queryFn: async () => {
      const p = profile ?? (await api.profile());
      return api.listBankAccounts(p.id);
    },
  });

  const categoriesQuery = useQuery({
    queryKey: ["categories"],
    queryFn: api.listCategories,
  });

  const incomesQuery = useQuery({
    queryKey: ["incomes", profile?.id, start, end, page],
    queryFn: async () => {
      const p = profile ?? (await api.profile());
      return api.listIncomes(p.id, {
        start: start || undefined,
        end: end || undefined,
        page,
        size: 10,
      });
    },
  });

  const createMutation = useMutation({
    mutationFn: async () => {
      const p = profile ?? (await api.profile());
      return api.createIncome({
        description: description.trim(),
        amount,
        date,
        categoryId,
        source: source.trim() || undefined,
        note: note.trim() || undefined,
        userId: p.id,
        bankAccountId,
      });
    },
    onSuccess: () => {
      setDescription("");
      setAmount(0);
      setSource("");
      setNote("");
      queryClient.invalidateQueries({ queryKey: ["incomes"] });
    },
  });

  const deleteMutation = useMutation({
    mutationFn: (id: number) => api.deleteIncome(id),
    onSuccess: () => queryClient.invalidateQueries({ queryKey: ["incomes"] }),
  });

  const totals = useMemo(() => {
    const rows = incomesQuery.data?.content || [];
    return rows.reduce((sum, row) => sum + Number(row.amount || 0), 0);
  }, [incomesQuery.data]);

  const onCreate = async (event: FormEvent) => {
    event.preventDefault();
    if (!description.trim() || amount <= 0) {
      return;
    }
    await createMutation.mutateAsync();
  };

  return (
    <ProtectedView>
      <AppShell>
        <div className="space-y-4">
          <section className="surface-card rounded-xl p-6">
            <h1 className="text-2xl font-bold">Incomes</h1>
            <p className="mt-1 text-sm text-zinc-600">Capture salary and other income streams with filters.</p>
          </section>

          <section className="surface-card rounded-xl p-4">
            <h2 className="font-semibold">Create income</h2>
            <form onSubmit={onCreate} className="mt-3 grid gap-2 md:grid-cols-2 lg:grid-cols-4">
              <input value={description} onChange={(e) => setDescription(e.target.value)} placeholder="Description" className="rounded-md border border-zinc-300 px-3 py-2 text-sm" />
              <input type="number" value={amount} onChange={(e) => setAmount(Number(e.target.value))} placeholder="Amount" className="rounded-md border border-zinc-300 px-3 py-2 text-sm" />
              <input type="date" value={date} onChange={(e) => setDate(e.target.value)} className="rounded-md border border-zinc-300 px-3 py-2 text-sm" />
              <select value={categoryId} onChange={(e) => setCategoryId(Number(e.target.value))} className="rounded-md border border-zinc-300 px-3 py-2 text-sm">
                {categoriesQuery.data?.map((cat) => (
                  <option key={cat.id} value={cat.id}>{cat.name}</option>
                ))}
              </select>
              <input value={source} onChange={(e) => setSource(e.target.value)} placeholder="Source" className="rounded-md border border-zinc-300 px-3 py-2 text-sm" />
              <input value={note} onChange={(e) => setNote(e.target.value)} placeholder="Note" className="rounded-md border border-zinc-300 px-3 py-2 text-sm" />
              <select value={bankAccountId} onChange={(e) => setBankAccountId(Number(e.target.value))} className="rounded-md border border-zinc-300 px-3 py-2 text-sm">
                {accountsQuery.data?.map((acc) => (
                  <option key={acc.id} value={acc.id}>{acc.name}</option>
                ))}
              </select>
              <button type="submit" className="rounded-md bg-teal-800 px-4 py-2 text-sm text-white disabled:opacity-60" disabled={createMutation.isPending}>
                {createMutation.isPending ? "Saving..." : "Add income"}
              </button>
            </form>
          </section>

          <section className="surface-card rounded-xl p-4">
            <h2 className="font-semibold">Filters</h2>
            <div className="mt-3 grid gap-2 md:grid-cols-3">
              <input type="date" value={start} onChange={(e) => setStart(e.target.value)} className="rounded-md border border-zinc-300 px-3 py-2 text-sm" />
              <input type="date" value={end} onChange={(e) => setEnd(e.target.value)} className="rounded-md border border-zinc-300 px-3 py-2 text-sm" />
              <button onClick={() => setPage(0)} type="button" className="rounded-md border border-zinc-300 px-3 py-2 text-sm">Apply</button>
            </div>
          </section>

          <section className="surface-card rounded-xl p-4">
            <div className="flex items-center justify-between">
              <h2 className="font-semibold">Income list</h2>
              <p className="text-sm text-zinc-600">Page total: ${totals.toFixed(2)}</p>
            </div>
            <div className="mt-3 space-y-2">
              {incomesQuery.data?.content?.map((row) => (
                <article key={row.id} className="rounded-md border border-zinc-200 bg-white p-3">
                  <div className="flex items-center justify-between gap-2">
                    <div>
                      <p className="font-medium">{row.description}</p>
                      <p className="text-sm text-zinc-600">{row.date} · {row.category || "Category"} · {row.source || "-"}</p>
                    </div>
                    <div className="text-right">
                      <p className="font-semibold text-emerald-700">+${Number(row.amount || 0).toFixed(2)}</p>
                      <button type="button" onClick={() => deleteMutation.mutate(row.id)} className="mt-1 rounded-md border border-red-300 px-2 py-1 text-xs text-red-700">Delete</button>
                    </div>
                  </div>
                </article>
              ))}
              {!incomesQuery.data?.content?.length ? <p className="text-sm text-zinc-500">No incomes found.</p> : null}
            </div>
            <div className="mt-3 flex items-center gap-2">
              <button type="button" onClick={() => setPage((p) => Math.max(0, p - 1))} className="rounded-md border border-zinc-300 px-3 py-1.5 text-sm">Previous</button>
              <span className="text-sm text-zinc-700">Page {page + 1} of {Math.max(1, incomesQuery.data?.totalPages || 1)}</span>
              <button
                type="button"
                onClick={() => setPage((p) => p + 1)}
                disabled={Boolean(incomesQuery.data?.last)}
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

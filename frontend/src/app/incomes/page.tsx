"use client";

import { useMemo, useState } from "react";
import { useMutation, useQuery, useQueryClient } from "@tanstack/react-query";
import { Edit2, Plus, Trash2 } from "lucide-react";
import { AppShell } from "@/components/app-shell";
import { ProtectedView } from "@/components/protected-view";
import { api } from "@/lib/api";
import { useAuthStore } from "@/lib/auth";
import { DataTable, type Column } from "@/components/data-table";
import { TransactionForm } from "@/components/transaction-form";
import { Button } from "@/components/ui/button";
import { Badge } from "@/components/ui/badge";
import { Input } from "@/components/ui/input";
import { toast } from "sonner";
import { useUserSettings } from "@/lib/hooks/use-user-settings";
import type { Income } from "@/lib/types";

export default function IncomesPage() {
  const queryClient = useQueryClient();
  const profile = useAuthStore((s) => s.profile);
  const { formatCurrency, formatDate } = useUserSettings();

  const [start, setStart] = useState("");
  const [end, setEnd] = useState("");
  const [page, setPage] = useState(0);
  const [showForm, setShowForm] = useState(false);
  const [editingIncome, setEditingIncome] = useState<Income | null>(null);

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
        size: 15,
      });
    },
  });

  const createMutation = useMutation({
    mutationFn: async (values: Record<string, unknown>) => {
      const p = profile ?? (await api.profile());
      return api.createIncome({ ...values, userId: p.id } as Parameters<typeof api.createIncome>[0]);
    },
    onSuccess: () => {
      toast.success("Income added");
      queryClient.invalidateQueries({ queryKey: ["incomes"] });
    },
    onError: (e) => toast.error(e.message),
  });

  const updateMutation = useMutation({
    mutationFn: async (values: Record<string, unknown>) => {
      const { id, ...payload } = values;
      return api.updateIncome(id as number, payload);
    },
    onSuccess: () => {
      toast.success("Income updated");
      setEditingIncome(null);
      queryClient.invalidateQueries({ queryKey: ["incomes"] });
    },
    onError: (e) => toast.error(e.message),
  });

  const deleteMutation = useMutation({
    mutationFn: (id: number) => api.deleteIncome(id),
    onSuccess: () => {
      toast.success("Income deleted");
      queryClient.invalidateQueries({ queryKey: ["incomes"] });
    },
  });

  const totals = useMemo(() => {
    const rows = incomesQuery.data?.content || [];
    return rows.reduce((sum, row) => sum + Number(row.amount || 0), 0);
  }, [incomesQuery.data]);

  const columns: Column<Income>[] = [
    {
      key: "description",
      header: "Description",
      cell: (row) => (
        <div>
          <p className="font-medium">{row.description}</p>
          {row.source && <p className="text-xs text-muted-foreground">{row.source}</p>}
        </div>
      ),
    },
    {
      key: "amount",
      header: "Amount",
      cell: (row) => <span className="font-semibold text-emerald-700">+{formatCurrency(row.amount)}</span>,
    },
    {
      key: "date",
      header: "Date",
      cell: (row) => <span className="text-sm">{formatDate(row.date)}</span>,
    },
    {
      key: "category",
      header: "Category",
      cell: (row) => <Badge variant="outline">{row.category || "—"}</Badge>,
    },
    {
      key: "account",
      header: "Account",
      cell: (row) => <span className="text-sm text-muted-foreground">{row.bankAccount || "—"}</span>,
    },
  ];

  const handleSubmit = async (values: Record<string, unknown>) => {
    if (editingIncome) {
      await updateMutation.mutateAsync({ ...values, id: editingIncome.id });
    } else {
      await createMutation.mutateAsync(values);
    }
  };

  return (
    <ProtectedView>
      <AppShell>
        <div className="space-y-4">
          <div className="flex items-center justify-between">
            <div>
              <h1 className="text-2xl font-bold">Incomes</h1>
              <p className="text-sm text-muted-foreground">
                Track salary and other income streams.{" "}
                {totals > 0 && <span className="font-medium text-emerald-700">Page total: {formatCurrency(totals)}</span>}
              </p>
            </div>
            <Button onClick={() => setShowForm(true)} className="gap-1">
              <Plus className="h-4 w-4" /> Add Income
            </Button>
          </div>

          <div className="flex items-center gap-3 flex-wrap">
            <Input
              type="date"
              value={start}
              onChange={(e) => { setStart(e.target.value); setPage(0); }}
              className="w-[150px]"
            />
            <Input
              type="date"
              value={end}
              onChange={(e) => { setEnd(e.target.value); setPage(0); }}
              className="w-[150px]"
            />
            {(start || end) && (
              <Button variant="ghost" size="sm" onClick={() => { setStart(""); setEnd(""); setPage(0); }}>
                Clear
              </Button>
            )}
          </div>

          <DataTable
            columns={columns}
            data={incomesQuery.data?.content || []}
            page={page}
            totalPages={incomesQuery.data?.totalPages || 1}
            onPageChange={setPage}
            emptyMessage="No incomes found. Add your first income to start tracking."
            actions={(row) => (
              <div className="flex items-center gap-1">
                <Button variant="ghost" size="icon-xs" onClick={() => setEditingIncome(row)}>
                  <Edit2 className="h-3.5 w-3.5" />
                </Button>
                <Button variant="ghost" size="icon-xs" className="text-destructive" onClick={() => deleteMutation.mutate(row.id)}>
                  <Trash2 className="h-3.5 w-3.5" />
                </Button>
              </div>
            )}
          />
        </div>

        <TransactionForm
          open={showForm || !!editingIncome}
          onOpenChange={(open) => { if (!open) { setShowForm(false); setEditingIncome(null); } }}
          kind="income"
          categories={categoriesQuery.data || []}
          accounts={accountsQuery.data || []}
          defaultValues={editingIncome ? {
            id: editingIncome.id,
            description: editingIncome.description,
            amount: editingIncome.amount,
            date: editingIncome.date,
            categoryId: editingIncome.categoryId,
            bankAccountId: editingIncome.bankAccountId,
            source: editingIncome.source || "",
            note: editingIncome.note || "",
          } : undefined}
          onSubmit={handleSubmit}
          isSubmitting={createMutation.isPending || updateMutation.isPending}
          title={editingIncome ? "Edit Income" : "Add Income"}
        />
      </AppShell>
    </ProtectedView>
  );
}

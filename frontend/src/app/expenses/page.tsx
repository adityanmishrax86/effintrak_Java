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
import {
  Select,
  SelectContent,
  SelectItem,
  SelectTrigger,
  SelectValue,
} from "@/components/ui/select";
import { toast } from "sonner";
import { useUserSettings } from "@/lib/hooks/use-user-settings";
import type { Expense } from "@/lib/types";

export default function ExpensesPage() {
  const queryClient = useQueryClient();
  const profile = useAuthStore((s) => s.profile);
  const { formatCurrency, formatDate } = useUserSettings();

  const [start, setStart] = useState("");
  const [end, setEnd] = useState("");
  const [paymentMethodFilter, setPaymentMethodFilter] = useState("");
  const [page, setPage] = useState(0);
  const [showForm, setShowForm] = useState(false);
  const [editingExpense, setEditingExpense] = useState<Expense | null>(null);

  const accountsQuery = useQuery({
    queryKey: ["accounts-for-expenses", profile?.id],
    queryFn: async () => {
      const p = profile ?? (await api.profile());
      return api.listBankAccounts(p.id);
    },
  });

  const categoriesQuery = useQuery({
    queryKey: ["categories"],
    queryFn: api.listCategories,
  });

  const expensesQuery = useQuery({
    queryKey: ["expenses", profile?.id, start, end, paymentMethodFilter, page],
    queryFn: async () => {
      const p = profile ?? (await api.profile());
      return api.listExpenses(p.id, {
        start: start || undefined,
        end: end || undefined,
        paymentMethod: paymentMethodFilter || undefined,
        page,
        size: 15,
      });
    },
  });

  const createMutation = useMutation({
    mutationFn: async (values: Record<string, unknown>) => {
      const p = profile ?? (await api.profile());
      return api.createExpense({ ...values, userId: p.id, isRecurring: false } as Parameters<typeof api.createExpense>[0]);
    },
    onSuccess: () => {
      toast.success("Expense added");
      queryClient.invalidateQueries({ queryKey: ["expenses"] });
    },
    onError: (e) => toast.error(e.message),
  });

  const updateMutation = useMutation({
    mutationFn: async (values: Record<string, unknown>) => {
      const { id, ...payload } = values;
      return api.updateExpense(id as number, payload);
    },
    onSuccess: () => {
      toast.success("Expense updated");
      setEditingExpense(null);
      queryClient.invalidateQueries({ queryKey: ["expenses"] });
    },
    onError: (e) => toast.error(e.message),
  });

  const deleteMutation = useMutation({
    mutationFn: (id: number) => api.deleteExpense(id),
    onSuccess: () => {
      toast.success("Expense deleted");
      queryClient.invalidateQueries({ queryKey: ["expenses"] });
    },
  });

  const totals = useMemo(() => {
    const rows = expensesQuery.data?.content || [];
    return rows.reduce((sum, row) => sum + Number(row.amount || 0), 0);
  }, [expensesQuery.data]);

  const columns: Column<Expense>[] = [
    {
      key: "description",
      header: "Description",
      cell: (row) => (
        <div>
          <p className="font-medium">{row.description}</p>
          {row.paidTo && <p className="text-xs text-muted-foreground">to {row.paidTo}</p>}
        </div>
      ),
    },
    {
      key: "amount",
      header: "Amount",
      cell: (row) => <span className="font-semibold text-red-700">-{formatCurrency(row.amount)}</span>,
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
      key: "payment",
      header: "Payment",
      cell: (row) => <span className="text-sm text-muted-foreground">{row.paymentMethod || "—"}</span>,
    },
  ];

  const handleSubmit = async (values: Record<string, unknown>) => {
    if (editingExpense) {
      await updateMutation.mutateAsync({ ...values, id: editingExpense.id });
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
              <h1 className="text-2xl font-bold">Expenses</h1>
              <p className="text-sm text-muted-foreground">
                Track and manage your spending.{" "}
                {totals > 0 && <span className="font-medium text-red-700">Page total: {formatCurrency(totals)}</span>}
              </p>
            </div>
            <Button onClick={() => setShowForm(true)} className="gap-1">
              <Plus className="h-4 w-4" /> Add Expense
            </Button>
          </div>

          {/* Filters */}
          <div className="flex items-center gap-3 flex-wrap">
            <Input
              type="date"
              value={start}
              onChange={(e) => { setStart(e.target.value); setPage(0); }}
              className="w-[150px]"
              placeholder="Start date"
            />
            <Input
              type="date"
              value={end}
              onChange={(e) => { setEnd(e.target.value); setPage(0); }}
              className="w-[150px]"
              placeholder="End date"
            />
            <Select value={paymentMethodFilter} onValueChange={(v) => { setPaymentMethodFilter(v === "ALL" ? "" : v); setPage(0); }}>
              <SelectTrigger className="w-[140px]">
                <SelectValue placeholder="Payment" />
              </SelectTrigger>
              <SelectContent>
                <SelectItem value="ALL">All Methods</SelectItem>
                <SelectItem value="CARD">Card</SelectItem>
                <SelectItem value="UPI">UPI</SelectItem>
                <SelectItem value="CASH">Cash</SelectItem>
                <SelectItem value="BANK_TRANSFER">Bank Transfer</SelectItem>
              </SelectContent>
            </Select>
            {(start || end || paymentMethodFilter) && (
              <Button variant="ghost" size="sm" onClick={() => { setStart(""); setEnd(""); setPaymentMethodFilter(""); setPage(0); }}>
                Clear filters
              </Button>
            )}
          </div>

          <DataTable
            columns={columns}
            data={expensesQuery.data?.content || []}
            page={page}
            totalPages={expensesQuery.data?.totalPages || 1}
            onPageChange={setPage}
            emptyMessage="No expenses found. Add your first expense to start tracking."
            actions={(row) => (
              <div className="flex items-center gap-1">
                <Button
                  variant="ghost"
                  size="icon-xs"
                  onClick={() => setEditingExpense(row)}
                >
                  <Edit2 className="h-3.5 w-3.5" />
                </Button>
                <Button
                  variant="ghost"
                  size="icon-xs"
                  className="text-destructive"
                  onClick={() => deleteMutation.mutate(row.id)}
                >
                  <Trash2 className="h-3.5 w-3.5" />
                </Button>
              </div>
            )}
          />
        </div>

        {/* Create / Edit form */}
        <TransactionForm
          open={showForm || !!editingExpense}
          onOpenChange={(open) => {
            if (!open) { setShowForm(false); setEditingExpense(null); }
          }}
          kind="expense"
          categories={categoriesQuery.data || []}
          accounts={accountsQuery.data || []}
          defaultValues={editingExpense ? {
            id: editingExpense.id,
            description: editingExpense.description,
            amount: editingExpense.amount,
            date: editingExpense.date,
            paymentMethod: editingExpense.paymentMethod || "CARD",
            paidTo: editingExpense.paidTo || "",
          } : undefined}
          onSubmit={handleSubmit}
          isSubmitting={createMutation.isPending || updateMutation.isPending}
          title={editingExpense ? "Edit Expense" : "Add Expense"}
        />
      </AppShell>
    </ProtectedView>
  );
}

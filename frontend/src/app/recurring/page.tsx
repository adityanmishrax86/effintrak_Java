"use client";

import { Suspense, useState } from "react";
import { useMutation, useQuery, useQueryClient } from "@tanstack/react-query";
import { useSearchParams } from "next/navigation";
import { Pause, Play, Plus, Trash2 } from "lucide-react";
import { AppShell } from "@/components/app-shell";
import { ProtectedView } from "@/components/protected-view";
import { api } from "@/lib/api";
import { useAuthStore } from "@/lib/auth";
import { DataTable, type Column } from "@/components/data-table";
import { Button } from "@/components/ui/button";
import { Badge } from "@/components/ui/badge";
import { Tabs, TabsContent, TabsList, TabsTrigger } from "@/components/ui/tabs";
import {
  Dialog,
  DialogContent,
  DialogHeader,
  DialogTitle,
  DialogFooter,
} from "@/components/ui/dialog";
import { Input } from "@/components/ui/input";
import { Label } from "@/components/ui/label";
import {
  Select,
  SelectContent,
  SelectItem,
  SelectTrigger,
  SelectValue,
} from "@/components/ui/select";
import { toast } from "sonner";
import { useUserSettings } from "@/lib/hooks/use-user-settings";
import { addDays, addWeeks, addMonths, addYears, format, differenceInDays } from "date-fns";

type UnifiedItem = {
  id: number;
  description: string;
  amount: number;
  frequency: string;
  type: "INCOME" | "EXPENSE" | "SUBSCRIPTION";
  category?: string;
  startDate: string;
  endDate?: string;
  status?: string;
  source: "recurring" | "subscription";
};

function computeNextOccurrence(startDate: string, frequency: string): string | null {
  const freq = frequency.toLowerCase();
  const start = new Date(startDate);
  let next = start;
  const now = new Date();

  const advanceFn =
    freq === "daily" ? addDays :
    freq === "weekly" ? addWeeks :
    freq === "monthly" ? addMonths :
    freq === "yearly" || freq === "annually" ? addYears : null;

  if (!advanceFn) return null;

  while (next <= now) {
    next = advanceFn(next, 1);
  }
  return format(next, "yyyy-MM-dd");
}

function getNextBadge(nextDate: string | null) {
  if (!nextDate) return <Badge variant="secondary">—</Badge>;
  const days = differenceInDays(new Date(nextDate), new Date());
  if (days <= 3) return <Badge variant="destructive">{nextDate} ({days}d)</Badge>;
  if (days <= 7) return <Badge variant="outline" className="border-amber-300 text-amber-700">{nextDate}</Badge>;
  return <Badge variant="secondary">{nextDate}</Badge>;
}

export default function RecurringPage() {
  return (
    <Suspense>
      <RecurringContent />
    </Suspense>
  );
}

function RecurringContent() {
  const queryClient = useQueryClient();
  const profile = useAuthStore((s) => s.profile);
  const { formatCurrency } = useUserSettings();
  const searchParams = useSearchParams();
  const initialTab = searchParams.get("tab") || "all";

  const [tab, setTab] = useState(initialTab);
  const [showCreate, setShowCreate] = useState(false);

  const [form, setForm] = useState({
    description: "",
    amount: "",
    frequency: "MONTHLY",
    type: "EXPENSE" as "INCOME" | "EXPENSE" | "SUBSCRIPTION",
    startDate: new Date().toISOString().split("T")[0],
    endDate: "",
    billingCycle: "MONTHLY",
  });

  const recurringQuery = useQuery({
    queryKey: ["recurring-transactions", profile?.id],
    queryFn: async () => {
      const p = profile ?? (await api.profile());
      return api.listRecurringTransactions(p.id, { size: 100 });
    },
  });

  const subscriptionsQuery = useQuery({
    queryKey: ["subscriptions", profile?.id],
    queryFn: async () => {
      const p = profile ?? (await api.profile());
      return api.listSubscriptions(p.id, { size: 100 });
    },
  });

  const allItems: UnifiedItem[] = [
    ...(recurringQuery.data?.content || []).map((r): UnifiedItem => ({
      id: r.id,
      description: r.description,
      amount: r.amount,
      frequency: r.frequency,
      type: r.type,
      category: r.category,
      startDate: r.startDate,
      endDate: r.endDate,
      status: r.status,
      source: "recurring",
    })),
    ...(subscriptionsQuery.data?.content || []).map((s): UnifiedItem => ({
      id: s.id,
      description: s.serviceName,
      amount: s.amount,
      frequency: s.billingCycle,
      type: "SUBSCRIPTION",
      category: s.category,
      startDate: s.startDate,
      endDate: s.endDate,
      status: s.status,
      source: "subscription",
    })),
  ];

  const filteredItems = allItems.filter((item) => {
    if (tab === "all") return true;
    if (tab === "subscriptions") return item.source === "subscription";
    if (tab === "expenses") return item.type === "EXPENSE";
    if (tab === "income") return item.type === "INCOME";
    return true;
  });

  const createMutation = useMutation({
    mutationFn: async () => {
      const p = profile ?? (await api.profile());
      if (form.type === "SUBSCRIPTION") {
        return api.createSubscription({
          userId: p.id,
          serviceName: form.description,
          amount: Number(form.amount),
          billingCycle: form.billingCycle,
          startDate: form.startDate,
        });
      }
      return api.createRecurringTransaction({
        userId: p.id,
        description: form.description,
        amount: Number(form.amount),
        frequency: form.frequency,
        type: form.type as "INCOME" | "EXPENSE",
        startDate: form.startDate,
        endDate: form.endDate || undefined,
      });
    },
    onSuccess: () => {
      toast.success("Created successfully");
      setShowCreate(false);
      setForm({ description: "", amount: "", frequency: "MONTHLY", type: "EXPENSE", startDate: new Date().toISOString().split("T")[0], endDate: "", billingCycle: "MONTHLY" });
      queryClient.invalidateQueries({ queryKey: ["recurring-transactions"] });
      queryClient.invalidateQueries({ queryKey: ["subscriptions"] });
    },
    onError: (e) => toast.error(e.message),
  });

  const toggleMutation = useMutation({
    mutationFn: async (item: UnifiedItem) => {
      const newStatus = item.status === "ACTIVE" ? "INACTIVE" : "ACTIVE";
      if (item.source === "subscription") {
        return api.updateSubscription(item.id, { status: newStatus });
      }
      return api.updateRecurringTransaction(item.id, { status: newStatus });
    },
    onSuccess: () => {
      toast.success("Status updated");
      queryClient.invalidateQueries({ queryKey: ["recurring-transactions"] });
      queryClient.invalidateQueries({ queryKey: ["subscriptions"] });
    },
  });

  const deleteMutation = useMutation({
    mutationFn: async (item: UnifiedItem) => {
      if (item.source === "subscription") return api.deleteSubscription(item.id);
      return api.deleteRecurringTransaction(item.id);
    },
    onSuccess: () => {
      toast.success("Deleted");
      queryClient.invalidateQueries({ queryKey: ["recurring-transactions"] });
      queryClient.invalidateQueries({ queryKey: ["subscriptions"] });
    },
  });

  const columns: Column<UnifiedItem>[] = [
    {
      key: "description",
      header: "Description",
      cell: (row) => (
        <div>
          <p className="font-medium">{row.description}</p>
          <p className="text-xs text-muted-foreground">{row.category || row.type.toLowerCase()}</p>
        </div>
      ),
    },
    {
      key: "amount",
      header: "Amount",
      cell: (row) => (
        <span className={row.type === "INCOME" ? "text-emerald-700 font-semibold" : "text-red-700 font-semibold"}>
          {row.type === "INCOME" ? "+" : "-"}{formatCurrency(row.amount)}
        </span>
      ),
    },
    {
      key: "frequency",
      header: "Frequency",
      cell: (row) => <Badge variant="outline" className="capitalize">{row.frequency.toLowerCase()}</Badge>,
    },
    {
      key: "next",
      header: "Next Due",
      cell: (row) => getNextBadge(computeNextOccurrence(row.startDate, row.frequency)),
    },
    {
      key: "status",
      header: "Status",
      cell: (row) => (
        <Badge variant={row.status === "ACTIVE" ? "default" : "secondary"}>
          {row.status || "ACTIVE"}
        </Badge>
      ),
    },
  ];

  return (
    <ProtectedView>
      <AppShell>
        <div className="space-y-4">
          <div className="flex items-center justify-between">
            <div>
              <h1 className="text-2xl font-bold">Recurring Transactions</h1>
              <p className="text-sm text-muted-foreground">
                Manage all recurring expenses, income, and subscriptions in one place.
              </p>
            </div>
            <Button onClick={() => setShowCreate(true)} className="gap-1">
              <Plus className="h-4 w-4" /> Add New
            </Button>
          </div>

          <Tabs value={tab} onValueChange={setTab}>
            <TabsList>
              <TabsTrigger value="all">All ({allItems.length})</TabsTrigger>
              <TabsTrigger value="subscriptions">
                Subscriptions ({allItems.filter(i => i.source === "subscription").length})
              </TabsTrigger>
              <TabsTrigger value="expenses">
                Expenses ({allItems.filter(i => i.type === "EXPENSE").length})
              </TabsTrigger>
              <TabsTrigger value="income">
                Income ({allItems.filter(i => i.type === "INCOME").length})
              </TabsTrigger>
            </TabsList>

            <TabsContent value={tab} className="mt-4">
              <DataTable
                columns={columns}
                data={filteredItems}
                searchPlaceholder="Search by description..."
                searchFn={(row, q) => row.description.toLowerCase().includes(q)}
                emptyMessage="No recurring transactions yet. Click 'Add New' to create one."
                actions={(row) => (
                  <div className="flex items-center gap-1">
                    <Button
                      variant="ghost"
                      size="icon-xs"
                      onClick={() => toggleMutation.mutate(row)}
                      title={row.status === "ACTIVE" ? "Pause" : "Resume"}
                    >
                      {row.status === "ACTIVE" ? <Pause className="h-3.5 w-3.5" /> : <Play className="h-3.5 w-3.5" />}
                    </Button>
                    <Button
                      variant="ghost"
                      size="icon-xs"
                      onClick={() => deleteMutation.mutate(row)}
                      className="text-destructive"
                    >
                      <Trash2 className="h-3.5 w-3.5" />
                    </Button>
                  </div>
                )}
              />
            </TabsContent>
          </Tabs>
        </div>

        <Dialog open={showCreate} onOpenChange={setShowCreate}>
          <DialogContent className="sm:max-w-md">
            <DialogHeader>
              <DialogTitle>Add Recurring Transaction</DialogTitle>
            </DialogHeader>
            <form
              onSubmit={(e) => { e.preventDefault(); createMutation.mutate(); }}
              className="grid gap-4 py-2"
            >
              <div className="grid gap-2">
                <Label>Type</Label>
                <Select value={form.type} onValueChange={(v) => setForm({ ...form, type: v as typeof form.type })}>
                  <SelectTrigger><SelectValue /></SelectTrigger>
                  <SelectContent>
                    <SelectItem value="EXPENSE">Expense</SelectItem>
                    <SelectItem value="INCOME">Income</SelectItem>
                    <SelectItem value="SUBSCRIPTION">Subscription</SelectItem>
                  </SelectContent>
                </Select>
              </div>
              <div className="grid gap-2">
                <Label>Description</Label>
                <Input value={form.description} onChange={(e) => setForm({ ...form, description: e.target.value })} placeholder={form.type === "SUBSCRIPTION" ? "e.g. Netflix, Spotify" : "e.g. Rent, Salary"} />
              </div>
              <div className="grid grid-cols-2 gap-3">
                <div className="grid gap-2">
                  <Label>Amount</Label>
                  <Input type="number" step="0.01" value={form.amount} onChange={(e) => setForm({ ...form, amount: e.target.value })} />
                </div>
                <div className="grid gap-2">
                  <Label>Frequency</Label>
                  <Select value={form.type === "SUBSCRIPTION" ? form.billingCycle : form.frequency} onValueChange={(v) => form.type === "SUBSCRIPTION" ? setForm({ ...form, billingCycle: v }) : setForm({ ...form, frequency: v })}>
                    <SelectTrigger><SelectValue /></SelectTrigger>
                    <SelectContent>
                      <SelectItem value="DAILY">Daily</SelectItem>
                      <SelectItem value="WEEKLY">Weekly</SelectItem>
                      <SelectItem value="MONTHLY">Monthly</SelectItem>
                      <SelectItem value="YEARLY">Yearly</SelectItem>
                    </SelectContent>
                  </Select>
                </div>
              </div>
              <div className="grid grid-cols-2 gap-3">
                <div className="grid gap-2">
                  <Label>Start Date</Label>
                  <Input type="date" value={form.startDate} onChange={(e) => setForm({ ...form, startDate: e.target.value })} />
                </div>
                {form.type !== "SUBSCRIPTION" && (
                  <div className="grid gap-2">
                    <Label>End Date (optional)</Label>
                    <Input type="date" value={form.endDate} onChange={(e) => setForm({ ...form, endDate: e.target.value })} />
                  </div>
                )}
              </div>
              <DialogFooter>
                <Button type="button" variant="outline" onClick={() => setShowCreate(false)}>Cancel</Button>
                <Button type="submit" disabled={createMutation.isPending}>
                  {createMutation.isPending ? "Creating..." : "Create"}
                </Button>
              </DialogFooter>
            </form>
          </DialogContent>
        </Dialog>
      </AppShell>
    </ProtectedView>
  );
}

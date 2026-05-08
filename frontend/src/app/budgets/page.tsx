"use client";

import { useState } from "react";
import { useMutation, useQuery, useQueryClient } from "@tanstack/react-query";
import { Trash2, Plus, Edit2 } from "lucide-react";
import { AppShell } from "@/components/app-shell";
import { ProtectedView } from "@/components/protected-view";
import { api } from "@/lib/api";
import { useAuthStore } from "@/lib/auth";
import { Button } from "@/components/ui/button";
import { Input } from "@/components/ui/input";
import { Label } from "@/components/ui/label";
import { Badge } from "@/components/ui/badge";
import { Select, SelectContent, SelectItem, SelectTrigger, SelectValue } from "@/components/ui/select";
import { Dialog, DialogContent, DialogHeader, DialogTitle, DialogFooter } from "@/components/ui/dialog";
import { toast } from "sonner";
import { useUserSettings } from "@/lib/hooks/use-user-settings";

export default function BudgetsPage() {
  const queryClient = useQueryClient();
  const profile = useAuthStore((s) => s.profile);
  const { formatCurrency } = useUserSettings();
  const [page, setPage] = useState(0);
  const [showCreate, setShowCreate] = useState(false);
  const [editingBudget, setEditingBudget] = useState<{ id: number; amount: string; endDate: string } | null>(null);

  const [name, setName] = useState("");
  const [amount, setAmount] = useState("");
  const [startDate, setStartDate] = useState("");
  const [endDate, setEndDate] = useState("");
  const [categoryId, setCategoryId] = useState("");

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
      setShowCreate(false);
      setName(""); setAmount(""); setStartDate(""); setEndDate(""); setCategoryId("");
      queryClient.invalidateQueries({ queryKey: ["budgets"] });
      toast.success("Budget created");
    },
    onError: (e) => toast.error(e.message),
  });

  const updateMutation = useMutation({
    mutationFn: () => api.updateBudget(editingBudget!.id, { amount: Number(editingBudget!.amount), endDate: editingBudget!.endDate }),
    onSuccess: () => {
      setEditingBudget(null);
      queryClient.invalidateQueries({ queryKey: ["budgets"] });
      toast.success("Budget updated");
    },
    onError: (e) => toast.error(e.message),
  });

  const deleteMutation = useMutation({
    mutationFn: (id: number) => api.deleteBudget(id),
    onSuccess: () => { queryClient.invalidateQueries({ queryKey: ["budgets"] }); toast.success("Budget deleted"); },
  });

  const canCreate = name.trim() && Number(amount) > 0 && startDate && endDate;

  return (
    <ProtectedView>
      <AppShell>
        <div className="space-y-4">
          <div className="flex items-center justify-between">
            <div>
              <h1 className="text-2xl font-bold">Budgets</h1>
              <p className="text-sm text-muted-foreground">Track spending against budget limits.</p>
            </div>
            <Button onClick={() => setShowCreate(true)}><Plus className="h-4 w-4 mr-1" /> New Budget</Button>
          </div>

          {/* Budget cards with progress bars */}
          <div className="grid gap-3 sm:grid-cols-2 lg:grid-cols-3">
            {budgetsQuery.data?.content?.map((row) => {
              const spent = Number(row.spent || 0);
              const budgetAmt = Number(row.amount || 1);
              const pct = Math.min((spent / budgetAmt) * 100, 100);
              const over = spent > budgetAmt;
              return (
                <div key={row.id} className="rounded-lg border bg-card p-4 space-y-3">
                  <div className="flex items-start justify-between">
                    <div>
                      <p className="font-semibold">{row.name}</p>
                      <p className="text-xs text-muted-foreground">{row.startDate} → {row.endDate}</p>
                    </div>
                    <div className="flex gap-1">
                      <Button variant="ghost" size="icon-xs" onClick={() => setEditingBudget({ id: row.id, amount: String(row.amount), endDate: row.endDate })}>
                        <Edit2 className="h-3.5 w-3.5" />
                      </Button>
                      <Button variant="ghost" size="icon-xs" className="text-destructive" onClick={() => deleteMutation.mutate(row.id)}>
                        <Trash2 className="h-3.5 w-3.5" />
                      </Button>
                    </div>
                  </div>
                  {/* Progress bar */}
                  <div className="space-y-1">
                    <div className="h-2.5 w-full rounded-full bg-muted">
                      <div
                        className={`h-2.5 rounded-full transition-all ${over ? "bg-destructive" : pct > 75 ? "bg-amber-500" : "bg-primary"}`}
                        style={{ width: `${pct}%` }}
                      />
                    </div>
                    <div className="flex justify-between text-xs">
                      <span className={over ? "text-destructive font-medium" : "text-muted-foreground"}>
                        {formatCurrency(spent)} spent
                      </span>
                      <span className="text-muted-foreground">{formatCurrency(budgetAmt)} limit</span>
                    </div>
                  </div>
                  {over && <Badge variant="destructive" className="text-[10px]">Over budget by {formatCurrency(spent - budgetAmt)}</Badge>}
                </div>
              );
            })}
          </div>

          {!budgetsQuery.data?.content?.length && (
            <p className="text-center py-12 text-muted-foreground">No budgets yet. Create one to start tracking spending.</p>
          )}

          {budgetsQuery.data && budgetsQuery.data.totalPages > 1 && (
            <div className="flex items-center justify-center gap-4">
              <Button variant="outline" size="sm" onClick={() => setPage((p) => Math.max(0, p - 1))} disabled={page === 0}>Previous</Button>
              <span className="text-sm text-muted-foreground">Page {page + 1} of {budgetsQuery.data.totalPages}</span>
              <Button variant="outline" size="sm" onClick={() => setPage((p) => p + 1)} disabled={budgetsQuery.data?.last ?? true}>Next</Button>
            </div>
          )}

          {/* Create Dialog */}
          <Dialog open={showCreate} onOpenChange={setShowCreate}>
            <DialogContent>
              <DialogHeader><DialogTitle>New Budget</DialogTitle></DialogHeader>
              <div className="grid gap-4 py-2">
                <div className="grid gap-2"><Label>Name</Label><Input value={name} onChange={(e) => setName(e.target.value)} placeholder="e.g. Groceries" autoFocus /></div>
                <div className="grid gap-2"><Label>Amount</Label><Input type="number" value={amount} onChange={(e) => setAmount(e.target.value)} placeholder="0.00" /></div>
                <div className="grid gap-2">
                  <Label>Category</Label>
                  <Select value={categoryId} onValueChange={setCategoryId}>
                    <SelectTrigger><SelectValue placeholder="Optional" /></SelectTrigger>
                    <SelectContent>
                      {categoriesQuery.data?.map((cat) => <SelectItem key={cat.id} value={String(cat.id)}>{cat.name}</SelectItem>)}
                    </SelectContent>
                  </Select>
                </div>
                <div className="grid grid-cols-2 gap-3">
                  <div className="grid gap-2"><Label>Start</Label><Input type="date" value={startDate} onChange={(e) => setStartDate(e.target.value)} /></div>
                  <div className="grid gap-2"><Label>End</Label><Input type="date" value={endDate} onChange={(e) => setEndDate(e.target.value)} /></div>
                </div>
              </div>
              <DialogFooter>
                <Button variant="outline" onClick={() => setShowCreate(false)}>Cancel</Button>
                <Button onClick={() => createMutation.mutate()} disabled={!canCreate || createMutation.isPending}>
                  {createMutation.isPending ? "Creating..." : "Create"}
                </Button>
              </DialogFooter>
            </DialogContent>
          </Dialog>

          {/* Edit Dialog */}
          <Dialog open={!!editingBudget} onOpenChange={(open) => { if (!open) setEditingBudget(null); }}>
            <DialogContent>
              <DialogHeader><DialogTitle>Edit Budget</DialogTitle></DialogHeader>
              <div className="grid gap-4 py-2">
                <div className="grid gap-2"><Label>Amount</Label><Input type="number" value={editingBudget?.amount ?? ""} onChange={(e) => setEditingBudget((prev) => prev ? { ...prev, amount: e.target.value } : null)} /></div>
                <div className="grid gap-2"><Label>End Date</Label><Input type="date" value={editingBudget?.endDate ?? ""} onChange={(e) => setEditingBudget((prev) => prev ? { ...prev, endDate: e.target.value } : null)} /></div>
              </div>
              <DialogFooter>
                <Button variant="outline" onClick={() => setEditingBudget(null)}>Cancel</Button>
                <Button onClick={() => updateMutation.mutate()} disabled={updateMutation.isPending}>Save</Button>
              </DialogFooter>
            </DialogContent>
          </Dialog>
        </div>
      </AppShell>
    </ProtectedView>
  );
}

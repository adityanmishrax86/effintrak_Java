"use client";

import { useState } from "react";
import { useMutation, useQuery, useQueryClient } from "@tanstack/react-query";
import { Plus, Target, TrendingUp } from "lucide-react";
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

export default function SavingsPage() {
  const queryClient = useQueryClient();
  const profile = useAuthStore((s) => s.profile);
  const { formatCurrency, formatDate } = useUserSettings();
  const [page, setPage] = useState(0);
  const [showCreate, setShowCreate] = useState(false);
  const [depositGoalId, setDepositGoalId] = useState<number | null>(null);
  const [depositAmount, setDepositAmount] = useState("");

  const [goalName, setGoalName] = useState("");
  const [targetAmount, setTargetAmount] = useState("");
  const [targetDate, setTargetDate] = useState("");
  const [frequency, setFrequency] = useState("");

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
      setShowCreate(false);
      setGoalName(""); setTargetAmount(""); setTargetDate(""); setFrequency("");
      queryClient.invalidateQueries({ queryKey: ["savings"] });
      toast.success("Savings goal created");
    },
    onError: (e) => toast.error(e.message),
  });

  const depositMutation = useMutation({
    mutationFn: (id: number) =>
      api.updateSavings(id, { currentAmount: Number(depositAmount) }),
    onSuccess: () => {
      setDepositGoalId(null);
      setDepositAmount("");
      queryClient.invalidateQueries({ queryKey: ["savings"] });
      toast.success("Deposit recorded");
    },
    onError: (e) => toast.error(e.message),
  });

  const deleteMutation = useMutation({
    mutationFn: (id: number) => api.deleteSavings(id),
    onSuccess: () => { queryClient.invalidateQueries({ queryKey: ["savings"] }); toast.success("Goal deleted"); },
  });

  const canCreate = goalName.trim() && Number(targetAmount) > 0 && targetDate;

  return (
    <ProtectedView>
      <AppShell>
        <div className="space-y-4">
          <div className="flex items-center justify-between">
            <div>
              <h1 className="text-2xl font-bold">Savings Goals</h1>
              <p className="text-sm text-muted-foreground">Track progress toward your financial goals.</p>
            </div>
            <Button onClick={() => setShowCreate(true)}><Plus className="h-4 w-4 mr-1" /> New Goal</Button>
          </div>

          {/* Goals grid */}
          <div className="grid gap-4 sm:grid-cols-2 lg:grid-cols-3">
            {savingsQuery.data?.content?.map((row) => {
              const current = Number(row.currentAmount || 0);
              const target = Number(row.targetAmount || 1);
              const remaining = Math.max(target - current, 0);
              const pct = Math.min((current / target) * 100, 100);
              const isComplete = pct >= 100;
              return (
                <div key={row.id} className="rounded-lg border bg-card p-4 space-y-3">
                  <div className="flex items-start justify-between">
                    <div className="flex items-center gap-2">
                      <Target className="h-4 w-4 text-muted-foreground" />
                      <p className="font-semibold">{row.goalName}</p>
                    </div>
                    {isComplete && <Badge className="text-[10px]">Complete</Badge>}
                  </div>

                  {/* Progress */}
                  <div className="space-y-1.5">
                    <div className="h-3 w-full rounded-full bg-muted">
                      <div
                        className={`h-3 rounded-full transition-all ${isComplete ? "bg-green-500" : "bg-primary"}`}
                        style={{ width: `${pct}%` }}
                      />
                    </div>
                    <div className="flex justify-between text-xs text-muted-foreground">
                      <span>Saved: {formatCurrency(current)}</span>
                      <span>Goal: {formatCurrency(target)}</span>
                    </div>
                    {!isComplete && (
                      <p className="text-xs text-muted-foreground">
                        {formatCurrency(remaining)} remaining · Target: {formatDate(row.targetDate)}
                      </p>
                    )}
                  </div>

                  {/* Actions */}
                  <div className="flex gap-2 pt-1">
                    {!isComplete && (
                      <Button
                        variant="outline"
                        size="sm"
                        className="flex-1"
                        onClick={() => { setDepositGoalId(row.id); setDepositAmount(String(current)); }}
                      >
                        <TrendingUp className="h-3 w-3 mr-1" /> Deposit
                      </Button>
                    )}
                    <Button
                      variant="ghost"
                      size="sm"
                      className="text-destructive"
                      onClick={() => deleteMutation.mutate(row.id)}
                    >
                      Delete
                    </Button>
                  </div>
                </div>
              );
            })}
          </div>

          {!savingsQuery.data?.content?.length && (
            <p className="text-center py-12 text-muted-foreground">No savings goals yet. Create one to start saving.</p>
          )}

          {savingsQuery.data && savingsQuery.data.totalPages > 1 && (
            <div className="flex items-center justify-center gap-4">
              <Button variant="outline" size="sm" onClick={() => setPage((p) => Math.max(0, p - 1))} disabled={page === 0}>Previous</Button>
              <span className="text-sm text-muted-foreground">Page {page + 1} of {savingsQuery.data.totalPages}</span>
              <Button variant="outline" size="sm" onClick={() => setPage((p) => p + 1)} disabled={savingsQuery.data?.last ?? true}>Next</Button>
            </div>
          )}

          {/* Create Dialog */}
          <Dialog open={showCreate} onOpenChange={setShowCreate}>
            <DialogContent>
              <DialogHeader><DialogTitle>New Savings Goal</DialogTitle></DialogHeader>
              <div className="grid gap-4 py-2">
                <div className="grid gap-2"><Label>Goal Name</Label><Input value={goalName} onChange={(e) => setGoalName(e.target.value)} placeholder="e.g. Emergency Fund" autoFocus /></div>
                <div className="grid gap-2"><Label>Target Amount</Label><Input type="number" value={targetAmount} onChange={(e) => setTargetAmount(e.target.value)} placeholder="0.00" /></div>
                <div className="grid gap-2"><Label>Target Date</Label><Input type="date" value={targetDate} onChange={(e) => setTargetDate(e.target.value)} /></div>
                <div className="grid gap-2">
                  <Label>Frequency (optional)</Label>
                  <Select value={frequency} onValueChange={setFrequency}>
                    <SelectTrigger><SelectValue placeholder="Select frequency" /></SelectTrigger>
                    <SelectContent>
                      <SelectItem value="weekly">Weekly</SelectItem>
                      <SelectItem value="monthly">Monthly</SelectItem>
                      <SelectItem value="quarterly">Quarterly</SelectItem>
                      <SelectItem value="yearly">Yearly</SelectItem>
                    </SelectContent>
                  </Select>
                </div>
              </div>
              <DialogFooter>
                <Button variant="outline" onClick={() => setShowCreate(false)}>Cancel</Button>
                <Button onClick={() => createMutation.mutate()} disabled={!canCreate || createMutation.isPending}>
                  {createMutation.isPending ? "Creating..." : "Create Goal"}
                </Button>
              </DialogFooter>
            </DialogContent>
          </Dialog>

          {/* Deposit Dialog */}
          <Dialog open={!!depositGoalId} onOpenChange={(open) => { if (!open) setDepositGoalId(null); }}>
            <DialogContent>
              <DialogHeader><DialogTitle>Update Saved Amount</DialogTitle></DialogHeader>
              <div className="grid gap-4 py-2">
                <div className="grid gap-2">
                  <Label>Current Saved Amount</Label>
                  <Input type="number" value={depositAmount} onChange={(e) => setDepositAmount(e.target.value)} autoFocus />
                </div>
              </div>
              <DialogFooter>
                <Button variant="outline" onClick={() => setDepositGoalId(null)}>Cancel</Button>
                <Button onClick={() => depositGoalId && depositMutation.mutate(depositGoalId)} disabled={depositMutation.isPending}>
                  Save
                </Button>
              </DialogFooter>
            </DialogContent>
          </Dialog>
        </div>
      </AppShell>
    </ProtectedView>
  );
}

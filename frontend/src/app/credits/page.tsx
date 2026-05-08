"use client";

import { useState } from "react";
import { useMutation, useQuery, useQueryClient } from "@tanstack/react-query";
import { AlertCircle, CheckCircle2, CreditCard, Plus } from "lucide-react";
import { AppShell } from "@/components/app-shell";
import { ProtectedView } from "@/components/protected-view";
import { api } from "@/lib/api";
import { useAuthStore } from "@/lib/auth";
import { Button } from "@/components/ui/button";
import { Input } from "@/components/ui/input";
import { Label } from "@/components/ui/label";
import { Badge } from "@/components/ui/badge";
import { Dialog, DialogContent, DialogHeader, DialogTitle, DialogFooter } from "@/components/ui/dialog";
import { toast } from "sonner";
import { useUserSettings } from "@/lib/hooks/use-user-settings";

export default function CreditsPage() {
  const queryClient = useQueryClient();
  const profile = useAuthStore((s) => s.profile);
  const { formatCurrency, formatDate } = useUserSettings();
  const [page, setPage] = useState(0);
  const [showCreate, setShowCreate] = useState(false);

  const [cardName, setCardName] = useState("");
  const [amount, setAmount] = useState("");
  const [dueDate, setDueDate] = useState("");
  const [creditLimit, setCreditLimit] = useState("");
  const [interestRate, setInterestRate] = useState("");

  const creditsQuery = useQuery({
    queryKey: ["credits", profile?.id, page],
    queryFn: async () => {
      const p = profile ?? (await api.profile());
      return api.listCredits(p.id, { page, size: 10 });
    },
  });

  const createMutation = useMutation({
    mutationFn: async () => {
      const p = profile ?? (await api.profile());
      return api.createCredit({
        userId: p.id,
        cardName: cardName.trim(),
        amount: Number(amount),
        dueDate,
        creditLimit: creditLimit ? Number(creditLimit) : undefined,
        interestRate: interestRate ? Number(interestRate) : undefined,
      });
    },
    onSuccess: () => {
      setShowCreate(false);
      setCardName(""); setAmount(""); setDueDate(""); setCreditLimit(""); setInterestRate("");
      queryClient.invalidateQueries({ queryKey: ["credits"] });
      toast.success("Credit bill added");
    },
    onError: (e) => toast.error(e.message),
  });

  const deleteMutation = useMutation({
    mutationFn: (id: number) => api.deleteCredit(id),
    onSuccess: () => { queryClient.invalidateQueries({ queryKey: ["credits"] }); toast.success("Marked as paid"); },
  });

  const canCreate = cardName.trim() && Number(amount) > 0 && dueDate;

  function getDaysUntilDue(dueDateStr: string) {
    const now = new Date();
    now.setHours(0, 0, 0, 0);
    const due = new Date(dueDateStr);
    return Math.ceil((due.getTime() - now.getTime()) / (1000 * 60 * 60 * 24));
  }

  return (
    <ProtectedView>
      <AppShell>
        <div className="space-y-4">
          <div className="flex items-center justify-between">
            <div>
              <h1 className="text-2xl font-bold">Credits</h1>
              <p className="text-sm text-muted-foreground">Track credit card bills and due dates.</p>
            </div>
            <Button onClick={() => setShowCreate(true)}><Plus className="h-4 w-4 mr-1" /> Add Bill</Button>
          </div>

          {/* Credit cards grid */}
          <div className="grid gap-3 sm:grid-cols-2 lg:grid-cols-3">
            {creditsQuery.data?.content?.map((row) => {
              const days = getDaysUntilDue(row.dueDate);
              const isOverdue = days < 0;
              const isDueSoon = days >= 0 && days <= 3;
              return (
                <div key={row.id} className={`rounded-lg border bg-card p-4 space-y-3 ${isOverdue ? "border-destructive/50" : ""}`}>
                  <div className="flex items-start justify-between">
                    <div className="flex items-center gap-2">
                      <CreditCard className="h-4 w-4 text-muted-foreground" />
                      <p className="font-semibold">{row.cardName}</p>
                    </div>
                    {isOverdue && <Badge variant="destructive">Overdue</Badge>}
                    {isDueSoon && !isOverdue && <Badge variant="outline" className="border-amber-400 text-amber-700">Due in {days}d</Badge>}
                    {days > 3 && <Badge variant="secondary">{days}d left</Badge>}
                  </div>

                  <div>
                    <p className="text-2xl font-bold">{formatCurrency(row.amount)}</p>
                    <p className="text-xs text-muted-foreground">Due: {formatDate(row.dueDate)}</p>
                    {row.interestRate ? <p className="text-xs text-muted-foreground">Interest: {row.interestRate}%</p> : null}
                    {row.creditLimit ? <p className="text-xs text-muted-foreground">Limit: {formatCurrency(row.creditLimit)}</p> : null}
                  </div>

                  <Button
                    variant="outline"
                    size="sm"
                    className="w-full"
                    onClick={() => deleteMutation.mutate(row.id)}
                  >
                    <CheckCircle2 className="h-3.5 w-3.5 mr-1" /> Mark Paid
                  </Button>
                </div>
              );
            })}
          </div>

          {!creditsQuery.data?.content?.length && (
            <div className="text-center py-12 space-y-2">
              <AlertCircle className="h-8 w-8 mx-auto text-muted-foreground/40" />
              <p className="text-muted-foreground">No credit bills. Add one to track due dates.</p>
            </div>
          )}

          {creditsQuery.data && creditsQuery.data.totalPages > 1 && (
            <div className="flex items-center justify-center gap-4">
              <Button variant="outline" size="sm" onClick={() => setPage((p) => Math.max(0, p - 1))} disabled={page === 0}>Previous</Button>
              <span className="text-sm text-muted-foreground">Page {page + 1} of {creditsQuery.data.totalPages}</span>
              <Button variant="outline" size="sm" onClick={() => setPage((p) => p + 1)} disabled={creditsQuery.data?.last ?? true}>Next</Button>
            </div>
          )}

          {/* Create Dialog */}
          <Dialog open={showCreate} onOpenChange={setShowCreate}>
            <DialogContent>
              <DialogHeader><DialogTitle>Add Credit Bill</DialogTitle></DialogHeader>
              <div className="grid gap-4 py-2">
                <div className="grid gap-2"><Label>Card Name</Label><Input value={cardName} onChange={(e) => setCardName(e.target.value)} placeholder="e.g. Visa Platinum" autoFocus /></div>
                <div className="grid gap-2"><Label>Bill Amount</Label><Input type="number" value={amount} onChange={(e) => setAmount(e.target.value)} placeholder="0.00" /></div>
                <div className="grid gap-2"><Label>Due Date</Label><Input type="date" value={dueDate} onChange={(e) => setDueDate(e.target.value)} /></div>
                <div className="grid grid-cols-2 gap-3">
                  <div className="grid gap-2"><Label>Credit Limit</Label><Input type="number" value={creditLimit} onChange={(e) => setCreditLimit(e.target.value)} placeholder="Optional" /></div>
                  <div className="grid gap-2"><Label>Interest Rate %</Label><Input type="number" step="0.1" value={interestRate} onChange={(e) => setInterestRate(e.target.value)} placeholder="Optional" /></div>
                </div>
              </div>
              <DialogFooter>
                <Button variant="outline" onClick={() => setShowCreate(false)}>Cancel</Button>
                <Button onClick={() => createMutation.mutate()} disabled={!canCreate || createMutation.isPending}>
                  {createMutation.isPending ? "Adding..." : "Add Bill"}
                </Button>
              </DialogFooter>
            </DialogContent>
          </Dialog>
        </div>
      </AppShell>
    </ProtectedView>
  );
}

"use client";

import { useState } from "react";
import { useMutation, useQuery, useQueryClient } from "@tanstack/react-query";
import { ArrowRight, Plus } from "lucide-react";
import { AppShell } from "@/components/app-shell";
import { ProtectedView } from "@/components/protected-view";
import { api } from "@/lib/api";
import { useAuthStore } from "@/lib/auth";
import { DataTable, type Column } from "@/components/data-table";
import { Button } from "@/components/ui/button";
import { Input } from "@/components/ui/input";
import { Label } from "@/components/ui/label";
import { Select, SelectContent, SelectItem, SelectTrigger, SelectValue } from "@/components/ui/select";
import { Dialog, DialogContent, DialogHeader, DialogTitle, DialogFooter } from "@/components/ui/dialog";
import { toast } from "sonner";
import { useUserSettings } from "@/lib/hooks/use-user-settings";

export default function TransfersPage() {
  const queryClient = useQueryClient();
  const profile = useAuthStore((s) => s.profile);
  const { formatCurrency, formatDate } = useUserSettings();
  const [showCreate, setShowCreate] = useState(false);

  const [amount, setAmount] = useState("");
  const [description, setDescription] = useState("");
  const [transferDate, setTransferDate] = useState(api.todayString());
  const [fromAccountId, setFromAccountId] = useState<string>("");
  const [toAccountId, setToAccountId] = useState<string>("");

  const accountsQuery = useQuery({
    queryKey: ["accounts-for-transfers", profile?.id],
    queryFn: async () => {
      const p = profile ?? (await api.profile());
      return api.listBankAccounts(p.id);
    },
  });

  const transfersQuery = useQuery({
    queryKey: ["transfers", profile?.id],
    queryFn: async () => {
      const p = profile ?? (await api.profile());
      return api.listTransfers(p.id);
    },
  });

  const createMutation = useMutation({
    mutationFn: async () => {
      const p = profile ?? (await api.profile());
      return api.createTransfer({
        amount: Number(amount),
        description: description.trim() || undefined,
        transferDate,
        fromAccountId: Number(fromAccountId),
        toAccountId: Number(toAccountId),
        userId: p.id,
      });
    },
    onSuccess: () => {
      setShowCreate(false);
      setAmount("");
      setDescription("");
      queryClient.invalidateQueries({ queryKey: ["transfers"] });
      queryClient.invalidateQueries({ queryKey: ["accounts"] });
      toast.success("Transfer created");
    },
    onError: (e) => toast.error(e.message),
  });

  const columns: Column<(typeof rows)[number]>[] = [
    { key: "transferDate", header: "Date", cell: (row) => formatDate(row.transferDate) },
    {
      key: "flow",
      header: "From → To",
      cell: (row) => (
        <span className="flex items-center gap-1 text-sm">
          {row.fromAccountName} <ArrowRight className="h-3 w-3 text-muted-foreground" /> {row.toAccountName}
        </span>
      ),
    },
    { key: "amount", header: "Amount", cell: (row) => formatCurrency(row.amount) },
    { key: "description", header: "Note", cell: (row) => row.description || "—" },
  ];

  const rows = (transfersQuery.data ?? []).map((t) => ({ ...t, flow: "" }));
  const canSubmit = Number(amount) > 0 && fromAccountId && toAccountId && fromAccountId !== toAccountId;

  return (
    <ProtectedView>
      <AppShell>
        <div className="space-y-4">
          <div className="flex items-center justify-between">
            <div>
              <h1 className="text-2xl font-bold">Transfers</h1>
              <p className="text-sm text-muted-foreground">Move money between your accounts.</p>
            </div>
            <Button onClick={() => setShowCreate(true)}><Plus className="h-4 w-4 mr-1" /> New Transfer</Button>
          </div>

          <DataTable columns={columns} data={rows} />

          <Dialog open={showCreate} onOpenChange={setShowCreate}>
            <DialogContent>
              <DialogHeader>
                <DialogTitle>New Transfer</DialogTitle>
              </DialogHeader>
              <div className="grid gap-4 py-2">
                <div className="grid gap-2">
                  <Label>Amount</Label>
                  <Input type="number" min="0.01" step="0.01" value={amount} onChange={(e) => setAmount(e.target.value)} placeholder="0.00" autoFocus />
                </div>
                <div className="grid grid-cols-2 gap-3">
                  <div className="grid gap-2">
                    <Label>From Account</Label>
                    <Select value={fromAccountId} onValueChange={setFromAccountId}>
                      <SelectTrigger><SelectValue placeholder="Select..." /></SelectTrigger>
                      <SelectContent>
                        {accountsQuery.data?.map((acc) => (
                          <SelectItem key={acc.id} value={String(acc.id)}>
                            {acc.name} {acc.balance != null && <span className="text-muted-foreground ml-1">({formatCurrency(acc.balance)})</span>}
                          </SelectItem>
                        ))}
                      </SelectContent>
                    </Select>
                  </div>
                  <div className="grid gap-2">
                    <Label>To Account</Label>
                    <Select value={toAccountId} onValueChange={setToAccountId}>
                      <SelectTrigger><SelectValue placeholder="Select..." /></SelectTrigger>
                      <SelectContent>
                        {accountsQuery.data?.filter((a) => String(a.id) !== fromAccountId).map((acc) => (
                          <SelectItem key={acc.id} value={String(acc.id)}>
                            {acc.name} {acc.balance != null && <span className="text-muted-foreground ml-1">({formatCurrency(acc.balance)})</span>}
                          </SelectItem>
                        ))}
                      </SelectContent>
                    </Select>
                  </div>
                </div>
                <div className="grid gap-2">
                  <Label>Date</Label>
                  <Input type="date" value={transferDate} onChange={(e) => setTransferDate(e.target.value)} />
                </div>
                <div className="grid gap-2">
                  <Label>Note (optional)</Label>
                  <Input value={description} onChange={(e) => setDescription(e.target.value)} placeholder="e.g. Monthly savings" />
                </div>
              </div>
              <DialogFooter>
                <Button variant="outline" onClick={() => setShowCreate(false)}>Cancel</Button>
                <Button onClick={() => createMutation.mutate()} disabled={!canSubmit || createMutation.isPending}>
                  {createMutation.isPending ? "Transferring..." : "Transfer"}
                </Button>
              </DialogFooter>
            </DialogContent>
          </Dialog>
        </div>
      </AppShell>
    </ProtectedView>
  );
}

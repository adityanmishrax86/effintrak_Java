"use client";

import { useForm } from "react-hook-form";
import { z } from "zod";
import {
  Dialog,
  DialogContent,
  DialogHeader,
  DialogTitle,
  DialogFooter,
} from "@/components/ui/dialog";
import { Button } from "@/components/ui/button";
import { Input } from "@/components/ui/input";
import { Label } from "@/components/ui/label";
import {
  Select,
  SelectContent,
  SelectItem,
  SelectTrigger,
  SelectValue,
} from "@/components/ui/select";
import type { BankAccount, Category } from "@/lib/types";

const baseSchema = z.object({
  description: z.string().min(1, "Description is required"),
  amount: z.coerce.number().positive("Amount must be positive"),
  date: z.string().min(1, "Date is required"),
  categoryId: z.coerce.number().min(1),
  bankAccountId: z.coerce.number().min(1),
});

const expenseSchema = baseSchema.extend({
  paymentMethod: z.string().min(1),
  paidTo: z.string().optional(),
});

const incomeSchema = baseSchema.extend({
  source: z.string().optional(),
  note: z.string().optional(),
});

export type TransactionKind = "expense" | "income";

type TransactionFormProps = {
  open: boolean;
  onOpenChange: (open: boolean) => void;
  kind: TransactionKind;
  categories: Category[];
  accounts: BankAccount[];
  defaultValues?: Record<string, unknown>;
  onSubmit: (values: Record<string, unknown>) => Promise<void> | void;
  isSubmitting?: boolean;
  title?: string;
};

export function TransactionForm({
  open,
  onOpenChange,
  kind,
  categories,
  accounts,
  defaultValues,
  onSubmit,
  isSubmitting,
  title,
}: TransactionFormProps) {
  const schema = kind === "expense" ? expenseSchema : incomeSchema;
  type FormValues = z.infer<typeof schema>;

  const form = useForm<FormValues>({
    defaultValues: {
      description: "",
      amount: 0,
      date: new Date().toISOString().split("T")[0],
      categoryId: categories[0]?.id || 1,
      bankAccountId: accounts[0]?.id || 1,
      ...(kind === "expense" ? { paymentMethod: "CARD", paidTo: "" } : { source: "", note: "" }),
      ...defaultValues,
    } as FormValues,
  });

  const handleSubmit = form.handleSubmit(async (values) => {
    await onSubmit(values as Record<string, unknown>);
    form.reset();
    onOpenChange(false);
  });

  return (
    <Dialog open={open} onOpenChange={onOpenChange}>
      <DialogContent className="sm:max-w-md">
        <DialogHeader>
          <DialogTitle>
            {title || (defaultValues?.id ? `Edit ${kind}` : `Add ${kind}`)}
          </DialogTitle>
        </DialogHeader>
        <form onSubmit={handleSubmit} className="grid gap-4 py-2">
          <div className="grid gap-2">
            <Label htmlFor="description">Description</Label>
            <Input id="description" {...form.register("description")} placeholder="What was it for?" />
            {form.formState.errors.description && (
              <p className="text-xs text-destructive">{form.formState.errors.description.message?.toString()}</p>
            )}
          </div>

          <div className="grid grid-cols-2 gap-3">
            <div className="grid gap-2">
              <Label htmlFor="amount">Amount</Label>
              <Input id="amount" type="number" step="0.01" {...form.register("amount", { valueAsNumber: true })} />
            </div>
            <div className="grid gap-2">
              <Label htmlFor="date">Date</Label>
              <Input id="date" type="date" {...form.register("date")} />
            </div>
          </div>

          <div className="grid grid-cols-2 gap-3">
            <div className="grid gap-2">
              <Label>Category</Label>
              <Select
                value={String(form.watch("categoryId"))}
                onValueChange={(val) => form.setValue("categoryId", Number(val) as never)}
              >
                <SelectTrigger>
                  <SelectValue placeholder="Select category" />
                </SelectTrigger>
                <SelectContent>
                  {categories.map((cat) => (
                    <SelectItem key={cat.id} value={String(cat.id)}>
                      {cat.name}
                    </SelectItem>
                  ))}
                </SelectContent>
              </Select>
            </div>
            <div className="grid gap-2">
              <Label>Account</Label>
              <Select
                value={String(form.watch("bankAccountId"))}
                onValueChange={(val) => form.setValue("bankAccountId", Number(val) as never)}
              >
                <SelectTrigger>
                  <SelectValue placeholder="Select account" />
                </SelectTrigger>
                <SelectContent>
                  {accounts.map((acc) => (
                    <SelectItem key={acc.id} value={String(acc.id)}>
                      {acc.name} (${acc.balance.toFixed(2)})
                    </SelectItem>
                  ))}
                </SelectContent>
              </Select>
            </div>
          </div>

          {kind === "expense" && (
            <div className="grid grid-cols-2 gap-3">
              <div className="grid gap-2">
                <Label>Payment Method</Label>
                <Select
                  value={String(form.watch("paymentMethod" as never) ?? "CARD")}
                  onValueChange={(val) => form.setValue("paymentMethod" as never, val as never)}
                >
                  <SelectTrigger>
                    <SelectValue />
                  </SelectTrigger>
                  <SelectContent>
                    <SelectItem value="CARD">Card</SelectItem>
                    <SelectItem value="UPI">UPI</SelectItem>
                    <SelectItem value="CASH">Cash</SelectItem>
                    <SelectItem value="BANK_TRANSFER">Bank Transfer</SelectItem>
                  </SelectContent>
                </Select>
              </div>
              <div className="grid gap-2">
                <Label htmlFor="paidTo">Paid To</Label>
                <Input id="paidTo" {...form.register("paidTo" as never)} placeholder="Merchant/Person" />
              </div>
            </div>
          )}

          {kind === "income" && (
            <div className="grid grid-cols-2 gap-3">
              <div className="grid gap-2">
                <Label htmlFor="source">Source</Label>
                <Input id="source" {...form.register("source" as never)} placeholder="Salary, Freelance..." />
              </div>
              <div className="grid gap-2">
                <Label htmlFor="note">Note</Label>
                <Input id="note" {...form.register("note" as never)} placeholder="Optional note" />
              </div>
            </div>
          )}

          <DialogFooter>
            <Button type="button" variant="outline" onClick={() => onOpenChange(false)}>
              Cancel
            </Button>
            <Button type="submit" disabled={isSubmitting}>
              {isSubmitting ? "Saving..." : defaultValues?.id ? "Update" : "Add"}
            </Button>
          </DialogFooter>
        </form>
      </DialogContent>
    </Dialog>
  );
}

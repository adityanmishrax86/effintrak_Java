"use client";

import { useEffect, useState } from "react";
import { useRouter } from "next/navigation";
import {
  CommandDialog,
  CommandEmpty,
  CommandGroup,
  CommandInput,
  CommandItem,
  CommandList,
  CommandSeparator,
} from "@/components/ui/command";
import {
  ArrowRightLeft,
  BarChart3,
  Bot,
  CircleDollarSign,
  CreditCard,
  Gem,
  LayoutDashboard,
  PiggyBank,
  Plus,
  Receipt,
  RotateCw,
  Settings,
  WalletCards,
} from "lucide-react";

type CommandPaletteProps = {
  onQuickAdd?: (kind: "expense" | "income" | "transfer") => void;
};

export function CommandPalette({ onQuickAdd }: CommandPaletteProps) {
  const [open, setOpen] = useState(false);
  const router = useRouter();

  useEffect(() => {
    function handler(e: KeyboardEvent) {
      if (e.key === "k" && (e.metaKey || e.ctrlKey)) {
        e.preventDefault();
        setOpen((o) => !o);
      }
    }
    window.addEventListener("keydown", handler);
    return () => window.removeEventListener("keydown", handler);
  }, []);

  const navigate = (path: string) => {
    router.push(path);
    setOpen(false);
  };

  const quickAdd = (kind: "expense" | "income" | "transfer") => {
    setOpen(false);
    onQuickAdd?.(kind);
  };

  return (
    <CommandDialog open={open} onOpenChange={setOpen}>
      <CommandInput placeholder="Type a command or search..." />
      <CommandList>
        <CommandEmpty>No results found.</CommandEmpty>
        <CommandGroup heading="Quick Actions">
          <CommandItem onSelect={() => quickAdd("expense")}>
            <Plus className="mr-2 h-4 w-4" />
            Add Expense
            <span className="ml-auto text-xs text-muted-foreground">⌘E</span>
          </CommandItem>
          <CommandItem onSelect={() => quickAdd("income")}>
            <Plus className="mr-2 h-4 w-4" />
            Add Income
            <span className="ml-auto text-xs text-muted-foreground">⌘I</span>
          </CommandItem>
          <CommandItem onSelect={() => quickAdd("transfer")}>
            <Plus className="mr-2 h-4 w-4" />
            Transfer Money
          </CommandItem>
          <CommandItem onSelect={() => navigate("/chat")}>
            <Bot className="mr-2 h-4 w-4" />
            Open AI Assistant
          </CommandItem>
        </CommandGroup>
        <CommandSeparator />
        <CommandGroup heading="Navigate">
          <CommandItem onSelect={() => navigate("/dashboard")}>
            <LayoutDashboard className="mr-2 h-4 w-4" />
            Dashboard
          </CommandItem>
          <CommandItem onSelect={() => navigate("/expenses")}>
            <Receipt className="mr-2 h-4 w-4" />
            Expenses
          </CommandItem>
          <CommandItem onSelect={() => navigate("/incomes")}>
            <CircleDollarSign className="mr-2 h-4 w-4" />
            Incomes
          </CommandItem>
          <CommandItem onSelect={() => navigate("/transfers")}>
            <ArrowRightLeft className="mr-2 h-4 w-4" />
            Transfers
          </CommandItem>
          <CommandItem onSelect={() => navigate("/budgets")}>
            <Gem className="mr-2 h-4 w-4" />
            Budgets
          </CommandItem>
          <CommandItem onSelect={() => navigate("/savings")}>
            <PiggyBank className="mr-2 h-4 w-4" />
            Savings
          </CommandItem>
          <CommandItem onSelect={() => navigate("/recurring")}>
            <RotateCw className="mr-2 h-4 w-4" />
            Recurring
          </CommandItem>
          <CommandItem onSelect={() => navigate("/credits")}>
            <CreditCard className="mr-2 h-4 w-4" />
            Credits
          </CommandItem>
          <CommandItem onSelect={() => navigate("/accounts")}>
            <WalletCards className="mr-2 h-4 w-4" />
            Accounts
          </CommandItem>
          <CommandItem onSelect={() => navigate("/reports")}>
            <BarChart3 className="mr-2 h-4 w-4" />
            Reports
          </CommandItem>
          <CommandItem onSelect={() => navigate("/settings")}>
            <Settings className="mr-2 h-4 w-4" />
            Settings
          </CommandItem>
        </CommandGroup>
      </CommandList>
    </CommandDialog>
  );
}

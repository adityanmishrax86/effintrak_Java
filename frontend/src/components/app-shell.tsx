"use client";

import Link from "next/link";
import { usePathname, useRouter } from "next/navigation";
import { useState, useCallback } from "react";
import {
  ArrowRightLeft,
  BarChart3,
  Bot,
  CircleDollarSign,
  CreditCard,
  Gem,
  LayoutDashboard,
  LogOut,
  PiggyBank,
  Receipt,
  RotateCw,
  Search,
  Settings,
  WalletCards,
} from "lucide-react";
import { api } from "@/lib/api";
import { useAuthStore } from "@/lib/auth";
import { useQuery } from "@tanstack/react-query";
import { Button } from "@/components/ui/button";
import { QuickAddMenu } from "./quick-add-menu";
import { CommandPalette } from "./command-palette";
import { TransactionForm, type TransactionKind } from "./transaction-form";
import { useMutation, useQueryClient } from "@tanstack/react-query";
import { toast } from "sonner";

const navGroups = [
  {
    label: "Overview",
    items: [
      { href: "/dashboard", label: "Dashboard", icon: LayoutDashboard },
      { href: "/reports", label: "Reports", icon: BarChart3 },
    ],
  },
  {
    label: "Money In / Out",
    items: [
      { href: "/expenses", label: "Expenses", icon: Receipt },
      { href: "/incomes", label: "Incomes", icon: CircleDollarSign },
      { href: "/transfers", label: "Transfers", icon: ArrowRightLeft },
    ],
  },
  {
    label: "Planning",
    items: [
      { href: "/budgets", label: "Budgets", icon: Gem },
      { href: "/savings", label: "Savings", icon: PiggyBank },
      { href: "/recurring", label: "Recurring", icon: RotateCw },
      { href: "/credits", label: "Credits", icon: CreditCard },
    ],
  },
  {
    label: "Setup",
    items: [
      { href: "/accounts", label: "Accounts", icon: WalletCards },
      { href: "/settings", label: "Settings", icon: Settings },
    ],
  },
  {
    label: "AI",
    items: [{ href: "/chat", label: "AI Assistant", icon: Bot }],
  },
];

export function AppShell({ children }: { children: React.ReactNode }) {
  const pathname = usePathname();
  const router = useRouter();
  const profile = useAuthStore((s) => s.profile);
  const queryClient = useQueryClient();
  const [quickAddKind, setQuickAddKind] = useState<TransactionKind | null>(null);

  const accountsQuery = useQuery({
    queryKey: ["accounts-global", profile?.id],
    queryFn: async () => {
      const p = profile ?? (await api.profile());
      return api.listBankAccounts(p.id);
    },
    enabled: !!profile,
  });

  const categoriesQuery = useQuery({
    queryKey: ["categories"],
    queryFn: api.listCategories,
    enabled: !!profile,
  });

  const createExpenseMutation = useMutation({
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

  const createIncomeMutation = useMutation({
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

  const handleQuickAdd = useCallback((kind: TransactionKind | "transfer") => {
    if (kind === "transfer") {
      router.push("/transfers");
    } else {
      setQuickAddKind(kind);
    }
  }, [router]);

  const handleSubmit = async (values: Record<string, unknown>) => {
    if (quickAddKind === "expense") {
      await createExpenseMutation.mutateAsync(values);
    } else {
      await createIncomeMutation.mutateAsync(values);
    }
  };

  const logout = async () => {
    await api.logout();
    router.replace("/login");
  };

  return (
    <div className="min-h-screen bg-gradient-to-br from-teal-50 via-white to-blue-50">
      <CommandPalette onQuickAdd={handleQuickAdd} />

      <header className="sticky top-0 z-40 border-b border-teal-100 bg-white/80 backdrop-blur-md shadow-sm">
        <div className="mx-auto flex w-full max-w-7xl items-center justify-between px-6 py-3">
          <div className="flex items-center gap-3">
            <div className="rounded-lg bg-gradient-to-br from-teal-600 to-teal-700 p-2">
              <WalletCards className="h-5 w-5 text-white" />
            </div>
            <div>
              <h1 className="text-lg font-bold text-zinc-900">EffinTrak</h1>
              <p className="text-xs text-zinc-500">Personal Finance</p>
            </div>
          </div>
          <div className="flex items-center gap-2">
            <Button
              variant="outline"
              size="sm"
              className="hidden sm:flex gap-2 text-muted-foreground"
              onClick={() => {
                window.dispatchEvent(new KeyboardEvent("keydown", { key: "k", metaKey: true }));
              }}
            >
              <Search className="h-3.5 w-3.5" />
              <span className="text-xs">Search</span>
              <kbd className="ml-2 rounded bg-muted px-1.5 py-0.5 text-[10px] font-mono">⌘K</kbd>
            </Button>
            <QuickAddMenu onAdd={handleQuickAdd} />
            <div className="hidden sm:flex items-center gap-2 px-3 py-1.5 rounded-lg bg-zinc-100">
              <div className="h-2 w-2 rounded-full bg-teal-600" />
              <span className="text-sm font-medium text-zinc-700">{profile?.username || profile?.email || "User"}</span>
            </div>
            <Button variant="ghost" size="icon-sm" onClick={logout}>
              <LogOut className="h-4 w-4" />
            </Button>
          </div>
        </div>
      </header>

      <div className="mx-auto flex w-full max-w-7xl gap-6 px-6 py-6">
        <aside className="h-fit w-56 rounded-xl bg-white border border-zinc-200 shadow-sm p-3 sticky top-20 hidden lg:block">
          <nav className="space-y-4">
            {navGroups.map((group) => (
              <div key={group.label}>
                <p className="px-3 mb-1 text-[11px] font-semibold uppercase tracking-wider text-zinc-400">
                  {group.label}
                </p>
                <div className="space-y-0.5">
                  {group.items.map(({ href, label, icon: Icon }) => {
                    const isActive = pathname === href;
                    return (
                      <Link
                        key={href}
                        href={href}
                        className={`flex items-center gap-2.5 rounded-lg px-3 py-2 text-sm font-medium transition-all duration-150 ${
                          isActive
                            ? "bg-gradient-to-r from-teal-600 to-teal-700 text-white shadow-sm"
                            : "text-zinc-600 hover:bg-zinc-100 hover:text-teal-700"
                        }`}
                      >
                        <Icon className="h-4 w-4 flex-shrink-0" />
                        <span>{label}</span>
                      </Link>
                    );
                  })}
                </div>
              </div>
            ))}
          </nav>
        </aside>

        <main className="min-w-0 flex-1">{children}</main>
      </div>

      {quickAddKind && (
        <TransactionForm
          open={!!quickAddKind}
          onOpenChange={(open) => !open && setQuickAddKind(null)}
          kind={quickAddKind}
          categories={categoriesQuery.data || []}
          accounts={accountsQuery.data || []}
          onSubmit={handleSubmit}
          isSubmitting={createExpenseMutation.isPending || createIncomeMutation.isPending}
        />
      )}
    </div>
  );
}

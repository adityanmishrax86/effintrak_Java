"use client";

import { useQuery } from "@tanstack/react-query";
import { useRouter } from "next/navigation";
import {
  ArrowRightLeft,
  Bot,
  CalendarClock,
  CircleDollarSign,
  Receipt,
  TrendingDown,
  TrendingUp,
  Wallet,
} from "lucide-react";
import { AppShell } from "@/components/app-shell";
import { ProtectedView } from "@/components/protected-view";
import { Button } from "@/components/ui/button";
import { api } from "@/lib/api";
import { useAuthStore } from "@/lib/auth";
import { useUserSettings } from "@/lib/hooks/use-user-settings";
import { addDays, addWeeks, addMonths, addYears, format, differenceInDays } from "date-fns";
import type { RecurringTransaction, Subscription } from "@/lib/types";

function computeNext(startDate: string, frequency: string): string | null {
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
  while (next <= now) next = advanceFn(next, 1);
  return format(next, "yyyy-MM-dd");
}

export default function DashboardPage() {
  const profile = useAuthStore((s) => s.profile);
  const router = useRouter();
  const { formatCurrency, formatDate } = useUserSettings();

  const dashboardQuery = useQuery({
    queryKey: ["dashboard", profile?.id],
    queryFn: async () => {
      const p = profile ?? (await api.profile());
      return api.dashboard(p.id);
    },
    enabled: !!profile?.id,
  });

  const recurringQuery = useQuery({
    queryKey: ["recurring-transactions", profile?.id],
    queryFn: async () => {
      const p = profile ?? (await api.profile());
      return api.listRecurringTransactions(p.id, { size: 50 });
    },
    enabled: !!profile?.id,
  });

  const subscriptionsQuery = useQuery({
    queryKey: ["subscriptions", profile?.id],
    queryFn: async () => {
      const p = profile ?? (await api.profile());
      return api.listSubscriptions(p.id, { size: 50 });
    },
    enabled: !!profile?.id,
  });

  const data = dashboardQuery.data;
  const netThisMonth = (data?.monthlyIncome || 0) - (data?.monthlyExpense || 0);

  // Coming up next 7 days
  const upcoming = [
    ...(recurringQuery.data?.content || []).filter(r => r.status === "ACTIVE").map(r => ({
      description: r.description,
      amount: r.amount,
      type: r.type,
      nextDate: computeNext(r.startDate, r.frequency),
    })),
    ...(subscriptionsQuery.data?.content || []).filter(s => s.status === "ACTIVE").map(s => ({
      description: s.serviceName,
      amount: s.amount,
      type: "EXPENSE" as const,
      nextDate: computeNext(s.startDate, s.billingCycle),
    })),
  ]
    .filter(item => item.nextDate && differenceInDays(new Date(item.nextDate!), new Date()) <= 7)
    .sort((a, b) => (a.nextDate! > b.nextDate! ? 1 : -1))
    .slice(0, 5);

  return (
    <ProtectedView>
      <AppShell>
        <div className="space-y-6">
          <div>
            <h1 className="text-2xl font-bold">Dashboard</h1>
            <p className="text-sm text-muted-foreground">Your financial overview at a glance.</p>
          </div>

          {dashboardQuery.isLoading ? (
            <div className="grid gap-4 md:grid-cols-3">
              {[1, 2, 3].map(i => (
                <div key={i} className="h-28 rounded-xl bg-muted animate-pulse" />
              ))}
            </div>
          ) : null}

          {data && (
            <>
              {/* Summary cards */}
              <div className="grid gap-4 md:grid-cols-4">
                <div className="rounded-xl border bg-card p-5 shadow-sm">
                  <div className="flex items-center justify-between">
                    <p className="text-sm font-medium text-muted-foreground">Total Balance</p>
                    <Wallet className="h-5 w-5 text-teal-600" />
                  </div>
                  <p className="mt-2 text-2xl font-bold">{formatCurrency(data.totalBalance)}</p>
                </div>
                <div className="rounded-xl border bg-card p-5 shadow-sm">
                  <div className="flex items-center justify-between">
                    <p className="text-sm font-medium text-muted-foreground">Income</p>
                    <TrendingUp className="h-5 w-5 text-emerald-600" />
                  </div>
                  <p className="mt-2 text-2xl font-bold text-emerald-700">+{formatCurrency(data.monthlyIncome)}</p>
                </div>
                <div className="rounded-xl border bg-card p-5 shadow-sm">
                  <div className="flex items-center justify-between">
                    <p className="text-sm font-medium text-muted-foreground">Expenses</p>
                    <TrendingDown className="h-5 w-5 text-red-600" />
                  </div>
                  <p className="mt-2 text-2xl font-bold text-red-700">-{formatCurrency(data.monthlyExpense)}</p>
                </div>
                <div className="rounded-xl border bg-card p-5 shadow-sm">
                  <div className="flex items-center justify-between">
                    <p className="text-sm font-medium text-muted-foreground">Net This Month</p>
                    <Wallet className="h-5 w-5 text-zinc-500" />
                  </div>
                  <p className={`mt-2 text-2xl font-bold ${netThisMonth >= 0 ? "text-emerald-700" : "text-red-700"}`}>
                    {netThisMonth >= 0 ? "+" : "-"}{formatCurrency(Math.abs(netThisMonth))}
                  </p>
                </div>
              </div>

              {/* Quick actions */}
              <div className="grid grid-cols-2 md:grid-cols-4 gap-3">
                <Button variant="outline" className="h-auto py-4 flex-col gap-2" onClick={() => router.push("/expenses")}>
                  <Receipt className="h-5 w-5 text-red-600" />
                  <span className="text-xs font-medium">Add Expense</span>
                </Button>
                <Button variant="outline" className="h-auto py-4 flex-col gap-2" onClick={() => router.push("/incomes")}>
                  <CircleDollarSign className="h-5 w-5 text-emerald-600" />
                  <span className="text-xs font-medium">Add Income</span>
                </Button>
                <Button variant="outline" className="h-auto py-4 flex-col gap-2" onClick={() => router.push("/transfers")}>
                  <ArrowRightLeft className="h-5 w-5 text-blue-600" />
                  <span className="text-xs font-medium">Transfer</span>
                </Button>
                <Button variant="outline" className="h-auto py-4 flex-col gap-2" onClick={() => router.push("/chat")}>
                  <Bot className="h-5 w-5 text-violet-600" />
                  <span className="text-xs font-medium">Ask AI</span>
                </Button>
              </div>

              {/* Two-column layout: Recent + Upcoming */}
              <div className="grid gap-4 md:grid-cols-2">
                {/* Recent transactions */}
                <div className="rounded-xl border bg-card p-5 shadow-sm">
                  <div className="flex items-center justify-between mb-4">
                    <h2 className="font-semibold">Recent Transactions</h2>
                    <Button variant="ghost" size="sm" onClick={() => router.push("/expenses")}>View all</Button>
                  </div>
                  <div className="space-y-2">
                    {data.recentTransactions?.slice(0, 6).map((tx) => (
                      <div key={tx.id} className="flex items-center justify-between py-2 border-b last:border-0">
                        <div>
                          <p className="text-sm font-medium">{tx.description}</p>
                          <p className="text-xs text-muted-foreground">{formatDate(tx.date)}</p>
                        </div>
                        <p className={`text-sm font-semibold ${tx.type === "INCOME" ? "text-emerald-700" : "text-red-700"}`}>
                          {tx.type === "INCOME" ? "+" : "-"}{formatCurrency(Math.abs(tx.amount))}
                        </p>
                      </div>
                    ))}
                    {!data.recentTransactions?.length && (
                      <p className="text-sm text-muted-foreground text-center py-4">No recent transactions</p>
                    )}
                  </div>
                </div>

                {/* Coming up */}
                <div className="rounded-xl border bg-card p-5 shadow-sm">
                  <div className="flex items-center justify-between mb-4">
                    <h2 className="font-semibold flex items-center gap-2">
                      <CalendarClock className="h-4 w-4" /> Coming Up (7 days)
                    </h2>
                    <Button variant="ghost" size="sm" onClick={() => router.push("/recurring")}>View all</Button>
                  </div>
                  <div className="space-y-2">
                    {upcoming.map((item, i) => (
                      <div key={i} className="flex items-center justify-between py-2 border-b last:border-0">
                        <div>
                          <p className="text-sm font-medium">{item.description}</p>
                          <p className="text-xs text-muted-foreground">{formatDate(item.nextDate!)}</p>
                        </div>
                        <p className={`text-sm font-semibold ${item.type === "INCOME" ? "text-emerald-700" : "text-red-700"}`}>
                          {item.type === "INCOME" ? "+" : "-"}{formatCurrency(item.amount)}
                        </p>
                      </div>
                    ))}
                    {upcoming.length === 0 && (
                      <p className="text-sm text-muted-foreground text-center py-4">Nothing due in the next 7 days</p>
                    )}
                  </div>
                </div>
              </div>
            </>
          )}
        </div>
      </AppShell>
    </ProtectedView>
  );
}

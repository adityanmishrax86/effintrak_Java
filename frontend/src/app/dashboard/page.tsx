"use client";

import { useQuery } from "@tanstack/react-query";
import { TrendingDown, TrendingUp, Wallet } from "lucide-react";
import { AppShell } from "@/components/app-shell";
import { ProtectedView } from "@/components/protected-view";
import { Card } from "@/components/ui";
import { api } from "@/lib/api";
import { useAuthStore } from "@/lib/auth";

export default function DashboardPage() {
  const profile = useAuthStore((s) => s.profile);

  const dashboardQuery = useQuery({
    queryKey: ["dashboard", profile?.id],
    queryFn: async () => {
      const ensuredProfile = profile ?? (await api.profile());
      if (!ensuredProfile?.id) {
        throw new Error("User profile not found");
      }
      return api.dashboard(ensuredProfile.id);
    },
    enabled: !!profile?.id,
  });

  const data = dashboardQuery.data;

  return (
    <ProtectedView>
      <AppShell>
        <div className="space-y-6">
          <div>
            <h1 className="text-3xl font-bold text-zinc-900">Financial Dashboard</h1>
            <p className="mt-1 text-zinc-600">Welcome back! Here&apos;s your financial overview.</p>
          </div>

          {dashboardQuery.isLoading ? (
            <Card className="text-center py-12">
              <div className="inline-block">
                <div className="animate-spin rounded-full h-8 w-8 border-b-2 border-teal-600"></div>
              </div>
              <p className="mt-3 text-zinc-600">Loading your dashboard...</p>
            </Card>
          ) : null}

          {dashboardQuery.error ? (
            <Card className="bg-red-50 border-red-200">
              <div className="text-red-800">
                <p className="font-semibold">Failed to load dashboard</p>
                <p className="text-sm mt-1">
                  {dashboardQuery.error instanceof Error
                    ? dashboardQuery.error.message
                    : "An error occurred"}
                </p>
              </div>
            </Card>
          ) : null}

          {data ? (
            <>
              <div className="grid gap-4 md:grid-cols-3">
                <Card>
                  <div className="flex items-start justify-between">
                    <div>
                      <p className="text-sm font-medium text-zinc-600">Total Balance</p>
                      <p className="mt-2 text-3xl font-bold text-zinc-900">
                        ${data.totalBalance?.toFixed(2) ?? "0.00"}
                      </p>
                    </div>
                    <div className="rounded-lg bg-teal-100 p-3">
                      <Wallet className="h-6 w-6 text-teal-700" />
                    </div>
                  </div>
                </Card>
                <Card>
                  <div className="flex items-start justify-between">
                    <div>
                      <p className="text-sm font-medium text-zinc-600">Monthly Income</p>
                      <p className="mt-2 text-3xl font-bold text-emerald-700">
                        ${data.monthlyIncome?.toFixed(2) ?? "0.00"}
                      </p>
                    </div>
                    <div className="rounded-lg bg-emerald-100 p-3">
                      <TrendingUp className="h-6 w-6 text-emerald-700" />
                    </div>
                  </div>
                </Card>
                <Card>
                  <div className="flex items-start justify-between">
                    <div>
                      <p className="text-sm font-medium text-zinc-600">Monthly Expense</p>
                      <p className="mt-2 text-3xl font-bold text-red-700">
                        ${data.monthlyExpense?.toFixed(2) ?? "0.00"}
                      </p>
                    </div>
                    <div className="rounded-lg bg-red-100 p-3">
                      <TrendingDown className="h-6 w-6 text-red-700" />
                    </div>
                  </div>
                </Card>
              </div>

              {data.recentTransactions && data.recentTransactions.length > 0 && (
                <Card>
                  <div className="space-y-4">
                    <div>
                      <h2 className="text-lg font-semibold text-zinc-900">Recent Transactions</h2>
                      <p className="text-sm text-zinc-600">Your latest account activity</p>
                    </div>
                    <div className="space-y-3">
                      {data.recentTransactions.map((tx) => (
                        <div
                          key={tx.id}
                          className="flex items-center justify-between p-3 rounded-lg bg-zinc-50 hover:bg-zinc-100 transition"
                        >
                          <div>
                            <p className="font-medium text-zinc-900">{tx.description}</p>
                            <p className="text-xs text-zinc-600">{tx.date} • {tx.type}</p>
                          </div>
                          <p
                            className={`font-semibold ${
                              tx.type === "INCOME" ? "text-emerald-700" : "text-red-700"
                            }`}
                          >
                            {tx.type === "INCOME" ? "+" : "-"}${Math.abs(tx.amount).toFixed(2)}
                          </p>
                        </div>
                      ))}
                    </div>
                  </div>
                </Card>
              )}
            </>
          ) : null}
        </div>
      </AppShell>
    </ProtectedView>
  );
}

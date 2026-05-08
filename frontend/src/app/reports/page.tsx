"use client";

import { useMemo, useState } from "react";
import { useQuery } from "@tanstack/react-query";
import { TrendingDown, TrendingUp, PieChart, BarChart3, Calendar } from "lucide-react";
import { AppShell } from "@/components/app-shell";
import { ProtectedView } from "@/components/protected-view";
import { Card } from "@/components/ui";
import { api } from "@/lib/api";
import { useAuthStore } from "@/lib/auth";
import { useUserSettings } from "@/lib/hooks/use-user-settings";

function parseDate(dateStr: string): Date {
  return new Date(dateStr + "T00:00:00Z");
}

export default function ReportsPage() {
  const profile = useAuthStore((s) => s.profile);
  const { formatCurrency, formatDate } = useUserSettings();
  const [month, setMonth] = useState(() => {
    const now = new Date();
    return now.getFullYear() + "-" + String(now.getMonth() + 1).padStart(2, "0");
  });

  const expensesQuery = useQuery({
    queryKey: ["expenses-analytics", profile?.id],
    queryFn: async () => {
      const p = profile ?? (await api.profile());
      return api.listExpenses(p.id, { size: 1000 });
    },
  });

  const incomesQuery = useQuery({
    queryKey: ["incomes-analytics", profile?.id],
    queryFn: async () => {
      const p = profile ?? (await api.profile());
      return api.listIncomes(p.id, { size: 1000 });
    },
  });

  // Analytics computations
  const analytics = useMemo(() => {
    const expenses = expensesQuery.data?.content || [];
    const incomes = incomesQuery.data?.content || [];

    const monthStart = new Date(month + "-01");
    const monthEnd = new Date(monthStart.getFullYear(), monthStart.getMonth() + 1, 0);

    // Filter by selected month
    const monthlyExpenses = expenses.filter((e) => {
      const d = parseDate(e.date);
      return d >= monthStart && d <= monthEnd;
    });

    const monthlyIncomes = incomes.filter((i) => {
      const d = parseDate(i.date);
      return d >= monthStart && d <= monthEnd;
    });

    // Calculate totals
    const totalExpenses = monthlyExpenses.reduce((sum, e) => sum + Number(e.amount || 0), 0);
    const totalIncomes = monthlyIncomes.reduce((sum, i) => sum + Number(i.amount || 0), 0);
    const netIncome = totalIncomes - totalExpenses;

    // Spending by category
    const categoryMap = new Map<string, number>();
    monthlyExpenses.forEach((e) => {
      const cat = e.category || "Uncategorized";
      categoryMap.set(cat, (categoryMap.get(cat) || 0) + Number(e.amount || 0));
    });

    const topCategories = Array.from(categoryMap.entries())
      .sort((a, b) => b[1] - a[1])
      .slice(0, 5);

    // Top expenses
    const topExpenses = monthlyExpenses
      .sort((a, b) => Number(b.amount || 0) - Number(a.amount || 0))
      .slice(0, 5);

    // Monthly trend (last 12 months)
    const monthlyTrend: Record<string, { income: number; expense: number }> = {};
    for (let i = 11; i >= 0; i--) {
      const d = new Date();
      d.setMonth(d.getMonth() - i);
      const key = d.getFullYear() + "-" + String(d.getMonth() + 1).padStart(2, "0");
      const start = new Date(key + "-01");
      const end = new Date(start.getFullYear(), start.getMonth() + 1, 0);

      const exp = expenses.filter((e) => {
        const ed = parseDate(e.date);
        return ed >= start && ed <= end;
      });
      const inc = incomes.filter((i) => {
        const id = parseDate(i.date);
        return id >= start && id <= end;
      });

      monthlyTrend[key] = {
        income: inc.reduce((sum, i) => sum + Number(i.amount || 0), 0),
        expense: exp.reduce((sum, e) => sum + Number(e.amount || 0), 0),
      };
    }

    return {
      totalExpenses,
      totalIncomes,
      netIncome,
      topCategories,
      topExpenses,
      monthlyTrend,
      averageExpense:
        monthlyExpenses.length > 0 ? totalExpenses / monthlyExpenses.length : 0,
      averageExpenseCat:
        topCategories.length > 0 ? totalExpenses / topCategories.length : 0,
    };
  }, [expensesQuery.data, incomesQuery.data, month]);

  const isLoading = expensesQuery.isLoading || incomesQuery.isLoading;

  return (
    <ProtectedView>
      <AppShell>
        <div className="space-y-6">
          <div>
            <h1 className="text-3xl font-bold text-zinc-900">Financial Reports</h1>
            <p className="mt-1 text-zinc-600">Analyze your spending and track financial trends.</p>
          </div>

          {isLoading ? (
            <Card className="text-center py-12">
              <div className="animate-pulse">
                <p className="text-zinc-600">Loading analytics...</p>
              </div>
            </Card>
          ) : (
            <>
              {/* Month selector */}
              <Card>
                <div className="flex items-end gap-4">
                  <div className="flex-1">
                    <label className="block text-sm font-medium text-zinc-700 mb-2">
                      <Calendar className="inline h-4 w-4 mr-2" />
                      Select Month
                    </label>
                    <input
                      type="month"
                      value={month}
                      onChange={(e) => setMonth(e.target.value)}
                      className="w-full rounded-lg border border-zinc-300 px-3 py-2 text-sm focus:outline-none focus:ring-2 focus:ring-teal-500"
                    />
                  </div>
                </div>
              </Card>

              {/* Key metrics */}
              <div className="grid gap-4 md:grid-cols-3">
                <Card>
                  <div className="flex items-start justify-between">
                    <div>
                      <p className="text-sm font-medium text-zinc-600">Total Income</p>
                      <p className="mt-2 text-3xl font-bold text-emerald-700">
                        {formatCurrency(analytics.totalIncomes)}
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
                      <p className="text-sm font-medium text-zinc-600">Total Expenses</p>
                      <p className="mt-2 text-3xl font-bold text-red-700">
                        {formatCurrency(analytics.totalExpenses)}
                      </p>
                    </div>
                    <div className="rounded-lg bg-red-100 p-3">
                      <TrendingDown className="h-6 w-6 text-red-700" />
                    </div>
                  </div>
                </Card>

                <Card>
                  <div className="flex items-start justify-between">
                    <div>
                      <p className="text-sm font-medium text-zinc-600">Net Income</p>
                      <p
                        className={`mt-2 text-3xl font-bold ${
                          analytics.netIncome >= 0 ? "text-teal-700" : "text-red-700"
                        }`}
                      >
                        {formatCurrency(analytics.netIncome)}
                      </p>
                    </div>
                    <div
                      className={`rounded-lg p-3 ${
                        analytics.netIncome >= 0 ? "bg-teal-100" : "bg-red-100"
                      }`}
                    >
                      <BarChart3
                        className={`h-6 w-6 ${
                          analytics.netIncome >= 0 ? "text-teal-700" : "text-red-700"
                        }`}
                      />
                    </div>
                  </div>
                </Card>
              </div>

              {/* Top spending categories */}
              {analytics.topCategories.length > 0 && (
                <Card>
                  <div className="space-y-4">
                    <h2 className="text-lg font-semibold text-zinc-900 flex items-center gap-2">
                      <PieChart className="h-5 w-5 text-teal-600" />
                      Top Spending Categories
                    </h2>
                    <div className="space-y-3">
                      {analytics.topCategories.map(([category, amount], idx) => {
                        const percentage =
                          analytics.totalExpenses > 0 ? (amount / analytics.totalExpenses) * 100 : 0;
                        return (
                          <div key={idx}>
                            <div className="flex items-center justify-between mb-1">
                              <span className="font-medium text-zinc-900">{category}</span>
                              <span className="text-sm text-zinc-600">{formatCurrency(amount)}</span>
                            </div>
                            <div className="h-2 w-full rounded-full bg-zinc-200">
                              <div
                                className="h-2 rounded-full bg-gradient-to-r from-teal-600 to-blue-600"
                                style={{ width: `${percentage}%` }}
                              />
                            </div>
                            <p className="text-xs text-zinc-500 mt-1">{Math.round(percentage)}% of total expenses</p>
                          </div>
                        );
                      })}
                    </div>
                  </div>
                </Card>
              )}

              {/* Key insights */}
              <Card>
                <div className="space-y-4">
                  <h2 className="text-lg font-semibold text-zinc-900">Key Insights</h2>
                  <div className="grid gap-3 md:grid-cols-2">
                    <div className="p-3 rounded-lg bg-blue-50 border border-blue-200">
                      <p className="text-sm text-blue-900">
                        <strong>Average Expense:</strong> {formatCurrency(analytics.averageExpense)} per transaction
                      </p>
                    </div>
                    <div className="p-3 rounded-lg bg-purple-50 border border-purple-200">
                      <p className="text-sm text-purple-900">
                        <strong>Avg per Category:</strong> {formatCurrency(analytics.averageExpenseCat)}
                      </p>
                    </div>
                    <div
                      className={`p-3 rounded-lg border ${
                        analytics.netIncome >= 0
                          ? "bg-emerald-50 border-emerald-200"
                          : "bg-red-50 border-red-200"
                      }`}
                    >
                      <p
                        className={`text-sm ${
                          analytics.netIncome >= 0 ? "text-emerald-900" : "text-red-900"
                        }`}
                      >
                        <strong>Net Cash Flow:</strong> {analytics.netIncome >= 0 ? "Positive" : "Negative"}
                      </p>
                    </div>
                    <div className="p-3 rounded-lg bg-orange-50 border border-orange-200">
                      <p className="text-sm text-orange-900">
                        <strong>Expense Ratio:</strong>{" "}
                        {analytics.totalIncomes > 0
                          ? ((analytics.totalExpenses / analytics.totalIncomes) * 100).toFixed(1)
                          : "0"}
                        %
                      </p>
                    </div>
                  </div>
                </div>
              </Card>

              {/* Top expenses table */}
              {analytics.topExpenses.length > 0 && (
                <Card>
                  <div className="space-y-4">
                    <h2 className="text-lg font-semibold text-zinc-900">Largest Expenses</h2>
                    <div className="space-y-2 max-h-72 overflow-y-auto">
                      {analytics.topExpenses.map((exp) => (
                        <div
                          key={exp.id}
                          className="flex items-center justify-between p-3 rounded-lg bg-zinc-50 border border-zinc-200 hover:bg-zinc-100 transition"
                        >
                          <div className="flex-1">
                            <p className="font-medium text-zinc-900">{exp.description}</p>
                            <p className="text-xs text-zinc-600">
                              {formatDate(exp.date)} • {exp.category || "Uncategorized"}
                            </p>
                          </div>
                          <p className="font-semibold text-red-700">{formatCurrency(exp.amount)}</p>
                        </div>
                      ))}
                    </div>
                  </div>
                </Card>
              )}

              {/* Monthly trend summary */}
              <Card>
                <div className="space-y-4">
                  <h2 className="text-lg font-semibold text-zinc-900">12-Month Trend</h2>
                  <div className="space-y-3">
                    {Object.entries(analytics.monthlyTrend).map(([monthKey, data]) => {
                      const balance = data.income - data.expense;
                      return (
                        <div key={monthKey}>
                          <div className="flex items-center justify-between mb-1">
                            <span className="text-sm font-medium text-zinc-700">{monthKey}</span>
                            <span className={`text-sm font-semibold ${balance >= 0 ? "text-emerald-600" : "text-red-600"}`}>
                              {balance >= 0 ? "+" : ""}${balance.toFixed(2)}
                            </span>
                          </div>
                          <div className="flex gap-2 h-6">
                            <div className="flex-1 rounded bg-emerald-100 relative">
                              {data.income > 0 && (
                                <div
                                  className="h-full bg-emerald-600 rounded"
                                  style={{
                                    width: `${Math.min((data.income / Math.max(...Object.values(analytics.monthlyTrend).map(m => m.income)) || 1) * 100, 100)}%`,
                                  }}
                                  title={`Income: $${data.income.toFixed(2)}`}
                                />
                              )}
                            </div>
                            <div className="flex-1 rounded bg-red-100 relative">
                              {data.expense > 0 && (
                                <div
                                  className="h-full bg-red-600 rounded"
                                  style={{
                                    width: `${Math.min((data.expense / Math.max(...Object.values(analytics.monthlyTrend).map(m => m.expense)) || 1) * 100, 100)}%`,
                                  }}
                                  title={`Expense: $${data.expense.toFixed(2)}`}
                                />
                              )}
                            </div>
                          </div>
                        </div>
                      );
                    })}
                  </div>
                  <div className="flex gap-4 mt-4 text-sm">
                    <div className="flex items-center gap-2">
                      <div className="h-3 w-3 rounded bg-emerald-600" />
                      <span className="text-zinc-600">Income</span>
                    </div>
                    <div className="flex items-center gap-2">
                      <div className="h-3 w-3 rounded bg-red-600" />
                      <span className="text-zinc-600">Expenses</span>
                    </div>
                  </div>
                </div>
              </Card>
            </>
          )}
        </div>
      </AppShell>
    </ProtectedView>
  );
}

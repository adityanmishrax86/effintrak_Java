"use client";

import Link from "next/link";
import { usePathname, useRouter } from "next/navigation";
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
  Settings,
  WalletCards,
} from "lucide-react";
import { api } from "@/lib/api";
import { useAuthStore } from "@/lib/auth";
import { Button } from "./ui";

const navItems = [
  { href: "/dashboard", label: "Dashboard", icon: LayoutDashboard },
  { href: "/accounts", label: "Accounts", icon: WalletCards },
  { href: "/expenses", label: "Expenses", icon: Receipt },
  { href: "/incomes", label: "Incomes", icon: CircleDollarSign },
  { href: "/transfers", label: "Transfers", icon: ArrowRightLeft },
  { href: "/budgets", label: "Budgets", icon: Gem },
  { href: "/savings", label: "Savings", icon: PiggyBank },
  { href: "/subscriptions", label: "Subscriptions", icon: RotateCw },
  { href: "/credits", label: "Credits", icon: CreditCard },
  { href: "/recurring", label: "Recurring", icon: RotateCw },
  { href: "/reports", label: "Reports", icon: BarChart3 },
  { href: "/chat", label: "AI Assistant", icon: Bot },
  { href: "/settings", label: "Settings", icon: Settings },
];

export function AppShell({ children }: { children: React.ReactNode }) {
  const pathname = usePathname();
  const router = useRouter();
  const profile = useAuthStore((s) => s.profile);

  const logout = async () => {
    await api.logout();
    router.replace("/login");
  };

  return (
    <div className="min-h-screen bg-gradient-to-br from-teal-50 via-white to-blue-50">
      <header className="sticky top-0 border-b border-teal-100 bg-white/80 backdrop-blur-md shadow-sm">
        <div className="mx-auto flex w-full max-w-7xl items-center justify-between px-6 py-4">
          <div className="flex items-center gap-3">
            <div className="rounded-lg bg-gradient-to-br from-teal-600 to-teal-700 p-2">
              <WalletCards className="h-5 w-5 text-white" />
            </div>
            <div>
              <h1 className="text-lg font-bold text-zinc-900">EffinTrak</h1>
              <p className="text-xs text-zinc-500">Personal Finance Management</p>
            </div>
          </div>
          <div className="flex items-center gap-4 text-sm">
            <div className="hidden sm:flex items-center gap-2 px-3 py-2 rounded-lg bg-zinc-100">
              <div className="h-2 w-2 rounded-full bg-teal-600"></div>
              <span className="font-medium text-zinc-700">{profile?.username || profile?.email || "User"}</span>
            </div>
            <Button
              variant="ghost"
              size="sm"
              onClick={logout}
              className="flex items-center gap-2"
            >
              <LogOut className="h-4 w-4" />
              Logout
            </Button>
          </div>
        </div>
      </header>

      <div className="mx-auto flex w-full max-w-7xl gap-6 px-6 py-8">
        <aside className="h-fit w-64 rounded-xl bg-white border border-zinc-200 shadow-sm p-4 sticky top-24">
          <nav className="space-y-1">
            {navItems.map(({ href, label, icon: Icon }) => {
              const isActive = pathname === href;
              return (
                <Link
                  key={href}
                  href={href}
                  className={`flex items-center gap-3 rounded-lg px-3 py-2.5 text-sm font-medium transition-all duration-200 ${
                    isActive
                      ? "bg-gradient-to-r from-teal-600 to-teal-700 text-white shadow-md"
                      : "text-zinc-700 hover:bg-zinc-100 hover:text-teal-700"
                  }`}
                >
                  <Icon className="h-4 w-4 flex-shrink-0" />
                  <span>{label}</span>
                </Link>
              );
            })}
          </nav>
          <div className="mt-6 pt-4 border-t border-zinc-200">
            <p className="text-xs text-zinc-500 px-3">Financial management made simple</p>
          </div>
        </aside>

        <main className="min-w-0 flex-1">{children}</main>
      </div>
    </div>
  );
}

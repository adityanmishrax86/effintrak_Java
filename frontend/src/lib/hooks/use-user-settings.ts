"use client";

import { useQuery } from "@tanstack/react-query";
import { api } from "@/lib/api";
import { useAuthStore } from "@/lib/auth";
import type { UserSettings } from "@/lib/types";

const CURRENCY_SYMBOLS: Record<string, string> = {
  USD: "$",
  EUR: "€",
  GBP: "£",
  INR: "₹",
  JPY: "¥",
  CNY: "¥",
  KRW: "₩",
  BRL: "R$",
  AUD: "A$",
  CAD: "C$",
  CHF: "CHF",
  SEK: "kr",
  NOK: "kr",
  DKK: "kr",
  PLN: "zł",
  CZK: "Kč",
  HUF: "Ft",
  RUB: "₽",
  TRY: "₺",
  ZAR: "R",
  MXN: "$",
  SGD: "S$",
  HKD: "HK$",
  NZD: "NZ$",
  THB: "฿",
  PHP: "₱",
  IDR: "Rp",
  MYR: "RM",
  VND: "₫",
  AED: "د.إ",
  SAR: "﷼",
};

function formatDateWithPattern(dateStr: string, pattern: string): string {
  if (!dateStr) return "";
  const date = new Date(dateStr);
  if (Number.isNaN(date.getTime())) return dateStr;

  const day = String(date.getDate()).padStart(2, "0");
  const month = String(date.getMonth() + 1).padStart(2, "0");
  const year = date.getFullYear();

  switch (pattern) {
    case "DD/MM/YYYY":
      return `${day}/${month}/${year}`;
    case "MM/DD/YYYY":
      return `${month}/${day}/${year}`;
    case "YYYY-MM-DD":
      return `${year}-${month}-${day}`;
    case "DD-MM-YYYY":
      return `${day}-${month}-${year}`;
    case "DD.MM.YYYY":
      return `${day}.${month}.${year}`;
    default:
      return `${year}-${month}-${day}`;
  }
}

export function useUserSettings() {
  const profile = useAuthStore((s) => s.profile);

  const { data: settings } = useQuery({
    queryKey: ["user-settings"],
    queryFn: () => api.getSettings(),
    enabled: !!profile?.id,
    staleTime: 5 * 60 * 1000, // cache for 5 minutes
  });

  const currencyCode = settings?.currencyCode || "USD";
  const dateFormat = settings?.dateFormat || "YYYY-MM-DD";
  const symbol = CURRENCY_SYMBOLS[currencyCode] || currencyCode;

  function formatCurrency(amount: number | string | undefined | null): string {
    const num = Number(amount || 0);
    return `${symbol}${num.toFixed(2)}`;
  }

  function formatDate(dateStr: string | undefined | null): string {
    if (!dateStr) return "";
    return formatDateWithPattern(dateStr, dateFormat);
  }

  return {
    settings: settings as UserSettings | undefined,
    currencyCode,
    currencySymbol: symbol,
    dateFormat,
    formatCurrency,
    formatDate,
  };
}

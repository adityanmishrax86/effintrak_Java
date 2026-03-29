"use client";

import { useEffect, useRef } from "react";
import { useRouter } from "next/navigation";
import { useAuthStore } from "@/lib/auth";

type Props = {
  children: React.ReactNode;
};

export function ProtectedView({ children }: Props) {
  const router = useRouter();
  const checkedRef = useRef(false);
  const tokens = useAuthStore((s) => s.tokens);

  // eslint-disable-next-line react-hooks/exhaustive-deps
  useEffect(() => {
    // Only check once on mount
    if (checkedRef.current) return;
    checkedRef.current = true;

    if (!tokens) {
      // Not authenticated, redirect to login
      router.replace("/login");
    }
  }, []); // Empty dependency array - only run on mount

  if (!tokens) {
    return (
      <div className="surface-card rounded-xl p-6 text-sm text-zinc-700">
        Checking your session...
      </div>
    );
  }

  return <>{children}</>;
}

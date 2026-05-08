"use client";

import { useEffect } from "react";
import { useRouter } from "next/navigation";
import { useResolvedSession } from "@/lib/auth";

type Props = {
  children: React.ReactNode;
};

export function ProtectedView({ children }: Props) {
  const router = useRouter();
  const { ready, tokens } = useResolvedSession();

  useEffect(() => {
    if (!ready || tokens) {
      return;
    }
    router.replace("/login");
  }, [ready, tokens, router]);

  if (!ready || !tokens) {
    return (
      <div className="surface-card rounded-xl p-6 text-sm text-zinc-700">
        Checking your session...
      </div>
    );
  }

  return <>{children}</>;
}

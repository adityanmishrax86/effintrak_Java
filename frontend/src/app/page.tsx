"use client";

import { useEffect, useRef } from "react";
import { useRouter } from "next/navigation";
import { useAuthStore } from "@/lib/auth";

export default function Home() {
  const router = useRouter();
  const redirectedRef = useRef(false);

  useEffect(() => {
    // Only redirect once
    if (redirectedRef.current) return;
    redirectedRef.current = true;

    // Get current tokens state
    const tokens = useAuthStore.getState().tokens;
    router.replace(tokens ? "/dashboard" : "/login");
  }, [router]);

  return <div className="p-8 text-sm text-zinc-700">Starting EffinTrak web app...</div>;
}

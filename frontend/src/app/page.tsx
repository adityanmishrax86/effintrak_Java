"use client";

import { useEffect } from "react";
import { useRouter } from "next/navigation";
import { useResolvedSession } from "@/lib/auth";

export default function Home() {
  const router = useRouter();
  const { ready, tokens } = useResolvedSession();

  useEffect(() => {
    if (!ready) {
      return;
    }
    router.replace(tokens ? "/dashboard" : "/login");
  }, [ready, tokens, router]);

  return <div className="p-8 text-sm text-zinc-700">Starting EffinTrak web app...</div>;
}

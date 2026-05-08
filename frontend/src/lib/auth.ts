"use client";

import { useEffect, useState } from "react";
import { create } from "zustand";
import { persist, createJSONStorage } from "zustand/middleware";
import type { AuthTokens, UserProfile } from "@/lib/types";

type AuthState = {
  tokens: AuthTokens | null;
  profile: UserProfile | null;
  setTokens: (tokens: AuthTokens | null) => void;
  setProfile: (profile: UserProfile | null) => void;
  clear: () => void;
};

const STORAGE_KEY = "effintrak.session";

type PersistedSessionPayload = {
  state?: {
    tokens?: AuthTokens | null;
    profile?: UserProfile | null;
  };
};

export const useAuthStore = create<AuthState>()(
  persist(
    (set) => ({
      tokens: null,
      profile: null,
      setTokens: (tokens) => set({ tokens }),
      setProfile: (profile) => set({ profile }),
      clear: () => {
        set({ tokens: null, profile: null });
      },
    }),
    {
      name: STORAGE_KEY,
      storage: createJSONStorage(() => localStorage),
      skipHydration: false,
    }
  )
);

export function getAccessToken() {
  return useAuthStore.getState().tokens?.token ?? null;
}

export function getRefreshToken() {
  return useAuthStore.getState().tokens?.refreshToken ?? null;
}

export function useAuthHydrated() {
  const [hydrated, setHydrated] = useState(useAuthStore.persist.hasHydrated());

  useEffect(() => {
    const unsubscribeHydrate = useAuthStore.persist.onHydrate(() => {
      setHydrated(false);
    });

    const unsubscribeFinishHydration = useAuthStore.persist.onFinishHydration(() => {
      setHydrated(true);
    });

    setHydrated(useAuthStore.persist.hasHydrated());

    return () => {
      unsubscribeHydrate();
      unsubscribeFinishHydration();
    };
  }, []);

  return hydrated;
}

export function readPersistedSession() {
  if (typeof window === "undefined") {
    return { tokens: null, profile: null };
  }

  try {
    const raw = window.localStorage.getItem(STORAGE_KEY);
    if (!raw) {
      return { tokens: null, profile: null };
    }

    const parsed = JSON.parse(raw) as PersistedSessionPayload;
    return {
      tokens: parsed.state?.tokens ?? null,
      profile: parsed.state?.profile ?? null,
    };
  } catch {
    return { tokens: null, profile: null };
  }
}

export function useResolvedSession() {
  const tokens = useAuthStore((s) => s.tokens);
  const [ready, setReady] = useState(false);

  useEffect(() => {
    if (tokens) {
      setReady(true);
      return;
    }

    const persisted = readPersistedSession();
    if (persisted.tokens) {
      useAuthStore.getState().setTokens(persisted.tokens);
      if (persisted.profile) {
        useAuthStore.getState().setProfile(persisted.profile);
      }
      return;
    }

    setReady(true);
  }, [tokens]);

  return { ready, tokens };
}

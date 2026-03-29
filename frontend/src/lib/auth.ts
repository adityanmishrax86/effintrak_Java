"use client";

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

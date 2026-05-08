"use client";

import { useEffect } from "react";

type ShortcutHandler = () => void;
type ShortcutMap = Record<string, ShortcutHandler>;

export function useShortcuts(shortcuts: ShortcutMap) {
  useEffect(() => {
    function handler(e: KeyboardEvent) {
      const meta = e.metaKey || e.ctrlKey;
      const key = e.key.toLowerCase();
      const combo = meta ? `mod+${key}` : key;

      if (shortcuts[combo]) {
        e.preventDefault();
        shortcuts[combo]();
      }
    }

    window.addEventListener("keydown", handler);
    return () => window.removeEventListener("keydown", handler);
  }, [shortcuts]);
}

"use client";

import { FormEvent, useEffect, useState } from "react";
import { useRouter } from "next/navigation";
import { useMutation, useQuery } from "@tanstack/react-query";
import { AppShell } from "@/components/app-shell";
import { ProtectedView } from "@/components/protected-view";
import { api } from "@/lib/api";
import { useAuthStore } from "@/lib/auth";
import type { UserSettings } from "@/lib/types";

export default function SettingsPage() {
  const router = useRouter();
  const profile = useAuthStore((s) => s.profile);
  const clear = useAuthStore((s) => s.clear);

  const [showPassword, setShowPassword] = useState(false);
  const [newPassword, setNewPassword] = useState("");
  const [confirmPassword, setConfirmPassword] = useState("");
  const [passwordStatus, setPasswordStatus] = useState<"idle" | "success" | "error">("idle");
  const [settingsStatus, setSettingsStatus] = useState<"idle" | "saving" | "success" | "error">("idle");
  const [edits, setEdits] = useState<Partial<UserSettings>>({});

  const { data: settings, isLoading } = useQuery({
    queryKey: ["user-settings"],
    queryFn: () => api.getSettings(),
    enabled: !!profile?.id,
  });

  useEffect(() => {
    if (settings) {
      setEdits(settings);
    }
  }, [settings]);

  const updateMutation = useMutation({
    mutationFn: (newSettings: Partial<UserSettings>) => api.updateSettings(newSettings),
    onSuccess: () => {
      setSettingsStatus("success");
      setTimeout(() => setSettingsStatus("idle"), 2000);
    },
    onError: () => {
      setSettingsStatus("error");
      setTimeout(() => setSettingsStatus("idle"), 2000);
    },
  });

  const onLogout = async (event: FormEvent) => {
    event.preventDefault();
    try {
      await api.logout();
      clear();
      router.push("/login");
    } catch (error) {
      console.error("Logout failed:", error);
    }
  };

  const onChangePassword = async (event: FormEvent) => {
    event.preventDefault();
    if (!newPassword.trim() || newPassword !== confirmPassword) {
      setPasswordStatus("error");
      return;
    }
    // TODO: Implement password change API call
    setPasswordStatus("success");
    setNewPassword("");
    setConfirmPassword("");
    setTimeout(() => setPasswordStatus("idle"), 2000);
  };

  const onSaveSettings = (event: FormEvent) => {
    event.preventDefault();
    setSettingsStatus("saving");
    updateMutation.mutate(edits);
  };

  const handleToggle = (key: keyof UserSettings) => {
    setEdits((prev) => ({
      ...prev,
      [key]: !prev[key],
    }));
  };

  const handleSelectChange = (key: keyof UserSettings, value: string) => {
    setEdits((prev) => ({
      ...prev,
      [key]: value,
    }));
  };

  const currencyCode = edits.currencyCode || "INR";

  if (isLoading) {
    return (
      <ProtectedView>
        <AppShell>
          <div className="text-center">Loading settings...</div>
        </AppShell>
      </ProtectedView>
    );
  }

  return (
    <ProtectedView>
      <AppShell>
        <div className="space-y-4 max-w-4xl">
          <section className="surface-card rounded-xl p-6">
            <h1 className="text-2xl font-bold">Settings</h1>
            <p className="mt-1 text-sm text-zinc-600">Manage your account preferences.</p>
            <p className="mt-2 text-sm text-teal-700 font-medium">Current currency: {currencyCode}</p>
          </section>

          <section className="surface-card rounded-xl p-6">
            <h2 className="text-lg font-semibold">Account Information</h2>
            <div className="mt-4 space-y-3">
              <div>
                <label className="block text-sm font-medium text-zinc-700">Username</label>
                <p className="mt-1 rounded-md border border-zinc-300 px-3 py-2 text-sm bg-zinc-50">
                  {profile?.username || "-"}
                </p>
              </div>
              <div>
                <label className="block text-sm font-medium text-zinc-700">Email</label>
                <p className="mt-1 rounded-md border border-zinc-300 px-3 py-2 text-sm bg-zinc-50">
                  {profile?.email || "-"}
                </p>
              </div>
            </div>
          </section>

          <section className="surface-card rounded-xl p-6">
            <h2 className="text-lg font-semibold mb-4">Preferences</h2>
            <form onSubmit={onSaveSettings} className="space-y-6">
              <div className="grid grid-cols-1 md:grid-cols-2 gap-4">
                <div>
                  <label className="block text-sm font-medium text-zinc-700 mb-2">Currency</label>
                  <select
                    value={currencyCode}
                    onChange={(e) => handleSelectChange("currencyCode", e.target.value)}
                    className="w-full rounded-md border border-zinc-300 px-3 py-2 text-sm"
                  >
                    <option value="INR">INR (₹)</option>
                    <option value="USD">USD ($)</option>
                    <option value="EUR">EUR (€)</option>
                    <option value="GBP">GBP (£)</option>
                    <option value="JPY">JPY (¥)</option>
                    <option value="CAD">CAD (C$)</option>
                    <option value="AUD">AUD (A$)</option>
                  </select>
                </div>

                <div>
                  <label className="block text-sm font-medium text-zinc-700 mb-2">Date Format</label>
                  <select
                    value={edits.dateFormat || "dd/MM/yyyy"}
                    onChange={(e) => handleSelectChange("dateFormat", e.target.value)}
                    className="w-full rounded-md border border-zinc-300 px-3 py-2 text-sm"
                  >
                    <option value="dd/MM/yyyy">DD/MM/YYYY</option>
                    <option value="MM/dd/yyyy">MM/DD/YYYY</option>
                    <option value="yyyy-MM-dd">YYYY-MM-DD</option>
                    <option value="dd-MM-yyyy">DD-MM-YYYY</option>
                  </select>
                </div>

                <div>
                  <label className="block text-sm font-medium text-zinc-700 mb-2">Locale</label>
                  <select
                    value={edits.locale || "en-US"}
                    onChange={(e) => handleSelectChange("locale", e.target.value)}
                    className="w-full rounded-md border border-zinc-300 px-3 py-2 text-sm"
                  >
                    <option value="en-US">English (US)</option>
                    <option value="en-GB">English (UK)</option>
                    <option value="hi-IN">Hindi (India)</option>
                  </select>
                </div>

                <div>
                  <label className="block text-sm font-medium text-zinc-700 mb-2">Time Zone</label>
                  <select
                    value={edits.timeZone || "Asia/Kolkata"}
                    onChange={(e) => handleSelectChange("timeZone", e.target.value)}
                    className="w-full rounded-md border border-zinc-300 px-3 py-2 text-sm"
                  >
                    <option value="Asia/Kolkata">India Standard Time</option>
                    <option value="UTC">UTC</option>
                    <option value="Europe/London">London</option>
                    <option value="America/New_York">Eastern Time</option>
                  </select>
                </div>
              </div>

              <div className="space-y-3">
                <label className="flex items-center gap-3 p-3 border border-zinc-200 rounded-md hover:bg-zinc-50">
                  <input
                    type="checkbox"
                    checked={edits.includeProactiveInsights ?? true}
                    onChange={() => handleToggle("includeProactiveInsights")}
                    className="rounded border-zinc-300"
                  />
                  <div>
                    <span className="font-medium text-sm">Proactive Insights</span>
                  </div>
                </label>

                <label className="flex items-center gap-3 p-3 border border-zinc-200 rounded-md hover:bg-zinc-50">
                  <input
                    type="checkbox"
                    checked={edits.includeCategoryHints ?? true}
                    onChange={() => handleToggle("includeCategoryHints")}
                    className="rounded border-zinc-300"
                  />
                  <div>
                    <span className="font-medium text-sm">Category Hints</span>
                  </div>
                </label>
              </div>

              {settingsStatus === "error" && (
                <p className="text-sm text-red-600 mt-3">Failed to save settings. Please try again.</p>
              )}
              {settingsStatus === "success" && (
                <p className="text-sm text-teal-600 mt-3">Settings saved successfully!</p>
              )}

              <button
                type="submit"
                disabled={settingsStatus === "saving"}
                className="rounded-md bg-teal-800 px-4 py-2 text-sm text-white hover:bg-teal-900 disabled:opacity-50"
              >
                {settingsStatus === "saving" ? "Saving..." : "Save Settings"}
              </button>
            </form>
          </section>

          <section className="surface-card rounded-xl p-6">
            <h2 className="text-lg font-semibold">Change Password</h2>
            <form onSubmit={onChangePassword} className="mt-4 space-y-3">
              <div>
                <label htmlFor="newPassword" className="block text-sm font-medium text-zinc-700">
                  New Password
                </label>
                <input
                  id="newPassword"
                  type={showPassword ? "text" : "password"}
                  value={newPassword}
                  onChange={(e) => setNewPassword(e.target.value)}
                  placeholder="Enter new password"
                  className="mt-1 rounded-md border border-zinc-300 px-3 py-2 text-sm w-full"
                />
              </div>
              <div>
                <label htmlFor="confirmPassword" className="block text-sm font-medium text-zinc-700">
                  Confirm Password
                </label>
                <input
                  id="confirmPassword"
                  type={showPassword ? "text" : "password"}
                  value={confirmPassword}
                  onChange={(e) => setConfirmPassword(e.target.value)}
                  placeholder="Confirm new password"
                  className="mt-1 rounded-md border border-zinc-300 px-3 py-2 text-sm w-full"
                />
              </div>
              <label className="flex items-center gap-2 text-sm">
                <input
                  type="checkbox"
                  checked={showPassword}
                  onChange={(e) => setShowPassword(e.target.checked)}
                  className="rounded border-zinc-300"
                />
                Show password
              </label>
              {passwordStatus === "error" && (
                <p className="text-sm text-red-600">Passwords do not match or are empty.</p>
              )}
              {passwordStatus === "success" && (
                <p className="text-sm text-teal-600">Password changed successfully!</p>
              )}
              <button
                type="submit"
                className="rounded-md bg-teal-800 px-4 py-2 text-sm text-white hover:bg-teal-900"
              >
                Change Password
              </button>
            </form>
          </section>

          <section className="surface-card rounded-xl p-6 border-red-200 bg-red-50">
            <h2 className="text-lg font-semibold text-red-900">Danger Zone</h2>
            <form onSubmit={onLogout} className="mt-4">
              <p className="text-sm text-red-800 mb-3">Logging out will clear all local session data.</p>
              <button
                type="submit"
                className="rounded-md bg-red-600 px-4 py-2 text-sm text-white hover:bg-red-700"
              >
                Logout
              </button>
            </form>
          </section>
        </div>
      </AppShell>
    </ProtectedView>
  );
}

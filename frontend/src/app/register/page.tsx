"use client";

import Link from "next/link";
import { useRouter } from "next/navigation";
import { useState } from "react";
import { useForm } from "react-hook-form";
import { z } from "zod";
import { zodResolver } from "@hookform/resolvers/zod";
import { api } from "@/lib/api";

const schema = z
  .object({
    username: z.string().min(3, "At least 3 characters"),
    email: z.string().email("Provide a valid email"),
    password: z.string().min(6, "Minimum 6 characters"),
    confirmPassword: z.string(),
  })
  .refine((value) => value.password === value.confirmPassword, {
    path: ["confirmPassword"],
    message: "Passwords do not match",
  });

type FormValues = z.infer<typeof schema>;

export default function RegisterPage() {
  const router = useRouter();
  const [error, setError] = useState<string | null>(null);
  const [submitting, setSubmitting] = useState(false);

  const form = useForm<FormValues>({
    resolver: zodResolver(schema),
    defaultValues: { username: "", email: "", password: "", confirmPassword: "" },
  });

  const onSubmit = form.handleSubmit(async (values) => {
    setSubmitting(true);
    setError(null);
    try {
      await api.register(values.username, values.email, values.password);
      router.replace("/login");
    } catch (e) {
      setError(e instanceof Error ? e.message : "Registration failed");
    } finally {
      setSubmitting(false);
    }
  });

  return (
    <div className="flex min-h-screen items-center justify-center p-4">
      <div className="surface-card w-full max-w-md rounded-2xl p-6">
        <h1 className="text-2xl font-bold">Create account</h1>
        <p className="mt-1 text-sm text-zinc-600">Start tracking finances with AI-assisted workflows.</p>

        <form onSubmit={onSubmit} className="mt-6 space-y-4">
          <label className="block space-y-1">
            <span className="text-sm">Username</span>
            <input className="w-full rounded-md border border-zinc-300 bg-white px-3 py-2 text-sm" {...form.register("username")} />
            <span className="text-xs text-red-700">{form.formState.errors.username?.message}</span>
          </label>

          <label className="block space-y-1">
            <span className="text-sm">Email</span>
            <input type="email" className="w-full rounded-md border border-zinc-300 bg-white px-3 py-2 text-sm" {...form.register("email")} />
            <span className="text-xs text-red-700">{form.formState.errors.email?.message}</span>
          </label>

          <label className="block space-y-1">
            <span className="text-sm">Password</span>
            <input type="password" className="w-full rounded-md border border-zinc-300 bg-white px-3 py-2 text-sm" {...form.register("password")} />
            <span className="text-xs text-red-700">{form.formState.errors.password?.message}</span>
          </label>

          <label className="block space-y-1">
            <span className="text-sm">Confirm password</span>
            <input type="password" className="w-full rounded-md border border-zinc-300 bg-white px-3 py-2 text-sm" {...form.register("confirmPassword")} />
            <span className="text-xs text-red-700">{form.formState.errors.confirmPassword?.message}</span>
          </label>

          {error ? <p className="text-sm text-red-700">{error}</p> : null}

          <button type="submit" disabled={submitting} className="w-full rounded-md bg-teal-800 px-3 py-2 text-sm font-medium text-white disabled:opacity-60">
            {submitting ? "Creating account..." : "Create account"}
          </button>
        </form>

        <p className="mt-4 text-sm text-zinc-700">
          Already have an account? <Link href="/login" className="font-semibold text-teal-800">Sign in</Link>
        </p>
      </div>
    </div>
  );
}

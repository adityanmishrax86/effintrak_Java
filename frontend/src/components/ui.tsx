import clsx from "clsx";

interface ButtonProps extends React.ButtonHTMLAttributes<HTMLButtonElement> {
  variant?: "primary" | "secondary" | "danger" | "ghost";
  size?: "sm" | "md" | "lg";
}

export function Button({
  variant = "primary",
  size = "md",
  className,
  ...props
}: ButtonProps) {
  return (
    <button
      className={clsx(
        "inline-flex items-center justify-center font-medium rounded-lg transition-colors",
        "focus:outline-none focus:ring-2 focus:ring-offset-2 disabled:opacity-50 disabled:cursor-not-allowed",
        {
          // Size variants
          "px-2 py-1 text-xs": size === "sm",
          "px-3 py-2 text-sm": size === "md",
          "px-4 py-3 text-base": size === "lg",
          // Color variants
          "bg-teal-700 text-white hover:bg-teal-800 focus:ring-teal-500": variant === "primary",
          "bg-zinc-200 text-zinc-900 hover:bg-zinc-300 focus:ring-zinc-400": variant === "secondary",
          "bg-red-600 text-white hover:bg-red-700 focus:ring-red-500": variant === "danger",
          "text-zinc-700 hover:bg-zinc-100 focus:ring-zinc-400": variant === "ghost",
        },
        className
      )}
      {...props}
    />
  );
}

interface CardProps extends React.HTMLAttributes<HTMLDivElement> {
  elevated?: boolean;
}

export function Card({ elevated = true, className, ...props }: CardProps) {
  return (
    <div
      className={clsx(
        "rounded-xl p-6 bg-white",
        elevated ? "border border-zinc-200 shadow-sm" : "border border-zinc-100",
        className
      )}
      {...props}
    />
  );
}

interface InputProps extends React.InputHTMLAttributes<HTMLInputElement> {
  label?: string;
  error?: string;
}

export function Input({ label, error, className, ...props }: InputProps) {
  return (
    <div className="flex flex-col gap-1">
      {label && <label className="text-sm font-medium text-zinc-700">{label}</label>}
      <input
        className={clsx(
          "rounded-lg border px-3 py-2 text-sm transition-colors",
          "focus:outline-none focus:ring-2 focus:ring-offset-2",
          error
            ? "border-red-300 text-red-900 focus:ring-red-500 bg-red-50"
            : "border-zinc-300 focus:ring-teal-500",
          className
        )}
        {...props}
      />
      {error && <span className="text-xs text-red-600">{error}</span>}
    </div>
  );
}

interface SelectProps extends React.SelectHTMLAttributes<HTMLSelectElement> {
  label?: string;
  error?: string;
}

export function Select({ label, error, className, ...props }: SelectProps) {
  return (
    <div className="flex flex-col gap-1">
      {label && <label className="text-sm font-medium text-zinc-700">{label}</label>}
      <select
        className={clsx(
          "rounded-lg border px-3 py-2 text-sm transition-colors",
          "focus:outline-none focus:ring-2 focus:ring-offset-2",
          error
            ? "border-red-300 text-red-900 focus:ring-red-500 bg-red-50"
            : "border-zinc-300 focus:ring-teal-500",
          className
        )}
        {...props}
      />
      {error && <span className="text-xs text-red-600">{error}</span>}
    </div>
  );
}

export function Label({ children, ...props }: React.LabelHTMLAttributes<HTMLLabelElement>) {
  return (
    <label className="text-sm font-medium text-zinc-700" {...props}>
      {children}
    </label>
  );
}

interface FormFieldProps {
  label?: string;
  error?: string;
  children: React.ReactNode;
}

export function FormField({ label, error, children }: FormFieldProps) {
  return (
    <div className="flex flex-col gap-1">
      {label && <Label>{label}</Label>}
      {children}
      {error && <span className="text-xs text-red-600">{error}</span>}
    </div>
  );
}

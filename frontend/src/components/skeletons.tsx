import { Card } from "./ui";

export function CardSkeleton() {
  return (
    <Card className="space-y-3 animate-pulse">
      <div className="h-4 bg-zinc-200 rounded w-1/3"></div>
      <div className="h-8 bg-zinc-200 rounded w-1/2"></div>
      <div className="h-3 bg-zinc-200 rounded w-2/3"></div>
      <div className="h-3 bg-zinc-200 rounded w-1/2"></div>
    </Card>
  );
}

export function TableSkeleton({ rows = 5 }: { rows?: number }) {
  return (
    <div className="space-y-2 animate-pulse">
      {Array.from({ length: rows }).map((_, i) => (
        <div key={i} className="h-12 bg-zinc-200 rounded-lg"></div>
      ))}
    </div>
  );
}

export function FormSkeleton() {
  return (
    <Card className="space-y-4 animate-pulse">
      <div className="h-5 bg-zinc-200 rounded w-1/3 mb-4"></div>
      <div className="grid gap-4 md:grid-cols-2">
        <div className="h-10 bg-zinc-200 rounded"></div>
        <div className="h-10 bg-zinc-200 rounded"></div>
        <div className="h-10 bg-zinc-200 rounded"></div>
        <div className="h-10 bg-zinc-200 rounded"></div>
      </div>
      <div className="flex gap-2">
        <div className="h-10 bg-zinc-200 rounded w-24"></div>
        <div className="h-10 bg-zinc-200 rounded w-24"></div>
      </div>
    </Card>
  );
}

export function MetricCardSkeleton() {
  return (
    <Card className="flex items-start justify-between animate-pulse">
      <div className="flex-1 space-y-2">
        <div className="h-4 bg-zinc-200 rounded w-2/3"></div>
        <div className="h-8 bg-zinc-200 rounded w-1/2 mt-2"></div>
      </div>
      <div className="h-12 w-12 bg-zinc-200 rounded-lg flex-shrink-0"></div>
    </Card>
  );
}

export function LoadingSkeleton() {
  return (
    <div className="space-y-6">
      <div className="space-y-2">
        <div className="h-8 bg-zinc-200 rounded w-1/2 animate-pulse"></div>
        <div className="h-4 bg-zinc-200 rounded w-2/3 animate-pulse"></div>
      </div>
      <div className="grid gap-4 md:grid-cols-3">
        <MetricCardSkeleton />
        <MetricCardSkeleton />
        <MetricCardSkeleton />
      </div>
      <FormSkeleton />
      <TableSkeleton rows={5} />
    </div>
  );
}

"use client";

import { Button } from "@/components/ui/button";
import {
  DropdownMenu,
  DropdownMenuContent,
  DropdownMenuItem,
  DropdownMenuTrigger,
} from "@/components/ui/dropdown-menu";
import {
  ArrowRightLeft,
  Bot,
  CircleDollarSign,
  Plus,
  Receipt,
} from "lucide-react";
import { useRouter } from "next/navigation";

type QuickAddMenuProps = {
  onAdd: (kind: "expense" | "income" | "transfer") => void;
};

export function QuickAddMenu({ onAdd }: QuickAddMenuProps) {
  const router = useRouter();

  return (
    <DropdownMenu>
      <DropdownMenuTrigger asChild>
        <Button size="sm" className="gap-1">
          <Plus className="h-4 w-4" />
          <span className="hidden sm:inline">Add</span>
        </Button>
      </DropdownMenuTrigger>
      <DropdownMenuContent align="end">
        <DropdownMenuItem onClick={() => onAdd("expense")}>
          <Receipt className="mr-2 h-4 w-4" />
          Expense
        </DropdownMenuItem>
        <DropdownMenuItem onClick={() => onAdd("income")}>
          <CircleDollarSign className="mr-2 h-4 w-4" />
          Income
        </DropdownMenuItem>
        <DropdownMenuItem onClick={() => onAdd("transfer")}>
          <ArrowRightLeft className="mr-2 h-4 w-4" />
          Transfer
        </DropdownMenuItem>
        <DropdownMenuItem onClick={() => router.push("/chat")}>
          <Bot className="mr-2 h-4 w-4" />
          Ask AI
        </DropdownMenuItem>
      </DropdownMenuContent>
    </DropdownMenu>
  );
}

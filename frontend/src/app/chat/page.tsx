"use client";

import { FormEvent, useCallback, useMemo, useRef, useState, useEffect } from "react";
import { useMutation, useQuery, useQueryClient } from "@tanstack/react-query";
import { Bot, Check, History, Loader2, Mic, MicOff, Send, Trash2 } from "lucide-react";
import { AppShell } from "@/components/app-shell";
import { ProtectedView } from "@/components/protected-view";
import { api } from "@/lib/api";
import { Button } from "@/components/ui/button";
import { Badge } from "@/components/ui/badge";
import { Tabs, TabsList, TabsTrigger } from "@/components/ui/tabs";
import { Textarea } from "@/components/ui/textarea";
import { toast } from "sonner";
import { useVoiceInput } from "@/lib/hooks/use-voice-input";
import type { ChatConversationMessage } from "@/lib/types";

type PromptCategory = "expenses" | "incomes" | "budgets" | "transfers" | "recurring" | "subscriptions" | "credits" | "savings" | "analytics";

const PROMPT_PRESETS: Record<PromptCategory, { label: string; prompts: string[] }> = {
  expenses: {
    label: "Expenses",
    prompts: [
      "Add an expense of $45 for groceries paid via card today.",
      "Record a $12 coffee expense under Food and Drinks paid by UPI to Starbucks.",
      "I spent $18 on breakfast at Cafe Rio and $42 on groceries at DMart today from UPI.",
    ],
  },
  analytics: {
    label: "Finance Queries",
    prompts: [
      "What were my total expenses yesterday?",
      "Show my top spending categories for this month.",
      "Give me a financial report from 2026-05-01 to 2026-05-09.",
    ],
  },
  incomes: {
    label: "Incomes",
    prompts: [
      "Add salary income of $3,000 received today in my primary account from Acme Corp.",
      "Record freelance income of $650 from Acme project with note milestone 2.",
      "Compare this month's income vs last month.",
    ],
  },
  budgets: {
    label: "Budgets",
    prompts: [
      "Create a monthly groceries budget of $400.",
      "How much of my dining budget is left this month?",
      "Set a travel budget of $250 for this month.",
    ],
  },
  transfers: {
    label: "Transfers",
    prompts: [
      "Transfer $200 from Checking to Savings today.",
      "Move $75 from Wallet to Main Bank account.",
      "Show my transfers in the last 30 days.",
    ],
  },
  recurring: {
    label: "Recurring",
    prompts: [
      "Create a recurring electricity expense of $90 monthly paid by UPI to BESCOM.",
      "Add a recurring income of $500 every Friday from Rental Income.",
      "List my active recurring transactions.",
    ],
  },
  subscriptions: {
    label: "Subscriptions",
    prompts: [
      "Add Netflix subscription for $15 monthly.",
      "Show all active subscriptions and total monthly cost.",
      "Cancel Spotify subscription from next billing cycle.",
    ],
  },
  credits: {
    label: "Credits",
    prompts: [
      "Add a credit payment of $150 for my Visa card via bank transfer.",
      "What credit dues are upcoming this week?",
      "Show my current credit utilization summary.",
    ],
  },
  savings: {
    label: "Savings",
    prompts: [
      "Create a savings goal called Emergency Fund for $5,000.",
      "Add $120 contribution to my Vacation savings goal.",
      "How close am I to each savings goal?",
    ],
  },
};

const SLASH_COMMANDS = [
  { cmd: "/expense", desc: "Add expense", example: "/expense 50 lunch today" },
  { cmd: "/income", desc: "Add income", example: "/income 3000 salary" },
  { cmd: "/transfer", desc: "Transfer money", example: "/transfer 200 checking savings" },
  { cmd: "/budget", desc: "Create budget", example: "/budget 400 groceries" },
  { cmd: "/recurring", desc: "Add recurring", example: "/recurring 90 electricity monthly" },
  { cmd: "/report", desc: "Get report", example: "/report expenses this month" },
];

function formatMessageTime(value?: string) {
  if (!value) return "";
  const date = new Date(value);
  if (Number.isNaN(date.getTime())) return "";
  return new Intl.DateTimeFormat("en-US", {
    month: "short", day: "numeric", hour: "numeric", minute: "2-digit",
  }).format(date);
}

function renderSimpleMarkdown(text: string) {
  return text.split("\n").map((line, lineIndex) => {
    const parts = line.split(/(\*\*.*?\*\*)/g).filter(Boolean);
    return (
      <span key={`line-${lineIndex}`}>
        {parts.map((part, partIndex) =>
          part.startsWith("**") && part.endsWith("**") ? (
            <strong key={`part-${lineIndex}-${partIndex}`}>{part.slice(2, -2)}</strong>
          ) : (
            <span key={`part-${lineIndex}-${partIndex}`}>{part}</span>
          )
        )}
        {lineIndex < text.split("\n").length - 1 ? <br /> : null}
      </span>
    );
  });
}

export default function ChatPage() {
  const queryClient = useQueryClient();
  const [prompt, setPrompt] = useState("");
  const [currentConversationId, setCurrentConversationId] = useState<string | undefined>();
  const [activeTab, setActiveTab] = useState<"assistant" | "history">("assistant");
  const [activePromptCategory, setActivePromptCategory] = useState<PromptCategory>("expenses");
  const [showSlashMenu, setShowSlashMenu] = useState(false);
  const [pendingUserMessage, setPendingUserMessage] = useState<string | null>(null);
  const promptInputRef = useRef<HTMLTextAreaElement | null>(null);
  const chatEndRef = useRef<HTMLDivElement | null>(null);

  const handleVoiceResult = useCallback((text: string) => {
    setPrompt((prev) => prev ? `${prev} ${text}` : text);
    promptInputRef.current?.focus();
  }, []);

  const { isListening, isSupported: voiceSupported, toggle: toggleVoice } = useVoiceInput(handleVoiceResult);

  const conversationsQuery = useQuery({
    queryKey: ["chat-conversations"],
    queryFn: api.listConversations,
  });

  const conversationQuery = useQuery({
    queryKey: ["chat-conversation", currentConversationId],
    queryFn: async () => {
      if (!currentConversationId) return null;
      return api.getConversation(currentConversationId);
    },
    enabled: Boolean(currentConversationId),
  });

  const sendPrompt = useMutation({
    mutationFn: async (value: string) => {
      return api.sendChatPrompt({
        prompt: value,
        conversationId: currentConversationId,
      });
    },
    onMutate: async (value) => {
      setPendingUserMessage(value);
    },
    onSuccess: (response) => {
      setCurrentConversationId(response.conversationId);
      queryClient.invalidateQueries({ queryKey: ["chat-conversations"] });
      queryClient.invalidateQueries({ queryKey: ["chat-conversation", response.conversationId] });
      // Invalidate related data that might have been created
      if (response.operation) {
        queryClient.invalidateQueries({ queryKey: ["expenses"] });
        queryClient.invalidateQueries({ queryKey: ["incomes"] });
        queryClient.invalidateQueries({ queryKey: ["recurring-transactions"] });
        queryClient.invalidateQueries({ queryKey: ["subscriptions"] });
        queryClient.invalidateQueries({ queryKey: ["dashboard"] });
      }
      if (response.status === "success" && response.operation) {
        toast.success(`Operation completed: ${response.operation}`, {
          action: {
            label: "View",
            onClick: () => {
              const page = response.operation?.toLowerCase().includes("expense") ? "/expenses"
                : response.operation?.toLowerCase().includes("income") ? "/incomes"
                : response.operation?.toLowerCase().includes("transfer") ? "/transfers"
                : response.operation?.toLowerCase().includes("budget") ? "/budgets"
                : response.operation?.toLowerCase().includes("recurring") ? "/recurring"
                : response.operation?.toLowerCase().includes("subscription") ? "/recurring?tab=subscriptions"
                : null;
              if (page) window.location.href = page;
            },
          },
        });
      }
    },
    onSettled: () => {
      setPendingUserMessage(null);
    },
    onError: (e) => toast.error(e.message),
  });

  const deleteConversation = useMutation({
    mutationFn: (conversationId: string) => api.deleteConversation(conversationId),
    onSuccess: (_, conversationId) => {
      if (currentConversationId === conversationId) setCurrentConversationId(undefined);
      queryClient.invalidateQueries({ queryKey: ["chat-conversations"] });
      toast.success("Conversation deleted");
    },
  });

  // Auto-scroll to bottom when new messages arrive
  useEffect(() => {
    chatEndRef.current?.scrollIntoView({ behavior: "smooth" });
  }, [conversationQuery.data?.messages?.length, sendPrompt.isPending]);

  const sortedConversations = useMemo(() => {
    return [...(conversationsQuery.data ?? [])].sort(
      (a, b) => new Date(b.updatedAt).getTime() - new Date(a.updatedAt).getTime()
    );
  }, [conversationsQuery.data]);

  const onSubmit = async (event: FormEvent) => {
    event.preventDefault();
    const text = prompt.trim();
    if (!text) return;
    setPrompt("");
    setShowSlashMenu(false);
    await sendPrompt.mutateAsync(text);
  };

  const handlePromptChange = (value: string) => {
    setPrompt(value);
    setShowSlashMenu(value === "/");
  };

  const applySlashCommand = (cmd: string) => {
    setPrompt(cmd + " ");
    setShowSlashMenu(false);
    promptInputRef.current?.focus();
  };

  const displayedMessages: ChatConversationMessage[] = conversationQuery.data?.messages ?? [];

  return (
    <ProtectedView>
      <AppShell>
        <div className="flex flex-col h-[calc(100vh-140px)]">
          <div className="flex items-center justify-between mb-4">
            <div>
              <h1 className="text-2xl font-bold">AI Assistant</h1>
              <p className="text-sm text-muted-foreground">
                Manage your finances with natural language. Type <kbd className="rounded bg-muted px-1 text-xs font-mono">/</kbd> for commands.
              </p>
            </div>
            <Tabs value={activeTab} onValueChange={(v) => setActiveTab(v as typeof activeTab)}>
              <TabsList>
                <TabsTrigger value="assistant"><Bot className="h-4 w-4 mr-1" /> Chat</TabsTrigger>
                <TabsTrigger value="history"><History className="h-4 w-4 mr-1" /> History</TabsTrigger>
              </TabsList>
            </Tabs>
          </div>

          {activeTab === "history" ? (
            <div className="flex-1 overflow-y-auto space-y-2">
              {sortedConversations.map((conversation) => (
                <div
                  key={conversation.conversationId}
                  className={`rounded-lg border p-3 cursor-pointer transition ${
                    currentConversationId === conversation.conversationId
                      ? "border-primary bg-primary/5"
                      : "hover:bg-muted"
                  }`}
                >
                  <div className="flex items-center justify-between gap-2">
                    <button
                      type="button"
                      onClick={() => { setCurrentConversationId(conversation.conversationId); setActiveTab("assistant"); }}
                      className="flex-1 text-left min-w-0"
                    >
                      <p className="font-medium truncate">{conversation.title || "Untitled"}</p>
                      <p className="text-xs text-muted-foreground mt-0.5">{formatMessageTime(conversation.updatedAt)}</p>
                    </button>
                    <Button
                      variant="ghost"
                      size="icon-xs"
                      className="text-destructive"
                      onClick={() => deleteConversation.mutate(conversation.conversationId)}
                    >
                      <Trash2 className="h-3.5 w-3.5" />
                    </Button>
                  </div>
                </div>
              ))}
              {!sortedConversations.length && (
                <p className="text-center text-muted-foreground py-8">No conversations yet. Start chatting!</p>
              )}
            </div>
          ) : (
            <>
              {/* Messages area */}
              <div className="flex-1 overflow-y-auto rounded-lg border bg-card p-4 mb-4 space-y-4">
                {!displayedMessages.length && !sendPrompt.isPending && (
                  <div className="text-center py-8 space-y-4">
                    <Bot className="h-12 w-12 mx-auto text-muted-foreground/40" />
                    <div>
                      <p className="font-medium">How can I help with your finances?</p>
                      <p className="text-sm text-muted-foreground mt-1">
                        Try natural language or use slash commands for quick entry.
                      </p>
                    </div>
                    {/* Quick prompts */}
                    <div className="max-w-lg mx-auto">
                      <div className="flex gap-1.5 justify-center flex-wrap mb-3">
                        {(Object.keys(PROMPT_PRESETS) as PromptCategory[]).map((cat) => (
                          <Button
                            key={cat}
                            variant={activePromptCategory === cat ? "default" : "outline"}
                            size="xs"
                            onClick={() => setActivePromptCategory(cat)}
                          >
                            {PROMPT_PRESETS[cat].label}
                          </Button>
                        ))}
                      </div>
                      <div className="grid gap-2 sm:grid-cols-3">
                        {PROMPT_PRESETS[activePromptCategory].prompts.map((p) => (
                          <button
                            key={p}
                            type="button"
                            onClick={() => { setPrompt(p); promptInputRef.current?.focus(); }}
                            className="rounded-lg border px-3 py-2 text-left text-xs hover:bg-muted transition"
                          >
                            {p}
                          </button>
                        ))}
                      </div>
                    </div>
                  </div>
                )}

                {displayedMessages.map((message) => (
                  <div key={message.id} className="space-y-3">
                    {/* User message */}
                    <div className="flex justify-end">
                      <div className="max-w-[80%] rounded-2xl rounded-br-sm bg-primary text-primary-foreground px-4 py-2.5">
                        <p className="text-sm whitespace-pre-wrap">{message.userMessage}</p>
                      </div>
                    </div>
                    {/* AI response */}
                    <div className="flex justify-start gap-2">
                      <div className="h-7 w-7 rounded-full bg-muted flex items-center justify-center flex-shrink-0 mt-1">
                        <Bot className="h-4 w-4" />
                      </div>
                      <div className="max-w-[80%] space-y-2">
                        <div className="rounded-2xl rounded-bl-sm border bg-card px-4 py-2.5">
                        <p className="text-sm whitespace-pre-wrap">{renderSimpleMarkdown(message.aiResponse)}</p>
                        </div>
                        {/* Confirmation card for operations */}
                        {message.operation && message.success && (
                          <div className="rounded-lg border bg-emerald-50 border-emerald-200 px-3 py-2 flex items-center gap-2">
                            <Check className="h-4 w-4 text-emerald-600" />
                            <span className="text-xs font-medium text-emerald-800">{message.operation}</span>
                            <Badge variant="secondary" className="text-[10px]">
                              {message.success ? "Done" : "Failed"}
                            </Badge>
                          </div>
                        )}
                        <p className="text-[11px] text-muted-foreground">
                          {formatMessageTime(message.createdAt)}
                        </p>
                      </div>
                    </div>
                  </div>
                ))}

                {pendingUserMessage && (
                  <div className="space-y-3">
                    <div className="flex justify-end">
                      <div className="max-w-[80%] rounded-2xl rounded-br-sm bg-primary text-primary-foreground px-4 py-2.5">
                        <p className="text-sm whitespace-pre-wrap">{pendingUserMessage}</p>
                      </div>
                    </div>
                    {sendPrompt.isPending && (
                      <div className="flex justify-start gap-2">
                        <div className="h-7 w-7 rounded-full bg-muted flex items-center justify-center flex-shrink-0 mt-1">
                          <Bot className="h-4 w-4" />
                        </div>
                        <div className="rounded-2xl rounded-bl-sm border bg-card px-4 py-2.5 text-sm text-muted-foreground flex items-center gap-2">
                          <Loader2 className="h-4 w-4 animate-spin" />
                          Thinking...
                        </div>
                      </div>
                    )}
                  </div>
                )}
                <div ref={chatEndRef} />
              </div>

              {/* Input area */}
              <div className="relative">
                {showSlashMenu && (
                  <div className="absolute bottom-full mb-2 w-full rounded-lg border bg-card shadow-lg p-2 max-h-64 overflow-y-auto z-10">
                    <p className="text-xs font-medium text-muted-foreground mb-2 px-2">Slash Commands</p>
                    {SLASH_COMMANDS.map((sc) => (
                      <button
                        key={sc.cmd}
                        type="button"
                        onClick={() => applySlashCommand(sc.cmd)}
                        className="w-full text-left rounded-md px-3 py-2 hover:bg-muted flex items-center gap-3"
                      >
                        <code className="text-xs font-mono font-semibold text-primary">{sc.cmd}</code>
                        <span className="text-sm">{sc.desc}</span>
                        <span className="ml-auto text-xs text-muted-foreground">{sc.example}</span>
                      </button>
                    ))}
                  </div>
                )}
                <form onSubmit={onSubmit} className="flex gap-2 items-end">
                  <div className="flex-1 relative">
                    <Textarea
                      ref={promptInputRef}
                      value={prompt}
                      onChange={(e) => handlePromptChange(e.target.value)}
                      onKeyDown={(e) => {
                        if (e.key === "Enter" && !e.shiftKey) {
                          e.preventDefault();
                          onSubmit(e as unknown as FormEvent);
                        }
                      }}
                      rows={2}
                      maxLength={2000}
                      placeholder='Type a message or "/" for commands...'
                      className="pr-10 resize-none"
                    />
                    <span className="absolute bottom-2 right-3 text-[10px] text-muted-foreground">
                      {prompt.length}/2000
                    </span>
                  </div>
                  <div className="flex flex-col gap-1">
                    {voiceSupported && (
                      <Button
                        type="button"
                        variant={isListening ? "destructive" : "outline"}
                        size="icon"
                        onClick={toggleVoice}
                        title={isListening ? "Stop listening" : "Voice input"}
                      >
                        {isListening ? <MicOff className="h-4 w-4" /> : <Mic className="h-4 w-4" />}
                      </Button>
                    )}
                    <Button type="submit" size="icon" disabled={sendPrompt.isPending || !prompt.trim()}>
                      <Send className="h-4 w-4" />
                    </Button>
                  </div>
                </form>
              </div>
            </>
          )}
        </div>
      </AppShell>
    </ProtectedView>
  );
}

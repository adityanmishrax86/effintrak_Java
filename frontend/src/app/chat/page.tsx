"use client";

import { FormEvent, useMemo, useState } from "react";
import { useMutation, useQuery, useQueryClient } from "@tanstack/react-query";
import { Trash2 } from "lucide-react";
import { AppShell } from "@/components/app-shell";
import { ProtectedView } from "@/components/protected-view";
import { api } from "@/lib/api";
import type { ChatConversationMessage } from "@/lib/types";

export default function ChatPage() {
  const queryClient = useQueryClient();
  const [prompt, setPrompt] = useState("");
  const [currentConversationId, setCurrentConversationId] = useState<string | undefined>();

  const conversationsQuery = useQuery({
    queryKey: ["chat-conversations"],
    queryFn: api.listConversations,
  });

  const conversationQuery = useQuery({
    queryKey: ["chat-conversation", currentConversationId],
    queryFn: async () => {
      if (!currentConversationId) {
        return null;
      }
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
    onSuccess: (response) => {
      setCurrentConversationId(response.conversationId);
      queryClient.invalidateQueries({ queryKey: ["chat-conversations"] });
      queryClient.invalidateQueries({ queryKey: ["chat-conversation", response.conversationId] });
    },
  });

  const deleteConversation = useMutation({
    mutationFn: (conversationId: string) => api.deleteConversation(conversationId),
    onSuccess: (_, conversationId) => {
      if (currentConversationId === conversationId) {
        setCurrentConversationId(undefined);
      }
      queryClient.invalidateQueries({ queryKey: ["chat-conversations"] });
    },
  });

  const lastError = sendPrompt.error instanceof Error ? sendPrompt.error.message : null;

  const helperText = useMemo(() => {
    if (sendPrompt.isPending) {
      return "AI is processing your request. This backend currently returns non-streaming responses.";
    }
    return "Try: Add $45 groceries from my default account";
  }, [sendPrompt.isPending]);

  const onSubmit = async (event: FormEvent) => {
    event.preventDefault();
    const text = prompt.trim();
    if (!text) {
      return;
    }
    setPrompt("");
    await sendPrompt.mutateAsync(text);
  };

  const displayedMessages: ChatConversationMessage[] = conversationQuery.data?.messages ?? [];

  return (
    <ProtectedView>
      <AppShell>
        <div className="grid gap-4 lg:grid-cols-[280px_1fr]">
          <aside className="surface-card rounded-xl p-4">
            <h2 className="font-semibold">Conversations</h2>
            <div className="mt-3 space-y-2 text-sm">
              {conversationsQuery.data?.map((conversation) => (
                <div key={conversation.conversationId} className="rounded-md border border-zinc-200 px-3 py-2 hover:bg-zinc-100">
                  <button
                    type="button"
                    onClick={() => setCurrentConversationId(conversation.conversationId)}
                    className="w-full text-left"
                  >
                    <p className="font-medium">{conversation.title || "Untitled"}</p>
                    <p className="text-xs text-zinc-500">{conversation.conversationId}</p>
                  </button>
                  <button
                    type="button"
                    onClick={() => deleteConversation.mutate(conversation.conversationId)}
                    className="mt-2 inline-flex items-center gap-1 text-xs text-red-700"
                    disabled={deleteConversation.isPending}
                  >
                    <Trash2 className="h-3 w-3" />
                    Delete
                  </button>
                </div>
              ))}
              {!conversationsQuery.data?.length ? (
                <p className="text-xs text-zinc-500">No conversations yet.</p>
              ) : null}
            </div>
          </aside>

          <section className="surface-card rounded-xl p-4">
            <h1 className="text-xl font-bold">AI Finance Assistant</h1>
            <p className="mt-1 text-sm text-zinc-600">{helperText}</p>

            <div className="mt-4 max-h-[420px] space-y-3 overflow-y-auto rounded-md border border-zinc-200 bg-white p-3">
              {displayedMessages.map((message) => (
                <article key={message.id} className="rounded-md border border-zinc-200 p-3">
                  <p className="text-xs uppercase tracking-wide text-zinc-500">
                    {message.operation || message.messageType || "GENERAL"} · {message.success ? "success" : "error"}
                  </p>
                  <p className="mt-1 text-xs text-zinc-500 whitespace-pre-wrap">You: {message.userMessage}</p>
                  <p className="mt-2 text-sm whitespace-pre-wrap">{message.aiResponse}</p>
                </article>
              ))}
              {!displayedMessages.length ? (
                <p className="text-sm text-zinc-500">
                  {currentConversationId
                    ? "No messages in this conversation yet."
                    : "No chat messages yet. Ask the assistant to record a transaction."}
                </p>
              ) : null}
              {sendPrompt.isPending ? (
                <article className="rounded-md border border-zinc-200 p-3">
                  <p className="text-xs uppercase tracking-wide text-zinc-500">PROCESSING</p>
                  <p className="mt-1 text-sm whitespace-pre-wrap">Generating response...</p>
                </article>
              ) : null}
              {lastError ? (
                <article className="rounded-md border border-red-300 bg-red-50 p-3">
                  <p className="text-xs uppercase tracking-wide text-red-700">ERROR</p>
                  <p className="mt-1 text-sm whitespace-pre-wrap">{lastError}</p>
                </article>
              ) : null}
            </div>

            <form onSubmit={onSubmit} className="mt-4 space-y-2">
              <textarea
                value={prompt}
                onChange={(event) => setPrompt(event.target.value)}
                rows={4}
                maxLength={2000}
                placeholder="Describe what to do with your finances..."
                className="w-full rounded-md border border-zinc-300 bg-white px-3 py-2 text-sm"
              />
              <button
                type="submit"
                disabled={sendPrompt.isPending}
                className="rounded-md bg-teal-800 px-4 py-2 text-sm font-medium text-white disabled:opacity-60"
              >
                {sendPrompt.isPending ? "Processing..." : "Send prompt"}
              </button>
            </form>
          </section>
        </div>
      </AppShell>
    </ProtectedView>
  );
}

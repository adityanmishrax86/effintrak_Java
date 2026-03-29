export type ApiEnvelope<T> = {
  success: boolean;
  message: string;
  data: T;
};

export type PageableResponse<T> = {
  content: T[];
  pageNumber: number;
  pageSize: number;
  totalElements: number;
  totalPages: number;
  last: boolean;
};

export type AuthTokens = {
  token: string;
  refreshToken: string;
};

export type UserProfile = {
  id: number;
  username: string;
  email: string;
};

export type UserSettings = {
  userId: number;
  currencyCode: string;
  locale: string;
  timeZone: string;
  dateFormat: string;
  aiPersona: string;
  includeProactiveInsights: boolean;
  includeCategoryHints: boolean;
  weekStartsOn: string;
};

export type Category = {
  id: number;
  name: string;
};

export type BankAccount = {
  id: number;
  name: string;
  balance: number;
};

export type Expense = {
  id: number;
  description: string;
  amount: number;
  category?: string;
  date: string;
  paymentMethod?: string;
  bankAccount?: string;
  paidTo?: string;
  isRecurring?: boolean;
};

export type Income = {
  id: number;
  description: string;
  amount: number;
  category?: string;
  source?: string;
  note?: string;
  bankAccount?: string;
  date: string;
};

export type Transfer = {
  id: number;
  amount: number;
  description?: string;
  transferDate: string;
  fromAccountName: string;
  toAccountName: string;
};

export type DashboardData = {
  totalBalance: number;
  monthlyIncome: number;
  monthlyExpense: number;
  recentTransactions: Array<{
    id: number;
    description: string;
    amount: number;
    date: string;
    type: string;
  }>;
};

export type ChatPromptRequest = {
  prompt: string;
  conversationId?: string;
  model?: string;
};

export type ChatPromptResponse = {
  response: string;
  userId: number;
  conversationId: string;
  timestamp: number;
  status: "success" | "error";
  operation?: string;
  errorCode?: string;
  model?: string;
  warnings?: string[];
};

export type ChatConversationMessage = {
  id: number;
  userMessage: string;
  aiResponse: string;
  messageType: string;
  operation?: string;
  success: boolean;
  createdAt: string;
};

export type Budget = {
  id: number;
  name: string;
  amount: number;
  spent?: number;
  category?: string;
  categoryId?: number;
  startDate: string;
  endDate: string;
  status?: string;
};

export type Savings = {
  id: number;
  goalName: string;
  targetAmount: number;
  currentAmount: number;
  targetDate: string;
  frequency?: string;
  status?: string;
};

export type Subscription = {
  id: number;
  serviceName: string;
  amount: number;
  billingCycle: string;
  startDate: string;
  endDate?: string;
  status?: string;
  category?: string;
};

export type Credit = {
  id: number;
  cardName: string;
  creditLimit?: number;
  amount: number;
  dueDate: string;
  interestRate?: number;
  status?: string;
};

export type RecurringTransaction = {
  id: number;
  description: string;
  amount: number;
  frequency: string;
  category?: string;
  startDate: string;
  endDate?: string;
  type: "INCOME" | "EXPENSE";
  status?: string;
};

export type ChatConversation = {
  id: number;
  conversationId: string;
  user?: {
    id: number;
  };
  title?: string;
  description?: string;
  messages?: ChatConversationMessage[];
  createdAt: string;
  updatedAt: string;
};

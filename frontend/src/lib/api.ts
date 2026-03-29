import { format } from "date-fns";
import { getAccessToken, getRefreshToken, useAuthStore } from "@/lib/auth";
import type {
	ApiEnvelope,
	AuthTokens,
	BankAccount,
	Budget,
	Category,
	ChatConversation,
	ChatPromptRequest,
	ChatPromptResponse,
	Credit,
	DashboardData,
	Expense,
	Income,
	PageableResponse,
	RecurringTransaction,
	Savings,
	Subscription,
	Transfer,
	UserProfile,
	UserSettings,
} from "@/lib/types";

const API_BASE_URL = process.env.NEXT_PUBLIC_API_BASE_URL || "/api";

type RawBudget = {
	id: number;
	amount: number;
	startDate: string;
	endDate: string;
	categoryName?: string;
};

type RawSavings = {
	id: number;
	name: string;
	balance: number;
	targetAmount: number;
	targetDate: string;
	depositFrequency?: string;
};

type RawSubscription = {
	id: number;
	name: string;
	price: number;
	billingCycle: string;
	startDate: string;
	endDate?: string;
	isActive?: boolean;
};

type RawCredit = {
	id: number;
	description: string;
	amount: number;
	dueDate: string;
	interestRate?: number;
	paid?: boolean;
};

type RawRecurringTransaction = {
	id: number;
	description: string;
	amount: number;
	type: "INCOME" | "EXPENSE";
	categoryName?: string;
	frequency: string;
	startDate: string;
	endDate?: string;
	isActive?: boolean;
};

function normalizeDate(date = new Date()) {
	return format(date, "yyyy-MM-dd");
}

async function parseResponse<T>(res: Response): Promise<T> {
	const contentType = res.headers.get("content-type") || "";
	const payload = contentType.includes("application/json") ? await res.json() : null;

	if (!res.ok) {
		const message = payload?.message || `Request failed with status ${res.status}`;
		throw new Error(message);
	}

	if (!payload) {
		return null as unknown as T;
	}

	if (typeof payload.success === "boolean") {
		const envelope = payload as ApiEnvelope<T>;
		if (!envelope.success) {
			throw new Error(envelope.message || "Request failed");
		}
		return envelope.data;
	}

	return payload as T;
}

async function refreshTokens(): Promise<AuthTokens | null> {
	const refreshToken = getRefreshToken();
	if (!refreshToken) {
		return null;
	}

	try {
		const res = await fetch(`${API_BASE_URL}/v1/users/refresh`, {
			method: "POST",
			headers: { "Content-Type": "application/json" },
			body: JSON.stringify({ refreshToken }),
		});
		const refreshed = await parseResponse<AuthTokens>(res);
		useAuthStore.getState().setTokens(refreshed);
		return refreshed;
	} catch {
		useAuthStore.getState().clear();
		return null;
	}
}

async function authFetch<T>(path: string, init?: RequestInit, allowRetry = true): Promise<T> {
	const token = getAccessToken();
	const headers = new Headers(init?.headers || {});
	if (token) {
		headers.set("Authorization", `Bearer ${token}`);
	}
	headers.set("Content-Type", headers.get("Content-Type") || "application/json");

	const res = await fetch(`${API_BASE_URL}${path}`, { ...init, headers });

	if (res.status === 401 && allowRetry) {
		const refreshed = await refreshTokens();
		if (!refreshed) {
			throw new Error("Session expired. Please login again.");
		}
		return authFetch<T>(path, init, false);
	}

	return parseResponse<T>(res);
}

function parseAuthPayload(payload: unknown): AuthTokens {
	const obj = payload as Record<string, unknown>;
	const token = String(obj.token || "");
	const refreshToken = String(obj.refreshToken || "");
	if (!token || !refreshToken) {
		throw new Error("Invalid auth response from server");
	}
	return { token, refreshToken };
}

function mapBudget(raw: RawBudget): Budget {
	return {
		id: raw.id,
		name: raw.categoryName || "Budget",
		amount: raw.amount,
		category: raw.categoryName,
		startDate: raw.startDate,
		endDate: raw.endDate,
	};
}

function mapSavings(raw: RawSavings): Savings {
	return {
		id: raw.id,
		goalName: raw.name,
		targetAmount: raw.targetAmount,
		currentAmount: raw.balance,
		targetDate: raw.targetDate,
		frequency: raw.depositFrequency,
	};
}

function mapSubscription(raw: RawSubscription): Subscription {
	return {
		id: raw.id,
		serviceName: raw.name,
		amount: raw.price,
		billingCycle: raw.billingCycle,
		startDate: raw.startDate,
		endDate: raw.endDate,
		status: raw.isActive ? "ACTIVE" : "INACTIVE",
	};
}

function mapCredit(raw: RawCredit): Credit {
	return {
		id: raw.id,
		cardName: raw.description,
		amount: raw.amount,
		dueDate: raw.dueDate,
		interestRate: raw.interestRate,
		status: raw.paid ? "PAID" : "UNPAID",
	};
}

function mapRecurringTransaction(raw: RawRecurringTransaction): RecurringTransaction {
	return {
		id: raw.id,
		description: raw.description,
		amount: raw.amount,
		frequency: String(raw.frequency || "").toLowerCase(),
		category: raw.categoryName,
		startDate: raw.startDate,
		endDate: raw.endDate,
		type: raw.type,
		status: raw.isActive ? "ACTIVE" : "INACTIVE",
	};
}

export const api = {
	todayString: normalizeDate,

	async login(email: string, password: string) {
		const data = await authFetch<unknown>("/v1/users/login", {
			method: "POST",
			body: JSON.stringify({ email, password }),
		});
		const tokens = parseAuthPayload(data);
		useAuthStore.getState().setTokens(tokens);
		return tokens;
	},

	async register(username: string, email: string, password: string) {
		await authFetch<void>("/v1/users/register", {
			method: "POST",
			body: JSON.stringify({ username, email, password }),
		});
	},

	async profile() {
		const profile = await authFetch<UserProfile>("/v1/users/profile", {
			method: "GET",
		});
		useAuthStore.getState().setProfile(profile);
		return profile;
	},

	async logout() {
		try {
			await authFetch<void>("/v1/users/logout", { method: "POST" });
		} finally {
			useAuthStore.getState().clear();
		}
	},

	async dashboard(userId: number) {
		return authFetch<DashboardData>(`/dashboard/${userId}`, { method: "GET" });
	},

	async listConversations() {
		return authFetch<ChatConversation[]>("/chat/conversations", { method: "GET" });
	},

	async getConversation(conversationId: string) {
		return authFetch<ChatConversation>(`/chat/conversations/${conversationId}`, { method: "GET" });
	},

	async deleteConversation(conversationId: string) {
		return authFetch<string>(`/chat/conversations/${conversationId}`, { method: "DELETE" });
	},

	async sendChatPrompt(request: ChatPromptRequest) {
		return authFetch<ChatPromptResponse>("/chat/prompt", {
			method: "POST",
			body: JSON.stringify(request),
		});
	},

	async listCategories() {
		return authFetch<Category[]>("/categories", { method: "GET" });
	},

	async listBankAccounts(userId: number) {
		return authFetch<BankAccount[]>(`/bankaccounts/${userId}`, { method: "GET" });
	},

	async createBankAccount(userId: number, bankName: string) {
		return authFetch<unknown>("/bankaccounts", {
			method: "POST",
			body: JSON.stringify({ bankName, userId }),
		});
	},

	async updateBankAccount(id: number, name: string, balance: number) {
		return authFetch<BankAccount>(`/bankaccounts/${id}`, {
			method: "PUT",
			body: JSON.stringify({ name, balance }),
		});
	},

	async deleteBankAccount(id: number) {
		return authFetch<unknown>(`/bankaccounts/${id}`, { method: "DELETE" });
	},

	async listExpenses(
		userId: number,
		filters: {
			start?: string;
			end?: string;
			categoryId?: number;
			minAmount?: number;
			maxAmount?: number;
			paymentMethod?: string;
			bankAccountId?: number;
			page?: number;
			size?: number;
		} = {}
	) {
		const params = new URLSearchParams();
		Object.entries(filters).forEach(([key, value]) => {
			if (value !== undefined && value !== null && String(value).trim() !== "") {
				params.set(key, String(value));
			}
		});
		const query = params.toString();
		const suffix = query ? `?${query}` : "";
		return authFetch<PageableResponse<Expense>>(`/expenses/user/${userId}${suffix}`, {
			method: "GET",
		});
	},

	async createExpense(payload: {
		description: string;
		amount: number;
		date: string;
		categoryId: number;
		paymentMethod: string;
		paidTo?: string;
		isRecurring?: boolean;
		userId: number;
		bankAccountId: number;
	}) {
		return authFetch<unknown>("/expenses", {
			method: "POST",
			body: JSON.stringify(payload),
		});
	},

	async deleteExpense(id: number) {
		return authFetch<unknown>(`/expenses/${id}`, { method: "DELETE" });
	},

	async listIncomes(
		userId: number,
		filters: {
			start?: string;
			end?: string;
			categoryId?: number;
			minAmount?: number;
			maxAmount?: number;
			bankAccountId?: number;
			page?: number;
			size?: number;
		} = {}
	) {
		const params = new URLSearchParams();
		Object.entries(filters).forEach(([key, value]) => {
			if (value !== undefined && value !== null && String(value).trim() !== "") {
				params.set(key, String(value));
			}
		});
		const query = params.toString();
		const suffix = query ? `?${query}` : "";
		return authFetch<PageableResponse<Income>>(`/incomes/user/${userId}${suffix}`, {
			method: "GET",
		});
	},

	async createIncome(payload: {
		description: string;
		amount: number;
		date: string;
		categoryId: number;
		source?: string;
		note?: string;
		userId: number;
		bankAccountId: number;
	}) {
		return authFetch<unknown>("/incomes", {
			method: "POST",
			body: JSON.stringify(payload),
		});
	},

	async deleteIncome(id: number) {
		return authFetch<unknown>(`/incomes/${id}`, { method: "DELETE" });
	},

	async listTransfers(userId: number) {
		return authFetch<Transfer[]>(`/transfers/user/${userId}`, { method: "GET" });
	},

	async createTransfer(payload: {
		amount: number;
		description?: string;
		transferDate: string;
		fromAccountId: number;
		toAccountId: number;
		userId: number;
	}) {
		return authFetch<Transfer>("/transfers", {
			method: "POST",
			body: JSON.stringify(payload),
		});
	},

	async deleteTransfer(id: number) {
		return authFetch<unknown>(`/transfers/${id}`, { method: "DELETE" });
	},

	async listBudgets(userId: number, filters: { page?: number; size?: number } = {}) {
		const params = new URLSearchParams();
		Object.entries(filters).forEach(([key, value]) => {
			if (value !== undefined && value !== null) {
				params.set(key, String(value));
			}
		});
		const query = params.toString();
		const suffix = query ? `?${query}` : "";
		const data = await authFetch<RawBudget[] | PageableResponse<RawBudget>>(`/budgets/user/${userId}${suffix}`, {
			method: "GET",
		});
		const isArray = Array.isArray(data);
		const content = (isArray ? data : (data.content || [])).map(mapBudget);
		return { ...(isArray ? {} : data), content } as PageableResponse<Budget>;
	},

	async createBudget(payload: {
		userId: number;
		name: string;
		amount: number;
		categoryId?: number;
		startDate: string;
		endDate: string;
	}) {
		const created = await authFetch<RawBudget>("/budgets", {
			method: "POST",
			body: JSON.stringify({
				userId: payload.userId,
				amount: payload.amount,
				categoryId: payload.categoryId,
				startDate: payload.startDate,
				endDate: payload.endDate,
				alertThreshold: 80,
			}),
		});
		return mapBudget(created);
	},

	async updateBudget(id: number, payload: { name?: string; amount?: number; endDate?: string }) {
		const updated = await authFetch<RawBudget>(`/budgets/${id}`, {
			method: "PUT",
			body: JSON.stringify(payload),
		});
		return mapBudget(updated);
	},

	async deleteBudget(id: number) {
		return authFetch<unknown>(`/budgets/${id}`, { method: "DELETE" });
	},

	async listSavings(userId: number, filters: { page?: number; size?: number } = {}) {
		const params = new URLSearchParams();
		Object.entries(filters).forEach(([key, value]) => {
			if (value !== undefined && value !== null) {
				params.set(key, String(value));
			}
		});
		const query = params.toString();
		const suffix = query ? `?${query}` : "";
		const data = await authFetch<RawSavings[] | PageableResponse<RawSavings>>(`/savings/user/${userId}${suffix}`, {
			method: "GET",
		});
		const isArray = Array.isArray(data);
		const content = (isArray ? data : (data.content || [])).map(mapSavings);
		return { ...(isArray ? {} : data), content } as PageableResponse<Savings>;
	},

	async createSavings(payload: {
		userId: number;
		goalName: string;
		targetAmount: number;
		targetDate: string;
		frequency?: string;
	}) {
		const created = await authFetch<RawSavings>("/savings", {
			method: "POST",
			body: JSON.stringify({
				userId: payload.userId,
				name: payload.goalName,
				targetAmount: payload.targetAmount,
				targetDate: payload.targetDate,
				depositFrequency: payload.frequency,
			}),
		});
		return mapSavings(created);
	},

	async updateSavings(id: number, payload: { currentAmount?: number; targetDate?: string; status?: string }) {
		const updated = await authFetch<RawSavings>(`/savings/${id}`, {
			method: "PUT",
			body: JSON.stringify({
				balance: payload.currentAmount,
				targetDate: payload.targetDate,
			}),
		});
		return mapSavings(updated);
	},

	async deleteSavings(id: number) {
		return authFetch<unknown>(`/savings/${id}`, { method: "DELETE" });
	},

	async listSubscriptions(userId: number, filters: { page?: number; size?: number } = {}) {
		const params = new URLSearchParams();
		Object.entries(filters).forEach(([key, value]) => {
			if (value !== undefined && value !== null) {
				params.set(key, String(value));
			}
		});
		const query = params.toString();
		const suffix = query ? `?${query}` : "";
		const data = await authFetch<RawSubscription[] | PageableResponse<RawSubscription>>(`/subscriptions/user/${userId}${suffix}`, {
			method: "GET",
		});
		const isArray = Array.isArray(data);
		const content = (isArray ? data : (data.content || [])).map(mapSubscription);
		return { ...(isArray ? {} : data), content } as PageableResponse<Subscription>;
	},

	async createSubscription(payload: {
		userId: number;
		serviceName: string;
		amount: number;
		billingCycle: string;
		startDate: string;
		categoryId?: number;
	}) {
		const created = await authFetch<RawSubscription>("/subscriptions", {
			method: "POST",
			body: JSON.stringify({
				userId: payload.userId,
				name: payload.serviceName,
				price: payload.amount,
				billingCycle: payload.billingCycle,
				startDate: payload.startDate,
			}),
		});
		return mapSubscription(created);
	},

	async updateSubscription(id: number, payload: { amount?: number; billingCycle?: string; status?: string; endDate?: string }) {
		const updated = await authFetch<RawSubscription>(`/subscriptions/${id}`, {
			method: "PUT",
			body: JSON.stringify({
				price: payload.amount,
				billingCycle: payload.billingCycle,
				endDate: payload.endDate,
				isActive: payload.status ? payload.status === "ACTIVE" : undefined,
			}),
		});
		return mapSubscription(updated);
	},

	async deleteSubscription(id: number) {
		return authFetch<unknown>(`/subscriptions/${id}`, { method: "DELETE" });
	},

	async listCredits(userId: number, filters: { page?: number; size?: number } = {}) {
		const params = new URLSearchParams();
		Object.entries(filters).forEach(([key, value]) => {
			if (value !== undefined && value !== null) {
				params.set(key, String(value));
			}
		});
		const query = params.toString();
		const suffix = query ? `?${query}` : "";
		const data = await authFetch<RawCredit[] | PageableResponse<RawCredit>>(`/credits/user/${userId}${suffix}`, {
			method: "GET",
		});
		const isArray = Array.isArray(data);
		const content = (isArray ? data : (data.content || [])).map(mapCredit);
		return { ...(isArray ? {} : data), content } as PageableResponse<Credit>;
	},

	async createCredit(payload: {
		userId: number;
		cardName: string;
		amount: number;
		dueDate: string;
		creditLimit?: number;
		interestRate?: number;
	}) {
		const created = await authFetch<RawCredit>("/credits", {
			method: "POST",
			body: JSON.stringify({
				userId: payload.userId,
				description: payload.cardName,
				amount: payload.amount,
				dueDate: payload.dueDate,
				type: "CREDIT_CARD",
				interestRate: payload.interestRate,
			}),
		});
		return mapCredit(created);
	},

	async updateCredit(id: number, payload: { amount?: number; status?: string; dueDate?: string }) {
		const updated = await authFetch<RawCredit>(`/credits/${id}`, {
			method: "PUT",
			body: JSON.stringify({
				amount: payload.amount,
				dueDate: payload.dueDate,
				paid: payload.status ? payload.status === "PAID" : undefined,
			}),
		});
		return mapCredit(updated);
	},

	async deleteCredit(id: number) {
		return authFetch<unknown>(`/credits/${id}`, { method: "DELETE" });
	},

	async listRecurringTransactions(userId: number, filters: { page?: number; size?: number } = {}) {
		const params = new URLSearchParams();
		Object.entries(filters).forEach(([key, value]) => {
			if (value !== undefined && value !== null) {
				params.set(key, String(value));
			}
		});
		const query = params.toString();
		const suffix = query ? `?${query}` : "";
		const data = await authFetch<RawRecurringTransaction[] | PageableResponse<RawRecurringTransaction>>(
			`/recurring-transactions/user/${userId}${suffix}`,
			{
				method: "GET",
			}
		);
		const isArray = Array.isArray(data);
		const content = (isArray ? data : (data.content || [])).map(mapRecurringTransaction);
		return {
			...(isArray ? {} : data),
			content,
		} as PageableResponse<RecurringTransaction>;
	},

	async createRecurringTransaction(payload: {
		userId: number;
		description: string;
		amount: number;
		frequency: string;
		type: "INCOME" | "EXPENSE";
		categoryId?: number;
		startDate: string;
		endDate?: string;
	}) {
		const created = await authFetch<RawRecurringTransaction>("/recurring-transactions", {
			method: "POST",
			body: JSON.stringify({
				userId: payload.userId,
				description: payload.description,
				amount: payload.amount,
				frequency: String(payload.frequency || "").toUpperCase(),
				type: payload.type,
				categoryId: payload.categoryId,
				startDate: payload.startDate,
				endDate: payload.endDate,
			}),
		});
		return mapRecurringTransaction(created);
	},

	async updateRecurringTransaction(id: number, payload: { amount?: number; status?: string; endDate?: string }) {
		const updated = await authFetch<RawRecurringTransaction>(`/recurring-transactions/${id}`, {
			method: "PUT",
			body: JSON.stringify({
				amount: payload.amount,
				endDate: payload.endDate,
				isActive: payload.status ? payload.status === "ACTIVE" : undefined,
			}),
		});
		return mapRecurringTransaction(updated);
	},

	async deleteRecurringTransaction(id: number) {
		return authFetch<unknown>(`/recurring-transactions/${id}`, { method: "DELETE" });
	},

	async getSettings() {
		return authFetch<UserSettings>("/v1/user-settings/me", {
			method: "GET",
		});
	},

	async updateSettings(settings: Partial<UserSettings>) {
		return authFetch<UserSettings>("/v1/user-settings/me", {
			method: "PUT",
			body: JSON.stringify(settings),
		});
	},
};

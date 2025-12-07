# EffinTrak API Documentation

## Base URL
```
http://localhost:8080/api
```

## Authentication

The API uses JWT (JSON Web Token) authentication. Most endpoints require authentication except for registration and login.

### Authentication Flow

1. **Register** a new user
2. **Login** to get access token and refresh token
3. Include the token in the `Authorization` header for subsequent requests:
   ```
   Authorization: Bearer <your-access-token>
   ```
4. Use the **refresh token** to get a new access token when it expires

---

## Response Format

All API responses follow a standard format:

### Success Response
```json
{
  "success": true,
  "message": "Operation completed successfully",
  "data": { ... }
}
```

### Error Response
```json
{
  "success": false,
  "message": "Error message",
  "data": null
}
```

### Paginated Response
```json
{
  "success": true,
  "message": "Fetched data successfully",
  "data": {
    "content": [ ... ],
    "page": 0,
    "size": 10,
    "totalElements": 100,
    "totalPages": 10
  }
}
```

---

## API Endpoints

### 1. Authentication & User Management

#### 1.1 Register User
**POST** `/v1/users/register`

**Authentication:** Not required

**Request Body:**
```json
{
  "firstName": "John",
  "lastName": "Doe",
  "email": "john.doe@example.com",
  "phoneNumber": "1234567890",
  "password": "password123",
  "role": "USER"
}
```

**Response:** `200 OK`
```json
"User registered successfully"
```

---

#### 1.2 Login
**POST** `/v1/users/login`

**Authentication:** Not required

**Request Body:**
```json
{
  "email": "john.doe@example.com",
  "password": "password123"
}
```

**Response:** `200 OK`
```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
  "refreshToken": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
}
```

**Error Response:** `401 Unauthorized`
```json
"Authentication failed"
```

---

#### 1.3 Refresh Token
**POST** `/v1/users/refresh`

**Authentication:** Not required

**Request Body:**
```json
{
  "refreshToken": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
}
```

**Response:** `200 OK`
```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
}
```

---

#### 1.4 Get User Profile
**GET** `/v1/users/profile`

**Authentication:** Required

**Response:** `200 OK`
```json
{
  "firstName": "John",
  "lastName": "Doe",
  "email": "john.doe@example.com",
  "phoneNumber": "1234567890",
  "role": "USER",
  "isActive": "true"
}
```

---

#### 1.5 Logout
**POST** `/v1/users/logout`

**Authentication:** Required

**Response:** `200 OK`
```json
"Logged out Successful"
```

---

### 2. Categories

#### 2.1 Create Category
**POST** `/categories`

**Authentication:** Required

**Request Body:**
```json
{
  "name": "Food"
}
```

**Response:** `200 OK`
```json
{
  "id": 1,
  "name": "Food"
}
```

---

#### 2.2 Get All Categories
**GET** `/categories`

**Authentication:** Required

**Response:** `200 OK`
```json
{
  "success": true,
  "message": "Categories retrieved successfully",
  "data": [
    {
      "id": 1,
      "name": "Food"
    },
    {
      "id": 2,
      "name": "Transportation"
    }
  ]
}
```

---

#### 2.3 Get Category by ID
**GET** `/categories/{id}`

**Authentication:** Required

**Response:** `200 OK`
```json
{
  "id": 1,
  "name": "Food"
}
```

---

#### 2.4 Update Category
**PUT** `/categories/{id}`

**Authentication:** Required

**Request Body:**
```json
{
  "name": "Updated Category Name"
}
```

**Response:** `200 OK`
```json
{
  "success": true,
  "message": "Category updated successfully",
  "data": {
    "id": 1,
    "name": "Updated Category Name"
  }
}
```

---

### 3. Bank Accounts

#### 3.1 Create Bank Account
**POST** `/bankaccounts`

**Authentication:** Required

**Request Body:**
```json
{
  "userId": 1,
  "bankName": "Chase Bank"
}
```

**Response:** `200 OK`
```json
{
  "success": true,
  "message": "Bank account created successfully"
}
```

---

#### 3.2 Get All Bank Accounts for User
**GET** `/bankaccounts/{userId}`

**Authentication:** Required

**Response:** `200 OK`
```json
{
  "success": true,
  "message": "Fetched all bank accounts",
  "data": [
    {
      "id": 1,
      "name": "Chase Bank",
      "balance": 5000.00
    }
  ]
}
```

---

#### 3.3 Update Bank Account
**PUT** `/bankaccounts/{id}`

**Authentication:** Required

**Request Body:**
```json
{
  "name": "Updated Bank Name",
  "balance": 6000.00
}
```

**Response:** `200 OK`
```json
{
  "success": true,
  "message": "Bank account updated successfully",
  "data": {
    "id": 1,
    "name": "Updated Bank Name",
    "balance": 6000.00
  }
}
```

---

#### 3.4 Delete Bank Account
**DELETE** `/bankaccounts/{id}`

**Authentication:** Required

**Response:** `200 OK`
```json
{
  "success": true,
  "message": "Bank account deleted successfully",
  "data": null
}
```

---

### 4. Income

#### 4.1 Create Income
**POST** `/incomes`

**Authentication:** Required

**Request Body:**
```json
{
  "description": "Monthly Salary",
  "amount": 5000.00,
  "date": "2024-01-15",
  "categoryId": 1,
  "source": "Employer",
  "note": "January salary",
  "userId": 1,
  "bankAccountId": 1
}
```

**Response:** `200 OK`
```json
{
  "success": true,
  "message": "Income added successfully"
}
```

---

#### 4.2 Get Incomes for User
**GET** `/incomes/user/{userId}`

**Authentication:** Required

**Query Parameters:**
- `start` (optional): Start date (format: yyyy-MM-dd)
- `end` (optional): End date (format: yyyy-MM-dd)
- `categoryId` (optional): Filter by category
- `minAmount` (optional): Minimum amount
- `maxAmount` (optional): Maximum amount
- `bankAccountId` (optional): Filter by bank account
- `page` (optional): Page number (default: 0)
- `size` (optional): Page size (default: 10)

**Example:**
```
GET /api/incomes/user/1?start=2024-01-01&end=2024-01-31&page=0&size=10
```

**Response:** `200 OK`
```json
{
  "success": true,
  "message": "Fetched incomes for user",
  "data": {
    "content": [
      {
        "id": 1,
        "description": "Monthly Salary",
        "amount": 5000.00,
        "category": "Salary",
        "source": "Employer",
        "note": "January salary",
        "bankAccount": "Chase Bank",
        "date": "2024-01-15"
      }
    ],
    "page": 0,
    "size": 10,
    "totalElements": 1,
    "totalPages": 1
  }
}
```

---

#### 4.3 Search Incomes
**GET** `/incomes/user/{userId}/search?search={query}`

**Authentication:** Required

**Response:** `200 OK`
```json
{
  "success": true,
  "message": "Search results for incomes",
  "data": [ ... ]
}
```

---

#### 4.4 Update Income
**PUT** `/incomes/user/{incomeId}`

**Authentication:** Required

**Request Body:**
```json
{
  "userId": 1,
  "description": "Updated Salary",
  "amount": 5500.00,
  "date": "2024-01-15",
  "categoryId": 1,
  "source": "Employer",
  "note": "Updated note"
}
```

**Response:** `200 OK`
```json
{
  "success": true,
  "message": "Income updated successfully",
  "data": { ... }
}
```

---

#### 4.5 Delete Income
**DELETE** `/incomes/{id}`

**Authentication:** Required

**Response:** `204 No Content`

---

### 5. Expenses

#### 5.1 Create Expense
**POST** `/expenses`

**Authentication:** Required

**Request Body:**
```json
{
  "description": "Grocery Shopping",
  "amount": 150.00,
  "date": "2024-01-15",
  "categoryId": 1,
  "paymentMethod": "Credit Card",
  "paidTo": "Walmart",
  "isRecurring": false,
  "userId": 1,
  "bankAccountId": 1
}
```

**Response:** `200 OK`
```json
{
  "success": true,
  "message": "Expense added successfully"
}
```

---

#### 5.2 Get Expenses for User
**GET** `/expenses/user/{userId}`

**Authentication:** Required

**Query Parameters:**
- `start` (optional): Start date (format: yyyy-MM-dd)
- `end` (optional): End date (format: yyyy-MM-dd)
- `categoryId` (optional): Filter by category
- `minAmount` (optional): Minimum amount
- `maxAmount` (optional): Maximum amount
- `paymentMethod` (optional): Filter by payment method
- `bankAccountId` (optional): Filter by bank account
- `page` (optional): Page number (default: 0)
- `size` (optional): Page size (default: 10)

**Response:** `200 OK`
```json
{
  "success": true,
  "message": "Fetched expenses for user",
  "data": {
    "content": [
      {
        "id": 1,
        "description": "Grocery Shopping",
        "amount": 150.00,
        "category": "Food",
        "date": "2024-01-15",
        "paymentMethod": "Credit Card",
        "bankAccount": "Chase Bank",
        "paidTo": "Walmart",
        "isRecurring": false
      }
    ],
    "page": 0,
    "size": 10,
    "totalElements": 1,
    "totalPages": 1
  }
}
```

---

#### 5.3 Search Expenses
**GET** `/expenses/user/{userId}/search?search={query}`

**Authentication:** Required

**Response:** `200 OK`
```json
{
  "success": true,
  "message": "Search results for expenses",
  "data": [ ... ]
}
```

---

#### 5.4 Update Expense
**PUT** `/expenses/user/{expenseId}`

**Authentication:** Required

**Request Body:**
```json
{
  "userId": 1,
  "description": "Updated Grocery Shopping",
  "amount": 175.00,
  "date": "2024-01-15",
  "categoryId": 1,
  "paymentMethod": "Debit Card",
  "paidTo": "Target",
  "isRecurring": false
}
```

**Response:** `200 OK`
```json
{
  "success": true,
  "message": "Income updated successfully",
  "data": { ... }
}
```

---

#### 5.5 Delete Expense
**DELETE** `/expenses/{id}`

**Authentication:** Required

**Response:** `204 No Content`

---

### 6. Transfers

#### 6.1 Create Transfer
**POST** `/transfers`

**Authentication:** Required

**Request Body:**
```json
{
  "userId": 1,
  "fromAccountId": 1,
  "toAccountId": 2,
  "amount": 500.00,
  "description": "Transfer to savings"
}
```

**Response:** `200 OK`
```json
{
  "success": true,
  "message": "Transfer completed successfully",
  "data": {
    "id": 1,
    "fromAccount": "Chase Bank",
    "toAccount": "Savings Account",
    "amount": 500.00,
    "date": "2024-01-15",
    "description": "Transfer to savings"
  }
}
```

---

#### 6.2 Get Transfers for User
**GET** `/transfers/user/{userId}`

**Authentication:** Required

**Response:** `200 OK`
```json
{
  "success": true,
  "message": "Fetched transfers for user",
  "data": [ ... ]
}
```

---

### 7. Transactions (Combined Income & Expense)

#### 7.1 Get All Transactions
**GET** `/transactions/user/{userId}`

**Authentication:** Required

**Response:** `200 OK`
```json
{
  "success": true,
  "message": "Fetched all transactions for user",
  "data": [
    {
      "id": 1,
      "type": "INCOME",
      "description": "Monthly Salary",
      "amount": 5000.00,
      "date": "2024-01-15",
      "categoryName": "Salary",
      "sourceOrPaidTo": "Employer"
    },
    {
      "id": 2,
      "type": "EXPENSE",
      "description": "Grocery Shopping",
      "amount": 150.00,
      "date": "2024-01-16",
      "categoryName": "Food",
      "sourceOrPaidTo": "Walmart"
    }
  ]
}
```

---

#### 7.2 Get Transactions Between Dates
**GET** `/transactions/user/{userId}/filter?startDate={startDate}&endDate={endDate}`

**Authentication:** Required

**Response:** `200 OK`
```json
{
  "success": true,
  "message": "Fetched transactions for user between dates",
  "data": [ ... ]
}
```

---

#### 7.3 Search Transactions
**GET** `/transactions/user/{userId}/search?query={searchTerm}`

**Authentication:** Required

**Response:** `200 OK`
```json
{
  "success": true,
  "message": "Search results for transactions",
  "data": [ ... ]
}
```

---

### 8. Budgets

#### 8.1 Create Budget
**POST** `/budgets`

**Authentication:** Required

**Request Body:**
```json
{
  "userId": 1,
  "categoryId": 1,
  "amount": 500.00,
  "startDate": "2024-01-01",
  "endDate": "2024-01-31",
  "period": "MONTHLY"
}
```

**Response:** `200 OK`
```json
{
  "success": true,
  "message": "Budget created successfully",
  "data": {
    "id": 1,
    "categoryId": 1,
    "amount": 500.00,
    "startDate": "2024-01-01",
    "endDate": "2024-01-31",
    "period": "MONTHLY"
  }
}
```

---

#### 8.2 Get Budgets for User
**GET** `/budgets/user/{userId}`

**Authentication:** Required

**Response:** `200 OK`
```json
{
  "success": true,
  "message": "Fetched budgets for user",
  "data": [ ... ]
}
```

---

#### 8.3 Update Budget
**PUT** `/budgets/{id}`

**Authentication:** Required

**Request Body:**
```json
{
  "amount": 600.00,
  "startDate": "2024-01-01",
  "endDate": "2024-01-31"
}
```

**Response:** `200 OK`
```json
{
  "success": true,
  "message": "Budget updated successfully",
  "data": { ... }
}
```

---

#### 8.4 Delete Budget
**DELETE** `/budgets/{id}`

**Authentication:** Required

**Response:** `200 OK`
```json
{
  "success": true,
  "message": "Budget deleted successfully",
  "data": null
}
```

---

### 9. Credits

#### 9.1 Create Credit
**POST** `/credits`

**Authentication:** Required

**Request Body:**
```json
{
  "userId": 1,
  "description": "Credit Card Payment",
  "type": "CREDIT_CARD",
  "amount": 1000.00,
  "dueDate": "2024-02-01",
  "creditorAccountId": 1
}
```

**Response:** `200 OK`
```json
{
  "success": true,
  "message": "Credit created successfully",
  "data": { ... }
}
```

---

#### 9.2 Get Credits for User
**GET** `/credits/user/{userId}`

**Authentication:** Required

**Response:** `200 OK`
```json
{
  "success": true,
  "message": "Fetched credits for user",
  "data": [ ... ]
}
```

---

#### 9.3 Get Credits Between Dates
**GET** `/credits/user/{userId}/filter?start={startDate}&end={endDate}`

**Authentication:** Required

**Response:** `200 OK`
```json
{
  "success": true,
  "message": "Fetched credits for user between dates",
  "data": [ ... ]
}
```

---

### 10. Savings

#### 10.1 Create Savings Goal
**POST** `/savings`

**Authentication:** Required

**Request Body:**
```json
{
  "userId": 1,
  "name": "Vacation Fund",
  "targetAmount": 5000.00,
  "currentAmount": 1000.00,
  "targetDate": "2024-12-31"
}
```

**Response:** `200 OK`
```json
{
  "success": true,
  "message": "Savings goal created successfully",
  "data": { ... }
}
```

---

#### 10.2 Get Savings for User
**GET** `/savings/user/{userId}`

**Authentication:** Required

**Response:** `200 OK`
```json
{
  "success": true,
  "message": "Fetched savings for user",
  "data": [ ... ]
}
```

---

### 11. Subscriptions

#### 11.1 Create Subscription
**POST** `/subscriptions`

**Authentication:** Required

**Request Body:**
```json
{
  "userId": 1,
  "name": "Netflix",
  "description": "Monthly subscription",
  "price": 15.99,
  "startDate": "2024-01-01",
  "endDate": "2024-12-31",
  "isActive": true
}
```

**Response:** `200 OK`
```json
{
  "success": true,
  "message": "Subscription created successfully",
  "data": { ... }
}
```

---

#### 11.2 Get Subscriptions for User
**GET** `/subscriptions/user/{userId}`

**Authentication:** Required

**Response:** `200 OK`
```json
{
  "success": true,
  "message": "Fetched subscriptions for user",
  "data": [ ... ]
}
```

---

### 12. Recurring Transactions

#### 12.1 Create Recurring Transaction
**POST** `/recurring-transactions`

**Authentication:** Required

**Request Body:**
```json
{
  "userId": 1,
  "description": "Monthly Rent",
  "amount": 1200.00,
  "frequency": "MONTHLY",
  "startDate": "2024-01-01",
  "categoryId": 1,
  "bankAccountId": 1
}
```

**Response:** `200 OK`
```json
{
  "success": true,
  "message": "Recurring transaction created successfully",
  "data": { ... }
}
```

---

#### 12.2 Get Recurring Transactions for User
**GET** `/recurring-transactions/user/{userId}`

**Authentication:** Required

**Response:** `200 OK`
```json
{
  "success": true,
  "message": "Fetched recurring transactions for user",
  "data": [ ... ]
}
```

---

### 13. Reports

#### 13.1 Generate Report
**GET** `/reports/user/{userId}?startDate={startDate}&endDate={endDate}`

**Authentication:** Required

**Response:** `200 OK`
```json
{
  "success": true,
  "message": "Generated report for user",
  "data": {
    "totalIncome": 5000.00,
    "totalExpense": 1500.00,
    "balance": 3500.00,
    "incomeByCategory": {
      "Salary": 5000.00
    },
    "expenseByCategory": {
      "Food": 800.00,
      "Transportation": 700.00
    }
  }
}
```

---

#### 13.2 Get Monthly Trend
**GET** `/reports/user/{userId}/monthly-trend?year={year}`

**Authentication:** Required

**Response:** `200 OK`
```json
{
  "success": true,
  "message": "Monthly trend retrieved successfully",
  "data": [
    {
      "month": "2024-01",
      "income": 5000.00,
      "expense": 1500.00,
      "balance": 3500.00
    }
  ]
}
```

---

#### 13.3 Get Category Trend
**GET** `/reports/user/{userId}/category-trend?categoryId={categoryId}&period={period}`

**Authentication:** Required

**Query Parameters:**
- `categoryId` (optional): Filter by category
- `period` (optional): "monthly", "weekly", "yearly" (default: "monthly")

**Response:** `200 OK`
```json
{
  "success": true,
  "message": "Category trend retrieved successfully",
  "data": [ ... ]
}
```

---

#### 13.4 Get Comparison Report
**GET** `/reports/user/{userId}/comparison?startDate={startDate}&endDate={endDate}`

**Authentication:** Required

**Response:** `200 OK`
```json
{
  "success": true,
  "message": "Comparison report retrieved successfully",
  "data": { ... }
}
```

---

### 14. Dashboard

#### 14.1 Get Dashboard Data
**GET** `/dashboard/{userId}`

**Authentication:** Required

**Response:** `200 OK`
```json
{
  "success": true,
  "message": "Dashboard data retrieved successfully",
  "data": {
    "totalBalance": 5000.00,
    "totalIncome": 10000.00,
    "totalExpense": 5000.00,
    "recentTransactions": [ ... ],
    "budgetSummary": [ ... ],
    "upcomingBills": [ ... ],
    "savingsProgress": [ ... ]
  }
}
```

---

### 15. Bills

#### 15.1 Get Overdue Bills
**GET** `/bills/user/{userId}/overdue`

**Authentication:** Required

**Response:** `200 OK`
```json
{
  "success": true,
  "message": "Fetched overdue bills",
  "data": [
    {
      "id": 1,
      "type": "CREDIT",
      "description": "Credit Card Payment",
      "amount": 1000.00,
      "dueDate": "2024-01-01",
      "daysUntilDue": -5,
      "isOverdue": true
    }
  ]
}
```

---

### 16. Notifications

#### 16.1 Get Notifications for User
**GET** `/notifications/user/{userId}`

**Authentication:** Required

**Response:** `200 OK`
```json
{
  "success": true,
  "message": "Fetched notifications for user",
  "data": [
    {
      "id": 1,
      "message": "Budget exceeded for Food category",
      "type": "BUDGET_ALERT",
      "isRead": false,
      "createdAt": "2024-01-15T10:00:00"
    }
  ]
}
```

---

### 17. Export

#### 17.1 Export Transactions
**GET** `/export/user/{userId}/transactions?startDate={startDate}&endDate={endDate}&format={format}`

**Authentication:** Required

**Query Parameters:**
- `startDate`: Start date (format: yyyy-MM-dd)
- `endDate`: End date (format: yyyy-MM-dd)
- `format`: Export format (default: "csv")

**Response:** `200 OK`
- Content-Type: `text/plain`
- Content-Disposition: `attachment; filename="transactions_2024-01-01_to_2024-01-31.csv"`
- Body: CSV file content

---

#### 17.2 Export Report
**GET** `/export/user/{userId}/report?startDate={startDate}&endDate={endDate}&format={format}`

**Authentication:** Required

**Query Parameters:**
- `startDate`: Start date (format: yyyy-MM-dd)
- `endDate`: End date (format: yyyy-MM-dd)
- `format`: Export format ("csv" or "text", default: "csv")

**Response:** `200 OK`
- Content-Type: `text/plain`
- Content-Disposition: `attachment; filename="report_2024-01-01_to_2024-01-31.csv"`
- Body: CSV or text file content

---

## Error Codes

| Status Code | Description |
|------------|-------------|
| 200 | Success |
| 201 | Created |
| 204 | No Content |
| 400 | Bad Request - Invalid input data |
| 401 | Unauthorized - Invalid or missing token |
| 403 | Forbidden - Insufficient permissions |
| 404 | Not Found - Resource not found |
| 500 | Internal Server Error |

---

## Common Patterns

### Date Format
All dates should be in `yyyy-MM-dd` format (e.g., "2024-01-15")

### Pagination
For paginated endpoints, use query parameters:
- `page`: Page number (0-indexed, default: 0)
- `size`: Number of items per page (default: 10)

### Filtering
Many endpoints support filtering with query parameters:
- Date ranges: `start` and `end`
- Amount ranges: `minAmount` and `maxAmount`
- Category: `categoryId`
- Bank Account: `bankAccountId`

### Search
Search endpoints use the `search` or `query` parameter for text-based searches.

---

## Frontend Integration Examples

### JavaScript/TypeScript Example

```typescript
// API Client Configuration
const API_BASE_URL = 'http://localhost:8080/api';

// Authentication Helper
class ApiClient {
  private token: string | null = null;

  async login(email: string, password: string) {
    const response = await fetch(`${API_BASE_URL}/v1/users/login`, {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({ email, password })
    });
    
    if (response.ok) {
      const data = await response.json();
      this.token = data.token;
      localStorage.setItem('token', data.token);
      localStorage.setItem('refreshToken', data.refreshToken);
      return data;
    }
    throw new Error('Login failed');
  }

  private getHeaders(): HeadersInit {
    const headers: HeadersInit = {
      'Content-Type': 'application/json'
    };
    
    const token = this.token || localStorage.getItem('token');
    if (token) {
      headers['Authorization'] = `Bearer ${token}`;
    }
    
    return headers;
  }

  async getIncomes(userId: number, filters?: {
    start?: string;
    end?: string;
    categoryId?: number;
    page?: number;
    size?: number;
  }) {
    const params = new URLSearchParams();
    if (filters?.start) params.append('start', filters.start);
    if (filters?.end) params.append('end', filters.end);
    if (filters?.categoryId) params.append('categoryId', filters.categoryId.toString());
    if (filters?.page !== undefined) params.append('page', filters.page.toString());
    if (filters?.size) params.append('size', filters.size.toString());

    const response = await fetch(
      `${API_BASE_URL}/incomes/user/${userId}?${params}`,
      { headers: this.getHeaders() }
    );
    
    return response.json();
  }

  async createIncome(income: {
    description: string;
    amount: number;
    date: string;
    categoryId: number;
    userId: number;
    bankAccountId: number;
    source?: string;
    note?: string;
  }) {
    const response = await fetch(`${API_BASE_URL}/incomes`, {
      method: 'POST',
      headers: this.getHeaders(),
      body: JSON.stringify(income)
    });
    
    return response.json();
  }
}

// Usage
const api = new ApiClient();

// Login
await api.login('user@example.com', 'password123');

// Get incomes
const incomes = await api.getIncomes(1, {
  start: '2024-01-01',
  end: '2024-01-31',
  page: 0,
  size: 10
});

// Create income
await api.createIncome({
  description: 'Salary',
  amount: 5000,
  date: '2024-01-15',
  categoryId: 1,
  userId: 1,
  bankAccountId: 1,
  source: 'Employer'
});
```

### React Example

```jsx
import { useState, useEffect } from 'react';

function IncomeList({ userId }) {
  const [incomes, setIncomes] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    const fetchIncomes = async () => {
      const token = localStorage.getItem('token');
      const response = await fetch(
        `http://localhost:8080/api/incomes/user/${userId}?page=0&size=10`,
        {
          headers: {
            'Authorization': `Bearer ${token}`,
            'Content-Type': 'application/json'
          }
        }
      );
      
      const data = await response.json();
      if (data.success) {
        setIncomes(data.data.content);
      }
      setLoading(false);
    };

    fetchIncomes();
  }, [userId]);

  if (loading) return <div>Loading...</div>;

  return (
    <div>
      <h2>Incomes</h2>
      <ul>
        {incomes.map(income => (
          <li key={income.id}>
            {income.description}: ${income.amount}
          </li>
        ))}
      </ul>
    </div>
  );
}
```

---

## Notes

1. **Token Expiration**: Access tokens expire after 1 hour. Use the refresh token endpoint to get a new access token.

2. **Pagination**: Always check `totalPages` and `totalElements` in paginated responses to implement proper pagination UI.

3. **Error Handling**: Always check the `success` field in responses and handle errors appropriately.

4. **Date Format**: Ensure all dates are sent in `yyyy-MM-dd` format.

5. **Required Fields**: Check each endpoint's request body for required fields. Missing required fields will result in 400 Bad Request.

6. **CORS**: If developing a frontend on a different origin, ensure CORS is properly configured on the backend.

---

## Swagger/OpenAPI

The API also exposes Swagger documentation at:
```
http://localhost:8080/swagger-ui.html
```

You can use this interactive documentation to explore and test all endpoints.


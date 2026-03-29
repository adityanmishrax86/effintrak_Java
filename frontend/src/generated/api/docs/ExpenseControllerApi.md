# ExpenseControllerApi

All URIs are relative to *http://localhost:8080*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**createExpense**](ExpenseControllerApi.md#createexpense) | **POST** /api/expenses |  |
| [**deleteExpense**](ExpenseControllerApi.md#deleteexpense) | **DELETE** /api/expenses/{id} |  |
| [**getExpenseByUserId**](ExpenseControllerApi.md#getexpensebyuserid) | **GET** /api/expenses/user/{userId} |  |
| [**searchExpenses**](ExpenseControllerApi.md#searchexpenses) | **GET** /api/expenses/user/{userId}/search |  |
| [**updateExpense**](ExpenseControllerApi.md#updateexpense) | **PUT** /api/expenses/user/{expenseId} |  |



## createExpense

> object createExpense(newExpenseRequestDTO)



### Example

```ts
import {
  Configuration,
  ExpenseControllerApi,
} from '';
import type { CreateExpenseRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new ExpenseControllerApi();

  const body = {
    // NewExpenseRequestDTO
    newExpenseRequestDTO: ...,
  } satisfies CreateExpenseRequest;

  try {
    const data = await api.createExpense(body);
    console.log(data);
  } catch (error) {
    console.error(error);
  }
}

// Run the test
example().catch(console.error);
```

### Parameters


| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **newExpenseRequestDTO** | [NewExpenseRequestDTO](NewExpenseRequestDTO.md) |  | |

### Return type

**object**

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: `application/json`
- **Accept**: `application/hal+json`


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **400** | Bad Request |  -  |
| **403** | Forbidden |  -  |
| **200** | OK |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#api-endpoints) [[Back to Model list]](../README.md#models) [[Back to README]](../README.md)


## deleteExpense

> deleteExpense(id)



### Example

```ts
import {
  Configuration,
  ExpenseControllerApi,
} from '';
import type { DeleteExpenseRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new ExpenseControllerApi();

  const body = {
    // number
    id: 789,
  } satisfies DeleteExpenseRequest;

  try {
    const data = await api.deleteExpense(body);
    console.log(data);
  } catch (error) {
    console.error(error);
  }
}

// Run the test
example().catch(console.error);
```

### Parameters


| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **id** | `number` |  | [Defaults to `undefined`] |

### Return type

`void` (Empty response body)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: `application/hal+json`


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **400** | Bad Request |  -  |
| **403** | Forbidden |  -  |
| **200** | OK |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#api-endpoints) [[Back to Model list]](../README.md#models) [[Back to README]](../README.md)


## getExpenseByUserId

> object getExpenseByUserId(userId, pageable, start, end, categoryId, minAmount, maxAmount, paymentMethod, bankAccountId)



### Example

```ts
import {
  Configuration,
  ExpenseControllerApi,
} from '';
import type { GetExpenseByUserIdRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new ExpenseControllerApi();

  const body = {
    // number
    userId: 789,
    // Pageable
    pageable: ...,
    // string (optional)
    start: start_example,
    // string (optional)
    end: end_example,
    // number (optional)
    categoryId: 789,
    // number (optional)
    minAmount: 1.2,
    // number (optional)
    maxAmount: 1.2,
    // string (optional)
    paymentMethod: paymentMethod_example,
    // number (optional)
    bankAccountId: 789,
  } satisfies GetExpenseByUserIdRequest;

  try {
    const data = await api.getExpenseByUserId(body);
    console.log(data);
  } catch (error) {
    console.error(error);
  }
}

// Run the test
example().catch(console.error);
```

### Parameters


| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **userId** | `number` |  | [Defaults to `undefined`] |
| **pageable** | [](.md) |  | [Defaults to `undefined`] |
| **start** | `string` |  | [Optional] [Defaults to `undefined`] |
| **end** | `string` |  | [Optional] [Defaults to `undefined`] |
| **categoryId** | `number` |  | [Optional] [Defaults to `undefined`] |
| **minAmount** | `number` |  | [Optional] [Defaults to `undefined`] |
| **maxAmount** | `number` |  | [Optional] [Defaults to `undefined`] |
| **paymentMethod** | `string` |  | [Optional] [Defaults to `undefined`] |
| **bankAccountId** | `number` |  | [Optional] [Defaults to `undefined`] |

### Return type

**object**

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: `application/hal+json`


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **400** | Bad Request |  -  |
| **403** | Forbidden |  -  |
| **200** | OK |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#api-endpoints) [[Back to Model list]](../README.md#models) [[Back to README]](../README.md)


## searchExpenses

> object searchExpenses(userId, search)



### Example

```ts
import {
  Configuration,
  ExpenseControllerApi,
} from '';
import type { SearchExpensesRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new ExpenseControllerApi();

  const body = {
    // number
    userId: 789,
    // string
    search: search_example,
  } satisfies SearchExpensesRequest;

  try {
    const data = await api.searchExpenses(body);
    console.log(data);
  } catch (error) {
    console.error(error);
  }
}

// Run the test
example().catch(console.error);
```

### Parameters


| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **userId** | `number` |  | [Defaults to `undefined`] |
| **search** | `string` |  | [Defaults to `undefined`] |

### Return type

**object**

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: `application/hal+json`


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **400** | Bad Request |  -  |
| **403** | Forbidden |  -  |
| **200** | OK |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#api-endpoints) [[Back to Model list]](../README.md#models) [[Back to README]](../README.md)


## updateExpense

> object updateExpense(expenseId, updateExpenseRequestDTO)



### Example

```ts
import {
  Configuration,
  ExpenseControllerApi,
} from '';
import type { UpdateExpenseRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new ExpenseControllerApi();

  const body = {
    // number
    expenseId: 789,
    // UpdateExpenseRequestDTO
    updateExpenseRequestDTO: ...,
  } satisfies UpdateExpenseRequest;

  try {
    const data = await api.updateExpense(body);
    console.log(data);
  } catch (error) {
    console.error(error);
  }
}

// Run the test
example().catch(console.error);
```

### Parameters


| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **expenseId** | `number` |  | [Defaults to `undefined`] |
| **updateExpenseRequestDTO** | [UpdateExpenseRequestDTO](UpdateExpenseRequestDTO.md) |  | |

### Return type

**object**

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: `application/json`
- **Accept**: `application/hal+json`


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **400** | Bad Request |  -  |
| **403** | Forbidden |  -  |
| **200** | OK |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#api-endpoints) [[Back to Model list]](../README.md#models) [[Back to README]](../README.md)


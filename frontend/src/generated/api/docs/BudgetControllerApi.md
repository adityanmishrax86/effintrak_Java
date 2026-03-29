# BudgetControllerApi

All URIs are relative to *http://localhost:8080*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**createBudget**](BudgetControllerApi.md#createbudget) | **POST** /api/budgets |  |
| [**deleteBudget**](BudgetControllerApi.md#deletebudget) | **DELETE** /api/budgets/{id} |  |
| [**getBudgetsByUserId**](BudgetControllerApi.md#getbudgetsbyuserid) | **GET** /api/budgets/user/{userId} |  |
| [**updateBudget**](BudgetControllerApi.md#updatebudget) | **PUT** /api/budgets/{id} |  |



## createBudget

> object createBudget(budgetRequestDTO)



### Example

```ts
import {
  Configuration,
  BudgetControllerApi,
} from '';
import type { CreateBudgetRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new BudgetControllerApi();

  const body = {
    // BudgetRequestDTO
    budgetRequestDTO: ...,
  } satisfies CreateBudgetRequest;

  try {
    const data = await api.createBudget(body);
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
| **budgetRequestDTO** | [BudgetRequestDTO](BudgetRequestDTO.md) |  | |

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


## deleteBudget

> object deleteBudget(id)



### Example

```ts
import {
  Configuration,
  BudgetControllerApi,
} from '';
import type { DeleteBudgetRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new BudgetControllerApi();

  const body = {
    // number
    id: 789,
  } satisfies DeleteBudgetRequest;

  try {
    const data = await api.deleteBudget(body);
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


## getBudgetsByUserId

> object getBudgetsByUserId(userId)



### Example

```ts
import {
  Configuration,
  BudgetControllerApi,
} from '';
import type { GetBudgetsByUserIdRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new BudgetControllerApi();

  const body = {
    // number
    userId: 789,
  } satisfies GetBudgetsByUserIdRequest;

  try {
    const data = await api.getBudgetsByUserId(body);
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


## updateBudget

> object updateBudget(id, updateBudgetRequestDTO)



### Example

```ts
import {
  Configuration,
  BudgetControllerApi,
} from '';
import type { UpdateBudgetRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new BudgetControllerApi();

  const body = {
    // number
    id: 789,
    // UpdateBudgetRequestDTO
    updateBudgetRequestDTO: ...,
  } satisfies UpdateBudgetRequest;

  try {
    const data = await api.updateBudget(body);
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
| **updateBudgetRequestDTO** | [UpdateBudgetRequestDTO](UpdateBudgetRequestDTO.md) |  | |

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


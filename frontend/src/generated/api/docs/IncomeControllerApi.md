# IncomeControllerApi

All URIs are relative to *http://localhost:8080*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**createIncome**](IncomeControllerApi.md#createincome) | **POST** /api/incomes |  |
| [**deleteIncome**](IncomeControllerApi.md#deleteincome) | **DELETE** /api/incomes/{id} |  |
| [**getIncomeByUserId**](IncomeControllerApi.md#getincomebyuserid) | **GET** /api/incomes/user/{userId} |  |
| [**searchIncomes**](IncomeControllerApi.md#searchincomes) | **GET** /api/incomes/user/{userId}/search |  |
| [**updateIncome**](IncomeControllerApi.md#updateincome) | **PUT** /api/incomes/user/{incomeId} |  |



## createIncome

> object createIncome(newIncomeRequestDTO)



### Example

```ts
import {
  Configuration,
  IncomeControllerApi,
} from '';
import type { CreateIncomeRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new IncomeControllerApi();

  const body = {
    // NewIncomeRequestDTO
    newIncomeRequestDTO: ...,
  } satisfies CreateIncomeRequest;

  try {
    const data = await api.createIncome(body);
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
| **newIncomeRequestDTO** | [NewIncomeRequestDTO](NewIncomeRequestDTO.md) |  | |

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


## deleteIncome

> deleteIncome(id)



### Example

```ts
import {
  Configuration,
  IncomeControllerApi,
} from '';
import type { DeleteIncomeRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new IncomeControllerApi();

  const body = {
    // number
    id: 789,
  } satisfies DeleteIncomeRequest;

  try {
    const data = await api.deleteIncome(body);
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


## getIncomeByUserId

> object getIncomeByUserId(userId, pageable, start, end, categoryId, minAmount, maxAmount, bankAccountId)



### Example

```ts
import {
  Configuration,
  IncomeControllerApi,
} from '';
import type { GetIncomeByUserIdRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new IncomeControllerApi();

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
    // number (optional)
    bankAccountId: 789,
  } satisfies GetIncomeByUserIdRequest;

  try {
    const data = await api.getIncomeByUserId(body);
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


## searchIncomes

> object searchIncomes(userId, search)



### Example

```ts
import {
  Configuration,
  IncomeControllerApi,
} from '';
import type { SearchIncomesRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new IncomeControllerApi();

  const body = {
    // number
    userId: 789,
    // string
    search: search_example,
  } satisfies SearchIncomesRequest;

  try {
    const data = await api.searchIncomes(body);
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


## updateIncome

> object updateIncome(incomeId, updateIncomeRequestDTO)



### Example

```ts
import {
  Configuration,
  IncomeControllerApi,
} from '';
import type { UpdateIncomeRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new IncomeControllerApi();

  const body = {
    // number
    incomeId: 789,
    // UpdateIncomeRequestDTO
    updateIncomeRequestDTO: ...,
  } satisfies UpdateIncomeRequest;

  try {
    const data = await api.updateIncome(body);
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
| **incomeId** | `number` |  | [Defaults to `undefined`] |
| **updateIncomeRequestDTO** | [UpdateIncomeRequestDTO](UpdateIncomeRequestDTO.md) |  | |

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


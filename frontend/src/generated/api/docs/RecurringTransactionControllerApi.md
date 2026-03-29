# RecurringTransactionControllerApi

All URIs are relative to *http://localhost:8080*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**createRecurringTransaction**](RecurringTransactionControllerApi.md#createrecurringtransaction) | **POST** /api/recurring-transactions |  |
| [**deleteRecurringTransaction**](RecurringTransactionControllerApi.md#deleterecurringtransaction) | **DELETE** /api/recurring-transactions/{id} |  |
| [**getRecurringTransactionById**](RecurringTransactionControllerApi.md#getrecurringtransactionbyid) | **GET** /api/recurring-transactions/{id} |  |
| [**getRecurringTransactionsByUserId**](RecurringTransactionControllerApi.md#getrecurringtransactionsbyuserid) | **GET** /api/recurring-transactions/user/{userId} |  |
| [**updateRecurringTransaction**](RecurringTransactionControllerApi.md#updaterecurringtransaction) | **PUT** /api/recurring-transactions/{id} |  |



## createRecurringTransaction

> object createRecurringTransaction(recurringTransactionRequestDTO)



### Example

```ts
import {
  Configuration,
  RecurringTransactionControllerApi,
} from '';
import type { CreateRecurringTransactionRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new RecurringTransactionControllerApi();

  const body = {
    // RecurringTransactionRequestDTO
    recurringTransactionRequestDTO: ...,
  } satisfies CreateRecurringTransactionRequest;

  try {
    const data = await api.createRecurringTransaction(body);
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
| **recurringTransactionRequestDTO** | [RecurringTransactionRequestDTO](RecurringTransactionRequestDTO.md) |  | |

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


## deleteRecurringTransaction

> object deleteRecurringTransaction(id)



### Example

```ts
import {
  Configuration,
  RecurringTransactionControllerApi,
} from '';
import type { DeleteRecurringTransactionRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new RecurringTransactionControllerApi();

  const body = {
    // number
    id: 789,
  } satisfies DeleteRecurringTransactionRequest;

  try {
    const data = await api.deleteRecurringTransaction(body);
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


## getRecurringTransactionById

> object getRecurringTransactionById(id)



### Example

```ts
import {
  Configuration,
  RecurringTransactionControllerApi,
} from '';
import type { GetRecurringTransactionByIdRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new RecurringTransactionControllerApi();

  const body = {
    // number
    id: 789,
  } satisfies GetRecurringTransactionByIdRequest;

  try {
    const data = await api.getRecurringTransactionById(body);
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


## getRecurringTransactionsByUserId

> object getRecurringTransactionsByUserId(userId)



### Example

```ts
import {
  Configuration,
  RecurringTransactionControllerApi,
} from '';
import type { GetRecurringTransactionsByUserIdRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new RecurringTransactionControllerApi();

  const body = {
    // number
    userId: 789,
  } satisfies GetRecurringTransactionsByUserIdRequest;

  try {
    const data = await api.getRecurringTransactionsByUserId(body);
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


## updateRecurringTransaction

> object updateRecurringTransaction(id, updateRecurringTransactionRequestDTO)



### Example

```ts
import {
  Configuration,
  RecurringTransactionControllerApi,
} from '';
import type { UpdateRecurringTransactionRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new RecurringTransactionControllerApi();

  const body = {
    // number
    id: 789,
    // UpdateRecurringTransactionRequestDTO
    updateRecurringTransactionRequestDTO: ...,
  } satisfies UpdateRecurringTransactionRequest;

  try {
    const data = await api.updateRecurringTransaction(body);
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
| **updateRecurringTransactionRequestDTO** | [UpdateRecurringTransactionRequestDTO](UpdateRecurringTransactionRequestDTO.md) |  | |

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


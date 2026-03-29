# TransactionControllerApi

All URIs are relative to *http://localhost:8080*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**getAllTransactions**](TransactionControllerApi.md#getalltransactions) | **GET** /api/transactions/user/{userId} |  |
| [**getTransactionsBetweenDates**](TransactionControllerApi.md#gettransactionsbetweendates) | **GET** /api/transactions/user/{userId}/filter |  |
| [**searchTransactions**](TransactionControllerApi.md#searchtransactions) | **GET** /api/transactions/user/{userId}/search |  |



## getAllTransactions

> object getAllTransactions(userId)



### Example

```ts
import {
  Configuration,
  TransactionControllerApi,
} from '';
import type { GetAllTransactionsRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new TransactionControllerApi();

  const body = {
    // number
    userId: 789,
  } satisfies GetAllTransactionsRequest;

  try {
    const data = await api.getAllTransactions(body);
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


## getTransactionsBetweenDates

> object getTransactionsBetweenDates(userId, startDate, endDate)



### Example

```ts
import {
  Configuration,
  TransactionControllerApi,
} from '';
import type { GetTransactionsBetweenDatesRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new TransactionControllerApi();

  const body = {
    // number
    userId: 789,
    // string
    startDate: startDate_example,
    // string
    endDate: endDate_example,
  } satisfies GetTransactionsBetweenDatesRequest;

  try {
    const data = await api.getTransactionsBetweenDates(body);
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
| **startDate** | `string` |  | [Defaults to `undefined`] |
| **endDate** | `string` |  | [Defaults to `undefined`] |

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


## searchTransactions

> object searchTransactions(userId, search)



### Example

```ts
import {
  Configuration,
  TransactionControllerApi,
} from '';
import type { SearchTransactionsRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new TransactionControllerApi();

  const body = {
    // number
    userId: 789,
    // string
    search: search_example,
  } satisfies SearchTransactionsRequest;

  try {
    const data = await api.searchTransactions(body);
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


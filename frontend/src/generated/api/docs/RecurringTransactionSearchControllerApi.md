# RecurringTransactionSearchControllerApi

All URIs are relative to *http://localhost:8080*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**executeSearchRecurringtransactionGet**](RecurringTransactionSearchControllerApi.md#executesearchrecurringtransactionget) | **GET** /recurringTransactions/search/findByIdAndUserId |  |
| [**executeSearchRecurringtransactionGet1**](RecurringTransactionSearchControllerApi.md#executesearchrecurringtransactionget1) | **GET** /recurringTransactions/search/findByNextDueDateLessThanEqualAndIsActiveTrue |  |
| [**executeSearchRecurringtransactionGet2**](RecurringTransactionSearchControllerApi.md#executesearchrecurringtransactionget2) | **GET** /recurringTransactions/search/findByUserId |  |
| [**executeSearchRecurringtransactionGet3**](RecurringTransactionSearchControllerApi.md#executesearchrecurringtransactionget3) | **GET** /recurringTransactions/search/findByUserIdAndIsActiveTrue |  |



## executeSearchRecurringtransactionGet

> EntityModelRecurringTransaction executeSearchRecurringtransactionGet(id, userId)



### Example

```ts
import {
  Configuration,
  RecurringTransactionSearchControllerApi,
} from '';
import type { ExecuteSearchRecurringtransactionGetRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new RecurringTransactionSearchControllerApi();

  const body = {
    // number (optional)
    id: 789,
    // number (optional)
    userId: 789,
  } satisfies ExecuteSearchRecurringtransactionGetRequest;

  try {
    const data = await api.executeSearchRecurringtransactionGet(body);
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
| **id** | `number` |  | [Optional] [Defaults to `undefined`] |
| **userId** | `number` |  | [Optional] [Defaults to `undefined`] |

### Return type

[**EntityModelRecurringTransaction**](EntityModelRecurringTransaction.md)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: `application/hal+json`


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | OK |  -  |
| **404** | Not Found |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#api-endpoints) [[Back to Model list]](../README.md#models) [[Back to README]](../README.md)


## executeSearchRecurringtransactionGet1

> CollectionModelEntityModelRecurringTransaction executeSearchRecurringtransactionGet1(date)



### Example

```ts
import {
  Configuration,
  RecurringTransactionSearchControllerApi,
} from '';
import type { ExecuteSearchRecurringtransactionGet1Request } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new RecurringTransactionSearchControllerApi();

  const body = {
    // Date (optional)
    date: 2013-10-20,
  } satisfies ExecuteSearchRecurringtransactionGet1Request;

  try {
    const data = await api.executeSearchRecurringtransactionGet1(body);
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
| **date** | `Date` |  | [Optional] [Defaults to `undefined`] |

### Return type

[**CollectionModelEntityModelRecurringTransaction**](CollectionModelEntityModelRecurringTransaction.md)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: `application/hal+json`


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | OK |  -  |
| **404** | Not Found |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#api-endpoints) [[Back to Model list]](../README.md#models) [[Back to README]](../README.md)


## executeSearchRecurringtransactionGet2

> CollectionModelEntityModelRecurringTransaction executeSearchRecurringtransactionGet2(userId)



### Example

```ts
import {
  Configuration,
  RecurringTransactionSearchControllerApi,
} from '';
import type { ExecuteSearchRecurringtransactionGet2Request } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new RecurringTransactionSearchControllerApi();

  const body = {
    // number (optional)
    userId: 789,
  } satisfies ExecuteSearchRecurringtransactionGet2Request;

  try {
    const data = await api.executeSearchRecurringtransactionGet2(body);
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
| **userId** | `number` |  | [Optional] [Defaults to `undefined`] |

### Return type

[**CollectionModelEntityModelRecurringTransaction**](CollectionModelEntityModelRecurringTransaction.md)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: `application/hal+json`


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | OK |  -  |
| **404** | Not Found |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#api-endpoints) [[Back to Model list]](../README.md#models) [[Back to README]](../README.md)


## executeSearchRecurringtransactionGet3

> CollectionModelEntityModelRecurringTransaction executeSearchRecurringtransactionGet3(userId)



### Example

```ts
import {
  Configuration,
  RecurringTransactionSearchControllerApi,
} from '';
import type { ExecuteSearchRecurringtransactionGet3Request } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new RecurringTransactionSearchControllerApi();

  const body = {
    // number (optional)
    userId: 789,
  } satisfies ExecuteSearchRecurringtransactionGet3Request;

  try {
    const data = await api.executeSearchRecurringtransactionGet3(body);
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
| **userId** | `number` |  | [Optional] [Defaults to `undefined`] |

### Return type

[**CollectionModelEntityModelRecurringTransaction**](CollectionModelEntityModelRecurringTransaction.md)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: `application/hal+json`


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | OK |  -  |
| **404** | Not Found |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#api-endpoints) [[Back to Model list]](../README.md#models) [[Back to README]](../README.md)


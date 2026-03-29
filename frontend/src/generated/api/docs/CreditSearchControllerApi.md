# CreditSearchControllerApi

All URIs are relative to *http://localhost:8080*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**executeSearchCreditGet**](CreditSearchControllerApi.md#executesearchcreditget) | **GET** /credits/search/findByIdAndUserId |  |
| [**executeSearchCreditGet1**](CreditSearchControllerApi.md#executesearchcreditget1) | **GET** /credits/search/findByUserId |  |
| [**executeSearchCreditGet2**](CreditSearchControllerApi.md#executesearchcreditget2) | **GET** /credits/search/findByUserIdAndDueDateBetween |  |



## executeSearchCreditGet

> EntityModelCredit executeSearchCreditGet(id, userId)



### Example

```ts
import {
  Configuration,
  CreditSearchControllerApi,
} from '';
import type { ExecuteSearchCreditGetRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new CreditSearchControllerApi();

  const body = {
    // number (optional)
    id: 789,
    // number (optional)
    userId: 789,
  } satisfies ExecuteSearchCreditGetRequest;

  try {
    const data = await api.executeSearchCreditGet(body);
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

[**EntityModelCredit**](EntityModelCredit.md)

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


## executeSearchCreditGet1

> CollectionModelEntityModelCredit executeSearchCreditGet1(userId)



### Example

```ts
import {
  Configuration,
  CreditSearchControllerApi,
} from '';
import type { ExecuteSearchCreditGet1Request } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new CreditSearchControllerApi();

  const body = {
    // number (optional)
    userId: 789,
  } satisfies ExecuteSearchCreditGet1Request;

  try {
    const data = await api.executeSearchCreditGet1(body);
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

[**CollectionModelEntityModelCredit**](CollectionModelEntityModelCredit.md)

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


## executeSearchCreditGet2

> CollectionModelEntityModelCredit executeSearchCreditGet2(userId, startDate, endDate)



### Example

```ts
import {
  Configuration,
  CreditSearchControllerApi,
} from '';
import type { ExecuteSearchCreditGet2Request } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new CreditSearchControllerApi();

  const body = {
    // number (optional)
    userId: 789,
    // Date (optional)
    startDate: 2013-10-20T19:20:30+01:00,
    // Date (optional)
    endDate: 2013-10-20T19:20:30+01:00,
  } satisfies ExecuteSearchCreditGet2Request;

  try {
    const data = await api.executeSearchCreditGet2(body);
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
| **startDate** | `Date` |  | [Optional] [Defaults to `undefined`] |
| **endDate** | `Date` |  | [Optional] [Defaults to `undefined`] |

### Return type

[**CollectionModelEntityModelCredit**](CollectionModelEntityModelCredit.md)

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


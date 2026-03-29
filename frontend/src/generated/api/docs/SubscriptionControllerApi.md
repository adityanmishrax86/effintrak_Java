# SubscriptionControllerApi

All URIs are relative to *http://localhost:8080*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**createSubscription**](SubscriptionControllerApi.md#createsubscription) | **POST** /api/subscriptions |  |
| [**deleteSubscription**](SubscriptionControllerApi.md#deletesubscription) | **DELETE** /api/subscriptions/{id} |  |
| [**getSubscriptionById**](SubscriptionControllerApi.md#getsubscriptionbyid) | **GET** /api/subscriptions/{id} |  |
| [**getSubscriptionsByUserId**](SubscriptionControllerApi.md#getsubscriptionsbyuserid) | **GET** /api/subscriptions/user/{userId} |  |
| [**getUpcomingRenewals**](SubscriptionControllerApi.md#getupcomingrenewals) | **GET** /api/subscriptions/user/{userId}/upcoming-renewals |  |
| [**updateSubscription**](SubscriptionControllerApi.md#updatesubscription) | **PUT** /api/subscriptions/{id} |  |



## createSubscription

> object createSubscription(subscriptionRequestDTO)



### Example

```ts
import {
  Configuration,
  SubscriptionControllerApi,
} from '';
import type { CreateSubscriptionRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new SubscriptionControllerApi();

  const body = {
    // SubscriptionRequestDTO
    subscriptionRequestDTO: ...,
  } satisfies CreateSubscriptionRequest;

  try {
    const data = await api.createSubscription(body);
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
| **subscriptionRequestDTO** | [SubscriptionRequestDTO](SubscriptionRequestDTO.md) |  | |

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


## deleteSubscription

> object deleteSubscription(id)



### Example

```ts
import {
  Configuration,
  SubscriptionControllerApi,
} from '';
import type { DeleteSubscriptionRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new SubscriptionControllerApi();

  const body = {
    // number
    id: 789,
  } satisfies DeleteSubscriptionRequest;

  try {
    const data = await api.deleteSubscription(body);
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


## getSubscriptionById

> object getSubscriptionById(id)



### Example

```ts
import {
  Configuration,
  SubscriptionControllerApi,
} from '';
import type { GetSubscriptionByIdRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new SubscriptionControllerApi();

  const body = {
    // number
    id: 789,
  } satisfies GetSubscriptionByIdRequest;

  try {
    const data = await api.getSubscriptionById(body);
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


## getSubscriptionsByUserId

> object getSubscriptionsByUserId(userId)



### Example

```ts
import {
  Configuration,
  SubscriptionControllerApi,
} from '';
import type { GetSubscriptionsByUserIdRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new SubscriptionControllerApi();

  const body = {
    // number
    userId: 789,
  } satisfies GetSubscriptionsByUserIdRequest;

  try {
    const data = await api.getSubscriptionsByUserId(body);
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


## getUpcomingRenewals

> object getUpcomingRenewals(userId, days)



### Example

```ts
import {
  Configuration,
  SubscriptionControllerApi,
} from '';
import type { GetUpcomingRenewalsRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new SubscriptionControllerApi();

  const body = {
    // number
    userId: 789,
    // number (optional)
    days: 56,
  } satisfies GetUpcomingRenewalsRequest;

  try {
    const data = await api.getUpcomingRenewals(body);
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
| **days** | `number` |  | [Optional] [Defaults to `30`] |

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


## updateSubscription

> object updateSubscription(id, updateSubscriptionRequestDTO)



### Example

```ts
import {
  Configuration,
  SubscriptionControllerApi,
} from '';
import type { UpdateSubscriptionRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new SubscriptionControllerApi();

  const body = {
    // number
    id: 789,
    // UpdateSubscriptionRequestDTO
    updateSubscriptionRequestDTO: ...,
  } satisfies UpdateSubscriptionRequest;

  try {
    const data = await api.updateSubscription(body);
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
| **updateSubscriptionRequestDTO** | [UpdateSubscriptionRequestDTO](UpdateSubscriptionRequestDTO.md) |  | |

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


# NotificationSearchControllerApi

All URIs are relative to *http://localhost:8080*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**executeSearchNotificationGet**](NotificationSearchControllerApi.md#executesearchnotificationget) | **GET** /notifications/search/countByUserIdAndIsReadFalse |  |
| [**executeSearchNotificationGet1**](NotificationSearchControllerApi.md#executesearchnotificationget1) | **GET** /notifications/search/findByIdAndUserId |  |
| [**executeSearchNotificationGet2**](NotificationSearchControllerApi.md#executesearchnotificationget2) | **GET** /notifications/search/findByUserIdAndIsReadFalseOrderByCreatedAtDesc |  |
| [**executeSearchNotificationGet3**](NotificationSearchControllerApi.md#executesearchnotificationget3) | **GET** /notifications/search/findByUserIdOrderByCreatedAtDesc |  |



## executeSearchNotificationGet

> number executeSearchNotificationGet(userId)



### Example

```ts
import {
  Configuration,
  NotificationSearchControllerApi,
} from '';
import type { ExecuteSearchNotificationGetRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new NotificationSearchControllerApi();

  const body = {
    // number (optional)
    userId: 789,
  } satisfies ExecuteSearchNotificationGetRequest;

  try {
    const data = await api.executeSearchNotificationGet(body);
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

**number**

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


## executeSearchNotificationGet1

> EntityModelNotification executeSearchNotificationGet1(id, userId)



### Example

```ts
import {
  Configuration,
  NotificationSearchControllerApi,
} from '';
import type { ExecuteSearchNotificationGet1Request } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new NotificationSearchControllerApi();

  const body = {
    // number (optional)
    id: 789,
    // number (optional)
    userId: 789,
  } satisfies ExecuteSearchNotificationGet1Request;

  try {
    const data = await api.executeSearchNotificationGet1(body);
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

[**EntityModelNotification**](EntityModelNotification.md)

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


## executeSearchNotificationGet2

> CollectionModelEntityModelNotification executeSearchNotificationGet2(userId)



### Example

```ts
import {
  Configuration,
  NotificationSearchControllerApi,
} from '';
import type { ExecuteSearchNotificationGet2Request } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new NotificationSearchControllerApi();

  const body = {
    // number (optional)
    userId: 789,
  } satisfies ExecuteSearchNotificationGet2Request;

  try {
    const data = await api.executeSearchNotificationGet2(body);
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

[**CollectionModelEntityModelNotification**](CollectionModelEntityModelNotification.md)

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


## executeSearchNotificationGet3

> CollectionModelEntityModelNotification executeSearchNotificationGet3(userId)



### Example

```ts
import {
  Configuration,
  NotificationSearchControllerApi,
} from '';
import type { ExecuteSearchNotificationGet3Request } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new NotificationSearchControllerApi();

  const body = {
    // number (optional)
    userId: 789,
  } satisfies ExecuteSearchNotificationGet3Request;

  try {
    const data = await api.executeSearchNotificationGet3(body);
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

[**CollectionModelEntityModelNotification**](CollectionModelEntityModelNotification.md)

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


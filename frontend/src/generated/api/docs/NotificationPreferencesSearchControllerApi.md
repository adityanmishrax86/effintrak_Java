# NotificationPreferencesSearchControllerApi

All URIs are relative to *http://localhost:8080*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**executeSearchNotificationpreferencesGet**](NotificationPreferencesSearchControllerApi.md#executesearchnotificationpreferencesget) | **GET** /notificationPreferenceses/search/findByUser |  |
| [**executeSearchNotificationpreferencesGet1**](NotificationPreferencesSearchControllerApi.md#executesearchnotificationpreferencesget1) | **GET** /notificationPreferenceses/search/findByUserId |  |



## executeSearchNotificationpreferencesGet

> EntityModelNotificationPreferences executeSearchNotificationpreferencesGet(user)



### Example

```ts
import {
  Configuration,
  NotificationPreferencesSearchControllerApi,
} from '';
import type { ExecuteSearchNotificationpreferencesGetRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new NotificationPreferencesSearchControllerApi();

  const body = {
    // User (optional)
    user: ...,
  } satisfies ExecuteSearchNotificationpreferencesGetRequest;

  try {
    const data = await api.executeSearchNotificationpreferencesGet(body);
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
| **user** | [](.md) |  | [Optional] [Defaults to `undefined`] |

### Return type

[**EntityModelNotificationPreferences**](EntityModelNotificationPreferences.md)

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


## executeSearchNotificationpreferencesGet1

> EntityModelNotificationPreferences executeSearchNotificationpreferencesGet1(userId)



### Example

```ts
import {
  Configuration,
  NotificationPreferencesSearchControllerApi,
} from '';
import type { ExecuteSearchNotificationpreferencesGet1Request } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new NotificationPreferencesSearchControllerApi();

  const body = {
    // number (optional)
    userId: 789,
  } satisfies ExecuteSearchNotificationpreferencesGet1Request;

  try {
    const data = await api.executeSearchNotificationpreferencesGet1(body);
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

[**EntityModelNotificationPreferences**](EntityModelNotificationPreferences.md)

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


# NotificationControllerApi

All URIs are relative to *http://localhost:8080*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**checkBudget**](NotificationControllerApi.md#checkbudget) | **POST** /api/notifications/check-budget/{userId} |  |
| [**deleteNotification**](NotificationControllerApi.md#deletenotification) | **DELETE** /api/notifications/{id} |  |
| [**getNotifications**](NotificationControllerApi.md#getnotifications) | **GET** /api/notifications/user/{userId} |  |
| [**getPreferences**](NotificationControllerApi.md#getpreferences) | **GET** /api/notifications/user/{userId}/preferences |  |
| [**getUnreadCount**](NotificationControllerApi.md#getunreadcount) | **GET** /api/notifications/user/{userId}/unread-count |  |
| [**getUnreadNotifications**](NotificationControllerApi.md#getunreadnotifications) | **GET** /api/notifications/user/{userId}/unread |  |
| [**markAsRead**](NotificationControllerApi.md#markasread) | **PUT** /api/notifications/{id}/read |  |
| [**updatePreferences**](NotificationControllerApi.md#updatepreferences) | **PUT** /api/notifications/user/{userId}/preferences |  |



## checkBudget

> object checkBudget(userId)



### Example

```ts
import {
  Configuration,
  NotificationControllerApi,
} from '';
import type { CheckBudgetRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new NotificationControllerApi();

  const body = {
    // number
    userId: 789,
  } satisfies CheckBudgetRequest;

  try {
    const data = await api.checkBudget(body);
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


## deleteNotification

> object deleteNotification(id)



### Example

```ts
import {
  Configuration,
  NotificationControllerApi,
} from '';
import type { DeleteNotificationRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new NotificationControllerApi();

  const body = {
    // number
    id: 789,
  } satisfies DeleteNotificationRequest;

  try {
    const data = await api.deleteNotification(body);
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


## getNotifications

> object getNotifications(userId)



### Example

```ts
import {
  Configuration,
  NotificationControllerApi,
} from '';
import type { GetNotificationsRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new NotificationControllerApi();

  const body = {
    // number
    userId: 789,
  } satisfies GetNotificationsRequest;

  try {
    const data = await api.getNotifications(body);
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


## getPreferences

> object getPreferences(userId)



### Example

```ts
import {
  Configuration,
  NotificationControllerApi,
} from '';
import type { GetPreferencesRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new NotificationControllerApi();

  const body = {
    // number
    userId: 789,
  } satisfies GetPreferencesRequest;

  try {
    const data = await api.getPreferences(body);
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


## getUnreadCount

> object getUnreadCount(userId)



### Example

```ts
import {
  Configuration,
  NotificationControllerApi,
} from '';
import type { GetUnreadCountRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new NotificationControllerApi();

  const body = {
    // number
    userId: 789,
  } satisfies GetUnreadCountRequest;

  try {
    const data = await api.getUnreadCount(body);
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


## getUnreadNotifications

> object getUnreadNotifications(userId)



### Example

```ts
import {
  Configuration,
  NotificationControllerApi,
} from '';
import type { GetUnreadNotificationsRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new NotificationControllerApi();

  const body = {
    // number
    userId: 789,
  } satisfies GetUnreadNotificationsRequest;

  try {
    const data = await api.getUnreadNotifications(body);
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


## markAsRead

> object markAsRead(id)



### Example

```ts
import {
  Configuration,
  NotificationControllerApi,
} from '';
import type { MarkAsReadRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new NotificationControllerApi();

  const body = {
    // number
    id: 789,
  } satisfies MarkAsReadRequest;

  try {
    const data = await api.markAsRead(body);
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


## updatePreferences

> object updatePreferences(userId, notificationPreferencesDTO)



### Example

```ts
import {
  Configuration,
  NotificationControllerApi,
} from '';
import type { UpdatePreferencesRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new NotificationControllerApi();

  const body = {
    // number
    userId: 789,
    // NotificationPreferencesDTO
    notificationPreferencesDTO: ...,
  } satisfies UpdatePreferencesRequest;

  try {
    const data = await api.updatePreferences(body);
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
| **notificationPreferencesDTO** | [NotificationPreferencesDTO](NotificationPreferencesDTO.md) |  | |

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


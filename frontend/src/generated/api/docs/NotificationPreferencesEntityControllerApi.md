# NotificationPreferencesEntityControllerApi

All URIs are relative to *http://localhost:8080*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**deleteItemResourceNotificationpreferencesDelete**](NotificationPreferencesEntityControllerApi.md#deleteitemresourcenotificationpreferencesdelete) | **DELETE** /notificationPreferenceses/{id} |  |
| [**getCollectionResourceNotificationpreferencesGet1**](NotificationPreferencesEntityControllerApi.md#getcollectionresourcenotificationpreferencesget1) | **GET** /notificationPreferenceses |  |
| [**getItemResourceNotificationpreferencesGet**](NotificationPreferencesEntityControllerApi.md#getitemresourcenotificationpreferencesget) | **GET** /notificationPreferenceses/{id} |  |
| [**patchItemResourceNotificationpreferencesPatch**](NotificationPreferencesEntityControllerApi.md#patchitemresourcenotificationpreferencespatch) | **PATCH** /notificationPreferenceses/{id} |  |
| [**postCollectionResourceNotificationpreferencesPost**](NotificationPreferencesEntityControllerApi.md#postcollectionresourcenotificationpreferencespost) | **POST** /notificationPreferenceses |  |
| [**putItemResourceNotificationpreferencesPut**](NotificationPreferencesEntityControllerApi.md#putitemresourcenotificationpreferencesput) | **PUT** /notificationPreferenceses/{id} |  |



## deleteItemResourceNotificationpreferencesDelete

> deleteItemResourceNotificationpreferencesDelete(id)



delete-notificationpreferences

### Example

```ts
import {
  Configuration,
  NotificationPreferencesEntityControllerApi,
} from '';
import type { DeleteItemResourceNotificationpreferencesDeleteRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new NotificationPreferencesEntityControllerApi();

  const body = {
    // string
    id: id_example,
  } satisfies DeleteItemResourceNotificationpreferencesDeleteRequest;

  try {
    const data = await api.deleteItemResourceNotificationpreferencesDelete(body);
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
| **id** | `string` |  | [Defaults to `undefined`] |

### Return type

`void` (Empty response body)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: Not defined


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **204** | No Content |  -  |
| **404** | Not Found |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#api-endpoints) [[Back to Model list]](../README.md#models) [[Back to README]](../README.md)


## getCollectionResourceNotificationpreferencesGet1

> PagedModelEntityModelNotificationPreferences getCollectionResourceNotificationpreferencesGet1(page, size, sort)



get-notificationpreferences

### Example

```ts
import {
  Configuration,
  NotificationPreferencesEntityControllerApi,
} from '';
import type { GetCollectionResourceNotificationpreferencesGet1Request } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new NotificationPreferencesEntityControllerApi();

  const body = {
    // number | Zero-based page index (0..N) (optional)
    page: 56,
    // number | The size of the page to be returned (optional)
    size: 56,
    // Array<string> | Sorting criteria in the format: property,(asc|desc). Default sort order is ascending. Multiple sort criteria are supported. (optional)
    sort: ...,
  } satisfies GetCollectionResourceNotificationpreferencesGet1Request;

  try {
    const data = await api.getCollectionResourceNotificationpreferencesGet1(body);
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
| **page** | `number` | Zero-based page index (0..N) | [Optional] [Defaults to `0`] |
| **size** | `number` | The size of the page to be returned | [Optional] [Defaults to `20`] |
| **sort** | `Array<string>` | Sorting criteria in the format: property,(asc|desc). Default sort order is ascending. Multiple sort criteria are supported. | [Optional] |

### Return type

[**PagedModelEntityModelNotificationPreferences**](PagedModelEntityModelNotificationPreferences.md)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: `application/hal+json`, `application/x-spring-data-compact+json`, `text/uri-list`


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | OK |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#api-endpoints) [[Back to Model list]](../README.md#models) [[Back to README]](../README.md)


## getItemResourceNotificationpreferencesGet

> EntityModelNotificationPreferences getItemResourceNotificationpreferencesGet(id)



get-notificationpreferences

### Example

```ts
import {
  Configuration,
  NotificationPreferencesEntityControllerApi,
} from '';
import type { GetItemResourceNotificationpreferencesGetRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new NotificationPreferencesEntityControllerApi();

  const body = {
    // string
    id: id_example,
  } satisfies GetItemResourceNotificationpreferencesGetRequest;

  try {
    const data = await api.getItemResourceNotificationpreferencesGet(body);
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
| **id** | `string` |  | [Defaults to `undefined`] |

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


## patchItemResourceNotificationpreferencesPatch

> EntityModelNotificationPreferences patchItemResourceNotificationpreferencesPatch(id, notificationPreferencesRequestBody)



patch-notificationpreferences

### Example

```ts
import {
  Configuration,
  NotificationPreferencesEntityControllerApi,
} from '';
import type { PatchItemResourceNotificationpreferencesPatchRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new NotificationPreferencesEntityControllerApi();

  const body = {
    // string
    id: id_example,
    // NotificationPreferencesRequestBody
    notificationPreferencesRequestBody: ...,
  } satisfies PatchItemResourceNotificationpreferencesPatchRequest;

  try {
    const data = await api.patchItemResourceNotificationpreferencesPatch(body);
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
| **id** | `string` |  | [Defaults to `undefined`] |
| **notificationPreferencesRequestBody** | [NotificationPreferencesRequestBody](NotificationPreferencesRequestBody.md) |  | |

### Return type

[**EntityModelNotificationPreferences**](EntityModelNotificationPreferences.md)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: `application/json`
- **Accept**: `application/hal+json`


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | OK |  -  |
| **204** | No Content |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#api-endpoints) [[Back to Model list]](../README.md#models) [[Back to README]](../README.md)


## postCollectionResourceNotificationpreferencesPost

> EntityModelNotificationPreferences postCollectionResourceNotificationpreferencesPost(notificationPreferencesRequestBody)



create-notificationpreferences

### Example

```ts
import {
  Configuration,
  NotificationPreferencesEntityControllerApi,
} from '';
import type { PostCollectionResourceNotificationpreferencesPostRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new NotificationPreferencesEntityControllerApi();

  const body = {
    // NotificationPreferencesRequestBody
    notificationPreferencesRequestBody: ...,
  } satisfies PostCollectionResourceNotificationpreferencesPostRequest;

  try {
    const data = await api.postCollectionResourceNotificationpreferencesPost(body);
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
| **notificationPreferencesRequestBody** | [NotificationPreferencesRequestBody](NotificationPreferencesRequestBody.md) |  | |

### Return type

[**EntityModelNotificationPreferences**](EntityModelNotificationPreferences.md)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: `application/json`
- **Accept**: `application/hal+json`


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **201** | Created |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#api-endpoints) [[Back to Model list]](../README.md#models) [[Back to README]](../README.md)


## putItemResourceNotificationpreferencesPut

> EntityModelNotificationPreferences putItemResourceNotificationpreferencesPut(id, notificationPreferencesRequestBody)



update-notificationpreferences

### Example

```ts
import {
  Configuration,
  NotificationPreferencesEntityControllerApi,
} from '';
import type { PutItemResourceNotificationpreferencesPutRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new NotificationPreferencesEntityControllerApi();

  const body = {
    // string
    id: id_example,
    // NotificationPreferencesRequestBody
    notificationPreferencesRequestBody: ...,
  } satisfies PutItemResourceNotificationpreferencesPutRequest;

  try {
    const data = await api.putItemResourceNotificationpreferencesPut(body);
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
| **id** | `string` |  | [Defaults to `undefined`] |
| **notificationPreferencesRequestBody** | [NotificationPreferencesRequestBody](NotificationPreferencesRequestBody.md) |  | |

### Return type

[**EntityModelNotificationPreferences**](EntityModelNotificationPreferences.md)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: `application/json`
- **Accept**: `application/hal+json`


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | OK |  -  |
| **201** | Created |  -  |
| **204** | No Content |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#api-endpoints) [[Back to Model list]](../README.md#models) [[Back to README]](../README.md)


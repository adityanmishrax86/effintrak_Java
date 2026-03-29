# NotificationEntityControllerApi

All URIs are relative to *http://localhost:8080*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**deleteItemResourceNotificationDelete**](NotificationEntityControllerApi.md#deleteitemresourcenotificationdelete) | **DELETE** /notifications/{id} |  |
| [**getCollectionResourceNotificationGet1**](NotificationEntityControllerApi.md#getcollectionresourcenotificationget1) | **GET** /notifications |  |
| [**getItemResourceNotificationGet**](NotificationEntityControllerApi.md#getitemresourcenotificationget) | **GET** /notifications/{id} |  |
| [**patchItemResourceNotificationPatch**](NotificationEntityControllerApi.md#patchitemresourcenotificationpatch) | **PATCH** /notifications/{id} |  |
| [**postCollectionResourceNotificationPost**](NotificationEntityControllerApi.md#postcollectionresourcenotificationpost) | **POST** /notifications |  |
| [**putItemResourceNotificationPut**](NotificationEntityControllerApi.md#putitemresourcenotificationput) | **PUT** /notifications/{id} |  |



## deleteItemResourceNotificationDelete

> deleteItemResourceNotificationDelete(id)



delete-notification

### Example

```ts
import {
  Configuration,
  NotificationEntityControllerApi,
} from '';
import type { DeleteItemResourceNotificationDeleteRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new NotificationEntityControllerApi();

  const body = {
    // string
    id: id_example,
  } satisfies DeleteItemResourceNotificationDeleteRequest;

  try {
    const data = await api.deleteItemResourceNotificationDelete(body);
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


## getCollectionResourceNotificationGet1

> PagedModelEntityModelNotification getCollectionResourceNotificationGet1(page, size, sort)



get-notification

### Example

```ts
import {
  Configuration,
  NotificationEntityControllerApi,
} from '';
import type { GetCollectionResourceNotificationGet1Request } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new NotificationEntityControllerApi();

  const body = {
    // number | Zero-based page index (0..N) (optional)
    page: 56,
    // number | The size of the page to be returned (optional)
    size: 56,
    // Array<string> | Sorting criteria in the format: property,(asc|desc). Default sort order is ascending. Multiple sort criteria are supported. (optional)
    sort: ...,
  } satisfies GetCollectionResourceNotificationGet1Request;

  try {
    const data = await api.getCollectionResourceNotificationGet1(body);
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

[**PagedModelEntityModelNotification**](PagedModelEntityModelNotification.md)

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


## getItemResourceNotificationGet

> EntityModelNotification getItemResourceNotificationGet(id)



get-notification

### Example

```ts
import {
  Configuration,
  NotificationEntityControllerApi,
} from '';
import type { GetItemResourceNotificationGetRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new NotificationEntityControllerApi();

  const body = {
    // string
    id: id_example,
  } satisfies GetItemResourceNotificationGetRequest;

  try {
    const data = await api.getItemResourceNotificationGet(body);
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


## patchItemResourceNotificationPatch

> EntityModelNotification patchItemResourceNotificationPatch(id, notificationRequestBody)



patch-notification

### Example

```ts
import {
  Configuration,
  NotificationEntityControllerApi,
} from '';
import type { PatchItemResourceNotificationPatchRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new NotificationEntityControllerApi();

  const body = {
    // string
    id: id_example,
    // NotificationRequestBody
    notificationRequestBody: ...,
  } satisfies PatchItemResourceNotificationPatchRequest;

  try {
    const data = await api.patchItemResourceNotificationPatch(body);
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
| **notificationRequestBody** | [NotificationRequestBody](NotificationRequestBody.md) |  | |

### Return type

[**EntityModelNotification**](EntityModelNotification.md)

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


## postCollectionResourceNotificationPost

> EntityModelNotification postCollectionResourceNotificationPost(notificationRequestBody)



create-notification

### Example

```ts
import {
  Configuration,
  NotificationEntityControllerApi,
} from '';
import type { PostCollectionResourceNotificationPostRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new NotificationEntityControllerApi();

  const body = {
    // NotificationRequestBody
    notificationRequestBody: ...,
  } satisfies PostCollectionResourceNotificationPostRequest;

  try {
    const data = await api.postCollectionResourceNotificationPost(body);
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
| **notificationRequestBody** | [NotificationRequestBody](NotificationRequestBody.md) |  | |

### Return type

[**EntityModelNotification**](EntityModelNotification.md)

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


## putItemResourceNotificationPut

> EntityModelNotification putItemResourceNotificationPut(id, notificationRequestBody)



update-notification

### Example

```ts
import {
  Configuration,
  NotificationEntityControllerApi,
} from '';
import type { PutItemResourceNotificationPutRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new NotificationEntityControllerApi();

  const body = {
    // string
    id: id_example,
    // NotificationRequestBody
    notificationRequestBody: ...,
  } satisfies PutItemResourceNotificationPutRequest;

  try {
    const data = await api.putItemResourceNotificationPut(body);
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
| **notificationRequestBody** | [NotificationRequestBody](NotificationRequestBody.md) |  | |

### Return type

[**EntityModelNotification**](EntityModelNotification.md)

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


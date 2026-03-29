# NotificationPropertyReferenceControllerApi

All URIs are relative to *http://localhost:8080*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**createPropertyReferenceNotificationPatch**](NotificationPropertyReferenceControllerApi.md#createpropertyreferencenotificationpatch) | **PATCH** /notifications/{id}/user |  |
| [**createPropertyReferenceNotificationPut**](NotificationPropertyReferenceControllerApi.md#createpropertyreferencenotificationput) | **PUT** /notifications/{id}/user |  |
| [**deletePropertyReferenceIdNotificationDelete**](NotificationPropertyReferenceControllerApi.md#deletepropertyreferenceidnotificationdelete) | **DELETE** /notifications/{id}/user/{propertyId} |  |
| [**deletePropertyReferenceNotificationDelete**](NotificationPropertyReferenceControllerApi.md#deletepropertyreferencenotificationdelete) | **DELETE** /notifications/{id}/user |  |
| [**followPropertyReferenceNotificationGet**](NotificationPropertyReferenceControllerApi.md#followpropertyreferencenotificationget) | **GET** /notifications/{id}/user/{propertyId} |  |
| [**followPropertyReferenceNotificationGet1**](NotificationPropertyReferenceControllerApi.md#followpropertyreferencenotificationget1) | **GET** /notifications/{id}/user |  |



## createPropertyReferenceNotificationPatch

> EntityModelUser createPropertyReferenceNotificationPatch(id, collectionModelObject)



patch-user-by-notification-Id

### Example

```ts
import {
  Configuration,
  NotificationPropertyReferenceControllerApi,
} from '';
import type { CreatePropertyReferenceNotificationPatchRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new NotificationPropertyReferenceControllerApi();

  const body = {
    // string
    id: id_example,
    // CollectionModelObject
    collectionModelObject: ...,
  } satisfies CreatePropertyReferenceNotificationPatchRequest;

  try {
    const data = await api.createPropertyReferenceNotificationPatch(body);
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
| **collectionModelObject** | [CollectionModelObject](CollectionModelObject.md) |  | |

### Return type

[**EntityModelUser**](EntityModelUser.md)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: `application/json`, `application/x-spring-data-compact+json`, `text/uri-list`
- **Accept**: `application/hal+json`


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | OK |  -  |
| **204** | No Content |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#api-endpoints) [[Back to Model list]](../README.md#models) [[Back to README]](../README.md)


## createPropertyReferenceNotificationPut

> EntityModelUser createPropertyReferenceNotificationPut(id, collectionModelObject)



update-user-by-notification-Id

### Example

```ts
import {
  Configuration,
  NotificationPropertyReferenceControllerApi,
} from '';
import type { CreatePropertyReferenceNotificationPutRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new NotificationPropertyReferenceControllerApi();

  const body = {
    // string
    id: id_example,
    // CollectionModelObject
    collectionModelObject: ...,
  } satisfies CreatePropertyReferenceNotificationPutRequest;

  try {
    const data = await api.createPropertyReferenceNotificationPut(body);
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
| **collectionModelObject** | [CollectionModelObject](CollectionModelObject.md) |  | |

### Return type

[**EntityModelUser**](EntityModelUser.md)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: `application/json`, `application/x-spring-data-compact+json`, `text/uri-list`
- **Accept**: `application/hal+json`


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | OK |  -  |
| **201** | Created |  -  |
| **204** | No Content |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#api-endpoints) [[Back to Model list]](../README.md#models) [[Back to README]](../README.md)


## deletePropertyReferenceIdNotificationDelete

> deletePropertyReferenceIdNotificationDelete(id, propertyId)



delete-user-by-notification-Id

### Example

```ts
import {
  Configuration,
  NotificationPropertyReferenceControllerApi,
} from '';
import type { DeletePropertyReferenceIdNotificationDeleteRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new NotificationPropertyReferenceControllerApi();

  const body = {
    // string
    id: id_example,
    // string
    propertyId: propertyId_example,
  } satisfies DeletePropertyReferenceIdNotificationDeleteRequest;

  try {
    const data = await api.deletePropertyReferenceIdNotificationDelete(body);
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
| **propertyId** | `string` |  | [Defaults to `undefined`] |

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


## deletePropertyReferenceNotificationDelete

> deletePropertyReferenceNotificationDelete(id)



delete-user-by-notification-Id

### Example

```ts
import {
  Configuration,
  NotificationPropertyReferenceControllerApi,
} from '';
import type { DeletePropertyReferenceNotificationDeleteRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new NotificationPropertyReferenceControllerApi();

  const body = {
    // string
    id: id_example,
  } satisfies DeletePropertyReferenceNotificationDeleteRequest;

  try {
    const data = await api.deletePropertyReferenceNotificationDelete(body);
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


## followPropertyReferenceNotificationGet

> EntityModelUser followPropertyReferenceNotificationGet(id, propertyId)



get-user-by-notification-Id

### Example

```ts
import {
  Configuration,
  NotificationPropertyReferenceControllerApi,
} from '';
import type { FollowPropertyReferenceNotificationGetRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new NotificationPropertyReferenceControllerApi();

  const body = {
    // string
    id: id_example,
    // string
    propertyId: propertyId_example,
  } satisfies FollowPropertyReferenceNotificationGetRequest;

  try {
    const data = await api.followPropertyReferenceNotificationGet(body);
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
| **propertyId** | `string` |  | [Defaults to `undefined`] |

### Return type

[**EntityModelUser**](EntityModelUser.md)

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


## followPropertyReferenceNotificationGet1

> EntityModelUser followPropertyReferenceNotificationGet1(id)



get-user-by-notification-Id

### Example

```ts
import {
  Configuration,
  NotificationPropertyReferenceControllerApi,
} from '';
import type { FollowPropertyReferenceNotificationGet1Request } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new NotificationPropertyReferenceControllerApi();

  const body = {
    // string
    id: id_example,
  } satisfies FollowPropertyReferenceNotificationGet1Request;

  try {
    const data = await api.followPropertyReferenceNotificationGet1(body);
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

[**EntityModelUser**](EntityModelUser.md)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: `application/hal+json`, `text/uri-list`


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | OK |  -  |
| **404** | Not Found |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#api-endpoints) [[Back to Model list]](../README.md#models) [[Back to README]](../README.md)


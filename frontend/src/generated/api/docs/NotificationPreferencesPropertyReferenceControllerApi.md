# NotificationPreferencesPropertyReferenceControllerApi

All URIs are relative to *http://localhost:8080*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**createPropertyReferenceNotificationpreferencesPatch**](NotificationPreferencesPropertyReferenceControllerApi.md#createpropertyreferencenotificationpreferencespatch) | **PATCH** /notificationPreferenceses/{id}/user |  |
| [**createPropertyReferenceNotificationpreferencesPut**](NotificationPreferencesPropertyReferenceControllerApi.md#createpropertyreferencenotificationpreferencesput) | **PUT** /notificationPreferenceses/{id}/user |  |
| [**deletePropertyReferenceIdNotificationpreferencesDelete**](NotificationPreferencesPropertyReferenceControllerApi.md#deletepropertyreferenceidnotificationpreferencesdelete) | **DELETE** /notificationPreferenceses/{id}/user/{propertyId} |  |
| [**deletePropertyReferenceNotificationpreferencesDelete**](NotificationPreferencesPropertyReferenceControllerApi.md#deletepropertyreferencenotificationpreferencesdelete) | **DELETE** /notificationPreferenceses/{id}/user |  |
| [**followPropertyReferenceNotificationpreferencesGet**](NotificationPreferencesPropertyReferenceControllerApi.md#followpropertyreferencenotificationpreferencesget) | **GET** /notificationPreferenceses/{id}/user/{propertyId} |  |
| [**followPropertyReferenceNotificationpreferencesGet1**](NotificationPreferencesPropertyReferenceControllerApi.md#followpropertyreferencenotificationpreferencesget1) | **GET** /notificationPreferenceses/{id}/user |  |



## createPropertyReferenceNotificationpreferencesPatch

> EntityModelUser createPropertyReferenceNotificationpreferencesPatch(id, collectionModelObject)



patch-user-by-notificationpreferences-Id

### Example

```ts
import {
  Configuration,
  NotificationPreferencesPropertyReferenceControllerApi,
} from '';
import type { CreatePropertyReferenceNotificationpreferencesPatchRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new NotificationPreferencesPropertyReferenceControllerApi();

  const body = {
    // string
    id: id_example,
    // CollectionModelObject
    collectionModelObject: ...,
  } satisfies CreatePropertyReferenceNotificationpreferencesPatchRequest;

  try {
    const data = await api.createPropertyReferenceNotificationpreferencesPatch(body);
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


## createPropertyReferenceNotificationpreferencesPut

> EntityModelUser createPropertyReferenceNotificationpreferencesPut(id, collectionModelObject)



update-user-by-notificationpreferences-Id

### Example

```ts
import {
  Configuration,
  NotificationPreferencesPropertyReferenceControllerApi,
} from '';
import type { CreatePropertyReferenceNotificationpreferencesPutRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new NotificationPreferencesPropertyReferenceControllerApi();

  const body = {
    // string
    id: id_example,
    // CollectionModelObject
    collectionModelObject: ...,
  } satisfies CreatePropertyReferenceNotificationpreferencesPutRequest;

  try {
    const data = await api.createPropertyReferenceNotificationpreferencesPut(body);
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


## deletePropertyReferenceIdNotificationpreferencesDelete

> deletePropertyReferenceIdNotificationpreferencesDelete(id, propertyId)



delete-user-by-notificationpreferences-Id

### Example

```ts
import {
  Configuration,
  NotificationPreferencesPropertyReferenceControllerApi,
} from '';
import type { DeletePropertyReferenceIdNotificationpreferencesDeleteRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new NotificationPreferencesPropertyReferenceControllerApi();

  const body = {
    // string
    id: id_example,
    // string
    propertyId: propertyId_example,
  } satisfies DeletePropertyReferenceIdNotificationpreferencesDeleteRequest;

  try {
    const data = await api.deletePropertyReferenceIdNotificationpreferencesDelete(body);
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


## deletePropertyReferenceNotificationpreferencesDelete

> deletePropertyReferenceNotificationpreferencesDelete(id)



delete-user-by-notificationpreferences-Id

### Example

```ts
import {
  Configuration,
  NotificationPreferencesPropertyReferenceControllerApi,
} from '';
import type { DeletePropertyReferenceNotificationpreferencesDeleteRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new NotificationPreferencesPropertyReferenceControllerApi();

  const body = {
    // string
    id: id_example,
  } satisfies DeletePropertyReferenceNotificationpreferencesDeleteRequest;

  try {
    const data = await api.deletePropertyReferenceNotificationpreferencesDelete(body);
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


## followPropertyReferenceNotificationpreferencesGet

> EntityModelUser followPropertyReferenceNotificationpreferencesGet(id, propertyId)



get-user-by-notificationpreferences-Id

### Example

```ts
import {
  Configuration,
  NotificationPreferencesPropertyReferenceControllerApi,
} from '';
import type { FollowPropertyReferenceNotificationpreferencesGetRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new NotificationPreferencesPropertyReferenceControllerApi();

  const body = {
    // string
    id: id_example,
    // string
    propertyId: propertyId_example,
  } satisfies FollowPropertyReferenceNotificationpreferencesGetRequest;

  try {
    const data = await api.followPropertyReferenceNotificationpreferencesGet(body);
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


## followPropertyReferenceNotificationpreferencesGet1

> EntityModelUser followPropertyReferenceNotificationpreferencesGet1(id)



get-user-by-notificationpreferences-Id

### Example

```ts
import {
  Configuration,
  NotificationPreferencesPropertyReferenceControllerApi,
} from '';
import type { FollowPropertyReferenceNotificationpreferencesGet1Request } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new NotificationPreferencesPropertyReferenceControllerApi();

  const body = {
    // string
    id: id_example,
  } satisfies FollowPropertyReferenceNotificationpreferencesGet1Request;

  try {
    const data = await api.followPropertyReferenceNotificationpreferencesGet1(body);
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


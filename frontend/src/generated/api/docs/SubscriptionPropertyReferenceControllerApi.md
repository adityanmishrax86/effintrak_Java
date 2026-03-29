# SubscriptionPropertyReferenceControllerApi

All URIs are relative to *http://localhost:8080*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**createPropertyReferenceSubscriptionPatch**](SubscriptionPropertyReferenceControllerApi.md#createpropertyreferencesubscriptionpatch) | **PATCH** /subscriptions/{id}/user |  |
| [**createPropertyReferenceSubscriptionPut**](SubscriptionPropertyReferenceControllerApi.md#createpropertyreferencesubscriptionput) | **PUT** /subscriptions/{id}/user |  |
| [**deletePropertyReferenceIdSubscriptionDelete**](SubscriptionPropertyReferenceControllerApi.md#deletepropertyreferenceidsubscriptiondelete) | **DELETE** /subscriptions/{id}/user/{propertyId} |  |
| [**deletePropertyReferenceSubscriptionDelete**](SubscriptionPropertyReferenceControllerApi.md#deletepropertyreferencesubscriptiondelete) | **DELETE** /subscriptions/{id}/user |  |
| [**followPropertyReferenceSubscriptionGet**](SubscriptionPropertyReferenceControllerApi.md#followpropertyreferencesubscriptionget) | **GET** /subscriptions/{id}/user/{propertyId} |  |
| [**followPropertyReferenceSubscriptionGet1**](SubscriptionPropertyReferenceControllerApi.md#followpropertyreferencesubscriptionget1) | **GET** /subscriptions/{id}/user |  |



## createPropertyReferenceSubscriptionPatch

> EntityModelUser createPropertyReferenceSubscriptionPatch(id, collectionModelObject)



patch-user-by-subscription-Id

### Example

```ts
import {
  Configuration,
  SubscriptionPropertyReferenceControllerApi,
} from '';
import type { CreatePropertyReferenceSubscriptionPatchRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new SubscriptionPropertyReferenceControllerApi();

  const body = {
    // string
    id: id_example,
    // CollectionModelObject
    collectionModelObject: ...,
  } satisfies CreatePropertyReferenceSubscriptionPatchRequest;

  try {
    const data = await api.createPropertyReferenceSubscriptionPatch(body);
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


## createPropertyReferenceSubscriptionPut

> EntityModelUser createPropertyReferenceSubscriptionPut(id, collectionModelObject)



update-user-by-subscription-Id

### Example

```ts
import {
  Configuration,
  SubscriptionPropertyReferenceControllerApi,
} from '';
import type { CreatePropertyReferenceSubscriptionPutRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new SubscriptionPropertyReferenceControllerApi();

  const body = {
    // string
    id: id_example,
    // CollectionModelObject
    collectionModelObject: ...,
  } satisfies CreatePropertyReferenceSubscriptionPutRequest;

  try {
    const data = await api.createPropertyReferenceSubscriptionPut(body);
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


## deletePropertyReferenceIdSubscriptionDelete

> deletePropertyReferenceIdSubscriptionDelete(id, propertyId)



delete-user-by-subscription-Id

### Example

```ts
import {
  Configuration,
  SubscriptionPropertyReferenceControllerApi,
} from '';
import type { DeletePropertyReferenceIdSubscriptionDeleteRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new SubscriptionPropertyReferenceControllerApi();

  const body = {
    // string
    id: id_example,
    // string
    propertyId: propertyId_example,
  } satisfies DeletePropertyReferenceIdSubscriptionDeleteRequest;

  try {
    const data = await api.deletePropertyReferenceIdSubscriptionDelete(body);
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


## deletePropertyReferenceSubscriptionDelete

> deletePropertyReferenceSubscriptionDelete(id)



delete-user-by-subscription-Id

### Example

```ts
import {
  Configuration,
  SubscriptionPropertyReferenceControllerApi,
} from '';
import type { DeletePropertyReferenceSubscriptionDeleteRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new SubscriptionPropertyReferenceControllerApi();

  const body = {
    // string
    id: id_example,
  } satisfies DeletePropertyReferenceSubscriptionDeleteRequest;

  try {
    const data = await api.deletePropertyReferenceSubscriptionDelete(body);
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


## followPropertyReferenceSubscriptionGet

> EntityModelUser followPropertyReferenceSubscriptionGet(id, propertyId)



get-user-by-subscription-Id

### Example

```ts
import {
  Configuration,
  SubscriptionPropertyReferenceControllerApi,
} from '';
import type { FollowPropertyReferenceSubscriptionGetRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new SubscriptionPropertyReferenceControllerApi();

  const body = {
    // string
    id: id_example,
    // string
    propertyId: propertyId_example,
  } satisfies FollowPropertyReferenceSubscriptionGetRequest;

  try {
    const data = await api.followPropertyReferenceSubscriptionGet(body);
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


## followPropertyReferenceSubscriptionGet1

> EntityModelUser followPropertyReferenceSubscriptionGet1(id)



get-user-by-subscription-Id

### Example

```ts
import {
  Configuration,
  SubscriptionPropertyReferenceControllerApi,
} from '';
import type { FollowPropertyReferenceSubscriptionGet1Request } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new SubscriptionPropertyReferenceControllerApi();

  const body = {
    // string
    id: id_example,
  } satisfies FollowPropertyReferenceSubscriptionGet1Request;

  try {
    const data = await api.followPropertyReferenceSubscriptionGet1(body);
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


# RefreshTokensPropertyReferenceControllerApi

All URIs are relative to *http://localhost:8080*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**createPropertyReferenceRefreshtokensPatch**](RefreshTokensPropertyReferenceControllerApi.md#createpropertyreferencerefreshtokenspatch) | **PATCH** /refreshTokenses/{id}/user |  |
| [**createPropertyReferenceRefreshtokensPut**](RefreshTokensPropertyReferenceControllerApi.md#createpropertyreferencerefreshtokensput) | **PUT** /refreshTokenses/{id}/user |  |
| [**deletePropertyReferenceIdRefreshtokensDelete**](RefreshTokensPropertyReferenceControllerApi.md#deletepropertyreferenceidrefreshtokensdelete) | **DELETE** /refreshTokenses/{id}/user/{propertyId} |  |
| [**deletePropertyReferenceRefreshtokensDelete**](RefreshTokensPropertyReferenceControllerApi.md#deletepropertyreferencerefreshtokensdelete) | **DELETE** /refreshTokenses/{id}/user |  |
| [**followPropertyReferenceRefreshtokensGet**](RefreshTokensPropertyReferenceControllerApi.md#followpropertyreferencerefreshtokensget) | **GET** /refreshTokenses/{id}/user/{propertyId} |  |
| [**followPropertyReferenceRefreshtokensGet1**](RefreshTokensPropertyReferenceControllerApi.md#followpropertyreferencerefreshtokensget1) | **GET** /refreshTokenses/{id}/user |  |



## createPropertyReferenceRefreshtokensPatch

> EntityModelUser createPropertyReferenceRefreshtokensPatch(id, collectionModelObject)



patch-user-by-refreshtokens-Id

### Example

```ts
import {
  Configuration,
  RefreshTokensPropertyReferenceControllerApi,
} from '';
import type { CreatePropertyReferenceRefreshtokensPatchRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new RefreshTokensPropertyReferenceControllerApi();

  const body = {
    // string
    id: id_example,
    // CollectionModelObject
    collectionModelObject: ...,
  } satisfies CreatePropertyReferenceRefreshtokensPatchRequest;

  try {
    const data = await api.createPropertyReferenceRefreshtokensPatch(body);
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


## createPropertyReferenceRefreshtokensPut

> EntityModelUser createPropertyReferenceRefreshtokensPut(id, collectionModelObject)



update-user-by-refreshtokens-Id

### Example

```ts
import {
  Configuration,
  RefreshTokensPropertyReferenceControllerApi,
} from '';
import type { CreatePropertyReferenceRefreshtokensPutRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new RefreshTokensPropertyReferenceControllerApi();

  const body = {
    // string
    id: id_example,
    // CollectionModelObject
    collectionModelObject: ...,
  } satisfies CreatePropertyReferenceRefreshtokensPutRequest;

  try {
    const data = await api.createPropertyReferenceRefreshtokensPut(body);
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


## deletePropertyReferenceIdRefreshtokensDelete

> deletePropertyReferenceIdRefreshtokensDelete(id, propertyId)



delete-user-by-refreshtokens-Id

### Example

```ts
import {
  Configuration,
  RefreshTokensPropertyReferenceControllerApi,
} from '';
import type { DeletePropertyReferenceIdRefreshtokensDeleteRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new RefreshTokensPropertyReferenceControllerApi();

  const body = {
    // string
    id: id_example,
    // string
    propertyId: propertyId_example,
  } satisfies DeletePropertyReferenceIdRefreshtokensDeleteRequest;

  try {
    const data = await api.deletePropertyReferenceIdRefreshtokensDelete(body);
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


## deletePropertyReferenceRefreshtokensDelete

> deletePropertyReferenceRefreshtokensDelete(id)



delete-user-by-refreshtokens-Id

### Example

```ts
import {
  Configuration,
  RefreshTokensPropertyReferenceControllerApi,
} from '';
import type { DeletePropertyReferenceRefreshtokensDeleteRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new RefreshTokensPropertyReferenceControllerApi();

  const body = {
    // string
    id: id_example,
  } satisfies DeletePropertyReferenceRefreshtokensDeleteRequest;

  try {
    const data = await api.deletePropertyReferenceRefreshtokensDelete(body);
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


## followPropertyReferenceRefreshtokensGet

> EntityModelUser followPropertyReferenceRefreshtokensGet(id, propertyId)



get-user-by-refreshtokens-Id

### Example

```ts
import {
  Configuration,
  RefreshTokensPropertyReferenceControllerApi,
} from '';
import type { FollowPropertyReferenceRefreshtokensGetRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new RefreshTokensPropertyReferenceControllerApi();

  const body = {
    // string
    id: id_example,
    // string
    propertyId: propertyId_example,
  } satisfies FollowPropertyReferenceRefreshtokensGetRequest;

  try {
    const data = await api.followPropertyReferenceRefreshtokensGet(body);
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


## followPropertyReferenceRefreshtokensGet1

> EntityModelUser followPropertyReferenceRefreshtokensGet1(id)



get-user-by-refreshtokens-Id

### Example

```ts
import {
  Configuration,
  RefreshTokensPropertyReferenceControllerApi,
} from '';
import type { FollowPropertyReferenceRefreshtokensGet1Request } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new RefreshTokensPropertyReferenceControllerApi();

  const body = {
    // string
    id: id_example,
  } satisfies FollowPropertyReferenceRefreshtokensGet1Request;

  try {
    const data = await api.followPropertyReferenceRefreshtokensGet1(body);
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


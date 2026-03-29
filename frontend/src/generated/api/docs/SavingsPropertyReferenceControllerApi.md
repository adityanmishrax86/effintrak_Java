# SavingsPropertyReferenceControllerApi

All URIs are relative to *http://localhost:8080*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**createPropertyReferenceSavingsPatch**](SavingsPropertyReferenceControllerApi.md#createpropertyreferencesavingspatch) | **PATCH** /savingses/{id}/user |  |
| [**createPropertyReferenceSavingsPut**](SavingsPropertyReferenceControllerApi.md#createpropertyreferencesavingsput) | **PUT** /savingses/{id}/user |  |
| [**deletePropertyReferenceIdSavingsDelete**](SavingsPropertyReferenceControllerApi.md#deletepropertyreferenceidsavingsdelete) | **DELETE** /savingses/{id}/user/{propertyId} |  |
| [**deletePropertyReferenceSavingsDelete**](SavingsPropertyReferenceControllerApi.md#deletepropertyreferencesavingsdelete) | **DELETE** /savingses/{id}/user |  |
| [**followPropertyReferenceSavingsGet**](SavingsPropertyReferenceControllerApi.md#followpropertyreferencesavingsget) | **GET** /savingses/{id}/user/{propertyId} |  |
| [**followPropertyReferenceSavingsGet1**](SavingsPropertyReferenceControllerApi.md#followpropertyreferencesavingsget1) | **GET** /savingses/{id}/user |  |



## createPropertyReferenceSavingsPatch

> EntityModelUser createPropertyReferenceSavingsPatch(id, collectionModelObject)



patch-user-by-savings-Id

### Example

```ts
import {
  Configuration,
  SavingsPropertyReferenceControllerApi,
} from '';
import type { CreatePropertyReferenceSavingsPatchRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new SavingsPropertyReferenceControllerApi();

  const body = {
    // string
    id: id_example,
    // CollectionModelObject
    collectionModelObject: ...,
  } satisfies CreatePropertyReferenceSavingsPatchRequest;

  try {
    const data = await api.createPropertyReferenceSavingsPatch(body);
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


## createPropertyReferenceSavingsPut

> EntityModelUser createPropertyReferenceSavingsPut(id, collectionModelObject)



update-user-by-savings-Id

### Example

```ts
import {
  Configuration,
  SavingsPropertyReferenceControllerApi,
} from '';
import type { CreatePropertyReferenceSavingsPutRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new SavingsPropertyReferenceControllerApi();

  const body = {
    // string
    id: id_example,
    // CollectionModelObject
    collectionModelObject: ...,
  } satisfies CreatePropertyReferenceSavingsPutRequest;

  try {
    const data = await api.createPropertyReferenceSavingsPut(body);
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


## deletePropertyReferenceIdSavingsDelete

> deletePropertyReferenceIdSavingsDelete(id, propertyId)



delete-user-by-savings-Id

### Example

```ts
import {
  Configuration,
  SavingsPropertyReferenceControllerApi,
} from '';
import type { DeletePropertyReferenceIdSavingsDeleteRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new SavingsPropertyReferenceControllerApi();

  const body = {
    // string
    id: id_example,
    // string
    propertyId: propertyId_example,
  } satisfies DeletePropertyReferenceIdSavingsDeleteRequest;

  try {
    const data = await api.deletePropertyReferenceIdSavingsDelete(body);
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


## deletePropertyReferenceSavingsDelete

> deletePropertyReferenceSavingsDelete(id)



delete-user-by-savings-Id

### Example

```ts
import {
  Configuration,
  SavingsPropertyReferenceControllerApi,
} from '';
import type { DeletePropertyReferenceSavingsDeleteRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new SavingsPropertyReferenceControllerApi();

  const body = {
    // string
    id: id_example,
  } satisfies DeletePropertyReferenceSavingsDeleteRequest;

  try {
    const data = await api.deletePropertyReferenceSavingsDelete(body);
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


## followPropertyReferenceSavingsGet

> EntityModelUser followPropertyReferenceSavingsGet(id, propertyId)



get-user-by-savings-Id

### Example

```ts
import {
  Configuration,
  SavingsPropertyReferenceControllerApi,
} from '';
import type { FollowPropertyReferenceSavingsGetRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new SavingsPropertyReferenceControllerApi();

  const body = {
    // string
    id: id_example,
    // string
    propertyId: propertyId_example,
  } satisfies FollowPropertyReferenceSavingsGetRequest;

  try {
    const data = await api.followPropertyReferenceSavingsGet(body);
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


## followPropertyReferenceSavingsGet1

> EntityModelUser followPropertyReferenceSavingsGet1(id)



get-user-by-savings-Id

### Example

```ts
import {
  Configuration,
  SavingsPropertyReferenceControllerApi,
} from '';
import type { FollowPropertyReferenceSavingsGet1Request } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new SavingsPropertyReferenceControllerApi();

  const body = {
    // string
    id: id_example,
  } satisfies FollowPropertyReferenceSavingsGet1Request;

  try {
    const data = await api.followPropertyReferenceSavingsGet1(body);
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


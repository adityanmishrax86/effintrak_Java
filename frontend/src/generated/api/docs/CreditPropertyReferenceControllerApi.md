# CreditPropertyReferenceControllerApi

All URIs are relative to *http://localhost:8080*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**createPropertyReferenceCreditPatch**](CreditPropertyReferenceControllerApi.md#createpropertyreferencecreditpatch) | **PATCH** /credits/{id}/creditor |  |
| [**createPropertyReferenceCreditPatch1**](CreditPropertyReferenceControllerApi.md#createpropertyreferencecreditpatch1) | **PATCH** /credits/{id}/user |  |
| [**createPropertyReferenceCreditPut**](CreditPropertyReferenceControllerApi.md#createpropertyreferencecreditput) | **PUT** /credits/{id}/creditor |  |
| [**createPropertyReferenceCreditPut1**](CreditPropertyReferenceControllerApi.md#createpropertyreferencecreditput1) | **PUT** /credits/{id}/user |  |
| [**deletePropertyReferenceCreditDelete**](CreditPropertyReferenceControllerApi.md#deletepropertyreferencecreditdelete) | **DELETE** /credits/{id}/creditor |  |
| [**deletePropertyReferenceCreditDelete1**](CreditPropertyReferenceControllerApi.md#deletepropertyreferencecreditdelete1) | **DELETE** /credits/{id}/user |  |
| [**deletePropertyReferenceIdCreditDelete**](CreditPropertyReferenceControllerApi.md#deletepropertyreferenceidcreditdelete) | **DELETE** /credits/{id}/creditor/{propertyId} |  |
| [**deletePropertyReferenceIdCreditDelete1**](CreditPropertyReferenceControllerApi.md#deletepropertyreferenceidcreditdelete1) | **DELETE** /credits/{id}/user/{propertyId} |  |
| [**followPropertyReferenceCreditGet**](CreditPropertyReferenceControllerApi.md#followpropertyreferencecreditget) | **GET** /credits/{id}/creditor/{propertyId} |  |
| [**followPropertyReferenceCreditGet1**](CreditPropertyReferenceControllerApi.md#followpropertyreferencecreditget1) | **GET** /credits/{id}/creditor |  |
| [**followPropertyReferenceCreditGet2**](CreditPropertyReferenceControllerApi.md#followpropertyreferencecreditget2) | **GET** /credits/{id}/user/{propertyId} |  |
| [**followPropertyReferenceCreditGet21**](CreditPropertyReferenceControllerApi.md#followpropertyreferencecreditget21) | **GET** /credits/{id}/user |  |



## createPropertyReferenceCreditPatch

> EntityModelBankAccount createPropertyReferenceCreditPatch(id, collectionModelObject)



patch-bankaccount-by-credit-Id

### Example

```ts
import {
  Configuration,
  CreditPropertyReferenceControllerApi,
} from '';
import type { CreatePropertyReferenceCreditPatchRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new CreditPropertyReferenceControllerApi();

  const body = {
    // string
    id: id_example,
    // CollectionModelObject
    collectionModelObject: ...,
  } satisfies CreatePropertyReferenceCreditPatchRequest;

  try {
    const data = await api.createPropertyReferenceCreditPatch(body);
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

[**EntityModelBankAccount**](EntityModelBankAccount.md)

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


## createPropertyReferenceCreditPatch1

> EntityModelUser createPropertyReferenceCreditPatch1(id, collectionModelObject)



patch-user-by-credit-Id

### Example

```ts
import {
  Configuration,
  CreditPropertyReferenceControllerApi,
} from '';
import type { CreatePropertyReferenceCreditPatch1Request } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new CreditPropertyReferenceControllerApi();

  const body = {
    // string
    id: id_example,
    // CollectionModelObject
    collectionModelObject: ...,
  } satisfies CreatePropertyReferenceCreditPatch1Request;

  try {
    const data = await api.createPropertyReferenceCreditPatch1(body);
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


## createPropertyReferenceCreditPut

> EntityModelBankAccount createPropertyReferenceCreditPut(id, collectionModelObject)



update-bankaccount-by-credit-Id

### Example

```ts
import {
  Configuration,
  CreditPropertyReferenceControllerApi,
} from '';
import type { CreatePropertyReferenceCreditPutRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new CreditPropertyReferenceControllerApi();

  const body = {
    // string
    id: id_example,
    // CollectionModelObject
    collectionModelObject: ...,
  } satisfies CreatePropertyReferenceCreditPutRequest;

  try {
    const data = await api.createPropertyReferenceCreditPut(body);
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

[**EntityModelBankAccount**](EntityModelBankAccount.md)

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


## createPropertyReferenceCreditPut1

> EntityModelUser createPropertyReferenceCreditPut1(id, collectionModelObject)



update-user-by-credit-Id

### Example

```ts
import {
  Configuration,
  CreditPropertyReferenceControllerApi,
} from '';
import type { CreatePropertyReferenceCreditPut1Request } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new CreditPropertyReferenceControllerApi();

  const body = {
    // string
    id: id_example,
    // CollectionModelObject
    collectionModelObject: ...,
  } satisfies CreatePropertyReferenceCreditPut1Request;

  try {
    const data = await api.createPropertyReferenceCreditPut1(body);
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


## deletePropertyReferenceCreditDelete

> deletePropertyReferenceCreditDelete(id)



delete-bankaccount-by-credit-Id

### Example

```ts
import {
  Configuration,
  CreditPropertyReferenceControllerApi,
} from '';
import type { DeletePropertyReferenceCreditDeleteRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new CreditPropertyReferenceControllerApi();

  const body = {
    // string
    id: id_example,
  } satisfies DeletePropertyReferenceCreditDeleteRequest;

  try {
    const data = await api.deletePropertyReferenceCreditDelete(body);
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


## deletePropertyReferenceCreditDelete1

> deletePropertyReferenceCreditDelete1(id)



delete-user-by-credit-Id

### Example

```ts
import {
  Configuration,
  CreditPropertyReferenceControllerApi,
} from '';
import type { DeletePropertyReferenceCreditDelete1Request } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new CreditPropertyReferenceControllerApi();

  const body = {
    // string
    id: id_example,
  } satisfies DeletePropertyReferenceCreditDelete1Request;

  try {
    const data = await api.deletePropertyReferenceCreditDelete1(body);
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


## deletePropertyReferenceIdCreditDelete

> deletePropertyReferenceIdCreditDelete(id, propertyId)



delete-bankaccount-by-credit-Id

### Example

```ts
import {
  Configuration,
  CreditPropertyReferenceControllerApi,
} from '';
import type { DeletePropertyReferenceIdCreditDeleteRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new CreditPropertyReferenceControllerApi();

  const body = {
    // string
    id: id_example,
    // string
    propertyId: propertyId_example,
  } satisfies DeletePropertyReferenceIdCreditDeleteRequest;

  try {
    const data = await api.deletePropertyReferenceIdCreditDelete(body);
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


## deletePropertyReferenceIdCreditDelete1

> deletePropertyReferenceIdCreditDelete1(id, propertyId)



delete-user-by-credit-Id

### Example

```ts
import {
  Configuration,
  CreditPropertyReferenceControllerApi,
} from '';
import type { DeletePropertyReferenceIdCreditDelete1Request } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new CreditPropertyReferenceControllerApi();

  const body = {
    // string
    id: id_example,
    // string
    propertyId: propertyId_example,
  } satisfies DeletePropertyReferenceIdCreditDelete1Request;

  try {
    const data = await api.deletePropertyReferenceIdCreditDelete1(body);
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


## followPropertyReferenceCreditGet

> EntityModelBankAccount followPropertyReferenceCreditGet(id, propertyId)



get-bankaccount-by-credit-Id

### Example

```ts
import {
  Configuration,
  CreditPropertyReferenceControllerApi,
} from '';
import type { FollowPropertyReferenceCreditGetRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new CreditPropertyReferenceControllerApi();

  const body = {
    // string
    id: id_example,
    // string
    propertyId: propertyId_example,
  } satisfies FollowPropertyReferenceCreditGetRequest;

  try {
    const data = await api.followPropertyReferenceCreditGet(body);
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

[**EntityModelBankAccount**](EntityModelBankAccount.md)

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


## followPropertyReferenceCreditGet1

> EntityModelBankAccount followPropertyReferenceCreditGet1(id)



get-bankaccount-by-credit-Id

### Example

```ts
import {
  Configuration,
  CreditPropertyReferenceControllerApi,
} from '';
import type { FollowPropertyReferenceCreditGet1Request } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new CreditPropertyReferenceControllerApi();

  const body = {
    // string
    id: id_example,
  } satisfies FollowPropertyReferenceCreditGet1Request;

  try {
    const data = await api.followPropertyReferenceCreditGet1(body);
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

[**EntityModelBankAccount**](EntityModelBankAccount.md)

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


## followPropertyReferenceCreditGet2

> EntityModelUser followPropertyReferenceCreditGet2(id, propertyId)



get-user-by-credit-Id

### Example

```ts
import {
  Configuration,
  CreditPropertyReferenceControllerApi,
} from '';
import type { FollowPropertyReferenceCreditGet2Request } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new CreditPropertyReferenceControllerApi();

  const body = {
    // string
    id: id_example,
    // string
    propertyId: propertyId_example,
  } satisfies FollowPropertyReferenceCreditGet2Request;

  try {
    const data = await api.followPropertyReferenceCreditGet2(body);
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


## followPropertyReferenceCreditGet21

> EntityModelUser followPropertyReferenceCreditGet21(id)



get-user-by-credit-Id

### Example

```ts
import {
  Configuration,
  CreditPropertyReferenceControllerApi,
} from '';
import type { FollowPropertyReferenceCreditGet21Request } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new CreditPropertyReferenceControllerApi();

  const body = {
    // string
    id: id_example,
  } satisfies FollowPropertyReferenceCreditGet21Request;

  try {
    const data = await api.followPropertyReferenceCreditGet21(body);
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


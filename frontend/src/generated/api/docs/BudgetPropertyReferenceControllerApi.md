# BudgetPropertyReferenceControllerApi

All URIs are relative to *http://localhost:8080*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**createPropertyReferenceBudgetPatch**](BudgetPropertyReferenceControllerApi.md#createpropertyreferencebudgetpatch) | **PATCH** /budgets/{id}/category |  |
| [**createPropertyReferenceBudgetPatch1**](BudgetPropertyReferenceControllerApi.md#createpropertyreferencebudgetpatch1) | **PATCH** /budgets/{id}/user |  |
| [**createPropertyReferenceBudgetPut**](BudgetPropertyReferenceControllerApi.md#createpropertyreferencebudgetput) | **PUT** /budgets/{id}/category |  |
| [**createPropertyReferenceBudgetPut1**](BudgetPropertyReferenceControllerApi.md#createpropertyreferencebudgetput1) | **PUT** /budgets/{id}/user |  |
| [**deletePropertyReferenceBudgetDelete**](BudgetPropertyReferenceControllerApi.md#deletepropertyreferencebudgetdelete) | **DELETE** /budgets/{id}/category |  |
| [**deletePropertyReferenceBudgetDelete1**](BudgetPropertyReferenceControllerApi.md#deletepropertyreferencebudgetdelete1) | **DELETE** /budgets/{id}/user |  |
| [**deletePropertyReferenceIdBudgetDelete**](BudgetPropertyReferenceControllerApi.md#deletepropertyreferenceidbudgetdelete) | **DELETE** /budgets/{id}/category/{propertyId} |  |
| [**deletePropertyReferenceIdBudgetDelete1**](BudgetPropertyReferenceControllerApi.md#deletepropertyreferenceidbudgetdelete1) | **DELETE** /budgets/{id}/user/{propertyId} |  |
| [**followPropertyReferenceBudgetGet**](BudgetPropertyReferenceControllerApi.md#followpropertyreferencebudgetget) | **GET** /budgets/{id}/category/{propertyId} |  |
| [**followPropertyReferenceBudgetGet1**](BudgetPropertyReferenceControllerApi.md#followpropertyreferencebudgetget1) | **GET** /budgets/{id}/category |  |
| [**followPropertyReferenceBudgetGet2**](BudgetPropertyReferenceControllerApi.md#followpropertyreferencebudgetget2) | **GET** /budgets/{id}/user/{propertyId} |  |
| [**followPropertyReferenceBudgetGet21**](BudgetPropertyReferenceControllerApi.md#followpropertyreferencebudgetget21) | **GET** /budgets/{id}/user |  |



## createPropertyReferenceBudgetPatch

> EntityModelCategory createPropertyReferenceBudgetPatch(id, collectionModelObject)



patch-category-by-budget-Id

### Example

```ts
import {
  Configuration,
  BudgetPropertyReferenceControllerApi,
} from '';
import type { CreatePropertyReferenceBudgetPatchRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new BudgetPropertyReferenceControllerApi();

  const body = {
    // string
    id: id_example,
    // CollectionModelObject
    collectionModelObject: ...,
  } satisfies CreatePropertyReferenceBudgetPatchRequest;

  try {
    const data = await api.createPropertyReferenceBudgetPatch(body);
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

[**EntityModelCategory**](EntityModelCategory.md)

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


## createPropertyReferenceBudgetPatch1

> EntityModelUser createPropertyReferenceBudgetPatch1(id, collectionModelObject)



patch-user-by-budget-Id

### Example

```ts
import {
  Configuration,
  BudgetPropertyReferenceControllerApi,
} from '';
import type { CreatePropertyReferenceBudgetPatch1Request } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new BudgetPropertyReferenceControllerApi();

  const body = {
    // string
    id: id_example,
    // CollectionModelObject
    collectionModelObject: ...,
  } satisfies CreatePropertyReferenceBudgetPatch1Request;

  try {
    const data = await api.createPropertyReferenceBudgetPatch1(body);
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


## createPropertyReferenceBudgetPut

> EntityModelCategory createPropertyReferenceBudgetPut(id, collectionModelObject)



update-category-by-budget-Id

### Example

```ts
import {
  Configuration,
  BudgetPropertyReferenceControllerApi,
} from '';
import type { CreatePropertyReferenceBudgetPutRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new BudgetPropertyReferenceControllerApi();

  const body = {
    // string
    id: id_example,
    // CollectionModelObject
    collectionModelObject: ...,
  } satisfies CreatePropertyReferenceBudgetPutRequest;

  try {
    const data = await api.createPropertyReferenceBudgetPut(body);
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

[**EntityModelCategory**](EntityModelCategory.md)

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


## createPropertyReferenceBudgetPut1

> EntityModelUser createPropertyReferenceBudgetPut1(id, collectionModelObject)



update-user-by-budget-Id

### Example

```ts
import {
  Configuration,
  BudgetPropertyReferenceControllerApi,
} from '';
import type { CreatePropertyReferenceBudgetPut1Request } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new BudgetPropertyReferenceControllerApi();

  const body = {
    // string
    id: id_example,
    // CollectionModelObject
    collectionModelObject: ...,
  } satisfies CreatePropertyReferenceBudgetPut1Request;

  try {
    const data = await api.createPropertyReferenceBudgetPut1(body);
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


## deletePropertyReferenceBudgetDelete

> deletePropertyReferenceBudgetDelete(id)



delete-category-by-budget-Id

### Example

```ts
import {
  Configuration,
  BudgetPropertyReferenceControllerApi,
} from '';
import type { DeletePropertyReferenceBudgetDeleteRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new BudgetPropertyReferenceControllerApi();

  const body = {
    // string
    id: id_example,
  } satisfies DeletePropertyReferenceBudgetDeleteRequest;

  try {
    const data = await api.deletePropertyReferenceBudgetDelete(body);
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


## deletePropertyReferenceBudgetDelete1

> deletePropertyReferenceBudgetDelete1(id)



delete-user-by-budget-Id

### Example

```ts
import {
  Configuration,
  BudgetPropertyReferenceControllerApi,
} from '';
import type { DeletePropertyReferenceBudgetDelete1Request } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new BudgetPropertyReferenceControllerApi();

  const body = {
    // string
    id: id_example,
  } satisfies DeletePropertyReferenceBudgetDelete1Request;

  try {
    const data = await api.deletePropertyReferenceBudgetDelete1(body);
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


## deletePropertyReferenceIdBudgetDelete

> deletePropertyReferenceIdBudgetDelete(id, propertyId)



delete-category-by-budget-Id

### Example

```ts
import {
  Configuration,
  BudgetPropertyReferenceControllerApi,
} from '';
import type { DeletePropertyReferenceIdBudgetDeleteRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new BudgetPropertyReferenceControllerApi();

  const body = {
    // string
    id: id_example,
    // string
    propertyId: propertyId_example,
  } satisfies DeletePropertyReferenceIdBudgetDeleteRequest;

  try {
    const data = await api.deletePropertyReferenceIdBudgetDelete(body);
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


## deletePropertyReferenceIdBudgetDelete1

> deletePropertyReferenceIdBudgetDelete1(id, propertyId)



delete-user-by-budget-Id

### Example

```ts
import {
  Configuration,
  BudgetPropertyReferenceControllerApi,
} from '';
import type { DeletePropertyReferenceIdBudgetDelete1Request } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new BudgetPropertyReferenceControllerApi();

  const body = {
    // string
    id: id_example,
    // string
    propertyId: propertyId_example,
  } satisfies DeletePropertyReferenceIdBudgetDelete1Request;

  try {
    const data = await api.deletePropertyReferenceIdBudgetDelete1(body);
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


## followPropertyReferenceBudgetGet

> EntityModelCategory followPropertyReferenceBudgetGet(id, propertyId)



get-category-by-budget-Id

### Example

```ts
import {
  Configuration,
  BudgetPropertyReferenceControllerApi,
} from '';
import type { FollowPropertyReferenceBudgetGetRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new BudgetPropertyReferenceControllerApi();

  const body = {
    // string
    id: id_example,
    // string
    propertyId: propertyId_example,
  } satisfies FollowPropertyReferenceBudgetGetRequest;

  try {
    const data = await api.followPropertyReferenceBudgetGet(body);
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

[**EntityModelCategory**](EntityModelCategory.md)

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


## followPropertyReferenceBudgetGet1

> EntityModelCategory followPropertyReferenceBudgetGet1(id)



get-category-by-budget-Id

### Example

```ts
import {
  Configuration,
  BudgetPropertyReferenceControllerApi,
} from '';
import type { FollowPropertyReferenceBudgetGet1Request } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new BudgetPropertyReferenceControllerApi();

  const body = {
    // string
    id: id_example,
  } satisfies FollowPropertyReferenceBudgetGet1Request;

  try {
    const data = await api.followPropertyReferenceBudgetGet1(body);
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

[**EntityModelCategory**](EntityModelCategory.md)

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


## followPropertyReferenceBudgetGet2

> EntityModelUser followPropertyReferenceBudgetGet2(id, propertyId)



get-user-by-budget-Id

### Example

```ts
import {
  Configuration,
  BudgetPropertyReferenceControllerApi,
} from '';
import type { FollowPropertyReferenceBudgetGet2Request } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new BudgetPropertyReferenceControllerApi();

  const body = {
    // string
    id: id_example,
    // string
    propertyId: propertyId_example,
  } satisfies FollowPropertyReferenceBudgetGet2Request;

  try {
    const data = await api.followPropertyReferenceBudgetGet2(body);
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


## followPropertyReferenceBudgetGet21

> EntityModelUser followPropertyReferenceBudgetGet21(id)



get-user-by-budget-Id

### Example

```ts
import {
  Configuration,
  BudgetPropertyReferenceControllerApi,
} from '';
import type { FollowPropertyReferenceBudgetGet21Request } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new BudgetPropertyReferenceControllerApi();

  const body = {
    // string
    id: id_example,
  } satisfies FollowPropertyReferenceBudgetGet21Request;

  try {
    const data = await api.followPropertyReferenceBudgetGet21(body);
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


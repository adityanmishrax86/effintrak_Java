# IncomePropertyReferenceControllerApi

All URIs are relative to *http://localhost:8080*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**createPropertyReferenceIncomePatch**](IncomePropertyReferenceControllerApi.md#createpropertyreferenceincomepatch) | **PATCH** /incomes/{id}/bankAccount |  |
| [**createPropertyReferenceIncomePatch1**](IncomePropertyReferenceControllerApi.md#createpropertyreferenceincomepatch1) | **PATCH** /incomes/{id}/category |  |
| [**createPropertyReferenceIncomePatch2**](IncomePropertyReferenceControllerApi.md#createpropertyreferenceincomepatch2) | **PATCH** /incomes/{id}/user |  |
| [**createPropertyReferenceIncomePut**](IncomePropertyReferenceControllerApi.md#createpropertyreferenceincomeput) | **PUT** /incomes/{id}/bankAccount |  |
| [**createPropertyReferenceIncomePut1**](IncomePropertyReferenceControllerApi.md#createpropertyreferenceincomeput1) | **PUT** /incomes/{id}/category |  |
| [**createPropertyReferenceIncomePut2**](IncomePropertyReferenceControllerApi.md#createpropertyreferenceincomeput2) | **PUT** /incomes/{id}/user |  |
| [**deletePropertyReferenceIdIncomeDelete**](IncomePropertyReferenceControllerApi.md#deletepropertyreferenceidincomedelete) | **DELETE** /incomes/{id}/bankAccount/{propertyId} |  |
| [**deletePropertyReferenceIdIncomeDelete1**](IncomePropertyReferenceControllerApi.md#deletepropertyreferenceidincomedelete1) | **DELETE** /incomes/{id}/category/{propertyId} |  |
| [**deletePropertyReferenceIdIncomeDelete2**](IncomePropertyReferenceControllerApi.md#deletepropertyreferenceidincomedelete2) | **DELETE** /incomes/{id}/user/{propertyId} |  |
| [**deletePropertyReferenceIncomeDelete**](IncomePropertyReferenceControllerApi.md#deletepropertyreferenceincomedelete) | **DELETE** /incomes/{id}/bankAccount |  |
| [**deletePropertyReferenceIncomeDelete1**](IncomePropertyReferenceControllerApi.md#deletepropertyreferenceincomedelete1) | **DELETE** /incomes/{id}/category |  |
| [**deletePropertyReferenceIncomeDelete2**](IncomePropertyReferenceControllerApi.md#deletepropertyreferenceincomedelete2) | **DELETE** /incomes/{id}/user |  |
| [**followPropertyReferenceIncomeGet**](IncomePropertyReferenceControllerApi.md#followpropertyreferenceincomeget) | **GET** /incomes/{id}/bankAccount/{propertyId} |  |
| [**followPropertyReferenceIncomeGet1**](IncomePropertyReferenceControllerApi.md#followpropertyreferenceincomeget1) | **GET** /incomes/{id}/bankAccount |  |
| [**followPropertyReferenceIncomeGet2**](IncomePropertyReferenceControllerApi.md#followpropertyreferenceincomeget2) | **GET** /incomes/{id}/category/{propertyId} |  |
| [**followPropertyReferenceIncomeGet21**](IncomePropertyReferenceControllerApi.md#followpropertyreferenceincomeget21) | **GET** /incomes/{id}/category |  |
| [**followPropertyReferenceIncomeGet3**](IncomePropertyReferenceControllerApi.md#followpropertyreferenceincomeget3) | **GET** /incomes/{id}/user/{propertyId} |  |
| [**followPropertyReferenceIncomeGet31**](IncomePropertyReferenceControllerApi.md#followpropertyreferenceincomeget31) | **GET** /incomes/{id}/user |  |



## createPropertyReferenceIncomePatch

> EntityModelBankAccount createPropertyReferenceIncomePatch(id, collectionModelObject)



patch-bankaccount-by-income-Id

### Example

```ts
import {
  Configuration,
  IncomePropertyReferenceControllerApi,
} from '';
import type { CreatePropertyReferenceIncomePatchRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new IncomePropertyReferenceControllerApi();

  const body = {
    // string
    id: id_example,
    // CollectionModelObject
    collectionModelObject: ...,
  } satisfies CreatePropertyReferenceIncomePatchRequest;

  try {
    const data = await api.createPropertyReferenceIncomePatch(body);
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


## createPropertyReferenceIncomePatch1

> EntityModelCategory createPropertyReferenceIncomePatch1(id, collectionModelObject)



patch-category-by-income-Id

### Example

```ts
import {
  Configuration,
  IncomePropertyReferenceControllerApi,
} from '';
import type { CreatePropertyReferenceIncomePatch1Request } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new IncomePropertyReferenceControllerApi();

  const body = {
    // string
    id: id_example,
    // CollectionModelObject
    collectionModelObject: ...,
  } satisfies CreatePropertyReferenceIncomePatch1Request;

  try {
    const data = await api.createPropertyReferenceIncomePatch1(body);
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


## createPropertyReferenceIncomePatch2

> EntityModelUser createPropertyReferenceIncomePatch2(id, collectionModelObject)



patch-user-by-income-Id

### Example

```ts
import {
  Configuration,
  IncomePropertyReferenceControllerApi,
} from '';
import type { CreatePropertyReferenceIncomePatch2Request } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new IncomePropertyReferenceControllerApi();

  const body = {
    // string
    id: id_example,
    // CollectionModelObject
    collectionModelObject: ...,
  } satisfies CreatePropertyReferenceIncomePatch2Request;

  try {
    const data = await api.createPropertyReferenceIncomePatch2(body);
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


## createPropertyReferenceIncomePut

> EntityModelBankAccount createPropertyReferenceIncomePut(id, collectionModelObject)



update-bankaccount-by-income-Id

### Example

```ts
import {
  Configuration,
  IncomePropertyReferenceControllerApi,
} from '';
import type { CreatePropertyReferenceIncomePutRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new IncomePropertyReferenceControllerApi();

  const body = {
    // string
    id: id_example,
    // CollectionModelObject
    collectionModelObject: ...,
  } satisfies CreatePropertyReferenceIncomePutRequest;

  try {
    const data = await api.createPropertyReferenceIncomePut(body);
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


## createPropertyReferenceIncomePut1

> EntityModelCategory createPropertyReferenceIncomePut1(id, collectionModelObject)



update-category-by-income-Id

### Example

```ts
import {
  Configuration,
  IncomePropertyReferenceControllerApi,
} from '';
import type { CreatePropertyReferenceIncomePut1Request } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new IncomePropertyReferenceControllerApi();

  const body = {
    // string
    id: id_example,
    // CollectionModelObject
    collectionModelObject: ...,
  } satisfies CreatePropertyReferenceIncomePut1Request;

  try {
    const data = await api.createPropertyReferenceIncomePut1(body);
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


## createPropertyReferenceIncomePut2

> EntityModelUser createPropertyReferenceIncomePut2(id, collectionModelObject)



update-user-by-income-Id

### Example

```ts
import {
  Configuration,
  IncomePropertyReferenceControllerApi,
} from '';
import type { CreatePropertyReferenceIncomePut2Request } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new IncomePropertyReferenceControllerApi();

  const body = {
    // string
    id: id_example,
    // CollectionModelObject
    collectionModelObject: ...,
  } satisfies CreatePropertyReferenceIncomePut2Request;

  try {
    const data = await api.createPropertyReferenceIncomePut2(body);
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


## deletePropertyReferenceIdIncomeDelete

> deletePropertyReferenceIdIncomeDelete(id, propertyId)



delete-bankaccount-by-income-Id

### Example

```ts
import {
  Configuration,
  IncomePropertyReferenceControllerApi,
} from '';
import type { DeletePropertyReferenceIdIncomeDeleteRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new IncomePropertyReferenceControllerApi();

  const body = {
    // string
    id: id_example,
    // string
    propertyId: propertyId_example,
  } satisfies DeletePropertyReferenceIdIncomeDeleteRequest;

  try {
    const data = await api.deletePropertyReferenceIdIncomeDelete(body);
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


## deletePropertyReferenceIdIncomeDelete1

> deletePropertyReferenceIdIncomeDelete1(id, propertyId)



delete-category-by-income-Id

### Example

```ts
import {
  Configuration,
  IncomePropertyReferenceControllerApi,
} from '';
import type { DeletePropertyReferenceIdIncomeDelete1Request } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new IncomePropertyReferenceControllerApi();

  const body = {
    // string
    id: id_example,
    // string
    propertyId: propertyId_example,
  } satisfies DeletePropertyReferenceIdIncomeDelete1Request;

  try {
    const data = await api.deletePropertyReferenceIdIncomeDelete1(body);
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


## deletePropertyReferenceIdIncomeDelete2

> deletePropertyReferenceIdIncomeDelete2(id, propertyId)



delete-user-by-income-Id

### Example

```ts
import {
  Configuration,
  IncomePropertyReferenceControllerApi,
} from '';
import type { DeletePropertyReferenceIdIncomeDelete2Request } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new IncomePropertyReferenceControllerApi();

  const body = {
    // string
    id: id_example,
    // string
    propertyId: propertyId_example,
  } satisfies DeletePropertyReferenceIdIncomeDelete2Request;

  try {
    const data = await api.deletePropertyReferenceIdIncomeDelete2(body);
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


## deletePropertyReferenceIncomeDelete

> deletePropertyReferenceIncomeDelete(id)



delete-bankaccount-by-income-Id

### Example

```ts
import {
  Configuration,
  IncomePropertyReferenceControllerApi,
} from '';
import type { DeletePropertyReferenceIncomeDeleteRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new IncomePropertyReferenceControllerApi();

  const body = {
    // string
    id: id_example,
  } satisfies DeletePropertyReferenceIncomeDeleteRequest;

  try {
    const data = await api.deletePropertyReferenceIncomeDelete(body);
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


## deletePropertyReferenceIncomeDelete1

> deletePropertyReferenceIncomeDelete1(id)



delete-category-by-income-Id

### Example

```ts
import {
  Configuration,
  IncomePropertyReferenceControllerApi,
} from '';
import type { DeletePropertyReferenceIncomeDelete1Request } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new IncomePropertyReferenceControllerApi();

  const body = {
    // string
    id: id_example,
  } satisfies DeletePropertyReferenceIncomeDelete1Request;

  try {
    const data = await api.deletePropertyReferenceIncomeDelete1(body);
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


## deletePropertyReferenceIncomeDelete2

> deletePropertyReferenceIncomeDelete2(id)



delete-user-by-income-Id

### Example

```ts
import {
  Configuration,
  IncomePropertyReferenceControllerApi,
} from '';
import type { DeletePropertyReferenceIncomeDelete2Request } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new IncomePropertyReferenceControllerApi();

  const body = {
    // string
    id: id_example,
  } satisfies DeletePropertyReferenceIncomeDelete2Request;

  try {
    const data = await api.deletePropertyReferenceIncomeDelete2(body);
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


## followPropertyReferenceIncomeGet

> EntityModelBankAccount followPropertyReferenceIncomeGet(id, propertyId)



get-bankaccount-by-income-Id

### Example

```ts
import {
  Configuration,
  IncomePropertyReferenceControllerApi,
} from '';
import type { FollowPropertyReferenceIncomeGetRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new IncomePropertyReferenceControllerApi();

  const body = {
    // string
    id: id_example,
    // string
    propertyId: propertyId_example,
  } satisfies FollowPropertyReferenceIncomeGetRequest;

  try {
    const data = await api.followPropertyReferenceIncomeGet(body);
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


## followPropertyReferenceIncomeGet1

> EntityModelBankAccount followPropertyReferenceIncomeGet1(id)



get-bankaccount-by-income-Id

### Example

```ts
import {
  Configuration,
  IncomePropertyReferenceControllerApi,
} from '';
import type { FollowPropertyReferenceIncomeGet1Request } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new IncomePropertyReferenceControllerApi();

  const body = {
    // string
    id: id_example,
  } satisfies FollowPropertyReferenceIncomeGet1Request;

  try {
    const data = await api.followPropertyReferenceIncomeGet1(body);
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


## followPropertyReferenceIncomeGet2

> EntityModelCategory followPropertyReferenceIncomeGet2(id, propertyId)



get-category-by-income-Id

### Example

```ts
import {
  Configuration,
  IncomePropertyReferenceControllerApi,
} from '';
import type { FollowPropertyReferenceIncomeGet2Request } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new IncomePropertyReferenceControllerApi();

  const body = {
    // string
    id: id_example,
    // string
    propertyId: propertyId_example,
  } satisfies FollowPropertyReferenceIncomeGet2Request;

  try {
    const data = await api.followPropertyReferenceIncomeGet2(body);
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


## followPropertyReferenceIncomeGet21

> EntityModelCategory followPropertyReferenceIncomeGet21(id)



get-category-by-income-Id

### Example

```ts
import {
  Configuration,
  IncomePropertyReferenceControllerApi,
} from '';
import type { FollowPropertyReferenceIncomeGet21Request } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new IncomePropertyReferenceControllerApi();

  const body = {
    // string
    id: id_example,
  } satisfies FollowPropertyReferenceIncomeGet21Request;

  try {
    const data = await api.followPropertyReferenceIncomeGet21(body);
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


## followPropertyReferenceIncomeGet3

> EntityModelUser followPropertyReferenceIncomeGet3(id, propertyId)



get-user-by-income-Id

### Example

```ts
import {
  Configuration,
  IncomePropertyReferenceControllerApi,
} from '';
import type { FollowPropertyReferenceIncomeGet3Request } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new IncomePropertyReferenceControllerApi();

  const body = {
    // string
    id: id_example,
    // string
    propertyId: propertyId_example,
  } satisfies FollowPropertyReferenceIncomeGet3Request;

  try {
    const data = await api.followPropertyReferenceIncomeGet3(body);
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


## followPropertyReferenceIncomeGet31

> EntityModelUser followPropertyReferenceIncomeGet31(id)



get-user-by-income-Id

### Example

```ts
import {
  Configuration,
  IncomePropertyReferenceControllerApi,
} from '';
import type { FollowPropertyReferenceIncomeGet31Request } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new IncomePropertyReferenceControllerApi();

  const body = {
    // string
    id: id_example,
  } satisfies FollowPropertyReferenceIncomeGet31Request;

  try {
    const data = await api.followPropertyReferenceIncomeGet31(body);
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


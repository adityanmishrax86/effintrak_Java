# ExpensePropertyReferenceControllerApi

All URIs are relative to *http://localhost:8080*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**createPropertyReferenceExpensePatch**](ExpensePropertyReferenceControllerApi.md#createpropertyreferenceexpensepatch) | **PATCH** /expenses/{id}/bankAccount |  |
| [**createPropertyReferenceExpensePatch1**](ExpensePropertyReferenceControllerApi.md#createpropertyreferenceexpensepatch1) | **PATCH** /expenses/{id}/category |  |
| [**createPropertyReferenceExpensePatch2**](ExpensePropertyReferenceControllerApi.md#createpropertyreferenceexpensepatch2) | **PATCH** /expenses/{id}/user |  |
| [**createPropertyReferenceExpensePut**](ExpensePropertyReferenceControllerApi.md#createpropertyreferenceexpenseput) | **PUT** /expenses/{id}/bankAccount |  |
| [**createPropertyReferenceExpensePut1**](ExpensePropertyReferenceControllerApi.md#createpropertyreferenceexpenseput1) | **PUT** /expenses/{id}/category |  |
| [**createPropertyReferenceExpensePut2**](ExpensePropertyReferenceControllerApi.md#createpropertyreferenceexpenseput2) | **PUT** /expenses/{id}/user |  |
| [**deletePropertyReferenceExpenseDelete**](ExpensePropertyReferenceControllerApi.md#deletepropertyreferenceexpensedelete) | **DELETE** /expenses/{id}/bankAccount |  |
| [**deletePropertyReferenceExpenseDelete1**](ExpensePropertyReferenceControllerApi.md#deletepropertyreferenceexpensedelete1) | **DELETE** /expenses/{id}/category |  |
| [**deletePropertyReferenceExpenseDelete2**](ExpensePropertyReferenceControllerApi.md#deletepropertyreferenceexpensedelete2) | **DELETE** /expenses/{id}/user |  |
| [**deletePropertyReferenceIdExpenseDelete**](ExpensePropertyReferenceControllerApi.md#deletepropertyreferenceidexpensedelete) | **DELETE** /expenses/{id}/bankAccount/{propertyId} |  |
| [**deletePropertyReferenceIdExpenseDelete1**](ExpensePropertyReferenceControllerApi.md#deletepropertyreferenceidexpensedelete1) | **DELETE** /expenses/{id}/category/{propertyId} |  |
| [**deletePropertyReferenceIdExpenseDelete2**](ExpensePropertyReferenceControllerApi.md#deletepropertyreferenceidexpensedelete2) | **DELETE** /expenses/{id}/user/{propertyId} |  |
| [**followPropertyReferenceExpenseGet**](ExpensePropertyReferenceControllerApi.md#followpropertyreferenceexpenseget) | **GET** /expenses/{id}/bankAccount/{propertyId} |  |
| [**followPropertyReferenceExpenseGet1**](ExpensePropertyReferenceControllerApi.md#followpropertyreferenceexpenseget1) | **GET** /expenses/{id}/bankAccount |  |
| [**followPropertyReferenceExpenseGet2**](ExpensePropertyReferenceControllerApi.md#followpropertyreferenceexpenseget2) | **GET** /expenses/{id}/category/{propertyId} |  |
| [**followPropertyReferenceExpenseGet21**](ExpensePropertyReferenceControllerApi.md#followpropertyreferenceexpenseget21) | **GET** /expenses/{id}/category |  |
| [**followPropertyReferenceExpenseGet3**](ExpensePropertyReferenceControllerApi.md#followpropertyreferenceexpenseget3) | **GET** /expenses/{id}/user/{propertyId} |  |
| [**followPropertyReferenceExpenseGet31**](ExpensePropertyReferenceControllerApi.md#followpropertyreferenceexpenseget31) | **GET** /expenses/{id}/user |  |



## createPropertyReferenceExpensePatch

> EntityModelBankAccount createPropertyReferenceExpensePatch(id, collectionModelObject)



patch-bankaccount-by-expense-Id

### Example

```ts
import {
  Configuration,
  ExpensePropertyReferenceControllerApi,
} from '';
import type { CreatePropertyReferenceExpensePatchRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new ExpensePropertyReferenceControllerApi();

  const body = {
    // string
    id: id_example,
    // CollectionModelObject
    collectionModelObject: ...,
  } satisfies CreatePropertyReferenceExpensePatchRequest;

  try {
    const data = await api.createPropertyReferenceExpensePatch(body);
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


## createPropertyReferenceExpensePatch1

> EntityModelCategory createPropertyReferenceExpensePatch1(id, collectionModelObject)



patch-category-by-expense-Id

### Example

```ts
import {
  Configuration,
  ExpensePropertyReferenceControllerApi,
} from '';
import type { CreatePropertyReferenceExpensePatch1Request } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new ExpensePropertyReferenceControllerApi();

  const body = {
    // string
    id: id_example,
    // CollectionModelObject
    collectionModelObject: ...,
  } satisfies CreatePropertyReferenceExpensePatch1Request;

  try {
    const data = await api.createPropertyReferenceExpensePatch1(body);
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


## createPropertyReferenceExpensePatch2

> EntityModelUser createPropertyReferenceExpensePatch2(id, collectionModelObject)



patch-user-by-expense-Id

### Example

```ts
import {
  Configuration,
  ExpensePropertyReferenceControllerApi,
} from '';
import type { CreatePropertyReferenceExpensePatch2Request } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new ExpensePropertyReferenceControllerApi();

  const body = {
    // string
    id: id_example,
    // CollectionModelObject
    collectionModelObject: ...,
  } satisfies CreatePropertyReferenceExpensePatch2Request;

  try {
    const data = await api.createPropertyReferenceExpensePatch2(body);
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


## createPropertyReferenceExpensePut

> EntityModelBankAccount createPropertyReferenceExpensePut(id, collectionModelObject)



update-bankaccount-by-expense-Id

### Example

```ts
import {
  Configuration,
  ExpensePropertyReferenceControllerApi,
} from '';
import type { CreatePropertyReferenceExpensePutRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new ExpensePropertyReferenceControllerApi();

  const body = {
    // string
    id: id_example,
    // CollectionModelObject
    collectionModelObject: ...,
  } satisfies CreatePropertyReferenceExpensePutRequest;

  try {
    const data = await api.createPropertyReferenceExpensePut(body);
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


## createPropertyReferenceExpensePut1

> EntityModelCategory createPropertyReferenceExpensePut1(id, collectionModelObject)



update-category-by-expense-Id

### Example

```ts
import {
  Configuration,
  ExpensePropertyReferenceControllerApi,
} from '';
import type { CreatePropertyReferenceExpensePut1Request } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new ExpensePropertyReferenceControllerApi();

  const body = {
    // string
    id: id_example,
    // CollectionModelObject
    collectionModelObject: ...,
  } satisfies CreatePropertyReferenceExpensePut1Request;

  try {
    const data = await api.createPropertyReferenceExpensePut1(body);
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


## createPropertyReferenceExpensePut2

> EntityModelUser createPropertyReferenceExpensePut2(id, collectionModelObject)



update-user-by-expense-Id

### Example

```ts
import {
  Configuration,
  ExpensePropertyReferenceControllerApi,
} from '';
import type { CreatePropertyReferenceExpensePut2Request } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new ExpensePropertyReferenceControllerApi();

  const body = {
    // string
    id: id_example,
    // CollectionModelObject
    collectionModelObject: ...,
  } satisfies CreatePropertyReferenceExpensePut2Request;

  try {
    const data = await api.createPropertyReferenceExpensePut2(body);
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


## deletePropertyReferenceExpenseDelete

> deletePropertyReferenceExpenseDelete(id)



delete-bankaccount-by-expense-Id

### Example

```ts
import {
  Configuration,
  ExpensePropertyReferenceControllerApi,
} from '';
import type { DeletePropertyReferenceExpenseDeleteRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new ExpensePropertyReferenceControllerApi();

  const body = {
    // string
    id: id_example,
  } satisfies DeletePropertyReferenceExpenseDeleteRequest;

  try {
    const data = await api.deletePropertyReferenceExpenseDelete(body);
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


## deletePropertyReferenceExpenseDelete1

> deletePropertyReferenceExpenseDelete1(id)



delete-category-by-expense-Id

### Example

```ts
import {
  Configuration,
  ExpensePropertyReferenceControllerApi,
} from '';
import type { DeletePropertyReferenceExpenseDelete1Request } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new ExpensePropertyReferenceControllerApi();

  const body = {
    // string
    id: id_example,
  } satisfies DeletePropertyReferenceExpenseDelete1Request;

  try {
    const data = await api.deletePropertyReferenceExpenseDelete1(body);
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


## deletePropertyReferenceExpenseDelete2

> deletePropertyReferenceExpenseDelete2(id)



delete-user-by-expense-Id

### Example

```ts
import {
  Configuration,
  ExpensePropertyReferenceControllerApi,
} from '';
import type { DeletePropertyReferenceExpenseDelete2Request } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new ExpensePropertyReferenceControllerApi();

  const body = {
    // string
    id: id_example,
  } satisfies DeletePropertyReferenceExpenseDelete2Request;

  try {
    const data = await api.deletePropertyReferenceExpenseDelete2(body);
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


## deletePropertyReferenceIdExpenseDelete

> deletePropertyReferenceIdExpenseDelete(id, propertyId)



delete-bankaccount-by-expense-Id

### Example

```ts
import {
  Configuration,
  ExpensePropertyReferenceControllerApi,
} from '';
import type { DeletePropertyReferenceIdExpenseDeleteRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new ExpensePropertyReferenceControllerApi();

  const body = {
    // string
    id: id_example,
    // string
    propertyId: propertyId_example,
  } satisfies DeletePropertyReferenceIdExpenseDeleteRequest;

  try {
    const data = await api.deletePropertyReferenceIdExpenseDelete(body);
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


## deletePropertyReferenceIdExpenseDelete1

> deletePropertyReferenceIdExpenseDelete1(id, propertyId)



delete-category-by-expense-Id

### Example

```ts
import {
  Configuration,
  ExpensePropertyReferenceControllerApi,
} from '';
import type { DeletePropertyReferenceIdExpenseDelete1Request } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new ExpensePropertyReferenceControllerApi();

  const body = {
    // string
    id: id_example,
    // string
    propertyId: propertyId_example,
  } satisfies DeletePropertyReferenceIdExpenseDelete1Request;

  try {
    const data = await api.deletePropertyReferenceIdExpenseDelete1(body);
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


## deletePropertyReferenceIdExpenseDelete2

> deletePropertyReferenceIdExpenseDelete2(id, propertyId)



delete-user-by-expense-Id

### Example

```ts
import {
  Configuration,
  ExpensePropertyReferenceControllerApi,
} from '';
import type { DeletePropertyReferenceIdExpenseDelete2Request } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new ExpensePropertyReferenceControllerApi();

  const body = {
    // string
    id: id_example,
    // string
    propertyId: propertyId_example,
  } satisfies DeletePropertyReferenceIdExpenseDelete2Request;

  try {
    const data = await api.deletePropertyReferenceIdExpenseDelete2(body);
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


## followPropertyReferenceExpenseGet

> EntityModelBankAccount followPropertyReferenceExpenseGet(id, propertyId)



get-bankaccount-by-expense-Id

### Example

```ts
import {
  Configuration,
  ExpensePropertyReferenceControllerApi,
} from '';
import type { FollowPropertyReferenceExpenseGetRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new ExpensePropertyReferenceControllerApi();

  const body = {
    // string
    id: id_example,
    // string
    propertyId: propertyId_example,
  } satisfies FollowPropertyReferenceExpenseGetRequest;

  try {
    const data = await api.followPropertyReferenceExpenseGet(body);
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


## followPropertyReferenceExpenseGet1

> EntityModelBankAccount followPropertyReferenceExpenseGet1(id)



get-bankaccount-by-expense-Id

### Example

```ts
import {
  Configuration,
  ExpensePropertyReferenceControllerApi,
} from '';
import type { FollowPropertyReferenceExpenseGet1Request } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new ExpensePropertyReferenceControllerApi();

  const body = {
    // string
    id: id_example,
  } satisfies FollowPropertyReferenceExpenseGet1Request;

  try {
    const data = await api.followPropertyReferenceExpenseGet1(body);
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


## followPropertyReferenceExpenseGet2

> EntityModelCategory followPropertyReferenceExpenseGet2(id, propertyId)



get-category-by-expense-Id

### Example

```ts
import {
  Configuration,
  ExpensePropertyReferenceControllerApi,
} from '';
import type { FollowPropertyReferenceExpenseGet2Request } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new ExpensePropertyReferenceControllerApi();

  const body = {
    // string
    id: id_example,
    // string
    propertyId: propertyId_example,
  } satisfies FollowPropertyReferenceExpenseGet2Request;

  try {
    const data = await api.followPropertyReferenceExpenseGet2(body);
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


## followPropertyReferenceExpenseGet21

> EntityModelCategory followPropertyReferenceExpenseGet21(id)



get-category-by-expense-Id

### Example

```ts
import {
  Configuration,
  ExpensePropertyReferenceControllerApi,
} from '';
import type { FollowPropertyReferenceExpenseGet21Request } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new ExpensePropertyReferenceControllerApi();

  const body = {
    // string
    id: id_example,
  } satisfies FollowPropertyReferenceExpenseGet21Request;

  try {
    const data = await api.followPropertyReferenceExpenseGet21(body);
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


## followPropertyReferenceExpenseGet3

> EntityModelUser followPropertyReferenceExpenseGet3(id, propertyId)



get-user-by-expense-Id

### Example

```ts
import {
  Configuration,
  ExpensePropertyReferenceControllerApi,
} from '';
import type { FollowPropertyReferenceExpenseGet3Request } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new ExpensePropertyReferenceControllerApi();

  const body = {
    // string
    id: id_example,
    // string
    propertyId: propertyId_example,
  } satisfies FollowPropertyReferenceExpenseGet3Request;

  try {
    const data = await api.followPropertyReferenceExpenseGet3(body);
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


## followPropertyReferenceExpenseGet31

> EntityModelUser followPropertyReferenceExpenseGet31(id)



get-user-by-expense-Id

### Example

```ts
import {
  Configuration,
  ExpensePropertyReferenceControllerApi,
} from '';
import type { FollowPropertyReferenceExpenseGet31Request } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new ExpensePropertyReferenceControllerApi();

  const body = {
    // string
    id: id_example,
  } satisfies FollowPropertyReferenceExpenseGet31Request;

  try {
    const data = await api.followPropertyReferenceExpenseGet31(body);
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


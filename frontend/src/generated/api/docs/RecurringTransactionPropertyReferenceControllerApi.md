# RecurringTransactionPropertyReferenceControllerApi

All URIs are relative to *http://localhost:8080*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**createPropertyReferenceRecurringtransactionPatch**](RecurringTransactionPropertyReferenceControllerApi.md#createpropertyreferencerecurringtransactionpatch) | **PATCH** /recurringTransactions/{id}/bankAccount |  |
| [**createPropertyReferenceRecurringtransactionPatch1**](RecurringTransactionPropertyReferenceControllerApi.md#createpropertyreferencerecurringtransactionpatch1) | **PATCH** /recurringTransactions/{id}/category |  |
| [**createPropertyReferenceRecurringtransactionPatch2**](RecurringTransactionPropertyReferenceControllerApi.md#createpropertyreferencerecurringtransactionpatch2) | **PATCH** /recurringTransactions/{id}/user |  |
| [**createPropertyReferenceRecurringtransactionPut**](RecurringTransactionPropertyReferenceControllerApi.md#createpropertyreferencerecurringtransactionput) | **PUT** /recurringTransactions/{id}/bankAccount |  |
| [**createPropertyReferenceRecurringtransactionPut1**](RecurringTransactionPropertyReferenceControllerApi.md#createpropertyreferencerecurringtransactionput1) | **PUT** /recurringTransactions/{id}/category |  |
| [**createPropertyReferenceRecurringtransactionPut2**](RecurringTransactionPropertyReferenceControllerApi.md#createpropertyreferencerecurringtransactionput2) | **PUT** /recurringTransactions/{id}/user |  |
| [**deletePropertyReferenceIdRecurringtransactionDelete**](RecurringTransactionPropertyReferenceControllerApi.md#deletepropertyreferenceidrecurringtransactiondelete) | **DELETE** /recurringTransactions/{id}/bankAccount/{propertyId} |  |
| [**deletePropertyReferenceIdRecurringtransactionDelete1**](RecurringTransactionPropertyReferenceControllerApi.md#deletepropertyreferenceidrecurringtransactiondelete1) | **DELETE** /recurringTransactions/{id}/category/{propertyId} |  |
| [**deletePropertyReferenceIdRecurringtransactionDelete2**](RecurringTransactionPropertyReferenceControllerApi.md#deletepropertyreferenceidrecurringtransactiondelete2) | **DELETE** /recurringTransactions/{id}/user/{propertyId} |  |
| [**deletePropertyReferenceRecurringtransactionDelete**](RecurringTransactionPropertyReferenceControllerApi.md#deletepropertyreferencerecurringtransactiondelete) | **DELETE** /recurringTransactions/{id}/bankAccount |  |
| [**deletePropertyReferenceRecurringtransactionDelete1**](RecurringTransactionPropertyReferenceControllerApi.md#deletepropertyreferencerecurringtransactiondelete1) | **DELETE** /recurringTransactions/{id}/category |  |
| [**deletePropertyReferenceRecurringtransactionDelete2**](RecurringTransactionPropertyReferenceControllerApi.md#deletepropertyreferencerecurringtransactiondelete2) | **DELETE** /recurringTransactions/{id}/user |  |
| [**followPropertyReferenceRecurringtransactionGet**](RecurringTransactionPropertyReferenceControllerApi.md#followpropertyreferencerecurringtransactionget) | **GET** /recurringTransactions/{id}/bankAccount/{propertyId} |  |
| [**followPropertyReferenceRecurringtransactionGet1**](RecurringTransactionPropertyReferenceControllerApi.md#followpropertyreferencerecurringtransactionget1) | **GET** /recurringTransactions/{id}/bankAccount |  |
| [**followPropertyReferenceRecurringtransactionGet2**](RecurringTransactionPropertyReferenceControllerApi.md#followpropertyreferencerecurringtransactionget2) | **GET** /recurringTransactions/{id}/category/{propertyId} |  |
| [**followPropertyReferenceRecurringtransactionGet21**](RecurringTransactionPropertyReferenceControllerApi.md#followpropertyreferencerecurringtransactionget21) | **GET** /recurringTransactions/{id}/category |  |
| [**followPropertyReferenceRecurringtransactionGet3**](RecurringTransactionPropertyReferenceControllerApi.md#followpropertyreferencerecurringtransactionget3) | **GET** /recurringTransactions/{id}/user/{propertyId} |  |
| [**followPropertyReferenceRecurringtransactionGet31**](RecurringTransactionPropertyReferenceControllerApi.md#followpropertyreferencerecurringtransactionget31) | **GET** /recurringTransactions/{id}/user |  |



## createPropertyReferenceRecurringtransactionPatch

> EntityModelBankAccount createPropertyReferenceRecurringtransactionPatch(id, collectionModelObject)



patch-bankaccount-by-recurringtransaction-Id

### Example

```ts
import {
  Configuration,
  RecurringTransactionPropertyReferenceControllerApi,
} from '';
import type { CreatePropertyReferenceRecurringtransactionPatchRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new RecurringTransactionPropertyReferenceControllerApi();

  const body = {
    // string
    id: id_example,
    // CollectionModelObject
    collectionModelObject: ...,
  } satisfies CreatePropertyReferenceRecurringtransactionPatchRequest;

  try {
    const data = await api.createPropertyReferenceRecurringtransactionPatch(body);
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


## createPropertyReferenceRecurringtransactionPatch1

> EntityModelCategory createPropertyReferenceRecurringtransactionPatch1(id, collectionModelObject)



patch-category-by-recurringtransaction-Id

### Example

```ts
import {
  Configuration,
  RecurringTransactionPropertyReferenceControllerApi,
} from '';
import type { CreatePropertyReferenceRecurringtransactionPatch1Request } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new RecurringTransactionPropertyReferenceControllerApi();

  const body = {
    // string
    id: id_example,
    // CollectionModelObject
    collectionModelObject: ...,
  } satisfies CreatePropertyReferenceRecurringtransactionPatch1Request;

  try {
    const data = await api.createPropertyReferenceRecurringtransactionPatch1(body);
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


## createPropertyReferenceRecurringtransactionPatch2

> EntityModelUser createPropertyReferenceRecurringtransactionPatch2(id, collectionModelObject)



patch-user-by-recurringtransaction-Id

### Example

```ts
import {
  Configuration,
  RecurringTransactionPropertyReferenceControllerApi,
} from '';
import type { CreatePropertyReferenceRecurringtransactionPatch2Request } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new RecurringTransactionPropertyReferenceControllerApi();

  const body = {
    // string
    id: id_example,
    // CollectionModelObject
    collectionModelObject: ...,
  } satisfies CreatePropertyReferenceRecurringtransactionPatch2Request;

  try {
    const data = await api.createPropertyReferenceRecurringtransactionPatch2(body);
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


## createPropertyReferenceRecurringtransactionPut

> EntityModelBankAccount createPropertyReferenceRecurringtransactionPut(id, collectionModelObject)



update-bankaccount-by-recurringtransaction-Id

### Example

```ts
import {
  Configuration,
  RecurringTransactionPropertyReferenceControllerApi,
} from '';
import type { CreatePropertyReferenceRecurringtransactionPutRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new RecurringTransactionPropertyReferenceControllerApi();

  const body = {
    // string
    id: id_example,
    // CollectionModelObject
    collectionModelObject: ...,
  } satisfies CreatePropertyReferenceRecurringtransactionPutRequest;

  try {
    const data = await api.createPropertyReferenceRecurringtransactionPut(body);
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


## createPropertyReferenceRecurringtransactionPut1

> EntityModelCategory createPropertyReferenceRecurringtransactionPut1(id, collectionModelObject)



update-category-by-recurringtransaction-Id

### Example

```ts
import {
  Configuration,
  RecurringTransactionPropertyReferenceControllerApi,
} from '';
import type { CreatePropertyReferenceRecurringtransactionPut1Request } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new RecurringTransactionPropertyReferenceControllerApi();

  const body = {
    // string
    id: id_example,
    // CollectionModelObject
    collectionModelObject: ...,
  } satisfies CreatePropertyReferenceRecurringtransactionPut1Request;

  try {
    const data = await api.createPropertyReferenceRecurringtransactionPut1(body);
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


## createPropertyReferenceRecurringtransactionPut2

> EntityModelUser createPropertyReferenceRecurringtransactionPut2(id, collectionModelObject)



update-user-by-recurringtransaction-Id

### Example

```ts
import {
  Configuration,
  RecurringTransactionPropertyReferenceControllerApi,
} from '';
import type { CreatePropertyReferenceRecurringtransactionPut2Request } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new RecurringTransactionPropertyReferenceControllerApi();

  const body = {
    // string
    id: id_example,
    // CollectionModelObject
    collectionModelObject: ...,
  } satisfies CreatePropertyReferenceRecurringtransactionPut2Request;

  try {
    const data = await api.createPropertyReferenceRecurringtransactionPut2(body);
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


## deletePropertyReferenceIdRecurringtransactionDelete

> deletePropertyReferenceIdRecurringtransactionDelete(id, propertyId)



delete-bankaccount-by-recurringtransaction-Id

### Example

```ts
import {
  Configuration,
  RecurringTransactionPropertyReferenceControllerApi,
} from '';
import type { DeletePropertyReferenceIdRecurringtransactionDeleteRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new RecurringTransactionPropertyReferenceControllerApi();

  const body = {
    // string
    id: id_example,
    // string
    propertyId: propertyId_example,
  } satisfies DeletePropertyReferenceIdRecurringtransactionDeleteRequest;

  try {
    const data = await api.deletePropertyReferenceIdRecurringtransactionDelete(body);
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


## deletePropertyReferenceIdRecurringtransactionDelete1

> deletePropertyReferenceIdRecurringtransactionDelete1(id, propertyId)



delete-category-by-recurringtransaction-Id

### Example

```ts
import {
  Configuration,
  RecurringTransactionPropertyReferenceControllerApi,
} from '';
import type { DeletePropertyReferenceIdRecurringtransactionDelete1Request } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new RecurringTransactionPropertyReferenceControllerApi();

  const body = {
    // string
    id: id_example,
    // string
    propertyId: propertyId_example,
  } satisfies DeletePropertyReferenceIdRecurringtransactionDelete1Request;

  try {
    const data = await api.deletePropertyReferenceIdRecurringtransactionDelete1(body);
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


## deletePropertyReferenceIdRecurringtransactionDelete2

> deletePropertyReferenceIdRecurringtransactionDelete2(id, propertyId)



delete-user-by-recurringtransaction-Id

### Example

```ts
import {
  Configuration,
  RecurringTransactionPropertyReferenceControllerApi,
} from '';
import type { DeletePropertyReferenceIdRecurringtransactionDelete2Request } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new RecurringTransactionPropertyReferenceControllerApi();

  const body = {
    // string
    id: id_example,
    // string
    propertyId: propertyId_example,
  } satisfies DeletePropertyReferenceIdRecurringtransactionDelete2Request;

  try {
    const data = await api.deletePropertyReferenceIdRecurringtransactionDelete2(body);
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


## deletePropertyReferenceRecurringtransactionDelete

> deletePropertyReferenceRecurringtransactionDelete(id)



delete-bankaccount-by-recurringtransaction-Id

### Example

```ts
import {
  Configuration,
  RecurringTransactionPropertyReferenceControllerApi,
} from '';
import type { DeletePropertyReferenceRecurringtransactionDeleteRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new RecurringTransactionPropertyReferenceControllerApi();

  const body = {
    // string
    id: id_example,
  } satisfies DeletePropertyReferenceRecurringtransactionDeleteRequest;

  try {
    const data = await api.deletePropertyReferenceRecurringtransactionDelete(body);
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


## deletePropertyReferenceRecurringtransactionDelete1

> deletePropertyReferenceRecurringtransactionDelete1(id)



delete-category-by-recurringtransaction-Id

### Example

```ts
import {
  Configuration,
  RecurringTransactionPropertyReferenceControllerApi,
} from '';
import type { DeletePropertyReferenceRecurringtransactionDelete1Request } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new RecurringTransactionPropertyReferenceControllerApi();

  const body = {
    // string
    id: id_example,
  } satisfies DeletePropertyReferenceRecurringtransactionDelete1Request;

  try {
    const data = await api.deletePropertyReferenceRecurringtransactionDelete1(body);
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


## deletePropertyReferenceRecurringtransactionDelete2

> deletePropertyReferenceRecurringtransactionDelete2(id)



delete-user-by-recurringtransaction-Id

### Example

```ts
import {
  Configuration,
  RecurringTransactionPropertyReferenceControllerApi,
} from '';
import type { DeletePropertyReferenceRecurringtransactionDelete2Request } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new RecurringTransactionPropertyReferenceControllerApi();

  const body = {
    // string
    id: id_example,
  } satisfies DeletePropertyReferenceRecurringtransactionDelete2Request;

  try {
    const data = await api.deletePropertyReferenceRecurringtransactionDelete2(body);
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


## followPropertyReferenceRecurringtransactionGet

> EntityModelBankAccount followPropertyReferenceRecurringtransactionGet(id, propertyId)



get-bankaccount-by-recurringtransaction-Id

### Example

```ts
import {
  Configuration,
  RecurringTransactionPropertyReferenceControllerApi,
} from '';
import type { FollowPropertyReferenceRecurringtransactionGetRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new RecurringTransactionPropertyReferenceControllerApi();

  const body = {
    // string
    id: id_example,
    // string
    propertyId: propertyId_example,
  } satisfies FollowPropertyReferenceRecurringtransactionGetRequest;

  try {
    const data = await api.followPropertyReferenceRecurringtransactionGet(body);
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


## followPropertyReferenceRecurringtransactionGet1

> EntityModelBankAccount followPropertyReferenceRecurringtransactionGet1(id)



get-bankaccount-by-recurringtransaction-Id

### Example

```ts
import {
  Configuration,
  RecurringTransactionPropertyReferenceControllerApi,
} from '';
import type { FollowPropertyReferenceRecurringtransactionGet1Request } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new RecurringTransactionPropertyReferenceControllerApi();

  const body = {
    // string
    id: id_example,
  } satisfies FollowPropertyReferenceRecurringtransactionGet1Request;

  try {
    const data = await api.followPropertyReferenceRecurringtransactionGet1(body);
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


## followPropertyReferenceRecurringtransactionGet2

> EntityModelCategory followPropertyReferenceRecurringtransactionGet2(id, propertyId)



get-category-by-recurringtransaction-Id

### Example

```ts
import {
  Configuration,
  RecurringTransactionPropertyReferenceControllerApi,
} from '';
import type { FollowPropertyReferenceRecurringtransactionGet2Request } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new RecurringTransactionPropertyReferenceControllerApi();

  const body = {
    // string
    id: id_example,
    // string
    propertyId: propertyId_example,
  } satisfies FollowPropertyReferenceRecurringtransactionGet2Request;

  try {
    const data = await api.followPropertyReferenceRecurringtransactionGet2(body);
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


## followPropertyReferenceRecurringtransactionGet21

> EntityModelCategory followPropertyReferenceRecurringtransactionGet21(id)



get-category-by-recurringtransaction-Id

### Example

```ts
import {
  Configuration,
  RecurringTransactionPropertyReferenceControllerApi,
} from '';
import type { FollowPropertyReferenceRecurringtransactionGet21Request } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new RecurringTransactionPropertyReferenceControllerApi();

  const body = {
    // string
    id: id_example,
  } satisfies FollowPropertyReferenceRecurringtransactionGet21Request;

  try {
    const data = await api.followPropertyReferenceRecurringtransactionGet21(body);
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


## followPropertyReferenceRecurringtransactionGet3

> EntityModelUser followPropertyReferenceRecurringtransactionGet3(id, propertyId)



get-user-by-recurringtransaction-Id

### Example

```ts
import {
  Configuration,
  RecurringTransactionPropertyReferenceControllerApi,
} from '';
import type { FollowPropertyReferenceRecurringtransactionGet3Request } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new RecurringTransactionPropertyReferenceControllerApi();

  const body = {
    // string
    id: id_example,
    // string
    propertyId: propertyId_example,
  } satisfies FollowPropertyReferenceRecurringtransactionGet3Request;

  try {
    const data = await api.followPropertyReferenceRecurringtransactionGet3(body);
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


## followPropertyReferenceRecurringtransactionGet31

> EntityModelUser followPropertyReferenceRecurringtransactionGet31(id)



get-user-by-recurringtransaction-Id

### Example

```ts
import {
  Configuration,
  RecurringTransactionPropertyReferenceControllerApi,
} from '';
import type { FollowPropertyReferenceRecurringtransactionGet31Request } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new RecurringTransactionPropertyReferenceControllerApi();

  const body = {
    // string
    id: id_example,
  } satisfies FollowPropertyReferenceRecurringtransactionGet31Request;

  try {
    const data = await api.followPropertyReferenceRecurringtransactionGet31(body);
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


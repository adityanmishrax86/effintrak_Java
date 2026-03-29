# TransferPropertyReferenceControllerApi

All URIs are relative to *http://localhost:8080*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**createPropertyReferenceTransferPatch**](TransferPropertyReferenceControllerApi.md#createpropertyreferencetransferpatch) | **PATCH** /transfers/{id}/fromAccount |  |
| [**createPropertyReferenceTransferPatch1**](TransferPropertyReferenceControllerApi.md#createpropertyreferencetransferpatch1) | **PATCH** /transfers/{id}/toAccount |  |
| [**createPropertyReferenceTransferPatch2**](TransferPropertyReferenceControllerApi.md#createpropertyreferencetransferpatch2) | **PATCH** /transfers/{id}/user |  |
| [**createPropertyReferenceTransferPut**](TransferPropertyReferenceControllerApi.md#createpropertyreferencetransferput) | **PUT** /transfers/{id}/fromAccount |  |
| [**createPropertyReferenceTransferPut1**](TransferPropertyReferenceControllerApi.md#createpropertyreferencetransferput1) | **PUT** /transfers/{id}/toAccount |  |
| [**createPropertyReferenceTransferPut2**](TransferPropertyReferenceControllerApi.md#createpropertyreferencetransferput2) | **PUT** /transfers/{id}/user |  |
| [**deletePropertyReferenceIdTransferDelete**](TransferPropertyReferenceControllerApi.md#deletepropertyreferenceidtransferdelete) | **DELETE** /transfers/{id}/fromAccount/{propertyId} |  |
| [**deletePropertyReferenceIdTransferDelete1**](TransferPropertyReferenceControllerApi.md#deletepropertyreferenceidtransferdelete1) | **DELETE** /transfers/{id}/toAccount/{propertyId} |  |
| [**deletePropertyReferenceIdTransferDelete2**](TransferPropertyReferenceControllerApi.md#deletepropertyreferenceidtransferdelete2) | **DELETE** /transfers/{id}/user/{propertyId} |  |
| [**deletePropertyReferenceTransferDelete**](TransferPropertyReferenceControllerApi.md#deletepropertyreferencetransferdelete) | **DELETE** /transfers/{id}/fromAccount |  |
| [**deletePropertyReferenceTransferDelete1**](TransferPropertyReferenceControllerApi.md#deletepropertyreferencetransferdelete1) | **DELETE** /transfers/{id}/toAccount |  |
| [**deletePropertyReferenceTransferDelete2**](TransferPropertyReferenceControllerApi.md#deletepropertyreferencetransferdelete2) | **DELETE** /transfers/{id}/user |  |
| [**followPropertyReferenceTransferGet**](TransferPropertyReferenceControllerApi.md#followpropertyreferencetransferget) | **GET** /transfers/{id}/fromAccount/{propertyId} |  |
| [**followPropertyReferenceTransferGet1**](TransferPropertyReferenceControllerApi.md#followpropertyreferencetransferget1) | **GET** /transfers/{id}/fromAccount |  |
| [**followPropertyReferenceTransferGet2**](TransferPropertyReferenceControllerApi.md#followpropertyreferencetransferget2) | **GET** /transfers/{id}/toAccount/{propertyId} |  |
| [**followPropertyReferenceTransferGet21**](TransferPropertyReferenceControllerApi.md#followpropertyreferencetransferget21) | **GET** /transfers/{id}/toAccount |  |
| [**followPropertyReferenceTransferGet3**](TransferPropertyReferenceControllerApi.md#followpropertyreferencetransferget3) | **GET** /transfers/{id}/user/{propertyId} |  |
| [**followPropertyReferenceTransferGet31**](TransferPropertyReferenceControllerApi.md#followpropertyreferencetransferget31) | **GET** /transfers/{id}/user |  |



## createPropertyReferenceTransferPatch

> EntityModelBankAccount createPropertyReferenceTransferPatch(id, collectionModelObject)



patch-bankaccount-by-transfer-Id

### Example

```ts
import {
  Configuration,
  TransferPropertyReferenceControllerApi,
} from '';
import type { CreatePropertyReferenceTransferPatchRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new TransferPropertyReferenceControllerApi();

  const body = {
    // string
    id: id_example,
    // CollectionModelObject
    collectionModelObject: ...,
  } satisfies CreatePropertyReferenceTransferPatchRequest;

  try {
    const data = await api.createPropertyReferenceTransferPatch(body);
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


## createPropertyReferenceTransferPatch1

> EntityModelBankAccount createPropertyReferenceTransferPatch1(id, collectionModelObject)



patch-bankaccount-by-transfer-Id

### Example

```ts
import {
  Configuration,
  TransferPropertyReferenceControllerApi,
} from '';
import type { CreatePropertyReferenceTransferPatch1Request } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new TransferPropertyReferenceControllerApi();

  const body = {
    // string
    id: id_example,
    // CollectionModelObject
    collectionModelObject: ...,
  } satisfies CreatePropertyReferenceTransferPatch1Request;

  try {
    const data = await api.createPropertyReferenceTransferPatch1(body);
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


## createPropertyReferenceTransferPatch2

> EntityModelUser createPropertyReferenceTransferPatch2(id, collectionModelObject)



patch-user-by-transfer-Id

### Example

```ts
import {
  Configuration,
  TransferPropertyReferenceControllerApi,
} from '';
import type { CreatePropertyReferenceTransferPatch2Request } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new TransferPropertyReferenceControllerApi();

  const body = {
    // string
    id: id_example,
    // CollectionModelObject
    collectionModelObject: ...,
  } satisfies CreatePropertyReferenceTransferPatch2Request;

  try {
    const data = await api.createPropertyReferenceTransferPatch2(body);
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


## createPropertyReferenceTransferPut

> EntityModelBankAccount createPropertyReferenceTransferPut(id, collectionModelObject)



update-bankaccount-by-transfer-Id

### Example

```ts
import {
  Configuration,
  TransferPropertyReferenceControllerApi,
} from '';
import type { CreatePropertyReferenceTransferPutRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new TransferPropertyReferenceControllerApi();

  const body = {
    // string
    id: id_example,
    // CollectionModelObject
    collectionModelObject: ...,
  } satisfies CreatePropertyReferenceTransferPutRequest;

  try {
    const data = await api.createPropertyReferenceTransferPut(body);
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


## createPropertyReferenceTransferPut1

> EntityModelBankAccount createPropertyReferenceTransferPut1(id, collectionModelObject)



update-bankaccount-by-transfer-Id

### Example

```ts
import {
  Configuration,
  TransferPropertyReferenceControllerApi,
} from '';
import type { CreatePropertyReferenceTransferPut1Request } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new TransferPropertyReferenceControllerApi();

  const body = {
    // string
    id: id_example,
    // CollectionModelObject
    collectionModelObject: ...,
  } satisfies CreatePropertyReferenceTransferPut1Request;

  try {
    const data = await api.createPropertyReferenceTransferPut1(body);
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


## createPropertyReferenceTransferPut2

> EntityModelUser createPropertyReferenceTransferPut2(id, collectionModelObject)



update-user-by-transfer-Id

### Example

```ts
import {
  Configuration,
  TransferPropertyReferenceControllerApi,
} from '';
import type { CreatePropertyReferenceTransferPut2Request } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new TransferPropertyReferenceControllerApi();

  const body = {
    // string
    id: id_example,
    // CollectionModelObject
    collectionModelObject: ...,
  } satisfies CreatePropertyReferenceTransferPut2Request;

  try {
    const data = await api.createPropertyReferenceTransferPut2(body);
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


## deletePropertyReferenceIdTransferDelete

> deletePropertyReferenceIdTransferDelete(id, propertyId)



delete-bankaccount-by-transfer-Id

### Example

```ts
import {
  Configuration,
  TransferPropertyReferenceControllerApi,
} from '';
import type { DeletePropertyReferenceIdTransferDeleteRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new TransferPropertyReferenceControllerApi();

  const body = {
    // string
    id: id_example,
    // string
    propertyId: propertyId_example,
  } satisfies DeletePropertyReferenceIdTransferDeleteRequest;

  try {
    const data = await api.deletePropertyReferenceIdTransferDelete(body);
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


## deletePropertyReferenceIdTransferDelete1

> deletePropertyReferenceIdTransferDelete1(id, propertyId)



delete-bankaccount-by-transfer-Id

### Example

```ts
import {
  Configuration,
  TransferPropertyReferenceControllerApi,
} from '';
import type { DeletePropertyReferenceIdTransferDelete1Request } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new TransferPropertyReferenceControllerApi();

  const body = {
    // string
    id: id_example,
    // string
    propertyId: propertyId_example,
  } satisfies DeletePropertyReferenceIdTransferDelete1Request;

  try {
    const data = await api.deletePropertyReferenceIdTransferDelete1(body);
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


## deletePropertyReferenceIdTransferDelete2

> deletePropertyReferenceIdTransferDelete2(id, propertyId)



delete-user-by-transfer-Id

### Example

```ts
import {
  Configuration,
  TransferPropertyReferenceControllerApi,
} from '';
import type { DeletePropertyReferenceIdTransferDelete2Request } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new TransferPropertyReferenceControllerApi();

  const body = {
    // string
    id: id_example,
    // string
    propertyId: propertyId_example,
  } satisfies DeletePropertyReferenceIdTransferDelete2Request;

  try {
    const data = await api.deletePropertyReferenceIdTransferDelete2(body);
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


## deletePropertyReferenceTransferDelete

> deletePropertyReferenceTransferDelete(id)



delete-bankaccount-by-transfer-Id

### Example

```ts
import {
  Configuration,
  TransferPropertyReferenceControllerApi,
} from '';
import type { DeletePropertyReferenceTransferDeleteRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new TransferPropertyReferenceControllerApi();

  const body = {
    // string
    id: id_example,
  } satisfies DeletePropertyReferenceTransferDeleteRequest;

  try {
    const data = await api.deletePropertyReferenceTransferDelete(body);
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


## deletePropertyReferenceTransferDelete1

> deletePropertyReferenceTransferDelete1(id)



delete-bankaccount-by-transfer-Id

### Example

```ts
import {
  Configuration,
  TransferPropertyReferenceControllerApi,
} from '';
import type { DeletePropertyReferenceTransferDelete1Request } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new TransferPropertyReferenceControllerApi();

  const body = {
    // string
    id: id_example,
  } satisfies DeletePropertyReferenceTransferDelete1Request;

  try {
    const data = await api.deletePropertyReferenceTransferDelete1(body);
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


## deletePropertyReferenceTransferDelete2

> deletePropertyReferenceTransferDelete2(id)



delete-user-by-transfer-Id

### Example

```ts
import {
  Configuration,
  TransferPropertyReferenceControllerApi,
} from '';
import type { DeletePropertyReferenceTransferDelete2Request } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new TransferPropertyReferenceControllerApi();

  const body = {
    // string
    id: id_example,
  } satisfies DeletePropertyReferenceTransferDelete2Request;

  try {
    const data = await api.deletePropertyReferenceTransferDelete2(body);
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


## followPropertyReferenceTransferGet

> EntityModelBankAccount followPropertyReferenceTransferGet(id, propertyId)



get-bankaccount-by-transfer-Id

### Example

```ts
import {
  Configuration,
  TransferPropertyReferenceControllerApi,
} from '';
import type { FollowPropertyReferenceTransferGetRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new TransferPropertyReferenceControllerApi();

  const body = {
    // string
    id: id_example,
    // string
    propertyId: propertyId_example,
  } satisfies FollowPropertyReferenceTransferGetRequest;

  try {
    const data = await api.followPropertyReferenceTransferGet(body);
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


## followPropertyReferenceTransferGet1

> EntityModelBankAccount followPropertyReferenceTransferGet1(id)



get-bankaccount-by-transfer-Id

### Example

```ts
import {
  Configuration,
  TransferPropertyReferenceControllerApi,
} from '';
import type { FollowPropertyReferenceTransferGet1Request } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new TransferPropertyReferenceControllerApi();

  const body = {
    // string
    id: id_example,
  } satisfies FollowPropertyReferenceTransferGet1Request;

  try {
    const data = await api.followPropertyReferenceTransferGet1(body);
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


## followPropertyReferenceTransferGet2

> EntityModelBankAccount followPropertyReferenceTransferGet2(id, propertyId)



get-bankaccount-by-transfer-Id

### Example

```ts
import {
  Configuration,
  TransferPropertyReferenceControllerApi,
} from '';
import type { FollowPropertyReferenceTransferGet2Request } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new TransferPropertyReferenceControllerApi();

  const body = {
    // string
    id: id_example,
    // string
    propertyId: propertyId_example,
  } satisfies FollowPropertyReferenceTransferGet2Request;

  try {
    const data = await api.followPropertyReferenceTransferGet2(body);
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


## followPropertyReferenceTransferGet21

> EntityModelBankAccount followPropertyReferenceTransferGet21(id)



get-bankaccount-by-transfer-Id

### Example

```ts
import {
  Configuration,
  TransferPropertyReferenceControllerApi,
} from '';
import type { FollowPropertyReferenceTransferGet21Request } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new TransferPropertyReferenceControllerApi();

  const body = {
    // string
    id: id_example,
  } satisfies FollowPropertyReferenceTransferGet21Request;

  try {
    const data = await api.followPropertyReferenceTransferGet21(body);
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


## followPropertyReferenceTransferGet3

> EntityModelUser followPropertyReferenceTransferGet3(id, propertyId)



get-user-by-transfer-Id

### Example

```ts
import {
  Configuration,
  TransferPropertyReferenceControllerApi,
} from '';
import type { FollowPropertyReferenceTransferGet3Request } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new TransferPropertyReferenceControllerApi();

  const body = {
    // string
    id: id_example,
    // string
    propertyId: propertyId_example,
  } satisfies FollowPropertyReferenceTransferGet3Request;

  try {
    const data = await api.followPropertyReferenceTransferGet3(body);
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


## followPropertyReferenceTransferGet31

> EntityModelUser followPropertyReferenceTransferGet31(id)



get-user-by-transfer-Id

### Example

```ts
import {
  Configuration,
  TransferPropertyReferenceControllerApi,
} from '';
import type { FollowPropertyReferenceTransferGet31Request } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new TransferPropertyReferenceControllerApi();

  const body = {
    // string
    id: id_example,
  } satisfies FollowPropertyReferenceTransferGet31Request;

  try {
    const data = await api.followPropertyReferenceTransferGet31(body);
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


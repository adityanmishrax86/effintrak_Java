# CreditEntityControllerApi

All URIs are relative to *http://localhost:8080*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**deleteItemResourceCreditDelete**](CreditEntityControllerApi.md#deleteitemresourcecreditdelete) | **DELETE** /credits/{id} |  |
| [**getCollectionResourceCreditGet1**](CreditEntityControllerApi.md#getcollectionresourcecreditget1) | **GET** /credits |  |
| [**getItemResourceCreditGet**](CreditEntityControllerApi.md#getitemresourcecreditget) | **GET** /credits/{id} |  |
| [**patchItemResourceCreditPatch**](CreditEntityControllerApi.md#patchitemresourcecreditpatch) | **PATCH** /credits/{id} |  |
| [**postCollectionResourceCreditPost**](CreditEntityControllerApi.md#postcollectionresourcecreditpost) | **POST** /credits |  |
| [**putItemResourceCreditPut**](CreditEntityControllerApi.md#putitemresourcecreditput) | **PUT** /credits/{id} |  |



## deleteItemResourceCreditDelete

> deleteItemResourceCreditDelete(id)



delete-credit

### Example

```ts
import {
  Configuration,
  CreditEntityControllerApi,
} from '';
import type { DeleteItemResourceCreditDeleteRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new CreditEntityControllerApi();

  const body = {
    // string
    id: id_example,
  } satisfies DeleteItemResourceCreditDeleteRequest;

  try {
    const data = await api.deleteItemResourceCreditDelete(body);
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


## getCollectionResourceCreditGet1

> PagedModelEntityModelCredit getCollectionResourceCreditGet1(page, size, sort)



get-credit

### Example

```ts
import {
  Configuration,
  CreditEntityControllerApi,
} from '';
import type { GetCollectionResourceCreditGet1Request } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new CreditEntityControllerApi();

  const body = {
    // number | Zero-based page index (0..N) (optional)
    page: 56,
    // number | The size of the page to be returned (optional)
    size: 56,
    // Array<string> | Sorting criteria in the format: property,(asc|desc). Default sort order is ascending. Multiple sort criteria are supported. (optional)
    sort: ...,
  } satisfies GetCollectionResourceCreditGet1Request;

  try {
    const data = await api.getCollectionResourceCreditGet1(body);
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
| **page** | `number` | Zero-based page index (0..N) | [Optional] [Defaults to `0`] |
| **size** | `number` | The size of the page to be returned | [Optional] [Defaults to `20`] |
| **sort** | `Array<string>` | Sorting criteria in the format: property,(asc|desc). Default sort order is ascending. Multiple sort criteria are supported. | [Optional] |

### Return type

[**PagedModelEntityModelCredit**](PagedModelEntityModelCredit.md)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: `application/hal+json`, `application/x-spring-data-compact+json`, `text/uri-list`


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | OK |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#api-endpoints) [[Back to Model list]](../README.md#models) [[Back to README]](../README.md)


## getItemResourceCreditGet

> EntityModelCredit getItemResourceCreditGet(id)



get-credit

### Example

```ts
import {
  Configuration,
  CreditEntityControllerApi,
} from '';
import type { GetItemResourceCreditGetRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new CreditEntityControllerApi();

  const body = {
    // string
    id: id_example,
  } satisfies GetItemResourceCreditGetRequest;

  try {
    const data = await api.getItemResourceCreditGet(body);
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

[**EntityModelCredit**](EntityModelCredit.md)

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


## patchItemResourceCreditPatch

> EntityModelCredit patchItemResourceCreditPatch(id, creditRequestBody)



patch-credit

### Example

```ts
import {
  Configuration,
  CreditEntityControllerApi,
} from '';
import type { PatchItemResourceCreditPatchRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new CreditEntityControllerApi();

  const body = {
    // string
    id: id_example,
    // CreditRequestBody
    creditRequestBody: ...,
  } satisfies PatchItemResourceCreditPatchRequest;

  try {
    const data = await api.patchItemResourceCreditPatch(body);
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
| **creditRequestBody** | [CreditRequestBody](CreditRequestBody.md) |  | |

### Return type

[**EntityModelCredit**](EntityModelCredit.md)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: `application/json`
- **Accept**: `application/hal+json`


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | OK |  -  |
| **204** | No Content |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#api-endpoints) [[Back to Model list]](../README.md#models) [[Back to README]](../README.md)


## postCollectionResourceCreditPost

> EntityModelCredit postCollectionResourceCreditPost(creditRequestBody)



create-credit

### Example

```ts
import {
  Configuration,
  CreditEntityControllerApi,
} from '';
import type { PostCollectionResourceCreditPostRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new CreditEntityControllerApi();

  const body = {
    // CreditRequestBody
    creditRequestBody: ...,
  } satisfies PostCollectionResourceCreditPostRequest;

  try {
    const data = await api.postCollectionResourceCreditPost(body);
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
| **creditRequestBody** | [CreditRequestBody](CreditRequestBody.md) |  | |

### Return type

[**EntityModelCredit**](EntityModelCredit.md)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: `application/json`
- **Accept**: `application/hal+json`


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **201** | Created |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#api-endpoints) [[Back to Model list]](../README.md#models) [[Back to README]](../README.md)


## putItemResourceCreditPut

> EntityModelCredit putItemResourceCreditPut(id, creditRequestBody)



update-credit

### Example

```ts
import {
  Configuration,
  CreditEntityControllerApi,
} from '';
import type { PutItemResourceCreditPutRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new CreditEntityControllerApi();

  const body = {
    // string
    id: id_example,
    // CreditRequestBody
    creditRequestBody: ...,
  } satisfies PutItemResourceCreditPutRequest;

  try {
    const data = await api.putItemResourceCreditPut(body);
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
| **creditRequestBody** | [CreditRequestBody](CreditRequestBody.md) |  | |

### Return type

[**EntityModelCredit**](EntityModelCredit.md)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: `application/json`
- **Accept**: `application/hal+json`


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | OK |  -  |
| **201** | Created |  -  |
| **204** | No Content |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#api-endpoints) [[Back to Model list]](../README.md#models) [[Back to README]](../README.md)


# RecurringTransactionEntityControllerApi

All URIs are relative to *http://localhost:8080*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**deleteItemResourceRecurringtransactionDelete**](RecurringTransactionEntityControllerApi.md#deleteitemresourcerecurringtransactiondelete) | **DELETE** /recurringTransactions/{id} |  |
| [**getCollectionResourceRecurringtransactionGet1**](RecurringTransactionEntityControllerApi.md#getcollectionresourcerecurringtransactionget1) | **GET** /recurringTransactions |  |
| [**getItemResourceRecurringtransactionGet**](RecurringTransactionEntityControllerApi.md#getitemresourcerecurringtransactionget) | **GET** /recurringTransactions/{id} |  |
| [**patchItemResourceRecurringtransactionPatch**](RecurringTransactionEntityControllerApi.md#patchitemresourcerecurringtransactionpatch) | **PATCH** /recurringTransactions/{id} |  |
| [**postCollectionResourceRecurringtransactionPost**](RecurringTransactionEntityControllerApi.md#postcollectionresourcerecurringtransactionpost) | **POST** /recurringTransactions |  |
| [**putItemResourceRecurringtransactionPut**](RecurringTransactionEntityControllerApi.md#putitemresourcerecurringtransactionput) | **PUT** /recurringTransactions/{id} |  |



## deleteItemResourceRecurringtransactionDelete

> deleteItemResourceRecurringtransactionDelete(id)



delete-recurringtransaction

### Example

```ts
import {
  Configuration,
  RecurringTransactionEntityControllerApi,
} from '';
import type { DeleteItemResourceRecurringtransactionDeleteRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new RecurringTransactionEntityControllerApi();

  const body = {
    // string
    id: id_example,
  } satisfies DeleteItemResourceRecurringtransactionDeleteRequest;

  try {
    const data = await api.deleteItemResourceRecurringtransactionDelete(body);
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


## getCollectionResourceRecurringtransactionGet1

> PagedModelEntityModelRecurringTransaction getCollectionResourceRecurringtransactionGet1(page, size, sort)



get-recurringtransaction

### Example

```ts
import {
  Configuration,
  RecurringTransactionEntityControllerApi,
} from '';
import type { GetCollectionResourceRecurringtransactionGet1Request } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new RecurringTransactionEntityControllerApi();

  const body = {
    // number | Zero-based page index (0..N) (optional)
    page: 56,
    // number | The size of the page to be returned (optional)
    size: 56,
    // Array<string> | Sorting criteria in the format: property,(asc|desc). Default sort order is ascending. Multiple sort criteria are supported. (optional)
    sort: ...,
  } satisfies GetCollectionResourceRecurringtransactionGet1Request;

  try {
    const data = await api.getCollectionResourceRecurringtransactionGet1(body);
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

[**PagedModelEntityModelRecurringTransaction**](PagedModelEntityModelRecurringTransaction.md)

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


## getItemResourceRecurringtransactionGet

> EntityModelRecurringTransaction getItemResourceRecurringtransactionGet(id)



get-recurringtransaction

### Example

```ts
import {
  Configuration,
  RecurringTransactionEntityControllerApi,
} from '';
import type { GetItemResourceRecurringtransactionGetRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new RecurringTransactionEntityControllerApi();

  const body = {
    // string
    id: id_example,
  } satisfies GetItemResourceRecurringtransactionGetRequest;

  try {
    const data = await api.getItemResourceRecurringtransactionGet(body);
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

[**EntityModelRecurringTransaction**](EntityModelRecurringTransaction.md)

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


## patchItemResourceRecurringtransactionPatch

> EntityModelRecurringTransaction patchItemResourceRecurringtransactionPatch(id, recurringTransactionRequestBody)



patch-recurringtransaction

### Example

```ts
import {
  Configuration,
  RecurringTransactionEntityControllerApi,
} from '';
import type { PatchItemResourceRecurringtransactionPatchRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new RecurringTransactionEntityControllerApi();

  const body = {
    // string
    id: id_example,
    // RecurringTransactionRequestBody
    recurringTransactionRequestBody: ...,
  } satisfies PatchItemResourceRecurringtransactionPatchRequest;

  try {
    const data = await api.patchItemResourceRecurringtransactionPatch(body);
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
| **recurringTransactionRequestBody** | [RecurringTransactionRequestBody](RecurringTransactionRequestBody.md) |  | |

### Return type

[**EntityModelRecurringTransaction**](EntityModelRecurringTransaction.md)

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


## postCollectionResourceRecurringtransactionPost

> EntityModelRecurringTransaction postCollectionResourceRecurringtransactionPost(recurringTransactionRequestBody)



create-recurringtransaction

### Example

```ts
import {
  Configuration,
  RecurringTransactionEntityControllerApi,
} from '';
import type { PostCollectionResourceRecurringtransactionPostRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new RecurringTransactionEntityControllerApi();

  const body = {
    // RecurringTransactionRequestBody
    recurringTransactionRequestBody: ...,
  } satisfies PostCollectionResourceRecurringtransactionPostRequest;

  try {
    const data = await api.postCollectionResourceRecurringtransactionPost(body);
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
| **recurringTransactionRequestBody** | [RecurringTransactionRequestBody](RecurringTransactionRequestBody.md) |  | |

### Return type

[**EntityModelRecurringTransaction**](EntityModelRecurringTransaction.md)

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


## putItemResourceRecurringtransactionPut

> EntityModelRecurringTransaction putItemResourceRecurringtransactionPut(id, recurringTransactionRequestBody)



update-recurringtransaction

### Example

```ts
import {
  Configuration,
  RecurringTransactionEntityControllerApi,
} from '';
import type { PutItemResourceRecurringtransactionPutRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new RecurringTransactionEntityControllerApi();

  const body = {
    // string
    id: id_example,
    // RecurringTransactionRequestBody
    recurringTransactionRequestBody: ...,
  } satisfies PutItemResourceRecurringtransactionPutRequest;

  try {
    const data = await api.putItemResourceRecurringtransactionPut(body);
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
| **recurringTransactionRequestBody** | [RecurringTransactionRequestBody](RecurringTransactionRequestBody.md) |  | |

### Return type

[**EntityModelRecurringTransaction**](EntityModelRecurringTransaction.md)

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


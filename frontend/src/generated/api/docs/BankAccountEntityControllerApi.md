# BankAccountEntityControllerApi

All URIs are relative to *http://localhost:8080*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**deleteItemResourceBankaccountDelete**](BankAccountEntityControllerApi.md#deleteitemresourcebankaccountdelete) | **DELETE** /bankAccounts/{id} |  |
| [**getCollectionResourceBankaccountGet1**](BankAccountEntityControllerApi.md#getcollectionresourcebankaccountget1) | **GET** /bankAccounts |  |
| [**getItemResourceBankaccountGet**](BankAccountEntityControllerApi.md#getitemresourcebankaccountget) | **GET** /bankAccounts/{id} |  |
| [**patchItemResourceBankaccountPatch**](BankAccountEntityControllerApi.md#patchitemresourcebankaccountpatch) | **PATCH** /bankAccounts/{id} |  |
| [**postCollectionResourceBankaccountPost**](BankAccountEntityControllerApi.md#postcollectionresourcebankaccountpost) | **POST** /bankAccounts |  |
| [**putItemResourceBankaccountPut**](BankAccountEntityControllerApi.md#putitemresourcebankaccountput) | **PUT** /bankAccounts/{id} |  |



## deleteItemResourceBankaccountDelete

> deleteItemResourceBankaccountDelete(id)



delete-bankaccount

### Example

```ts
import {
  Configuration,
  BankAccountEntityControllerApi,
} from '';
import type { DeleteItemResourceBankaccountDeleteRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new BankAccountEntityControllerApi();

  const body = {
    // string
    id: id_example,
  } satisfies DeleteItemResourceBankaccountDeleteRequest;

  try {
    const data = await api.deleteItemResourceBankaccountDelete(body);
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


## getCollectionResourceBankaccountGet1

> PagedModelEntityModelBankAccount getCollectionResourceBankaccountGet1(page, size, sort)



get-bankaccount

### Example

```ts
import {
  Configuration,
  BankAccountEntityControllerApi,
} from '';
import type { GetCollectionResourceBankaccountGet1Request } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new BankAccountEntityControllerApi();

  const body = {
    // number | Zero-based page index (0..N) (optional)
    page: 56,
    // number | The size of the page to be returned (optional)
    size: 56,
    // Array<string> | Sorting criteria in the format: property,(asc|desc). Default sort order is ascending. Multiple sort criteria are supported. (optional)
    sort: ...,
  } satisfies GetCollectionResourceBankaccountGet1Request;

  try {
    const data = await api.getCollectionResourceBankaccountGet1(body);
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

[**PagedModelEntityModelBankAccount**](PagedModelEntityModelBankAccount.md)

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


## getItemResourceBankaccountGet

> EntityModelBankAccount getItemResourceBankaccountGet(id)



get-bankaccount

### Example

```ts
import {
  Configuration,
  BankAccountEntityControllerApi,
} from '';
import type { GetItemResourceBankaccountGetRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new BankAccountEntityControllerApi();

  const body = {
    // string
    id: id_example,
  } satisfies GetItemResourceBankaccountGetRequest;

  try {
    const data = await api.getItemResourceBankaccountGet(body);
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
- **Accept**: `application/hal+json`


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | OK |  -  |
| **404** | Not Found |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#api-endpoints) [[Back to Model list]](../README.md#models) [[Back to README]](../README.md)


## patchItemResourceBankaccountPatch

> EntityModelBankAccount patchItemResourceBankaccountPatch(id, bankAccountRequestBody)



patch-bankaccount

### Example

```ts
import {
  Configuration,
  BankAccountEntityControllerApi,
} from '';
import type { PatchItemResourceBankaccountPatchRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new BankAccountEntityControllerApi();

  const body = {
    // string
    id: id_example,
    // BankAccountRequestBody
    bankAccountRequestBody: ...,
  } satisfies PatchItemResourceBankaccountPatchRequest;

  try {
    const data = await api.patchItemResourceBankaccountPatch(body);
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
| **bankAccountRequestBody** | [BankAccountRequestBody](BankAccountRequestBody.md) |  | |

### Return type

[**EntityModelBankAccount**](EntityModelBankAccount.md)

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


## postCollectionResourceBankaccountPost

> EntityModelBankAccount postCollectionResourceBankaccountPost(bankAccountRequestBody)



create-bankaccount

### Example

```ts
import {
  Configuration,
  BankAccountEntityControllerApi,
} from '';
import type { PostCollectionResourceBankaccountPostRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new BankAccountEntityControllerApi();

  const body = {
    // BankAccountRequestBody
    bankAccountRequestBody: ...,
  } satisfies PostCollectionResourceBankaccountPostRequest;

  try {
    const data = await api.postCollectionResourceBankaccountPost(body);
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
| **bankAccountRequestBody** | [BankAccountRequestBody](BankAccountRequestBody.md) |  | |

### Return type

[**EntityModelBankAccount**](EntityModelBankAccount.md)

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


## putItemResourceBankaccountPut

> EntityModelBankAccount putItemResourceBankaccountPut(id, bankAccountRequestBody)



update-bankaccount

### Example

```ts
import {
  Configuration,
  BankAccountEntityControllerApi,
} from '';
import type { PutItemResourceBankaccountPutRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new BankAccountEntityControllerApi();

  const body = {
    // string
    id: id_example,
    // BankAccountRequestBody
    bankAccountRequestBody: ...,
  } satisfies PutItemResourceBankaccountPutRequest;

  try {
    const data = await api.putItemResourceBankaccountPut(body);
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
| **bankAccountRequestBody** | [BankAccountRequestBody](BankAccountRequestBody.md) |  | |

### Return type

[**EntityModelBankAccount**](EntityModelBankAccount.md)

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


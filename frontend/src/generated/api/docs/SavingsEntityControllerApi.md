# SavingsEntityControllerApi

All URIs are relative to *http://localhost:8080*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**deleteItemResourceSavingsDelete**](SavingsEntityControllerApi.md#deleteitemresourcesavingsdelete) | **DELETE** /savingses/{id} |  |
| [**getCollectionResourceSavingsGet1**](SavingsEntityControllerApi.md#getcollectionresourcesavingsget1) | **GET** /savingses |  |
| [**getItemResourceSavingsGet**](SavingsEntityControllerApi.md#getitemresourcesavingsget) | **GET** /savingses/{id} |  |
| [**patchItemResourceSavingsPatch**](SavingsEntityControllerApi.md#patchitemresourcesavingspatch) | **PATCH** /savingses/{id} |  |
| [**postCollectionResourceSavingsPost**](SavingsEntityControllerApi.md#postcollectionresourcesavingspost) | **POST** /savingses |  |
| [**putItemResourceSavingsPut**](SavingsEntityControllerApi.md#putitemresourcesavingsput) | **PUT** /savingses/{id} |  |



## deleteItemResourceSavingsDelete

> deleteItemResourceSavingsDelete(id)



delete-savings

### Example

```ts
import {
  Configuration,
  SavingsEntityControllerApi,
} from '';
import type { DeleteItemResourceSavingsDeleteRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new SavingsEntityControllerApi();

  const body = {
    // string
    id: id_example,
  } satisfies DeleteItemResourceSavingsDeleteRequest;

  try {
    const data = await api.deleteItemResourceSavingsDelete(body);
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


## getCollectionResourceSavingsGet1

> PagedModelEntityModelSavings getCollectionResourceSavingsGet1(page, size, sort)



get-savings

### Example

```ts
import {
  Configuration,
  SavingsEntityControllerApi,
} from '';
import type { GetCollectionResourceSavingsGet1Request } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new SavingsEntityControllerApi();

  const body = {
    // number | Zero-based page index (0..N) (optional)
    page: 56,
    // number | The size of the page to be returned (optional)
    size: 56,
    // Array<string> | Sorting criteria in the format: property,(asc|desc). Default sort order is ascending. Multiple sort criteria are supported. (optional)
    sort: ...,
  } satisfies GetCollectionResourceSavingsGet1Request;

  try {
    const data = await api.getCollectionResourceSavingsGet1(body);
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

[**PagedModelEntityModelSavings**](PagedModelEntityModelSavings.md)

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


## getItemResourceSavingsGet

> EntityModelSavings getItemResourceSavingsGet(id)



get-savings

### Example

```ts
import {
  Configuration,
  SavingsEntityControllerApi,
} from '';
import type { GetItemResourceSavingsGetRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new SavingsEntityControllerApi();

  const body = {
    // string
    id: id_example,
  } satisfies GetItemResourceSavingsGetRequest;

  try {
    const data = await api.getItemResourceSavingsGet(body);
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

[**EntityModelSavings**](EntityModelSavings.md)

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


## patchItemResourceSavingsPatch

> EntityModelSavings patchItemResourceSavingsPatch(id, savingsRequestBody)



patch-savings

### Example

```ts
import {
  Configuration,
  SavingsEntityControllerApi,
} from '';
import type { PatchItemResourceSavingsPatchRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new SavingsEntityControllerApi();

  const body = {
    // string
    id: id_example,
    // SavingsRequestBody
    savingsRequestBody: ...,
  } satisfies PatchItemResourceSavingsPatchRequest;

  try {
    const data = await api.patchItemResourceSavingsPatch(body);
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
| **savingsRequestBody** | [SavingsRequestBody](SavingsRequestBody.md) |  | |

### Return type

[**EntityModelSavings**](EntityModelSavings.md)

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


## postCollectionResourceSavingsPost

> EntityModelSavings postCollectionResourceSavingsPost(savingsRequestBody)



create-savings

### Example

```ts
import {
  Configuration,
  SavingsEntityControllerApi,
} from '';
import type { PostCollectionResourceSavingsPostRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new SavingsEntityControllerApi();

  const body = {
    // SavingsRequestBody
    savingsRequestBody: ...,
  } satisfies PostCollectionResourceSavingsPostRequest;

  try {
    const data = await api.postCollectionResourceSavingsPost(body);
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
| **savingsRequestBody** | [SavingsRequestBody](SavingsRequestBody.md) |  | |

### Return type

[**EntityModelSavings**](EntityModelSavings.md)

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


## putItemResourceSavingsPut

> EntityModelSavings putItemResourceSavingsPut(id, savingsRequestBody)



update-savings

### Example

```ts
import {
  Configuration,
  SavingsEntityControllerApi,
} from '';
import type { PutItemResourceSavingsPutRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new SavingsEntityControllerApi();

  const body = {
    // string
    id: id_example,
    // SavingsRequestBody
    savingsRequestBody: ...,
  } satisfies PutItemResourceSavingsPutRequest;

  try {
    const data = await api.putItemResourceSavingsPut(body);
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
| **savingsRequestBody** | [SavingsRequestBody](SavingsRequestBody.md) |  | |

### Return type

[**EntityModelSavings**](EntityModelSavings.md)

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


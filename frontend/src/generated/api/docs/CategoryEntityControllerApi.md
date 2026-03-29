# CategoryEntityControllerApi

All URIs are relative to *http://localhost:8080*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**deleteItemResourceCategoryDelete**](CategoryEntityControllerApi.md#deleteitemresourcecategorydelete) | **DELETE** /categories/{id} |  |
| [**getCollectionResourceCategoryGet1**](CategoryEntityControllerApi.md#getcollectionresourcecategoryget1) | **GET** /categories |  |
| [**getItemResourceCategoryGet**](CategoryEntityControllerApi.md#getitemresourcecategoryget) | **GET** /categories/{id} |  |
| [**patchItemResourceCategoryPatch**](CategoryEntityControllerApi.md#patchitemresourcecategorypatch) | **PATCH** /categories/{id} |  |
| [**postCollectionResourceCategoryPost**](CategoryEntityControllerApi.md#postcollectionresourcecategorypost) | **POST** /categories |  |
| [**putItemResourceCategoryPut**](CategoryEntityControllerApi.md#putitemresourcecategoryput) | **PUT** /categories/{id} |  |



## deleteItemResourceCategoryDelete

> deleteItemResourceCategoryDelete(id)



delete-category

### Example

```ts
import {
  Configuration,
  CategoryEntityControllerApi,
} from '';
import type { DeleteItemResourceCategoryDeleteRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new CategoryEntityControllerApi();

  const body = {
    // string
    id: id_example,
  } satisfies DeleteItemResourceCategoryDeleteRequest;

  try {
    const data = await api.deleteItemResourceCategoryDelete(body);
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


## getCollectionResourceCategoryGet1

> PagedModelEntityModelCategory getCollectionResourceCategoryGet1(page, size, sort)



get-category

### Example

```ts
import {
  Configuration,
  CategoryEntityControllerApi,
} from '';
import type { GetCollectionResourceCategoryGet1Request } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new CategoryEntityControllerApi();

  const body = {
    // number | Zero-based page index (0..N) (optional)
    page: 56,
    // number | The size of the page to be returned (optional)
    size: 56,
    // Array<string> | Sorting criteria in the format: property,(asc|desc). Default sort order is ascending. Multiple sort criteria are supported. (optional)
    sort: ...,
  } satisfies GetCollectionResourceCategoryGet1Request;

  try {
    const data = await api.getCollectionResourceCategoryGet1(body);
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

[**PagedModelEntityModelCategory**](PagedModelEntityModelCategory.md)

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


## getItemResourceCategoryGet

> EntityModelCategory getItemResourceCategoryGet(id)



get-category

### Example

```ts
import {
  Configuration,
  CategoryEntityControllerApi,
} from '';
import type { GetItemResourceCategoryGetRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new CategoryEntityControllerApi();

  const body = {
    // string
    id: id_example,
  } satisfies GetItemResourceCategoryGetRequest;

  try {
    const data = await api.getItemResourceCategoryGet(body);
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
- **Accept**: `application/hal+json`


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | OK |  -  |
| **404** | Not Found |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#api-endpoints) [[Back to Model list]](../README.md#models) [[Back to README]](../README.md)


## patchItemResourceCategoryPatch

> EntityModelCategory patchItemResourceCategoryPatch(id, categoryRequestBody)



patch-category

### Example

```ts
import {
  Configuration,
  CategoryEntityControllerApi,
} from '';
import type { PatchItemResourceCategoryPatchRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new CategoryEntityControllerApi();

  const body = {
    // string
    id: id_example,
    // CategoryRequestBody
    categoryRequestBody: ...,
  } satisfies PatchItemResourceCategoryPatchRequest;

  try {
    const data = await api.patchItemResourceCategoryPatch(body);
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
| **categoryRequestBody** | [CategoryRequestBody](CategoryRequestBody.md) |  | |

### Return type

[**EntityModelCategory**](EntityModelCategory.md)

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


## postCollectionResourceCategoryPost

> EntityModelCategory postCollectionResourceCategoryPost(categoryRequestBody)



create-category

### Example

```ts
import {
  Configuration,
  CategoryEntityControllerApi,
} from '';
import type { PostCollectionResourceCategoryPostRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new CategoryEntityControllerApi();

  const body = {
    // CategoryRequestBody
    categoryRequestBody: ...,
  } satisfies PostCollectionResourceCategoryPostRequest;

  try {
    const data = await api.postCollectionResourceCategoryPost(body);
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
| **categoryRequestBody** | [CategoryRequestBody](CategoryRequestBody.md) |  | |

### Return type

[**EntityModelCategory**](EntityModelCategory.md)

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


## putItemResourceCategoryPut

> EntityModelCategory putItemResourceCategoryPut(id, categoryRequestBody)



update-category

### Example

```ts
import {
  Configuration,
  CategoryEntityControllerApi,
} from '';
import type { PutItemResourceCategoryPutRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new CategoryEntityControllerApi();

  const body = {
    // string
    id: id_example,
    // CategoryRequestBody
    categoryRequestBody: ...,
  } satisfies PutItemResourceCategoryPutRequest;

  try {
    const data = await api.putItemResourceCategoryPut(body);
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
| **categoryRequestBody** | [CategoryRequestBody](CategoryRequestBody.md) |  | |

### Return type

[**EntityModelCategory**](EntityModelCategory.md)

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


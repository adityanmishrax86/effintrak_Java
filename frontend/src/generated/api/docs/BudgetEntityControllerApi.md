# BudgetEntityControllerApi

All URIs are relative to *http://localhost:8080*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**deleteItemResourceBudgetDelete**](BudgetEntityControllerApi.md#deleteitemresourcebudgetdelete) | **DELETE** /budgets/{id} |  |
| [**getCollectionResourceBudgetGet1**](BudgetEntityControllerApi.md#getcollectionresourcebudgetget1) | **GET** /budgets |  |
| [**getItemResourceBudgetGet**](BudgetEntityControllerApi.md#getitemresourcebudgetget) | **GET** /budgets/{id} |  |
| [**patchItemResourceBudgetPatch**](BudgetEntityControllerApi.md#patchitemresourcebudgetpatch) | **PATCH** /budgets/{id} |  |
| [**postCollectionResourceBudgetPost**](BudgetEntityControllerApi.md#postcollectionresourcebudgetpost) | **POST** /budgets |  |
| [**putItemResourceBudgetPut**](BudgetEntityControllerApi.md#putitemresourcebudgetput) | **PUT** /budgets/{id} |  |



## deleteItemResourceBudgetDelete

> deleteItemResourceBudgetDelete(id)



delete-budget

### Example

```ts
import {
  Configuration,
  BudgetEntityControllerApi,
} from '';
import type { DeleteItemResourceBudgetDeleteRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new BudgetEntityControllerApi();

  const body = {
    // string
    id: id_example,
  } satisfies DeleteItemResourceBudgetDeleteRequest;

  try {
    const data = await api.deleteItemResourceBudgetDelete(body);
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


## getCollectionResourceBudgetGet1

> PagedModelEntityModelBudget getCollectionResourceBudgetGet1(page, size, sort)



get-budget

### Example

```ts
import {
  Configuration,
  BudgetEntityControllerApi,
} from '';
import type { GetCollectionResourceBudgetGet1Request } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new BudgetEntityControllerApi();

  const body = {
    // number | Zero-based page index (0..N) (optional)
    page: 56,
    // number | The size of the page to be returned (optional)
    size: 56,
    // Array<string> | Sorting criteria in the format: property,(asc|desc). Default sort order is ascending. Multiple sort criteria are supported. (optional)
    sort: ...,
  } satisfies GetCollectionResourceBudgetGet1Request;

  try {
    const data = await api.getCollectionResourceBudgetGet1(body);
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

[**PagedModelEntityModelBudget**](PagedModelEntityModelBudget.md)

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


## getItemResourceBudgetGet

> EntityModelBudget getItemResourceBudgetGet(id)



get-budget

### Example

```ts
import {
  Configuration,
  BudgetEntityControllerApi,
} from '';
import type { GetItemResourceBudgetGetRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new BudgetEntityControllerApi();

  const body = {
    // string
    id: id_example,
  } satisfies GetItemResourceBudgetGetRequest;

  try {
    const data = await api.getItemResourceBudgetGet(body);
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

[**EntityModelBudget**](EntityModelBudget.md)

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


## patchItemResourceBudgetPatch

> EntityModelBudget patchItemResourceBudgetPatch(id, budgetRequestBody)



patch-budget

### Example

```ts
import {
  Configuration,
  BudgetEntityControllerApi,
} from '';
import type { PatchItemResourceBudgetPatchRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new BudgetEntityControllerApi();

  const body = {
    // string
    id: id_example,
    // BudgetRequestBody
    budgetRequestBody: ...,
  } satisfies PatchItemResourceBudgetPatchRequest;

  try {
    const data = await api.patchItemResourceBudgetPatch(body);
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
| **budgetRequestBody** | [BudgetRequestBody](BudgetRequestBody.md) |  | |

### Return type

[**EntityModelBudget**](EntityModelBudget.md)

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


## postCollectionResourceBudgetPost

> EntityModelBudget postCollectionResourceBudgetPost(budgetRequestBody)



create-budget

### Example

```ts
import {
  Configuration,
  BudgetEntityControllerApi,
} from '';
import type { PostCollectionResourceBudgetPostRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new BudgetEntityControllerApi();

  const body = {
    // BudgetRequestBody
    budgetRequestBody: ...,
  } satisfies PostCollectionResourceBudgetPostRequest;

  try {
    const data = await api.postCollectionResourceBudgetPost(body);
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
| **budgetRequestBody** | [BudgetRequestBody](BudgetRequestBody.md) |  | |

### Return type

[**EntityModelBudget**](EntityModelBudget.md)

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


## putItemResourceBudgetPut

> EntityModelBudget putItemResourceBudgetPut(id, budgetRequestBody)



update-budget

### Example

```ts
import {
  Configuration,
  BudgetEntityControllerApi,
} from '';
import type { PutItemResourceBudgetPutRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new BudgetEntityControllerApi();

  const body = {
    // string
    id: id_example,
    // BudgetRequestBody
    budgetRequestBody: ...,
  } satisfies PutItemResourceBudgetPutRequest;

  try {
    const data = await api.putItemResourceBudgetPut(body);
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
| **budgetRequestBody** | [BudgetRequestBody](BudgetRequestBody.md) |  | |

### Return type

[**EntityModelBudget**](EntityModelBudget.md)

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


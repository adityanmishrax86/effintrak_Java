# IncomeEntityControllerApi

All URIs are relative to *http://localhost:8080*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**deleteItemResourceIncomeDelete**](IncomeEntityControllerApi.md#deleteitemresourceincomedelete) | **DELETE** /incomes/{id} |  |
| [**getCollectionResourceIncomeGet1**](IncomeEntityControllerApi.md#getcollectionresourceincomeget1) | **GET** /incomes |  |
| [**getItemResourceIncomeGet**](IncomeEntityControllerApi.md#getitemresourceincomeget) | **GET** /incomes/{id} |  |
| [**patchItemResourceIncomePatch**](IncomeEntityControllerApi.md#patchitemresourceincomepatch) | **PATCH** /incomes/{id} |  |
| [**postCollectionResourceIncomePost**](IncomeEntityControllerApi.md#postcollectionresourceincomepost) | **POST** /incomes |  |
| [**putItemResourceIncomePut**](IncomeEntityControllerApi.md#putitemresourceincomeput) | **PUT** /incomes/{id} |  |



## deleteItemResourceIncomeDelete

> deleteItemResourceIncomeDelete(id)



delete-income

### Example

```ts
import {
  Configuration,
  IncomeEntityControllerApi,
} from '';
import type { DeleteItemResourceIncomeDeleteRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new IncomeEntityControllerApi();

  const body = {
    // string
    id: id_example,
  } satisfies DeleteItemResourceIncomeDeleteRequest;

  try {
    const data = await api.deleteItemResourceIncomeDelete(body);
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


## getCollectionResourceIncomeGet1

> PagedModelEntityModelIncome getCollectionResourceIncomeGet1(page, size, sort)



get-income

### Example

```ts
import {
  Configuration,
  IncomeEntityControllerApi,
} from '';
import type { GetCollectionResourceIncomeGet1Request } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new IncomeEntityControllerApi();

  const body = {
    // number | Zero-based page index (0..N) (optional)
    page: 56,
    // number | The size of the page to be returned (optional)
    size: 56,
    // Array<string> | Sorting criteria in the format: property,(asc|desc). Default sort order is ascending. Multiple sort criteria are supported. (optional)
    sort: ...,
  } satisfies GetCollectionResourceIncomeGet1Request;

  try {
    const data = await api.getCollectionResourceIncomeGet1(body);
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

[**PagedModelEntityModelIncome**](PagedModelEntityModelIncome.md)

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


## getItemResourceIncomeGet

> EntityModelIncome getItemResourceIncomeGet(id)



get-income

### Example

```ts
import {
  Configuration,
  IncomeEntityControllerApi,
} from '';
import type { GetItemResourceIncomeGetRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new IncomeEntityControllerApi();

  const body = {
    // string
    id: id_example,
  } satisfies GetItemResourceIncomeGetRequest;

  try {
    const data = await api.getItemResourceIncomeGet(body);
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

[**EntityModelIncome**](EntityModelIncome.md)

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


## patchItemResourceIncomePatch

> EntityModelIncome patchItemResourceIncomePatch(id, incomeRequestBody)



patch-income

### Example

```ts
import {
  Configuration,
  IncomeEntityControllerApi,
} from '';
import type { PatchItemResourceIncomePatchRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new IncomeEntityControllerApi();

  const body = {
    // string
    id: id_example,
    // IncomeRequestBody
    incomeRequestBody: ...,
  } satisfies PatchItemResourceIncomePatchRequest;

  try {
    const data = await api.patchItemResourceIncomePatch(body);
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
| **incomeRequestBody** | [IncomeRequestBody](IncomeRequestBody.md) |  | |

### Return type

[**EntityModelIncome**](EntityModelIncome.md)

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


## postCollectionResourceIncomePost

> EntityModelIncome postCollectionResourceIncomePost(incomeRequestBody)



create-income

### Example

```ts
import {
  Configuration,
  IncomeEntityControllerApi,
} from '';
import type { PostCollectionResourceIncomePostRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new IncomeEntityControllerApi();

  const body = {
    // IncomeRequestBody
    incomeRequestBody: ...,
  } satisfies PostCollectionResourceIncomePostRequest;

  try {
    const data = await api.postCollectionResourceIncomePost(body);
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
| **incomeRequestBody** | [IncomeRequestBody](IncomeRequestBody.md) |  | |

### Return type

[**EntityModelIncome**](EntityModelIncome.md)

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


## putItemResourceIncomePut

> EntityModelIncome putItemResourceIncomePut(id, incomeRequestBody)



update-income

### Example

```ts
import {
  Configuration,
  IncomeEntityControllerApi,
} from '';
import type { PutItemResourceIncomePutRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new IncomeEntityControllerApi();

  const body = {
    // string
    id: id_example,
    // IncomeRequestBody
    incomeRequestBody: ...,
  } satisfies PutItemResourceIncomePutRequest;

  try {
    const data = await api.putItemResourceIncomePut(body);
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
| **incomeRequestBody** | [IncomeRequestBody](IncomeRequestBody.md) |  | |

### Return type

[**EntityModelIncome**](EntityModelIncome.md)

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


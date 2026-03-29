# ExpenseEntityControllerApi

All URIs are relative to *http://localhost:8080*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**deleteItemResourceExpenseDelete**](ExpenseEntityControllerApi.md#deleteitemresourceexpensedelete) | **DELETE** /expenses/{id} |  |
| [**getCollectionResourceExpenseGet1**](ExpenseEntityControllerApi.md#getcollectionresourceexpenseget1) | **GET** /expenses |  |
| [**getItemResourceExpenseGet**](ExpenseEntityControllerApi.md#getitemresourceexpenseget) | **GET** /expenses/{id} |  |
| [**patchItemResourceExpensePatch**](ExpenseEntityControllerApi.md#patchitemresourceexpensepatch) | **PATCH** /expenses/{id} |  |
| [**postCollectionResourceExpensePost**](ExpenseEntityControllerApi.md#postcollectionresourceexpensepost) | **POST** /expenses |  |
| [**putItemResourceExpensePut**](ExpenseEntityControllerApi.md#putitemresourceexpenseput) | **PUT** /expenses/{id} |  |



## deleteItemResourceExpenseDelete

> deleteItemResourceExpenseDelete(id)



delete-expense

### Example

```ts
import {
  Configuration,
  ExpenseEntityControllerApi,
} from '';
import type { DeleteItemResourceExpenseDeleteRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new ExpenseEntityControllerApi();

  const body = {
    // string
    id: id_example,
  } satisfies DeleteItemResourceExpenseDeleteRequest;

  try {
    const data = await api.deleteItemResourceExpenseDelete(body);
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


## getCollectionResourceExpenseGet1

> PagedModelEntityModelExpense getCollectionResourceExpenseGet1(page, size, sort)



get-expense

### Example

```ts
import {
  Configuration,
  ExpenseEntityControllerApi,
} from '';
import type { GetCollectionResourceExpenseGet1Request } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new ExpenseEntityControllerApi();

  const body = {
    // number | Zero-based page index (0..N) (optional)
    page: 56,
    // number | The size of the page to be returned (optional)
    size: 56,
    // Array<string> | Sorting criteria in the format: property,(asc|desc). Default sort order is ascending. Multiple sort criteria are supported. (optional)
    sort: ...,
  } satisfies GetCollectionResourceExpenseGet1Request;

  try {
    const data = await api.getCollectionResourceExpenseGet1(body);
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

[**PagedModelEntityModelExpense**](PagedModelEntityModelExpense.md)

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


## getItemResourceExpenseGet

> EntityModelExpense getItemResourceExpenseGet(id)



get-expense

### Example

```ts
import {
  Configuration,
  ExpenseEntityControllerApi,
} from '';
import type { GetItemResourceExpenseGetRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new ExpenseEntityControllerApi();

  const body = {
    // string
    id: id_example,
  } satisfies GetItemResourceExpenseGetRequest;

  try {
    const data = await api.getItemResourceExpenseGet(body);
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

[**EntityModelExpense**](EntityModelExpense.md)

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


## patchItemResourceExpensePatch

> EntityModelExpense patchItemResourceExpensePatch(id, expenseRequestBody)



patch-expense

### Example

```ts
import {
  Configuration,
  ExpenseEntityControllerApi,
} from '';
import type { PatchItemResourceExpensePatchRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new ExpenseEntityControllerApi();

  const body = {
    // string
    id: id_example,
    // ExpenseRequestBody
    expenseRequestBody: ...,
  } satisfies PatchItemResourceExpensePatchRequest;

  try {
    const data = await api.patchItemResourceExpensePatch(body);
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
| **expenseRequestBody** | [ExpenseRequestBody](ExpenseRequestBody.md) |  | |

### Return type

[**EntityModelExpense**](EntityModelExpense.md)

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


## postCollectionResourceExpensePost

> EntityModelExpense postCollectionResourceExpensePost(expenseRequestBody)



create-expense

### Example

```ts
import {
  Configuration,
  ExpenseEntityControllerApi,
} from '';
import type { PostCollectionResourceExpensePostRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new ExpenseEntityControllerApi();

  const body = {
    // ExpenseRequestBody
    expenseRequestBody: ...,
  } satisfies PostCollectionResourceExpensePostRequest;

  try {
    const data = await api.postCollectionResourceExpensePost(body);
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
| **expenseRequestBody** | [ExpenseRequestBody](ExpenseRequestBody.md) |  | |

### Return type

[**EntityModelExpense**](EntityModelExpense.md)

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


## putItemResourceExpensePut

> EntityModelExpense putItemResourceExpensePut(id, expenseRequestBody)



update-expense

### Example

```ts
import {
  Configuration,
  ExpenseEntityControllerApi,
} from '';
import type { PutItemResourceExpensePutRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new ExpenseEntityControllerApi();

  const body = {
    // string
    id: id_example,
    // ExpenseRequestBody
    expenseRequestBody: ...,
  } satisfies PutItemResourceExpensePutRequest;

  try {
    const data = await api.putItemResourceExpensePut(body);
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
| **expenseRequestBody** | [ExpenseRequestBody](ExpenseRequestBody.md) |  | |

### Return type

[**EntityModelExpense**](EntityModelExpense.md)

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


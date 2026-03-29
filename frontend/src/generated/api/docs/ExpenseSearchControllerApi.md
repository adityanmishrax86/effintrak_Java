# ExpenseSearchControllerApi

All URIs are relative to *http://localhost:8080*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**executeSearchExpenseGet**](ExpenseSearchControllerApi.md#executesearchexpenseget) | **GET** /expenses/search/findAllByUserIdAndDateBetweenOrderByDateDesc |  |
| [**executeSearchExpenseGet1**](ExpenseSearchControllerApi.md#executesearchexpenseget1) | **GET** /expenses/search/findAllByUserIdOrderByDateDesc |  |
| [**executeSearchExpenseGet2**](ExpenseSearchControllerApi.md#executesearchexpenseget2) | **GET** /expenses/search/findByUserIdAndCategoryIdOrderByDateDesc |  |
| [**executeSearchExpenseGet3**](ExpenseSearchControllerApi.md#executesearchexpenseget3) | **GET** /expenses/search/findByUserIdAndDateBetweenOrderByDateDesc |  |
| [**executeSearchExpenseGet4**](ExpenseSearchControllerApi.md#executesearchexpenseget4) | **GET** /expenses/search/findByUserIdAndDescriptionContainingIgnoreCase |  |
| [**executeSearchExpenseGet5**](ExpenseSearchControllerApi.md#executesearchexpenseget5) | **GET** /expenses/search/findByUserIdAndId |  |
| [**executeSearchExpenseGet6**](ExpenseSearchControllerApi.md#executesearchexpenseget6) | **GET** /expenses/search/findByUserIdOrderByDateDesc |  |
| [**executeSearchExpenseGet7**](ExpenseSearchControllerApi.md#executesearchexpenseget7) | **GET** /expenses/search/findExpensesWithFilters |  |



## executeSearchExpenseGet

> CollectionModelEntityModelExpense executeSearchExpenseGet(userId, start, end)



### Example

```ts
import {
  Configuration,
  ExpenseSearchControllerApi,
} from '';
import type { ExecuteSearchExpenseGetRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new ExpenseSearchControllerApi();

  const body = {
    // number (optional)
    userId: 789,
    // Date (optional)
    start: 2013-10-20,
    // Date (optional)
    end: 2013-10-20,
  } satisfies ExecuteSearchExpenseGetRequest;

  try {
    const data = await api.executeSearchExpenseGet(body);
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
| **userId** | `number` |  | [Optional] [Defaults to `undefined`] |
| **start** | `Date` |  | [Optional] [Defaults to `undefined`] |
| **end** | `Date` |  | [Optional] [Defaults to `undefined`] |

### Return type

[**CollectionModelEntityModelExpense**](CollectionModelEntityModelExpense.md)

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


## executeSearchExpenseGet1

> CollectionModelEntityModelExpense executeSearchExpenseGet1(userId)



### Example

```ts
import {
  Configuration,
  ExpenseSearchControllerApi,
} from '';
import type { ExecuteSearchExpenseGet1Request } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new ExpenseSearchControllerApi();

  const body = {
    // number (optional)
    userId: 789,
  } satisfies ExecuteSearchExpenseGet1Request;

  try {
    const data = await api.executeSearchExpenseGet1(body);
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
| **userId** | `number` |  | [Optional] [Defaults to `undefined`] |

### Return type

[**CollectionModelEntityModelExpense**](CollectionModelEntityModelExpense.md)

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


## executeSearchExpenseGet2

> PagedModelEntityModelExpense executeSearchExpenseGet2(userId, categoryId, page, size, sort)



### Example

```ts
import {
  Configuration,
  ExpenseSearchControllerApi,
} from '';
import type { ExecuteSearchExpenseGet2Request } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new ExpenseSearchControllerApi();

  const body = {
    // number (optional)
    userId: 789,
    // number (optional)
    categoryId: 789,
    // number | Zero-based page index (0..N) (optional)
    page: 56,
    // number | The size of the page to be returned (optional)
    size: 56,
    // Array<string> | Sorting criteria in the format: property,(asc|desc). Default sort order is ascending. Multiple sort criteria are supported. (optional)
    sort: ...,
  } satisfies ExecuteSearchExpenseGet2Request;

  try {
    const data = await api.executeSearchExpenseGet2(body);
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
| **userId** | `number` |  | [Optional] [Defaults to `undefined`] |
| **categoryId** | `number` |  | [Optional] [Defaults to `undefined`] |
| **page** | `number` | Zero-based page index (0..N) | [Optional] [Defaults to `0`] |
| **size** | `number` | The size of the page to be returned | [Optional] [Defaults to `20`] |
| **sort** | `Array<string>` | Sorting criteria in the format: property,(asc|desc). Default sort order is ascending. Multiple sort criteria are supported. | [Optional] |

### Return type

[**PagedModelEntityModelExpense**](PagedModelEntityModelExpense.md)

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


## executeSearchExpenseGet3

> PagedModelEntityModelExpense executeSearchExpenseGet3(userId, start, end, page, size, sort)



### Example

```ts
import {
  Configuration,
  ExpenseSearchControllerApi,
} from '';
import type { ExecuteSearchExpenseGet3Request } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new ExpenseSearchControllerApi();

  const body = {
    // number (optional)
    userId: 789,
    // Date (optional)
    start: 2013-10-20,
    // Date (optional)
    end: 2013-10-20,
    // number | Zero-based page index (0..N) (optional)
    page: 56,
    // number | The size of the page to be returned (optional)
    size: 56,
    // Array<string> | Sorting criteria in the format: property,(asc|desc). Default sort order is ascending. Multiple sort criteria are supported. (optional)
    sort: ...,
  } satisfies ExecuteSearchExpenseGet3Request;

  try {
    const data = await api.executeSearchExpenseGet3(body);
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
| **userId** | `number` |  | [Optional] [Defaults to `undefined`] |
| **start** | `Date` |  | [Optional] [Defaults to `undefined`] |
| **end** | `Date` |  | [Optional] [Defaults to `undefined`] |
| **page** | `number` | Zero-based page index (0..N) | [Optional] [Defaults to `0`] |
| **size** | `number` | The size of the page to be returned | [Optional] [Defaults to `20`] |
| **sort** | `Array<string>` | Sorting criteria in the format: property,(asc|desc). Default sort order is ascending. Multiple sort criteria are supported. | [Optional] |

### Return type

[**PagedModelEntityModelExpense**](PagedModelEntityModelExpense.md)

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


## executeSearchExpenseGet4

> CollectionModelEntityModelExpense executeSearchExpenseGet4(userId, search)



### Example

```ts
import {
  Configuration,
  ExpenseSearchControllerApi,
} from '';
import type { ExecuteSearchExpenseGet4Request } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new ExpenseSearchControllerApi();

  const body = {
    // number (optional)
    userId: 789,
    // string (optional)
    search: search_example,
  } satisfies ExecuteSearchExpenseGet4Request;

  try {
    const data = await api.executeSearchExpenseGet4(body);
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
| **userId** | `number` |  | [Optional] [Defaults to `undefined`] |
| **search** | `string` |  | [Optional] [Defaults to `undefined`] |

### Return type

[**CollectionModelEntityModelExpense**](CollectionModelEntityModelExpense.md)

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


## executeSearchExpenseGet5

> EntityModelExpense executeSearchExpenseGet5(userId, id)



### Example

```ts
import {
  Configuration,
  ExpenseSearchControllerApi,
} from '';
import type { ExecuteSearchExpenseGet5Request } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new ExpenseSearchControllerApi();

  const body = {
    // number (optional)
    userId: 789,
    // number (optional)
    id: 789,
  } satisfies ExecuteSearchExpenseGet5Request;

  try {
    const data = await api.executeSearchExpenseGet5(body);
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
| **userId** | `number` |  | [Optional] [Defaults to `undefined`] |
| **id** | `number` |  | [Optional] [Defaults to `undefined`] |

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


## executeSearchExpenseGet6

> PagedModelEntityModelExpense executeSearchExpenseGet6(userId, page, size, sort)



### Example

```ts
import {
  Configuration,
  ExpenseSearchControllerApi,
} from '';
import type { ExecuteSearchExpenseGet6Request } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new ExpenseSearchControllerApi();

  const body = {
    // number (optional)
    userId: 789,
    // number | Zero-based page index (0..N) (optional)
    page: 56,
    // number | The size of the page to be returned (optional)
    size: 56,
    // Array<string> | Sorting criteria in the format: property,(asc|desc). Default sort order is ascending. Multiple sort criteria are supported. (optional)
    sort: ...,
  } satisfies ExecuteSearchExpenseGet6Request;

  try {
    const data = await api.executeSearchExpenseGet6(body);
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
| **userId** | `number` |  | [Optional] [Defaults to `undefined`] |
| **page** | `number` | Zero-based page index (0..N) | [Optional] [Defaults to `0`] |
| **size** | `number` | The size of the page to be returned | [Optional] [Defaults to `20`] |
| **sort** | `Array<string>` | Sorting criteria in the format: property,(asc|desc). Default sort order is ascending. Multiple sort criteria are supported. | [Optional] |

### Return type

[**PagedModelEntityModelExpense**](PagedModelEntityModelExpense.md)

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


## executeSearchExpenseGet7

> PagedModelEntityModelExpense executeSearchExpenseGet7(userId, categoryId, minAmount, maxAmount, paymentMethod, bankAccountId, startDate, endDate, page, size, sort)



### Example

```ts
import {
  Configuration,
  ExpenseSearchControllerApi,
} from '';
import type { ExecuteSearchExpenseGet7Request } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new ExpenseSearchControllerApi();

  const body = {
    // number (optional)
    userId: 789,
    // number (optional)
    categoryId: 789,
    // number (optional)
    minAmount: 1.2,
    // number (optional)
    maxAmount: 1.2,
    // string (optional)
    paymentMethod: paymentMethod_example,
    // number (optional)
    bankAccountId: 789,
    // Date (optional)
    startDate: 2013-10-20,
    // Date (optional)
    endDate: 2013-10-20,
    // number | Zero-based page index (0..N) (optional)
    page: 56,
    // number | The size of the page to be returned (optional)
    size: 56,
    // Array<string> | Sorting criteria in the format: property,(asc|desc). Default sort order is ascending. Multiple sort criteria are supported. (optional)
    sort: ...,
  } satisfies ExecuteSearchExpenseGet7Request;

  try {
    const data = await api.executeSearchExpenseGet7(body);
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
| **userId** | `number` |  | [Optional] [Defaults to `undefined`] |
| **categoryId** | `number` |  | [Optional] [Defaults to `undefined`] |
| **minAmount** | `number` |  | [Optional] [Defaults to `undefined`] |
| **maxAmount** | `number` |  | [Optional] [Defaults to `undefined`] |
| **paymentMethod** | `string` |  | [Optional] [Defaults to `undefined`] |
| **bankAccountId** | `number` |  | [Optional] [Defaults to `undefined`] |
| **startDate** | `Date` |  | [Optional] [Defaults to `undefined`] |
| **endDate** | `Date` |  | [Optional] [Defaults to `undefined`] |
| **page** | `number` | Zero-based page index (0..N) | [Optional] [Defaults to `0`] |
| **size** | `number` | The size of the page to be returned | [Optional] [Defaults to `20`] |
| **sort** | `Array<string>` | Sorting criteria in the format: property,(asc|desc). Default sort order is ascending. Multiple sort criteria are supported. | [Optional] |

### Return type

[**PagedModelEntityModelExpense**](PagedModelEntityModelExpense.md)

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


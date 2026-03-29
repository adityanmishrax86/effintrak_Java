# IncomeSearchControllerApi

All URIs are relative to *http://localhost:8080*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**executeSearchIncomeGet**](IncomeSearchControllerApi.md#executesearchincomeget) | **GET** /incomes/search/findAllByUserIdAndDateBetweenOrderByDateDesc |  |
| [**executeSearchIncomeGet1**](IncomeSearchControllerApi.md#executesearchincomeget1) | **GET** /incomes/search/findAllByUserIdOrderByDateDesc |  |
| [**executeSearchIncomeGet2**](IncomeSearchControllerApi.md#executesearchincomeget2) | **GET** /incomes/search/findByUserId |  |
| [**executeSearchIncomeGet3**](IncomeSearchControllerApi.md#executesearchincomeget3) | **GET** /incomes/search/findByUserIdAndCategoryIdOrderByDateDesc |  |
| [**executeSearchIncomeGet4**](IncomeSearchControllerApi.md#executesearchincomeget4) | **GET** /incomes/search/findByUserIdAndDateBetweenOrderByDateDesc |  |
| [**executeSearchIncomeGet5**](IncomeSearchControllerApi.md#executesearchincomeget5) | **GET** /incomes/search/findByUserIdAndDescriptionContainingIgnoreCase |  |
| [**executeSearchIncomeGet6**](IncomeSearchControllerApi.md#executesearchincomeget6) | **GET** /incomes/search/findByUserIdAndId |  |
| [**executeSearchIncomeGet7**](IncomeSearchControllerApi.md#executesearchincomeget7) | **GET** /incomes/search/findByUserIdOrderByDateDesc |  |
| [**executeSearchIncomeGet8**](IncomeSearchControllerApi.md#executesearchincomeget8) | **GET** /incomes/search/findIncomesWithFilters |  |



## executeSearchIncomeGet

> CollectionModelEntityModelIncome executeSearchIncomeGet(userId, start, end)



### Example

```ts
import {
  Configuration,
  IncomeSearchControllerApi,
} from '';
import type { ExecuteSearchIncomeGetRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new IncomeSearchControllerApi();

  const body = {
    // number (optional)
    userId: 789,
    // Date (optional)
    start: 2013-10-20,
    // Date (optional)
    end: 2013-10-20,
  } satisfies ExecuteSearchIncomeGetRequest;

  try {
    const data = await api.executeSearchIncomeGet(body);
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

[**CollectionModelEntityModelIncome**](CollectionModelEntityModelIncome.md)

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


## executeSearchIncomeGet1

> CollectionModelEntityModelIncome executeSearchIncomeGet1(userId)



### Example

```ts
import {
  Configuration,
  IncomeSearchControllerApi,
} from '';
import type { ExecuteSearchIncomeGet1Request } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new IncomeSearchControllerApi();

  const body = {
    // number (optional)
    userId: 789,
  } satisfies ExecuteSearchIncomeGet1Request;

  try {
    const data = await api.executeSearchIncomeGet1(body);
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

[**CollectionModelEntityModelIncome**](CollectionModelEntityModelIncome.md)

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


## executeSearchIncomeGet2

> CollectionModelEntityModelIncome executeSearchIncomeGet2(id)



### Example

```ts
import {
  Configuration,
  IncomeSearchControllerApi,
} from '';
import type { ExecuteSearchIncomeGet2Request } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new IncomeSearchControllerApi();

  const body = {
    // number (optional)
    id: 789,
  } satisfies ExecuteSearchIncomeGet2Request;

  try {
    const data = await api.executeSearchIncomeGet2(body);
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
| **id** | `number` |  | [Optional] [Defaults to `undefined`] |

### Return type

[**CollectionModelEntityModelIncome**](CollectionModelEntityModelIncome.md)

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


## executeSearchIncomeGet3

> PagedModelEntityModelIncome executeSearchIncomeGet3(userId, categoryId, page, size, sort)



### Example

```ts
import {
  Configuration,
  IncomeSearchControllerApi,
} from '';
import type { ExecuteSearchIncomeGet3Request } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new IncomeSearchControllerApi();

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
  } satisfies ExecuteSearchIncomeGet3Request;

  try {
    const data = await api.executeSearchIncomeGet3(body);
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

[**PagedModelEntityModelIncome**](PagedModelEntityModelIncome.md)

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


## executeSearchIncomeGet4

> PagedModelEntityModelIncome executeSearchIncomeGet4(userId, start, end, page, size, sort)



### Example

```ts
import {
  Configuration,
  IncomeSearchControllerApi,
} from '';
import type { ExecuteSearchIncomeGet4Request } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new IncomeSearchControllerApi();

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
  } satisfies ExecuteSearchIncomeGet4Request;

  try {
    const data = await api.executeSearchIncomeGet4(body);
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

[**PagedModelEntityModelIncome**](PagedModelEntityModelIncome.md)

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


## executeSearchIncomeGet5

> CollectionModelEntityModelIncome executeSearchIncomeGet5(userId, search)



### Example

```ts
import {
  Configuration,
  IncomeSearchControllerApi,
} from '';
import type { ExecuteSearchIncomeGet5Request } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new IncomeSearchControllerApi();

  const body = {
    // number (optional)
    userId: 789,
    // string (optional)
    search: search_example,
  } satisfies ExecuteSearchIncomeGet5Request;

  try {
    const data = await api.executeSearchIncomeGet5(body);
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

[**CollectionModelEntityModelIncome**](CollectionModelEntityModelIncome.md)

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


## executeSearchIncomeGet6

> EntityModelIncome executeSearchIncomeGet6(userId, id)



### Example

```ts
import {
  Configuration,
  IncomeSearchControllerApi,
} from '';
import type { ExecuteSearchIncomeGet6Request } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new IncomeSearchControllerApi();

  const body = {
    // number (optional)
    userId: 789,
    // number (optional)
    id: 789,
  } satisfies ExecuteSearchIncomeGet6Request;

  try {
    const data = await api.executeSearchIncomeGet6(body);
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


## executeSearchIncomeGet7

> PagedModelEntityModelIncome executeSearchIncomeGet7(userId, page, size, sort)



### Example

```ts
import {
  Configuration,
  IncomeSearchControllerApi,
} from '';
import type { ExecuteSearchIncomeGet7Request } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new IncomeSearchControllerApi();

  const body = {
    // number (optional)
    userId: 789,
    // number | Zero-based page index (0..N) (optional)
    page: 56,
    // number | The size of the page to be returned (optional)
    size: 56,
    // Array<string> | Sorting criteria in the format: property,(asc|desc). Default sort order is ascending. Multiple sort criteria are supported. (optional)
    sort: ...,
  } satisfies ExecuteSearchIncomeGet7Request;

  try {
    const data = await api.executeSearchIncomeGet7(body);
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

[**PagedModelEntityModelIncome**](PagedModelEntityModelIncome.md)

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


## executeSearchIncomeGet8

> PagedModelEntityModelIncome executeSearchIncomeGet8(userId, categoryId, minAmount, maxAmount, bankAccountId, startDate, endDate, page, size, sort)



### Example

```ts
import {
  Configuration,
  IncomeSearchControllerApi,
} from '';
import type { ExecuteSearchIncomeGet8Request } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new IncomeSearchControllerApi();

  const body = {
    // number (optional)
    userId: 789,
    // number (optional)
    categoryId: 789,
    // number (optional)
    minAmount: 1.2,
    // number (optional)
    maxAmount: 1.2,
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
  } satisfies ExecuteSearchIncomeGet8Request;

  try {
    const data = await api.executeSearchIncomeGet8(body);
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
| **bankAccountId** | `number` |  | [Optional] [Defaults to `undefined`] |
| **startDate** | `Date` |  | [Optional] [Defaults to `undefined`] |
| **endDate** | `Date` |  | [Optional] [Defaults to `undefined`] |
| **page** | `number` | Zero-based page index (0..N) | [Optional] [Defaults to `0`] |
| **size** | `number` | The size of the page to be returned | [Optional] [Defaults to `20`] |
| **sort** | `Array<string>` | Sorting criteria in the format: property,(asc|desc). Default sort order is ascending. Multiple sort criteria are supported. | [Optional] |

### Return type

[**PagedModelEntityModelIncome**](PagedModelEntityModelIncome.md)

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


# ReportControllerApi

All URIs are relative to *http://localhost:8080*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**getCategoryTrend**](ReportControllerApi.md#getcategorytrend) | **GET** /api/reports/user/{userId}/category-trend |  |
| [**getComparison**](ReportControllerApi.md#getcomparison) | **GET** /api/reports/user/{userId}/comparison |  |
| [**getMonthlyTrend**](ReportControllerApi.md#getmonthlytrend) | **GET** /api/reports/user/{userId}/monthly-trend |  |
| [**getReport**](ReportControllerApi.md#getreport) | **GET** /api/reports/user/{userId} |  |



## getCategoryTrend

> object getCategoryTrend(userId, categoryId, period)



### Example

```ts
import {
  Configuration,
  ReportControllerApi,
} from '';
import type { GetCategoryTrendRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new ReportControllerApi();

  const body = {
    // number
    userId: 789,
    // number (optional)
    categoryId: 789,
    // string (optional)
    period: period_example,
  } satisfies GetCategoryTrendRequest;

  try {
    const data = await api.getCategoryTrend(body);
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
| **userId** | `number` |  | [Defaults to `undefined`] |
| **categoryId** | `number` |  | [Optional] [Defaults to `undefined`] |
| **period** | `string` |  | [Optional] [Defaults to `&#39;monthly&#39;`] |

### Return type

**object**

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: `application/hal+json`


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **400** | Bad Request |  -  |
| **403** | Forbidden |  -  |
| **200** | OK |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#api-endpoints) [[Back to Model list]](../README.md#models) [[Back to README]](../README.md)


## getComparison

> object getComparison(userId, startDate, endDate)



### Example

```ts
import {
  Configuration,
  ReportControllerApi,
} from '';
import type { GetComparisonRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new ReportControllerApi();

  const body = {
    // number
    userId: 789,
    // string
    startDate: startDate_example,
    // string
    endDate: endDate_example,
  } satisfies GetComparisonRequest;

  try {
    const data = await api.getComparison(body);
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
| **userId** | `number` |  | [Defaults to `undefined`] |
| **startDate** | `string` |  | [Defaults to `undefined`] |
| **endDate** | `string` |  | [Defaults to `undefined`] |

### Return type

**object**

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: `application/hal+json`


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **400** | Bad Request |  -  |
| **403** | Forbidden |  -  |
| **200** | OK |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#api-endpoints) [[Back to Model list]](../README.md#models) [[Back to README]](../README.md)


## getMonthlyTrend

> object getMonthlyTrend(userId, year)



### Example

```ts
import {
  Configuration,
  ReportControllerApi,
} from '';
import type { GetMonthlyTrendRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new ReportControllerApi();

  const body = {
    // number
    userId: 789,
    // number (optional)
    year: 56,
  } satisfies GetMonthlyTrendRequest;

  try {
    const data = await api.getMonthlyTrend(body);
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
| **userId** | `number` |  | [Defaults to `undefined`] |
| **year** | `number` |  | [Optional] [Defaults to `undefined`] |

### Return type

**object**

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: `application/hal+json`


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **400** | Bad Request |  -  |
| **403** | Forbidden |  -  |
| **200** | OK |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#api-endpoints) [[Back to Model list]](../README.md#models) [[Back to README]](../README.md)


## getReport

> object getReport(userId, startDate, endDate)



### Example

```ts
import {
  Configuration,
  ReportControllerApi,
} from '';
import type { GetReportRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new ReportControllerApi();

  const body = {
    // number
    userId: 789,
    // string
    startDate: startDate_example,
    // string
    endDate: endDate_example,
  } satisfies GetReportRequest;

  try {
    const data = await api.getReport(body);
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
| **userId** | `number` |  | [Defaults to `undefined`] |
| **startDate** | `string` |  | [Defaults to `undefined`] |
| **endDate** | `string` |  | [Defaults to `undefined`] |

### Return type

**object**

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: `application/hal+json`


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **400** | Bad Request |  -  |
| **403** | Forbidden |  -  |
| **200** | OK |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#api-endpoints) [[Back to Model list]](../README.md#models) [[Back to README]](../README.md)


# ExportControllerApi

All URIs are relative to *http://localhost:8080*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**exportReport**](ExportControllerApi.md#exportreport) | **GET** /api/export/user/{userId}/report |  |
| [**exportTransactions**](ExportControllerApi.md#exporttransactions) | **GET** /api/export/user/{userId}/transactions |  |



## exportReport

> object exportReport(userId, startDate, endDate, format)



### Example

```ts
import {
  Configuration,
  ExportControllerApi,
} from '';
import type { ExportReportRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new ExportControllerApi();

  const body = {
    // number
    userId: 789,
    // string
    startDate: startDate_example,
    // string
    endDate: endDate_example,
    // string (optional)
    format: format_example,
  } satisfies ExportReportRequest;

  try {
    const data = await api.exportReport(body);
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
| **format** | `string` |  | [Optional] [Defaults to `&#39;csv&#39;`] |

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


## exportTransactions

> object exportTransactions(userId, startDate, endDate, format)



### Example

```ts
import {
  Configuration,
  ExportControllerApi,
} from '';
import type { ExportTransactionsRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new ExportControllerApi();

  const body = {
    // number
    userId: 789,
    // string
    startDate: startDate_example,
    // string
    endDate: endDate_example,
    // string (optional)
    format: format_example,
  } satisfies ExportTransactionsRequest;

  try {
    const data = await api.exportTransactions(body);
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
| **format** | `string` |  | [Optional] [Defaults to `&#39;csv&#39;`] |

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


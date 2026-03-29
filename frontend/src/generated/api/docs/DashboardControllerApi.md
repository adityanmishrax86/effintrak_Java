# DashboardControllerApi

All URIs are relative to *http://localhost:8080*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**getDashboard**](DashboardControllerApi.md#getdashboard) | **GET** /api/dashboard/{userId} |  |



## getDashboard

> object getDashboard(userId)



### Example

```ts
import {
  Configuration,
  DashboardControllerApi,
} from '';
import type { GetDashboardRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new DashboardControllerApi();

  const body = {
    // number
    userId: 789,
  } satisfies GetDashboardRequest;

  try {
    const data = await api.getDashboard(body);
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


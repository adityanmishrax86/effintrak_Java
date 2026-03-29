# BillControllerApi

All URIs are relative to *http://localhost:8080*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**getOverdueBills**](BillControllerApi.md#getoverduebills) | **GET** /api/bills/user/{userId}/overdue |  |



## getOverdueBills

> object getOverdueBills(userId)



### Example

```ts
import {
  Configuration,
  BillControllerApi,
} from '';
import type { GetOverdueBillsRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new BillControllerApi();

  const body = {
    // number
    userId: 789,
  } satisfies GetOverdueBillsRequest;

  try {
    const data = await api.getOverdueBills(body);
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


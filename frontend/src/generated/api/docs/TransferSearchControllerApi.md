# TransferSearchControllerApi

All URIs are relative to *http://localhost:8080*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**executeSearchTransferGet**](TransferSearchControllerApi.md#executesearchtransferget) | **GET** /transfers/search/findByUserId |  |



## executeSearchTransferGet

> CollectionModelEntityModelTransfer executeSearchTransferGet(userId)



### Example

```ts
import {
  Configuration,
  TransferSearchControllerApi,
} from '';
import type { ExecuteSearchTransferGetRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new TransferSearchControllerApi();

  const body = {
    // number (optional)
    userId: 789,
  } satisfies ExecuteSearchTransferGetRequest;

  try {
    const data = await api.executeSearchTransferGet(body);
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

[**CollectionModelEntityModelTransfer**](CollectionModelEntityModelTransfer.md)

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


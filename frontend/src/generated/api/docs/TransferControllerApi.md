# TransferControllerApi

All URIs are relative to *http://localhost:8080*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**createTransfer**](TransferControllerApi.md#createtransfer) | **POST** /api/transfers |  |
| [**getTransfersByUserId**](TransferControllerApi.md#gettransfersbyuserid) | **GET** /api/transfers/user/{userId} |  |



## createTransfer

> object createTransfer(transferRequestDTO)



### Example

```ts
import {
  Configuration,
  TransferControllerApi,
} from '';
import type { CreateTransferRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new TransferControllerApi();

  const body = {
    // TransferRequestDTO
    transferRequestDTO: ...,
  } satisfies CreateTransferRequest;

  try {
    const data = await api.createTransfer(body);
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
| **transferRequestDTO** | [TransferRequestDTO](TransferRequestDTO.md) |  | |

### Return type

**object**

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: `application/json`
- **Accept**: `application/hal+json`


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **400** | Bad Request |  -  |
| **403** | Forbidden |  -  |
| **200** | OK |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#api-endpoints) [[Back to Model list]](../README.md#models) [[Back to README]](../README.md)


## getTransfersByUserId

> object getTransfersByUserId(userId)



### Example

```ts
import {
  Configuration,
  TransferControllerApi,
} from '';
import type { GetTransfersByUserIdRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new TransferControllerApi();

  const body = {
    // number
    userId: 789,
  } satisfies GetTransfersByUserIdRequest;

  try {
    const data = await api.getTransfersByUserId(body);
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


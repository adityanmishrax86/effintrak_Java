# TransferEntityControllerApi

All URIs are relative to *http://localhost:8080*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**deleteItemResourceTransferDelete**](TransferEntityControllerApi.md#deleteitemresourcetransferdelete) | **DELETE** /transfers/{id} |  |
| [**getCollectionResourceTransferGet1**](TransferEntityControllerApi.md#getcollectionresourcetransferget1) | **GET** /transfers |  |
| [**getItemResourceTransferGet**](TransferEntityControllerApi.md#getitemresourcetransferget) | **GET** /transfers/{id} |  |
| [**patchItemResourceTransferPatch**](TransferEntityControllerApi.md#patchitemresourcetransferpatch) | **PATCH** /transfers/{id} |  |
| [**postCollectionResourceTransferPost**](TransferEntityControllerApi.md#postcollectionresourcetransferpost) | **POST** /transfers |  |
| [**putItemResourceTransferPut**](TransferEntityControllerApi.md#putitemresourcetransferput) | **PUT** /transfers/{id} |  |



## deleteItemResourceTransferDelete

> deleteItemResourceTransferDelete(id)



delete-transfer

### Example

```ts
import {
  Configuration,
  TransferEntityControllerApi,
} from '';
import type { DeleteItemResourceTransferDeleteRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new TransferEntityControllerApi();

  const body = {
    // string
    id: id_example,
  } satisfies DeleteItemResourceTransferDeleteRequest;

  try {
    const data = await api.deleteItemResourceTransferDelete(body);
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


## getCollectionResourceTransferGet1

> PagedModelEntityModelTransfer getCollectionResourceTransferGet1(page, size, sort)



get-transfer

### Example

```ts
import {
  Configuration,
  TransferEntityControllerApi,
} from '';
import type { GetCollectionResourceTransferGet1Request } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new TransferEntityControllerApi();

  const body = {
    // number | Zero-based page index (0..N) (optional)
    page: 56,
    // number | The size of the page to be returned (optional)
    size: 56,
    // Array<string> | Sorting criteria in the format: property,(asc|desc). Default sort order is ascending. Multiple sort criteria are supported. (optional)
    sort: ...,
  } satisfies GetCollectionResourceTransferGet1Request;

  try {
    const data = await api.getCollectionResourceTransferGet1(body);
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

[**PagedModelEntityModelTransfer**](PagedModelEntityModelTransfer.md)

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


## getItemResourceTransferGet

> EntityModelTransfer getItemResourceTransferGet(id)



get-transfer

### Example

```ts
import {
  Configuration,
  TransferEntityControllerApi,
} from '';
import type { GetItemResourceTransferGetRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new TransferEntityControllerApi();

  const body = {
    // string
    id: id_example,
  } satisfies GetItemResourceTransferGetRequest;

  try {
    const data = await api.getItemResourceTransferGet(body);
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

[**EntityModelTransfer**](EntityModelTransfer.md)

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


## patchItemResourceTransferPatch

> EntityModelTransfer patchItemResourceTransferPatch(id, transferRequestBody)



patch-transfer

### Example

```ts
import {
  Configuration,
  TransferEntityControllerApi,
} from '';
import type { PatchItemResourceTransferPatchRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new TransferEntityControllerApi();

  const body = {
    // string
    id: id_example,
    // TransferRequestBody
    transferRequestBody: ...,
  } satisfies PatchItemResourceTransferPatchRequest;

  try {
    const data = await api.patchItemResourceTransferPatch(body);
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
| **transferRequestBody** | [TransferRequestBody](TransferRequestBody.md) |  | |

### Return type

[**EntityModelTransfer**](EntityModelTransfer.md)

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


## postCollectionResourceTransferPost

> EntityModelTransfer postCollectionResourceTransferPost(transferRequestBody)



create-transfer

### Example

```ts
import {
  Configuration,
  TransferEntityControllerApi,
} from '';
import type { PostCollectionResourceTransferPostRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new TransferEntityControllerApi();

  const body = {
    // TransferRequestBody
    transferRequestBody: ...,
  } satisfies PostCollectionResourceTransferPostRequest;

  try {
    const data = await api.postCollectionResourceTransferPost(body);
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
| **transferRequestBody** | [TransferRequestBody](TransferRequestBody.md) |  | |

### Return type

[**EntityModelTransfer**](EntityModelTransfer.md)

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


## putItemResourceTransferPut

> EntityModelTransfer putItemResourceTransferPut(id, transferRequestBody)



update-transfer

### Example

```ts
import {
  Configuration,
  TransferEntityControllerApi,
} from '';
import type { PutItemResourceTransferPutRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new TransferEntityControllerApi();

  const body = {
    // string
    id: id_example,
    // TransferRequestBody
    transferRequestBody: ...,
  } satisfies PutItemResourceTransferPutRequest;

  try {
    const data = await api.putItemResourceTransferPut(body);
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
| **transferRequestBody** | [TransferRequestBody](TransferRequestBody.md) |  | |

### Return type

[**EntityModelTransfer**](EntityModelTransfer.md)

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


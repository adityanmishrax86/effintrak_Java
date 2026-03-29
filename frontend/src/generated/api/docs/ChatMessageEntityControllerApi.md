# ChatMessageEntityControllerApi

All URIs are relative to *http://localhost:8080*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**deleteItemResourceChatmessageDelete**](ChatMessageEntityControllerApi.md#deleteitemresourcechatmessagedelete) | **DELETE** /chatMessages/{id} |  |
| [**getCollectionResourceChatmessageGet1**](ChatMessageEntityControllerApi.md#getcollectionresourcechatmessageget1) | **GET** /chatMessages |  |
| [**getItemResourceChatmessageGet**](ChatMessageEntityControllerApi.md#getitemresourcechatmessageget) | **GET** /chatMessages/{id} |  |
| [**patchItemResourceChatmessagePatch**](ChatMessageEntityControllerApi.md#patchitemresourcechatmessagepatch) | **PATCH** /chatMessages/{id} |  |
| [**postCollectionResourceChatmessagePost**](ChatMessageEntityControllerApi.md#postcollectionresourcechatmessagepost) | **POST** /chatMessages |  |
| [**putItemResourceChatmessagePut**](ChatMessageEntityControllerApi.md#putitemresourcechatmessageput) | **PUT** /chatMessages/{id} |  |



## deleteItemResourceChatmessageDelete

> deleteItemResourceChatmessageDelete(id)



delete-chatmessage

### Example

```ts
import {
  Configuration,
  ChatMessageEntityControllerApi,
} from '';
import type { DeleteItemResourceChatmessageDeleteRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new ChatMessageEntityControllerApi();

  const body = {
    // string
    id: id_example,
  } satisfies DeleteItemResourceChatmessageDeleteRequest;

  try {
    const data = await api.deleteItemResourceChatmessageDelete(body);
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


## getCollectionResourceChatmessageGet1

> PagedModelEntityModelChatMessage getCollectionResourceChatmessageGet1(page, size, sort)



get-chatmessage

### Example

```ts
import {
  Configuration,
  ChatMessageEntityControllerApi,
} from '';
import type { GetCollectionResourceChatmessageGet1Request } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new ChatMessageEntityControllerApi();

  const body = {
    // number | Zero-based page index (0..N) (optional)
    page: 56,
    // number | The size of the page to be returned (optional)
    size: 56,
    // Array<string> | Sorting criteria in the format: property,(asc|desc). Default sort order is ascending. Multiple sort criteria are supported. (optional)
    sort: ...,
  } satisfies GetCollectionResourceChatmessageGet1Request;

  try {
    const data = await api.getCollectionResourceChatmessageGet1(body);
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

[**PagedModelEntityModelChatMessage**](PagedModelEntityModelChatMessage.md)

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


## getItemResourceChatmessageGet

> EntityModelChatMessage getItemResourceChatmessageGet(id)



get-chatmessage

### Example

```ts
import {
  Configuration,
  ChatMessageEntityControllerApi,
} from '';
import type { GetItemResourceChatmessageGetRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new ChatMessageEntityControllerApi();

  const body = {
    // string
    id: id_example,
  } satisfies GetItemResourceChatmessageGetRequest;

  try {
    const data = await api.getItemResourceChatmessageGet(body);
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

[**EntityModelChatMessage**](EntityModelChatMessage.md)

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


## patchItemResourceChatmessagePatch

> EntityModelChatMessage patchItemResourceChatmessagePatch(id, chatMessageRequestBody)



patch-chatmessage

### Example

```ts
import {
  Configuration,
  ChatMessageEntityControllerApi,
} from '';
import type { PatchItemResourceChatmessagePatchRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new ChatMessageEntityControllerApi();

  const body = {
    // string
    id: id_example,
    // ChatMessageRequestBody
    chatMessageRequestBody: ...,
  } satisfies PatchItemResourceChatmessagePatchRequest;

  try {
    const data = await api.patchItemResourceChatmessagePatch(body);
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
| **chatMessageRequestBody** | [ChatMessageRequestBody](ChatMessageRequestBody.md) |  | |

### Return type

[**EntityModelChatMessage**](EntityModelChatMessage.md)

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


## postCollectionResourceChatmessagePost

> EntityModelChatMessage postCollectionResourceChatmessagePost(chatMessageRequestBody)



create-chatmessage

### Example

```ts
import {
  Configuration,
  ChatMessageEntityControllerApi,
} from '';
import type { PostCollectionResourceChatmessagePostRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new ChatMessageEntityControllerApi();

  const body = {
    // ChatMessageRequestBody
    chatMessageRequestBody: ...,
  } satisfies PostCollectionResourceChatmessagePostRequest;

  try {
    const data = await api.postCollectionResourceChatmessagePost(body);
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
| **chatMessageRequestBody** | [ChatMessageRequestBody](ChatMessageRequestBody.md) |  | |

### Return type

[**EntityModelChatMessage**](EntityModelChatMessage.md)

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


## putItemResourceChatmessagePut

> EntityModelChatMessage putItemResourceChatmessagePut(id, chatMessageRequestBody)



update-chatmessage

### Example

```ts
import {
  Configuration,
  ChatMessageEntityControllerApi,
} from '';
import type { PutItemResourceChatmessagePutRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new ChatMessageEntityControllerApi();

  const body = {
    // string
    id: id_example,
    // ChatMessageRequestBody
    chatMessageRequestBody: ...,
  } satisfies PutItemResourceChatmessagePutRequest;

  try {
    const data = await api.putItemResourceChatmessagePut(body);
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
| **chatMessageRequestBody** | [ChatMessageRequestBody](ChatMessageRequestBody.md) |  | |

### Return type

[**EntityModelChatMessage**](EntityModelChatMessage.md)

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


# ChatConversationEntityControllerApi

All URIs are relative to *http://localhost:8080*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**deleteItemResourceChatconversationDelete**](ChatConversationEntityControllerApi.md#deleteitemresourcechatconversationdelete) | **DELETE** /chatConversations/{id} |  |
| [**getCollectionResourceChatconversationGet1**](ChatConversationEntityControllerApi.md#getcollectionresourcechatconversationget1) | **GET** /chatConversations |  |
| [**getItemResourceChatconversationGet**](ChatConversationEntityControllerApi.md#getitemresourcechatconversationget) | **GET** /chatConversations/{id} |  |
| [**patchItemResourceChatconversationPatch**](ChatConversationEntityControllerApi.md#patchitemresourcechatconversationpatch) | **PATCH** /chatConversations/{id} |  |
| [**postCollectionResourceChatconversationPost**](ChatConversationEntityControllerApi.md#postcollectionresourcechatconversationpost) | **POST** /chatConversations |  |
| [**putItemResourceChatconversationPut**](ChatConversationEntityControllerApi.md#putitemresourcechatconversationput) | **PUT** /chatConversations/{id} |  |



## deleteItemResourceChatconversationDelete

> deleteItemResourceChatconversationDelete(id)



delete-chatconversation

### Example

```ts
import {
  Configuration,
  ChatConversationEntityControllerApi,
} from '';
import type { DeleteItemResourceChatconversationDeleteRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new ChatConversationEntityControllerApi();

  const body = {
    // string
    id: id_example,
  } satisfies DeleteItemResourceChatconversationDeleteRequest;

  try {
    const data = await api.deleteItemResourceChatconversationDelete(body);
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


## getCollectionResourceChatconversationGet1

> PagedModelEntityModelChatConversation getCollectionResourceChatconversationGet1(page, size, sort)



get-chatconversation

### Example

```ts
import {
  Configuration,
  ChatConversationEntityControllerApi,
} from '';
import type { GetCollectionResourceChatconversationGet1Request } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new ChatConversationEntityControllerApi();

  const body = {
    // number | Zero-based page index (0..N) (optional)
    page: 56,
    // number | The size of the page to be returned (optional)
    size: 56,
    // Array<string> | Sorting criteria in the format: property,(asc|desc). Default sort order is ascending. Multiple sort criteria are supported. (optional)
    sort: ...,
  } satisfies GetCollectionResourceChatconversationGet1Request;

  try {
    const data = await api.getCollectionResourceChatconversationGet1(body);
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

[**PagedModelEntityModelChatConversation**](PagedModelEntityModelChatConversation.md)

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


## getItemResourceChatconversationGet

> EntityModelChatConversation getItemResourceChatconversationGet(id)



get-chatconversation

### Example

```ts
import {
  Configuration,
  ChatConversationEntityControllerApi,
} from '';
import type { GetItemResourceChatconversationGetRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new ChatConversationEntityControllerApi();

  const body = {
    // string
    id: id_example,
  } satisfies GetItemResourceChatconversationGetRequest;

  try {
    const data = await api.getItemResourceChatconversationGet(body);
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

[**EntityModelChatConversation**](EntityModelChatConversation.md)

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


## patchItemResourceChatconversationPatch

> EntityModelChatConversation patchItemResourceChatconversationPatch(id, chatConversationRequestBody)



patch-chatconversation

### Example

```ts
import {
  Configuration,
  ChatConversationEntityControllerApi,
} from '';
import type { PatchItemResourceChatconversationPatchRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new ChatConversationEntityControllerApi();

  const body = {
    // string
    id: id_example,
    // ChatConversationRequestBody
    chatConversationRequestBody: ...,
  } satisfies PatchItemResourceChatconversationPatchRequest;

  try {
    const data = await api.patchItemResourceChatconversationPatch(body);
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
| **chatConversationRequestBody** | [ChatConversationRequestBody](ChatConversationRequestBody.md) |  | |

### Return type

[**EntityModelChatConversation**](EntityModelChatConversation.md)

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


## postCollectionResourceChatconversationPost

> EntityModelChatConversation postCollectionResourceChatconversationPost(chatConversationRequestBody)



create-chatconversation

### Example

```ts
import {
  Configuration,
  ChatConversationEntityControllerApi,
} from '';
import type { PostCollectionResourceChatconversationPostRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new ChatConversationEntityControllerApi();

  const body = {
    // ChatConversationRequestBody
    chatConversationRequestBody: ...,
  } satisfies PostCollectionResourceChatconversationPostRequest;

  try {
    const data = await api.postCollectionResourceChatconversationPost(body);
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
| **chatConversationRequestBody** | [ChatConversationRequestBody](ChatConversationRequestBody.md) |  | |

### Return type

[**EntityModelChatConversation**](EntityModelChatConversation.md)

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


## putItemResourceChatconversationPut

> EntityModelChatConversation putItemResourceChatconversationPut(id, chatConversationRequestBody)



update-chatconversation

### Example

```ts
import {
  Configuration,
  ChatConversationEntityControllerApi,
} from '';
import type { PutItemResourceChatconversationPutRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new ChatConversationEntityControllerApi();

  const body = {
    // string
    id: id_example,
    // ChatConversationRequestBody
    chatConversationRequestBody: ...,
  } satisfies PutItemResourceChatconversationPutRequest;

  try {
    const data = await api.putItemResourceChatconversationPut(body);
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
| **chatConversationRequestBody** | [ChatConversationRequestBody](ChatConversationRequestBody.md) |  | |

### Return type

[**EntityModelChatConversation**](EntityModelChatConversation.md)

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


# ChatConversationSearchControllerApi

All URIs are relative to *http://localhost:8080*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**executeSearchChatconversationGet**](ChatConversationSearchControllerApi.md#executesearchchatconversationget) | **GET** /chatConversations/search/deleteByConversationId |  |
| [**executeSearchChatconversationGet1**](ChatConversationSearchControllerApi.md#executesearchchatconversationget1) | **GET** /chatConversations/search/findByConversationId |  |
| [**executeSearchChatconversationGet2**](ChatConversationSearchControllerApi.md#executesearchchatconversationget2) | **GET** /chatConversations/search/findByUserId |  |
| [**executeSearchChatconversationGet3**](ChatConversationSearchControllerApi.md#executesearchchatconversationget3) | **GET** /chatConversations/search/findByUserIdOrderByUpdatedAtDesc |  |



## executeSearchChatconversationGet

> executeSearchChatconversationGet(conversationId)



### Example

```ts
import {
  Configuration,
  ChatConversationSearchControllerApi,
} from '';
import type { ExecuteSearchChatconversationGetRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new ChatConversationSearchControllerApi();

  const body = {
    // string (optional)
    conversationId: conversationId_example,
  } satisfies ExecuteSearchChatconversationGetRequest;

  try {
    const data = await api.executeSearchChatconversationGet(body);
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
| **conversationId** | `string` |  | [Optional] [Defaults to `undefined`] |

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
| **200** | OK |  -  |
| **404** | Not Found |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#api-endpoints) [[Back to Model list]](../README.md#models) [[Back to README]](../README.md)


## executeSearchChatconversationGet1

> EntityModelChatConversation executeSearchChatconversationGet1(conversationId)



### Example

```ts
import {
  Configuration,
  ChatConversationSearchControllerApi,
} from '';
import type { ExecuteSearchChatconversationGet1Request } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new ChatConversationSearchControllerApi();

  const body = {
    // string (optional)
    conversationId: conversationId_example,
  } satisfies ExecuteSearchChatconversationGet1Request;

  try {
    const data = await api.executeSearchChatconversationGet1(body);
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
| **conversationId** | `string` |  | [Optional] [Defaults to `undefined`] |

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


## executeSearchChatconversationGet2

> PagedModelEntityModelChatConversation executeSearchChatconversationGet2(userId, page, size, sort)



### Example

```ts
import {
  Configuration,
  ChatConversationSearchControllerApi,
} from '';
import type { ExecuteSearchChatconversationGet2Request } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new ChatConversationSearchControllerApi();

  const body = {
    // number (optional)
    userId: 789,
    // number | Zero-based page index (0..N) (optional)
    page: 56,
    // number | The size of the page to be returned (optional)
    size: 56,
    // Array<string> | Sorting criteria in the format: property,(asc|desc). Default sort order is ascending. Multiple sort criteria are supported. (optional)
    sort: ...,
  } satisfies ExecuteSearchChatconversationGet2Request;

  try {
    const data = await api.executeSearchChatconversationGet2(body);
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

[**PagedModelEntityModelChatConversation**](PagedModelEntityModelChatConversation.md)

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


## executeSearchChatconversationGet3

> CollectionModelEntityModelChatConversation executeSearchChatconversationGet3(userId)



### Example

```ts
import {
  Configuration,
  ChatConversationSearchControllerApi,
} from '';
import type { ExecuteSearchChatconversationGet3Request } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new ChatConversationSearchControllerApi();

  const body = {
    // number (optional)
    userId: 789,
  } satisfies ExecuteSearchChatconversationGet3Request;

  try {
    const data = await api.executeSearchChatconversationGet3(body);
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

[**CollectionModelEntityModelChatConversation**](CollectionModelEntityModelChatConversation.md)

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


# ChatMessageSearchControllerApi

All URIs are relative to *http://localhost:8080*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**executeSearchChatmessageGet**](ChatMessageSearchControllerApi.md#executesearchchatmessageget) | **GET** /chatMessages/search/deleteByConversationId |  |
| [**executeSearchChatmessageGet1**](ChatMessageSearchControllerApi.md#executesearchchatmessageget1) | **GET** /chatMessages/search/findByConversationIdOrderByCreatedAtAsc |  |
| [**executeSearchChatmessageGet2**](ChatMessageSearchControllerApi.md#executesearchchatmessageget2) | **GET** /chatMessages/search/findByConversationIdOrderByCreatedAtDesc |  |



## executeSearchChatmessageGet

> executeSearchChatmessageGet(conversationId)



### Example

```ts
import {
  Configuration,
  ChatMessageSearchControllerApi,
} from '';
import type { ExecuteSearchChatmessageGetRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new ChatMessageSearchControllerApi();

  const body = {
    // number (optional)
    conversationId: 789,
  } satisfies ExecuteSearchChatmessageGetRequest;

  try {
    const data = await api.executeSearchChatmessageGet(body);
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
| **conversationId** | `number` |  | [Optional] [Defaults to `undefined`] |

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


## executeSearchChatmessageGet1

> CollectionModelEntityModelChatMessage executeSearchChatmessageGet1(conversationId)



### Example

```ts
import {
  Configuration,
  ChatMessageSearchControllerApi,
} from '';
import type { ExecuteSearchChatmessageGet1Request } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new ChatMessageSearchControllerApi();

  const body = {
    // number (optional)
    conversationId: 789,
  } satisfies ExecuteSearchChatmessageGet1Request;

  try {
    const data = await api.executeSearchChatmessageGet1(body);
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
| **conversationId** | `number` |  | [Optional] [Defaults to `undefined`] |

### Return type

[**CollectionModelEntityModelChatMessage**](CollectionModelEntityModelChatMessage.md)

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


## executeSearchChatmessageGet2

> PagedModelEntityModelChatMessage executeSearchChatmessageGet2(conversationId, page, size, sort)



### Example

```ts
import {
  Configuration,
  ChatMessageSearchControllerApi,
} from '';
import type { ExecuteSearchChatmessageGet2Request } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new ChatMessageSearchControllerApi();

  const body = {
    // number (optional)
    conversationId: 789,
    // number | Zero-based page index (0..N) (optional)
    page: 56,
    // number | The size of the page to be returned (optional)
    size: 56,
    // Array<string> | Sorting criteria in the format: property,(asc|desc). Default sort order is ascending. Multiple sort criteria are supported. (optional)
    sort: ...,
  } satisfies ExecuteSearchChatmessageGet2Request;

  try {
    const data = await api.executeSearchChatmessageGet2(body);
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
| **conversationId** | `number` |  | [Optional] [Defaults to `undefined`] |
| **page** | `number` | Zero-based page index (0..N) | [Optional] [Defaults to `0`] |
| **size** | `number` | The size of the page to be returned | [Optional] [Defaults to `20`] |
| **sort** | `Array<string>` | Sorting criteria in the format: property,(asc|desc). Default sort order is ascending. Multiple sort criteria are supported. | [Optional] |

### Return type

[**PagedModelEntityModelChatMessage**](PagedModelEntityModelChatMessage.md)

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


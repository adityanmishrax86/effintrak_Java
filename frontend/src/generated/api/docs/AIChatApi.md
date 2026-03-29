# AIChatApi

All URIs are relative to *http://localhost:8080*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**deleteConversation**](AIChatApi.md#deleteconversation) | **DELETE** /api/chat/conversations/{conversationId} |  |
| [**getConversation**](AIChatApi.md#getconversation) | **GET** /api/chat/conversations/{conversationId} |  |
| [**getUserConversations**](AIChatApi.md#getuserconversations) | **GET** /api/chat/conversations |  |
| [**getUserConversationsPaginated**](AIChatApi.md#getuserconversationspaginated) | **GET** /api/chat/conversations/paginated |  |
| [**processNaturalPrompt**](AIChatApi.md#processnaturalprompt) | **POST** /api/chat/prompt | Process a natural language finance request |
| [**processSimplePrompt**](AIChatApi.md#processsimpleprompt) | **POST** /api/chat/prompt/simple |  |
| [**updateConversation**](AIChatApi.md#updateconversation) | **PUT** /api/chat/conversations/{conversationId} |  |



## deleteConversation

> string deleteConversation(conversationId)



### Example

```ts
import {
  Configuration,
  AIChatApi,
} from '';
import type { DeleteConversationRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const config = new Configuration({ 
    // Configure HTTP bearer authorization: bearerAuth
    accessToken: "YOUR BEARER TOKEN",
  });
  const api = new AIChatApi(config);

  const body = {
    // string
    conversationId: conversationId_example,
  } satisfies DeleteConversationRequest;

  try {
    const data = await api.deleteConversation(body);
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
| **conversationId** | `string` |  | [Defaults to `undefined`] |

### Return type

**string**

### Authorization

[bearerAuth](../README.md#bearerAuth)

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


## getConversation

> ChatConversation getConversation(conversationId)



### Example

```ts
import {
  Configuration,
  AIChatApi,
} from '';
import type { GetConversationRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const config = new Configuration({ 
    // Configure HTTP bearer authorization: bearerAuth
    accessToken: "YOUR BEARER TOKEN",
  });
  const api = new AIChatApi(config);

  const body = {
    // string
    conversationId: conversationId_example,
  } satisfies GetConversationRequest;

  try {
    const data = await api.getConversation(body);
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
| **conversationId** | `string` |  | [Defaults to `undefined`] |

### Return type

[**ChatConversation**](ChatConversation.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

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


## getUserConversations

> Array&lt;ChatConversation&gt; getUserConversations()



### Example

```ts
import {
  Configuration,
  AIChatApi,
} from '';
import type { GetUserConversationsRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const config = new Configuration({ 
    // Configure HTTP bearer authorization: bearerAuth
    accessToken: "YOUR BEARER TOKEN",
  });
  const api = new AIChatApi(config);

  try {
    const data = await api.getUserConversations();
    console.log(data);
  } catch (error) {
    console.error(error);
  }
}

// Run the test
example().catch(console.error);
```

### Parameters

This endpoint does not need any parameter.

### Return type

[**Array&lt;ChatConversation&gt;**](ChatConversation.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

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


## getUserConversationsPaginated

> PageChatConversation getUserConversationsPaginated(page, size)



### Example

```ts
import {
  Configuration,
  AIChatApi,
} from '';
import type { GetUserConversationsPaginatedRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const config = new Configuration({ 
    // Configure HTTP bearer authorization: bearerAuth
    accessToken: "YOUR BEARER TOKEN",
  });
  const api = new AIChatApi(config);

  const body = {
    // number (optional)
    page: 56,
    // number (optional)
    size: 56,
  } satisfies GetUserConversationsPaginatedRequest;

  try {
    const data = await api.getUserConversationsPaginated(body);
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
| **page** | `number` |  | [Optional] [Defaults to `0`] |
| **size** | `number` |  | [Optional] [Defaults to `10`] |

### Return type

[**PageChatConversation**](PageChatConversation.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

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


## processNaturalPrompt

> ChatResponse processNaturalPrompt(naturalPromptRequest)

Process a natural language finance request

### Example

```ts
import {
  Configuration,
  AIChatApi,
} from '';
import type { ProcessNaturalPromptRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const config = new Configuration({ 
    // Configure HTTP bearer authorization: bearerAuth
    accessToken: "YOUR BEARER TOKEN",
  });
  const api = new AIChatApi(config);

  const body = {
    // NaturalPromptRequest
    naturalPromptRequest: ...,
  } satisfies ProcessNaturalPromptRequest;

  try {
    const data = await api.processNaturalPrompt(body);
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
| **naturalPromptRequest** | [NaturalPromptRequest](NaturalPromptRequest.md) |  | |

### Return type

[**ChatResponse**](ChatResponse.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

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


## processSimplePrompt

> ChatResponse processSimplePrompt(prompt)



### Example

```ts
import {
  Configuration,
  AIChatApi,
} from '';
import type { ProcessSimplePromptRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const config = new Configuration({ 
    // Configure HTTP bearer authorization: bearerAuth
    accessToken: "YOUR BEARER TOKEN",
  });
  const api = new AIChatApi(config);

  const body = {
    // string
    prompt: prompt_example,
  } satisfies ProcessSimplePromptRequest;

  try {
    const data = await api.processSimplePrompt(body);
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
| **prompt** | `string` |  | [Defaults to `undefined`] |

### Return type

[**ChatResponse**](ChatResponse.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

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


## updateConversation

> ChatConversation updateConversation(conversationId, requestBody)



### Example

```ts
import {
  Configuration,
  AIChatApi,
} from '';
import type { UpdateConversationRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const config = new Configuration({ 
    // Configure HTTP bearer authorization: bearerAuth
    accessToken: "YOUR BEARER TOKEN",
  });
  const api = new AIChatApi(config);

  const body = {
    // string
    conversationId: conversationId_example,
    // { [key: string]: string; }
    requestBody: ...,
  } satisfies UpdateConversationRequest;

  try {
    const data = await api.updateConversation(body);
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
| **conversationId** | `string` |  | [Defaults to `undefined`] |
| **requestBody** | `{ [key: string]: string; }` |  | |

### Return type

[**ChatConversation**](ChatConversation.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

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


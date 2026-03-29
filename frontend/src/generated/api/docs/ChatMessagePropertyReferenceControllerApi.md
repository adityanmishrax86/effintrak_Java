# ChatMessagePropertyReferenceControllerApi

All URIs are relative to *http://localhost:8080*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**createPropertyReferenceChatmessagePatch**](ChatMessagePropertyReferenceControllerApi.md#createpropertyreferencechatmessagepatch) | **PATCH** /chatMessages/{id}/conversation |  |
| [**createPropertyReferenceChatmessagePut**](ChatMessagePropertyReferenceControllerApi.md#createpropertyreferencechatmessageput) | **PUT** /chatMessages/{id}/conversation |  |
| [**deletePropertyReferenceChatmessageDelete**](ChatMessagePropertyReferenceControllerApi.md#deletepropertyreferencechatmessagedelete) | **DELETE** /chatMessages/{id}/conversation |  |
| [**deletePropertyReferenceIdChatmessageDelete**](ChatMessagePropertyReferenceControllerApi.md#deletepropertyreferenceidchatmessagedelete) | **DELETE** /chatMessages/{id}/conversation/{propertyId} |  |
| [**followPropertyReferenceChatmessageGet**](ChatMessagePropertyReferenceControllerApi.md#followpropertyreferencechatmessageget) | **GET** /chatMessages/{id}/conversation/{propertyId} |  |
| [**followPropertyReferenceChatmessageGet1**](ChatMessagePropertyReferenceControllerApi.md#followpropertyreferencechatmessageget1) | **GET** /chatMessages/{id}/conversation |  |



## createPropertyReferenceChatmessagePatch

> EntityModelChatConversation createPropertyReferenceChatmessagePatch(id, collectionModelObject)



patch-chatconversation-by-chatmessage-Id

### Example

```ts
import {
  Configuration,
  ChatMessagePropertyReferenceControllerApi,
} from '';
import type { CreatePropertyReferenceChatmessagePatchRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new ChatMessagePropertyReferenceControllerApi();

  const body = {
    // string
    id: id_example,
    // CollectionModelObject
    collectionModelObject: ...,
  } satisfies CreatePropertyReferenceChatmessagePatchRequest;

  try {
    const data = await api.createPropertyReferenceChatmessagePatch(body);
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
| **collectionModelObject** | [CollectionModelObject](CollectionModelObject.md) |  | |

### Return type

[**EntityModelChatConversation**](EntityModelChatConversation.md)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: `application/json`, `application/x-spring-data-compact+json`, `text/uri-list`
- **Accept**: `application/hal+json`


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | OK |  -  |
| **204** | No Content |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#api-endpoints) [[Back to Model list]](../README.md#models) [[Back to README]](../README.md)


## createPropertyReferenceChatmessagePut

> EntityModelChatConversation createPropertyReferenceChatmessagePut(id, collectionModelObject)



update-chatconversation-by-chatmessage-Id

### Example

```ts
import {
  Configuration,
  ChatMessagePropertyReferenceControllerApi,
} from '';
import type { CreatePropertyReferenceChatmessagePutRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new ChatMessagePropertyReferenceControllerApi();

  const body = {
    // string
    id: id_example,
    // CollectionModelObject
    collectionModelObject: ...,
  } satisfies CreatePropertyReferenceChatmessagePutRequest;

  try {
    const data = await api.createPropertyReferenceChatmessagePut(body);
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
| **collectionModelObject** | [CollectionModelObject](CollectionModelObject.md) |  | |

### Return type

[**EntityModelChatConversation**](EntityModelChatConversation.md)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: `application/json`, `application/x-spring-data-compact+json`, `text/uri-list`
- **Accept**: `application/hal+json`


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | OK |  -  |
| **201** | Created |  -  |
| **204** | No Content |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#api-endpoints) [[Back to Model list]](../README.md#models) [[Back to README]](../README.md)


## deletePropertyReferenceChatmessageDelete

> deletePropertyReferenceChatmessageDelete(id)



delete-chatconversation-by-chatmessage-Id

### Example

```ts
import {
  Configuration,
  ChatMessagePropertyReferenceControllerApi,
} from '';
import type { DeletePropertyReferenceChatmessageDeleteRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new ChatMessagePropertyReferenceControllerApi();

  const body = {
    // string
    id: id_example,
  } satisfies DeletePropertyReferenceChatmessageDeleteRequest;

  try {
    const data = await api.deletePropertyReferenceChatmessageDelete(body);
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


## deletePropertyReferenceIdChatmessageDelete

> deletePropertyReferenceIdChatmessageDelete(id, propertyId)



delete-chatconversation-by-chatmessage-Id

### Example

```ts
import {
  Configuration,
  ChatMessagePropertyReferenceControllerApi,
} from '';
import type { DeletePropertyReferenceIdChatmessageDeleteRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new ChatMessagePropertyReferenceControllerApi();

  const body = {
    // string
    id: id_example,
    // string
    propertyId: propertyId_example,
  } satisfies DeletePropertyReferenceIdChatmessageDeleteRequest;

  try {
    const data = await api.deletePropertyReferenceIdChatmessageDelete(body);
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
| **propertyId** | `string` |  | [Defaults to `undefined`] |

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


## followPropertyReferenceChatmessageGet

> EntityModelChatConversation followPropertyReferenceChatmessageGet(id, propertyId)



get-chatconversation-by-chatmessage-Id

### Example

```ts
import {
  Configuration,
  ChatMessagePropertyReferenceControllerApi,
} from '';
import type { FollowPropertyReferenceChatmessageGetRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new ChatMessagePropertyReferenceControllerApi();

  const body = {
    // string
    id: id_example,
    // string
    propertyId: propertyId_example,
  } satisfies FollowPropertyReferenceChatmessageGetRequest;

  try {
    const data = await api.followPropertyReferenceChatmessageGet(body);
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
| **propertyId** | `string` |  | [Defaults to `undefined`] |

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


## followPropertyReferenceChatmessageGet1

> EntityModelChatConversation followPropertyReferenceChatmessageGet1(id)



get-chatconversation-by-chatmessage-Id

### Example

```ts
import {
  Configuration,
  ChatMessagePropertyReferenceControllerApi,
} from '';
import type { FollowPropertyReferenceChatmessageGet1Request } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new ChatMessagePropertyReferenceControllerApi();

  const body = {
    // string
    id: id_example,
  } satisfies FollowPropertyReferenceChatmessageGet1Request;

  try {
    const data = await api.followPropertyReferenceChatmessageGet1(body);
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
- **Accept**: `application/hal+json`, `text/uri-list`


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | OK |  -  |
| **404** | Not Found |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#api-endpoints) [[Back to Model list]](../README.md#models) [[Back to README]](../README.md)


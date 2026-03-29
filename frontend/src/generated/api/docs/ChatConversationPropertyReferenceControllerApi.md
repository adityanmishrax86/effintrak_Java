# ChatConversationPropertyReferenceControllerApi

All URIs are relative to *http://localhost:8080*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**createPropertyReferenceChatconversationPatch**](ChatConversationPropertyReferenceControllerApi.md#createpropertyreferencechatconversationpatch) | **PATCH** /chatConversations/{id}/messages |  |
| [**createPropertyReferenceChatconversationPatch1**](ChatConversationPropertyReferenceControllerApi.md#createpropertyreferencechatconversationpatch1) | **PATCH** /chatConversations/{id}/user |  |
| [**createPropertyReferenceChatconversationPut**](ChatConversationPropertyReferenceControllerApi.md#createpropertyreferencechatconversationput) | **PUT** /chatConversations/{id}/messages |  |
| [**createPropertyReferenceChatconversationPut1**](ChatConversationPropertyReferenceControllerApi.md#createpropertyreferencechatconversationput1) | **PUT** /chatConversations/{id}/user |  |
| [**deletePropertyReferenceChatconversationDelete**](ChatConversationPropertyReferenceControllerApi.md#deletepropertyreferencechatconversationdelete) | **DELETE** /chatConversations/{id}/messages |  |
| [**deletePropertyReferenceChatconversationDelete1**](ChatConversationPropertyReferenceControllerApi.md#deletepropertyreferencechatconversationdelete1) | **DELETE** /chatConversations/{id}/user |  |
| [**deletePropertyReferenceIdChatconversationDelete**](ChatConversationPropertyReferenceControllerApi.md#deletepropertyreferenceidchatconversationdelete) | **DELETE** /chatConversations/{id}/messages/{propertyId} |  |
| [**deletePropertyReferenceIdChatconversationDelete1**](ChatConversationPropertyReferenceControllerApi.md#deletepropertyreferenceidchatconversationdelete1) | **DELETE** /chatConversations/{id}/user/{propertyId} |  |
| [**followPropertyReferenceChatconversationGet**](ChatConversationPropertyReferenceControllerApi.md#followpropertyreferencechatconversationget) | **GET** /chatConversations/{id}/messages/{propertyId} |  |
| [**followPropertyReferenceChatconversationGet1**](ChatConversationPropertyReferenceControllerApi.md#followpropertyreferencechatconversationget1) | **GET** /chatConversations/{id}/messages |  |
| [**followPropertyReferenceChatconversationGet2**](ChatConversationPropertyReferenceControllerApi.md#followpropertyreferencechatconversationget2) | **GET** /chatConversations/{id}/user/{propertyId} |  |
| [**followPropertyReferenceChatconversationGet21**](ChatConversationPropertyReferenceControllerApi.md#followpropertyreferencechatconversationget21) | **GET** /chatConversations/{id}/user |  |



## createPropertyReferenceChatconversationPatch

> CollectionModelChatMessage createPropertyReferenceChatconversationPatch(id, collectionModelObject)



patch-chatmessage-by-chatconversation-Id

### Example

```ts
import {
  Configuration,
  ChatConversationPropertyReferenceControllerApi,
} from '';
import type { CreatePropertyReferenceChatconversationPatchRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new ChatConversationPropertyReferenceControllerApi();

  const body = {
    // string
    id: id_example,
    // CollectionModelObject
    collectionModelObject: ...,
  } satisfies CreatePropertyReferenceChatconversationPatchRequest;

  try {
    const data = await api.createPropertyReferenceChatconversationPatch(body);
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

[**CollectionModelChatMessage**](CollectionModelChatMessage.md)

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


## createPropertyReferenceChatconversationPatch1

> EntityModelUser createPropertyReferenceChatconversationPatch1(id, collectionModelObject)



patch-user-by-chatconversation-Id

### Example

```ts
import {
  Configuration,
  ChatConversationPropertyReferenceControllerApi,
} from '';
import type { CreatePropertyReferenceChatconversationPatch1Request } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new ChatConversationPropertyReferenceControllerApi();

  const body = {
    // string
    id: id_example,
    // CollectionModelObject
    collectionModelObject: ...,
  } satisfies CreatePropertyReferenceChatconversationPatch1Request;

  try {
    const data = await api.createPropertyReferenceChatconversationPatch1(body);
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

[**EntityModelUser**](EntityModelUser.md)

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


## createPropertyReferenceChatconversationPut

> CollectionModelChatMessage createPropertyReferenceChatconversationPut(id, collectionModelObject)



update-chatmessage-by-chatconversation-Id

### Example

```ts
import {
  Configuration,
  ChatConversationPropertyReferenceControllerApi,
} from '';
import type { CreatePropertyReferenceChatconversationPutRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new ChatConversationPropertyReferenceControllerApi();

  const body = {
    // string
    id: id_example,
    // CollectionModelObject
    collectionModelObject: ...,
  } satisfies CreatePropertyReferenceChatconversationPutRequest;

  try {
    const data = await api.createPropertyReferenceChatconversationPut(body);
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

[**CollectionModelChatMessage**](CollectionModelChatMessage.md)

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


## createPropertyReferenceChatconversationPut1

> EntityModelUser createPropertyReferenceChatconversationPut1(id, collectionModelObject)



update-user-by-chatconversation-Id

### Example

```ts
import {
  Configuration,
  ChatConversationPropertyReferenceControllerApi,
} from '';
import type { CreatePropertyReferenceChatconversationPut1Request } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new ChatConversationPropertyReferenceControllerApi();

  const body = {
    // string
    id: id_example,
    // CollectionModelObject
    collectionModelObject: ...,
  } satisfies CreatePropertyReferenceChatconversationPut1Request;

  try {
    const data = await api.createPropertyReferenceChatconversationPut1(body);
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

[**EntityModelUser**](EntityModelUser.md)

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


## deletePropertyReferenceChatconversationDelete

> deletePropertyReferenceChatconversationDelete(id)



delete-chatmessage-by-chatconversation-Id

### Example

```ts
import {
  Configuration,
  ChatConversationPropertyReferenceControllerApi,
} from '';
import type { DeletePropertyReferenceChatconversationDeleteRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new ChatConversationPropertyReferenceControllerApi();

  const body = {
    // string
    id: id_example,
  } satisfies DeletePropertyReferenceChatconversationDeleteRequest;

  try {
    const data = await api.deletePropertyReferenceChatconversationDelete(body);
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


## deletePropertyReferenceChatconversationDelete1

> deletePropertyReferenceChatconversationDelete1(id)



delete-user-by-chatconversation-Id

### Example

```ts
import {
  Configuration,
  ChatConversationPropertyReferenceControllerApi,
} from '';
import type { DeletePropertyReferenceChatconversationDelete1Request } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new ChatConversationPropertyReferenceControllerApi();

  const body = {
    // string
    id: id_example,
  } satisfies DeletePropertyReferenceChatconversationDelete1Request;

  try {
    const data = await api.deletePropertyReferenceChatconversationDelete1(body);
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


## deletePropertyReferenceIdChatconversationDelete

> deletePropertyReferenceIdChatconversationDelete(id, propertyId)



delete-chatmessage-by-chatconversation-Id

### Example

```ts
import {
  Configuration,
  ChatConversationPropertyReferenceControllerApi,
} from '';
import type { DeletePropertyReferenceIdChatconversationDeleteRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new ChatConversationPropertyReferenceControllerApi();

  const body = {
    // string
    id: id_example,
    // string
    propertyId: propertyId_example,
  } satisfies DeletePropertyReferenceIdChatconversationDeleteRequest;

  try {
    const data = await api.deletePropertyReferenceIdChatconversationDelete(body);
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


## deletePropertyReferenceIdChatconversationDelete1

> deletePropertyReferenceIdChatconversationDelete1(id, propertyId)



delete-user-by-chatconversation-Id

### Example

```ts
import {
  Configuration,
  ChatConversationPropertyReferenceControllerApi,
} from '';
import type { DeletePropertyReferenceIdChatconversationDelete1Request } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new ChatConversationPropertyReferenceControllerApi();

  const body = {
    // string
    id: id_example,
    // string
    propertyId: propertyId_example,
  } satisfies DeletePropertyReferenceIdChatconversationDelete1Request;

  try {
    const data = await api.deletePropertyReferenceIdChatconversationDelete1(body);
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


## followPropertyReferenceChatconversationGet

> CollectionModelChatMessage followPropertyReferenceChatconversationGet(id, propertyId)



get-chatmessage-by-chatconversation-Id

### Example

```ts
import {
  Configuration,
  ChatConversationPropertyReferenceControllerApi,
} from '';
import type { FollowPropertyReferenceChatconversationGetRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new ChatConversationPropertyReferenceControllerApi();

  const body = {
    // string
    id: id_example,
    // string
    propertyId: propertyId_example,
  } satisfies FollowPropertyReferenceChatconversationGetRequest;

  try {
    const data = await api.followPropertyReferenceChatconversationGet(body);
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

[**CollectionModelChatMessage**](CollectionModelChatMessage.md)

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


## followPropertyReferenceChatconversationGet1

> CollectionModelChatMessage followPropertyReferenceChatconversationGet1(id)



get-chatmessage-by-chatconversation-Id

### Example

```ts
import {
  Configuration,
  ChatConversationPropertyReferenceControllerApi,
} from '';
import type { FollowPropertyReferenceChatconversationGet1Request } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new ChatConversationPropertyReferenceControllerApi();

  const body = {
    // string
    id: id_example,
  } satisfies FollowPropertyReferenceChatconversationGet1Request;

  try {
    const data = await api.followPropertyReferenceChatconversationGet1(body);
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

[**CollectionModelChatMessage**](CollectionModelChatMessage.md)

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


## followPropertyReferenceChatconversationGet2

> EntityModelUser followPropertyReferenceChatconversationGet2(id, propertyId)



get-user-by-chatconversation-Id

### Example

```ts
import {
  Configuration,
  ChatConversationPropertyReferenceControllerApi,
} from '';
import type { FollowPropertyReferenceChatconversationGet2Request } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new ChatConversationPropertyReferenceControllerApi();

  const body = {
    // string
    id: id_example,
    // string
    propertyId: propertyId_example,
  } satisfies FollowPropertyReferenceChatconversationGet2Request;

  try {
    const data = await api.followPropertyReferenceChatconversationGet2(body);
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

[**EntityModelUser**](EntityModelUser.md)

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


## followPropertyReferenceChatconversationGet21

> EntityModelUser followPropertyReferenceChatconversationGet21(id)



get-user-by-chatconversation-Id

### Example

```ts
import {
  Configuration,
  ChatConversationPropertyReferenceControllerApi,
} from '';
import type { FollowPropertyReferenceChatconversationGet21Request } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new ChatConversationPropertyReferenceControllerApi();

  const body = {
    // string
    id: id_example,
  } satisfies FollowPropertyReferenceChatconversationGet21Request;

  try {
    const data = await api.followPropertyReferenceChatconversationGet21(body);
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

[**EntityModelUser**](EntityModelUser.md)

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


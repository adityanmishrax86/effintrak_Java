# SubscriptionEntityControllerApi

All URIs are relative to *http://localhost:8080*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**deleteItemResourceSubscriptionDelete**](SubscriptionEntityControllerApi.md#deleteitemresourcesubscriptiondelete) | **DELETE** /subscriptions/{id} |  |
| [**getCollectionResourceSubscriptionGet1**](SubscriptionEntityControllerApi.md#getcollectionresourcesubscriptionget1) | **GET** /subscriptions |  |
| [**getItemResourceSubscriptionGet**](SubscriptionEntityControllerApi.md#getitemresourcesubscriptionget) | **GET** /subscriptions/{id} |  |
| [**patchItemResourceSubscriptionPatch**](SubscriptionEntityControllerApi.md#patchitemresourcesubscriptionpatch) | **PATCH** /subscriptions/{id} |  |
| [**postCollectionResourceSubscriptionPost**](SubscriptionEntityControllerApi.md#postcollectionresourcesubscriptionpost) | **POST** /subscriptions |  |
| [**putItemResourceSubscriptionPut**](SubscriptionEntityControllerApi.md#putitemresourcesubscriptionput) | **PUT** /subscriptions/{id} |  |



## deleteItemResourceSubscriptionDelete

> deleteItemResourceSubscriptionDelete(id)



delete-subscription

### Example

```ts
import {
  Configuration,
  SubscriptionEntityControllerApi,
} from '';
import type { DeleteItemResourceSubscriptionDeleteRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new SubscriptionEntityControllerApi();

  const body = {
    // string
    id: id_example,
  } satisfies DeleteItemResourceSubscriptionDeleteRequest;

  try {
    const data = await api.deleteItemResourceSubscriptionDelete(body);
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


## getCollectionResourceSubscriptionGet1

> PagedModelEntityModelSubscription getCollectionResourceSubscriptionGet1(page, size, sort)



get-subscription

### Example

```ts
import {
  Configuration,
  SubscriptionEntityControllerApi,
} from '';
import type { GetCollectionResourceSubscriptionGet1Request } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new SubscriptionEntityControllerApi();

  const body = {
    // number | Zero-based page index (0..N) (optional)
    page: 56,
    // number | The size of the page to be returned (optional)
    size: 56,
    // Array<string> | Sorting criteria in the format: property,(asc|desc). Default sort order is ascending. Multiple sort criteria are supported. (optional)
    sort: ...,
  } satisfies GetCollectionResourceSubscriptionGet1Request;

  try {
    const data = await api.getCollectionResourceSubscriptionGet1(body);
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

[**PagedModelEntityModelSubscription**](PagedModelEntityModelSubscription.md)

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


## getItemResourceSubscriptionGet

> EntityModelSubscription getItemResourceSubscriptionGet(id)



get-subscription

### Example

```ts
import {
  Configuration,
  SubscriptionEntityControllerApi,
} from '';
import type { GetItemResourceSubscriptionGetRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new SubscriptionEntityControllerApi();

  const body = {
    // string
    id: id_example,
  } satisfies GetItemResourceSubscriptionGetRequest;

  try {
    const data = await api.getItemResourceSubscriptionGet(body);
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

[**EntityModelSubscription**](EntityModelSubscription.md)

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


## patchItemResourceSubscriptionPatch

> EntityModelSubscription patchItemResourceSubscriptionPatch(id, subscriptionRequestBody)



patch-subscription

### Example

```ts
import {
  Configuration,
  SubscriptionEntityControllerApi,
} from '';
import type { PatchItemResourceSubscriptionPatchRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new SubscriptionEntityControllerApi();

  const body = {
    // string
    id: id_example,
    // SubscriptionRequestBody
    subscriptionRequestBody: ...,
  } satisfies PatchItemResourceSubscriptionPatchRequest;

  try {
    const data = await api.patchItemResourceSubscriptionPatch(body);
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
| **subscriptionRequestBody** | [SubscriptionRequestBody](SubscriptionRequestBody.md) |  | |

### Return type

[**EntityModelSubscription**](EntityModelSubscription.md)

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


## postCollectionResourceSubscriptionPost

> EntityModelSubscription postCollectionResourceSubscriptionPost(subscriptionRequestBody)



create-subscription

### Example

```ts
import {
  Configuration,
  SubscriptionEntityControllerApi,
} from '';
import type { PostCollectionResourceSubscriptionPostRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new SubscriptionEntityControllerApi();

  const body = {
    // SubscriptionRequestBody
    subscriptionRequestBody: ...,
  } satisfies PostCollectionResourceSubscriptionPostRequest;

  try {
    const data = await api.postCollectionResourceSubscriptionPost(body);
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
| **subscriptionRequestBody** | [SubscriptionRequestBody](SubscriptionRequestBody.md) |  | |

### Return type

[**EntityModelSubscription**](EntityModelSubscription.md)

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


## putItemResourceSubscriptionPut

> EntityModelSubscription putItemResourceSubscriptionPut(id, subscriptionRequestBody)



update-subscription

### Example

```ts
import {
  Configuration,
  SubscriptionEntityControllerApi,
} from '';
import type { PutItemResourceSubscriptionPutRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new SubscriptionEntityControllerApi();

  const body = {
    // string
    id: id_example,
    // SubscriptionRequestBody
    subscriptionRequestBody: ...,
  } satisfies PutItemResourceSubscriptionPutRequest;

  try {
    const data = await api.putItemResourceSubscriptionPut(body);
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
| **subscriptionRequestBody** | [SubscriptionRequestBody](SubscriptionRequestBody.md) |  | |

### Return type

[**EntityModelSubscription**](EntityModelSubscription.md)

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


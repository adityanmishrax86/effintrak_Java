# RefreshTokensEntityControllerApi

All URIs are relative to *http://localhost:8080*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**deleteItemResourceRefreshtokensDelete**](RefreshTokensEntityControllerApi.md#deleteitemresourcerefreshtokensdelete) | **DELETE** /refreshTokenses/{id} |  |
| [**getCollectionResourceRefreshtokensGet1**](RefreshTokensEntityControllerApi.md#getcollectionresourcerefreshtokensget1) | **GET** /refreshTokenses |  |
| [**getItemResourceRefreshtokensGet**](RefreshTokensEntityControllerApi.md#getitemresourcerefreshtokensget) | **GET** /refreshTokenses/{id} |  |
| [**patchItemResourceRefreshtokensPatch**](RefreshTokensEntityControllerApi.md#patchitemresourcerefreshtokenspatch) | **PATCH** /refreshTokenses/{id} |  |
| [**postCollectionResourceRefreshtokensPost**](RefreshTokensEntityControllerApi.md#postcollectionresourcerefreshtokenspost) | **POST** /refreshTokenses |  |
| [**putItemResourceRefreshtokensPut**](RefreshTokensEntityControllerApi.md#putitemresourcerefreshtokensput) | **PUT** /refreshTokenses/{id} |  |



## deleteItemResourceRefreshtokensDelete

> deleteItemResourceRefreshtokensDelete(id)



delete-refreshtokens

### Example

```ts
import {
  Configuration,
  RefreshTokensEntityControllerApi,
} from '';
import type { DeleteItemResourceRefreshtokensDeleteRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new RefreshTokensEntityControllerApi();

  const body = {
    // string
    id: id_example,
  } satisfies DeleteItemResourceRefreshtokensDeleteRequest;

  try {
    const data = await api.deleteItemResourceRefreshtokensDelete(body);
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


## getCollectionResourceRefreshtokensGet1

> PagedModelEntityModelRefreshTokens getCollectionResourceRefreshtokensGet1(page, size, sort)



get-refreshtokens

### Example

```ts
import {
  Configuration,
  RefreshTokensEntityControllerApi,
} from '';
import type { GetCollectionResourceRefreshtokensGet1Request } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new RefreshTokensEntityControllerApi();

  const body = {
    // number | Zero-based page index (0..N) (optional)
    page: 56,
    // number | The size of the page to be returned (optional)
    size: 56,
    // Array<string> | Sorting criteria in the format: property,(asc|desc). Default sort order is ascending. Multiple sort criteria are supported. (optional)
    sort: ...,
  } satisfies GetCollectionResourceRefreshtokensGet1Request;

  try {
    const data = await api.getCollectionResourceRefreshtokensGet1(body);
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

[**PagedModelEntityModelRefreshTokens**](PagedModelEntityModelRefreshTokens.md)

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


## getItemResourceRefreshtokensGet

> EntityModelRefreshTokens getItemResourceRefreshtokensGet(id)



get-refreshtokens

### Example

```ts
import {
  Configuration,
  RefreshTokensEntityControllerApi,
} from '';
import type { GetItemResourceRefreshtokensGetRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new RefreshTokensEntityControllerApi();

  const body = {
    // string
    id: id_example,
  } satisfies GetItemResourceRefreshtokensGetRequest;

  try {
    const data = await api.getItemResourceRefreshtokensGet(body);
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

[**EntityModelRefreshTokens**](EntityModelRefreshTokens.md)

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


## patchItemResourceRefreshtokensPatch

> EntityModelRefreshTokens patchItemResourceRefreshtokensPatch(id, refreshTokensRequestBody)



patch-refreshtokens

### Example

```ts
import {
  Configuration,
  RefreshTokensEntityControllerApi,
} from '';
import type { PatchItemResourceRefreshtokensPatchRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new RefreshTokensEntityControllerApi();

  const body = {
    // string
    id: id_example,
    // RefreshTokensRequestBody
    refreshTokensRequestBody: ...,
  } satisfies PatchItemResourceRefreshtokensPatchRequest;

  try {
    const data = await api.patchItemResourceRefreshtokensPatch(body);
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
| **refreshTokensRequestBody** | [RefreshTokensRequestBody](RefreshTokensRequestBody.md) |  | |

### Return type

[**EntityModelRefreshTokens**](EntityModelRefreshTokens.md)

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


## postCollectionResourceRefreshtokensPost

> EntityModelRefreshTokens postCollectionResourceRefreshtokensPost(refreshTokensRequestBody)



create-refreshtokens

### Example

```ts
import {
  Configuration,
  RefreshTokensEntityControllerApi,
} from '';
import type { PostCollectionResourceRefreshtokensPostRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new RefreshTokensEntityControllerApi();

  const body = {
    // RefreshTokensRequestBody
    refreshTokensRequestBody: ...,
  } satisfies PostCollectionResourceRefreshtokensPostRequest;

  try {
    const data = await api.postCollectionResourceRefreshtokensPost(body);
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
| **refreshTokensRequestBody** | [RefreshTokensRequestBody](RefreshTokensRequestBody.md) |  | |

### Return type

[**EntityModelRefreshTokens**](EntityModelRefreshTokens.md)

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


## putItemResourceRefreshtokensPut

> EntityModelRefreshTokens putItemResourceRefreshtokensPut(id, refreshTokensRequestBody)



update-refreshtokens

### Example

```ts
import {
  Configuration,
  RefreshTokensEntityControllerApi,
} from '';
import type { PutItemResourceRefreshtokensPutRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new RefreshTokensEntityControllerApi();

  const body = {
    // string
    id: id_example,
    // RefreshTokensRequestBody
    refreshTokensRequestBody: ...,
  } satisfies PutItemResourceRefreshtokensPutRequest;

  try {
    const data = await api.putItemResourceRefreshtokensPut(body);
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
| **refreshTokensRequestBody** | [RefreshTokensRequestBody](RefreshTokensRequestBody.md) |  | |

### Return type

[**EntityModelRefreshTokens**](EntityModelRefreshTokens.md)

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


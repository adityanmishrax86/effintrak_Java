# UserEntityControllerApi

All URIs are relative to *http://localhost:8080*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**deleteItemResourceUserDelete**](UserEntityControllerApi.md#deleteitemresourceuserdelete) | **DELETE** /users/{id} |  |
| [**getCollectionResourceUserGet1**](UserEntityControllerApi.md#getcollectionresourceuserget1) | **GET** /users |  |
| [**getItemResourceUserGet**](UserEntityControllerApi.md#getitemresourceuserget) | **GET** /users/{id} |  |
| [**patchItemResourceUserPatch**](UserEntityControllerApi.md#patchitemresourceuserpatch) | **PATCH** /users/{id} |  |
| [**postCollectionResourceUserPost**](UserEntityControllerApi.md#postcollectionresourceuserpost) | **POST** /users |  |
| [**putItemResourceUserPut**](UserEntityControllerApi.md#putitemresourceuserput) | **PUT** /users/{id} |  |



## deleteItemResourceUserDelete

> deleteItemResourceUserDelete(id)



delete-user

### Example

```ts
import {
  Configuration,
  UserEntityControllerApi,
} from '';
import type { DeleteItemResourceUserDeleteRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new UserEntityControllerApi();

  const body = {
    // string
    id: id_example,
  } satisfies DeleteItemResourceUserDeleteRequest;

  try {
    const data = await api.deleteItemResourceUserDelete(body);
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


## getCollectionResourceUserGet1

> PagedModelEntityModelUser getCollectionResourceUserGet1(page, size, sort)



get-user

### Example

```ts
import {
  Configuration,
  UserEntityControllerApi,
} from '';
import type { GetCollectionResourceUserGet1Request } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new UserEntityControllerApi();

  const body = {
    // number | Zero-based page index (0..N) (optional)
    page: 56,
    // number | The size of the page to be returned (optional)
    size: 56,
    // Array<string> | Sorting criteria in the format: property,(asc|desc). Default sort order is ascending. Multiple sort criteria are supported. (optional)
    sort: ...,
  } satisfies GetCollectionResourceUserGet1Request;

  try {
    const data = await api.getCollectionResourceUserGet1(body);
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

[**PagedModelEntityModelUser**](PagedModelEntityModelUser.md)

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


## getItemResourceUserGet

> EntityModelUser getItemResourceUserGet(id)



get-user

### Example

```ts
import {
  Configuration,
  UserEntityControllerApi,
} from '';
import type { GetItemResourceUserGetRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new UserEntityControllerApi();

  const body = {
    // string
    id: id_example,
  } satisfies GetItemResourceUserGetRequest;

  try {
    const data = await api.getItemResourceUserGet(body);
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
- **Accept**: `application/hal+json`


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | OK |  -  |
| **404** | Not Found |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#api-endpoints) [[Back to Model list]](../README.md#models) [[Back to README]](../README.md)


## patchItemResourceUserPatch

> EntityModelUser patchItemResourceUserPatch(id, userRequestBody)



patch-user

### Example

```ts
import {
  Configuration,
  UserEntityControllerApi,
} from '';
import type { PatchItemResourceUserPatchRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new UserEntityControllerApi();

  const body = {
    // string
    id: id_example,
    // UserRequestBody
    userRequestBody: ...,
  } satisfies PatchItemResourceUserPatchRequest;

  try {
    const data = await api.patchItemResourceUserPatch(body);
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
| **userRequestBody** | [UserRequestBody](UserRequestBody.md) |  | |

### Return type

[**EntityModelUser**](EntityModelUser.md)

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


## postCollectionResourceUserPost

> EntityModelUser postCollectionResourceUserPost(userRequestBody)



create-user

### Example

```ts
import {
  Configuration,
  UserEntityControllerApi,
} from '';
import type { PostCollectionResourceUserPostRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new UserEntityControllerApi();

  const body = {
    // UserRequestBody
    userRequestBody: ...,
  } satisfies PostCollectionResourceUserPostRequest;

  try {
    const data = await api.postCollectionResourceUserPost(body);
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
| **userRequestBody** | [UserRequestBody](UserRequestBody.md) |  | |

### Return type

[**EntityModelUser**](EntityModelUser.md)

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


## putItemResourceUserPut

> EntityModelUser putItemResourceUserPut(id, userRequestBody)



update-user

### Example

```ts
import {
  Configuration,
  UserEntityControllerApi,
} from '';
import type { PutItemResourceUserPutRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new UserEntityControllerApi();

  const body = {
    // string
    id: id_example,
    // UserRequestBody
    userRequestBody: ...,
  } satisfies PutItemResourceUserPutRequest;

  try {
    const data = await api.putItemResourceUserPut(body);
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
| **userRequestBody** | [UserRequestBody](UserRequestBody.md) |  | |

### Return type

[**EntityModelUser**](EntityModelUser.md)

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


# UserSettingsEntityControllerApi

All URIs are relative to *http://localhost:8080*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**deleteItemResourceUsersettingsDelete**](UserSettingsEntityControllerApi.md#deleteitemresourceusersettingsdelete) | **DELETE** /userSettingses/{id} |  |
| [**getCollectionResourceUsersettingsGet1**](UserSettingsEntityControllerApi.md#getcollectionresourceusersettingsget1) | **GET** /userSettingses |  |
| [**getItemResourceUsersettingsGet**](UserSettingsEntityControllerApi.md#getitemresourceusersettingsget) | **GET** /userSettingses/{id} |  |
| [**patchItemResourceUsersettingsPatch**](UserSettingsEntityControllerApi.md#patchitemresourceusersettingspatch) | **PATCH** /userSettingses/{id} |  |
| [**postCollectionResourceUsersettingsPost**](UserSettingsEntityControllerApi.md#postcollectionresourceusersettingspost) | **POST** /userSettingses |  |
| [**putItemResourceUsersettingsPut**](UserSettingsEntityControllerApi.md#putitemresourceusersettingsput) | **PUT** /userSettingses/{id} |  |



## deleteItemResourceUsersettingsDelete

> deleteItemResourceUsersettingsDelete(id)



delete-usersettings

### Example

```ts
import {
  Configuration,
  UserSettingsEntityControllerApi,
} from '';
import type { DeleteItemResourceUsersettingsDeleteRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new UserSettingsEntityControllerApi();

  const body = {
    // string
    id: id_example,
  } satisfies DeleteItemResourceUsersettingsDeleteRequest;

  try {
    const data = await api.deleteItemResourceUsersettingsDelete(body);
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


## getCollectionResourceUsersettingsGet1

> PagedModelEntityModelUserSettings getCollectionResourceUsersettingsGet1(page, size, sort)



get-usersettings

### Example

```ts
import {
  Configuration,
  UserSettingsEntityControllerApi,
} from '';
import type { GetCollectionResourceUsersettingsGet1Request } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new UserSettingsEntityControllerApi();

  const body = {
    // number | Zero-based page index (0..N) (optional)
    page: 56,
    // number | The size of the page to be returned (optional)
    size: 56,
    // Array<string> | Sorting criteria in the format: property,(asc|desc). Default sort order is ascending. Multiple sort criteria are supported. (optional)
    sort: ...,
  } satisfies GetCollectionResourceUsersettingsGet1Request;

  try {
    const data = await api.getCollectionResourceUsersettingsGet1(body);
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

[**PagedModelEntityModelUserSettings**](PagedModelEntityModelUserSettings.md)

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


## getItemResourceUsersettingsGet

> EntityModelUserSettings getItemResourceUsersettingsGet(id)



get-usersettings

### Example

```ts
import {
  Configuration,
  UserSettingsEntityControllerApi,
} from '';
import type { GetItemResourceUsersettingsGetRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new UserSettingsEntityControllerApi();

  const body = {
    // string
    id: id_example,
  } satisfies GetItemResourceUsersettingsGetRequest;

  try {
    const data = await api.getItemResourceUsersettingsGet(body);
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

[**EntityModelUserSettings**](EntityModelUserSettings.md)

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


## patchItemResourceUsersettingsPatch

> EntityModelUserSettings patchItemResourceUsersettingsPatch(id, userSettingsRequestBody)



patch-usersettings

### Example

```ts
import {
  Configuration,
  UserSettingsEntityControllerApi,
} from '';
import type { PatchItemResourceUsersettingsPatchRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new UserSettingsEntityControllerApi();

  const body = {
    // string
    id: id_example,
    // UserSettingsRequestBody
    userSettingsRequestBody: ...,
  } satisfies PatchItemResourceUsersettingsPatchRequest;

  try {
    const data = await api.patchItemResourceUsersettingsPatch(body);
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
| **userSettingsRequestBody** | [UserSettingsRequestBody](UserSettingsRequestBody.md) |  | |

### Return type

[**EntityModelUserSettings**](EntityModelUserSettings.md)

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


## postCollectionResourceUsersettingsPost

> EntityModelUserSettings postCollectionResourceUsersettingsPost(userSettingsRequestBody)



create-usersettings

### Example

```ts
import {
  Configuration,
  UserSettingsEntityControllerApi,
} from '';
import type { PostCollectionResourceUsersettingsPostRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new UserSettingsEntityControllerApi();

  const body = {
    // UserSettingsRequestBody
    userSettingsRequestBody: ...,
  } satisfies PostCollectionResourceUsersettingsPostRequest;

  try {
    const data = await api.postCollectionResourceUsersettingsPost(body);
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
| **userSettingsRequestBody** | [UserSettingsRequestBody](UserSettingsRequestBody.md) |  | |

### Return type

[**EntityModelUserSettings**](EntityModelUserSettings.md)

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


## putItemResourceUsersettingsPut

> EntityModelUserSettings putItemResourceUsersettingsPut(id, userSettingsRequestBody)



update-usersettings

### Example

```ts
import {
  Configuration,
  UserSettingsEntityControllerApi,
} from '';
import type { PutItemResourceUsersettingsPutRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new UserSettingsEntityControllerApi();

  const body = {
    // string
    id: id_example,
    // UserSettingsRequestBody
    userSettingsRequestBody: ...,
  } satisfies PutItemResourceUsersettingsPutRequest;

  try {
    const data = await api.putItemResourceUsersettingsPut(body);
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
| **userSettingsRequestBody** | [UserSettingsRequestBody](UserSettingsRequestBody.md) |  | |

### Return type

[**EntityModelUserSettings**](EntityModelUserSettings.md)

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


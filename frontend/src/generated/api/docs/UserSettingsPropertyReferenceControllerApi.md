# UserSettingsPropertyReferenceControllerApi

All URIs are relative to *http://localhost:8080*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**createPropertyReferenceUsersettingsPatch**](UserSettingsPropertyReferenceControllerApi.md#createpropertyreferenceusersettingspatch) | **PATCH** /userSettingses/{id}/user |  |
| [**createPropertyReferenceUsersettingsPut**](UserSettingsPropertyReferenceControllerApi.md#createpropertyreferenceusersettingsput) | **PUT** /userSettingses/{id}/user |  |
| [**deletePropertyReferenceIdUsersettingsDelete**](UserSettingsPropertyReferenceControllerApi.md#deletepropertyreferenceidusersettingsdelete) | **DELETE** /userSettingses/{id}/user/{propertyId} |  |
| [**deletePropertyReferenceUsersettingsDelete**](UserSettingsPropertyReferenceControllerApi.md#deletepropertyreferenceusersettingsdelete) | **DELETE** /userSettingses/{id}/user |  |
| [**followPropertyReferenceUsersettingsGet**](UserSettingsPropertyReferenceControllerApi.md#followpropertyreferenceusersettingsget) | **GET** /userSettingses/{id}/user/{propertyId} |  |
| [**followPropertyReferenceUsersettingsGet1**](UserSettingsPropertyReferenceControllerApi.md#followpropertyreferenceusersettingsget1) | **GET** /userSettingses/{id}/user |  |



## createPropertyReferenceUsersettingsPatch

> EntityModelUser createPropertyReferenceUsersettingsPatch(id, collectionModelObject)



patch-user-by-usersettings-Id

### Example

```ts
import {
  Configuration,
  UserSettingsPropertyReferenceControllerApi,
} from '';
import type { CreatePropertyReferenceUsersettingsPatchRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new UserSettingsPropertyReferenceControllerApi();

  const body = {
    // string
    id: id_example,
    // CollectionModelObject
    collectionModelObject: ...,
  } satisfies CreatePropertyReferenceUsersettingsPatchRequest;

  try {
    const data = await api.createPropertyReferenceUsersettingsPatch(body);
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


## createPropertyReferenceUsersettingsPut

> EntityModelUser createPropertyReferenceUsersettingsPut(id, collectionModelObject)



update-user-by-usersettings-Id

### Example

```ts
import {
  Configuration,
  UserSettingsPropertyReferenceControllerApi,
} from '';
import type { CreatePropertyReferenceUsersettingsPutRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new UserSettingsPropertyReferenceControllerApi();

  const body = {
    // string
    id: id_example,
    // CollectionModelObject
    collectionModelObject: ...,
  } satisfies CreatePropertyReferenceUsersettingsPutRequest;

  try {
    const data = await api.createPropertyReferenceUsersettingsPut(body);
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


## deletePropertyReferenceIdUsersettingsDelete

> deletePropertyReferenceIdUsersettingsDelete(id, propertyId)



delete-user-by-usersettings-Id

### Example

```ts
import {
  Configuration,
  UserSettingsPropertyReferenceControllerApi,
} from '';
import type { DeletePropertyReferenceIdUsersettingsDeleteRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new UserSettingsPropertyReferenceControllerApi();

  const body = {
    // string
    id: id_example,
    // string
    propertyId: propertyId_example,
  } satisfies DeletePropertyReferenceIdUsersettingsDeleteRequest;

  try {
    const data = await api.deletePropertyReferenceIdUsersettingsDelete(body);
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


## deletePropertyReferenceUsersettingsDelete

> deletePropertyReferenceUsersettingsDelete(id)



delete-user-by-usersettings-Id

### Example

```ts
import {
  Configuration,
  UserSettingsPropertyReferenceControllerApi,
} from '';
import type { DeletePropertyReferenceUsersettingsDeleteRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new UserSettingsPropertyReferenceControllerApi();

  const body = {
    // string
    id: id_example,
  } satisfies DeletePropertyReferenceUsersettingsDeleteRequest;

  try {
    const data = await api.deletePropertyReferenceUsersettingsDelete(body);
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


## followPropertyReferenceUsersettingsGet

> EntityModelUser followPropertyReferenceUsersettingsGet(id, propertyId)



get-user-by-usersettings-Id

### Example

```ts
import {
  Configuration,
  UserSettingsPropertyReferenceControllerApi,
} from '';
import type { FollowPropertyReferenceUsersettingsGetRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new UserSettingsPropertyReferenceControllerApi();

  const body = {
    // string
    id: id_example,
    // string
    propertyId: propertyId_example,
  } satisfies FollowPropertyReferenceUsersettingsGetRequest;

  try {
    const data = await api.followPropertyReferenceUsersettingsGet(body);
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


## followPropertyReferenceUsersettingsGet1

> EntityModelUser followPropertyReferenceUsersettingsGet1(id)



get-user-by-usersettings-Id

### Example

```ts
import {
  Configuration,
  UserSettingsPropertyReferenceControllerApi,
} from '';
import type { FollowPropertyReferenceUsersettingsGet1Request } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new UserSettingsPropertyReferenceControllerApi();

  const body = {
    // string
    id: id_example,
  } satisfies FollowPropertyReferenceUsersettingsGet1Request;

  try {
    const data = await api.followPropertyReferenceUsersettingsGet1(body);
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


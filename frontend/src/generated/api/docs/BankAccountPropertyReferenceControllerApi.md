# BankAccountPropertyReferenceControllerApi

All URIs are relative to *http://localhost:8080*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**createPropertyReferenceBankaccountPatch**](BankAccountPropertyReferenceControllerApi.md#createpropertyreferencebankaccountpatch) | **PATCH** /bankAccounts/{id}/user |  |
| [**createPropertyReferenceBankaccountPut**](BankAccountPropertyReferenceControllerApi.md#createpropertyreferencebankaccountput) | **PUT** /bankAccounts/{id}/user |  |
| [**deletePropertyReferenceBankaccountDelete**](BankAccountPropertyReferenceControllerApi.md#deletepropertyreferencebankaccountdelete) | **DELETE** /bankAccounts/{id}/user |  |
| [**deletePropertyReferenceIdBankaccountDelete**](BankAccountPropertyReferenceControllerApi.md#deletepropertyreferenceidbankaccountdelete) | **DELETE** /bankAccounts/{id}/user/{propertyId} |  |
| [**followPropertyReferenceBankaccountGet**](BankAccountPropertyReferenceControllerApi.md#followpropertyreferencebankaccountget) | **GET** /bankAccounts/{id}/user/{propertyId} |  |
| [**followPropertyReferenceBankaccountGet1**](BankAccountPropertyReferenceControllerApi.md#followpropertyreferencebankaccountget1) | **GET** /bankAccounts/{id}/user |  |



## createPropertyReferenceBankaccountPatch

> EntityModelUser createPropertyReferenceBankaccountPatch(id, collectionModelObject)



patch-user-by-bankaccount-Id

### Example

```ts
import {
  Configuration,
  BankAccountPropertyReferenceControllerApi,
} from '';
import type { CreatePropertyReferenceBankaccountPatchRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new BankAccountPropertyReferenceControllerApi();

  const body = {
    // string
    id: id_example,
    // CollectionModelObject
    collectionModelObject: ...,
  } satisfies CreatePropertyReferenceBankaccountPatchRequest;

  try {
    const data = await api.createPropertyReferenceBankaccountPatch(body);
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


## createPropertyReferenceBankaccountPut

> EntityModelUser createPropertyReferenceBankaccountPut(id, collectionModelObject)



update-user-by-bankaccount-Id

### Example

```ts
import {
  Configuration,
  BankAccountPropertyReferenceControllerApi,
} from '';
import type { CreatePropertyReferenceBankaccountPutRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new BankAccountPropertyReferenceControllerApi();

  const body = {
    // string
    id: id_example,
    // CollectionModelObject
    collectionModelObject: ...,
  } satisfies CreatePropertyReferenceBankaccountPutRequest;

  try {
    const data = await api.createPropertyReferenceBankaccountPut(body);
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


## deletePropertyReferenceBankaccountDelete

> deletePropertyReferenceBankaccountDelete(id)



delete-user-by-bankaccount-Id

### Example

```ts
import {
  Configuration,
  BankAccountPropertyReferenceControllerApi,
} from '';
import type { DeletePropertyReferenceBankaccountDeleteRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new BankAccountPropertyReferenceControllerApi();

  const body = {
    // string
    id: id_example,
  } satisfies DeletePropertyReferenceBankaccountDeleteRequest;

  try {
    const data = await api.deletePropertyReferenceBankaccountDelete(body);
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


## deletePropertyReferenceIdBankaccountDelete

> deletePropertyReferenceIdBankaccountDelete(id, propertyId)



delete-user-by-bankaccount-Id

### Example

```ts
import {
  Configuration,
  BankAccountPropertyReferenceControllerApi,
} from '';
import type { DeletePropertyReferenceIdBankaccountDeleteRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new BankAccountPropertyReferenceControllerApi();

  const body = {
    // string
    id: id_example,
    // string
    propertyId: propertyId_example,
  } satisfies DeletePropertyReferenceIdBankaccountDeleteRequest;

  try {
    const data = await api.deletePropertyReferenceIdBankaccountDelete(body);
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


## followPropertyReferenceBankaccountGet

> EntityModelUser followPropertyReferenceBankaccountGet(id, propertyId)



get-user-by-bankaccount-Id

### Example

```ts
import {
  Configuration,
  BankAccountPropertyReferenceControllerApi,
} from '';
import type { FollowPropertyReferenceBankaccountGetRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new BankAccountPropertyReferenceControllerApi();

  const body = {
    // string
    id: id_example,
    // string
    propertyId: propertyId_example,
  } satisfies FollowPropertyReferenceBankaccountGetRequest;

  try {
    const data = await api.followPropertyReferenceBankaccountGet(body);
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


## followPropertyReferenceBankaccountGet1

> EntityModelUser followPropertyReferenceBankaccountGet1(id)



get-user-by-bankaccount-Id

### Example

```ts
import {
  Configuration,
  BankAccountPropertyReferenceControllerApi,
} from '';
import type { FollowPropertyReferenceBankaccountGet1Request } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new BankAccountPropertyReferenceControllerApi();

  const body = {
    // string
    id: id_example,
  } satisfies FollowPropertyReferenceBankaccountGet1Request;

  try {
    const data = await api.followPropertyReferenceBankaccountGet1(body);
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


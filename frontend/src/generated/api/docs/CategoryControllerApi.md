# CategoryControllerApi

All URIs are relative to *http://localhost:8080*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**createCategory**](CategoryControllerApi.md#createcategory) | **POST** /api/categories |  |
| [**deleteCategory**](CategoryControllerApi.md#deletecategory) | **DELETE** /api/categories/{id} |  |
| [**getAllCategories**](CategoryControllerApi.md#getallcategories) | **GET** /api/categories |  |
| [**getCategoryById**](CategoryControllerApi.md#getcategorybyid) | **GET** /api/categories/{id} |  |
| [**updateCategory**](CategoryControllerApi.md#updatecategory) | **PUT** /api/categories/{id} |  |



## createCategory

> Category createCategory(category)



### Example

```ts
import {
  Configuration,
  CategoryControllerApi,
} from '';
import type { CreateCategoryRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new CategoryControllerApi();

  const body = {
    // Category
    category: ...,
  } satisfies CreateCategoryRequest;

  try {
    const data = await api.createCategory(body);
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
| **category** | [Category](Category.md) |  | |

### Return type

[**Category**](Category.md)

### Authorization

No authorization required

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


## deleteCategory

> deleteCategory(id)



### Example

```ts
import {
  Configuration,
  CategoryControllerApi,
} from '';
import type { DeleteCategoryRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new CategoryControllerApi();

  const body = {
    // number
    id: 789,
  } satisfies DeleteCategoryRequest;

  try {
    const data = await api.deleteCategory(body);
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
| **id** | `number` |  | [Defaults to `undefined`] |

### Return type

`void` (Empty response body)

### Authorization

No authorization required

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


## getAllCategories

> object getAllCategories()



### Example

```ts
import {
  Configuration,
  CategoryControllerApi,
} from '';
import type { GetAllCategoriesRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new CategoryControllerApi();

  try {
    const data = await api.getAllCategories();
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

**object**

### Authorization

No authorization required

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


## getCategoryById

> Category getCategoryById(id)



### Example

```ts
import {
  Configuration,
  CategoryControllerApi,
} from '';
import type { GetCategoryByIdRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new CategoryControllerApi();

  const body = {
    // number
    id: 789,
  } satisfies GetCategoryByIdRequest;

  try {
    const data = await api.getCategoryById(body);
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
| **id** | `number` |  | [Defaults to `undefined`] |

### Return type

[**Category**](Category.md)

### Authorization

No authorization required

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


## updateCategory

> object updateCategory(id, updateCategoryRequestDTO)



### Example

```ts
import {
  Configuration,
  CategoryControllerApi,
} from '';
import type { UpdateCategoryRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new CategoryControllerApi();

  const body = {
    // number
    id: 789,
    // UpdateCategoryRequestDTO
    updateCategoryRequestDTO: ...,
  } satisfies UpdateCategoryRequest;

  try {
    const data = await api.updateCategory(body);
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
| **id** | `number` |  | [Defaults to `undefined`] |
| **updateCategoryRequestDTO** | [UpdateCategoryRequestDTO](UpdateCategoryRequestDTO.md) |  | |

### Return type

**object**

### Authorization

No authorization required

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


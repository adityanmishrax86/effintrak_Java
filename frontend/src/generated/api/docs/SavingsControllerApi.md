# SavingsControllerApi

All URIs are relative to *http://localhost:8080*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**createSavings**](SavingsControllerApi.md#createsavings) | **POST** /api/savings |  |
| [**deleteSavings**](SavingsControllerApi.md#deletesavings) | **DELETE** /api/savings/{id} |  |
| [**getSavingsById**](SavingsControllerApi.md#getsavingsbyid) | **GET** /api/savings/{id} |  |
| [**getSavingsByUserId**](SavingsControllerApi.md#getsavingsbyuserid) | **GET** /api/savings/user/{userId} |  |
| [**updateSavings**](SavingsControllerApi.md#updatesavings) | **PUT** /api/savings/{id} |  |



## createSavings

> object createSavings(savingsRequestDTO)



### Example

```ts
import {
  Configuration,
  SavingsControllerApi,
} from '';
import type { CreateSavingsRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new SavingsControllerApi();

  const body = {
    // SavingsRequestDTO
    savingsRequestDTO: ...,
  } satisfies CreateSavingsRequest;

  try {
    const data = await api.createSavings(body);
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
| **savingsRequestDTO** | [SavingsRequestDTO](SavingsRequestDTO.md) |  | |

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


## deleteSavings

> object deleteSavings(id)



### Example

```ts
import {
  Configuration,
  SavingsControllerApi,
} from '';
import type { DeleteSavingsRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new SavingsControllerApi();

  const body = {
    // number
    id: 789,
  } satisfies DeleteSavingsRequest;

  try {
    const data = await api.deleteSavings(body);
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


## getSavingsById

> object getSavingsById(id)



### Example

```ts
import {
  Configuration,
  SavingsControllerApi,
} from '';
import type { GetSavingsByIdRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new SavingsControllerApi();

  const body = {
    // number
    id: 789,
  } satisfies GetSavingsByIdRequest;

  try {
    const data = await api.getSavingsById(body);
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


## getSavingsByUserId

> object getSavingsByUserId(userId)



### Example

```ts
import {
  Configuration,
  SavingsControllerApi,
} from '';
import type { GetSavingsByUserIdRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new SavingsControllerApi();

  const body = {
    // number
    userId: 789,
  } satisfies GetSavingsByUserIdRequest;

  try {
    const data = await api.getSavingsByUserId(body);
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
| **userId** | `number` |  | [Defaults to `undefined`] |

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


## updateSavings

> object updateSavings(id, updateSavingsRequestDTO)



### Example

```ts
import {
  Configuration,
  SavingsControllerApi,
} from '';
import type { UpdateSavingsRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new SavingsControllerApi();

  const body = {
    // number
    id: 789,
    // UpdateSavingsRequestDTO
    updateSavingsRequestDTO: ...,
  } satisfies UpdateSavingsRequest;

  try {
    const data = await api.updateSavings(body);
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
| **updateSavingsRequestDTO** | [UpdateSavingsRequestDTO](UpdateSavingsRequestDTO.md) |  | |

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


# UserControllerApi

All URIs are relative to *http://localhost:8080*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**authenticateUser**](UserControllerApi.md#authenticateuser) | **POST** /api/v1/users/login |  |
| [**getUserProfiile**](UserControllerApi.md#getuserprofiile) | **GET** /api/v1/users/profile |  |
| [**logout**](UserControllerApi.md#logout) | **POST** /api/v1/users/logout |  |
| [**refreshToken**](UserControllerApi.md#refreshtoken) | **POST** /api/v1/users/refresh |  |
| [**registerUser**](UserControllerApi.md#registeruser) | **POST** /api/v1/users/register |  |



## authenticateUser

> object authenticateUser(loginRequestDTO)



### Example

```ts
import {
  Configuration,
  UserControllerApi,
} from '';
import type { AuthenticateUserRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new UserControllerApi();

  const body = {
    // LoginRequestDTO
    loginRequestDTO: ...,
  } satisfies AuthenticateUserRequest;

  try {
    const data = await api.authenticateUser(body);
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
| **loginRequestDTO** | [LoginRequestDTO](LoginRequestDTO.md) |  | |

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


## getUserProfiile

> { [key: string]: string; } getUserProfiile()



### Example

```ts
import {
  Configuration,
  UserControllerApi,
} from '';
import type { GetUserProfiileRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new UserControllerApi();

  try {
    const data = await api.getUserProfiile();
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

**{ [key: string]: string; }**

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


## logout

> object logout()



### Example

```ts
import {
  Configuration,
  UserControllerApi,
} from '';
import type { LogoutRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new UserControllerApi();

  try {
    const data = await api.logout();
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


## refreshToken

> object refreshToken(requestBody)



### Example

```ts
import {
  Configuration,
  UserControllerApi,
} from '';
import type { RefreshTokenRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new UserControllerApi();

  const body = {
    // { [key: string]: string; }
    requestBody: ...,
  } satisfies RefreshTokenRequest;

  try {
    const data = await api.refreshToken(body);
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
| **requestBody** | `{ [key: string]: string; }` |  | |

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


## registerUser

> string registerUser(registerRequest)



### Example

```ts
import {
  Configuration,
  UserControllerApi,
} from '';
import type { RegisterUserRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new UserControllerApi();

  const body = {
    // RegisterRequest
    registerRequest: ...,
  } satisfies RegisterUserRequest;

  try {
    const data = await api.registerUser(body);
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
| **registerRequest** | [RegisterRequest](RegisterRequest.md) |  | |

### Return type

**string**

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


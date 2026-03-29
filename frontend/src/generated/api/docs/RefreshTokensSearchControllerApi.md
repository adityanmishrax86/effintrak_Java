# RefreshTokensSearchControllerApi

All URIs are relative to *http://localhost:8080*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**executeSearchRefreshtokensGet**](RefreshTokensSearchControllerApi.md#executesearchrefreshtokensget) | **GET** /refreshTokenses/search/deleteByUser |  |
| [**executeSearchRefreshtokensGet1**](RefreshTokensSearchControllerApi.md#executesearchrefreshtokensget1) | **GET** /refreshTokenses/search/findByToken |  |
| [**executeSearchRefreshtokensGet2**](RefreshTokensSearchControllerApi.md#executesearchrefreshtokensget2) | **GET** /refreshTokenses/search/findByUserId |  |



## executeSearchRefreshtokensGet

> executeSearchRefreshtokensGet(user)



### Example

```ts
import {
  Configuration,
  RefreshTokensSearchControllerApi,
} from '';
import type { ExecuteSearchRefreshtokensGetRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new RefreshTokensSearchControllerApi();

  const body = {
    // User (optional)
    user: ...,
  } satisfies ExecuteSearchRefreshtokensGetRequest;

  try {
    const data = await api.executeSearchRefreshtokensGet(body);
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
| **user** | [](.md) |  | [Optional] [Defaults to `undefined`] |

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
| **200** | OK |  -  |
| **404** | Not Found |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#api-endpoints) [[Back to Model list]](../README.md#models) [[Back to README]](../README.md)


## executeSearchRefreshtokensGet1

> EntityModelRefreshTokens executeSearchRefreshtokensGet1(token)



### Example

```ts
import {
  Configuration,
  RefreshTokensSearchControllerApi,
} from '';
import type { ExecuteSearchRefreshtokensGet1Request } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new RefreshTokensSearchControllerApi();

  const body = {
    // string (optional)
    token: token_example,
  } satisfies ExecuteSearchRefreshtokensGet1Request;

  try {
    const data = await api.executeSearchRefreshtokensGet1(body);
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
| **token** | `string` |  | [Optional] [Defaults to `undefined`] |

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


## executeSearchRefreshtokensGet2

> EntityModelRefreshTokens executeSearchRefreshtokensGet2(userId)



### Example

```ts
import {
  Configuration,
  RefreshTokensSearchControllerApi,
} from '';
import type { ExecuteSearchRefreshtokensGet2Request } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new RefreshTokensSearchControllerApi();

  const body = {
    // number (optional)
    userId: 789,
  } satisfies ExecuteSearchRefreshtokensGet2Request;

  try {
    const data = await api.executeSearchRefreshtokensGet2(body);
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
| **userId** | `number` |  | [Optional] [Defaults to `undefined`] |

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


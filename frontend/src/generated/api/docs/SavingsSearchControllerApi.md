# SavingsSearchControllerApi

All URIs are relative to *http://localhost:8080*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**executeSearchSavingsGet**](SavingsSearchControllerApi.md#executesearchsavingsget) | **GET** /savingses/search/findByIdAndUserId |  |
| [**executeSearchSavingsGet1**](SavingsSearchControllerApi.md#executesearchsavingsget1) | **GET** /savingses/search/findByUserId |  |



## executeSearchSavingsGet

> EntityModelSavings executeSearchSavingsGet(id, userId)



### Example

```ts
import {
  Configuration,
  SavingsSearchControllerApi,
} from '';
import type { ExecuteSearchSavingsGetRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new SavingsSearchControllerApi();

  const body = {
    // number (optional)
    id: 789,
    // number (optional)
    userId: 789,
  } satisfies ExecuteSearchSavingsGetRequest;

  try {
    const data = await api.executeSearchSavingsGet(body);
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
| **id** | `number` |  | [Optional] [Defaults to `undefined`] |
| **userId** | `number` |  | [Optional] [Defaults to `undefined`] |

### Return type

[**EntityModelSavings**](EntityModelSavings.md)

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


## executeSearchSavingsGet1

> CollectionModelEntityModelSavings executeSearchSavingsGet1(userId)



### Example

```ts
import {
  Configuration,
  SavingsSearchControllerApi,
} from '';
import type { ExecuteSearchSavingsGet1Request } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new SavingsSearchControllerApi();

  const body = {
    // number (optional)
    userId: 789,
  } satisfies ExecuteSearchSavingsGet1Request;

  try {
    const data = await api.executeSearchSavingsGet1(body);
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

[**CollectionModelEntityModelSavings**](CollectionModelEntityModelSavings.md)

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


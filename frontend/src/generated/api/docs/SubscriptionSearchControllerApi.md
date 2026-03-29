# SubscriptionSearchControllerApi

All URIs are relative to *http://localhost:8080*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**executeSearchSubscriptionGet**](SubscriptionSearchControllerApi.md#executesearchsubscriptionget) | **GET** /subscriptions/search/findByIdAndUserId |  |
| [**executeSearchSubscriptionGet1**](SubscriptionSearchControllerApi.md#executesearchsubscriptionget1) | **GET** /subscriptions/search/findByUserId |  |



## executeSearchSubscriptionGet

> EntityModelSubscription executeSearchSubscriptionGet(id, userId)



### Example

```ts
import {
  Configuration,
  SubscriptionSearchControllerApi,
} from '';
import type { ExecuteSearchSubscriptionGetRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new SubscriptionSearchControllerApi();

  const body = {
    // number (optional)
    id: 789,
    // number (optional)
    userId: 789,
  } satisfies ExecuteSearchSubscriptionGetRequest;

  try {
    const data = await api.executeSearchSubscriptionGet(body);
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

[**EntityModelSubscription**](EntityModelSubscription.md)

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


## executeSearchSubscriptionGet1

> CollectionModelEntityModelSubscription executeSearchSubscriptionGet1(userId)



### Example

```ts
import {
  Configuration,
  SubscriptionSearchControllerApi,
} from '';
import type { ExecuteSearchSubscriptionGet1Request } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new SubscriptionSearchControllerApi();

  const body = {
    // number (optional)
    userId: 789,
  } satisfies ExecuteSearchSubscriptionGet1Request;

  try {
    const data = await api.executeSearchSubscriptionGet1(body);
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

[**CollectionModelEntityModelSubscription**](CollectionModelEntityModelSubscription.md)

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


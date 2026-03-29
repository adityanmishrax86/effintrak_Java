# BudgetSearchControllerApi

All URIs are relative to *http://localhost:8080*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**executeSearchBudgetGet**](BudgetSearchControllerApi.md#executesearchbudgetget) | **GET** /budgets/search/findByIdAndUserId |  |
| [**executeSearchBudgetGet1**](BudgetSearchControllerApi.md#executesearchbudgetget1) | **GET** /budgets/search/findByUserId |  |
| [**executeSearchBudgetGet2**](BudgetSearchControllerApi.md#executesearchbudgetget2) | **GET** /budgets/search/findByUserIdAndCategoryId |  |



## executeSearchBudgetGet

> EntityModelBudget executeSearchBudgetGet(id, userId)



### Example

```ts
import {
  Configuration,
  BudgetSearchControllerApi,
} from '';
import type { ExecuteSearchBudgetGetRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new BudgetSearchControllerApi();

  const body = {
    // number (optional)
    id: 789,
    // number (optional)
    userId: 789,
  } satisfies ExecuteSearchBudgetGetRequest;

  try {
    const data = await api.executeSearchBudgetGet(body);
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

[**EntityModelBudget**](EntityModelBudget.md)

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


## executeSearchBudgetGet1

> CollectionModelEntityModelBudget executeSearchBudgetGet1(userId)



### Example

```ts
import {
  Configuration,
  BudgetSearchControllerApi,
} from '';
import type { ExecuteSearchBudgetGet1Request } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new BudgetSearchControllerApi();

  const body = {
    // number (optional)
    userId: 789,
  } satisfies ExecuteSearchBudgetGet1Request;

  try {
    const data = await api.executeSearchBudgetGet1(body);
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

[**CollectionModelEntityModelBudget**](CollectionModelEntityModelBudget.md)

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


## executeSearchBudgetGet2

> EntityModelBudget executeSearchBudgetGet2(userId, categoryId)



### Example

```ts
import {
  Configuration,
  BudgetSearchControllerApi,
} from '';
import type { ExecuteSearchBudgetGet2Request } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new BudgetSearchControllerApi();

  const body = {
    // number (optional)
    userId: 789,
    // number (optional)
    categoryId: 789,
  } satisfies ExecuteSearchBudgetGet2Request;

  try {
    const data = await api.executeSearchBudgetGet2(body);
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
| **categoryId** | `number` |  | [Optional] [Defaults to `undefined`] |

### Return type

[**EntityModelBudget**](EntityModelBudget.md)

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


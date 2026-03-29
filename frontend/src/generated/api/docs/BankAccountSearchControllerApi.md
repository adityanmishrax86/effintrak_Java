# BankAccountSearchControllerApi

All URIs are relative to *http://localhost:8080*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**executeSearchBankaccountGet**](BankAccountSearchControllerApi.md#executesearchbankaccountget) | **GET** /bankAccounts/search/findByIdAndUserId |  |
| [**executeSearchBankaccountGet1**](BankAccountSearchControllerApi.md#executesearchbankaccountget1) | **GET** /bankAccounts/search/findByuser_id |  |



## executeSearchBankaccountGet

> EntityModelBankAccount executeSearchBankaccountGet(id, userId)



### Example

```ts
import {
  Configuration,
  BankAccountSearchControllerApi,
} from '';
import type { ExecuteSearchBankaccountGetRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new BankAccountSearchControllerApi();

  const body = {
    // number (optional)
    id: 789,
    // number (optional)
    userId: 789,
  } satisfies ExecuteSearchBankaccountGetRequest;

  try {
    const data = await api.executeSearchBankaccountGet(body);
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

[**EntityModelBankAccount**](EntityModelBankAccount.md)

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


## executeSearchBankaccountGet1

> CollectionModelEntityModelBankAccount executeSearchBankaccountGet1(userId)



### Example

```ts
import {
  Configuration,
  BankAccountSearchControllerApi,
} from '';
import type { ExecuteSearchBankaccountGet1Request } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new BankAccountSearchControllerApi();

  const body = {
    // number (optional)
    userId: 789,
  } satisfies ExecuteSearchBankaccountGet1Request;

  try {
    const data = await api.executeSearchBankaccountGet1(body);
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

[**CollectionModelEntityModelBankAccount**](CollectionModelEntityModelBankAccount.md)

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


# BankAccountControllerApi

All URIs are relative to *http://localhost:8080*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**createBankAccount**](BankAccountControllerApi.md#createbankaccount) | **POST** /api/bankaccounts |  |
| [**deleteBankAccount**](BankAccountControllerApi.md#deletebankaccount) | **DELETE** /api/bankaccounts/{id} |  |
| [**getAllBankAccounts**](BankAccountControllerApi.md#getallbankaccounts) | **GET** /api/bankaccounts/{userId} |  |
| [**updateBankAccount**](BankAccountControllerApi.md#updatebankaccount) | **PUT** /api/bankaccounts/{id} |  |



## createBankAccount

> object createBankAccount(bankAccountCreateRequestDTO)



### Example

```ts
import {
  Configuration,
  BankAccountControllerApi,
} from '';
import type { CreateBankAccountRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new BankAccountControllerApi();

  const body = {
    // BankAccountCreateRequestDTO
    bankAccountCreateRequestDTO: ...,
  } satisfies CreateBankAccountRequest;

  try {
    const data = await api.createBankAccount(body);
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
| **bankAccountCreateRequestDTO** | [BankAccountCreateRequestDTO](BankAccountCreateRequestDTO.md) |  | |

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


## deleteBankAccount

> object deleteBankAccount(id)



### Example

```ts
import {
  Configuration,
  BankAccountControllerApi,
} from '';
import type { DeleteBankAccountRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new BankAccountControllerApi();

  const body = {
    // number
    id: 789,
  } satisfies DeleteBankAccountRequest;

  try {
    const data = await api.deleteBankAccount(body);
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


## getAllBankAccounts

> object getAllBankAccounts(userId)



### Example

```ts
import {
  Configuration,
  BankAccountControllerApi,
} from '';
import type { GetAllBankAccountsRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new BankAccountControllerApi();

  const body = {
    // number
    userId: 789,
  } satisfies GetAllBankAccountsRequest;

  try {
    const data = await api.getAllBankAccounts(body);
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


## updateBankAccount

> object updateBankAccount(id, updateBankAccountRequestDTO)



### Example

```ts
import {
  Configuration,
  BankAccountControllerApi,
} from '';
import type { UpdateBankAccountRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new BankAccountControllerApi();

  const body = {
    // number
    id: 789,
    // UpdateBankAccountRequestDTO
    updateBankAccountRequestDTO: ...,
  } satisfies UpdateBankAccountRequest;

  try {
    const data = await api.updateBankAccount(body);
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
| **updateBankAccountRequestDTO** | [UpdateBankAccountRequestDTO](UpdateBankAccountRequestDTO.md) |  | |

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


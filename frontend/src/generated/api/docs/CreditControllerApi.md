# CreditControllerApi

All URIs are relative to *http://localhost:8080*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**createCredit**](CreditControllerApi.md#createcredit) | **POST** /api/credits |  |
| [**deleteCredit**](CreditControllerApi.md#deletecredit) | **DELETE** /api/credits/{id} |  |
| [**getCreditById**](CreditControllerApi.md#getcreditbyid) | **GET** /api/credits/{id} |  |
| [**getCreditsByUserId**](CreditControllerApi.md#getcreditsbyuserid) | **GET** /api/credits/user/{userId} |  |
| [**getCreditsByUserIdBetweenDates**](CreditControllerApi.md#getcreditsbyuseridbetweendates) | **GET** /api/credits/user/{userId}/filter |  |
| [**getOverdueCredits**](CreditControllerApi.md#getoverduecredits) | **GET** /api/credits/user/{userId}/overdue |  |
| [**getUpcomingCredits**](CreditControllerApi.md#getupcomingcredits) | **GET** /api/credits/user/{userId}/upcoming |  |
| [**updateCredit**](CreditControllerApi.md#updatecredit) | **PUT** /api/credits/{id} |  |



## createCredit

> object createCredit(creditRequestDTO)



### Example

```ts
import {
  Configuration,
  CreditControllerApi,
} from '';
import type { CreateCreditRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new CreditControllerApi();

  const body = {
    // CreditRequestDTO
    creditRequestDTO: ...,
  } satisfies CreateCreditRequest;

  try {
    const data = await api.createCredit(body);
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
| **creditRequestDTO** | [CreditRequestDTO](CreditRequestDTO.md) |  | |

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


## deleteCredit

> object deleteCredit(id)



### Example

```ts
import {
  Configuration,
  CreditControllerApi,
} from '';
import type { DeleteCreditRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new CreditControllerApi();

  const body = {
    // number
    id: 789,
  } satisfies DeleteCreditRequest;

  try {
    const data = await api.deleteCredit(body);
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


## getCreditById

> object getCreditById(id)



### Example

```ts
import {
  Configuration,
  CreditControllerApi,
} from '';
import type { GetCreditByIdRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new CreditControllerApi();

  const body = {
    // number
    id: 789,
  } satisfies GetCreditByIdRequest;

  try {
    const data = await api.getCreditById(body);
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


## getCreditsByUserId

> object getCreditsByUserId(userId)



### Example

```ts
import {
  Configuration,
  CreditControllerApi,
} from '';
import type { GetCreditsByUserIdRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new CreditControllerApi();

  const body = {
    // number
    userId: 789,
  } satisfies GetCreditsByUserIdRequest;

  try {
    const data = await api.getCreditsByUserId(body);
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


## getCreditsByUserIdBetweenDates

> object getCreditsByUserIdBetweenDates(userId, start, end)



### Example

```ts
import {
  Configuration,
  CreditControllerApi,
} from '';
import type { GetCreditsByUserIdBetweenDatesRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new CreditControllerApi();

  const body = {
    // number
    userId: 789,
    // string
    start: start_example,
    // string
    end: end_example,
  } satisfies GetCreditsByUserIdBetweenDatesRequest;

  try {
    const data = await api.getCreditsByUserIdBetweenDates(body);
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
| **start** | `string` |  | [Defaults to `undefined`] |
| **end** | `string` |  | [Defaults to `undefined`] |

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


## getOverdueCredits

> object getOverdueCredits(userId)



### Example

```ts
import {
  Configuration,
  CreditControllerApi,
} from '';
import type { GetOverdueCreditsRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new CreditControllerApi();

  const body = {
    // number
    userId: 789,
  } satisfies GetOverdueCreditsRequest;

  try {
    const data = await api.getOverdueCredits(body);
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


## getUpcomingCredits

> object getUpcomingCredits(userId, days)



### Example

```ts
import {
  Configuration,
  CreditControllerApi,
} from '';
import type { GetUpcomingCreditsRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new CreditControllerApi();

  const body = {
    // number
    userId: 789,
    // number (optional)
    days: 56,
  } satisfies GetUpcomingCreditsRequest;

  try {
    const data = await api.getUpcomingCredits(body);
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
| **days** | `number` |  | [Optional] [Defaults to `30`] |

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


## updateCredit

> object updateCredit(id, updateCreditRequestDTO)



### Example

```ts
import {
  Configuration,
  CreditControllerApi,
} from '';
import type { UpdateCreditRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new CreditControllerApi();

  const body = {
    // number
    id: 789,
    // UpdateCreditRequestDTO
    updateCreditRequestDTO: ...,
  } satisfies UpdateCreditRequest;

  try {
    const data = await api.updateCredit(body);
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
| **updateCreditRequestDTO** | [UpdateCreditRequestDTO](UpdateCreditRequestDTO.md) |  | |

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


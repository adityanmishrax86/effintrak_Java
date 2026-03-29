# UserSettingsApi

All URIs are relative to *http://localhost:8080*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**getCurrentUserSettings**](UserSettingsApi.md#getcurrentusersettings) | **GET** /api/v1/user-settings/me | Get current user settings |
| [**updateCurrentUserSettings**](UserSettingsApi.md#updatecurrentusersettings) | **PUT** /api/v1/user-settings/me | Create or update current user settings |



## getCurrentUserSettings

> UserSettingsResponse getCurrentUserSettings()

Get current user settings

### Example

```ts
import {
  Configuration,
  UserSettingsApi,
} from '';
import type { GetCurrentUserSettingsRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const config = new Configuration({ 
    // Configure HTTP bearer authorization: bearerAuth
    accessToken: "YOUR BEARER TOKEN",
  });
  const api = new UserSettingsApi(config);

  try {
    const data = await api.getCurrentUserSettings();
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

[**UserSettingsResponse**](UserSettingsResponse.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

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


## updateCurrentUserSettings

> UserSettingsResponse updateCurrentUserSettings(userSettingsRequest)

Create or update current user settings

### Example

```ts
import {
  Configuration,
  UserSettingsApi,
} from '';
import type { UpdateCurrentUserSettingsRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const config = new Configuration({ 
    // Configure HTTP bearer authorization: bearerAuth
    accessToken: "YOUR BEARER TOKEN",
  });
  const api = new UserSettingsApi(config);

  const body = {
    // UserSettingsRequest
    userSettingsRequest: ...,
  } satisfies UpdateCurrentUserSettingsRequest;

  try {
    const data = await api.updateCurrentUserSettings(body);
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
| **userSettingsRequest** | [UserSettingsRequest](UserSettingsRequest.md) |  | |

### Return type

[**UserSettingsResponse**](UserSettingsResponse.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

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


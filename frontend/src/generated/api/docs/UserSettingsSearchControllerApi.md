# UserSettingsSearchControllerApi

All URIs are relative to *http://localhost:8080*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**executeSearchUsersettingsGet**](UserSettingsSearchControllerApi.md#executesearchusersettingsget) | **GET** /userSettingses/search/findByUserId |  |



## executeSearchUsersettingsGet

> EntityModelUserSettings executeSearchUsersettingsGet(userId)



### Example

```ts
import {
  Configuration,
  UserSettingsSearchControllerApi,
} from '';
import type { ExecuteSearchUsersettingsGetRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new UserSettingsSearchControllerApi();

  const body = {
    // number (optional)
    userId: 789,
  } satisfies ExecuteSearchUsersettingsGetRequest;

  try {
    const data = await api.executeSearchUsersettingsGet(body);
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

[**EntityModelUserSettings**](EntityModelUserSettings.md)

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


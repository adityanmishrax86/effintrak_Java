# UserSearchControllerApi

All URIs are relative to *http://localhost:8080*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**executeSearchUserGet**](UserSearchControllerApi.md#executesearchuserget) | **GET** /users/search/findByEmail |  |



## executeSearchUserGet

> EntityModelUser executeSearchUserGet(email)



### Example

```ts
import {
  Configuration,
  UserSearchControllerApi,
} from '';
import type { ExecuteSearchUserGetRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new UserSearchControllerApi();

  const body = {
    // string (optional)
    email: email_example,
  } satisfies ExecuteSearchUserGetRequest;

  try {
    const data = await api.executeSearchUserGet(body);
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
| **email** | `string` |  | [Optional] [Defaults to `undefined`] |

### Return type

[**EntityModelUser**](EntityModelUser.md)

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


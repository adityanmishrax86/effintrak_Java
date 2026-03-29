# CategorySearchControllerApi

All URIs are relative to *http://localhost:8080*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**executeSearchCategoryGet**](CategorySearchControllerApi.md#executesearchcategoryget) | **GET** /categories/search/findByNameIgnoreCase |  |



## executeSearchCategoryGet

> EntityModelCategory executeSearchCategoryGet(name)



### Example

```ts
import {
  Configuration,
  CategorySearchControllerApi,
} from '';
import type { ExecuteSearchCategoryGetRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const api = new CategorySearchControllerApi();

  const body = {
    // string (optional)
    name: name_example,
  } satisfies ExecuteSearchCategoryGetRequest;

  try {
    const data = await api.executeSearchCategoryGet(body);
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
| **name** | `string` |  | [Optional] [Defaults to `undefined`] |

### Return type

[**EntityModelCategory**](EntityModelCategory.md)

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


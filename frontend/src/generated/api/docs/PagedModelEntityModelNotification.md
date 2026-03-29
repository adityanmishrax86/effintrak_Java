
# PagedModelEntityModelNotification


## Properties

Name | Type
------------ | -------------
`embedded` | [PagedModelEntityModelNotificationEmbedded](PagedModelEntityModelNotificationEmbedded.md)
`links` | [{ [key: string]: Link; }](Link.md)
`page` | [PageMetadata](PageMetadata.md)

## Example

```typescript
import type { PagedModelEntityModelNotification } from ''

// TODO: Update the object below with actual values
const example = {
  "embedded": null,
  "links": null,
  "page": null,
} satisfies PagedModelEntityModelNotification

console.log(example)

// Convert the instance to a JSON string
const exampleJSON: string = JSON.stringify(example)
console.log(exampleJSON)

// Parse the JSON string back to an object
const exampleParsed = JSON.parse(exampleJSON) as PagedModelEntityModelNotification
console.log(exampleParsed)
```

[[Back to top]](#) [[Back to API list]](../README.md#api-endpoints) [[Back to Model list]](../README.md#models) [[Back to README]](../README.md)



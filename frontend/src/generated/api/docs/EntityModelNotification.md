
# EntityModelNotification


## Properties

Name | Type
------------ | -------------
`message` | string
`type` | string
`isRead` | boolean
`createdAt` | Date
`links` | [{ [key: string]: Link; }](Link.md)

## Example

```typescript
import type { EntityModelNotification } from ''

// TODO: Update the object below with actual values
const example = {
  "message": null,
  "type": null,
  "isRead": null,
  "createdAt": null,
  "links": null,
} satisfies EntityModelNotification

console.log(example)

// Convert the instance to a JSON string
const exampleJSON: string = JSON.stringify(example)
console.log(exampleJSON)

// Parse the JSON string back to an object
const exampleParsed = JSON.parse(exampleJSON) as EntityModelNotification
console.log(exampleParsed)
```

[[Back to top]](#) [[Back to API list]](../README.md#api-endpoints) [[Back to Model list]](../README.md#models) [[Back to README]](../README.md)



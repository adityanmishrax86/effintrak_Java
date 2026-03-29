
# ChatMessageResponse


## Properties

Name | Type
------------ | -------------
`userMessage` | string
`aiResponse` | string
`messageType` | string
`operation` | string
`model` | string
`promptProfile` | string
`promptVersion` | string
`errorCode` | string
`success` | boolean
`createdAt` | Date

## Example

```typescript
import type { ChatMessageResponse } from ''

// TODO: Update the object below with actual values
const example = {
  "userMessage": null,
  "aiResponse": null,
  "messageType": null,
  "operation": null,
  "model": null,
  "promptProfile": null,
  "promptVersion": null,
  "errorCode": null,
  "success": null,
  "createdAt": null,
} satisfies ChatMessageResponse

console.log(example)

// Convert the instance to a JSON string
const exampleJSON: string = JSON.stringify(example)
console.log(exampleJSON)

// Parse the JSON string back to an object
const exampleParsed = JSON.parse(exampleJSON) as ChatMessageResponse
console.log(exampleParsed)
```

[[Back to top]](#) [[Back to API list]](../README.md#api-endpoints) [[Back to Model list]](../README.md#models) [[Back to README]](../README.md)




# ChatConversationRequestBody


## Properties

Name | Type
------------ | -------------
`id` | number
`conversationId` | string
`user` | string
`title` | string
`description` | string
`messages` | Array&lt;string&gt;
`createdAt` | Date
`updatedAt` | Date

## Example

```typescript
import type { ChatConversationRequestBody } from ''

// TODO: Update the object below with actual values
const example = {
  "id": null,
  "conversationId": null,
  "user": null,
  "title": null,
  "description": null,
  "messages": null,
  "createdAt": null,
  "updatedAt": null,
} satisfies ChatConversationRequestBody

console.log(example)

// Convert the instance to a JSON string
const exampleJSON: string = JSON.stringify(example)
console.log(exampleJSON)

// Parse the JSON string back to an object
const exampleParsed = JSON.parse(exampleJSON) as ChatConversationRequestBody
console.log(exampleParsed)
```

[[Back to top]](#) [[Back to API list]](../README.md#api-endpoints) [[Back to Model list]](../README.md#models) [[Back to README]](../README.md)




# PageChatConversation


## Properties

Name | Type
------------ | -------------
`totalPages` | number
`totalElements` | number
`numberOfElements` | number
`pageable` | [PageableObject](PageableObject.md)
`first` | boolean
`last` | boolean
`size` | number
`content` | [Array&lt;ChatConversation&gt;](ChatConversation.md)
`number` | number
`sort` | [Array&lt;SortObject&gt;](SortObject.md)
`empty` | boolean

## Example

```typescript
import type { PageChatConversation } from ''

// TODO: Update the object below with actual values
const example = {
  "totalPages": null,
  "totalElements": null,
  "numberOfElements": null,
  "pageable": null,
  "first": null,
  "last": null,
  "size": null,
  "content": null,
  "number": null,
  "sort": null,
  "empty": null,
} satisfies PageChatConversation

console.log(example)

// Convert the instance to a JSON string
const exampleJSON: string = JSON.stringify(example)
console.log(exampleJSON)

// Parse the JSON string back to an object
const exampleParsed = JSON.parse(exampleJSON) as PageChatConversation
console.log(exampleParsed)
```

[[Back to top]](#) [[Back to API list]](../README.md#api-endpoints) [[Back to Model list]](../README.md#models) [[Back to README]](../README.md)



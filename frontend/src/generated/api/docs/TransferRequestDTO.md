
# TransferRequestDTO


## Properties

Name | Type
------------ | -------------
`amount` | number
`description` | string
`transferDate` | string
`fromAccountId` | number
`toAccountId` | number
`userId` | number

## Example

```typescript
import type { TransferRequestDTO } from ''

// TODO: Update the object below with actual values
const example = {
  "amount": null,
  "description": null,
  "transferDate": null,
  "fromAccountId": null,
  "toAccountId": null,
  "userId": null,
} satisfies TransferRequestDTO

console.log(example)

// Convert the instance to a JSON string
const exampleJSON: string = JSON.stringify(example)
console.log(exampleJSON)

// Parse the JSON string back to an object
const exampleParsed = JSON.parse(exampleJSON) as TransferRequestDTO
console.log(exampleParsed)
```

[[Back to top]](#) [[Back to API list]](../README.md#api-endpoints) [[Back to Model list]](../README.md#models) [[Back to README]](../README.md)



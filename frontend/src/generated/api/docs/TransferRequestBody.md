
# TransferRequestBody


## Properties

Name | Type
------------ | -------------
`id` | number
`amount` | number
`description` | string
`transferDate` | Date
`fromAccount` | string
`toAccount` | string
`user` | string

## Example

```typescript
import type { TransferRequestBody } from ''

// TODO: Update the object below with actual values
const example = {
  "id": null,
  "amount": null,
  "description": null,
  "transferDate": null,
  "fromAccount": null,
  "toAccount": null,
  "user": null,
} satisfies TransferRequestBody

console.log(example)

// Convert the instance to a JSON string
const exampleJSON: string = JSON.stringify(example)
console.log(exampleJSON)

// Parse the JSON string back to an object
const exampleParsed = JSON.parse(exampleJSON) as TransferRequestBody
console.log(exampleParsed)
```

[[Back to top]](#) [[Back to API list]](../README.md#api-endpoints) [[Back to Model list]](../README.md#models) [[Back to README]](../README.md)



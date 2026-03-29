
# ExpenseRequestBody


## Properties

Name | Type
------------ | -------------
`id` | number
`description` | string
`amount` | number
`date` | Date
`category` | string
`paymentMethod` | string
`paidTo` | string
`isRecurring` | boolean
`user` | string
`bankAccount` | string

## Example

```typescript
import type { ExpenseRequestBody } from ''

// TODO: Update the object below with actual values
const example = {
  "id": null,
  "description": null,
  "amount": null,
  "date": null,
  "category": null,
  "paymentMethod": null,
  "paidTo": null,
  "isRecurring": null,
  "user": null,
  "bankAccount": null,
} satisfies ExpenseRequestBody

console.log(example)

// Convert the instance to a JSON string
const exampleJSON: string = JSON.stringify(example)
console.log(exampleJSON)

// Parse the JSON string back to an object
const exampleParsed = JSON.parse(exampleJSON) as ExpenseRequestBody
console.log(exampleParsed)
```

[[Back to top]](#) [[Back to API list]](../README.md#api-endpoints) [[Back to Model list]](../README.md#models) [[Back to README]](../README.md)



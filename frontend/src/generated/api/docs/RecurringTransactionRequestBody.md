
# RecurringTransactionRequestBody


## Properties

Name | Type
------------ | -------------
`id` | number
`description` | string
`amount` | number
`type` | string
`category` | string
`bankAccount` | string
`frequency` | string
`startDate` | Date
`endDate` | Date
`nextDueDate` | Date
`paymentMethod` | string
`paidTo` | string
`source` | string
`note` | string
`isActive` | boolean
`user` | string

## Example

```typescript
import type { RecurringTransactionRequestBody } from ''

// TODO: Update the object below with actual values
const example = {
  "id": null,
  "description": null,
  "amount": null,
  "type": null,
  "category": null,
  "bankAccount": null,
  "frequency": null,
  "startDate": null,
  "endDate": null,
  "nextDueDate": null,
  "paymentMethod": null,
  "paidTo": null,
  "source": null,
  "note": null,
  "isActive": null,
  "user": null,
} satisfies RecurringTransactionRequestBody

console.log(example)

// Convert the instance to a JSON string
const exampleJSON: string = JSON.stringify(example)
console.log(exampleJSON)

// Parse the JSON string back to an object
const exampleParsed = JSON.parse(exampleJSON) as RecurringTransactionRequestBody
console.log(exampleParsed)
```

[[Back to top]](#) [[Back to API list]](../README.md#api-endpoints) [[Back to Model list]](../README.md#models) [[Back to README]](../README.md)




# RecurringTransactionRequestDTO


## Properties

Name | Type
------------ | -------------
`description` | string
`amount` | number
`type` | string
`categoryId` | number
`bankAccountId` | number
`frequency` | string
`startDate` | string
`endDate` | string
`paymentMethod` | string
`paidTo` | string
`source` | string
`note` | string
`userId` | number

## Example

```typescript
import type { RecurringTransactionRequestDTO } from ''

// TODO: Update the object below with actual values
const example = {
  "description": null,
  "amount": null,
  "type": null,
  "categoryId": null,
  "bankAccountId": null,
  "frequency": null,
  "startDate": null,
  "endDate": null,
  "paymentMethod": null,
  "paidTo": null,
  "source": null,
  "note": null,
  "userId": null,
} satisfies RecurringTransactionRequestDTO

console.log(example)

// Convert the instance to a JSON string
const exampleJSON: string = JSON.stringify(example)
console.log(exampleJSON)

// Parse the JSON string back to an object
const exampleParsed = JSON.parse(exampleJSON) as RecurringTransactionRequestDTO
console.log(exampleParsed)
```

[[Back to top]](#) [[Back to API list]](../README.md#api-endpoints) [[Back to Model list]](../README.md#models) [[Back to README]](../README.md)



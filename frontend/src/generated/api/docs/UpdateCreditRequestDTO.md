
# UpdateCreditRequestDTO


## Properties

Name | Type
------------ | -------------
`description` | string
`amount` | number
`dueDate` | string
`creditorId` | number
`type` | string
`interestRate` | number
`paymentMethod` | string
`paid` | boolean

## Example

```typescript
import type { UpdateCreditRequestDTO } from ''

// TODO: Update the object below with actual values
const example = {
  "description": null,
  "amount": null,
  "dueDate": null,
  "creditorId": null,
  "type": null,
  "interestRate": null,
  "paymentMethod": null,
  "paid": null,
} satisfies UpdateCreditRequestDTO

console.log(example)

// Convert the instance to a JSON string
const exampleJSON: string = JSON.stringify(example)
console.log(exampleJSON)

// Parse the JSON string back to an object
const exampleParsed = JSON.parse(exampleJSON) as UpdateCreditRequestDTO
console.log(exampleParsed)
```

[[Back to top]](#) [[Back to API list]](../README.md#api-endpoints) [[Back to Model list]](../README.md#models) [[Back to README]](../README.md)



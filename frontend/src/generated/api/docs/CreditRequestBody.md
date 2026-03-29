
# CreditRequestBody


## Properties

Name | Type
------------ | -------------
`id` | number
`description` | string
`amount` | number
`dueDate` | Date
`creditor` | string
`type` | string
`interestRate` | number
`paymentMethod` | string
`paid` | boolean
`user` | string

## Example

```typescript
import type { CreditRequestBody } from ''

// TODO: Update the object below with actual values
const example = {
  "id": null,
  "description": null,
  "amount": null,
  "dueDate": null,
  "creditor": null,
  "type": null,
  "interestRate": null,
  "paymentMethod": null,
  "paid": null,
  "user": null,
} satisfies CreditRequestBody

console.log(example)

// Convert the instance to a JSON string
const exampleJSON: string = JSON.stringify(example)
console.log(exampleJSON)

// Parse the JSON string back to an object
const exampleParsed = JSON.parse(exampleJSON) as CreditRequestBody
console.log(exampleParsed)
```

[[Back to top]](#) [[Back to API list]](../README.md#api-endpoints) [[Back to Model list]](../README.md#models) [[Back to README]](../README.md)




# EntityModelExpense


## Properties

Name | Type
------------ | -------------
`description` | string
`amount` | number
`date` | Date
`paymentMethod` | string
`paidTo` | string
`isRecurring` | boolean
`links` | [{ [key: string]: Link; }](Link.md)

## Example

```typescript
import type { EntityModelExpense } from ''

// TODO: Update the object below with actual values
const example = {
  "description": null,
  "amount": null,
  "date": null,
  "paymentMethod": null,
  "paidTo": null,
  "isRecurring": null,
  "links": null,
} satisfies EntityModelExpense

console.log(example)

// Convert the instance to a JSON string
const exampleJSON: string = JSON.stringify(example)
console.log(exampleJSON)

// Parse the JSON string back to an object
const exampleParsed = JSON.parse(exampleJSON) as EntityModelExpense
console.log(exampleParsed)
```

[[Back to top]](#) [[Back to API list]](../README.md#api-endpoints) [[Back to Model list]](../README.md#models) [[Back to README]](../README.md)



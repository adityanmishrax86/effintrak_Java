
# EntityModelRecurringTransaction


## Properties

Name | Type
------------ | -------------
`description` | string
`amount` | number
`type` | string
`frequency` | string
`startDate` | Date
`endDate` | Date
`nextDueDate` | Date
`paymentMethod` | string
`paidTo` | string
`source` | string
`note` | string
`isActive` | boolean
`links` | [{ [key: string]: Link; }](Link.md)

## Example

```typescript
import type { EntityModelRecurringTransaction } from ''

// TODO: Update the object below with actual values
const example = {
  "description": null,
  "amount": null,
  "type": null,
  "frequency": null,
  "startDate": null,
  "endDate": null,
  "nextDueDate": null,
  "paymentMethod": null,
  "paidTo": null,
  "source": null,
  "note": null,
  "isActive": null,
  "links": null,
} satisfies EntityModelRecurringTransaction

console.log(example)

// Convert the instance to a JSON string
const exampleJSON: string = JSON.stringify(example)
console.log(exampleJSON)

// Parse the JSON string back to an object
const exampleParsed = JSON.parse(exampleJSON) as EntityModelRecurringTransaction
console.log(exampleParsed)
```

[[Back to top]](#) [[Back to API list]](../README.md#api-endpoints) [[Back to Model list]](../README.md#models) [[Back to README]](../README.md)



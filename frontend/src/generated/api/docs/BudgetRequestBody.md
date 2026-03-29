
# BudgetRequestBody


## Properties

Name | Type
------------ | -------------
`id` | number
`amount` | number
`startDate` | Date
`endDate` | Date
`category` | string
`user` | string
`alertThreshold` | number

## Example

```typescript
import type { BudgetRequestBody } from ''

// TODO: Update the object below with actual values
const example = {
  "id": null,
  "amount": null,
  "startDate": null,
  "endDate": null,
  "category": null,
  "user": null,
  "alertThreshold": null,
} satisfies BudgetRequestBody

console.log(example)

// Convert the instance to a JSON string
const exampleJSON: string = JSON.stringify(example)
console.log(exampleJSON)

// Parse the JSON string back to an object
const exampleParsed = JSON.parse(exampleJSON) as BudgetRequestBody
console.log(exampleParsed)
```

[[Back to top]](#) [[Back to API list]](../README.md#api-endpoints) [[Back to Model list]](../README.md#models) [[Back to README]](../README.md)



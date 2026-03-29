
# BudgetRequestDTO


## Properties

Name | Type
------------ | -------------
`amount` | number
`startDate` | string
`endDate` | string
`categoryId` | number
`userId` | number
`alertThreshold` | number

## Example

```typescript
import type { BudgetRequestDTO } from ''

// TODO: Update the object below with actual values
const example = {
  "amount": null,
  "startDate": null,
  "endDate": null,
  "categoryId": null,
  "userId": null,
  "alertThreshold": null,
} satisfies BudgetRequestDTO

console.log(example)

// Convert the instance to a JSON string
const exampleJSON: string = JSON.stringify(example)
console.log(exampleJSON)

// Parse the JSON string back to an object
const exampleParsed = JSON.parse(exampleJSON) as BudgetRequestDTO
console.log(exampleParsed)
```

[[Back to top]](#) [[Back to API list]](../README.md#api-endpoints) [[Back to Model list]](../README.md#models) [[Back to README]](../README.md)



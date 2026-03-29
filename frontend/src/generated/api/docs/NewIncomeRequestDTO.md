
# NewIncomeRequestDTO


## Properties

Name | Type
------------ | -------------
`description` | string
`amount` | number
`date` | string
`categoryId` | number
`source` | string
`note` | string
`userId` | number
`bankAccountId` | number

## Example

```typescript
import type { NewIncomeRequestDTO } from ''

// TODO: Update the object below with actual values
const example = {
  "description": null,
  "amount": null,
  "date": null,
  "categoryId": null,
  "source": null,
  "note": null,
  "userId": null,
  "bankAccountId": null,
} satisfies NewIncomeRequestDTO

console.log(example)

// Convert the instance to a JSON string
const exampleJSON: string = JSON.stringify(example)
console.log(exampleJSON)

// Parse the JSON string back to an object
const exampleParsed = JSON.parse(exampleJSON) as NewIncomeRequestDTO
console.log(exampleParsed)
```

[[Back to top]](#) [[Back to API list]](../README.md#api-endpoints) [[Back to Model list]](../README.md#models) [[Back to README]](../README.md)



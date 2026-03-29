
# SavingsRequestBody


## Properties

Name | Type
------------ | -------------
`id` | number
`name` | string
`description` | string
`balance` | number
`targetAmount` | number
`targetDate` | Date
`depositFrequency` | string
`user` | string

## Example

```typescript
import type { SavingsRequestBody } from ''

// TODO: Update the object below with actual values
const example = {
  "id": null,
  "name": null,
  "description": null,
  "balance": null,
  "targetAmount": null,
  "targetDate": null,
  "depositFrequency": null,
  "user": null,
} satisfies SavingsRequestBody

console.log(example)

// Convert the instance to a JSON string
const exampleJSON: string = JSON.stringify(example)
console.log(exampleJSON)

// Parse the JSON string back to an object
const exampleParsed = JSON.parse(exampleJSON) as SavingsRequestBody
console.log(exampleParsed)
```

[[Back to top]](#) [[Back to API list]](../README.md#api-endpoints) [[Back to Model list]](../README.md#models) [[Back to README]](../README.md)



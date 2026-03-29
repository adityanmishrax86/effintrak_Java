
# EntityModelSavings


## Properties

Name | Type
------------ | -------------
`name` | string
`description` | string
`balance` | number
`targetAmount` | number
`targetDate` | Date
`depositFrequency` | string
`links` | [{ [key: string]: Link; }](Link.md)

## Example

```typescript
import type { EntityModelSavings } from ''

// TODO: Update the object below with actual values
const example = {
  "name": null,
  "description": null,
  "balance": null,
  "targetAmount": null,
  "targetDate": null,
  "depositFrequency": null,
  "links": null,
} satisfies EntityModelSavings

console.log(example)

// Convert the instance to a JSON string
const exampleJSON: string = JSON.stringify(example)
console.log(exampleJSON)

// Parse the JSON string back to an object
const exampleParsed = JSON.parse(exampleJSON) as EntityModelSavings
console.log(exampleParsed)
```

[[Back to top]](#) [[Back to API list]](../README.md#api-endpoints) [[Back to Model list]](../README.md#models) [[Back to README]](../README.md)



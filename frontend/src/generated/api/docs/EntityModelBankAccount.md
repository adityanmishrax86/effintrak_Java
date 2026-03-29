
# EntityModelBankAccount


## Properties

Name | Type
------------ | -------------
`name` | string
`balance` | number
`links` | [{ [key: string]: Link; }](Link.md)

## Example

```typescript
import type { EntityModelBankAccount } from ''

// TODO: Update the object below with actual values
const example = {
  "name": null,
  "balance": null,
  "links": null,
} satisfies EntityModelBankAccount

console.log(example)

// Convert the instance to a JSON string
const exampleJSON: string = JSON.stringify(example)
console.log(exampleJSON)

// Parse the JSON string back to an object
const exampleParsed = JSON.parse(exampleJSON) as EntityModelBankAccount
console.log(exampleParsed)
```

[[Back to top]](#) [[Back to API list]](../README.md#api-endpoints) [[Back to Model list]](../README.md#models) [[Back to README]](../README.md)




# EntityModelRefreshTokens


## Properties

Name | Type
------------ | -------------
`token` | string
`expiryDate` | Date
`links` | [{ [key: string]: Link; }](Link.md)

## Example

```typescript
import type { EntityModelRefreshTokens } from ''

// TODO: Update the object below with actual values
const example = {
  "token": null,
  "expiryDate": null,
  "links": null,
} satisfies EntityModelRefreshTokens

console.log(example)

// Convert the instance to a JSON string
const exampleJSON: string = JSON.stringify(example)
console.log(exampleJSON)

// Parse the JSON string back to an object
const exampleParsed = JSON.parse(exampleJSON) as EntityModelRefreshTokens
console.log(exampleParsed)
```

[[Back to top]](#) [[Back to API list]](../README.md#api-endpoints) [[Back to Model list]](../README.md#models) [[Back to README]](../README.md)



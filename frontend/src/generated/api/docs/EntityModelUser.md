
# EntityModelUser


## Properties

Name | Type
------------ | -------------
`firstName` | string
`lastName` | string
`email` | string
`phoneNumber` | string
`role` | string
`active` | boolean
`links` | [{ [key: string]: Link; }](Link.md)

## Example

```typescript
import type { EntityModelUser } from ''

// TODO: Update the object below with actual values
const example = {
  "firstName": null,
  "lastName": null,
  "email": null,
  "phoneNumber": null,
  "role": null,
  "active": null,
  "links": null,
} satisfies EntityModelUser

console.log(example)

// Convert the instance to a JSON string
const exampleJSON: string = JSON.stringify(example)
console.log(exampleJSON)

// Parse the JSON string back to an object
const exampleParsed = JSON.parse(exampleJSON) as EntityModelUser
console.log(exampleParsed)
```

[[Back to top]](#) [[Back to API list]](../README.md#api-endpoints) [[Back to Model list]](../README.md#models) [[Back to README]](../README.md)




# EntityModelSubscription


## Properties

Name | Type
------------ | -------------
`name` | string
`description` | string
`price` | number
`billingCycle` | string
`startDate` | Date
`endDate` | Date
`isActive` | boolean
`links` | [{ [key: string]: Link; }](Link.md)

## Example

```typescript
import type { EntityModelSubscription } from ''

// TODO: Update the object below with actual values
const example = {
  "name": null,
  "description": null,
  "price": null,
  "billingCycle": null,
  "startDate": null,
  "endDate": null,
  "isActive": null,
  "links": null,
} satisfies EntityModelSubscription

console.log(example)

// Convert the instance to a JSON string
const exampleJSON: string = JSON.stringify(example)
console.log(exampleJSON)

// Parse the JSON string back to an object
const exampleParsed = JSON.parse(exampleJSON) as EntityModelSubscription
console.log(exampleParsed)
```

[[Back to top]](#) [[Back to API list]](../README.md#api-endpoints) [[Back to Model list]](../README.md#models) [[Back to README]](../README.md)




# SubscriptionRequestBody


## Properties

Name | Type
------------ | -------------
`id` | number
`name` | string
`description` | string
`price` | number
`billingCycle` | string
`startDate` | Date
`endDate` | Date
`isActive` | boolean
`user` | string

## Example

```typescript
import type { SubscriptionRequestBody } from ''

// TODO: Update the object below with actual values
const example = {
  "id": null,
  "name": null,
  "description": null,
  "price": null,
  "billingCycle": null,
  "startDate": null,
  "endDate": null,
  "isActive": null,
  "user": null,
} satisfies SubscriptionRequestBody

console.log(example)

// Convert the instance to a JSON string
const exampleJSON: string = JSON.stringify(example)
console.log(exampleJSON)

// Parse the JSON string back to an object
const exampleParsed = JSON.parse(exampleJSON) as SubscriptionRequestBody
console.log(exampleParsed)
```

[[Back to top]](#) [[Back to API list]](../README.md#api-endpoints) [[Back to Model list]](../README.md#models) [[Back to README]](../README.md)



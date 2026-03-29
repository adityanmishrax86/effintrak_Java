
# NotificationPreferencesRequestBody


## Properties

Name | Type
------------ | -------------
`id` | number
`user` | string
`budgetAlerts` | boolean
`billReminders` | boolean
`subscriptionRenewals` | boolean
`goalAchievements` | boolean
`lowBalanceAlerts` | boolean
`unusualSpendingAlerts` | boolean
`emailNotifications` | boolean
`pushNotifications` | boolean

## Example

```typescript
import type { NotificationPreferencesRequestBody } from ''

// TODO: Update the object below with actual values
const example = {
  "id": null,
  "user": null,
  "budgetAlerts": null,
  "billReminders": null,
  "subscriptionRenewals": null,
  "goalAchievements": null,
  "lowBalanceAlerts": null,
  "unusualSpendingAlerts": null,
  "emailNotifications": null,
  "pushNotifications": null,
} satisfies NotificationPreferencesRequestBody

console.log(example)

// Convert the instance to a JSON string
const exampleJSON: string = JSON.stringify(example)
console.log(exampleJSON)

// Parse the JSON string back to an object
const exampleParsed = JSON.parse(exampleJSON) as NotificationPreferencesRequestBody
console.log(exampleParsed)
```

[[Back to top]](#) [[Back to API list]](../README.md#api-endpoints) [[Back to Model list]](../README.md#models) [[Back to README]](../README.md)



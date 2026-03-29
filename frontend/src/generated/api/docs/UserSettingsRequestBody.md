
# UserSettingsRequestBody


## Properties

Name | Type
------------ | -------------
`id` | number
`user` | string
`currencyCode` | string
`locale` | string
`timeZone` | string
`dateFormat` | string
`aiPersona` | string
`includeProactiveInsights` | boolean
`includeCategoryHints` | boolean
`weekStartsOn` | string
`createdAt` | Date
`updatedAt` | Date

## Example

```typescript
import type { UserSettingsRequestBody } from ''

// TODO: Update the object below with actual values
const example = {
  "id": null,
  "user": null,
  "currencyCode": null,
  "locale": null,
  "timeZone": null,
  "dateFormat": null,
  "aiPersona": null,
  "includeProactiveInsights": null,
  "includeCategoryHints": null,
  "weekStartsOn": null,
  "createdAt": null,
  "updatedAt": null,
} satisfies UserSettingsRequestBody

console.log(example)

// Convert the instance to a JSON string
const exampleJSON: string = JSON.stringify(example)
console.log(exampleJSON)

// Parse the JSON string back to an object
const exampleParsed = JSON.parse(exampleJSON) as UserSettingsRequestBody
console.log(exampleParsed)
```

[[Back to top]](#) [[Back to API list]](../README.md#api-endpoints) [[Back to Model list]](../README.md#models) [[Back to README]](../README.md)



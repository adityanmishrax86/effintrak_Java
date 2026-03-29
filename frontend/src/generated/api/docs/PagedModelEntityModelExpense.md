
# PagedModelEntityModelExpense


## Properties

Name | Type
------------ | -------------
`embedded` | [PagedModelEntityModelExpenseEmbedded](PagedModelEntityModelExpenseEmbedded.md)
`links` | [{ [key: string]: Link; }](Link.md)
`page` | [PageMetadata](PageMetadata.md)

## Example

```typescript
import type { PagedModelEntityModelExpense } from ''

// TODO: Update the object below with actual values
const example = {
  "embedded": null,
  "links": null,
  "page": null,
} satisfies PagedModelEntityModelExpense

console.log(example)

// Convert the instance to a JSON string
const exampleJSON: string = JSON.stringify(example)
console.log(exampleJSON)

// Parse the JSON string back to an object
const exampleParsed = JSON.parse(exampleJSON) as PagedModelEntityModelExpense
console.log(exampleParsed)
```

[[Back to top]](#) [[Back to API list]](../README.md#api-endpoints) [[Back to Model list]](../README.md#models) [[Back to README]](../README.md)



---
navigation_title: "JSON"
mapped_pages:
  - https://www.elastic.co/guide/en/elasticsearch/reference/current/json-processor.html
---

# JSON processor [json-processor]


Converts a JSON string into a structured JSON object.

$$$json-options$$$

| Name | Required | Default | Description |
| --- | --- | --- | --- |
| `field` | yes | - | The field to be parsed. |
| `target_field` | no | `field` | The field that the converted structured object will be written into. Any existing content in this field will be overwritten. |
| `add_to_root` | no | false | Flag that forces the parsed JSON to be added at the top level of the document. `target_field` must not be set when this option is chosen. |
| `add_to_root_conflict_strategy` | no | `replace` | When set to `replace`, root fields that conflict with fields from the parsed JSON will be overridden. When set to `merge`, conflicting fields will be merged. Only applicable if `add_to_root` is set to `true`. |
| `allow_duplicate_keys` | no | false | When set to `true`, the JSON parser will not fail if the JSON contains duplicate keys. Instead, the last encountered value for any duplicate key wins. |
| `strict_json_parsing` | no | true | When set to `true`, the JSON parser will strictly parse the field value. When set to `false`, the JSON parser will be more lenient but also more likely to drop parts of the field value. For example if `strict_json_parsing` is set to `true` and the field value is `123 "foo"` then the processor will throw an IllegalArgumentException. But if `strict_json_parsing` is set to `false` then the field value will be parsed as `123`. |
| `description` | no | - | Description of the processor. Useful for describing the purpose of the processor or its configuration. |
| `if` | no | - | Conditionally execute the processor. See [Conditionally run a processor](docs-content://manage-data/ingest/transform-enrich/ingest-pipelines.md#conditionally-run-processor). |
| `ignore_failure` | no | `false` | Ignore failures for the processor. See [Handling pipeline failures](docs-content://manage-data/ingest/transform-enrich/ingest-pipelines.md#handling-pipeline-failures). |
| `on_failure` | no | - | Handle failures for the processor. See [Handling pipeline failures](docs-content://manage-data/ingest/transform-enrich/ingest-pipelines.md#handling-pipeline-failures). |
| `tag` | no | - | Identifier for the processor. Useful for debugging and metrics. |

All JSON-supported types will be parsed (null, boolean, number, array, object, string).

Suppose you provide this configuration of the `json` processor:

```js
{
  "json" : {
    "field" : "string_source",
    "target_field" : "json_target"
  }
}
```

If the following document is processed:

```js
{
  "string_source": "{\"foo\": 2000}"
}
```

after the `json` processor operates on it, it will look like:

```js
{
  "string_source": "{\"foo\": 2000}",
  "json_target": {
    "foo": 2000
  }
}
```

If the following configuration is provided, omitting the optional `target_field` setting:

```js
{
  "json" : {
    "field" : "source_and_target"
  }
}
```

then after the `json` processor operates on this document:

```js
{
  "source_and_target": "{\"foo\": 2000}"
}
```

it will look like:

```js
{
  "source_and_target": {
    "foo": 2000
  }
}
```

This illustrates that, unless it is explicitly named in the processor configuration, the `target_field` is the same field provided in the required `field` configuration.


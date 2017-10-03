# Hive query language snippets
Extract fields from JSON array:
```sql
select get_json_object(concat(concat('{"root":', '[{"a": 10}]'), '}'), '$.root.a[0]') as a;
```

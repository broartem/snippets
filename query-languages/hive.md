# Hive query language snippets
Extract fields from JSON array:
```sql
select get_json_object(concat(concat('{"root":', '[{"a": 10}]'), '}'), '$.root.a[0]') as a;
```
Convert date with specific format to UNIX timestamp:
```sql
select from_unixtime(unix_timestamp('2017-09-26 05:17:00,000', 'yyyy-MM-dd HH:mm:ss,SSS'),'HH:mm:ss') as ts;
```sql
Convert date from UNIX timestamp to date in specific format:
```
select from_unixtime(1507032575,'yyyy-MM-dd HH:mm:ss.SSS') as ts;
```

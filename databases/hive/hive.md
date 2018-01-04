# Hive query language snippets
Extract fields from JSON array:
```sql
select get_json_object(concat(concat('{"root":', '[{"a": 10}]'), '}'), '$.root.a[0]') as a;
```
Convert date with specific format to UNIX timestamp:
```sql
select from_unixtime(unix_timestamp('2017-09-26 05:17:00,000', 'yyyy-MM-dd HH:mm:ss,SSS'),'HH:mm:ss') as ts;
```
Convert date from UNIX timestamp to date in specific format:
```sql
select from_unixtime(1507032575,'yyyy-MM-dd HH:mm:ss.SSS') as ts;
```
`In` operator:
```sql
select 1 where 2 in (2,3,4,5);
```
Data Generation with Recursive SQL (inspired by ["10 SQL Tricks That You Didn’t Think Were Possible" post](https://blog.jooq.org/2016/04/25/10-sql-tricks-that-you-didnt-think-were-possible/):
```sql
WITH
  t1 AS (SELECT 1 as v1, 2 as v2),
  t2 AS (
    SELECT v1 * 2 as w1, v2 * 2 as w2
    FROM t1
  )
SELECT *
FROM t1, t2
```

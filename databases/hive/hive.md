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
Using `with` clause (unfortunately, hive does not support recursive SQL as described in ["10 SQL Tricks That You Didn’t Think Were Possible"](https://blog.jooq.org/2016/04/25/10-sql-tricks-that-you-didnt-think-were-possible/) post):
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
Find the Largest Series with no Gaps (adopted from ["10 SQL Tricks That You Didn’t Think Were Possible"](https://blog.jooq.org/2016/04/25/10-sql-tricks-that-you-didnt-think-were-possible/) post):
```
WITH
  login_dates AS (
    SELECT '2014-03-18' as login_date UNION ALL
    SELECT '2014-03-16' as login_date UNION ALL
    SELECT '2014-03-15' as login_date UNION ALL
    SELECT '2014-03-14' as login_date
  ),
  -- Unfortunately Hive rejects to compute statement `date_sub(login_date, ROW_NUMBER() ..`,
  -- so we have to create two subqueries
  login_dates_enumerated AS (
    SELECT
      login_date,
      ROW_NUMBER() OVER (ORDER BY login_date) AS row_num
    FROM login_dates
  ),
  login_date_groups AS (
    SELECT
      login_date,
      date_sub(login_date, row_num) AS grp
    FROM login_dates_enumerated
  )
SELECT
  min(login_date), max(login_date), 
  datediff(max(login_date), min(login_date)) + 1 AS length
FROM login_date_groups
GROUP BY grp
ORDER BY length DESC
```

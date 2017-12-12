# PostgreSQL query language snippets
Select current date and time:
```sql
SELECT now();
```
Select only rows with max date for each key (taken from https://stackoverflow.com/questions/1684244):
```sql
SELECT * 
FROM table a 
JOIN (SELECT ID, max(date) maxDate
      FROM table
      GROUP BY ID) b
ON a.ID = b.ID AND a.date = b.maxDate
```
Split column into multiple rows in Postgres using lateral join (see https://stackoverflow.com/questions/29419993):
```sql
SELECT s.token, flag
FROM
  (SELECT 'this is a test' as subject, 2 as flag) t,
  unnest(string_to_array(t.subject, ' ')) s(token)
WHERE  flag = 2;
```

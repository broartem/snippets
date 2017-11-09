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

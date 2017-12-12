# Oracle DB snippets
Construct query for granting rights for tables in user_tables:
```sql
select 'GRANT select, update, insert on ' || table_name || ' to <username>;' from user_tables;
```

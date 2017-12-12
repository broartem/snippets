# Aerospike shell snippets
Aerospike command line interactive shell is `aql`. If you are inside this shell, you can type `set output json` in order to see shell responses in JSON format. Also it is possible to send commands via pipe.

Show sets
```bash
echo "show sets" | aql
```

Show bins
```bash
echo "show bins" | aql
```

Select all the data from a set
```bash
echo "select * from mynamespace.myset" | aql
```

Select record by key
```bash
echo "select * from mynamespace.myset where PK='mykey'" | aql
```

Insert a record
```bash
echo "insert into mynamespace.myset (PK, bin1, bin2) values ('mykey', 'abc', 123)" | aql
```

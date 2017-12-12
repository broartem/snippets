# HBase shell snippets
Create a table:
```bash
echo "create tablename, { NAME => 'v', BLOCKCACHE => 'true', BLOCKSIZE => '16384', VERSIONS => 1 }" | hbase shell
```

Drop a table:
```bash
echo "disable tablename" | hbase shell
echo "drop tablename" | hbase shell
```

Get all the rowkeys from HBase tables
```bash
echo "count 'mytable', INTERVAL=> 1" | hbase shell | cut -d':' -f3 | tr -d ' '
```

Search HBase by rowkey substring:
```bash
echo "scan 'mytable', {FILTER => \"RowFilter (=, 'substring:mysubstring')\"}" | hbase shell
```

Scan metadata (how keys are distributed among regions) for particular table:
```bash
printf "scan 'hbase:meta',{FILTER=>\"PrefixFilter('mytable')\"}" | hbase shell
```

HBase property to avoid aborting region if one of coprocessors failed to start:
```
hbase.coprocessor.abortonerror
```
Describe table (schema and options):
```bash
printf "describe 'mytable'" | hbase shell
```

How much space HBase tables occupy on HDFS (-h is for human readable Mb, Gb and so on, skip this option if you want to output just bytes).
```
hadoop fs -du -h /hbase/data/default
```
, to have up to date sizes you should perform Memstore flush manually on tables you are interested in just like that:
```
echo "flush 'tableName'" | hbase shell
```

# Hadoop shell snippets
Copy HDFS files between Hadoop clusters:
```bash
hadoop distcp \
-Dmapreduce.job.maps=10 \
-Dmapreduce.map.memory.mb=3000 \
-Dmapreduce.reduce.memory.mb=6000 \
-pb -bandwidth 3000 -m 10 \
hdfs://source-host:port/path/to/my/file_src \
hdfs://destination-host:port/path/to/my/file_dst
```
NOTE: if you replicate parts of hive table using snippet above, do not forget to repair destination table afterwards with the following command:
```sql
MSCK REPAIR TABLE <table_name>
```

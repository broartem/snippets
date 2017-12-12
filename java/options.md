# Java options
Configure GC logging:
```
-XX:+PrintGCDetails
-XX:+PrintGCTimeStamps
-Xloggc:logs/gc.log
-XX:+UseGCLogFileRotation
-XX:NumberOfGCLogFiles=100
-XX:+HeapDumpOnOutOfMemoryError
```

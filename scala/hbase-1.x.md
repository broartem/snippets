# HBase 1.x snippets
Initialize connection and get table (make sure hbase-site.xml is in your classpath. You can find one inside `$HBASE_HOME/conf` dir):
```scala
import org.apache.hadoop.hbase.client.{ConnectionFactory, Table}
import org.apache.hadoop.hbase.TableName

val connection = ConnectionFactory.createConnection()
val mytable: Table = connection.getTable(TableName.valueOf("mytable"))

// Do stuff

connection.close()
```

Performing scan:
```scala
import org.apache.hadoop.hbase.client.{Scan, ResultScanner, Result}
import org.apache.hadoop.hbase.util.Bytes
import scala.collection.JavaConversions._

// Initialize connection and get table here .. (see above)

// Scan with row range (it just defines borders, "startrow" and "endrow" must not be presented in the table)
val scan = new Scan("startrow".getBytes, "endrow".getBytes)
// Or just `val scan = new Scan()` if you do not want ranges

// Optionally, set column family
scan.addFamily("f1".getBytes)
// Or (and) family and column
scan.addColumn("f2".getBytes, "c1".getBytes)

// Set filters and so on here ..

val scanner: ResultScanner = mytable.getScanner(scan)

// ResultScanner extends Iterable[Result], so Scala can wrap it with TraversableLike, so we can `map`,
// `flatMap` and so on. Just make sure you have imported JavaConversions
scanner.foreach { (rr: Result) =>
  // You can convert bytes to other types with Bytes.toString, Bytes.toFloat and so on
  val rowKey: Array[Byte] = rr.getRow
  
  // Map of qualifiers to values
  val values: java.util.NavigableMap[Array[Byte], Array[Byte]] = rr.getFamilyMap("f1".getBytes)
  
  // Scala helps us here again
  values.map { case (q, v) => /* extract something */ }.reduce(...)
  
  // And so on ..
}

scanner.close()
```

Scan with multiple prefix filters (actually, you can use other filters too). Be careful, even single prefix filter will perform the whole table scan (which will likely fail with timeout exception on huge tables), so better use row ranges (see above). But there is a restriction - prior to version 2.0 HBase does not support multiple ranges for single scan, so you have to make separate scan queries. In HBase 2 there is a [MultiRowRangeFilter](https://hbase.apache.org/apidocs/org/apache/hadoop/hbase/filter/MultiRowRangeFilter.html)
```scala
import org.apache.hadoop.hbase.client.Scan
import org.apache.hadoop.hbase.filter.{FilterList, PrefixFilter}

val scan = new Scan()

val filters = new FilterList()
List("prefix1", "prefix2").foreach(prefix => filters.addFilter(new PrefixFilter(prefix.getBytes)))
scan.setFilter(filters)

// Perform scan (see above)
```
Remove any records, including those written with future timestamps (note Long.MaxValue - 1, for some reason it won't work with Long.MaxValue - 1):
```
table.delete(new Delete("row1".getRow, Long.MaxValue - 1))
```

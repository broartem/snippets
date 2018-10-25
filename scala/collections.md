# Scala collections snippets
Import implicit converters from/to Java collections:
```
import scala.collection.JavaConverters._
```

Invert Map of Seqs:
```
def invertMap[K, V](m: Map[K, Seq[V]]): Map[V, Seq[K]] =
    m
      .toSeq
      .flatMap { case (k, vs) => vs zip Stream.continually(k) }
      .groupBy(_._1)
      .mapValues(_.map(_._2))
```
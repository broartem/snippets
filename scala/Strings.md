# Snippets for working with strings in Scala
Remove newlines and spaces in multi-line string (taken from https://stackoverflow.com/questions/17581714):
```scala
"""
  |line 1
  |l i n e   2
  | lll i  n e3
""".stripMargin.split('\n').map(_.trim.filter(_ >= ' ')).mkString
```

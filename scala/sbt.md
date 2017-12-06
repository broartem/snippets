# Sbt snippets
Add `provided` dependencies to classpath on `sbt run` (useful for running Spark locally and so on, see https://stackoverflow.com/questions/18838944):
```scala
fullClasspath in Runtime := (fullClasspath in Compile).value
```
Run non-default project (see https://stackoverflow.com/questions/7674615):
```bash
sbt "project foo" "run arg1 arg2"
```

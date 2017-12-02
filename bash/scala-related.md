# Scala shell snippets
Working with scalac, Ammonite and sbt from within the shell

Show generated Java code for a specific scala snippet:
```bash
cat test.scala && scalac -Xprint:typer test.scala
```

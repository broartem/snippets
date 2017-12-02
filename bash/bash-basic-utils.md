# Snippets of bash basic utils
Grep lines which do not match criteria
```bash
cat myfile.csv | grep -v "China"
```
Find files by specific pattern modified in last 1 day and calculate their total size: 
```
find /some/path -type f -name '*.txt' -mtime 1 -exec du -ch {} + | grep total$
```
Find files by specific pattern and sort by creation timestamp:
```
find /some/path -type f -name '*.txt' -printf '%Ts\t%p\n' | sort -nr | cut -f2
```
Grep in files created at specific date (do not forger '*' after directory path):
```
ls -ld /path/to/directory/* | awk '$6 == "Jul" && $7 == 7 {print $9}' | xargs grep "something"
```
Intersect input from several pipes:
```
grep -Fx -f <(cat file1 | ...) <(cat file2 | ...)
```
Grep specific UTF-8 symbol in the file using its UTF-16 (hex) representation (which can for example be fount in this table http://www.fileformat.info/info/unicode/char/a.htm inside 'Unicode Value' field):
```
cat myfile | iconv -f utf-8 -t utf-16 | hexdump | grep c390
```

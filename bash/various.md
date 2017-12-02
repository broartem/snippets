# Various shell snippets
Compress PNG image with maximum lossless compression and adjuct image height using ImageMagic
```bash
convert \
  -resize 200 \
  -define png:compression-filter=1 \
  -define png:compression-level=9 \
  -define png:compression-strategy=2 \
  input.png output.png
```

Convert text from one encoding into another (list supporting encodings `iconv -l`):
```bash
iconv -f UTF-8 -t ISO-8859-1 in.txt > out.txt
```

Enumerate columns of a line from a CSV file starting from zero 
```bash
echo "field1,field2,field3" | tr "," "\n" | nl -v 0
0  field1
1  field2
2  field3
```

Extract a particular column (say, number 3, enumeration starts from 1) from a CSV file
```bash
cat myfile.csv | cut -d',' -f3
```

Count all the duplicates in the third column of a CSV file:
```bash
cat myfile.csv | cut -d',' -f3 | sort | uniq -cd
    3 Anastasia
    4 Peter
    1 Sally
```

Beautify a JSON document
```bash
echo "{\"name\": \"George\", \"city\": \"Paris\"}" | python -mjson.tool
{
    "city": "Paris",
    "name": "George"
}
```

List names of persons from Paris from input JSON documents (one line = one JSON). Expression `tr -d "\" "` removes quotes and spaces, so do not forget to add space after `\"`
```bash
cat persons.json | grep "\"city\": \"Paris\"" | python -mjson.tool | grep "name" | cut -d':' -f2 | tr -d "\" "
```

Generate 100 random 32 character alphanumeric strings (upper and lowercase) (see https://gist.github.com/earthgecko/3089509)
```bash
cat /dev/urandom | tr -dc 'a-zA-Z0-9' | fold -w 32 | head -n 100
```

Grep log files stored in distributed cluster (e.g. with hostnames node001-node009):
```bash
for srv in $(seq 1 9); do ssh node$srv "cat '/var/log/myservice/error.log' | grep 'My Error'"; done
```

Run a bash command in detached GNU Screen window (`exec sh` is important):
```bash
screen -dm bash -c "bash start-my-service.sh; exec sh";
```

Pick a specific field from an input a file, containing JSONs on each line, using Python:
```bash
cat jsons.txt | while read -r d; do echo ${d} | \
  python -c 'import json,sys;doc=json.load(sys.stdin);print doc["key1"]["key2"]'; done
```

Generate an array of doubles and save to a binary file:
```
for i in `seq 1 10`; do perl -e "print pack('d', $i.1)" >> myfile.bin; done
```

Read an array of doubles from a binary file, one value per line. Option `-v` forces `od` to show even duplicate values (by default it places `*` while encountering duplicates:
```bash
od -w8 -v -t fD myfile.bin
```

Kill a process, running on specific TCP port:
```
fuser -k 3118/tcp
```

Python pip compile and install package from custom local repo from a specific branch:
```
pip install —upgrade git+file:///home/username/projects/scikit-learn@myplayground
```

MD5 checksum for a message (be careful, echo adds '\n' at the end of message, so use printf):
```bash
printf "message" | md5sum | awk '{print $1}'
```

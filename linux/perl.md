# Perl snippets
More one-liners https://gist.github.com/mischapeters/1e8eef09a0aafd4f24f0

Print lines from stdin which starts from '1' character, otherwise sample:
```bash
time  perl -ne 'print if(substr($_,0,1) == 1 || rand() < 0.01)' file.txt
```
Interestingly, it is much faster then AWK version (file.txt size was 5M lines):
```bash
$ time  perl -ne 'print if(substr($_,0,1) == 1 || rand() < 0.01)' file.txt > /dev/null

real    0m3.114s
user    0m2.448s
sys     0m0.670s

$ time awk 'BEGIN {srand()} !/^$/ { if (($1 ~ /^1/) || (rand() <= .01)) print $0}' file.txt > /dev/null

real    0m11.494s
user    0m10.888s
sys     0m0.607s
```

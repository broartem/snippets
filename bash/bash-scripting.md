# Bash scripting snippets
## Associative arrays
Create an associative array and access its values:
```bash
declare -A myarray=(
    ["mykey1"]="myvalue1"
    ["mykey2"]="myvalue2"
)

echo ${myarray["mykey1"]
```
Check the key exists, see http://stackoverflow.com/questions/13219634
```bash
[ ${myarray["mykey"]+isset} ] && echo "YEAP!"

keyvar="mykey"
[ ${myarray["$keyvar"]+isset} ] && echo "YEAP!"
```

## Various
Check last command status code:
```bash
some_command
if [ $? -eq 0 ]; then echo OK; else echo FAIL; fi;
```

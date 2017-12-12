# Windows PowerShell snippets
Which process uses specific port (Note: at least in PowerShell v2.0 you need to add a space after the find criteria, otherwise you'll get `FIND: Parameter format not correct`). The last column in the output will be process PID:
```
netstat -aon | find /i "listening " | find "8080 "
```
Kill a process by PID:
```
taskkill /F /PID 3312
```
One liner to kill process listening on specific port (replace 8080 with you port number), taken from http://stackoverflow.com/questions/48198:
```
netstat -a -o -n | select -skip 4 | % {$a = $_ -split ' {3,}'; New-Object 'PSObject' -Property @{Original=$_;Fields=$a}} | ? {$_.Fields[1] -match '8080$'} | % {taskkill /F /PID $_.Fields[4] }
```

# Java related tools
List files inside jar:
```bash
jar tf file.jar
```

Update files inside jar:
```
jar uf jar-file input-file(s)
```

Output particular .class file contents from jar (for example, typesafe's Config.class):
```bash
unzip -q -c file.jar com/typesafe/config/Config.class
```

Import a certificate (on Windows):
```bash
C:\Program Files\Java\jdk1.x.y_zz\bin\keytool.exe -importcert -trustcacerts -alias YourAlias -file C:\Path\To\Certificate.cer -keystore "C:\Program Files\Java\jdk1.x.y_zz\jre\lib\security\cacerts" -storepass changeit
```

Remove a certificate (on Windows):
```bash
C:\Program Files\Java\jdk1.x.y_zz\bin\keytool.exe -delete -alias TCS -keystore "C:\Program Files\Java\jdk1.x.y_zz\jre\lib\security\cacerts" -storepass changeit
```
List running java processes:
```bash
jps
```
Create java process heap dump by pid:
```bash
jmap -heap:format=b <process-id>
```
Output stack trace of java app by pid:
```bash
jstack <process-id>
```

Get java version of a running java process
```bash
jcmd <process_id> VM.version
```

# GNU Screen shell snippets
Start a service in a separate detached screen only in the case the port it uses is free
```bash
if ! netstat -tupln | grep 3118; then \
	screen -dm bash -c "bash /path/to/service-starter.sh; exec sh"; \
else \
	echo "Your service (or something else) alredy started on port 3118. Exiting .."; \
fi;
```

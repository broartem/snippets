# Makefile snippets
Makefile cat be useful not only for C/C++ compilation, but also for a job automation. Therefore, some such useful snippets are presented here.

Function to redeploy a Storm topology:
```bash
# Enable bash scripting inside the Makefile
SHELL := /bin/bash

# Params:
# $1 - topology name
# $2 - jar file
# $3 - Java class path
define redeploy-storm-topology
	@echo "Redeploying Storm topology '$1' ..."
	# TODO: do not try to kill topology if its status is KILLED
	if storm list | grep '$1'; then \
		storm kill $1; \
	fi;
	while storm list | grep '$1'; do \
		echo "Waiting topology '$1' to stop"; \
		sleep 2; \
	done
	storm jar $2 $3
endef
```
which can be used like that:
```
redeploy-my-topology-target:
	$(call redeploy-storm-topology,my-topology-name, \
		my-topology-jar-with-dependencies.jar, \
		com.company.my.topology.Application)
```

# Apache Storm shell snippets
Deploy a topology:
```bash
storm jar sometopology-with-dependencies.jar mymain.class.path arg1 arg2
```

Deploy a Storm Flux topology:
```bash
storm jar sometopology-with-dependencies.jar org.apache.storm.flux.Flux \
	--local conf/sometopology.yaml \
	--filter conf/somehost.properties
```

Kill a topology:
```bash
storm kill "sometopoliogy-name"
```

A function to kill a list of Storm topologies synchroniously:
```bash
# Kill storm topologies and exit the script only after all the topologies are down
# see https://issues.apache.org/jira/browse/STORM-193
# Usage: kill_topologies_synchronously [topology-name ..]
function kill_topologies_synchronously {
    echo_log "Killing Storm topologies ${@} ..."
    for topology in ${@}; do
        if storm list | grep ${topology}; then \
            storm kill ${topology}; \
        fi;
    done
    # NOTE: without `head -n 1` while loop condition will be evaluated to false
    while [[ -n $(storm list | grep `join "\|" "${@}"` | head -n 1) ]]; do
        echo "Waiting topologies ${@} to stop";
        sleep 2s;
    done
}

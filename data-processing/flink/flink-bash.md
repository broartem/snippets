# Flink shell snippets
List url paths to log files for each container of finished job. Takes history server host and yarn application_id as arguments:
```bash
# Usage: ./container_logs.sh <yarn_host> <application_id>
# Example: ./container_logs.sh localhost application_1496852131171_7633
  
yarn_host=$1
app_id=$2
  
master_logs=$(curl -s -H 'Accept: application/json' "${yarn_host}:8088/ws/v1/cluster/apps/${app_id}" \
    | python -c "import json,sys;obj=json.load(sys.stdin);print obj['app']['amContainerLogs'];")
 
# Sometimes Hadoop places a page with redirect instructions inside Javascript. So, we have to extract a page to redirect to by ourselves.
jm_log_or_redirect_page=$(wget -qO- "${master_logs}/jobmanager.log/?start=0")
 
redirect_page=$(echo "${jm_log_or_redirect_page}" | grep "<meta http-equiv=\"refresh\" content=" | sed -n "s/.*url=\(.*\)/\1/p" | tr -d '"' | tr -d '>')
 
if [ ! -z "$redirect_page" ]
then
  jm_log=$(wget -qO- "${redirect_page}/jobmanager.log/?start=0")
else
  jm_log=${jm_log_or_redirect_page}
fi
 
containers=$(echo "${jm_log}" | grep "Launching TaskManager in container ContainerInLaunch" \
    | sed -n "s/.*\(\[.*\]\).*/\1/p" | cut -d',' -f1-2 | tr -d " " | sed -n "s/.*ContainerId:\([a-zA-Z0-9\_\-]\+\),NodeId:\(.*\)/\1,\2/p")
  
OLDIFS=$IFS
IFS=,
while read container_id container_host
do
    echo "${yarn_host}:19888/jobhistory/logs/${container_host}/${container_id}/${container_id}/flink/taskmanager.log"
done <<< "${containers}"
IFS=$OLDIFS
```

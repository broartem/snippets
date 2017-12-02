# Confluent Kafka shell snippets
List Kafka topics:
```bash
kafka-topics \
    --zookeeper localhost:2181 \
    --list
```

Create a topic:
```bash
kafka-topics \
    --zookeeper localhost:2181 \
    --topic sometopic \
    --create --partitions 1 --replication-factor 1
```

Remove a topic:
```bash
kafka-topics \
    --zookeeper localhost:2181 \
    --topic sometopic \
    --delete
```

Push messages into a topic from pipe:
```bash
echo "message" | kafka-console-producer \
    --broker-list localhost:9092 \
    --topic sometopic
```

Consume incoming messages:
```bash
kafka-console-consumer \
    --bootstrap-server localhost:9092 \
    --topic customers \
    --from-beginning
```

Read last 10 messages from a topic using kafkacat
```bash
/path/to/kafkacat -C \
	-b localhost:9092 \
	-e -t sometopic -o -10
```

Calculate the number of messages in the topic. GetOffsetShell tool returns triples like sometopic:0:5429160613 (topic, partition and offset). We sum up offsets from every partition to get the topic offset and calculate the difference between start offset and end offset.
```bash
get_offset(){ \
  kafka-run-class kafka.tools.GetOffsetShell \
    --broker-list localhost:9092 \
    --topic sometopic \
    --time $1 \
    --offsets 1 | awk -F  ":" '{sum += $3} END {print sum}'; \
}; start=$(get_offset -1); end=$(get_offset -2); echo $((end-start))
```

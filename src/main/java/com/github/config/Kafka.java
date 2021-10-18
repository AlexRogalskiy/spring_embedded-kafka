package com.github.config;

import com.github.AvroKafkaApplication;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.common.serialization.ByteArrayDeserializer;
import org.apache.kafka.common.serialization.ByteArraySerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.kafka.test.EmbeddedKafkaBroker;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.kafka.test.utils.KafkaTestUtils;
import org.springframework.test.context.ContextConfiguration;

import java.util.HashMap;
import java.util.Map;

@EmbeddedKafka(partitions = 1, brokerProperties = {
        "listeners=PLAINTEXT://localhost:9092",
        "port=9092",
        "log.dir=target/embedded-kafka"
})
@Configuration
//@SpringBootTest
public class Kafka {

    @Autowired
    private EmbeddedKafkaBroker embeddedKafkaBroker;

    @BeforeAll
    public void setUpKafka() {

        Map<String, Object> consumerConfigs = new HashMap<>(KafkaTestUtils.consumerProps("lp-cl-executor-group", "false", embeddedKafkaBroker));
        Consumer<byte[], byte[]> consumer = new DefaultKafkaConsumerFactory<>(consumerConfigs, new ByteArrayDeserializer(), new ByteArrayDeserializer()).createConsumer();

        Map<String, Object> producerConfigs = new HashMap<>(KafkaTestUtils.producerProps(embeddedKafkaBroker));
        Producer<byte[], byte[]> producer = new DefaultKafkaProducerFactory<>(producerConfigs, new ByteArraySerializer(), new ByteArraySerializer()).createProducer();
    }

    @AfterAll
    public void tearDownKafka() {
        embeddedKafkaBroker.getKafkaServers().forEach(b -> b.shutdown());
        embeddedKafkaBroker.getKafkaServers().forEach(b -> b.awaitShutdown());
    }
}

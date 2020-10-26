package kafkaplay;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Arrays;
import java.util.Collections;
import java.util.Properties;
import java.util.Random;


@SuppressWarnings("WeakerAccess")
class KafkaFactory {

    static final String BOOTSTRAP_SERVERS = "localhost:9092";
    static final String TOPIC = "my-example-topic";
    static final String CONSUMER_GROUP_ID = "cg-cons-grooop";
    static final String USUARIO_KAFKA = "kafka_user_informer";
    static final String SENHA_USUARIO_KAFKA = "kafka-pwd-informer";
    static final String SASL_MECHANISM = "PLAIN";
    static final String SASL_JAAS_CONFIG = String.format("org.apache.kafka.common.security.plain.PlainLoginModule required username=\"%s\" password=\"%s\";", USUARIO_KAFKA, SENHA_USUARIO_KAFKA);


    static KafkaProducer<String, String> criarProducer() {
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
        props.put(ProducerConfig.CLIENT_ID_CONFIG, "producer-id");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put("security.protocol", "SASL_PLAINTEXT");
        props.put("sasl.mechanism", SASL_MECHANISM);
        props.put("sasl.jaas.config", SASL_JAAS_CONFIG);
        return new KafkaProducer<>(props);
    }

    static Consumer<String, String> criarConsumer() {
        final Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, CONSUMER_GROUP_ID);
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "false"); // exige commit explicito/manual
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest"); // quando sobe, receberá todos os eventos passados ainda nao commitados
        props.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, 1); // "polla" um registro/evento por vez, para que o commit/"rollback" possa ser relativo a somente ele
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.put("security.protocol", "SASL_PLAINTEXT");
        props.put("sasl.mechanism", SASL_MECHANISM);
        props.put("sasl.jaas.config", SASL_JAAS_CONFIG);

        Consumer<String, String> consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Arrays.asList(TOPIC.split(",")));
        return consumer;
    }

}

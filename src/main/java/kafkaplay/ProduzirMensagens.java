package kafkaplay;

import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.concurrent.TimeUnit;

public class ProduzirMensagens {

    public static void main(String[] args) {
        ProduzirMensagens.produzir("2510542");
    }

    static void produzir(final String value) {
        System.out.println("Escrevendo...");
        enviarMensagem("abc", "{\"teste\":" + value + ",\"tipo\":\"TIPO_TESTE\"}");
        System.out.println("Escreveu!");
    }

    static void enviarMensagem(String key, String message) {
        Producer<String, String> producer = KafkaFactory.criarProducer();

        ProducerRecord<String, String> record = new ProducerRecord<>(KafkaFactory.TOPIC, key, message);
        try {
            RecordMetadata metadata = producer.send(record).get();
            System.out.println("Mensagem " + message + " p/ topico " + KafkaFactory.TOPIC + " c/ key " + key + " p/ partition " + metadata.partition() + " e offset " + metadata.offset() + "...");
        } catch (Exception e) {
            throw new RuntimeException("Error in sending record", e);
        }

        producer.close(2, TimeUnit.SECONDS);
    }

}

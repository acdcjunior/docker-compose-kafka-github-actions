package kafkaplay;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("ALL")
public class ConsumirMensagens {

    public static void main(String[] args) {
//        System.setProperty(org.slf4j.impl.SimpleLogger.DEFAULT_LOG_LEVEL_KEY, "TRACE");

        consumir(300, (ConsumerRecords<String, String> records) -> {
            System.out.println();
            records.forEach(cr -> {
                System.out.println("RECEBIDO: " + cr.topic() + "->" + cr.value());
            });
        });
    }

    static List<ConsumerRecords<String, String>> consumir(int vezes, java.util.function.Consumer<ConsumerRecords<String, String>> fn) {
        List<ConsumerRecords<String, String>> rs = awaitMessage(vezes, fn);
        for (ConsumerRecords<String, String> consumerRecords : rs) {
            System.out.println("COUNT: "+ consumerRecords.count());
            ConsumerRecord<String, String> record = consumerRecords.iterator().next();
            System.out.println("PRIMEIRO VALUE: " + record.value());
        }
        return rs;
    }

    static List<ConsumerRecords<String, String>> awaitMessage(int vezes, java.util.function.Consumer<ConsumerRecords<String, String>> fn) {
        System.out.println("Criando consumer: " + KafkaFactory.CONSUMER_GROUP_ID + " " + KafkaFactory.TOPIC);
        Consumer<String, String> consumer = KafkaFactory.criarConsumer();
        System.out.println("Criado consumer: " + KafkaFactory.CONSUMER_GROUP_ID + " " + KafkaFactory.TOPIC);

        List<ConsumerRecords<String, String>> rs = new ArrayList<>();
        for (int i = 0; i < vezes; i++) {
            System.out.print(".");
            ConsumerRecords<String, String> records = consumer.poll(1000);
            System.out.println("EH emptu? " + records.isEmpty());
            if (!records.isEmpty()) {
                fn.accept(records);
                rs.add(records);
                consumer.commitSync();
            }
        }
        consumer.close();
        return rs;
    }

}
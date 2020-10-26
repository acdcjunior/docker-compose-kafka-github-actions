package kafkaplay;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

class ConsumirMensagensTest {

    @Test
    void name() {
        String valueA = "via-teste#" + ThreadLocalRandom.current().nextLong(1, Long.MAX_VALUE);
        ProduzirMensagens.produzir(valueA);
        String valueB = "via-teste#" + ThreadLocalRandom.current().nextLong(1, Long.MAX_VALUE);
        ProduzirMensagens.produzir(valueB);
        List<ConsumerRecords<String, String>> rs = ConsumirMensagens.consumir(10, (x) -> {
            ConsumerRecord<String, String> record = x.iterator().next();
            System.out.println("GOT: " + record.value() + " - " +
                    "partition: " + record.partition() + " - " +
                    "offset: " + record.offset() + " - " +
                    "topic: " + record.topic() + " - " +
                    "timestamp: " + record.timestamp() + " - " +
                    "timestampType: " + record.timestampType()
            );
        });

        String vA = rs.get(0).iterator().next().value();
        Assertions.assertTrue(vA.contains(valueA), vA);
        String vB = rs.get(1).iterator().next().value();
        Assertions.assertTrue(vB.contains(valueB), vB);
    }

}
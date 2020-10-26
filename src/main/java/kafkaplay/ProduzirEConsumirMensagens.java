package kafkaplay;

import org.apache.kafka.clients.consumer.ConsumerRecord;

public class ProduzirEConsumirMensagens {

    public static void main(String[] args) {
        ProduzirMensagens.produzir("2510542");
        ConsumirMensagens.consumir(10, (x) -> {
            ConsumerRecord<String, String> record = x.iterator().next();
            System.out.println("GOT: " + record.value() + " - " +
                    "partition: " + record.partition() + " - " +
                    "offset: " + record.offset() + " - " +
                    "topic: " + record.topic() + " - " +
                    "timestamp: " + record.timestamp() + " - " +
                    "timestampType: " + record.timestampType()
            );
        });
    }

}

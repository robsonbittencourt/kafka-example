package com.rbittencourt.kafka

import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.clients.consumer.KafkaConsumer
import org.apache.kafka.common.serialization.StringDeserializer
import java.time.Duration
import java.util.*

class KafkaConsumerClient<T>(
    type: Class<T>,
    private val parser: ConsumerFunction<T>
) {

    private val kafkaConsumer: KafkaConsumer<String, T> = KafkaConsumer(getProperties(type))

    fun listen() {
        kafkaConsumer.subscribe(listOf("topic"))

        while (true) {
            val records = kafkaConsumer.poll(Duration.ofMillis(100))

            if (!records.isEmpty) {
                println("Found ${records.count()} records")

                for (record in records) {
                    parser.consume(record)
                }
            }
        }
    }

    private fun getProperties(type: Class<T>): Properties {
        val properties = Properties()

        properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092")
        properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer::class.java.name)
        properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, GsonDeserializer::class.java.name)
        properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, "group")
        properties.setProperty(GsonDeserializer.TYPE_CONFIG, type.name)

        return properties
    }

}
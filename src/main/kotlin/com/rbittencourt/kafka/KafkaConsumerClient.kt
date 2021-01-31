package com.rbittencourt.kafka

import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.clients.consumer.KafkaConsumer
import org.apache.kafka.common.serialization.StringDeserializer
import java.time.Duration
import java.util.*

class KafkaConsumerClient {

    private val kafkaConsumer: KafkaConsumer<String, String> = KafkaConsumer(getProperties())

    private fun getProperties(): Properties {
        val properties = Properties()

        properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092")
        properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer::class.java.name)
        properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer::class.java.name)
        properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, "group")

        return properties
    }

    fun listen() {
        kafkaConsumer.subscribe(listOf("topic"))

        while (true) {
            val records = kafkaConsumer.poll(Duration.ofMillis(100))

            if (!records.isEmpty) {
                println("Found ${records.count()} records")

                for (record in records) {
                    println(record)
                }
            }
        }
    }
}
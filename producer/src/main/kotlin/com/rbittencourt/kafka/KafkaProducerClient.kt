package com.rbittencourt.kafka

import org.apache.kafka.clients.producer.Callback
import org.apache.kafka.clients.producer.KafkaProducer
import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.clients.producer.ProducerRecord
import org.apache.kafka.common.serialization.StringSerializer
import java.util.*

class KafkaProducerClient {

    private val producer: KafkaProducer<String, String> = KafkaProducer(properties())

    private fun properties(): Properties {
        val properties = Properties()

        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092")
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer::class.java.name)
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer::class.java.name)

        return properties
    }

    fun send() {
        val record = ProducerRecord("topic", "key", "value")

        val callback = Callback { data, ex ->
            println("Topic: ${data.topic()} Partition: ${data.partition()} Offset: ${data.offset()}")
        }

        producer.send(record, callback).get()
    }

}
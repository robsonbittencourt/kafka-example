package com.rbittencourt.kafka

fun main(args: Array<String>) {

    for (i in 1..10) {
        val producer = KafkaProducerClient()
        producer.send()
    }

}


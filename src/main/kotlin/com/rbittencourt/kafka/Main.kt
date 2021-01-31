package com.rbittencourt.kafka

fun main(args: Array<String>) {

    val thread = Thread {
        val consumer = KafkaConsumerClient()
        consumer.listen()
    }
    thread.start()

    for (i in 1..10) {
        val producer = KafkaProducerClient()
        producer.send()
    }

    Thread.sleep(500000)
}


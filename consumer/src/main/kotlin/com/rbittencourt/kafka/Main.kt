package com.rbittencourt.kafka

fun main(args: Array<String>) {

    val consumer = KafkaConsumerClient()
    consumer.listen()

}


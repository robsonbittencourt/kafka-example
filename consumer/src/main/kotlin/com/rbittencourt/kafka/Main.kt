package com.rbittencourt.kafka

fun main(args: Array<String>) {
    val parser = ConsumerFunction<Order> { record ->
        println("Order received: ${record.value()}")
    }

    val consumer = KafkaConsumerClient(Order::class.java, parser)
    consumer.listen()
}


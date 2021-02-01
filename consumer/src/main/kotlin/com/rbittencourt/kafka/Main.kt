package com.rbittencourt.kafka

fun main() {
    val parser = ConsumerFunction<Order> { record ->
        println("Order received: ${record.value()}")
    }

    val consumer = KafkaConsumerClient(Order::class.java, "ORDER_WAS_REQUESTED", parser)
    consumer.listen()
}


package com.rbittencourt.kafka

import java.math.BigDecimal
import java.math.RoundingMode.HALF_UP

fun main() {

    for (i in 1..10) {
        val producer = KafkaProducerClient<Order>()

        val order = Order("client-$i", "id-$i", BigDecimal(Math.random()).setScale(2, HALF_UP))
        producer.send("ORDER_WAS_REQUESTED", "key-$i", order)
    }

}

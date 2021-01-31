package com.rbittencourt.kafka

import org.apache.kafka.clients.consumer.ConsumerRecord

fun interface ConsumerFunction<T> {

    fun consume(record: ConsumerRecord<String, T>)

}
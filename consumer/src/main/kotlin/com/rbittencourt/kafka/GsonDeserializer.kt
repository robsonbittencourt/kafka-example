package com.rbittencourt.kafka

import com.google.gson.GsonBuilder
import com.rbittencourt.kafka.GsonDeserializer.Companion.TYPE_CONFIG
import org.apache.kafka.common.serialization.Deserializer
import org.apache.kafka.common.serialization.Serializer

class GsonDeserializer<T> : Deserializer<T> {

    companion object {
        const val TYPE_CONFIG = "com.rbittencourt.kafka.type_config"
    }

    private val gson = GsonBuilder().create()
    private var type: Class<T>? = null

    override fun configure(configs: Map<String?, *>, isKey: Boolean) {
        val typeName = configs[TYPE_CONFIG].toString()

        try {
            this.type = Class.forName(typeName) as Class<T>
        } catch (e: ClassNotFoundException) {
            throw RuntimeException("Class does not exist")
        }
    }

    override fun deserialize(topic: String?, data: ByteArray): T {
        return gson.fromJson(String(data), type)
    }

}
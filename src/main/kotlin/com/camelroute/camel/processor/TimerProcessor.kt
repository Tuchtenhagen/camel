package com.camelroute.camel.processor

import org.apache.camel.Exchange
import org.apache.camel.Processor
import org.apache.camel.component.file.GenericFile

class TimerProcessor : Processor {
    override fun process(exchange: Exchange?) {
        val body = exchange?.getIn()?.body as GenericFile<*>
        val fileName = body.fileName
    }
}
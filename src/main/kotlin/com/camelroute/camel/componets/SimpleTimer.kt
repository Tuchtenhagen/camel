package com.camelroute.camel.componets

import org.apache.camel.LoggingLevel
import org.apache.camel.builder.RouteBuilder
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class SimpleTimer : RouteBuilder() {

    companion object {
        const val TIMER_ROUTE_ENDPOINT = "timer:simpletimer?period=5000&repeatCount=3"
        const val TIMER_ROUTE_ID = "timerId"
    }

    @Override
    override fun configure() {

        from(TIMER_ROUTE_ENDPOINT)
            .routeId(TIMER_ROUTE_ID)
            .setBody(constant("1. Simple Timer Route!"))
            .log(LoggingLevel.INFO,"\${body}")
            .to("direct:checkRoute")
    }

}
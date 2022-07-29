package com.camelroute.camel.componets

import com.camelroute.camel.processor.TimerProcessor
import org.apache.camel.builder.RouteBuilder
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class LegacyFilesRoute : RouteBuilder() {

    companion object {
        const val ROUTE_FILE_DESTINATION = "file:src/data/output?fileName=output-\${file:name}"
        const val ROUTE_FILE_ENDPOINT = "file:src/data/input?fileName=\${file:name}"
        const val ROUTE_FILE_ID = "direct:fileLegacyRouteId"
    }

    override fun configure() {

        from("direct:checkRoute")
            .routeId(LegacyFilesRoute.ROUTE_FILE_ID)
            .log("2. Check File Route")
            .choice()
                .`when`{ "validator:file:src/data/input?fileName=\${file:name}".isNotBlank() }
                .to("file:src/data/input?fileName=\${file:name}&fileExist=Move&moveExisting=\${file:src/data/output?fileName=output-\${file:name}}")
                .log("move completed1: \${file:name}")
            .endChoice()
            .end()


        from("file:src/data/input?fileName=\${file:name}&fileExist=Move&moveExisting=\${file:src/data/output?fileName=output-\${file:name}}")
            .routeId("moveFileId")
            .process(TimerProcessor())
            .log("movendo...")
            .to("file:src/data/output?fileName=output-\${file:name}")

    }
}
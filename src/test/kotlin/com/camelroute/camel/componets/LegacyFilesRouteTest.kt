package com.camelroute.camel.componets

import org.apache.camel.CamelContext
import org.apache.camel.EndpointInject
import org.apache.camel.component.mock.MockEndpoint
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.apache.camel.builder.AdviceWith

@SpringBootTest()
class LegacyFilesRouteTest {
    @Autowired
    lateinit var context : CamelContext

    @EndpointInject("mock:fileLegacyRouteId")
    private lateinit var mockEndpoint : MockEndpoint

    @Test
    fun testFileMoveByMockingEndpoint() {
        var expectedBody = ""
        mockEndpoint.name = MOCK_WALLY_ROUTE

        mockEndpoint.expectedBodiesReceived(expectedBody)
        mockEndpoint.expectedMinimumMessageCount(1)

        AdviceWith.adviceWith(context, MOCK_WALLY_ROUTE){
            it.replaceFromWith("direct:mockStart")
        }

        mockEndpoint.assertIsSatisfied()
    }


    companion object {
        private const val MOCK_WALLY_ROUTE = "fileLegacyRouteId"
    }
}
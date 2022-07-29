package com.camelroute.camel.componets

import org.apache.camel.CamelContext
import org.apache.camel.EndpointInject
import org.apache.camel.component.mock.MockEndpoint
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.apache.camel.builder.AdviceWith

@SpringBootTest()
class SimpleTimerTest {
    @Autowired
    lateinit var context : CamelContext

    @EndpointInject("mock:timerId")
    private lateinit var mockEndpoint : MockEndpoint

    @Test
    fun testSimpleTimer() {
        var expectedBody = "Mentoria!"
        mockEndpoint.name = MOCK_WALLY_ROUTE

        mockEndpoint.expectedBodiesReceived(expectedBody)
        mockEndpoint.expectedMinimumMessageCount(1)

        AdviceWith.adviceWith(context, MOCK_WALLY_ROUTE){
            it.weaveAddLast().to(mockEndpoint)
        }

        mockEndpoint.assertIsSatisfied()
    }

    //TODO: add new test with count cases

    companion object {
        private const val MOCK_WALLY_ROUTE = "timerId"
    }
}
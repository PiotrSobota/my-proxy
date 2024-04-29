package com.x.eleven.connection


import com.x.eleven.logger.LoggerUtils
import com.x.eleven.payload.Payload
import com.x.eleven.services.ResponseProcessingService
import spock.lang.Specification
import spock.lang.Unroll

class ClientConnectionTest extends Specification {

    ResponseProcessingService responseService = Mock()
    LoggerUtils loggerUtils = Mock()
    ClientConnection clientConnection = new ClientConnection(responseService, loggerUtils)

    @Unroll
    def 'should send response depending on whether the processing result is empty or not'() {
        given:
        Payload payload = Mock()
        def clientRequestsIndex = 4L

        when:
        clientConnection.send(payload)

        then:
        1 * responseService.processClientRequest(payload) >> processingResult
        loggingExecutionsCount * loggerUtils.logServerResponse(clientRequestsIndex)

        where:
        processingResult        || loggingExecutionsCount
        Optional.ofNullable(4L) || 1
        Optional.empty()        || 0
    }
}

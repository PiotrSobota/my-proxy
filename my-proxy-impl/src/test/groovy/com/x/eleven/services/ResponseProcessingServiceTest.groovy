package com.x.eleven.services

import com.x.eleven.dbmock.DbServiceSimpleMock
import com.x.eleven.payload.requests.ClientRequest
import com.x.eleven.payload.requests.InternalRequest
import com.x.eleven.payload.requests.ServerResponseRequest
import spock.lang.Specification

class ResponseProcessingServiceTest extends Specification {

    ClientRequestCounterService counterService = Mock()

    def 'should process request and return count'() {
        given:
        def searchedIndex = 2
        def expectedClientRequestsIndex = 1L

        ServerResponseRequest responseRequest = Mock {
            getIndex() >> searchedIndex
        }

        ClientRequest clientRequest1 = Mock()
        ClientRequest clientRequest2 = Mock()
        InternalRequest internalRequest1 = Mock()
        InternalRequest internalRequest2 = Mock()

        def requestsBelowIndex = [clientRequest1, internalRequest1, clientRequest2]

        DbServiceSimpleMock dbServiceMock = Mock {
            getByIndex(0) >> requestsBelowIndex.get(0)
            getByIndex(1) >> requestsBelowIndex.get(1)
            getByIndex(2) >> requestsBelowIndex.get(2)
            getByIndex(3) >> internalRequest2
        }

        ResponseProcessingService service = new ResponseProcessingService(counterService, dbServiceMock)

        when:
        def result = service.processClientRequest(responseRequest)

        then:
        1 * counterService.countClientRequests(dbServiceMock, requestsBelowIndex, searchedIndex) >> Optional.of(2L)
        result == Optional.of(expectedClientRequestsIndex)
    }
}

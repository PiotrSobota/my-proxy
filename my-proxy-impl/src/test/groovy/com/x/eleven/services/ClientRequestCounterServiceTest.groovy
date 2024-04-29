package com.x.eleven.services

import com.x.eleven.dbmock.DbServiceSimpleMock
import com.x.eleven.logger.LoggerUtils
import com.x.eleven.payload.requests.ClientRequest
import com.x.eleven.payload.requests.InternalRequest
import spock.lang.Specification
import spock.lang.Unroll

class ClientRequestCounterServiceTest extends Specification {

    LoggerUtils loggerUtils = Mock()

    ClientRequestCounterService counterService = new ClientRequestCounterService(loggerUtils)

    ClientRequest clientRequest1 = Mock()
    ClientRequest clientRequest2 = Mock()
    InternalRequest internalRequest1 = Mock()
    InternalRequest internalRequest2 = Mock()
    InternalRequest internalRequest3 = Mock()

    def requestsBelowIndex = [internalRequest1, clientRequest1, internalRequest2, clientRequest2, internalRequest3]

    DbServiceSimpleMock dbServiceMock = Mock {

        getByIndex(0) >> requestsBelowIndex.get(0)
        getByIndex(1) >> requestsBelowIndex.get(1)
        getByIndex(2) >> requestsBelowIndex.get(2)
        getByIndex(3) >> requestsBelowIndex.get(3)
        getByIndex(4) >> requestsBelowIndex.get(4)
    }

    @Unroll
    def 'should return client requests count'() {
        when:
        def result = counterService.countClientRequests(dbServiceMock, requestsBelowIndex, searchedIndex)

        then:
        result == Optional.of(expectedResult)
        1 * loggerUtils.logClientRequestDescription(requestsBelowIndex.get(searchedIndex))

        where:
        searchedIndex || expectedResult
        1             || 1L
        3             || 2L
    }

    @Unroll
    def 'should return empty if requested request is internal request'() {
        when:
        def result = counterService.countClientRequests(dbServiceMock, requestsBelowIndex, searchedIndex)

        then:
        result.isEmpty()
        0 * loggerUtils.logClientRequestDescription(_)

        where:
        searchedIndex << [0, 2, 4]
    }
}

package com.x.eleven.connection

import com.x.eleven.dbmock.DbServiceSimpleMock
import com.x.eleven.payload.Payload
import spock.lang.Specification

class ServerConnectionTest extends Specification {

    DbServiceSimpleMock dbServiceMock = Mock()
    ServerConnection serverConnection = new ServerConnection(dbServiceMock)

    def 'should send payload'() {
        given:
        Payload payload = Mock()

        when:
        serverConnection.send(payload)

        then:
        1 * dbServiceMock.insert(payload)
    }
}

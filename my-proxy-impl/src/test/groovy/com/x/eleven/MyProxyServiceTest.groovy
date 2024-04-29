package com.x.eleven

import com.x.eleven.connection.ClientConnection
import com.x.eleven.connection.ServerConnection
import com.x.eleven.payload.Payload
import com.x.eleven.payload.requests.ClientRequest
import com.x.eleven.payload.requests.InternalRequest
import com.x.eleven.payload.requests.ServerResponseRequest
import spock.lang.Specification

class MyProxyServiceTest extends Specification {

    ServerConnection serverConnection = Mock()
    ClientConnection clientConnection = Mock()
    MyProxyService service = new MyProxyService(serverConnection, clientConnection)

    def 'should send request from client'() {
        given:
        def request = GroovyMock(ClientRequest)

        when:
        service.fromClient(request)

        then:
        serverConnection.send(request as Payload)
    }

    def 'should send request from internal server'() {
        given:
        def request = Mock(InternalRequest)

        when:
        service.internalRequest(request)

        then:
        1 * serverConnection.send(request as Payload)
    }

    def 'should accept server response request'() {
        given:
        def request = Mock(ServerResponseRequest)

        when:
        service.fromServer(request)

        then:
        1 * clientConnection.send(request as Payload)
    }
}

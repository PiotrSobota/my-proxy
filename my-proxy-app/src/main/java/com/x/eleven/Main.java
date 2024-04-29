package com.x.eleven;

import com.x.eleven.connection.ClientConnection;
import com.x.eleven.connection.ServerConnection;
import com.x.eleven.dbmock.DbServiceSimpleMock;
import com.x.eleven.payload.requests.ClientRequest;
import com.x.eleven.payload.requests.InternalRequest;
import com.x.eleven.payload.requests.ServerResponseRequest;
import com.x.eleven.services.ClientRequestCounterService;

public class Main {

    public static void main(String[] args) {
        DbServiceSimpleMock dbServiceMock = new DbServiceSimpleMock();
        ClientRequestCounterService requestCounterService = new ClientRequestCounterService();
        ServerConnection serverConnection = new ServerConnection(dbServiceMock);
        ClientConnection clientConnection = new ClientConnection(dbServiceMock, requestCounterService);
        MyProxyService proxy = new MyProxyService(serverConnection, clientConnection);

        System.out.println("Application started");
        proxy.fromClient(new ClientRequest("request1", 999888111L));
        proxy.internalRequest(new InternalRequest("request2", 999888113L));
        proxy.fromClient(new ClientRequest("request3", 999888114L));
        proxy.fromClient(new ClientRequest("request4", 999888115L));
        proxy.fromClient(new ClientRequest("request5", 999888116L));
        proxy.internalRequest(new InternalRequest("request6", 999888117L));
        proxy.fromClient(new ClientRequest("request7", 999888118L));
        System.out.println("----------------------------------------------------");
        proxy.fromServer(new ServerResponseRequest(0)); // should keep sequence number 0
        proxy.fromServer(new ServerResponseRequest(1)); // should skip internal request
        proxy.fromServer(new ServerResponseRequest(2)); // should change sequence number to 1
        proxy.fromServer(new ServerResponseRequest(3)); // should change sequence number to 2
        proxy.fromServer(new ServerResponseRequest(4)); // should change sequence number to 3
        proxy.fromServer(new ServerResponseRequest(5)); // should skip internal request
        proxy.fromServer(new ServerResponseRequest(6)); // shoul

        // Expected result
        // Sending Server Response [0]
        // Sending Server Response [1]
        // Sending Server Response [2]
        // Sending Server Response [3]
        // Sending Server Response [4]
    }
}
package com.x.eleven;

import com.x.eleven.connection.ClientConnection;
import com.x.eleven.connection.ServerConnection;
import com.x.eleven.payload.requests.Request;
import com.x.eleven.payload.requests.ServerResponseRequest;

public class MyProxyService {

    private final ServerConnection serverConnection;
    private final ClientConnection clientConnection;

    public MyProxyService(ServerConnection serverConnection, ClientConnection clientConnection) {
        this.serverConnection = serverConnection;
        this.clientConnection = clientConnection;
    }

    public void fromClient(Request clientRequest) {
        serverConnection.send(clientRequest);
    }

    public void internalRequest(Request internalRequest) {
        serverConnection.send(internalRequest);
    }

    public void fromServer(ServerResponseRequest serverResponseRequest) {
        clientConnection.send(serverResponseRequest);
    }
}

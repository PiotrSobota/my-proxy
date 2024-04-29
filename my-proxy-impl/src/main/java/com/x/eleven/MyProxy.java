package com.x.eleven;

public class MyProxy {

    private final Connection serverConnection;
    private final Connection clientConnection;
    public MyProxy(Connection serverConnection, Connection clientConnection) {
        this.serverConnection = serverConnection;
        this.clientConnection = clientConnection;
    }
    public void fromClient(Payload clientRequest) {
        //TODO
        serverConnection.send(clientRequest);
    }
    public void internalRequest(Payload internalRequest) {
        // TODO
        serverConnection.send(internalRequest);
    }
    public void fromServer(Payload serverResponse) {
        // TODO
        clientConnection.send(serverResponse);
    }

    //w tej klasie modyfikacja tylko //todo
}

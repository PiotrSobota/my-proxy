package com.x.eleven.connection;

import com.x.eleven.dbmock.DbServiceSimpleMock;
import com.x.eleven.payload.Payload;

public class ServerConnection implements Connection {

    private final DbServiceSimpleMock dbServiceMock;

    public ServerConnection(DbServiceSimpleMock dbServiceMock) {
        this.dbServiceMock = dbServiceMock;
    }

    @Override
    public void send(Payload request) {
        dbServiceMock.set(request);
    }
}

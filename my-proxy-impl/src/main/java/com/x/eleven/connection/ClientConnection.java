package com.x.eleven.connection;

import com.x.eleven.dbmock.DbServiceSimpleMock;
import com.x.eleven.payload.Payload;
import com.x.eleven.services.ClientRequestCounterService;
import com.x.eleven.services.ResponseProcessingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ClientConnection implements Connection {

    private final Logger logger = LoggerFactory.getLogger(ClientConnection.class);
    private final ResponseProcessingService responseService;

    public ClientConnection(DbServiceSimpleMock dbServiceMock, ClientRequestCounterService requestCounterService) {
        this.responseService = new ResponseProcessingService(requestCounterService, dbServiceMock);
    }

    @Override
    public void send(Payload payload) {
        responseService.processClientRequest(payload)
                        .ifPresent(count -> logger.info("{} [{}]", "Sending Server Response", count));
    }
}

package com.x.eleven.connection;

import com.x.eleven.ClientRequestCounterService;
import com.x.eleven.dbmock.DbServiceSimpleMock;
import com.x.eleven.payload.Payload;
import com.x.eleven.payload.requests.Request;
import com.x.eleven.payload.requests.ServerResponseRequest;
import java.util.ArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ClientConnection implements Connection {

    private final Logger logger = LoggerFactory.getLogger(ClientConnection.class);
    private final DbServiceSimpleMock dbServiceMock;
    private final ClientRequestCounterService requestCounterService;

    public ClientConnection(DbServiceSimpleMock dbServiceMock, ClientRequestCounterService requestCounterService) {
        this.dbServiceMock = dbServiceMock;
        this.requestCounterService = requestCounterService;
    }

    @Override
    public void send(Payload payload) {
        int searchedIndex = ((ServerResponseRequest) payload).getIndex();
        ArrayList<Request> requestsToIndex = requestCounterService.collectFromZeroToIndex(dbServiceMock, searchedIndex);
        requestCounterService.calculateProcessedClientRequestsCount(dbServiceMock, requestsToIndex, searchedIndex)
                        .ifPresent(count -> logger.info("{} [{}]", "Sending Server Response", count));
    }
}

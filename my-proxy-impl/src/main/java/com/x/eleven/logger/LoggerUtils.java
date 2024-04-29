package com.x.eleven.logger;

import com.x.eleven.connection.ClientConnection;
import com.x.eleven.payload.Payload;
import com.x.eleven.payload.requests.ClientRequest;
import com.x.eleven.payload.requests.ServerResponseRequest;
import com.x.eleven.services.ClientRequestCounterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggerUtils {

    private final Logger clientConnection = LoggerFactory.getLogger(ClientConnection.class);
    private final Logger clientRequestCounterService = LoggerFactory.getLogger(ClientRequestCounterService.class);

    public void logServerResponse(long count) {
        clientConnection.info("{} [{}]", "Sending Server Response", count);
    }

    public void logClientRequestDescription(ClientRequest clientRequest) {
        clientRequestCounterService.info("{} : {}", "Object description", clientRequest);
    }

    public void logNotExistingIndex(Payload payload) {
        ServerResponseRequest request = (ServerResponseRequest) payload;
        clientConnection.error("{} : {}", "Response requested for not existing index", request.getIndex());
    }
}

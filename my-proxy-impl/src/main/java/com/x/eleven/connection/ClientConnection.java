package com.x.eleven.connection;

import com.x.eleven.logger.LoggerUtils;
import com.x.eleven.payload.Payload;
import com.x.eleven.services.ResponseProcessingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ClientConnection implements Connection {

    private final Logger logger = LoggerFactory.getLogger(ClientConnection.class);
    private final ResponseProcessingService responseService;
    private final LoggerUtils loggerUtils;

    public ClientConnection(ResponseProcessingService responseService, LoggerUtils loggerUtils) {
        this.responseService = responseService;
        this.loggerUtils = loggerUtils;
    }

    @Override
    public void send(Payload payload) {
        try {
            responseService.processClientRequest(payload)
                    .ifPresent(loggerUtils::logServerResponse);
        } catch (IndexOutOfBoundsException e) {
            loggerUtils.logNotExistingIndex(payload);
        }
    }
}

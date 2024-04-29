package com.x.eleven.logger;

import com.x.eleven.connection.ClientConnection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggerUtils {

    private final Logger clientConnection = LoggerFactory.getLogger(ClientConnection.class);

    public void logServerResponse(long count) {
        clientConnection.info("{} [{}]", "Sending Server Response", count);
    }
}

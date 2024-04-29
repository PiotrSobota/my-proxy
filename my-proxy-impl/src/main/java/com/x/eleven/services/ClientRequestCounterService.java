package com.x.eleven.services;

import com.x.eleven.dbmock.DbServiceSimpleMock;
import com.x.eleven.payload.requests.ClientRequest;
import com.x.eleven.payload.requests.Request;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ClientRequestCounterService {

    private final Logger logger = LoggerFactory.getLogger(ClientRequestCounterService.class);

    public Optional<Long> countClientRequests(DbServiceSimpleMock dbServiceMock, List<Request> requestsBelowIndex,
            int searchedIndex) {
        if (requestsBelowIndex.get(searchedIndex) instanceof ClientRequest clientRequest) {
            Long processedClientRequestsCount = 0L;
            for (int i = 0; i < searchedIndex; i++) {
                Request request = dbServiceMock.getByIndex(i);
                if (request instanceof ClientRequest) {
                    processedClientRequestsCount++;
                }
            }
            logger.info("{} : {}", "Object description", clientRequest);
            return Optional.of(processedClientRequestsCount);
        } else {
            return Optional.empty();
        }
    }
}

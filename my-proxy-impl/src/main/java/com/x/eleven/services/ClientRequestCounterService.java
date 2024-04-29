package com.x.eleven.services;

import com.x.eleven.dbmock.DbServiceSimpleMock;
import com.x.eleven.payload.requests.ClientRequest;
import com.x.eleven.payload.requests.Request;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ClientRequestCounterService {

    private final Logger logger = LoggerFactory.getLogger(ClientRequestCounterService.class);

    public Optional<Long> countClientRequests(DbServiceSimpleMock dbServiceMock, List<Request> requestsBelowIndex,
            int searchedIndex) {
        if (requestsBelowIndex.get(searchedIndex) instanceof ClientRequest clientRequest) {
            long processedClientRequestsCount = 1L;

            processedClientRequestsCount += IntStream.range(0, searchedIndex)
                    .mapToObj(dbServiceMock::getByIndex)
                    .filter(ClientRequest.class::isInstance)
                    .count();

            logger.info("{} : {}", "Object description", clientRequest);
            return Optional.of(processedClientRequestsCount);
        } else {
            return Optional.empty();
        }
    }
}

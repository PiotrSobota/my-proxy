package com.x.eleven;

import com.x.eleven.dbmock.DbServiceSimpleMock;
import com.x.eleven.payload.Payload;
import com.x.eleven.payload.requests.ClientRequest;
import com.x.eleven.payload.requests.Request;
import java.util.ArrayList;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ClientRequestCounterService {

    private final Logger logger = LoggerFactory.getLogger(ClientRequestCounterService.class);

    public Optional<Long> calculateProcessedClientRequestsCount(DbServiceSimpleMock dbServiceMock,
            ArrayList<Request> requestsToIndex, int searchedIndex) {
        Long processedClientRequestsCount = null;
        if (requestsToIndex.get(searchedIndex) instanceof ClientRequest clientRequest) {
            processedClientRequestsCount = 0L;
            for (int i = 0; i < searchedIndex; i++) {
                Request request = dbServiceMock.getByIndex(i);
                if (request instanceof ClientRequest) {
                    processedClientRequestsCount++;
                }
            }
            logger.info("{} : {}", "Object description", clientRequest);
        }

        return Optional.ofNullable(processedClientRequestsCount);
    }

    public ArrayList<Request> collectFromZeroToIndex(DbServiceSimpleMock dbServiceMock, int index) {
        ArrayList<Request> valuesToIndex = new ArrayList<>();
        for (int i = 0; i <= index; i++) {
            valuesToIndex.add(dbServiceMock.getByIndex(i));
        }
        return valuesToIndex;
    }
}

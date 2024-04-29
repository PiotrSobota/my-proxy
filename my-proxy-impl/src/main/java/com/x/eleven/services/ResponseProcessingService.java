package com.x.eleven.services;

import com.x.eleven.dbmock.DbServiceSimpleMock;
import com.x.eleven.payload.Payload;
import com.x.eleven.payload.requests.Request;
import com.x.eleven.payload.requests.ServerResponseRequest;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

public class ResponseProcessingService {

    private final ClientRequestCounterService requestCounterService;
    private final DbServiceSimpleMock dbServiceMock;

    public ResponseProcessingService(ClientRequestCounterService clientRequestCounterService,
            DbServiceSimpleMock dbServiceMock) {
        this.requestCounterService = clientRequestCounterService;
        this.dbServiceMock = dbServiceMock;
    }

    public Optional<Long> processClientRequest(Payload responseRequest) {
        int searchedIndex = ((ServerResponseRequest) responseRequest).getIndex();
        List<Request> requestsToIndex = collectRequestsBelowIndex(dbServiceMock, searchedIndex);
        // Could have additional processes, but now only count is needed
        return requestCounterService
                .countClientRequests(dbServiceMock, requestsToIndex, searchedIndex)
                .map(clientRequestsCount -> clientRequestsCount - 1);
    }

    private List<Request> collectRequestsBelowIndex(DbServiceSimpleMock dbServiceMock, int index) {
        return IntStream.rangeClosed(0, index)
                .mapToObj(dbServiceMock::getByIndex)
                .toList();
    }
}

package com.x.eleven.dbmock;

import com.x.eleven.payload.Payload;
import com.x.eleven.payload.requests.Request;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class DbServiceSimpleMock {

    private final LinkedHashMap<String, Request> payloads = new LinkedHashMap<>();

    public void set(Payload payload) {
        Request request = (Request) payload;
        payloads.put(request.getRequestId(), request);
    }

    public Request getByIndex(int index) {
        ArrayList<Request> values = new ArrayList<>(payloads.values());
        return values.get(index);
    }
}

package com.x.eleven.dbmock;

import com.x.eleven.payload.Payload;
import com.x.eleven.payload.requests.Request;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class DbServiceSimpleMock {

    LinkedHashMap<String, Request> payloads = new LinkedHashMap<>();

    public void insert(Payload payload) {
        Request request = (Request) payload;

        if (!payloads.containsKey(request.getRequestId())) {
            payloads.put(request.getRequestId(), request);
        } else {
            throw new IllegalArgumentException("Duplicated key, not allowed for insert method");
        }
    }

    public Request getByIndex(int index) {
        ArrayList<Request> values = new ArrayList<>(payloads.values());
        return values.get(index);
    }
}

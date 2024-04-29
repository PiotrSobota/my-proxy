package com.x.eleven.payload.requests;

public class ClientRequest extends Request {

    public ClientRequest(String requestId, long timestamp) {
        super(requestId, timestamp, Source.CLIENT);
    }
}

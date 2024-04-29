package com.x.eleven.payload.requests;

public class InternalRequest extends Request {

    public InternalRequest(String requestId, long timestamp) {
        super(requestId, timestamp, Source.INTERNAL);
    }
}

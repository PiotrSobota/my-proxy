package com.x.eleven.payload.requests;

import com.x.eleven.payload.Payload;

public abstract class Request implements Payload {

    private String requestId;
    private long timestamp;
    private Source source;

    public Request(String requestId, long timestamp, Source source) {
        this.requestId = requestId;
        this.timestamp = timestamp;
        this.source = source;
    }

    public String getRequestId() {
        return requestId;
    }

    @Override
    public String toString() {
        return "Request{" +
                "requestId='" + requestId + '\'' +
                ", timestamp=" + timestamp +
                ", source=" + source +
                '}';
    }

    public enum Source {
        CLIENT, INTERNAL
    }
}

package com.x.eleven.payload.requests;

import com.x.eleven.payload.Payload;

public class ServerResponseRequest implements Payload {

    private int index;

    public ServerResponseRequest(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }
}

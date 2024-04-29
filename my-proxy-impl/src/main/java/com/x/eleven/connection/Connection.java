package com.x.eleven.connection;

import com.x.eleven.payload.Payload;

public interface Connection {

    void send(Payload payload);
}

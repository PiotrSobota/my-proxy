package com.x.eleven;

public class Main {

    public static void main(String[] args) {
//        proxy.fromClient(new ClientRequest());
//        proxy.internalRequest(new InternalRequest());
//        proxy.fromClient(new ClientRequest());
//        proxy.fromClient(new ClientRequest());
//        proxy.fromClient(new ClientRequest());
//        proxy.internalRequest(new InternalRequest());
//        proxy.fromClient(new ClientRequest());
//        System.out.println("----------------------------------------------------");
//        proxy.fromServer(new ServerResponse(0)); // should keep sequence number 0
//        proxy.fromServer(new ServerResponse(1)); // should skip internal request
//        proxy.fromServer(new ServerResponse(2)); // should change sequence number to 1
//        proxy.fromServer(new ServerResponse(3)); // should change sequence number to 2
//        proxy.fromServer(new ServerResponse(4)); // should change sequence number to 3
//        proxy.fromServer(new ServerResponse(5)); // should skip internal request
//        proxy.fromServer(new ServerResponse(6)); // shoul

        // Expected result
        // Sending Server Response [0]
        // Sending Server Response [1]
        // Sending Server Response [2]
        // Sending Server Response [3]
        // Sending Server Response [4]
    }
}
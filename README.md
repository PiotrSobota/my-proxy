# My-Proxy

This is test project for my custom proxy which has the following responsibilities:
- Service records all incoming messages (from the internal server and clients) in the order they have arrived.
- Service would be called to get response for selected input message index.
It should respond only if request came from client. Internal requests should be skipped
- Sequence number in response should be sequence number of called client input.
- Internal requests should not affect sequence number in response.

## Requirements

- java 17
- maven

## Setup and running

### First time setup

```
$ mvn clean install -U
```
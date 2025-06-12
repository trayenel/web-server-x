package main.java.com.myWebServer.http.enums;

public enum HttpMethod {
    GET,
    POST,
    PUT,
    DELETE,
    HEAD,
    OPTIONS,
    TRACE,
    CONNECT,
    PATCH;

    public boolean isIdempotent() {
        return this == PUT || this == DELETE || this == HEAD || this == OPTIONS;
    }
}

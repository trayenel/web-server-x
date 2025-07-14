package com.trayenel.http.enums;

public enum HttpMethod {
    GET("GET"),
    POST("POST"),
    PUT("PUT"),
    DELETE("DELETE"),
    HEAD("HEAD"),
    OPTIONS("OPTIONS"),
    TRACE("TRACE"),
    CONNECT("CONNECT"),
    PATCH("PATCH");

    private final String text;

    HttpMethod(String method) {
        this.text = method;
    }

    public boolean isIdempotent() {
        return this == PUT || this == DELETE || this == HEAD || this == OPTIONS;
    }

    public static HttpMethod fromString(String method) {
        for (HttpMethod httpMethod : HttpMethod.values()) {
            if (httpMethod.text.equalsIgnoreCase(method)) {
                return httpMethod;
            }
        }
        throw new IllegalArgumentException("Method " + method + " not found");
    }
}

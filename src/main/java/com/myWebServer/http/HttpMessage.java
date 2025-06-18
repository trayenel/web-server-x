package main.java.com.myWebServer.http;

import main.java.com.myWebServer.http.enums.HttpVersion;

import java.util.HashMap;
import java.util.Map;

public abstract class HttpMessage {
    private final Map<String, String> headers = new HashMap<>();
    protected HttpVersion httpVersion;

    protected void addHeader(String key, String value) {
        this.headers.put(key, value);
    };

    protected void setHttpVersion(String httpVersion) {
        this.httpVersion = HttpVersion.fromString(httpVersion);
    };

    public HttpVersion getHttpVersion() {
        return httpVersion;
    }

    public void printHeaders() {
        for (Map.Entry<String, String> header : this.headers.entrySet()) {
            System.out.println(header.getKey() + ": " + header.getValue());
        }
    }

    public String getHeader(String key) {
        return this.headers.get(key);
    }
}

package main.java.com.myWebServer.http;

import main.java.com.myWebServer.http.enums.HttpVersion;

import java.util.HashMap;
import java.util.Map;

public abstract class HttpMessage {
    private final Map<String, String> headers = new HashMap<>();
    protected HttpVersion httpVersion;

    protected void addHeader(String key, String value) {
        this.headers.put(key, value);
    }

    protected void setHeaders(Map<String, String> headers) {
        this.headers.putAll(headers);
    }

    protected void setHttpVersion(String httpVersion) {
        this.httpVersion = HttpVersion.fromString(httpVersion);
    }

    public HttpVersion getHttpVersion() {
        return httpVersion;
    }

    public String getHeaders() {
        StringBuilder headers = new StringBuilder();

        for (Map.Entry<String, String> header : this.headers.entrySet()) {
            headers.append(header.getKey()).append(": ").append(header.getValue());
        }

        return headers.toString();
    }

    public String getHeader(String key) {
        return this.headers.get(key);
    }
}

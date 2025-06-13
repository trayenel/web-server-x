package main.java.com.myWebServer.http;

import main.java.com.myWebServer.http.enums.HttpVersion;

import java.util.HashMap;
import java.util.Map;

public abstract class HttpMessage {
    private Map<String, String> headers = new HashMap<>();
    protected HttpVersion httpVersion;

    protected void addHeader(Header header) {
        this.headers.put(header.key(), header.value());
    }

    protected void removeHeader(Header header) {
        this.headers.remove(header.key());
    }

    protected abstract void setHttpVersion(String httpVersion);

    public Map<String, String> getHeaders() {
        return headers;
    }

    public HttpVersion getHttpVersion() {
        return httpVersion;
    }

    protected void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }
}

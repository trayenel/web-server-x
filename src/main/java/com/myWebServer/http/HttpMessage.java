package main.java.com.myWebServer.http;

import java.util.HashMap;
import java.util.Map;

public abstract class HttpMessage {
    private Map<String, String> headers = new HashMap<>();
    protected String body;

    HttpMessage(String body) {
        this.body = body;
    }

    public void addHeader(Header header) {
        this.headers.put(header.key(), header.value());
    }

    public void removeHeader(Header header) {
        this.headers.remove(header.key());
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}

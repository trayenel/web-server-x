package main.java.com.myWebServer.http;

import main.java.com.myWebServer.http.enums.HttpMethod;
import main.java.com.myWebServer.http.enums.HttpStatusCode;

public class HttpRequest extends HttpMessage {
    private HttpStatusCode statusCode;
    private HttpMethod method;

    HttpRequest(String body, HttpStatusCode statusCode, HttpMethod method) {
        super(body);
        this.statusCode = statusCode;
        this.method = method;
    }

    public HttpStatusCode getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(HttpStatusCode statusCode) {
        this.statusCode = statusCode;
    }

    public HttpMethod getMethod() {
        return method;
    }

    public void setMethod(HttpMethod method) {
        this.method = method;
    }
}
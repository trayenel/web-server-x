package main.java.com.myWebServer.http;

import main.java.com.myWebServer.http.enums.HttpMethod;
import main.java.com.myWebServer.http.enums.HttpVersion;

public abstract class HttpHandler {
    HttpRequest httpRequest;

    HttpHandler(HttpRequest httpRequest) {
        this.httpRequest = httpRequest;
    }

    public HttpResponse handleRequest() {
        HttpMethod method = httpRequest.getMethod();

        return switch (method) {
            case GET -> handleGet();
            case POST -> handlePost();
            case PUT -> handlePut();
            case DELETE -> handleDelete();
            case PATCH -> handlePatch();
            default -> handleBadMethod();
        };
    }

    private HttpResponse handleBadMethod() {
        return new HttpResponse(HttpVersion.HTTP_1_1, 405, "");
    }

    protected abstract HttpResponse handleGet();

    protected abstract HttpResponse handlePost();

    protected abstract HttpResponse handlePut();

    protected abstract HttpResponse handleDelete();

    protected abstract HttpResponse handlePatch();
}

package main.java.com.myWebServer.http;

import main.java.com.myWebServer.http.enums.HttpMethod;
import main.java.com.myWebServer.http.enums.HttpStatusCode;
import main.java.com.myWebServer.http.login.LoginHandler;
import main.java.com.myWebServer.http.user.UserHandler;
import main.java.com.myWebServer.managers.FileManager;

import java.nio.file.Path;
import java.util.HashMap;

public class HttpHandler {
    protected HttpRequest httpRequest;

    protected HttpHandler(HttpRequest httpRequest) {
        this.httpRequest = httpRequest;
    }

    public static HttpHandler createHandler(HttpRequest httpRequest) {
        String path = httpRequest.getPath();
        
        return switch (path) {
            case "/login" -> new LoginHandler(httpRequest);
            case "/users" -> new UserHandler(httpRequest);
            default -> new HttpHandler(httpRequest);
        };
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

    protected HttpResponse handleGet() {
        Path path = Path.of("/home/trayenel/programmingProjects/web-server-x/index.html");

        FileManager fm = new FileManager(path);
        String file = fm.start();

        HashMap<String, String> headers = new HashMap<>();

        headers.put("Content-Type", "text/html");

        return new HttpResponse(httpRequest.getHttpVersion(), 200, file, headers);
    }

    protected HttpResponse handlePost() {
        return this.handleBadMethod();
    }

    protected HttpResponse handlePut() {
        return this.handleBadMethod();
    }

    protected HttpResponse handleDelete() {
        return this.handleBadMethod();
    }

    protected HttpResponse handlePatch() {
        return this.handleBadMethod();
    }

    protected HttpResponse handleBadMethod() {
        int statusCode = 405;

        return new HttpResponse(httpRequest.getHttpVersion(), statusCode, HttpStatusCode.fromCode(405).getDescription(), null);
    }
}
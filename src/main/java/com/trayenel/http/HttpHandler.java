package com.trayenel.http;

import com.trayenel.http.enums.HttpMethod;
import com.trayenel.http.enums.HttpStatusCode;
import com.trayenel.http.login.LoginHandler;
import com.trayenel.http.user.UserHandler;
import com.trayenel.file.FileManager;
import com.trayenel.url.UrlRouter;
import com.trayenel.db.DatabaseManager;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.Objects;

public class HttpHandler {
    protected HttpRequest httpRequest;
    protected FileManager fileManager;
    protected UrlRouter urlRouter;
    protected DatabaseManager databaseManager;
    private final String htmlFiesPath;

    protected HttpHandler(HttpRequest httpRequest, FileManager fileManager, UrlRouter urlRouter, DatabaseManager databaseManager, String htmlFiesPath) {
        this.urlRouter = urlRouter;
        this.fileManager = fileManager;
        this.httpRequest = httpRequest;
        this.databaseManager = databaseManager;
        this.htmlFiesPath = Objects.requireNonNull(this.getClass().getClassLoader().getResource(htmlFiesPath)).getPath();
    }

    public static HttpHandler createHandler(HttpRequest httpRequest, FileManager fileManager, UrlRouter urlRouter, DatabaseManager databaseManager, String htmlFiesPath) {
        String[] pathParts = httpRequest.getPath().split("/");
        String path = "/";

        if (pathParts.length > 1) {
            path = pathParts[1];
        }

        return switch (path) {
            case "login" -> new LoginHandler(httpRequest, fileManager, urlRouter, databaseManager, htmlFiesPath);
            case "user" -> new UserHandler(httpRequest, fileManager, urlRouter, databaseManager, htmlFiesPath);
            default -> new HttpHandler(httpRequest, fileManager, urlRouter, databaseManager, htmlFiesPath);
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
        String route = urlRouter.handleRoute(httpRequest.getPath());

        Path path = Path.of(this.htmlFiesPath + "/" + route);
        int statusCode;

        if (Objects.equals(route, "error.html")) {
            statusCode = 404;
        } else {
            statusCode = 200;
        }

        this.fileManager.loadFile(path);
        String file = this.fileManager.start();

        HashMap<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "text/html");

        return new HttpResponse(httpRequest.getHttpVersion(), statusCode, file, headers);
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
        HashMap<String, String> headers = new HashMap<>();

        headers.put("Content-Type", "application/json");

        return new HttpResponse(httpRequest.getHttpVersion(), statusCode, HttpStatusCode.fromCode(405).getDescription(), headers);
    }
}
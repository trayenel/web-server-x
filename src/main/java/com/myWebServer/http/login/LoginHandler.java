package main.java.com.myWebServer.http.login;

import main.java.com.myWebServer.http.HttpHandler;
import main.java.com.myWebServer.http.HttpRequest;
import main.java.com.myWebServer.http.HttpResponse;

public class LoginHandler extends HttpHandler {
    public LoginHandler(HttpRequest httpRequest) {
        super(httpRequest);
    }

    @Override
    protected HttpResponse handleGet() {
        return null;
    }

    @Override
    protected HttpResponse handlePost() {
        return null;
    }

    @Override
    protected HttpResponse handlePut() {
        return null;
    }

    @Override
    protected HttpResponse handleDelete() {
        return null;
    }

    @Override
    protected HttpResponse handlePatch() {
        return null;
    }
}

package main.java.com.myWebServer.http.user;

import main.java.com.myWebServer.http.HttpHandler;
import main.java.com.myWebServer.http.HttpRequest;
import main.java.com.myWebServer.http.HttpResponse;

public class UserHandler extends HttpHandler {

   public UserHandler(HttpRequest httpRequest) {
        super(httpRequest);
    }

    @Override
    protected HttpResponse handleGet() {

        return new HttpResponse(this.httpRequest.getHttpVersion(), 200, "Body lol", null);
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

package main.java.com.myWebServer.http.user;

import main.java.com.myWebServer.http.HttpHandler;
import main.java.com.myWebServer.http.HttpRequest;
import main.java.com.myWebServer.http.HttpResponse;
import main.java.com.myWebServer.managers.FileManager;
import main.java.com.myWebServer.url.UrlRouter;

public class UserHandler extends HttpHandler {

   public UserHandler(HttpRequest httpRequest, FileManager fileManager,  UrlRouter urlRouter, String htmlFilesPath) {
        super(httpRequest,  fileManager, urlRouter, htmlFilesPath);
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

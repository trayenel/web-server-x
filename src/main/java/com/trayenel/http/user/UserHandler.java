package com.trayenel.http.user;

import com.trayenel.http.HttpHandler;
import com.trayenel.http.HttpRequest;
import com.trayenel.http.HttpResponse;
import com.trayenel.base.FileManager;
import com.trayenel.url.UrlRouter;

public class UserHandler extends HttpHandler {

   public UserHandler(HttpRequest httpRequest, FileManager fileManager, UrlRouter urlRouter, String htmlFilesPath) {
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

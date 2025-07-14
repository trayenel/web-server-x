package com.trayenel.http.login;

import com.trayenel.http.HttpHandler;
import com.trayenel.http.HttpRequest;
import com.trayenel.http.HttpResponse;
import com.trayenel.managers.FileManager;
import com.trayenel.url.UrlRouter;

public class LoginHandler extends HttpHandler {
    public LoginHandler(HttpRequest httpRequest, FileManager fileManager, UrlRouter urlRouter, String htmlFilesPath) {
        super(httpRequest, fileManager, urlRouter, htmlFilesPath);
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

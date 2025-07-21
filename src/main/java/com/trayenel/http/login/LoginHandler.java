package com.trayenel.http.login;

import com.trayenel.http.HttpHandler;
import com.trayenel.http.HttpRequest;
import com.trayenel.http.HttpResponse;
import com.trayenel.file.FileManager;
import com.trayenel.url.UrlRouter;
import com.trayenel.db.DatabaseManager;

public class LoginHandler extends HttpHandler {
    public LoginHandler(HttpRequest httpRequest, FileManager fileManager, UrlRouter urlRouter, DatabaseManager databaseManager, String htmlFilesPath) {
        super(httpRequest, fileManager, urlRouter, databaseManager, htmlFilesPath);
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
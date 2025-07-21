package com.trayenel.http.user;

import com.trayenel.http.HttpHandler;
import com.trayenel.http.HttpRequest;
import com.trayenel.http.HttpResponse;
import com.trayenel.file.FileManager;
import com.trayenel.url.UrlRouter;
import com.trayenel.db.DatabaseManager;

import java.sql.SQLException;
import java.util.HashMap;

public class UserHandler extends HttpHandler {

   public UserHandler(HttpRequest httpRequest, FileManager fileManager, UrlRouter urlRouter, DatabaseManager databaseManager, String htmlFilesPath) {
        super(httpRequest, fileManager, urlRouter, databaseManager, htmlFilesPath);
    }

    @Override
    protected HttpResponse handleGet() {
        String result = "User not found";
        int statusCode = 404;

        try {
           if (this.httpRequest.getPath().split("/").length > 2) {
               int id = Integer.parseInt(this.httpRequest.getPath().split("/")[2]);

               HashMap<String, Object> user = (HashMap<String, Object>) this.databaseManager.selectById(id, "user");

               if (user != null) {
                   result = user.toString();
                   statusCode = 200;
               }
           }

           return new HttpResponse(this.httpRequest.getHttpVersion(), statusCode, result, new HashMap<>());
       } catch (SQLException e) {
           throw new RuntimeException(e);
       }
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
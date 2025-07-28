package com.trayenel.http.user;

import com.google.gson.Gson;
import com.trayenel.http.HttpHandler;
import com.trayenel.http.HttpRequest;
import com.trayenel.http.HttpResponse;
import com.trayenel.file.FileManager;
import com.trayenel.http.enums.HttpMethod;
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
                   Gson gson = new Gson();
                   result = gson.toJson(user);
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
           String body = this.httpRequest.getBody();
           Gson gson = new Gson();

           HashMap<String, Object> user = gson.fromJson(body, HashMap.class);

           try {
               int statusCode = this.databaseManager.insert("user", user);
               String result;

               if (statusCode == 200) {
                   result = gson.toJson(user);
               } else {
                   result = "";
               }

               return new HttpResponse(this.httpRequest.getHttpVersion(), statusCode, result, new HashMap<>());
           } catch (SQLException e) {
               System.out.println(e);
               return new HttpResponse(this.httpRequest.getHttpVersion(), 400, e.getMessage(), new HashMap<>());
           }
    }

    @Override
    protected HttpResponse handlePut() {
        return null;
    }

    @Override
    protected HttpResponse handleDelete() {
        String result = "User not found";
        int statusCode = 404;

        try {
            if (this.httpRequest.getPath().split("/").length > 2) {
                int id = Integer.parseInt(this.httpRequest.getPath().split("/")[2]);

                statusCode = this.databaseManager.deleteById(id, "user");
                result = "";
            }

            return new HttpResponse(this.httpRequest.getHttpVersion(), statusCode, result, new HashMap<>());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected HttpResponse handlePatch() {
        return null;
    }
}
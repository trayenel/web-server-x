package main.java.com.myWebServer.url;

import main.java.com.myWebServer.base.Router;
import main.java.com.myWebServer.http.HttpResponse;
import main.java.com.myWebServer.managers.FileManager;
import main.java.com.myWebServer.managers.Manager;

import java.nio.charset.StandardCharsets;

public class UrlRouter extends Router {
    private final FileManager fileManager;

    public UrlRouter(FileManager fileManager) {
        this.fileManager = fileManager;
    }

    @Override
    public void loadConfig(String configPath) {
        String configFile = fileManager.start(configPath);
    }

    @Override
    public void addRoute(String route) {
        fileManager.writeString(route);
    }

    @Override
    public void removeRoute() {

    }

    @Override
    public void handleRoute(String route) {
    }
}
package main.java.com.myWebServer.url;

import main.java.com.myWebServer.base.Router;
import main.java.com.myWebServer.managers.FileManager;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UrlRouter extends Router {
    private final FileManager fileManager;
    private final HashMap<String, String> routes;

    public UrlRouter(FileManager fileManager, String configPath) {
        this.fileManager = fileManager;
        this.routes = new HashMap<String, String>();

        loadConfig(configPath);
    }

    @Override
    public void loadConfig(String configPath) {
        String configFile = fileManager.start(configPath);

        Pattern pattern = Pattern.compile("^ *([^:\\n]+):\\s*\"([^\"]+)\"", Pattern.MULTILINE);
        Matcher matcher = pattern.matcher(configFile);

        while (matcher.find()) {
            String key = matcher.group(1).trim();
            String value = matcher.group(2).trim();

            routes.put(key, value);
        }
    }

    @Override
    public void addRoute(String route) {
        fileManager.writeString(route);
    }

    @Override
    public void removeRoute() {

    }

    @Override
    public String handleRoute(String route) {
         String storedPage = this.routes.get(route);

         if  (storedPage == null) {
             return "Not found";
         }

         return storedPage;
    }
}
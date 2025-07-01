package main.java.com.myWebServer.url;

import main.java.com.myWebServer.base.Router;
import main.java.com.myWebServer.managers.FileManager;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UrlRouter extends Router {
    private final FileManager fileManager;
    private final HashMap<String, String> routes;

    public UrlRouter(String configPath) {
        this.fileManager = new FileManager(Path.of(configPath));
        this.routes = new HashMap<>();

        loadConfig();
    }

    @Override
    public void loadConfig() {
        String configFile = fileManager.start();

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
        int colonIdx = route.indexOf(':');
        int slashIdx = route.indexOf('/');

        if (colonIdx != -1 && slashIdx != -1) {
            String key = route.substring(0, colonIdx).trim();

            if (!routes.containsKey(key)) {
                String value = route.substring(colonIdx + 1).trim();
                routes.put(key, value);

                fileManager.writeString(String.format("\n  %s: \"%s\"", key, value));
            } else {
                System.err.println("Route already added");
            }
        } else {
            System.err.println("Invalid route format");
        }
    }

    @Override
    public void removeRoute() {

    }

    @Override
    public String handleRoute(String route) {
         String storedRoute = this.routes.get(route);

         if  (storedRoute == null) {
             return "Not found";
         }

         return storedRoute;
    }
}
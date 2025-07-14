package com.trayenel.url;

import com.trayenel.base.Configurable;
import com.trayenel.base.Router;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UrlRouter extends Router implements Configurable {
    private final HashMap<String, String> routes;

    public UrlRouter() {
        this.routes = new HashMap<>();
    }

    @Override
    public void loadConfig(String configFile) {
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
             return this.routes.get("/404");
         }

         return storedRoute;
    }
}
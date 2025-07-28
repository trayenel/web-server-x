package com.trayenel.url;

import com.trayenel.base.Configurable;
import com.trayenel.base.Router;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

public class UrlRouter extends Router implements Configurable {
    private Map<String, Object> routes;

    public UrlRouter() {
        this.routes = new HashMap<>();
    }

    @Override
    public void loadConfig(Map<String, Object> config) {
        this.routes = config;
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
         Object storedRoute = this.routes.get(route);

         if  (storedRoute == null) {
             return this.routes.get("/404").toString();
         }

         return storedRoute.toString();
    }
}
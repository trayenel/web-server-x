package com.trayenel.base;

import java.util.Map;

public abstract class Router {
    public abstract void addRoute(String route);

    public abstract void removeRoute();

    public abstract String handleRoute(String route);
}

package main.java.com.myWebServer.base;

public abstract class Router {
    public abstract void addRoute(String route);

    public abstract void removeRoute();

    public abstract String handleRoute(String route);
}

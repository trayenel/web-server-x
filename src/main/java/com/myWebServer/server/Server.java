package main.java.com.myWebServer.server;

import main.java.com.myWebServer.http.HttpRequest;
import main.java.com.myWebServer.http.HttpResponse;
import main.java.com.myWebServer.http.enums.HttpVersion;
import main.java.com.myWebServer.managers.FileManager;
import main.java.com.myWebServer.url.UrlRouter;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class Server {
    private final ServerSocket serverSocket;

    public Server(int port) throws IOException {
        System.out.println("Starting server on port " + port);
        serverSocket = new ServerSocket(port);
    }

    public void listen() throws IOException {
        FileManager fm = new FileManager();
        UrlRouter urlRouter = new UrlRouter(fm);

        while (true) {
            Socket clientSocket = serverSocket.accept();
            clientSocket.setKeepAlive(true);
            InputStream inputStream = clientSocket.getInputStream();
            OutputStream outputStream = clientSocket.getOutputStream();

            System.out.println("New connection established from " + clientSocket.getInetAddress());

            HttpRequest request = new HttpRequest(inputStream);
            HttpResponse response = new HttpResponse(HttpVersion.HTTP_1_1, 200, "Hello World");

            urlRouter.handleRoute(request.getPath());

            byte[] bytes = HttpResponse.stringify(response).getBytes(StandardCharsets.UTF_8);

            outputStream.write(bytes);
            outputStream.flush();

            inputStream.close();
            outputStream.close();
        }
    }
}
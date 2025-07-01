package main.java.com.myWebServer.server;

import main.java.com.myWebServer.http.HttpRequest;
import main.java.com.myWebServer.http.HttpResponse;
import main.java.com.myWebServer.url.UrlRouter;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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
        UrlRouter urlRouter = new UrlRouter("src/main/java/com/myWebServer/routes.yaml");

        while (true) {
            Socket clientSocket = serverSocket.accept();
            clientSocket.setKeepAlive(true);
            InputStream inputStream = clientSocket.getInputStream();
            OutputStream outputStream = clientSocket.getOutputStream();

            System.out.println("New connection established from " + clientSocket.getInetAddress());

            HttpRequest request = new HttpRequest(inputStream);

            HttpResponse response = new HttpResponse(request.getHttpVersion(), 200, urlRouter.handleRoute(request.getPath()));

            byte[] bytes = HttpResponse.stringify(response).getBytes(StandardCharsets.UTF_8);

            outputStream.write(bytes);
            outputStream.flush();

            inputStream.close();
            outputStream.close();
        }
    }
}
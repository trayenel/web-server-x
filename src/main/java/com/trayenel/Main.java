package com.trayenel;

import com.trayenel.server.Server;

public class Main {
    public static void main(String[] args) {
        try {
            Server server = new Server("src/main/java/com/trayenel/config.yaml");

            server.listen();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
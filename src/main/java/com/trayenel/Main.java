package com.trayenel;

import com.trayenel.server.Server;

public class Main {
    public static void main(String[] args) {
        try {
            Server server = new Server("config.yaml");

            server.listen();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
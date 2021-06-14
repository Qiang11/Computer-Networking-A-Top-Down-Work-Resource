package com.computetNetWorking.resource.chapter2_webServer;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class WebServer {

    public static void main(String[] args) {
        try {
            ServerSocket socket = new ServerSocket(10001);
            while (true){
                Socket connection  = socket.accept();
                HttpServer httpServer = new HttpServer(connection);
                new Thread(httpServer).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}



package com.computetNetWorking.resource.chapter2_tcp;

import java.net.ServerSocket;
import java.net.Socket;

public class TcpServer {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(10000);
            while (true){
                Socket socket = serverSocket.accept();
                TcpResponse tcpResponse = new TcpResponse(socket);
                new Thread(tcpResponse).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

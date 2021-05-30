package com.computetNetWorking.resource.chapter2_tcp;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpServer {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(10000);
            Socket socket = serverSocket.accept();
            while (true){
                InputStream inputStream = socket.getInputStream();
                byte[] buffer = new byte[1024];
                int length = inputStream.read(buffer);
                String str = new String(buffer,0,length);
                System.out.println("服务端接受:"+str);
                if (str.equals("q")){
                    serverSocket.close();
                    System.out.println("服务端退出");
                    break;
                }

                //服务端发送数据到客户端
                OutputStream outputStream = socket.getOutputStream();
                String data = str.toUpperCase();
                System.out.println("服务端响应客户端数据："+data);
                outputStream.write(data.getBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

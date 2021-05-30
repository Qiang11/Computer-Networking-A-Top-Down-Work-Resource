package com.computetNetWorking.resource.chapter2_tcp;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class TcpClient {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket(InetAddress.getLocalHost(),10000);
            OutputStream outputStream = socket.getOutputStream();
            Scanner sc = new Scanner(System.in);
            while (true){
                String data = sc.next();
                outputStream.write(data.getBytes());
                System.out.println("客户端发送数据到服务端："+data);
                if (data.equals("q")){
                    socket.close();
                    System.out.println("客户端退出");
                    break;
                }

                //客户端接受服务端发送得数据
                InputStream inputStream = socket.getInputStream();
                byte[] buffer = new byte[1024];
                int length = inputStream.read(buffer);
                String str = new String(buffer,0,length);
                System.out.println("客户端接收服务端响应数据："+str);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

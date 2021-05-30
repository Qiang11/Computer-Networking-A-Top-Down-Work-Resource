package com.computetNetWorking.resource.chapter2_udp;


import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class UdpClient {
    public static void main(String[] args) {
        try {
            DatagramSocket socket = new DatagramSocket();
            Scanner sc = new Scanner(System.in);
            while (true){
                String data = sc.next();
                System.out.println("客户端发送数据到服务端："+data);
                DatagramPacket packet = new DatagramPacket(data.getBytes(),0,data.length(),
                        InetAddress.getLocalHost(),9999);
                socket.send(packet);
                if (data.equals("q")){
                    socket.close();
                    System.out.println("客户端退出");
                    break;
                }

                //客户端接收服务端数据
                byte[] buff = new byte[1024];
                DatagramPacket inputPacket = new DatagramPacket(buff,buff.length);
                socket.receive(inputPacket);
                String str2 = new String(buff,0,inputPacket.getLength());
                System.out.println("客户端接收服务端响应数据："+str2);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

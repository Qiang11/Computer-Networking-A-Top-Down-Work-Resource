package com.computetNetWorking.resource.chapter2_udp;


import java.net.DatagramPacket;
import java.net.DatagramSocket;


public class UdpServer {
    public static void main(String[] args) {
        try {
            DatagramSocket socket = new DatagramSocket(9999);
            while (true){
                byte[] buff = new byte[1024];
                DatagramPacket packet = new DatagramPacket(buff,buff.length);
                socket.receive(packet);
                String str = new String(buff,0,packet.getLength());
                System.out.println("服务端接受客户端发送数据："+str);
                //客户端发送q请求socket断开
                if (str.equals("q")){
                    System.out.println("服务端退出");
                    socket.close();
                    break;
                }

                //服务端向客户端发送数据
                String str2 = str.toUpperCase();
                System.out.println("服务端响应客户端数据："+str2);
                packet.setData(str2.getBytes());
                socket.send(packet);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

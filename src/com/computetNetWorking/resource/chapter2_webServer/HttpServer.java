package com.computetNetWorking.resource.chapter2_webServer;

import java.io.*;
import java.net.Socket;
import java.util.StringTokenizer;

public class HttpServer implements Runnable{

    Socket connection;

    public HttpServer(Socket connection){
        this.connection = connection;
    }


    @Override
    public void run() {
        try {
            System.out.println(Thread.currentThread().getName());
            InputStream is = connection.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            StringTokenizer tokens = new StringTokenizer(br.readLine());
            tokens.nextToken(); //跳过get请求符号
            String fileName = tokens.nextToken();
            String line;
            while ((line=br.readLine()).length()!=0){
                System.out.println(line);
            }

            File file = new File("D:\\work\\Computer-Networking-A-Top-Down-Work\\src\\com\\computetNetWorking\\resource\\chapter2_webServer"+fileName);
            String header;
            OutputStream os = connection.getOutputStream();
            if (file.exists()){
                //这里一定要写\r\n否则浏览器不解析
                StringBuffer result = new StringBuffer();
                result.append("HTTP/1.1 200 ok \r\n");
                result.append("Content-Language:zh-CN \r\n");
                result.append("Content-Type:text/html;charset=UTF-8 \r\n");
                result.append("Content-Length:" + file.length() + "\r\n");
                result.append("\r\n");
                os.write(result.toString().getBytes());
                byte[] buff = new byte[1024];
                int bytes = 0;
                FileInputStream fis = new FileInputStream(file);
                while ((bytes = fis.read(buff))!=-1){
                    System.out.println(new String(buff,0,bytes));
                    os.write(buff,0,bytes);
                }
                fis.close();
            }else {
                header = "HTTP/1.1 404 Found \r\n";
                os.write(header.getBytes());
            }

            br.close();
            os.close();
            connection.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

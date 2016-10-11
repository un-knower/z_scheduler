package com.bigdata;

import java.io.IOException;
import java.net.Socket;

/**
 * Created by GP39 on 2016/8/16.
 */
public class CheckPortIsOpen {
    public static void main(String[] arg){
        String ip = "localhost";
        Integer port = 22;
        if(arg.length!=2){
            System.out.println("错误参数");
        }else{
          ip=arg[0];
          port=Integer.parseInt(arg[1]);
        }
        try {
            Socket socket = new Socket(ip, port);
            System.out.println("已经打开端口");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("没有打开端口");
        }
    }
}

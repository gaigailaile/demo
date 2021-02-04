package com.gai.jsch;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

public class JschDemo {
     //测试连接
//    public static void main(String[] args) throws JSchException {
//        //账号密码连接
//        JSch jSch = new JSch();
//        Session session = jSch.getSession("root","127.0.0.1",22);
//        session.setPassword("123456");
//        session.setConfig("StrictHostKeyChecking", "no");
//        session.connect();
//        if(session.isConnected()){
//            System.out.println("Host connected :" + session.getHost());
//        }
//        session.disconnect();
//    }

//    //测试命令
//    public static void main(String[] args) throws JSchException {
//        JSch jSch = new JSch();
//        Session session = jSch.getSession("root","127.0.0.1");
//        session.setPassword("123456");
//        session.setConfig("StrictHostKeyChecking","no");
//        session.connect();
//        if (session.isConnected()){
//            JSchUtils.remoteExecute(session,"ls -all ~/");
//        }
//        session.disconnect();
//    }

//    public static void main(String[] args) throws Exception {
//        JSch jSch = new JSch();
//        Session session = jSch.getSession("root","127.0.0.1");
//        session.setPassword("123456");
//        session.setConfig("StrictHostKeyChecking","no");
//        session.connect();
//        if(session.isConnected()){
//            System.out.println("Host connected :" + session.getHost());
//        }
//        JSchUtils.scpFrom(session, "/home/gaidongxu/1.txt", "file-from-remote.txt");
//
//        session.disconnect();
//    }

    public static void main(String[] args) throws Exception {
        JSch jSch = new JSch();
        Session session = jSch.getSession("dataexa","192.168.5.101");
        session.setPassword("DataExa@9038479457");
        session.setConfig("StrictHostKeyChecking","no");
        session.connect();
        if(session.isConnected()){
            System.out.println("Host connected :" + session.getHost());
        }
        JSchUtils.scpTo(session, "/Users/gaidongxu/a.py",
                "/home/dataexa/artisan-microservice/artisan-asset/a.py");

        JSchUtils.remoteExecute(session,"mkdir /home/dataexa/artisan-microservice/artisan-asset/pythondemo");

        JSchUtils.remoteExecute(session,"python /home/dataexa/artisan-microservice/artisan-asset/a.py");

//        JSchUtils.remoteExecute(session,"ls /home/dataexa/artisan-microservice/artisan-asset/");

        JSchUtils.remoteExecute(session,"rm /home/dataexa/artisan-microservice/artisan-asset/a.py");

//        JSchUtils.remoteExecute(session,"ls /home/dataexa/artisan-microservice/artisan-asset");

        session.disconnect();
    }
}

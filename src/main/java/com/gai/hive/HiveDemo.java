package com.gai.hive;

import org.apache.commons.lang3.StringUtils;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class HiveDemo {
    public static void main(String[] args) {
        //连接hive
        String driverName ="org.apache.hive.jdbc.HiveDriver";   // 此Class 位于 hive-jdbc的jar包下
//        String Url="jdbc:hive2://192.168.2.2:10000/hdfswriter";    //填写hive的IP，之前在配置文件中配置的IP
        String Url="jdbc:hive2://10.30.10.11:10000/gai";
        Connection conn = null;
        try {
            Class.forName(driverName);
//            conn = DriverManager.getConnection(Url,"dataexa","@dataexa");        //只是连接hive, 用户名可不传
            conn = DriverManager.getConnection(Url);
        } catch(ClassNotFoundException e)  {
            e.printStackTrace();
            System.exit(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Integer pageNum = 1;
        Integer pageSize = 10;
        Integer count = 0;

        try {
//            String sql = "\"delete from a_dst_user_info where name like 'zenmeshuo%'\"";
//            String selectsql = "select * from a_dst_user_info where name = 'zenmeshuo'";
            Statement sta = conn.createStatement();
//            int c = sta.executeUpdate(sql);
//            System.out.println(c);

//            PreparedStatement ps = conn.prepareStatement("select * from a_dst_user_info where name = 'zenmeshuo'");

            String selectSql = "select * from gaidemo";
            ResultSet rs = sta.executeQuery(selectSql);
            int columns=rs.getMetaData().getColumnCount();

            while(rs.next())
            {
                    for(int i=1;i<=columns;i++)
                    {
                        System.out.print(rs.getString(i));
                        System.out.print("\t\t");
                    }
                    System.out.println();
            }


//            ResultSet rs = ps.executeQuery();

//            ResultSetMetaData rsm = rs.getMetaData();
//            int count1 = rsm.getColumnCount();
//            List<String> headerList = new ArrayList<>();
//            for (int cnt = 0; cnt < count1; cnt++) {
//                String headerName = rsm.getColumnName(cnt + 1);
//                headerList.add(headerName);
//                System.out.println(headerName);
//            }

//            String sql = "insert into a_dst_user_info(name,sex) values('zenmeshuo','男')";
//            ps.executeUpdate(sql);
//
//            ResultSet rs = ps.executeQuery();
//            int columns=rs.getMetaData().getColumnCount();
//
//            while(rs.next())
//            {
//                count++;
//                if(count > (pageNum-1)*pageSize + pageSize){
//                    break;
//                }
//                if(count > (pageNum-1)*pageSize){
//                    for(int i=1;i<=columns;i++)
//                    {
//                        System.out.print(rs.getString(i));
//                        System.out.print("\t\t");
//                    }
//                    System.out.println();
//                }
//
//            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getLocalizedMessage());
        }
    }

//    public static void main(String[] args) throws ClassNotFoundException, SQLException, IllegalAccessException, InstantiationException, MalformedURLException {
//        Connection conn = null;
//        String driverPath = "/Users/gaidongxu/maven_lib/org/apache/hive/hive-jdbc/1.2.1/hive-jdbc-1.2.1.jar";
//        URLClassLoader classLoader = new URLClassLoader(new URL[]{new URL("file://" + driverPath)},Thread.currentThread().getContextClassLoader());
//
//        Driver driver = (Driver) Class.forName("org.apache.hive.jdbc.HiveDriver", true, classLoader).newInstance();
//        Properties properties = new Properties();
//
//        properties.setProperty("user", "dataexa");
//        properties.setProperty("password", "@dataexa");
//        conn = driver.connect("jdbc:hive2://192.168.2.2:10000/hdfswriter", properties);
//
//        PreparedStatement ps = conn.prepareStatement("select * from a_dst_user_info where name = 'zenmeshuo'");
//
//        ResultSet rs = ps.executeQuery();
//        int columns=rs.getMetaData().getColumnCount();
//
//        while(rs.next())
//        {
//                for(int i=1;i<=columns;i++)
//                {
//                    System.out.print(rs.getString(i));
//                    System.out.print("\t\t");
//                }
//                System.out.println();
//        }
//    }
}

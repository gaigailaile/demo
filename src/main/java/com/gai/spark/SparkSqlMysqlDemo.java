package com.gai.spark;

import com.google.common.base.Strings;
import org.apache.spark.SparkConf;
import org.apache.spark.SparkContext;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.*;

import java.util.*;

public class SparkSqlMysqlDemo {
    public static void main(String[] args) throws AnalysisException {
        SparkSession spark = SparkSession
                .builder()
                .appName("Spark SQL mysql example")
                .master("local")
                .getOrCreate();
        DataFrameReader reader = spark.read().format("jdbc");
        reader.option("url","jdbc:mysql://127.0.0.1:3306/demo?serverTimezone=GMT");
        reader.option("driver","com.mysql.cj.jdbc.Driver");
        reader.option("user","root");
        reader.option("password","*****");
        reader.option("dbtable","users").load().createOrReplaceTempView("users");
//        reader.option("dbtable","home").load().createOrReplaceTempView("home");
        Dataset users = reader.load();
        users.createOrReplaceTempView("users");
//        Dataset home = reader.option("dbtable","home").load();
//        home.createTempView("home");

        String sql = "SELECT * FROM users limit 5,10";
//        String alterSql = "ALTER TABLE users add birthday";
//        String createSql = "CREATE TABLE a (id INT,City VARCHAR (255))";

//        String sql = "SELECT * FROM users u,home h WHERE u.id = h.users_id";
        Dataset<Row> result = null;
        try{
            result = spark.sql(sql);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        Long count = result.count();
        List<String> dataList = null;
        Integer pageStart = 0;
        Integer pageEnd = 20;
        if(count > pageStart && count < pageEnd){
            dataList = result.toJSON().collectAsList().subList(pageStart, count.intValue());
        }else if(count >= pageEnd){
            result = result.limit(pageEnd);
            dataList = result.toJSON().collectAsList().subList(pageStart, pageEnd);
        }
        if(dataList == null){
            System.out.println("所在页无数据!");
        }else {
            dataList.stream().forEach(s -> System.out.println(s));
        }

//        System.out.println(result.limit(20).toJSON().);
        List<String> list =  result.toJSON().collectAsList().subList(10,15);
        list.stream().forEach(s -> System.out.println(s));
//        result.write().json("/Users/gaidongxu/mysql");

//        result.write().csv("/Users/gaidongxu/aa2");
        System.out.println(result.showString(50,0));

//        String sql1 = "SELECT * FROM home";
//        Dataset<Row> result1 = spark.sql(sql1);
//        result1.show();

    }

//    public static void main(String[] args) {
//        //首先新建一个sparkconf定义参数
//        SparkConf conf = new SparkConf().setMaster("local").setAppName("JDBCDataSource");
//        //创建sparkContext，是通往spark集群的唯一通道
//        JavaSparkContext sc = new JavaSparkContext(conf);
//        //新建一个sparksql
//        SQLContext sqlContext = new SQLContext(sc);
//        //sparksql连接mysql
//        /*
//         * 方法1：分别将两张表中的数据加载为DataFrame
//         * */
//        /*Map<String,String> options = new HashMap<String,String>();
//        options.put("url","jdbc:mysql://localhost:3306/tset");
//        options.put("driver","com.mysql.jdbc.Driver");
//        options.put("user","root");
//        options.put("password","admin");
//        options.put("dbtable","information");
//        Dataset myinfromation = sqlContext.read().format("jdbc").options(options).load();
//        //如果需要多张表，则需要再put一遍
//        options.put("dbtable","score");
//        Dataset scores = sqlContext.read().format("jdbc").options(options).load();*/
//
//        //方法2：分别将mysql中两张表的数据加载为DataFrame
//        DataFrameReader reader = sqlContext.read().format("jdbc");
//        reader.option("url","jdbc:mysql://127.0.0.1:3306/demo?serverTimezone=GMT");
//        reader.option("driver","com.mysql.cj.jdbc.Driver");
//        reader.option("user","root");
//        reader.option("password","Aiyanghui521");
//        reader.option("dbtable","users");
//        Dataset myinformation = reader.load();
////        reader.option("dbtable","score");
////        Dataset scores = reader.load();
//
//        //将两个DataFrame转换为javapairrdd，执行join操作
//        myinformation.registerTempTable("users");
////        scores.registerTempTable("score");
//
//        //定义sql语句
////        String sql = "select info.name,age"
////                +"      from info join score"
////                +"      on(info.name=score.name)"
////                +"      where score.score>90";
//        String sql = "SELECT * FROM users";
//
//        Dataset sql2 = sqlContext.sql(sql);
//        sql2.show();
//    }
}

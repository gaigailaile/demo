package com.gai.spark;

import com.google.common.base.Strings;
import org.apache.spark.SparkConf;
import org.apache.spark.SparkContext;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

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
        reader.option("password","Aiyanghui521");
        reader.option("dbtable","users");
        Dataset users = reader.load();
        users.createTempView("users");

        String sql = "SELECT * FROM users;";
        Dataset<Row> result = spark.sql(sql);
        result.write().json("/Users/gaidongxu/mysql");

//        result.write().csv("/Users/gaidongxu/aa2");
        result.show();

        String sql1 = "SELECT * FROM score";
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

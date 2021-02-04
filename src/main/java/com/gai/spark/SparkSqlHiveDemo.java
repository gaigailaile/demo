package com.gai.spark;

import org.apache.spark.sql.DataFrameReader;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

public class SparkSqlHiveDemo {
    public static void main(String[] args) {
        SparkSession spark = SparkSession
                .builder()
                .appName("Spark SQL hive example")
                .master("local")
//                .enableHiveSupport()
                .getOrCreate();
        DataFrameReader reader = spark.read().format("jdbc");
        reader.option("url","jdbc:hive2://localhost:10000/hdfs");
        reader.option("driver","com.cloudera.hive.jdbc41.HS2Driver");
        reader.option("user","root");
        reader.option("password","root");
        reader.option("dbtable","a");
        Dataset users = reader.load();
        users.createOrReplaceTempView("a");
//        reader.option("dbtable","score");
//        Dataset scores = reader.load();

        String sql = "select * from a where name like '%于霞%';";
        Dataset<Row> result = spark.sql(sql);
        result.show();
        result.write().json("/Users/gaidongxu/hive");

        spark.close();
    }
}

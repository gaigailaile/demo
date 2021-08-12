//package com.gai.temp;
//
//import com.alibaba.fastjson.JSONArray;
//import com.alibaba.fastjson.JSONObject;
//import org.geotools.data.shapefile.ShapefileDataStore;
//import org.geotools.data.simple.SimpleFeatureCollection;
//import org.geotools.data.simple.SimpleFeatureIterator;
//import org.geotools.data.simple.SimpleFeatureSource;
//import org.geotools.geojson.feature.FeatureJSON;
//import org.opengis.feature.simple.SimpleFeature;
//
//import java.io.*;
//import java.nio.charset.Charset;
//
//public class Shp {
//
//    public static void shpToJson(){
//        StringBuffer sb=new StringBuffer();
//        FeatureJSON fJson=new FeatureJSON();
//        String shpPath="/Users/gaidongxu/Desktop/天机图谱/矢量shp样例/地级行政界线/地级行政界线/diquJie_polyline.shp";
//        File file = new File(shpPath);
//        ShapefileDataStore store=null;
//        JSONArray array=new JSONArray();
//        JSONObject json=new JSONObject();
//        try {
//            store = new ShapefileDataStore(file.toURL());
//            Charset charset = Charset.forName("GBK");
//            String typeName =store.getTypeNames()[0];
//            SimpleFeatureSource featureSource=null;
//            featureSource=store.getFeatureSource(typeName);
//            SimpleFeatureCollection collection=featureSource.getFeatures();
//            SimpleFeatureIterator iterator=collection.features();
//            while (iterator.hasNext()) {
//                SimpleFeature feature=iterator.next();
//                StringWriter writer=new StringWriter();
//                fJson.writeFeature(feature, writer);
//                json=JSONObject.parseObject(writer.toString());
//                //array.add(json);//使用jsonArray可以把所有数据转成一条；不使用，
//                //下方输出只会输出一条JSON数据，如需存入数据库，改写此方法，在实现类里迭代。
//            }
//            iterator.close();
//            sb.append(json);
//        }catch (IOException e){
//            e.printStackTrace();
//        }
//    }
//
//    public static void WriteStringToFile(String string) {
//        String filePath="/Users/gaidongxu/Desktop/天机图谱/矢量shp样例/地级行政界线/地级行政界线/point.geojson.txt";
//        try {
//            File file = new File(filePath);
//            PrintStream ps = new PrintStream(new FileOutputStream(file));
//            ps.append(string);// 在已有的基础上添加字符串
//        } catch (Exception e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//    }
//
//    public static void main(String[] args) {
//        shpToJson();
//    }
//}

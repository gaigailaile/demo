package com.gai.httpclient;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HttpclientUtils {
//    public static void get(String url) throws IOException {
//        CloseableHttpClient httpClient = HttpClients.createDefault();
//        HttpGet httpGet = new HttpGet(url);
//        // 配置信息
//        RequestConfig requestConfig = RequestConfig.custom()
//                // 设置连接超时时间(单位毫秒)
//                .setConnectTimeout(5000)
//                // 设置请求超时时间(单位毫秒)
//                .setConnectionRequestTimeout(5000)
//                // socket读写超时时间(单位毫秒)
//                .setSocketTimeout(5000)
//                // 设置是否允许重定向(默认为true)
//                .setRedirectsEnabled(true).build();
//
//        // 将上面的配置信息 运用到这个Get请求里
//        httpGet.setConfig(requestConfig);
//
//        CloseableHttpResponse response = httpClient.execute(httpGet);
//        System.out.println("响应状态为:" + response.getStatusLine());
//        //获取状态码
//        int status = response.getStatusLine().getStatusCode();
//        if(status == 200){
//            HttpEntity httpEntity = response.getEntity();
//            System.out.println("相应内容为:" + EntityUtils.toString(httpEntity));
//        }
//        response.close();
//        httpClient.close();
//    }

//    public static void post(String url) throws IOException {
//        CloseableHttpClient httpClient = HttpClients.createDefault();
//        HttpPost httpPost = new HttpPost(url);
//
//        StringEntity entity = new StringEntity("{\"username\":\"admin\",\"password\":\"admin123\"}", "UTF-8");
//        httpPost.setEntity(entity);
//        httpPost.setHeader("Content-Type", "application/json;charset=utf8");
//        CloseableHttpResponse response = httpClient.execute(httpPost);
//
//        System.out.println("响应状态为:" + response.getStatusLine());
//        //获取状态码
//        int status = response.getStatusLine().getStatusCode();
//        if(status == 200){
//            HttpEntity httpEntity = response.getEntity();
//            System.out.println("相应内容为:" + EntityUtils.toString(httpEntity));
//        }
//        response.close();
//        httpClient.close();
//    }

    public static void post(String url) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);

        List<NameValuePair> list = new ArrayList<>();
        list.add(new BasicNameValuePair("username","admin"));
        list.add(new BasicNameValuePair("password","admin123"));

        UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(list);
        httpPost.setEntity(urlEncodedFormEntity);
        CloseableHttpResponse response = httpClient.execute(httpPost);

        System.out.println("响应状态为:" + response.getStatusLine());
        //获取状态码
        int status = response.getStatusLine().getStatusCode();
        if(status == 200){
            HttpEntity httpEntity = response.getEntity();
            System.out.println("相应内容为:" + EntityUtils.toString(httpEntity));
        }
        response.close();
        httpClient.close();
    }

    public static void main(String[] args) {
        try {
            HttpclientUtils.post("http://localhost:8080/Web_Demo_war_exploded/PostServlet");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

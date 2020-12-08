package com.gai.json;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class FastJson {
    static String jsonStr = "{\"people\":[{\"name\":\"gaidongxu\",\"age\":\"11\"},{\"name\":\"yanghui\",\"age\":\"12\"}]}";
    public static void main(String[] args) {
//        String json = FastJson.assembleJson();
//        System.out.println(json);
        FastJson.analysisJson();
    }

    //解析json
    public static void analysisJson(){
        JSONObject jsonObject = JSONObject.parseObject(jsonStr);

        JSONArray jsonArray = jsonObject.getJSONArray("people");
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject object = (JSONObject) jsonArray.get(i);
            String name = object.getString("name");
            Integer age = object.getInteger("age");
            System.out.println("name : " + name + " age : " + age);
        }
    }

    //组装json
    public static String assembleJson(){
        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        JSONObject peopleGai = new JSONObject();
        peopleGai.put("name","gaidongxu");
        peopleGai.put("age",11);
        jsonArray.add(peopleGai);
        JSONObject peopleYang = new JSONObject();
        peopleYang.put("name","yanghui");
        peopleYang.put("age",12);
        jsonArray.add(peopleYang);
        jsonObject.put("people",jsonArray);
        return jsonObject.toJSONString();
    }
}

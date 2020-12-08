package com.gai.json;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class RepeatedReferences {
    //错误示范
//    public static void main(String[] args) {
//        String json = FastJson.assembleJson();
//        JSONObject jsonObject = JSONObject.parseObject(json);
//        JSONArray jsonArray = (JSONArray) jsonObject.get("people");
//        for (Object object:jsonArray) {
//            JSONObject object1 = (JSONObject) object;
//            if("gaidongxu".equals(object1.getString("name"))){
//                object1.put("name","gaidongxu1");
//                jsonArray.add(object1);
//                break;
//            }
//        }
//        jsonObject.put("people",jsonArray);
//        System.out.println(jsonObject.toJSONString());
//    }

//    public static void main(String[] args) {
//        String json = FastJson.assembleJson();
//        JSONObject jsonObject = JSONObject.parseObject(json);
//        JSONArray jsonArray = (JSONArray) jsonObject.get("people");
//        for (Object object:jsonArray) {
//            JSONObject object1 = (JSONObject) object;
//            if("gaidongxu".equals(object1.getString("name"))){
//                object1.put("name","gaidongxu1");
//                break;
//            }
//        }
//        jsonObject.put("people",jsonArray);
//        System.out.println(jsonObject.toJSONString());
//    }

    public static void main(String[] args) {
        String json = FastJson.assembleJson();
        JSONObject jsonObject = JSONObject.parseObject(json);
        JSONArray jsonArray = (JSONArray) jsonObject.get("people");
        for (int i = 0; i < jsonArray.size(); i++){
            JSONObject object = (JSONObject) jsonArray.get(i);
            if("gaidongxu".equals(object.get("name"))){
                object.put("name","gaidongxu1");
                break;
            }
        }
//        ((JSONObject)jsonArray.get(0)).put("name","gaidongxu1");
        jsonObject.put("people",jsonArray);
        System.out.println(jsonObject.toJSONString());
    }
}

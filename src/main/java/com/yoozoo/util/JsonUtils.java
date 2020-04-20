package com.yoozoo.util;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Iterator;

/**
 * json相关工具类
 * Created by guoxx on 2018/10/17.
 */
public class JsonUtils {

    /**
     * 将jsonArray格式转换为json对象
     * @param jsonArray jsonArray对象
     * @return json对象
     */
    public static JSONObject getJsonByJsonArray(JSONArray jsonArray){

        JSONObject obj = new JSONObject();
        if (jsonArray != null){
            for(int i=0;i<jsonArray.length();i++){
                JSONObject aJson =(JSONObject) jsonArray.get(i);
                Iterator<String> it = aJson.keys();
                while (it.hasNext()){
                    String key = it.next();
                    Object value = aJson.get(key);
                    obj.put(key,value);
                }
            }
        }
        return obj;

    }
}

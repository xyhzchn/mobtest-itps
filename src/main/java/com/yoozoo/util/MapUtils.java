package com.yoozoo.util;

import com.yoozoo.bean.Role;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 *
 * Created by guoxx on 2018/6/28.
 */
public class MapUtils {
    /**
     * 将Map<Integer,Object>转换为Map<Integer,String>
     * @param oldMap Map<Integer,Object>类型
     * @return Map<Integer,String>类型
     */
    public static Map<Integer,String> getRoleMap(Map<Integer,Role> oldMap){
        Map<Integer,String> map = new HashMap<>();
        for(int i=0;i<oldMap.size();i++){
            for(Map.Entry<Integer,Role> entry :oldMap.entrySet()){
               Role role =  entry.getValue();
                map.put(role.getRoleId(),role.getRoleName());
            }
        }
        return map;
    }

    /**
     * 将String类型的json字符串，转换为map类型
     * @param str json字符串
     * @return map类型
     */
    public static Map<String,String> String2Map(String str){
        Map<String,String> map = new HashMap();
        //将请求头转换为json格式
        JSONArray requestHeaderJsonArr = new JSONArray(str);
        for(int i=0;i<requestHeaderJsonArr.length();i++){
            JSONObject requestHeaderJson =(JSONObject)requestHeaderJsonArr.get(i);
            Iterator iterator = requestHeaderJson.keys();
            while (iterator.hasNext()){
                String key =(String) iterator.next();
                String value =(String) requestHeaderJson.get(key);
                map.put(key,value);
            }
        }
        return map;
    }
}

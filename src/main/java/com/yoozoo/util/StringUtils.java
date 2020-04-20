package com.yoozoo.util;

import org.json.JSONArray;
import org.json.JSONObject;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * 字符串工具类
 * Created by guoxx on 2018/9/10.
 */
public class StringUtils {

    /**
     * 将一个jsonArray对象，转换为json字符串
     * @param array jsonArray对象
     * @return son字符串
     */
    public static String convertJsonStrByJsonArray(JSONArray array){
        JSONObject obj = new JSONObject();
        String str = "";
        if (array != null){
            for(int i=0;i<array.length();i++){
                JSONObject aJson =(JSONObject) array.get(i);
                Iterator<String> it = aJson.keys();
                while (it.hasNext()){
                    String key = it.next();
                    Object value = aJson.get(key);
                    obj.put(key,value);
                }
            }
        }
         str = obj.toString();
        return str;
    }

    /**
     * 将字符串类型的jsonarray对象转换为字符串类型的json对象
     * @param str 字符串类型的jsonarray对象
     * @return 字符串类型的json对象
     */
    public static String convertJsonStrByJsonArrayStr(String str){

        String jsonStr = "";
        JSONObject obj = new JSONObject();
        JSONArray array = new JSONArray(str);
        if (array != null){
            for(int i=0;i<array.length();i++){
                JSONObject aJson =(JSONObject) array.get(i);
                Iterator<String> it = aJson.keys();
                while (it.hasNext()){
                    String key = it.next();
                    Object value = aJson.get(key);
                    obj.put(key,value);
                }
            }
        }
        jsonStr = obj.toString();

        return jsonStr;
    }

    /**
     * 将堆栈信息以string类型打印
     * @param e 错误信息
     * @return string类型打印
     */
    public static String convertStringStackTrace (AssertionError e) {
        // StringWriter将包含堆栈信息
        StringWriter stringWriter = new StringWriter();
        //必须将StringWriter封装成PrintWriter对象，
        //以满足printStackTrace的要求
        PrintWriter printWriter = new PrintWriter(stringWriter);
        //获取堆栈信息
        e.printStackTrace(printWriter);
        //转换成String，并返回该String
        StringBuffer error = stringWriter.getBuffer();
        return error.toString();
    }


    /**
     * 将json格式的String字符串，转换成key=value&key=value格式
     * @param parameterJson 将json格式的String字符串
     * @return 转换成key=value&key=value格式
     */
    public static String convertStringParamter(String parameterJson) {
        StringBuffer parameterBuffer = new StringBuffer();
        if (parameterJson != null) {
            JSONObject requestOriginBodyJson = new JSONObject(parameterJson);
            Iterator iterator = requestOriginBodyJson.keys();
            String key = null;
            String value = null;
            while (iterator.hasNext()) {
                key = (String) iterator.next();
                if (requestOriginBodyJson.get(key) != null) {
                    value = (String) requestOriginBodyJson.get(key);
                } else {
                    value = "";
                }
                parameterBuffer.append(key).append("=").append(value);
                if (iterator.hasNext()) {
                    parameterBuffer.append("&");
                }
            }
        }
        System.out.println(parameterBuffer.toString());
        return parameterBuffer.toString();
    }


    /**
     * 将string类型的日期，转换为时间戳
     * @param dateStr string类型的日期
     * @return 时间戳
     */
    public static Timestamp convertStringToTimestamp(String dateStr) throws ParseException {
        //定义返回对象
        Timestamp time = null;
        //设置日期格式
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        //强制转换
        Date date = sdf.parse(dateStr);
        //设置时间戳
        time = new Timestamp(date.getTime());

        return time;
    }

    /**
     * 将string类型的日期，转换为日期类型
     * @param dateStr string类型的日期
     * @return 日期类型
     */
    public static Date convertStringToDate(String dateStr) throws ParseException {
        //设置日期格式
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        //强制转换
        Date date = sdf.parse(dateStr);

        return date;
    }

    /**
     * 将string类型字符串分割，转换为list
     * @param str string类型字符
     * @return list
     * @throws Exception 错误
     */
    public static List<Integer> convertStringToList(String str)throws Exception{
        List<Integer> list = new ArrayList<>();
        if(!str.equals("")){
            String[] arr = str.trim().split(",");
            for(String astr:arr){
                list.add(Integer.parseInt(astr));
            }
        }
        return list;
    }
}

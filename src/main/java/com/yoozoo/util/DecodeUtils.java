package com.yoozoo.util;

import com.lamfire.json.JSON;
import com.yoozoo.bean.Api;
import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * 解密操作父类
 * Created by guoxx on 2018/8/24.
 */
public class DecodeUtils {
    private static final Map<String, Class<?>> map = new HashMap<String,  Class<?>>();

    static {
        //1--didking
        Map<String, String>  didking =  com.yoozoo.util.didiking2.DecodeMapUtil.getDecodeMap();
        putMap(didking);
        //2--data_collector
        Map<String, String>  data_collector =  com.cn.ceshi.util.data_collector.DecodeMapUtil.getDecodeMap();
        putMap(data_collector);
        //3--sharesdk_collector
        Map<String, String>  sharesdk_collector =  com.cn.ceshi.util.sharesdk.DecodeMapUtil.getDecodeMap();
        putMap(sharesdk_collector);
        //4-bbsdk_collector
        Map<String, String>  bbsdk_collector =  com.cn.ceshi.util.bbssdk.DecodeMapUtilImpl.getDecodeMap();
        putMap(bbsdk_collector);
    }

    private static void  putMap( Map<String, String> kValue){
        for(Map.Entry<String,String> obj:kValue.entrySet()){
            try {
                Class<?> clazz = Class.forName(obj.getValue());
                map.put(obj.getKey(),clazz);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        }
    }

    /**
    //success= 1-响应参数无误  0-响应参数错误
    //resultData=响应明文数据
    //errorMsg=响应参数错误说明
    //passDes -1=不经过解密  0=解密失败  1-解密成功
    {"resultData":Obj,"success":1,"passDes":0}
    {"resultData":Obj,"success":0,"errorMsg":"缺少参数userName","passDes":0}

     */
    public static JSON inDecode(DecodeParams params){
        Class<?> clazz = map.get(params.getOriginHost());
        int passDes = -1;
        JSON json = new JSON();
        if(clazz!=null){
            json.put("passDes",passDes=0);
            try {
                Method method = clazz.getMethod("inDecode",String.class,Map.class,String.class,JSON.class);
                JSON jsonTmp = (JSON) method.invoke(clazz.newInstance(),params.getRequestUrl(),params.getHeaders(),params.getHttpBody(),params.getNeedKey());
                //解密返回null
                if(jsonTmp == null){
                    json.put("resultData",params.getHttpBody());
                    json.put("success",1);
                    json.put("passDes",-2);
                    return json;
                }
                json = jsonTmp;
                json.put("passDes",passDes=1);
                return json;
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        json.put("resultData",params.getHttpBody());
        json.put("success",1);//1-参数无误  0-参数错误
        json.put("passDes",passDes);
        return json;
    }

    public static JSON outDecode(DecodeParams params){
        Class<?> clazz = map.get(params.getOriginHost());
        JSON result = new JSON();
        int passDes = -1;
        if(clazz!=null){
            result.put("passDes",passDes=0);
            try {
                Method method = clazz.getMethod("outDecode",String.class,Map.class,String.class,JSON.class);
                result = (JSON) method.invoke(clazz.newInstance(),params.getRequestUrl(),params.getHeaders(),params.getHttpBody(),params.getNeedKey());
                result.put("passDes",passDes=1);
                return result;
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }

        result.put("resultData",params.getHttpBody());
        result.put("success",1);//1-参数无误  0-参数错误
        result.put("passDes",passDes);
        return result;
    }

    /**
     * 根据接口信息返回解密需要的请求体
     * @param api
     * @return
     */
    public static DecodeParams getDecodeParams(Api api){

        DecodeParams params = new DecodeParams();
        //定义原始host
        String originHost = api.getApiHost();
        //定义请求url
        String requestUrl ="";
        //定义要解密的字符串
        String httpBody = "";
        //定义解密需要的请求参数JSON
        JSON needKey = new JSON();

        if(api.getMethod().equals(CommonParam.GET)){
            String[] urlAndParams = api.getUrl().split("\\?");
            //对传递过来的url再次拆分，设置url
            requestUrl = api.getApiHost()+urlAndParams[0];

            //对传递过来的url再次拆分，设置requestOriginBody
            String requestParams = urlAndParams[1];
            String[] paramsArr = requestParams.split("&");

            for(String param:paramsArr){
                String[] aparam = param.split("=");
                needKey.put(aparam[0],aparam[1]);
            }
        }else {
            requestUrl = api.getApiHost()+api.getUrl();
        }
        //获取请求头
        Map<String ,String> headers = MapUtils.String2Map(api.getRequestHeader());
        if(originHost.equals("api.share.mob.com")){
            if (api.getRequestOriginBody() != null) {
                JSONArray requestOriginBodyArr = new JSONArray(api.getRequestOriginBody());
                for(int i=0;i<requestOriginBodyArr.length();i++) {
                    JSONObject requestOriginBodyJson = (JSONObject) requestOriginBodyArr.get(i);
                    Iterator iterator = requestOriginBodyJson.keys();
                    while (iterator.hasNext()) {
                        String name = iterator.next().toString();
                        String value = requestOriginBodyJson.get(name).toString();
                        headers.put(name,value);
                    }
                }
            }
        }
        //设置解密数据
        if(api.getDecodeType() > 0){
            if(api.getDecodeType() == 1){
                if(api.getRequestOriginBody() != null){
                    httpBody = api.getRequestOriginBody();
                }

            }else {
                if(api.getResponseOriginBody() != null){
                    httpBody = api.getResponseOriginBody();
                }
            }
        }

        //设置返回对象
        params.setOriginHost(originHost);
        params.setRequestUrl(requestUrl);
        params.setHeaders(headers);
        params.setHttpBody(httpBody);
        params.setNeedKey(needKey);

        return params;
    }

}

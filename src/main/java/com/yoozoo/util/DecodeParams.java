package com.yoozoo.util;

import com.lamfire.json.JSON;

import java.util.Map;

/**
 * 因各个SDK对于解密时传递的参数不同，故编写类来保存参数
 * Created by guoxx on 2018/8/24.
 */
public class DecodeParams {
    //设置host
    private String originHost;
    //请求的url；例如：m.data.mob.com/v4/cconf
    private String requestUrl;
    //需要解密的数据，String类型表示的json对象
    private String httpBody;
    //请求头信息
    private Map<String,String> headers;
    //请求体信息
    private JSON needKey;

    public String getOriginHost() {
        return originHost;
    }

    public void setOriginHost(String originHost) {
        this.originHost = originHost;
    }

    public String getRequestUrl() {
        return requestUrl;
    }

    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }

    public String getHttpBody() {
        return httpBody;
    }

    public void setHttpBody(String httpBody) {
        this.httpBody = httpBody;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    public JSON getNeedKey() {
        return needKey;
    }

    public void setNeedKey(JSON needKey) {
        this.needKey = needKey;
    }
}

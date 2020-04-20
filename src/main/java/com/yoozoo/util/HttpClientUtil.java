package com.yoozoo.util;


import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.config.SocketConfig;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.Header;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.http.conn.ssl.TrustStrategy;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import org.apache.http.ssl.SSLContextBuilder;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 模拟请求发送
 * Created by guoxx on 2018/7/25.
 */
public class HttpClientUtil {
    private static final Logger LOGGER = LogManager.getLogger(HttpClientUtil.class.getName());

    private HttpClientUtil() {
    }

    private static PoolingHttpClientConnectionManager poolConnManager;
    private static final int maxTotalPool = 200;
    private static final int maxConPerRoute = 20;
    private static final int socketTimeout = 2000;
    private static final int connectionRequestTimeout = 3000;
    private static final int connectTimeout = 1000;

    static {
        try {
            SSLContextBuilder builder = new SSLContextBuilder();
            // 全部信任 不做身份鉴定
            builder.loadTrustMaterial(null, new TrustStrategy() {
                @Override
                public boolean isTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
                    return true;
                }
            });
            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(builder.build(),
                    new String[]{"SSLv2Hello", "SSLv3", "TLSv1", "TLSv1.2"},
                    null,
                    NoopHostnameVerifier.INSTANCE);


            Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder
                    .<ConnectionSocketFactory>create()
                    .register("http",
                            PlainConnectionSocketFactory.getSocketFactory())
                    .register("https", sslsf).build();
            poolConnManager = new PoolingHttpClientConnectionManager(
                    socketFactoryRegistry);
            // Increase max total connection to 200
            poolConnManager.setMaxTotal(maxTotalPool);
            // Increase default max connection per route to 20
            poolConnManager.setDefaultMaxPerRoute(maxConPerRoute);
            SocketConfig socketConfig = SocketConfig.custom()
                    .setSoTimeout(socketTimeout).build();
            poolConnManager.setDefaultSocketConfig(socketConfig);
        } catch (Exception e) {

        }
    }

    private static CloseableHttpClient getConnection() {
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectionRequestTimeout(connectionRequestTimeout)
                .setConnectTimeout(connectTimeout)
                .setSocketTimeout(socketTimeout).build();
        CloseableHttpClient httpClient = HttpClients.custom()
                .setConnectionManager(poolConnManager)
                .setDefaultRequestConfig(requestConfig).build();
        return httpClient;
    }

    public static JSONObject get(String httpGetUrl, String httpHeader) {

        JSONObject result = new JSONObject();
        //设置url
        HttpGet httpGet = new HttpGet(httpGetUrl);

        //设置请求头
        JSONObject headerJson = new JSONObject(httpHeader);
        Iterator sIterator = headerJson.keys();
        while(sIterator.hasNext()){
            // 获得key
            String key = (String)sIterator.next();
            // 根据key获得value, value也可以是JSONObject,JSONArray,使用对应的参数接收即可
            String value = headerJson.getString(key);
            httpGet.setHeader(key, value);
        }

        int status = -111;
        try {
            CloseableHttpResponse response = getConnection().execute(httpGet);
            //获取响应头
            Header[] allHeaders = response.getAllHeaders();
            JSONObject responseHeaderJson = new JSONObject();
            for(Header header:allHeaders){
                responseHeaderJson.put(header.getName(),header.getValue());
            }

            String responseHeader = responseHeaderJson.toString();

            result.put("responseHeader",responseHeader);
            //获取状态码
            status = response.getStatusLine().getStatusCode();
            result.put("status", status);
            //获取响应体
            HttpEntity backEntity = response.getEntity();
            //获取responseBody
            InputStream resStream = backEntity.getContent();
            BufferedReader br = new BufferedReader(new InputStreamReader(resStream));
            StringBuffer resBuffer = new StringBuffer();
            String resTemp = "";
            while ((resTemp = br.readLine()) != null) {
                resBuffer.append(resTemp);
            }
            result.put("responseOriginBody", resBuffer.toString());

        } catch (Exception e) {
            e.printStackTrace();
            //记日志3
            LOGGER.error("[url]=" + httpGetUrl);
            httpGet.abort();
        }
        result.put("status", status);
        return result;
    }

    public static JSONObject post(String postRequsetBody,String httpHeader,String httpAndUrl) {

        JSONObject result = new JSONObject();
        //设置httpPost的url
        HttpPost httpPost = new HttpPost(httpAndUrl);

        //设置请求头
        JSONObject headerJson = new JSONObject(httpHeader);
        Iterator sIterator = headerJson.keys();
        while(sIterator.hasNext()){
            // 获得key
            String key = (String)sIterator.next();
            // 根据key获得value, value也可以是JSONObject,JSONArray,使用对应的参数接收即可
            String value = headerJson.getString(key);
            httpPost.setHeader(key, value);
        }

        //设置请求体
        StringEntity se = new StringEntity(postRequsetBody, "UTF-8");
        httpPost.setEntity(se);

        int status = -111;
        try {
            CloseableHttpResponse response = getConnection().execute(httpPost);
            //获取响应头
            Header[] allHeaders = response.getAllHeaders();
            JSONObject responseHeaderJson = new JSONObject();
            for(Header header:allHeaders){
                responseHeaderJson.put(header.getName(),header.getValue());
            }
//            for(int i=0;i<allHeaders.length;i++){
//                JSONObject aJson =(JSONObject)allHeaders[i];
//                Iterator iterator = aJson.keys();
//                while(iterator.hasNext()){
//                    // 获得key
//                    String key = (String)iterator.next();
//                    // 根据key获得value, value也可以是JSONObject,JSONArray,使用对应的参数接收即可
//                    String value =(String) aJson.get(key);
//                    responseHeaderJson.put(key, value);
//                }
//
//            }
            String responseHeader = responseHeaderJson.toString();
            //设置响应头
            result.put("responseHeader",responseHeader);

            //获取状态码
            status = response.getStatusLine().getStatusCode();
            result.put("status", status);

            //设置响应体
            HttpEntity backEntity = response.getEntity();
            InputStream resStream = backEntity.getContent();
            BufferedReader br = new BufferedReader(new InputStreamReader(resStream));
            StringBuffer resBuffer = new StringBuffer();
            String resTemp = "";
            while ((resTemp = br.readLine()) != null) {
                resBuffer.append(resTemp);
            }
            result.put("responseOriginBody", resBuffer.toString());

        } catch (Exception e) {
            e.printStackTrace();
            //记日志3
            LOGGER.error("[url]=" + httpAndUrl);
            httpPost.abort();
        }
        return result;
    }
}

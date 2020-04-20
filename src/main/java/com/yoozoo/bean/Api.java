package com.yoozoo.bean;

import java.sql.Timestamp;
import java.util.List;

/**
 * 接口相关类
 * Created by guoxx on 2018/7/23.
 */
public class Api {

    //接口id
    private Integer apiId;
    //所属项目id
    private Integer itemId;
    //所属项目名称
    private String itemName;
    //接口名称
    private String apiName;
    //接口描述
    private String apiDesc;
    //请求方式
    private Integer method;
    //Host
    private String apiHost;
    //ip地址
    private String ipAddr;
    //端口号
    private String apiPort;
    //访问地址
    private String url;
    //创建者
    private Integer creator;
    //创建者名称
    private String creatorName;
    //请求方式：1：form-data  2:json
    private Integer paramType;
    //状态吗
    private String statusCode;
    //请求头
    private String requestHeader;
    //请求体原始
    private String requestOriginBody;
    //请求体已解密
    private String requestBody;
    //响应头
    private String responseHeader;
    //响应体原始
    private String responseOriginBody;
    //响应体已解密
    private String responseBody;
    //请求发起时间
    private Timestamp requestTime;
    //响应时间
    private Timestamp responseTime;
    //接口是否删除
    private Integer isDelete;
    //接口创建时间
    private Timestamp createTime;
    //接口修改时间
    private Timestamp updateTime;
    //接口运行结果
    private Integer runResult;
    //是否解密
    private int decodeType;
    //检查点列表
    private List<Point> points;

    public Integer getApiId() {
        return apiId;
    }

    public void setApiId(Integer apiId) {
        this.apiId = apiId;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getApiName() {
        return apiName;
    }

    public void setApiName(String apiName) {
        this.apiName = apiName;
    }

    public String getApiDesc() {
        return apiDesc;
    }

    public void setApiDesc(String apiDesc) {
        this.apiDesc = apiDesc;
    }

    public Integer getMethod() {
        return method;
    }

    public void setMethod(Integer method) {
        this.method = method;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getIpAddr() {
        return ipAddr;
    }

    public void setIpAddr(String ipAddr) {
        this.ipAddr = ipAddr;
    }

    public String getApiHost() {
        return apiHost;
    }

    public void setApiHost(String apiHost) {
        this.apiHost = apiHost;
    }

    public String getApiPort() {
        return apiPort;
    }

    public void setApiPort(String apiPort) {
        this.apiPort = apiPort;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getCreator() {
        return creator;
    }

    public void setCreator(Integer creator) {
        this.creator = creator;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getParamType() {
        return paramType;
    }
    public void setParamType(Integer paramType) {
        this.paramType = paramType;
    }

    public Timestamp getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(Timestamp requestTime) {
        this.requestTime = requestTime;
    }

    public Timestamp getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(Timestamp responseTime) {
        this.responseTime = responseTime;
    }

    public String getRequestHeader() {
        return requestHeader;
    }

    public void setRequestHeader(String requestHeader) {
        this.requestHeader = requestHeader;
    }

    public String getRequestOriginBody() {
        return requestOriginBody;
    }

    public void setRequestOriginBody(String requestOriginBody) {
        this.requestOriginBody = requestOriginBody;
    }

    public String getRequestBody() {
        return requestBody;
    }

    public void setRequestBody(String requestBody) {
        this.requestBody = requestBody;
    }

    public String getResponseHeader() {
        return responseHeader;
    }

    public void setResponseHeader(String responseHeader) {
        this.responseHeader = responseHeader;
    }

    public String getResponseOriginBody() {
        return responseOriginBody;
    }

    public void setResponseOriginBody(String responseOriginBody) {
        this.responseOriginBody = responseOriginBody;
    }

    public String getResponseBody() {
        return responseBody;
    }

    public void setResponseBody(String responseBody) {
        this.responseBody = responseBody;
    }

    public Integer getRunResult() {
        return runResult;
    }

    public void setRunResult(Integer runResult) {
        this.runResult = runResult;
    }
    public int getDecodeType() {
        return decodeType;
    }

    public void setDecodeType(int decodeType) {
        this.decodeType = decodeType;
    }

    public List<Point> getPoints() {
        return points;
    }

    public void setPoints(List<Point> points) {
        this.points = points;
    }
}

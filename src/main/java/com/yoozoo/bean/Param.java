package com.yoozoo.bean;

import java.io.File;
import java.sql.Timestamp;

/**
 * 参数对应javabean
 * Created by guoxx on 2018/9/5.
 */
public class Param {
    //参数id
    private Integer paramId;
    //接口id
    private Integer apiId;
    //参数名
    private String paramName;
    //参数描述
    private String paramDesc;
    //参数类型
    private Integer paramType;
    //条件
    private String paramCondition;
    //参数值
    private String paramValue;
    //是否全局参数
    private Integer isGlobal;
    //是否删除
    private Integer isDelete;
    //创建时间
    private Timestamp createTime;
    //修改时间
    private Timestamp updateTime;
    //参数列表-通过key-value方式
    private String paramValues;
    //获取参数的SQL语句
    private String paramSqlStr;
    //获取参数依赖的接口ID
    private Integer apiKey;
    //获取参数依赖的接口响应字段
    private String apiValue;
    //获取参数对应的文件
    private File paramFile;

    public String getParamCondition() {
        return paramCondition;
    }

    public void setParamCondition(String paramCondition) {
        this.paramCondition = paramCondition;
    }

    public String getParamValues() {
        return paramValues;
    }

    public void setParamValues(String paramValues) {
        this.paramValues = paramValues;
    }

    public String getParamSqlStr() {
        return paramSqlStr;
    }

    public void setParamSqlStr(String paramSqlStr) {
        this.paramSqlStr = paramSqlStr;
    }

    public Integer getApiKey() {
        return apiKey;
    }

    public void setApiKey(Integer apiKey) {
        this.apiKey = apiKey;
    }

    public String getApiValue() {
        return apiValue;
    }

    public void setApiValue(String apiValue) {
        this.apiValue = apiValue;
    }

    public File getParamFile() {
        return paramFile;
    }

    public void setParamFile(File paramFile) {
        this.paramFile = paramFile;
    }

    public Integer getParamId() {
        return paramId;
    }

    public void setParamId(Integer paramId) {
        this.paramId = paramId;
    }

    public Integer getApiId() {
        return apiId;
    }

    public void setApiId(Integer apiId) {
        this.apiId = apiId;
    }

    public String getParamName() {
        return paramName;
    }

    public void setParamName(String paramName) {
        this.paramName = paramName;
    }

    public String getParamDesc() {
        return paramDesc;
    }

    public void setParamDesc(String paramDesc) {
        this.paramDesc = paramDesc;
    }

    public Integer getParamType() {
        return paramType;
    }

    public void setParamType(Integer paramType) {
        this.paramType = paramType;
    }

    public String getParamValue() {
        return paramValue;
    }

    public void setParamValue(String paramValue) {
        this.paramValue = paramValue;
    }

    public Integer getIsGlobal() {
        return isGlobal;
    }

    public void setIsGlobal(Integer isGlobal) {
        this.isGlobal = isGlobal;
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
}

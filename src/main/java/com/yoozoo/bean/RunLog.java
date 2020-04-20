package com.yoozoo.bean;

import java.sql.Timestamp;

/**
 * 接口执行结果
 * Created by guoxx on 2018/10/18.
 */
public class RunLog {

    //id
    private Integer runId;
    //接口ID
    private Integer apiId;
    //运行结果
    private Integer runResult;
    //失败原因
    private String reason;
    //检查点类型
    private Integer checkType;
    //执行时间
    private Timestamp createTime;
    //运行版本
    private Integer version;

    public Integer getApiId() {
        return apiId;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public void setApiId(Integer apiId) {
        this.apiId = apiId;
    }

    public Integer getRunId() {
        return runId;
    }

    public void setRunId(Integer runId) {
        this.runId = runId;
    }

    public Integer getRunResult() {
        return runResult;
    }

    public void setRunResult(Integer runResult) {
        this.runResult = runResult;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Integer getCheckType() {
        return checkType;
    }

    public void setCheckType(Integer checkType) {
        this.checkType = checkType;
    }
}

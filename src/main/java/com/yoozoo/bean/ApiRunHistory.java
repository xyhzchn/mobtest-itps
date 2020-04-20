package com.yoozoo.bean;

import java.sql.Timestamp;

/**
 * 接口运行历史
 * Created by guoxx on 2018/10/18.
 */
public class ApiRunHistory {
    //id
    private Integer id;
    //接口id
    private Integer apiId;
    //版本号
    private Integer version;
    //运行结果
    private Integer runResult;
    //执行者
    private Integer runUser;
    //创建时间
    private Timestamp createTime;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getApiId() {
        return apiId;
    }

    public void setApiId(Integer apiId) {
        this.apiId = apiId;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Integer getRunResult() {
        return runResult;
    }

    public void setRunResult(Integer runResult) {
        this.runResult = runResult;
    }
    public Integer getRunUser() {
        return runUser;
    }

    public void setRunUser(Integer runUser) {
        this.runUser = runUser;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }
}

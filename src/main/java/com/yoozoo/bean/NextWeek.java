package com.yoozoo.bean;

import java.sql.Timestamp;
import java.util.Date;

/**
 * 下周计划
 * Created by guoxx on 2018/12/19.
 */
public class NextWeek {

    //下周工作计划记录id
    private Integer nextWeekId;
    //id
    private Integer id;
    //工作类型
    private Integer workType;
    //jiraid
    private String jiraId;
    //项目名称
    private String projectName;
    //内容
    private String content;
    //预计开始时间
    private Date yujiTestDate;
    //预计结束时间
    private Date yujiDoneDate;
    //其他描述信息
    private String otherDesc;
    //是否删除
    private Integer isDelete;
    //创建时间
    private Timestamp createTime;
    //修改时间
    private Timestamp updateTime;
    //用户id
    private Integer userId;
    //用户名称
    private String userName;

    public Integer getNextWeekId() {
        return nextWeekId;
    }

    public void setNextWeekId(Integer nextWeekId) {
        this.nextWeekId = nextWeekId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getWorkType() {
        return workType;
    }

    public void setWorkType(Integer workType) {
        this.workType = workType;
    }

    public String getJiraId() {
        return jiraId;
    }

    public void setJiraId(String jiraId) {
        this.jiraId = jiraId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getYujiTestDate() {
        return yujiTestDate;
    }

    public void setYujiTestDate(Date yujiTestDate) {
        this.yujiTestDate = yujiTestDate;
    }

    public Date getYujiDoneDate() {
        return yujiDoneDate;
    }

    public void setYujiDoneDate(Date yujiDoneDate) {
        this.yujiDoneDate = yujiDoneDate;
    }

    public String getOtherDesc() {
        return otherDesc;
    }

    public void setOtherDesc(String otherDesc) {
        this.otherDesc = otherDesc;
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

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}

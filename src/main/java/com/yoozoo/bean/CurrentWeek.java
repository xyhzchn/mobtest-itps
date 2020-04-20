package com.yoozoo.bean;

import java.sql.Timestamp;

/**
 * 周报记录关于项目部分
 * Created by guoxx on 2018/11/30.
 */
public class CurrentWeek {

    //本周记录id
    private Integer detailId;
    //id
    private Integer id;
    //jiraid
    private String jiraId;
    //工作事项
    private Integer workType;
    //项目组
    private String projectName;
    //内容
    private String content;
    //(状态)/进度
    private Float progress;
    //是否上线
    private Integer isOnline;
    //是否回测
    private Integer isRegressionTest;
    //预计测试周期-D
    private Float estimatedTime;
    //实际累计耗时-D
    private Float realTime;
    //备注
    private String otherDesc;
    //是否删除
    private Integer isDelete;
    //创建时间
    private Timestamp createTime;
    //更新时间
    private Timestamp updateTime;

    //用户id
    private Integer userId;
    //用户名称
    private String userName;
    //周的总预计时间
    private Float totalEstimatedTime;
    //周的总时间消耗时间
    private Float totalRealTime;


    public Integer getDetailId() {
        return detailId;
    }

    public void setDetailId(Integer detailId) {
        this.detailId = detailId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getJiraId() {
        return jiraId;
    }

    public void setJiraId(String jiraId) {
        this.jiraId = jiraId;
    }

    public Integer getWorkType() {
        return workType;
    }

    public void setWorkType(Integer workType) {
        this.workType = workType;
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

    public Float getProgress() {
        return progress;
    }

    public void setProgress(Float progress) {
        this.progress = progress;
    }

    public Integer getIsOnline() {
        return isOnline;
    }

    public void setIsOnline(Integer isOnline) {
        this.isOnline = isOnline;
    }

    public Integer getIsRegressionTest() {
        return isRegressionTest;
    }

    public void setIsRegressionTest(Integer isRegressionTest) {
        this.isRegressionTest = isRegressionTest;
    }

    public Float getEstimatedTime() {
        return estimatedTime;
    }

    public void setEstimatedTime(Float estimatedTime) {
        this.estimatedTime = estimatedTime;
    }

    public Float getRealTime() {
        return realTime;
    }

    public void setRealTime(Float realTime) {
        this.realTime = realTime;
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

    public Float getTotalEstimatedTime() {
        return totalEstimatedTime;
    }

    public void setTotalEstimatedTime(Float totalEstimatedTime) {
        this.totalEstimatedTime = totalEstimatedTime;
    }

    public Float getTotalRealTime() {
        return totalRealTime;
    }

    public void setTotalRealTime(Float totalRealTime) {
        this.totalRealTime = totalRealTime;
    }
}

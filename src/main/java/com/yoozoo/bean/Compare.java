package com.yoozoo.bean;
/**
 * 对比数据
 * Created by guoxx on 2019/1/2.
 */
public class Compare {
    //对比周
    private String compareWeek;
    //对比月
    private Integer compareMonth;
    //对比人id
    private Integer userId;
    //对比人名称
    private String userName;
    //总预计耗费时间
    private Float totalEstimatedTime;
    //总实际耗费时间
    private Float totalRealTime;
    //总用例数
    private Integer totalCase;
    //总bug数
    private Integer totalBug;

    public String getCompareWeek() {
        return compareWeek;
    }

    public void setCompareWeek(String compareWeek) {
        this.compareWeek = compareWeek;
    }

    public Integer getCompareMonth() {
        return compareMonth;
    }

    public void setCompareMonth(Integer compareMonth) {
        this.compareMonth = compareMonth;
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

    public Integer getTotalCase() {
        return totalCase;
    }

    public void setTotalCase(Integer totalCase) {
        this.totalCase = totalCase;
    }

    public Integer getTotalBug() {
        return totalBug;
    }

    public void setTotalBug(Integer totalBug) {
        this.totalBug = totalBug;
    }
}

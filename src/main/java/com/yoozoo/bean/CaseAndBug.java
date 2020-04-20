package com.yoozoo.bean;

import java.sql.Timestamp;
import java.util.List;

/**
 * 周报中包含的用例和bug数
 * Created by guoxx on 2018/12/4.
 */
public class CaseAndBug {

    //bug和用例id
    private Integer cabId;
    //id
    private Integer id;
    //预留问题
    private String remaining;
    //建议
    private String suggest;
    //新增用例数-p1
    private Integer case_P1;
    //新增用例数-p2
    private Integer case_P2;
    //新增用例数-p3
    private Integer case_P3;
    //新增用例数-p4
    private Integer case_P4;
    //新增bug数-p1
    private Integer bug_P1;
    //新增bug数-p2
    private Integer bug_P2;
    //新增bug数-p3
    private Integer bug_P3;
    //新增bug数-p4
    private Integer bug_P4;
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
    //周的总新增用例数量
    private Integer totalCase;
    //周的总新增bug数量
    private Integer totalBug;


    public Integer getCabId() {
        return cabId;
    }

    public void setCabId(Integer cabId) {
        this.cabId = cabId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRemaining() {
        return remaining;
    }

    public void setRemaining(String remaining) {
        this.remaining = remaining;
    }

    public String getSuggest() {
        return suggest;
    }

    public void setSuggest(String suggest) {
        this.suggest = suggest;
    }

    public Integer getCase_P1() {
        return case_P1;
    }

    public void setCase_P1(Integer case_P1) {
        this.case_P1 = case_P1;
    }

    public Integer getCase_P2() {
        return case_P2;
    }

    public void setCase_P2(Integer case_P2) {
        this.case_P2 = case_P2;
    }

    public Integer getCase_P3() {
        return case_P3;
    }

    public void setCase_P3(Integer case_P3) {
        this.case_P3 = case_P3;
    }

    public Integer getCase_P4() {
        return case_P4;
    }

    public void setCase_P4(Integer case_P4) {
        this.case_P4 = case_P4;
    }

    public Integer getBug_P1() {
        return bug_P1;
    }

    public void setBug_P1(Integer bug_P1) {
        this.bug_P1 = bug_P1;
    }

    public Integer getBug_P2() {
        return bug_P2;
    }

    public void setBug_P2(Integer bug_P2) {
        this.bug_P2 = bug_P2;
    }

    public Integer getBug_P3() {
        return bug_P3;
    }

    public void setBug_P3(Integer bug_P3) {
        this.bug_P3 = bug_P3;
    }

    public Integer getBug_P4() {
        return bug_P4;
    }

    public void setBug_P4(Integer bug_P4) {
        this.bug_P4 = bug_P4;
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

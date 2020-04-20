package com.yoozoo.bean;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * 周报信息
 * Created by guoxx on 2018/11/30.
 */
public class Week {

    //周报记录id
    private Integer weekId;
    //周报周期开始之间，即统计时间-5天
    private Date startDate;
    //周报周期结束时间，提交日期
    private Date endDate;
    //关联表的id
    private Integer id;
    //汇报者id
    private Integer userId;
    //汇报者真实姓名
    private String userName;
    //是否删除
    private Integer isDelete;
    //创建时间
    private Timestamp createTime;
    //更新时间
    private Timestamp updateTime;

    //周报信息
    private List<CurrentWeek> currentWeekList;
    //周报中用例和bug信息
    private List<CaseAndBug> caseAndBugList;
    //周报中用例和bug信息
    private CaseAndBug caseAndBug;
    //下周计划记录
    private List<NextWeek> nextWeekList;
    //周统计信息
    private CurrentWeek currentWeek;


    //查询的周报id
    private Integer selectWeek;
    //查询的月份
    private Integer selectMonth;
    //查询的用户id
    private Integer selectUser;
    //关联表的id列表
    private List<Integer> weekUserIds;
    //对比开始周
    private Integer selectWeekFrom;
    //对比结束周
    private Integer selectWeekTo;
    //对比开始月
    private Integer selectMonthFrom;
    //对比结束月
    private Integer selectMonthTo;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getWeekId() {
        return weekId;
    }

    public void setWeekId(Integer weekId) {
        this.weekId = weekId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
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

    public List<CurrentWeek> getCurrentWeekList() {
        return currentWeekList;
    }

    public void setCurrentWeekList(List<CurrentWeek> currentWeekList) {
        this.currentWeekList = currentWeekList;
    }

    public List<CaseAndBug> getCaseAndBugList() {
        return caseAndBugList;
    }

    public void setCaseAndBugList(List<CaseAndBug> caseAndBugList) {
        this.caseAndBugList = caseAndBugList;
    }

    public CaseAndBug getCaseAndBug() {
        return caseAndBug;
    }

    public void setCaseAndBug(CaseAndBug caseAndBug) {
        this.caseAndBug = caseAndBug;
    }

    public List<NextWeek> getNextWeekList() {
        return nextWeekList;
    }

    public void setNextWeekList(List<NextWeek> nextWeekList) {
        this.nextWeekList = nextWeekList;
    }

    public Integer getSelectWeek() {
        return selectWeek;
    }

    public void setSelectWeek(Integer selectWeek) {
        this.selectWeek = selectWeek;
    }

    public Integer getSelectMonth() {
        return selectMonth;
    }

    public void setSelectMonth(Integer selectMonth) {
        this.selectMonth = selectMonth;
    }

    public Integer getSelectUser() {
        return selectUser;
    }

    public void setSelectUser(Integer selectUser) {
        this.selectUser = selectUser;
    }

    public List<Integer> getWeekUserIds() {
        return weekUserIds;
    }

    public void setWeekUserIds(List<Integer> weekUserIds) {
        this.weekUserIds = weekUserIds;
    }

    public Integer getSelectWeekFrom() {
        return selectWeekFrom;
    }

    public void setSelectWeekFrom(Integer selectWeekFrom) {
        this.selectWeekFrom = selectWeekFrom;
    }

    public Integer getSelectWeekTo() {
        return selectWeekTo;
    }

    public void setSelectWeekTo(Integer selectWeekTo) {
        this.selectWeekTo = selectWeekTo;
    }

    public Integer getSelectMonthFrom() {
        return selectMonthFrom;
    }

    public void setSelectMonthFrom(Integer selectMonthFrom) {
        this.selectMonthFrom = selectMonthFrom;
    }

    public Integer getSelectMonthTo() {
        return selectMonthTo;
    }

    public void setSelectMonthTo(Integer selectMonthTo) {
        this.selectMonthTo = selectMonthTo;
    }

    public CurrentWeek getCurrentWeek() {
        return currentWeek;
    }

    public void setCurrentWeek(CurrentWeek currentWeek) {
        this.currentWeek = currentWeek;
    }
}

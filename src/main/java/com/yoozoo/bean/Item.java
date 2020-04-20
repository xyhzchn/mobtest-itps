package com.yoozoo.bean;

import java.sql.Timestamp;

/**
 * 项目信息
 * Created by guoxx on 2018/7/13.
 */
public class Item {

    //项目id
    private Integer itemId;
    //用户id
    private Integer userId;
    //用户名-项目创建者
    private String userName;
    //项目名称
    private String itemName;
    //项目描述
    private String itemDesc;
    //是否删除
    private Integer isDelete;
    //创建时间
    private Timestamp createTime;
    //修改时间
    private Timestamp updateTime;


    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemDesc() {
        return itemDesc;
    }

    public void setItemDesc(String itemDesc) {
        this.itemDesc = itemDesc;
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

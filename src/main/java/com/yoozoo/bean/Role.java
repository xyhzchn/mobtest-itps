package com.yoozoo.bean;

import java.sql.Timestamp;

/**
 * 角色
 * Created by guoxx on 2018/6/22.
 */
public class Role {

    //角色ID
    private Integer roleId;
    //角色名
    private String roleName;
    //角色描述
    private String roleDesc;
    //角色是否删除
    private Integer isDelete;
    //创建时间
    private Timestamp createTime;
    //修改时间
    private Timestamp updateTime;


    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleDesc() {
        return roleDesc;
    }

    public void setRoleDesc(String roleDesc) {
        this.roleDesc = roleDesc;
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

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }
}

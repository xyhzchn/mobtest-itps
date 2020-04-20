package com.yoozoo.service;

import com.yoozoo.bean.Role;

import java.util.List;
import java.util.Map;

/**
 * 角色接口
 * Created by guoxx on 2018/6/27.
 */
public interface RoleService {

    /**
     * 添加角色
     * @param role 添加的角色信息
     * @return 影响条数
     * @throws Exception 抛出错误
     */
     int addRole(Role role)throws Exception;

    /**
     * 获取角色列表
     * @param role 查询条件
     * @return 角色列表
     * @throws Exception 错误
     */
     List<Role> getRoles(Role role)throws Exception;

    /**
     * 根据角色id查询角色信息
     * @param id 角色id
     * @return 角色信息
     * @throws Exception 错误
     */
     Role getRoleDetail(Integer id)throws Exception;

    /**
     * 修改角色信息
     * @param role 角色
     * @return 影响数据条数
     * @throws Exception 错误
     */
    int editRole(Role role)throws Exception;

    /**
     * 删除角色
     * @param role 角色
     * @return 影响数据条数
     * @throws Exception 错误
     */
    int deleteRole(Role role) throws Exception;

    /**
     * 获取所有的角色列表，通过map的方式
     * @return 角色列表map
     * @throws Exception 错误信息
     */
    Map<Integer,Role> getRoleByMap()throws Exception;
}

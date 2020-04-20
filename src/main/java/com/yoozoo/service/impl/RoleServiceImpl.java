package com.yoozoo.service.impl;

import com.yoozoo.bean.Role;
import com.yoozoo.mapper.RoleMapper;
import com.yoozoo.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
 * 角色接口实现类
 * Created by guoxx on 2018/6/27.
 */
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    /**
     * 添加角色
     * @param role 添加的角色信息
     * @return 添加操作影响条数
     * @throws Exception 抛出发生的错误
     */
    @Override
    public int addRole(Role role) throws Exception {
        return roleMapper.insertOneRole(role);
    }

    /**
     * 获取角色列表
     * @param role 查询条件
     * @return 角色列表
     * @throws Exception 错误
     */
    public List<Role> getRoles(Role role)throws Exception{
        return roleMapper.selectAllRoles(role);
    }

    /**
     * 根据角色id查询角色信息
     * @param id 角色id
     * @return 角色信息
     * @throws Exception 错误
     */
    public Role getRoleDetail(Integer id)throws Exception{
        return roleMapper.selectRoleById(id);
    }

    /**
     * 修改角色信息
     * @param role 角色
     * @return 影响数据条数
     * @throws Exception 错误
     */
    public int editRole(Role role)throws Exception{
        return roleMapper.updateOneRole(role);
    }

    /**
     * 删除角色
     * @param role 角色
     * @return 影响数据条数
     * @throws Exception 错误
     */
    public int deleteRole(Role role) throws Exception{
        int num = 0;
        int updateNum = roleMapper.deleteRoleById(role);
        if (updateNum > 0){
            int middleNum = roleMapper.deleteMiddleRole(role);
            if(middleNum > 0){
                num = roleMapper.deleteUsers(role);
            }

        }

        return num;
    }

    /**
     * 获取所有的角色列表，通过map的方式
     * @return 角色列表map
     * @throws Exception 错误信息
     */
    public  Map<Integer,Role> getRoleByMap()throws Exception{
        return roleMapper.selectRoleIdAndName();
    }
}

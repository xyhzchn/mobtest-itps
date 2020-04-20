package com.yoozoo.mapper;

import com.yoozoo.bean.Role;
import org.apache.ibatis.annotations.MapKey;

import java.util.List;
import java.util.Map;

/**
 * 角色管理mapper
 * Created by guoxx on 2018/6/27.
 */
public interface RoleMapper {

    /**
     * 添加一条角色信息
     * @param role 角色信息
     * @return 影响数据条数
     * @throws Exception 错误
     */
    int insertOneRole(Role role)throws Exception;

    /**
     * 查询所有的角色列表
     * @return 角色列表
     * @throws Exception 错误
     */
    List<Role> selectAllRoles(Role role)throws Exception;

    /**
     * 根据主键查询角色信息
     * @param id 主键：角色id
     * @return 角色信息
     * @throws Exception 错误
     */
    Role selectRoleById(Integer id)throws Exception;

    /**
     * 修改单个角色信息
     * @param role 角色信息
     * @return 影响数据条数
     * @throws Exception 错误
     */
    int updateOneRole(Role role)throws Exception;

    /**
     * 删除指定的角色
     * @param role 角色
     * @return 影响数据条数
     * @throws Exception 错误
     */
    int deleteRoleById(Role role) throws Exception;

    /**
     * 删除角色所管理的中间表字段
     * @param role 角色对象
     * @return 影响数据条数
     * @throws Exception 错误信息
     */
    int deleteMiddleRole(Role role)throws Exception;

    /**
     * 删除角色下的所有用户
     * @param role 角色对象
     * @return 影响数据条数
     * @throws Exception 错误
     */
    int deleteUsers(Role role)throws Exception;

    /**
     * 获取所有的角色列表
     * @return 角色列表
     * @throws Exception 错误信息
     */
    @MapKey("roleId")
    Map<Integer,Role> selectRoleIdAndName()throws Exception;
}

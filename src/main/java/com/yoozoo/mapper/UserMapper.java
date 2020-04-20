package com.yoozoo.mapper;

import com.yoozoo.bean.User;

import java.util.List;

/**
 * 用户相关Dao接口
 * Created by guoxx on 2018/6/21.
 */

public interface UserMapper {

    /**
     * 根据用户名获取一个User对象
     */
    User getUserByName(User username);

    /**
     * 获取所有的用户列表
     * @param user 用户信息，查询条件
     * @return 用户信息
     * @throws Exception 错误信息
     */
    List<User> selectAllUsers(User user)throws Exception;

    /**
     * 添加一条用户信息，不包含关联表数据
     * @param user 用户
     * @return 影响数据条数
     * @throws Exception 错误
     */
    int insertOneUser(User user)throws Exception;

    /**
     * 添加用户角色关联表数据
     * @param user 参数
     * @return 影响数据条数
     * @throws Exception 错误
     */
    int addUserRole(User user)throws Exception;

    /**
     * 根据用户id查询用户信息
     * @param id 用户id
     * @return 用户
     * @throws Exception 错误信息
     */
    User selectUserById(Integer id)throws Exception;

    /**
     * 根据id修改用户
     * @param user 用户信息
     * @return 影响数据条数
     * @throws Exception 抛出错误
     */
    int updateUserById(User user)throws Exception;


    /**
     * 根据id修改用户关联角色
     * @param user 用户信息
     * @return 影响数据条数
     * @throws Exception 错误
     */
    int updateUserRole(User user)throws Exception;

    /**
     * 设置用户为禁用
     * @param user 用户信息
     * @return 影响数据条数
     * @throws Exception 错误
     */
    int setUserDelete(User user)throws Exception;

    /**
     * 设置关联表中的用户为已删除
     * @param user 用户信息
     * @return 影响数据条数
     * @throws Exception 错误信息
     */
    int setUserRoleDelete(User user)throws Exception;

    /**
     * 设置用户为启用
     * @param user 用户信息
     * @return 影响数据条数
     * @throws Exception 错误
     */
    int setUserNotDelete(User user)throws Exception;

    /**
     * 设置关联表中的用户为未删除
     * @param user 用户信息
     * @return 影响数据条数
     * @throws Exception 错误信息
     */
    int setUserRoleNotDelete(User user)throws Exception;

    /**
     * 修改用户密码
     * @param user 用户信息
     * @return 影响数据条数
     * @throws Exception 错误
     */
    int updateUserPswd(User user)throws Exception;
}

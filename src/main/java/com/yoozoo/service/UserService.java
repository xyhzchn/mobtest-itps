package com.yoozoo.service;

import com.yoozoo.bean.User;

import java.util.List;

/**
 * 用户相关Service接口
 * Created by guoxx on 2018/6/21.
 */
public interface UserService {
    /**
     * 根据用户名获取一个User对象
     */
     User getUserByName(User user)throws Exception;

    /**
     * 获取所有的用户列表
     * @param user 查询条件
     * @return 用户列表
     * @throws Exception 错误信息
     */
     List<User> getAllUsers(User user)throws Exception;

    /**
     * 添加一条用户，包含关联表的添加
     * @param user 用户
     * @return 影响数据条数
     * @throws Exception 错误
     */
     int addOneUser(User user)throws Exception;

    /**
     * 根据用户id查询用户信息
     * @param id 用户id
     * @return 用户
     * @throws Exception 错误信息
     */
    User getOneUser(Integer id)throws Exception;

    /**
     * 修改用户信息
     * @param user 用户信息
     * @return 影响数据条数
     * @throws Exception 抛出错误
     */
    int editOneUser(User user)throws Exception;

    /**
     * 设置用户为禁用
     * @param user 用户信息
     * @return 影响数据条数
     * @throws Exception 错误
     */
    int setUserDisable(User user)throws Exception;

    /**
     * 设置用户为启用
     * @param user 用户信息
     * @return 影响数据条数
     * @throws Exception 错误
     */
    int setUserEnable(User user)throws Exception;

    /**
     * 修改用户密码
     * @param user 用户信息
     * @return 影响数据条数
     * @throws Exception 错误
     */
    int resetUserPswd(User user)throws Exception;


}

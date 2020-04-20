package com.yoozoo.service.impl;

import com.yoozoo.bean.User;
import com.yoozoo.mapper.UserMapper;
import com.yoozoo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户相关service接口实现类
 * Created by guoxx on 2018/6/21.
 */
public class UserServiceImpl implements UserService {

    //定义dao接口
    @Autowired
    private UserMapper userMapper;

    /**
     * 根据用户名获取某一用户
     */
    @Override
    public User getUserByName(User user)throws Exception{
        return userMapper.getUserByName(user);

    }

    /**
     * 获取所有的用户列表
     * @param user 查询条件
     * @return 用户列表
     * @throws Exception 错误信息
     */
    public List<User> getAllUsers(User user)throws Exception{
        return userMapper.selectAllUsers(user);
    }

    /**
     * 添加一条用户，包含关联表的添加
     * @param user 用户
     * @return 影响数据条数
     * @throws Exception 错误
     */
    public int addOneUser(User user)throws Exception{
        int num = 0;
        int addnum = userMapper.insertOneUser(user);
        if(addnum > 0){
            if(user.getUserId() > 0 ){
                num = userMapper.addUserRole(user);
            }
        }
        return num;
    }


    /**
     * 根据用户id查询用户信息
     * @param id 用户id
     * @return 用户
     * @throws Exception 错误信息
     */
    public User getOneUser(Integer id)throws Exception{
        return userMapper.selectUserById(id);
    }

    /**
     * 修改用户信息
     * @param user 用户信息
     * @return 影响数据条数
     * @throws Exception 抛出错误
     */
    public int editOneUser(User user)throws Exception{
        int num = 0;
        int updateNum = userMapper.updateUserById(user);
        if(updateNum > 0){
            num = userMapper.updateUserRole(user);
        }
        return num;
    }

    /**
     * 设置用户为禁用
     * @param user 用户信息
     * @return 影响数据条数
     * @throws Exception 错误
     */
    public int setUserDisable(User user)throws Exception{
        int num = 0;
        int updateNum = userMapper.setUserDelete(user);
        if(updateNum > 0){
            num = userMapper.setUserRoleDelete(user);
        }
        return num;
    }


    /**
     * 设置用户为启用
     * @param user 用户信息
     * @return 影响数据条数
     * @throws Exception 错误
     */
    public int setUserEnable(User user)throws Exception{
        int num = 0;
        int updateNum = userMapper.setUserNotDelete(user);
        if(updateNum > 0){
            num = userMapper.setUserRoleNotDelete(user);
        }
        return num;
    }

    /**
     * 修改用户密码
     * @param user 用户信息
     * @return 影响数据条数
     * @throws Exception 错误
     */
    public int resetUserPswd(User user)throws Exception{
        return userMapper.updateUserPswd(user);
    }
}

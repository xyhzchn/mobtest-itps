package com.yoozoo.controller;

import com.yoozoo.bean.Role;
import com.yoozoo.bean.User;
import com.yoozoo.service.RoleService;
import com.yoozoo.service.UserService;
import com.yoozoo.util.CommonParam;
import com.yoozoo.util.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import java.sql.Timestamp;
import java.util.*;

/**
 * 用户管理
 * Created by guoxx on 2018/6/28.
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserService userService;

    /**
     * 获取用户列表页面，首先需要加载所有的角色列表
     * @return modelandview对象
     * @throws Exception 错误
     */
    @RequestMapping("/userList")
    public ModelAndView toUserListPage(User user)throws Exception{

        ModelAndView mav = new ModelAndView();
        //定义从数据库中获取的角色列表。格式为Map<Integer,Role>
        Map<Integer,Role> roleMapByDB = new HashMap<>();
        //定义处理后的角色列表，格式为：Map<Integer,String>
        Map<Integer,String> roleMap = new HashMap<>();

        roleMapByDB = roleService.getRoleByMap();
        roleMap = MapUtils.getRoleMap(roleMapByDB); //将角色列表进行处理

        //获取用户列表
        List<User> userList = new ArrayList<>();
        userList = userService.getAllUsers(user);

        mav.addObject("roleMap",roleMap);
        mav.addObject("userList",userList);
        mav.setViewName("userList");

        return mav;
    }

    /**
     * 跳转到用户添加页面
     * @return ModelAndView
     */
    @RequestMapping("/toUserAddPage")
    public ModelAndView toUserAddPage()throws Exception{
        ModelAndView mav = new ModelAndView();

        //定义从数据库中获取的角色列表。格式为Map<Integer,Role>
        Map<Integer,Role> roleMapByDB = new HashMap<>();
        //定义处理后的角色列表，格式为：Map<Integer,String>
        Map<Integer,String> roleMap = new HashMap<>();

        roleMapByDB = roleService.getRoleByMap();
        roleMap = MapUtils.getRoleMap(roleMapByDB); //将角色列表进行处理

        mav.addObject("roleMap",roleMap);
        mav.addObject("flag","add");
        mav.setViewName("addUser");

        return mav;
    }

    /**
     * 添加用户
     * @param user 用户信息
     * @return 返回页面
     * @throws Exception 错误
     */
    @RequestMapping("/addUser")
    public String addUser(User user)throws Exception{

        //获取当前时间
        Date date = new Date();
        Timestamp now  = new Timestamp(date.getTime());
        //设置创建和修改时间
        user.setCreateTime(now);
        user.setUpdateTime(now);
        user.setIsDelete(CommonParam.NOT_DELETE);
        //密码加密
//        String encodePswd = (Base64.getEncoder().encode(user.getPassword().getBytes())).toString();
//        user.setPassword(encodePswd);

        //执行添加操作
        int num =  userService.addOneUser(user);
        if(num > 0){
            //重定向
            return "redirect:/user/userList";
        }else {
            return "error";
        }
    }

    /**
     * 获取用户详情
     * @param id 用户id
     * @return ModelAndView
     */
    @RequestMapping("/getUserDetail")
    public ModelAndView getUserDetail(Integer id)throws Exception{
        ModelAndView mav = new ModelAndView();
        //根据id获取用户信息
        User user = userService.getOneUser(id);

        //定义从数据库中获取的角色列表。格式为Map<Integer,Role>
        Map<Integer,Role> roleMapByDB = new HashMap<>();
        //定义处理后的角色列表，格式为：Map<Integer,String>
        Map<Integer,String> roleMap = new HashMap<>();

        roleMapByDB = roleService.getRoleByMap();
        roleMap = MapUtils.getRoleMap(roleMapByDB); //将角色列表进行处理

        mav.addObject("roleMap",roleMap);
        mav.addObject("user",user);
        mav.addObject("flag","detail");
        mav.setViewName("addUser");

        return mav;
    }

    /**
     * 修改用户
     * @param user 用户信息
     * @return 重定向页面
     * @throws Exception 错误信息
     */
    @RequestMapping("/editUser")
    public String editUser(User user)throws Exception{
        //获取当前时间
        Date date = new Date();
        Timestamp now  = new Timestamp(date.getTime());
        //设置修改时间
        user.setUpdateTime(now);
        //执行修改操作
        int num =  userService.editOneUser(user);
        if(num > 0){
            //重定向
            return "redirect:/user/userList";
        }else {
            return "error";
        }
    }


    /**
     * 设置用户状态为禁用，即删除用户
     * @param id 用户id
     * @return 跳转页面
     * @throws Exception 错误信息
     */
    @RequestMapping("/setUserDisable")
    public String setUserDisable(Integer id)throws Exception{

        //获取当前时间
        Date date = new Date();
        Timestamp now  = new Timestamp(date.getTime());
        User user = new User();
        //设置修改时间
        user.setUpdateTime(now);
        user.setUserId(id);

        int num =  userService.setUserDisable(user);
        if(num > 0){
            //重定向
            return "redirect:/user/userList";
        }else {
            return "error";
        }
    }

    /**
     * 设置用户状态为启用，即重启用户
     * @param id 用户id
     * @return 跳转页面
     * @throws Exception 错误信息
     */
    @RequestMapping("/setUserEnable")
    public String setUserEnable(Integer id)throws Exception{

        //获取当前时间
        Date date = new Date();
        Timestamp now  = new Timestamp(date.getTime());
        User user = new User();
        //设置修改时间
        user.setUpdateTime(now);
        user.setUserId(id);

        int num =  userService.setUserEnable(user);
        if(num > 0){
            //重定向
            return "redirect:/user/userList";
        }else {
            return "error";
        }
    }
}

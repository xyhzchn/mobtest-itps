package com.yoozoo.controller;

import com.yoozoo.bean.Role;
import com.yoozoo.service.RoleService;
import com.yoozoo.util.CommonParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * 角色管理相关控制类
 * Created by guoxx on 2018/6/27.
 */
@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    /**
     * 添加角色
     * @param role 角色信息
     * @return 影响数据库条数
     * @throws Exception 错误信息
     */
    @RequestMapping("/addRole")
    public String addRole(Role role)throws Exception{
        ModelAndView mav = new ModelAndView();
        //创建和修改角色时间
        Date date = new Date();
        Timestamp now = new Timestamp(date.getTime());
        role.setIsDelete(CommonParam.NOT_DELETE);
        role.setCreateTime(now);
        role.setUpdateTime(now);
        //执行添加操作
        int num = roleService.addRole(role);
        if(num > 0){
            return "redirect:/role/getRoles";
        }else {
            return "error";
        }
    }

    /**
     * 获取角色列表
     * @param role 查询条件
     * @return 角色列表
     * @throws Exception 错误信息
     */
    @RequestMapping("/getRoles")
    public ModelAndView getRoles(Role role)throws Exception{

        ModelAndView mav = new ModelAndView();
        List<Role> roleList = new ArrayList<>();

        roleList = roleService.getRoles(role);
        System.out.println(roleList.size());

        mav.addObject("roleList",roleList);
        mav.setViewName("roleList");

        return mav;
    }

    @RequestMapping("/getObjDetail")
    public ModelAndView getObjDetail(Integer id)throws Exception{
        ModelAndView mav = new ModelAndView();
        Role role = roleService.getRoleDetail(id);

        mav.addObject("role",role);
        mav.addObject("flag","detail");
        mav.setViewName("addRole");

        return mav;
    }

    @RequestMapping("/toAddRolePage")
    public ModelAndView toAddRolePage()throws Exception{
        ModelAndView mav = new ModelAndView();
        mav.addObject("flag","add");
        mav.setViewName("addRole");

        return mav;
    }

    @RequestMapping("/editRole")
    public String editRole(Role role)throws Exception{
        ModelAndView mav = new ModelAndView();

        //修改角色时间
        Date date = new Date();
        Timestamp now = new Timestamp(date.getTime());
        role.setUpdateTime(now);
        role.setIsDelete(CommonParam.NOT_DELETE);

        int num = roleService.editRole(role);

        if(num > 0){
            return "redirect:/role/getRoles";
        }else {
            return "error";
        }
    }

    @RequestMapping("/deleteRole")
    public String deleteRole(Integer id)throws Exception{
        ModelAndView mav = new ModelAndView();
        //角色id
        Role role = new Role();
        role.setRoleId(id);
        role.setIsDelete(CommonParam.DELETE);
        //修改角色时间
        Date date = new Date();
        Timestamp now = new Timestamp(date.getTime());
        role.setUpdateTime(now);

        int num = roleService.deleteRole(role);

        if(num > 0){
            return "redirect:/role/getRoles";
        }else {
            return "error";
        }
    }
}

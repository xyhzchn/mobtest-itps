package com.yoozoo.controller;

import com.yoozoo.service.UserService;
import com.yoozoo.util.CommonParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import com.yoozoo.bean.User;

/**
 * 登录逻辑处理类
 * Created by guoxx on 2018/6/22.
 */
@Controller
@SessionAttributes("userInfo")
public class LoginController {

    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    public ModelAndView userLogin(User user, ModelMap model)throws Exception{

        ModelAndView modelAndView = new ModelAndView();
        if(user == null){
            modelAndView.addObject("msg","");
            modelAndView.setViewName("login");
        }
        //根据用户名获取用户信息
        User auser = new User();
        auser = userService.getUserByName(user);
        if(auser != null){
            if(auser.getIsDelete() == CommonParam.DELETE){
                modelAndView.addObject("msg","用户已禁用");
                modelAndView.setViewName("login");
            }else {
                if(user.getUserName().equals(auser.getUserName())){
//                    String pswd = new String(Base64.getDecoder().decode(auser.getPassword())) ;
                    if(auser.getPassword().equals(user.getPassword())){
                        //将用户信息放到session中
                        model.addAttribute("userInfo",auser);
                        modelAndView.setViewName("homePage");
                    }else {
                        modelAndView.addObject("msg","登录密码输入错误");
                        modelAndView.setViewName("login");
                    }
                }
            }

        }else{
            modelAndView.addObject("msg","用户不存在");
            modelAndView.setViewName("login");
        }

        return modelAndView;
    }

    /**
     * 跳转到设置密码页面
     * @param user 用户信息
     * @return modelandview
     * @throws Exception 错误
     */
    @RequestMapping("/toSetPswd")
    public ModelAndView toSetPswd(User user)throws Exception{
        ModelAndView mav = new ModelAndView();
        User auser = userService.getUserByName(user);
        if(auser == null){
            mav.addObject("msg","用户不存在");
            mav.setViewName("findPswd");
        }else {
            mav.addObject("user",user);
            mav.setViewName("resetPswd");
        }
        return mav;
    }

    /**
     * 重置指定用户的密码
     * @param user 用户信息
     * @return 跳转页面
     * @throws Exception 错误
     */
    @RequestMapping("/resetPswd")
    public String resetPswd(User user)throws Exception{

       int num = userService.resetUserPswd(user);
        if(num > 0){
            return "login";
        }else {
            return "error";
        }

    }

}

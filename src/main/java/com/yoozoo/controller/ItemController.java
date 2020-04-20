package com.yoozoo.controller;

import com.yoozoo.service.ItemService;
import com.yoozoo.util.CommonParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import com.yoozoo.bean.Item;
import com.yoozoo.bean.User;

import java.sql.Timestamp;
import java.util.*;

/**
 * 项目相关控制类
 * Created by guoxx on 2018/7/13.
 */
@Controller
@RequestMapping("/item")
@SessionAttributes("userInfo")
public class ItemController {

    @Autowired
    private ItemService itemService;


    @RequestMapping("/getItemsForJson")
    public @ResponseBody List<Map<String,String>> getAllItemsForJson(Item item, @ModelAttribute("userInfo")User user)throws Exception{
        List<Map<String,String>> list = new ArrayList<>();
        Map<String,String> map = null;
        //设置用户id
        item.setUserId(user.getUserId());
        //获取查询结果
        List<Item> itemList = itemService.getAllItems(item);
        //将项目列表放入map中。且以json格式返回
        for(Item aitem:itemList){
            map = new HashMap<>();
            map.put("id",String.valueOf(aitem.getItemId()).trim());
            map.put("name",aitem.getItemName());
            map.put("desc",aitem.getItemDesc());
            list.add(map);
        }
        return list;
    }

    /**
     * 获取所有得项目列表
     * @param item 项目信息
     * @param user session中得user信息
     * @return model and view
     */
    @RequestMapping("/getItems")
    public ModelAndView getAllItems(Item item, @ModelAttribute("userInfo")User user)throws Exception{
        ModelAndView mav = new ModelAndView();
        //设置用户id
        item.setUserId(user.getUserId());
        //获取查询结果
        List<Item> itemList = itemService.getAllItems(item);
        //放到mav对象中
        mav.addObject("itemList",itemList);
        mav.setViewName("itemList");
        return mav;
    }

    /**
     * 添加项目
     * @return ModelAndView
     * @throws Exception 错误
     */
    @RequestMapping("/toAddItemPage")
    public ModelAndView toAddItemPage()throws Exception{
        ModelAndView mav = new ModelAndView();
        mav.addObject("flag","add");
        mav.setViewName("addItem");

        return mav;
    }

    /**
     * 添加一个项目
     * @param item  项目信息
     * @param user  session中的用户信息
     * @return 重定向
     * @throws Exception 错误
     */
    @RequestMapping("/addItem")
    public String addItem(Item item,@ModelAttribute("userInfo")User user)throws Exception{
        ModelAndView mav = new ModelAndView();
        //创建和修改角色时间
        Date date = new Date();
        Timestamp now = new Timestamp(date.getTime());
        item.setIsDelete(CommonParam.NOT_DELETE);
        item.setCreateTime(now);
        item.setUpdateTime(now);
        item.setUserId(user.getUserId());
        //执行添加操作
        int num = itemService.addOneItem(item);
        if(num > 0){
            return "redirect:/item/getItems";
        }else {
            return "error";
        }
    }

    /**
     * 根据id 获得项目信息
     * @param id 项目id
     * @return ModelAndView
     * @throws Exception 错误
     */
    @RequestMapping("/getItemDetail")
    public ModelAndView getItemDetail(Integer id)throws Exception{
        ModelAndView mav = new ModelAndView();
        Item item = itemService.getItemById(id);

        mav.addObject("item",item);
        mav.addObject("flag","detail");
        mav.setViewName("addItem");

        return mav;
    }

    @RequestMapping("/editItem")
    public String editItem(Item item,@ModelAttribute("userInfo")User user)throws Exception{
        ModelAndView mav = new ModelAndView();

        //修改角色时间
        Date date = new Date();
        Timestamp now = new Timestamp(date.getTime());
        item.setUserId(user.getUserId());
        item.setUpdateTime(now);
        item.setIsDelete(CommonParam.NOT_DELETE);

        int num = itemService.editItem(item);

        if(num > 0){
            return "redirect:/item/getItems";
        }else {
            return "error";
        }
    }


    /**
     * 修改项目的状态为已删除
     * @param id 项目
     * @return 影响数据条数
     * @throws Exception 错误
     */
    @RequestMapping("/deleteItem")
    public String deleteItem(Integer id)throws Exception{
        ModelAndView mav = new ModelAndView();
        //角色id
        Item item = new Item();
        item.setItemId(id);
        item.setIsDelete(CommonParam.DELETE);
        //修改角色时间
        Date date = new Date();
        Timestamp now = new Timestamp(date.getTime());
        item.setUpdateTime(now);

        int num = itemService.deleteItem(item);

        if(num > 0){
            return "redirect:/item/getItems";
        }else {
            return "error";
        }
    }
}

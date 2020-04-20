package com.yoozoo.service;

import com.yoozoo.bean.Item;

import java.util.List;

/**
 * 项目管理接口类
 * Created by guoxx on 2018/7/13.
 */
public interface ItemService {

    /**
     * 查询所有得项目列表
     * @param item 项目信息
     * @return 项目列表
     * @throws Exception 错误
     */
    List<Item> getAllItems(Item item)throws Exception;

    /**
     * 添加一个项目
     * @param item 项目信息
     * @return 影响数据条数
     * @throws Exception 错误
     */
    int addOneItem(Item item)throws Exception;

    /**
     * 通过项目id获得项目信息
     * @param id 项目id
     * @return 项目信息
     * @throws Exception 错误
     */
    Item getItemById(Integer id) throws Exception;

    /**
     * 修改一条项目信息
     * @param item 项目信息
     * @return 影响数据条数
     * @throws Exception 错误
     */
    int editItem(Item item) throws Exception;

    /**
     * 修改项目状态为已删除
     * @param item 项目信息
     * @return 影响数据条数
     * @throws Exception 错误
     */
    int deleteItem(Item item) throws Exception;
}

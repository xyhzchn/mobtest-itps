package com.yoozoo.mapper;

import com.yoozoo.bean.Item;

import java.util.List;

/**
 * 项目管理相关dao接口
 * Created by guoxx on 2018/7/13.
 */
public interface ItemMapper {

    /**
     * 查询所有得项目列表
     * @param item 项目信息
     * @return 项目列表
     * @throws Exception 错误
     */
    List<Item> selectAllItems(Item item)throws Exception;

    /**
     * 添加一个项目
     * @param item  项目信息
     * @return 影响数据条数
     * @throws Exception 错误
     */
    int insertOneItem(Item item) throws Exception;

    /**
     * 根据项目id获取项目信息
     * @param id 项目ID
     * @return 项目信息
     * @throws Exception 错误
     */
    Item selectItemById(Integer id)throws Exception;

    /**
     * 修改某一项目信息
     * @param item 项目信息
     * @return 影响数据条数
     * @throws Exception 错误
     */
    int updateOneItem(Item item) throws Exception;

    /**
     * 修改项目状态为已删除
     * @param item 项目信息
     * @return 影响数据条数
     * @throws Exception 错误
     */
    int deleteItemById(Item item) throws Exception;
}

package com.yoozoo.service.impl;

import com.yoozoo.bean.Item;
import com.yoozoo.mapper.ItemMapper;
import com.yoozoo.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 项目接口实现类
 * Created by guoxx on 2018/7/13.
 */
public class ItemServiceImpl implements ItemService{

    //项目dao接口
    @Autowired
    private ItemMapper itemMapper;

    /**
     * 查询所有得项目列表
     * @param item 项目信息
     * @return 项目列表
     * @throws Exception 错误
     */
    public List<Item> getAllItems(Item item)throws Exception{
        return itemMapper.selectAllItems(item);
    }


    /**
     * 添加一个项目
     * @param item 项目信息
     * @return 影响数据条数
     * @throws Exception 错误
     */
    public int addOneItem(Item item)throws Exception{
        return itemMapper.insertOneItem(item);
    }

    /**
     * 通过项目id获得项目信息
     * @param id 项目id
     * @return 项目信息
     * @throws Exception 错误
     */
    public Item getItemById(Integer id) throws Exception{
        return itemMapper.selectItemById(id);
    }

    /**
     * 修改一条项目信息
     * @param item 项目信息
     * @return 影响数据条数
     * @throws Exception 错误
     */
    public int editItem(Item item) throws Exception{
        return itemMapper.updateOneItem(item);
    }

    /**
     * 修改项目状态为已删除
     * @param item 项目信息
     * @return 影响数据条数
     * @throws Exception 错误
     */
    public int deleteItem(Item item) throws Exception{
        return itemMapper.deleteItemById(item);
    }
}

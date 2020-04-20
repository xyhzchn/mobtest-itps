package com.yoozoo.service.impl;

import com.yoozoo.bean.Param;
import com.yoozoo.mapper.ParamMapper;
import com.yoozoo.service.ParamService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 参数相关接口实现类
 * Created by guoxx on 2018/9/5.
 */
public class ParamServiceImpl implements ParamService {

    @Autowired
    private ParamMapper paramMapper;

    /**
     * 添加一条参数信息
     * @param param 参数信息
     * @return 影响数据条数
     * @throws Exception    错误
     */
    public int addOneParam(Param param)throws Exception{
        return paramMapper.insertOneParam(param);
    }
}

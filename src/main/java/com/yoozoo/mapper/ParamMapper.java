package com.yoozoo.mapper;

import com.yoozoo.bean.Param;

/**
 * 参数实现类
 * Created by guoxx on 2018/9/6.
 */
public interface ParamMapper {
    /**
     * 添加一条参数信息
     * @param param 参数信息
     * @return 影响数据条数
     * @throws Exception    错误
     */
    int insertOneParam(Param param)throws Exception;
}

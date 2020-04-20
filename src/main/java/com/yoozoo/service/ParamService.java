package com.yoozoo.service;

import com.yoozoo.bean.Param;

/**
 *参数相关接口类
 * Created by guoxx on 2018/9/5.
 */
public interface ParamService {
    /**
     * 添加一条参数信息
     * @param param 参数信息
     * @return 影响数据条数
     * @throws Exception    错误
     */
    int addOneParam(Param param)throws Exception;
}

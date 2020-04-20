package com.yoozoo.service;

import com.github.pagehelper.PageInfo;
import com.yoozoo.bean.Api;
import com.yoozoo.bean.ApiRunHistory;
import com.yoozoo.bean.RunLog;

import java.util.List;

/**
 * 接口功能相关
 * Created by guoxx on 2018/7/24.
 */
public interface ApiService {

    /**
     * 创建一个接口
     * @param api 接口信息
     * @return  影响数据条数
     * @throws Exception 错误
     */
    int addOneApi(Api api)throws Exception;

    /**
     * 获取接口列表
     * @param api 接口信息
     * @return 接口列表
     * @throws Exception 错误
     */
    PageInfo<Api> getAllApiList(Api api, Integer currentPage, Integer pageSize)throws Exception;

    /**
     * 根据接口id获取接口信息
     * @param apiId 接口id
     * @return 接口信息
     * @throws Exception 错误
     */
    Api getOneApiById(Integer apiId) throws Exception;

    /**
     * 运行测试后，修改接口相关数据据
     * @param api   接口信息
     * @param logs  接口运行对应的检查点结果列表
     * @param history 接口运行结果
     * @return 响应数据条数
     * @throws Exception 错误
     */
    int editOneApiAfterTest(Api api, List<RunLog> logs, ApiRunHistory history)throws Exception;

    /**
     * 删除某一接口
     * @param api 接口信息
     * @return 影响数据条数
     * @throws Exception 错误信息
     */
    int deleteApi(Api api)throws Exception;

    /**
     * 修改某一接口的请求信息
     * @param api 接口信息
     * @return 影响数据条数
     * @throws Exception 错误信息
     */
    int editApiRequestInfo(Api api)throws Exception;


    /**
     * 查询指定接口下的所有的检查点报告
     * @param apiId 接口id
     * @return 报告条数
     * @throws Exception 错误
     */
    List<RunLog> getAllRunLogByApiId(Integer apiId)throws Exception;

    /**
     * 获取当前接口最大的版本号
     * @param apiId 接口ID
     * @return 最大的坂本号
     * @throws Exception 错误
     */
    int getMaxVersionByApiId(Integer apiId)throws Exception;

    /**
     * 添加接口运行结果
     * @param log 运行日志
     * @return 影响数据条数
     * @throws Exception 错误
     */
    int addRunLog(RunLog log)throws Exception;

}

package com.yoozoo.mapper;

import com.yoozoo.bean.Api;

import java.util.List;
import java.util.Map;

import com.yoozoo.bean.ApiRunHistory;
import com.yoozoo.bean.Point;
import com.yoozoo.bean.RunLog;

/**
 * 接口dao
 * Created by guoxx on 2018/7/24.
 */
public interface ApiMapper {

    /**
     * 添加一条接口信息
     *
     * @param api 接口信息
     * @return 影响数据条数
     * @throws Exception 错误
     */
    int insertOneApi(Api api) throws Exception;

    /**
     * 添加检查点
     * @param point 检查点对象
     * @return 影响数据条数
     * @throws Exception 错误
     */
    int insertCheckPoints(Point point)throws Exception;

    /**
     * 查询所有的接口列表
     *
     * @param api 接口信息
     * @return 接口列表
     * @throws Exception 错误
     */
    List<Api> selectAllApis(Api api) throws Exception;

    /**
     * 根据接口id获取接口信息
     * @param apiId 接口id
     * @return 接口信息
     * @throws Exception 错误
     */
    Api selectOneApiById(Integer apiId) throws Exception;

    /**
     * 获取某个接口下的所有检查点列表
     * @param apiId 接口id
     * @return 检查点列表
     * @throws Exception 错误
     */
    List<Point> selectAllPointByApiId(Integer apiId) throws Exception;

    /**
     * 删除某一接口
     * @param api 接口信息
     * @return 影响数据条数
     * @throws Exception 错误信息
     */
    int deleteApiById(Api api)throws Exception;

    /**
     * 修改某一接口的请求信息
     * @param api 接口信息
     * @return 影响数据条数
     * @throws Exception 错误信息
     */
    int updateApiRequestInfo(Api api)throws Exception;

    /**
     * 删除某个接口下的所有检查点
     * @param apiId 接口
     * @return 影响数据条数
     * @throws Exception 错误
     */
    int deleteCheckPointByApiId(Integer apiId)throws Exception;

    /**
     * 查询指定接口下的所有的检查点报告
     * @param apiId 接口id
     * @return 报告条数
     * @throws Exception 错误
     */
    List<RunLog> selectAllRunLogByApiId(Integer apiId)throws Exception;

    /**
     * 获取当前接口最大的版本号
     * @param apiId 接口ID
     * @return 最大的坂本号
     * @throws Exception 错误
     */
    int selectMaxVersionByApiId(Integer apiId)throws Exception;

    /**
     * 添加一条检查点执行结果
     * @param log 执行日志
     * @return 影响数据条数
     * @throws Exception 错误
     */
    int insertOneRunLog(RunLog log)throws Exception;

    /**
     * 根据接口id和版本号，获取接口对应版本的检查点检查结果的值
     * @param map 包含接口id和版本号
     * @return 结果列表
     * @throws Exception 错误
     */
    List<Integer> selectAllCheckResult(Map map)throws Exception;

    /**
     * 添加一条接口执行结果
     * @param history 执行结果
     * @return 影响数据条数
     * @throws Exception 错误
     */
    int insertOneApiRunHistory(ApiRunHistory history)throws Exception;

    /**
     * 修改某一接口的响应信息
     * @param api 接口信息
     * @return 影响数据条数
     * @throws Exception 错误信息
     */
    int updateOneApi(Api api)throws Exception;

    /**
     * 删除接口下的所有运行记录
     * @param apiId 接口
     * @return 影响数据条数
     * @throws Exception 错误信息
     */
    int deleteRunlogById(Integer apiId)throws Exception;

    /**
     * 删除接口下的所有运行历史
     * @param apiId 接口
     * @return 影响数据条数
     * @throws Exception 错误信息
     */
    int deleteHistoryById(Integer apiId)throws Exception;

    /**
     * 查询接口下的所有运行历史
     * @param apiId 接口id
     * @return 运行历史列表
     * @throws Exception 错误
     */
    List<ApiRunHistory> getAllHistoryByApiId(Integer apiId) throws Exception;
}
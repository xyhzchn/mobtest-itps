package com.yoozoo.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yoozoo.bean.Api;
import com.yoozoo.bean.ApiRunHistory;
import com.yoozoo.bean.RunLog;
import com.yoozoo.service.ApiService;
import com.yoozoo.mapper.ApiMapper;
import com.yoozoo.util.CommonParam;
import org.springframework.beans.factory.annotation.Autowired;
import com.yoozoo.bean.Point;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 接口实现类
 * Created by guoxx on 2018/7/24.
 */
public class ApiServiceImpl implements ApiService{

    @Autowired
    private ApiMapper apiMapper;

    /**
     * 创建一个接口
     * @param api 接口信息
     * @return  影响数据条数
     * @throws Exception 错误
     */
    public int addOneApi(Api api)throws Exception{
         int result = 0;
         apiMapper.insertOneApi(api);
         int apiId = api.getApiId();
         for(Point point:api.getPoints()){
             point.setApiId(apiId);
             point.setIsDelete(CommonParam.NOT_DELETE);
             result = apiMapper.insertCheckPoints(point);
         }
         return result;
    }

    /**
     * 获取接口列表
     * @param api 接口信息
     * @return 接口列表
     * @throws Exception 错误
     */
    public PageInfo<Api> getAllApiList(Api api, Integer currentPage, Integer pageSize)throws Exception{
        PageHelper.startPage(currentPage,pageSize); //设置分页开始页面，和显示条数
        List<Api> apiList = apiMapper.selectAllApis(api);
        PageInfo<Api> pageInfo = new PageInfo<Api>(apiList);
        return pageInfo;
    }

    /**
     * 根据接口id获取接口信息
     * @param apiId 接口id
     * @return 接口信息
     * @throws Exception 错误
     */
    public Api getOneApiById(Integer apiId) throws Exception{
        Api api = null;
        api = apiMapper.selectOneApiById(apiId);
        if(api != null){
            List<Point> points = apiMapper.selectAllPointByApiId(apiId);
            if (points.size() > 0){
                api.setPoints(points);
            }
        }

        return api;
    }

    /**
     * 运行测试后，修改接口相关数据据
     * @param api   接口信息
     * @param logList  接口运行对应的检查点结果列表
     * @param history 接口运行结果
     * @return 响应数据条数
     * @throws Exception 错误
     */
    public int editOneApiAfterTest(Api api,List<RunLog> logList, ApiRunHistory history)throws Exception{
        int result = 0;
        //设置版本号
        int version = 0;
        List<RunLog> logs = apiMapper.selectAllRunLogByApiId(api.getApiId());
        if(logs.size() == 0){
            version = 1;
        }else{
            int maxVersion = apiMapper.selectMaxVersionByApiId(api.getApiId());
            version = maxVersion + 1;
        }
        //添加检查点结果列表
        int num1 = 0;
        for(RunLog log:logList){
            log.setVersion(version);
            num1 = apiMapper.insertOneRunLog(log);
        }
        //添加接口运行结果
        int num2 = 0;
        if(num1 > 0){
            history.setVersion(version);
            Map<String,Integer> map = new HashMap<>();
            map.put("apiId",api.getApiId());
            map.put("version",version);
            List<Integer> checkResult = apiMapper.selectAllCheckResult(map);
            if(checkResult.contains(CommonParam.FAIL)){
                history.setRunResult(CommonParam.FAIL);
            }else {
                history.setRunResult(CommonParam.PASS);
            }
            num2 = apiMapper.insertOneApiRunHistory(history);
        }

        if(num2 > 0){
            result = apiMapper.updateOneApi(api);
        }
        return result;
    }

    /**
     * 删除某一接口
     * @param api 接口信息
     * @return 影响数据条数
     * @throws Exception 错误信息
     */
    public int deleteApi(Api api)throws Exception{
        int apiId = api.getApiId();
        //删除一个用例，若用例有执行结果，则首先删除执行结果
        List<RunLog> logs = getAllRunLogByApiId(apiId);
        if(logs != null){
            apiMapper.deleteRunlogById(apiId);
        }
        List<ApiRunHistory> histories = apiMapper.getAllHistoryByApiId(apiId);
        if(histories != null){
            apiMapper.deleteHistoryById(apiId);
        }
        //若无执行结果，则删除用例的检查点
        List<Point> points = apiMapper.selectAllPointByApiId(apiId);
        if(points != null){
            apiMapper.deleteCheckPointByApiId(apiId);
        }

        //然后删除用例
        return apiMapper.deleteApiById(api);
    }

    /**
     * 修改某一接口的请求信息
     * @param api 接口信息
     * @return 影响数据条数
     * @throws Exception 错误信息
     */
    public int editApiRequestInfo(Api api)throws Exception{
        int result = 0;
        result = apiMapper.updateApiRequestInfo(api);
        if(result > 0){
           int num = apiMapper.deleteCheckPointByApiId(api.getApiId());
            if(num > 0){
                for(Point point:api.getPoints()){
                    point.setApiId(api.getApiId());
                    point.setIsDelete(CommonParam.NOT_DELETE);
                    result = apiMapper.insertCheckPoints(point);
                }
            }
        }
        return result;
    }


    /**
     * 查询指定接口下的所有的检查点报告
     * @param apiId 接口id
     * @return 报告条数
     * @throws Exception 错误
     */
    public List<RunLog> getAllRunLogByApiId(Integer apiId) throws Exception {
        return apiMapper.selectAllRunLogByApiId(apiId);
    }


    /**
     * 获取当前接口最大的版本号
     * @param apiId 接口ID
     * @return 最大的坂本号
     * @throws Exception 错误
     */
    public int getMaxVersionByApiId(Integer apiId) throws Exception {
        return apiMapper.selectMaxVersionByApiId(apiId);
    }

    /**
     * 添加接口运行结果
     * @param log 运行日志
     * @return 影响数据条数
     * @throws Exception 错误
     */
    public int addRunLog(RunLog log)throws Exception{
        return apiMapper.insertOneRunLog(log);

    }
}

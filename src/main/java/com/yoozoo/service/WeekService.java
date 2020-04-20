package com.yoozoo.service;

import com.github.pagehelper.PageInfo;
import com.yoozoo.bean.Compare;
import com.yoozoo.bean.Week;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 * 周报业务相关接口
 * Created by guoxx on 2018/11/26.
 */
public interface WeekService {

    /**
     * 导入数据
     * @param in 输入流
     * @param file 文件
     * @param userId 登录用户id
     * @throws Exception 错误信息
     */
     void importExcelInfo(InputStream in, MultipartFile file,Integer userId)throws Exception;

    /**
     * 获取周列表并分页
     * @param week    周列表
     * @param currentPage 当前页面
     * @param pageSize 每页显示数据条数
     * @return 所有周列表
     * @throws Exception    错误
     */
     PageInfo<Week> getAllWeekList(Week week, Integer currentPage, Integer pageSize)throws Exception;

    /**
     * 获取所有的周报列表
     * @return  所有的周报列表
     * @throws Exception 错误
     */
    List<Week> getAllWeek()throws Exception;

    /**
     * 根据周的id以及用户信息查询出该用户的对应周的周报详情
     * @param week  周信息和用户信息
     * @return  周报信息
     * @throws Exception 错误
     */
    Week getWeekForSomeOne(Week week)throws Exception;

    /**
     * 根据周的id，查询出该周下所有已提交周报的用户id
     * @param weeklyId  周id
     * @return  已提交周报的用户列表
     * @throws Exception 错误
     */
    Map<Integer,String> getUserListByWeekId(Integer weeklyId)throws Exception;
    /**
     * 根据月份获取该月上传周报的用户列表
     * @param month 月份
     * @return 该月上传周报的用户列表
     * @throws Exception 错误
     */
    Map<Integer,String> getUserListByMonth(Integer month)throws Exception;

    /**
     * 根据周的id，查询出该周下所有已提交周报的用户id
     * @param weekIdList  周id
     * @return  已提交周报的用户列表
     * @throws Exception 错误
     */
    Map<Integer,String> getUserListByWeekList(List<Integer> weekIdList)throws Exception;
    /**
     * 根据月份，查询出该月下所有已提交周报的用户id
     * @param monthList  月
     * @return  已提交周报的用户列表
     * @throws Exception 错误
     */
    Map<Integer,String> getUserListByMonthList(List<Integer> monthList)throws Exception;

    /**
     * 根据用户传递的查询条件，获取周报列表
     * @param week  用户传递的查询条件
     * @return  周报列表
     * @throws Exception    错误
     */
    List<Week> getWeeklyList(Week week)throws Exception;


    /**
     * 根据查询的周列表和用户id，查询某用户对应的周记录信息
     * @param week    根据查询的周列表和用户id
     * @return  查询某用户对应的周记录信息
     * @throws Exception 错误
     */
    List<Compare> getCompareDataWeek(Week week)throws Exception;

    /**
     * 根据查询的月列表和用户id，查询某用户对应的月记录信息
     * @param week    根据查询的月列表和用户id
     * @return  查询某用户对应的月记录信息
     * @throws Exception 错误
     */
    List<Compare> getCompareDataMonth(Week week)throws Exception;



}

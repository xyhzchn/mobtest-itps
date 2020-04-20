package com.yoozoo.mapper;

import com.yoozoo.bean.CaseAndBug;
import com.yoozoo.bean.CurrentWeek;
import com.yoozoo.bean.NextWeek;
import com.yoozoo.bean.Week;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * 周报相关数据库操作类
 * Created by guoxx on 2018/11/30.
 */
public interface WeekMapper {

    /**
     * 添加周报信息
     * @param week 周信息
     * @return 影响数据条数
     */
    int insertOneWeek(Week week);

    /**
     * 添加信息到关联表
     * @param week 周信息
     * @return 关联表的主键
     */
    int insertOneWeekUser(Week week);

    /**
     *添加周报中的记录详情
     * @param details 周报详情
     * @return 影响数据条数
     */
    int insertCurrentWeek(List<CurrentWeek> details);

    /**
     * 添加周报中的用例和bug等
     * @param caseAndBug  用例和bug等
     * @return 影响数据条数
     */
    int insertCaseAndBug(CaseAndBug caseAndBug);

    /**
     * 添加周报中下周计划数据
     * @param nextWeeks 下周计划信息
     * @return  影响数据条数
     */
    int insertNextWeek(List<NextWeek> nextWeeks);

    /**
     * 根据上传的汇报者名字获取汇报者的id
     * @param userName 汇报者的真实名字
     * @return 用户
     */
    Integer getUserIdByRealName(@Param(value = "userName") String userName);

    /**
     * 根据周的结束时间，查询是否存在周信息
     * @param endDate 周的结束信息
     * @return weeklyId
     */
    Integer selectWeeklyIdbyDate(Date endDate);

    /**
     * 根据周的id和用户的id查询对应的关联表的主键
     * @param week 周的id和用户的id
     * @return 对应的关联表的主键
     */
    List<Integer> selectWeekUserId(Week week);

    /**
     * 根据月份信息查询该月份包含的周id列表
     * @param week 月份信息
     * @return 该月份包含的周id列表
     */
    List<Integer> selectWeekId(Week week);

    /**
     * 查询所有的周信息
     * @param week 周信息
     * @return 周信息列表
     */
    List<Week> selectAllWeek(Week week);

    /**
     * 根据周的id获取该周所有用户的用例和bug
     * @param selectWeek 周id
     * @return 用例和bug
     * @throws Exception    错误
     */
    List<Week> selectUserListByWeekId(@Param(value = "selectWeek")Integer selectWeek)throws Exception;

    /**
     * 根据月份获取该月上传周报的用户列表
     * @param month 周id
     * @return 用例和bug
     * @throws Exception    错误
     */
    List<Week> selectUserListByMonth(@Param(value = "month") Integer month)throws Exception;


    /**
     * 查询周信息
     * @param week 查询条件
     * @return 周信息
     * @throws Exception    错误
     */
    Week selectWeekly(Week week)throws Exception;

    /**
     * 根据周的id获取周信息详情
     * @param week 周
     * @return 详情
     * @throws Exception    错误
     */
    List<CurrentWeek> selectCurrentWeek(Week week)throws Exception;

    /**
     * 根据周报id和用户id查询出该周某个人的用例和bug信息
     * @param week 周id
     * @return 用例和bug
     * @throws Exception    错误
     */
    List<CaseAndBug> selectCaseAndBug(Week week)throws Exception;

    /**
     * 根据周报id和用户的id查询出该周某个人的下周计划信息
     * @param week 周id
     * @return  下周计划列表
     * @throws Exception    错误
     */
    List<NextWeek> selectNextWeek(Week week)throws Exception;



    /**
     * 根据选择的多个要对比的周id查询出包含的用户列表
     * @param weekIdList  周id
     * @return 包含的用户列表
     * @throws Exception 错误
     */
    List<Week> selectUserListByWeekList(@Param("weekIdList") List<Integer> weekIdList)throws Exception;

    /**
     * 根据月份，查询出该月下所有已提交周报的用户id
     * @param monthList  月
     * @return  已提交周报的用户列表
     * @throws Exception 错误
     */
    List<Week> selectUserListByMonthList(@Param("monthList") List<Integer> monthList)throws Exception;


    /**
     * 根据关联id查询对应的总的预计时间和实际时间
     * @param weekUserIdList 关联id
     * @return 对应的总的预计时间和实际时间
     * @throws Exception 错误
     */
    CurrentWeek selectCurrentWeekCompare(@Param(value = "weekUserIdList") List<Integer> weekUserIdList)throws Exception;

    /**
     * 查询对应周中的对应用户的总bug和总用例数
     * @param weekUserIdList 关联id
     * @return 对应用户的总bug和总用例数
     * @throws Exception 错误
     */
    CaseAndBug selectCaseAndBugCompare(@Param(value = "weekUserIdList") List<Integer> weekUserIdList)throws Exception;



    /**
     * 根据月份和用户id查询出该月下关联id
     * @param week    月份信息和用户id
     * @return  该月下关联id
     * @throws Exception    错误
     */
    List<Integer> selectWeekUserIdByMonth(Week week)throws Exception;


}

package com.yoozoo.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yoozoo.bean.*;
import com.yoozoo.mapper.WeekMapper;
import com.yoozoo.service.WeekService;
import com.yoozoo.util.CommonParam;
import com.yoozoo.util.DateUtils;
import com.yoozoo.util.ExcelUtil;
import com.yoozoo.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.*;

/**
 * 周报相关接口实现类
 * Created by guoxx on 2018/11/26.
 */
public class WeekServiceImpl implements WeekService {

    @Autowired
    private WeekMapper weekMapper;

    @Transactional
    @Override
    public void importExcelInfo(InputStream in, MultipartFile file, Integer userId) throws Exception {
        //获取上传excel的所有数据
        List<List<Object>> listob = ExcelUtil.getBankListByExcel(in,file.getOriginalFilename());
        //将上传数据解析成周报信息
        Week week = getCurrentWeekInfo(listob);
        //设置默认未删除
        week.setIsDelete(CommonParam.NOT_DELETE);
        //设置创建时间和修改时间
        Date date = new Date();
        Timestamp now = new Timestamp(date.getTime());
        week.setCreateTime(now);
        week.setUpdateTime(now);

        //先判断周是否已存在，如果已存在，则获取周的id,若不存在，这添加周信息，并返回主键
        Integer weekId = 0;
        weekId = weekMapper.selectWeeklyIdbyDate(week.getEndDate());
        //当没有对应的周信息时,添加一个周信息
        if(weekId == null){
            weekMapper.insertOneWeek(week);
        }else {
            week.setWeekId(weekId);
        }

        //判断对应周的对应用户是否已在关联表，如果已存在，这返回关联表id，若不存在，则添加一条关联表记录，并返回主键
//        Integer weekUserId = 0;
//        //设置查询条件
//        week.setSelectWeek(weekId);
//        week.setSelectUser(week.getUserId());

//        List<Integer> weekUserIds = weekMapper.selectWeekUserId(week);
//        if(weekUserIds != null && weekUserIds.size() > 0){
//            weekUserId = weekUserIds.get(0);
//        }else {
            weekMapper.insertOneWeekUser(week);
//        }

        if(week.getId().intValue() > 0){
            //补全周报记录信息
            for(CurrentWeek current: week.getCurrentWeekList()){
                current.setId(week.getId());
                current.setIsDelete(CommonParam.NOT_DELETE);
                current.setCreateTime(now);
                current.setUpdateTime(now);
            }
            //补全用例和bug信息
            CaseAndBug caseAndBug = week.getCaseAndBug();
            caseAndBug.setId(week.getId());
            caseAndBug.setIsDelete(CommonParam.NOT_DELETE);
            caseAndBug.setCreateTime(now);
            caseAndBug.setUpdateTime(now);
            //补全下周计划信息
            for(NextWeek next: week.getNextWeekList()){
                next.setId(week.getId());
                next.setIsDelete(CommonParam.NOT_DELETE);
                next.setCreateTime(now);
                next.setUpdateTime(now);
            }

            if(week.getCurrentWeekList().size() > 0){
                weekMapper.insertCurrentWeek(week.getCurrentWeekList());
            }
            if(week.getCaseAndBug() != null){
                weekMapper.insertCaseAndBug(week.getCaseAndBug());
            }
            if(week.getNextWeekList().size() > 0){
                weekMapper.insertNextWeek(week.getNextWeekList());
            }

        }
    }



    /**
     * 解析上传的excel,将其转换为周报列表
     * @param list 上传的excel所有数据
     * @return 周报列表
     */
    public Week getCurrentWeekInfo(List<List<Object>> list) throws ParseException {
        Week week = new Week();
        List<CurrentWeek> currentWeekList = new ArrayList<>();
        CurrentWeek currentWeek = null;
        CaseAndBug caseAndBug = new CaseAndBug();
        //循环上传excel数据，区分本周工作内容和下周工作内容
        List<List<Object>> nextWeekList = new ArrayList<>();
        for(int i=0;i<list.size();i++){
            for(int j=0;j<list.get(i).size();j++){
                if(list.get(i).get(j).equals("第二部份：下周工作计划")){
                    do {
                        if(list.size() == i){
                            continue;
                        }else{
                            nextWeekList.add(list.get(i));
                            list.remove(i);
                        }
                    }while (list.size() > i);
                    break;
                }
            }
        }
        //将格式后的list转换为周报对象列表

        String userName = null;
        Integer userId = null;
        if(list.size() > 0){
            for(int i=0;i<list.size();i++){
                //for(int j=0;j<list.get(i).size();j++){
                List<Object> objectList = list.get(i);
                //第一行数据
                if(i==0){
                    //获取汇报者真实姓名
                    userName = objectList.get(6).toString();
                    userId = weekMapper.getUserIdByRealName(userName);
                    if(userId == null){
                        //TODO
                    }
                    //获取汇报时间
                    Date endDate = StringUtils.convertStringToDate(objectList.get(8).toString());
                    //获取该周的开始时间
                    Date startDate = DateUtils.getDateBefore(endDate,4);
                    //设置周报的开始时间和结束时间
                    week.setStartDate(startDate);
                    week.setEndDate(endDate);
                    week.setUserId(userId);
                    week.setUserName(userName);
                    continue;
                }
                //如果是第二行或者第三行数据，则直接跳过
                if(i==1 || i==2){
                    continue;
                }
                //前3行和最后4行时，不创建 CurrentWeek 对象
                if(i>2 && i<list.size()-4){
                    currentWeek = new CurrentWeek();
                    //工作事项
                    int workType = 0;
                    String workTypeStr = objectList.get(0).toString();
                    if(workTypeStr.equals("用例编写")){
                        workType = 1;
                    }else if(workTypeStr.equals("测试执行")){
                        workType = 2;
                    }else if(workTypeStr.equals("技术改进")){
                        workType = 3;
                    }else if(workTypeStr.equals("其他")){
                        workType = 4;
                    }
                    currentWeek.setWorkType(workType);
                    //jiraid
                    currentWeek.setJiraId(objectList.get(1).toString());
                    //项目组
                    currentWeek.setProjectName(objectList.get(2).toString());
                    //内容
                    currentWeek.setContent(objectList.get(3).toString());
                    //进度
                    currentWeek.setProgress(Float.parseFloat(objectList.get(5).toString()));
                    //是否上线
                    String isOnlineStr = objectList.get(9).toString();
                    if(isOnlineStr.equals("是")){
                        currentWeek.setIsOnline(1);
                    }else {
                        currentWeek.setIsOnline(0);
                    }
                    //是否回测
                    String isTestStr = objectList.get(10).toString();
                    if(isTestStr.equals("是")){
                        currentWeek.setIsRegressionTest(1);
                    }else {
                        currentWeek.setIsRegressionTest(0);
                    }
                    //预计测试周期
                    String estimatedTimeStr = objectList.get(11).toString();
                    currentWeek.setEstimatedTime(Float.parseFloat(estimatedTimeStr.substring(0,estimatedTimeStr.length()-1)));
                    //实际累计耗时
                    String realTimeStr = objectList.get(12).toString();
                    currentWeek.setRealTime(Float.parseFloat(realTimeStr.substring(0,realTimeStr.length()-1)));
                    //备注
                    currentWeek.setOtherDesc(objectList.get(13).toString());

                    currentWeekList.add(currentWeek);

                }
                //遗留问题
                if(i == list.size()-4){
                    caseAndBug.setRemaining(objectList.get(5).toString());
                }
                //新增用例数
                if(i == list.size()-3){
                    String cases = objectList.get(5).toString();
                    String[] caseArray = cases.split(",");
                    for(String singleCase:caseArray){
                        String[] strArr = singleCase.split(":");
                        String key = strArr[0].trim();
                        Integer value;
                        if(strArr[1].trim().equals("*")){
                            value = 0;
                        }else {
                            value = Integer.parseInt(strArr[1].trim());
                        }

                        if(key.equals("P0") || key.equals("p0")){
                            caseAndBug.setCase_P1(value);
                        }
                        if(key.equals("P1") || key.equals("p1")){
                            caseAndBug.setCase_P2(value);
                        }
                        if(key.equals("P2") || key.equals("p2")){
                            caseAndBug.setCase_P3(value);
                        }
                        if(key.equals("P3") || key.equals("p3")){
                            caseAndBug.setCase_P4(value);
                        }
                    }
                }
                //新增bug数
                if(i == list.size()-2){
                    String bugs = objectList.get(5).toString();
                    String[] bugArray = bugs.split(",");
                    for(String singleBug:bugArray){
                        String[] strArr = singleBug.split(":");
                        String key = strArr[0].trim();
                        Integer value;
                        if(strArr[1].trim().equals("*")){
                            value = 0;
                        }else {
                            value = Integer.parseInt(strArr[1].trim());
                        }

                        if(key.equals("P0") || key.equals("p0")){
                            caseAndBug.setBug_P1(value);
                        }
                        if(key.equals("P1") || key.equals("p1")){
                            caseAndBug.setBug_P2(value);
                        }
                        if(key.equals("P2") || key.equals("p2")){
                            caseAndBug.setBug_P3(value);
                        }
                        if(key.equals("P3") || key.equals("p3")){
                            caseAndBug.setBug_P4(value);
                        }
                    }
                }
                //建议
                if(i == list.size()-1){
                    caseAndBug.setSuggest(objectList.get(5).toString());
                }
                //}
            }
        }
        List<NextWeek> nextWeeks = new ArrayList<>();
        NextWeek nextWeek = null;
        if(nextWeekList.size() > 0){
            for(int i=0;i<nextWeekList.size();i++){
                List<Object> objectList = nextWeekList.get(i);
                if(i>1){
                    nextWeek = new NextWeek();
                    //工作事项
                    int workType = 0;
                    String workTypeStr = objectList.get(0).toString();
                    if(workTypeStr.equals("用例编写")){
                        workType = 1;
                    }else if(workTypeStr.equals("测试执行")){
                        workType = 2;
                    }else if(workTypeStr.equals("技术改进")){
                        workType = 3;
                    }else if(workTypeStr.equals("其他")){
                        workType = 4;
                    }
                    nextWeek.setWorkType(workType);
                    //jiraid
                    nextWeek.setJiraId(objectList.get(1).toString());
                    //项目组
                    nextWeek.setProjectName(objectList.get(2).toString());
                    //内容
                    nextWeek.setContent(objectList.get(3).toString());
                    //预计提测时间
                    nextWeek.setYujiTestDate(StringUtils.convertStringToDate(objectList.get(8).toString()));
                    //预计完成时间
                    nextWeek.setYujiDoneDate(StringUtils.convertStringToDate(objectList.get(9).toString()));
                    //备注
                    nextWeek.setOtherDesc(objectList.get(10).toString());

                    nextWeeks.add(nextWeek);
                }
            }
        }
        //获取汇报者id
        week.setCurrentWeekList(currentWeekList);
        week.setCaseAndBug(caseAndBug);
        week.setNextWeekList(nextWeeks);

        return week;
    }


    /**
     * 获取所有的周信息
     * @param week    周列表
     * @param currentPage 当前页面
     * @param pageSize 每页显示数据条数
     * @return pageInfo对象
     * @throws Exception 错误
     */
    @Override
    public PageInfo<Week> getAllWeekList(Week week, Integer currentPage, Integer pageSize) throws Exception {
        PageHelper.startPage(currentPage,pageSize); //设置分页开始页面，和显示条数
        List<Week> weekList = weekMapper.selectAllWeek(week);
        PageInfo<Week> pageInfo = new PageInfo<Week>(weekList);
        return pageInfo;
    }


//    /**
//     * 根据周id获取周报信息
//     * @param week 周
//     * @return  周报信息
//     * @throws Exception 错误
//     */
//    @Override
//    public Week getWeeklyById(Week week) throws Exception {
//
//        //查询出该周的信息
//        Week aweek = weekMapper.selectWeeklyById(week);
//        //查询出该周对应的所有人员的周报列表
//        List<CurrentWeek> details = weekMapper.selectWeeklyDetailById(week);
//        //查询出该周对应的所有人员的测试用例和bug列表等
//        List<CaseAndBug> caseAndBugList = weekMapper.selectWeekCaseAndBugsById(week);
//
//        if(week != null){
//            aweek.setStartDate(DateUtils.formatDateToString(week.getStartTime()));
//            aweek.setEndDate(DateUtils.formatDateToString(week.getEndTime()));
//            if(details.size() > 0){
//                aweek.setInfoList(details);
//            }
//            if(caseAndBugList.size() > 0){
//                aweek.setCaseAndBugList(caseAndBugList);
//            }
//
//        }
//        return  aweek;
//    }

    /**
     * 获取所有的周报列表
     * @return 所有的周报列表
     * @throws Exception 错误
     */
    @Override
    public List<Week> getAllWeek() throws Exception {
        List<Week> weeklies = weekMapper.selectAllWeek(new Week());
        return weeklies;
    }

    /**
     * 根据周的id以及用户信息查询出该用户的对应周的周报详情
     * @param week  周信息和用户信息
     * @return  周报信息
     * @throws Exception 错误
     */
    @Override
    public Week getWeekForSomeOne(Week week) throws Exception {
        Week aweek = new Week();
        //周数据查询
        if(week.getSelectWeek() != null && week.getSelectWeek() > 0){
            //1、根据周id和用户id查询出对应的的关联id
            List<Integer> weekUserIds = weekMapper.selectWeekUserId(week);
            if(weekUserIds.size() > 0 ){
                //设置多个关联id
                week.setWeekUserIds(weekUserIds);
                //根据周报id查询出该周的信息
                aweek =  weekMapper.selectWeekly(week);
                //根据周id和用户id查询出对应的的关联id查询出该周下所有的用户本周周报信息
                List<CurrentWeek> currentWeeks = weekMapper.selectCurrentWeek(week);
                //根据周报id和用户id查询出该周某个人的用例和bug信息
                List<CaseAndBug> caseAndBugList = weekMapper.selectCaseAndBug(week);
                //根据周报id和用户的id查询出该周某个人的下周计划信息
                List<NextWeek> nextWeeks = weekMapper.selectNextWeek(week);

                if(aweek != null){
                    if(currentWeeks.size() > 0){
                        aweek.setCurrentWeekList(currentWeeks);
                    }
                    if(caseAndBugList != null){
                        aweek.setCaseAndBugList(caseAndBugList);
                    }
                    if(nextWeeks.size() > 0){
                        aweek.setNextWeekList(nextWeeks);
                    }
                }
            }
        }
        return aweek;
    }

    /**
     * 根据周的id，查询出该周下所有已提交周报的用户id
     * @param selectWeek  周id
     * @return  已提交周报的用户列表
     * @throws Exception 错误
     */
    @Override
    public Map<Integer, String> getUserListByWeekId(Integer selectWeek) throws Exception {
        Map<Integer, String> map = new HashMap<>();
        //根据id查询所有的用例和bug列表
        List<Week> weeks = weekMapper.selectUserListByWeekId(selectWeek);
        //当查询结果不为空
        if(weeks.size() > 0){
            for(Week week:weeks){
                //将用例和bug列表中的的汇报者id和汇报者名字放入map中
                map.put(week.getUserId(),week.getUserName());
            }
        }
        return map;
    }

    /**
     * 根据月份获取该月上传周报的用户列表
     * @param month 月份
     * @return 该月上传周报的用户列表
     * @throws Exception 错误
     */
    @Override
    public Map<Integer, String> getUserListByMonth(Integer month) throws Exception {
        Map<Integer, String> map = new HashMap<>();
        //根据id查询所有的用例和bug列表
        List<Week> weeks = weekMapper.selectUserListByMonth(month);
        //当查询结果不为空
        if(weeks.size() > 0){
            for(Week week:weeks){
                //将用例和bug列表中的的汇报者id和汇报者名字放入map中
                map.put(week.getUserId(),week.getUserName());
            }
        }
        return map;
    }




    /**
     * 根据用户传递的查询条件，获取周报列表
     * @param week  用户传递的查询条件
     * @return  周报列表
     * @throws Exception    错误
     */
    @Override
    public List<Week> getWeeklyList(Week week) throws Exception {
        List<Week> weeklyList = new ArrayList<>();
        //周数据查询
        if(week.getSelectWeek() != null && week.getSelectWeek() > 0){
            //1、根据周id和用户id查询出对应的的关联id
            List<Integer> weekUserIds = weekMapper.selectWeekUserId(week);
            if(weekUserIds.size() > 0 ){
                //设置多个关联id
                week.setWeekUserIds(weekUserIds);

                //根据周报id查询出该周的信息
                Week aweek =  weekMapper.selectWeekly(week);
                //根据周id和用户id查询出对应的的关联id查询出该周下所有的用户本周周报信息
                List<CurrentWeek> currentWeeks = weekMapper.selectCurrentWeek(week);
                //根据周报id和用户id查询出该周某个人的用例和bug信息
                List<CaseAndBug> caseAndBugList = weekMapper.selectCaseAndBug(week);
                //根据周报id和用户的id查询出该周某个人的下周计划信息
                List<NextWeek> nextWeeks = weekMapper.selectNextWeek(week);

                if(aweek != null){
                    if(currentWeeks.size() > 0){
                        aweek.setCurrentWeekList(currentWeeks);
                    }
                    if(caseAndBugList != null){
                        aweek.setCaseAndBugList(caseAndBugList);
                    }
                    if(nextWeeks.size() > 0){
                        aweek.setNextWeekList(nextWeeks);
                    }
                }
                weeklyList.add(aweek);
            }
        }
        //月数据统计
        if(week.getSelectMonth() != null && week.getSelectMonth() > 0){
            //首先查询出月对应的周列表
            List<Integer> weekIds = weekMapper.selectWeekId(week);
            if(weekIds.size() > 0){
                for(Integer id:weekIds){
                    //将id设置为查询的周id
                    week.setSelectWeek(id);
                    //1、根据周id和用户id查询出对应的的关联id
                    List<Integer> weekUserIds = weekMapper.selectWeekUserId(week);
                    if(weekUserIds.size() > 0 ){
                        //设置多个关联id
                        week.setWeekUserIds(weekUserIds);
                        //根据周报id查询出该周的信息
                        Week aweek =  weekMapper.selectWeekly(week);
                        //根据周id和用户id查询出对应的的关联id查询出该周下所有的用户本周周报信息
                        List<CurrentWeek> currentWeeks = weekMapper.selectCurrentWeek(week);
                        //根据周报id和用户id查询出该周某个人的用例和bug信息
                        List<CaseAndBug> caseAndBugList = weekMapper.selectCaseAndBug(week);
                        //根据周报id和用户的id查询出该周某个人的下周计划信息
                        List<NextWeek> nextWeeks = weekMapper.selectNextWeek(week);

                        if(aweek != null){
                            if(currentWeeks.size() > 0){
                                aweek.setCurrentWeekList(currentWeeks);
                            }
                            if(caseAndBugList != null){
                                aweek.setCaseAndBugList(caseAndBugList);
                            }
                            if(nextWeeks.size() > 0){
                                aweek.setNextWeekList(nextWeeks);
                            }
                        }
                        weeklyList.add(aweek);
                    }
                }
             }
         }
        return  weeklyList;
    }


    /**
     * 根据选择的多个要对比的周id查询出包含的用户列表
     * @param weeklyIdList  周id
     * @return 包含的用户列表
     * @throws Exception 错误
     */
    @Override
    public Map<Integer, String> getUserListByWeekList(List<Integer> weeklyIdList) throws Exception {
        Map<Integer, String> map = new HashMap<>();
        //根据id查询所有的用户列表
        List<Week> weeks = weekMapper.selectUserListByWeekList(weeklyIdList);
        //当查询结果不为空
        if(weeks.size() > 0){
            for(Week week:weeks){
                //将用户id和用户名字放入map中
                map.put(week.getUserId(),week.getUserName());
            }
        }
        return map;
    }

    /**
     * 根据月份，查询出该月下所有已提交周报的用户id
     * @param monthList  月
     * @return  已提交周报的用户列表
     * @throws Exception 错误
     */
    @Override
    public Map<Integer, String> getUserListByMonthList(List<Integer> monthList) throws Exception {
        Map<Integer, String> map = new HashMap<>();
        //根据id查询所有的用户列表
        List<Week> weeks = weekMapper.selectUserListByMonthList(monthList);
        //当查询结果不为空
        if(weeks.size() > 0){
            for(Week week:weeks){
                //将用户id和用户名字放入map中
                map.put(week.getUserId(),week.getUserName());
            }
        }
        return map;
    }


    /**
     * 根据查询的周列表和用户id，查询某用户对应的周记录信息
     * @param week    根据查询的周列表和用户id
     * @return  查询某用户对应的周记录信息
     * @throws Exception 错误
     */
    @Override
    public List<Compare> getCompareDataWeek(Week week) throws Exception {

        List<Compare> compareList = new ArrayList<>();
        Compare compare = null;
        if(week.getSelectWeekFrom() > 0 && week.getSelectWeekTo() > 0){
            for(int i=0;i<2;i++){
                if(i == 0){
                    week.setSelectWeek(week.getSelectWeekFrom());
                }
                if(i == 1){
                    week.setSelectWeek(week.getSelectWeekTo());
                }
                compare = new Compare();
                //根据周的id查询周信息
                Week aweek = weekMapper.selectWeekly(week);
                //根据周id和用户id查询出对应的关联id
                List<Integer> weekUserIdList = weekMapper.selectWeekUserId(week);
                //查询对应周中的对应用户的总预计时间和实际时间
                CurrentWeek currentWeek = weekMapper.selectCurrentWeekCompare(weekUserIdList);
                //查询对应周中的对应用户的总bug和总用例数
                CaseAndBug caseAndBug = weekMapper.selectCaseAndBugCompare(weekUserIdList);
                //设置周信息
                if(aweek != null){
                    compare.setCompareWeek(DateUtils.formatDateToString(aweek.getStartDate())+"~"+DateUtils.formatDateToString(aweek.getEndDate()));
                    compare.setUserId(week.getSelectUser());
                }
                if(currentWeek != null){
                    compare.setTotalEstimatedTime(currentWeek.getTotalEstimatedTime());
                    compare.setTotalRealTime(currentWeek.getTotalRealTime());
                }
                if(caseAndBug != null){
                    compare.setTotalCase(caseAndBug.getTotalCase());
                    compare.setTotalBug(caseAndBug.getTotalBug());
                }

                compareList.add(compare);
            }
        }
        return  compareList;
    }

    /**
     * 根据查询的月列表和用户id，查询某用户对应的月记录信息
     * @param week    根据查询的月列表和用户id
     * @return  查询某用户对应的月记录信息
     * @throws Exception 错误
     */
    @Override
    public List<Compare> getCompareDataMonth(Week week) throws Exception {
        List<Compare> compareList = new ArrayList<>();
        Compare compare = null;
        if(week.getSelectMonthFrom() > 0 && week.getSelectMonthTo() > 0){
            for(int i=0;i<2;i++){
                List<Integer> weekUserIdList = null;
                compare = new Compare();
                if(i == 0){
                    week.setSelectMonth(week.getSelectMonthFrom());
                    weekUserIdList = weekMapper.selectWeekUserIdByMonth(week);
                    compare.setCompareMonth(week.getSelectMonthFrom());
                }
                if(i == 1){
                    week.setSelectMonth(week.getSelectMonthTo());
                    weekUserIdList = weekMapper.selectWeekUserIdByMonth(week);
                    compare.setCompareMonth(week.getSelectMonthTo());
                }
                //查询对应周中的对应用户的总预计时间和实际时间
                CurrentWeek currentWeek = weekMapper.selectCurrentWeekCompare(weekUserIdList);
                //查询对应周中的对应用户的总bug和总用例数
                CaseAndBug caseAndBug = weekMapper.selectCaseAndBugCompare(weekUserIdList);
                //设置周信息
                compare.setUserId(week.getSelectUser());

                if(currentWeek != null){
                    compare.setTotalEstimatedTime(currentWeek.getTotalEstimatedTime());
                    compare.setTotalRealTime(currentWeek.getTotalRealTime());
                }
                if(caseAndBug != null){
                    compare.setTotalCase(caseAndBug.getTotalCase());
                    compare.setTotalBug(caseAndBug.getTotalBug());
                }

                compareList.add(compare);
            }
        }
        return  compareList;
    }
}

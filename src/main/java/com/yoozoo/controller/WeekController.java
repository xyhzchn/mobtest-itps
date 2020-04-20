package com.yoozoo.controller;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import com.github.pagehelper.PageInfo;
import com.sun.javafx.sg.prism.NGShape;
import com.yoozoo.bean.Compare;
import com.yoozoo.bean.User;
import com.yoozoo.bean.Week;
import com.yoozoo.service.WeekService;
import com.yoozoo.util.DateUtils;
import com.yoozoo.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;


/**
 * 周报业务相关控制类
 * Created by guoxx on 2018/11/26.
 */
@Controller
@RequestMapping("/week")
@SessionAttributes("userInfo")
public class WeekController {

    @Autowired
    private WeekService weekService;

    @RequestMapping(value = "/uploadFile")
    public String uploadFile(HttpServletRequest request, @ModelAttribute("userInfo") User user)throws Exception{
        //获取上传的文件
        MultipartHttpServletRequest multipart = (MultipartHttpServletRequest) request;
        MultipartFile file = multipart.getFile("myfiles");
        InputStream in = file.getInputStream();
        //数据导入
        weekService.importExcelInfo(in,file,user.getUserId());
        in.close();
        return "redirect:/week/getAllWeek";
    }
    /**
     * 查询所有的周信息
     * @param week 周信息
     * @param user 用户信息
     * @param currentPage 当前页
     * @param pageSize 每页显示条数
     * @return 周信息列表
     * @throws Exception 错误
     */
    @RequestMapping("/getAllWeek")
    public ModelAndView getAllWeek(Week week, @ModelAttribute("userInfo") User user,
                                      @RequestParam(required=true,defaultValue="1") Integer currentPage,
                                      @RequestParam(required=false,defaultValue="10") Integer pageSize)throws Exception{

        ModelAndView mav = new ModelAndView();
        PageInfo<Week> pageInfo = weekService.getAllWeekList(week,currentPage,pageSize);
        mav.addObject("page",pageInfo);
        mav.setViewName("weekList");

        return mav;
    }

    /**
     * 获取某一周某个用户的周报详情
     * @param user  用户信息
     * @param weekId    周id
     * @return  mav
     */
    @RequestMapping("/getWeeklyDetail")
    public ModelAndView getWeeklyDetail( @ModelAttribute("userInfo") User user,Integer weekId)throws Exception{
        ModelAndView mav = new ModelAndView();
        //设置查询条件
        Week aweek = new Week();
        aweek.setSelectWeek(weekId);
        aweek.setSelectUser(user.getUserId());
        //查询
        Week weekly = weekService.getWeekForSomeOne(aweek);
        if(weekly != null){
            mav.addObject("weekly",weekly);
        }
        mav.setViewName("weekDetail");
        return mav;
    }

    /**
     * 获取周报详情信息
     * @param week 周报
     * @return mav
     */
    @RequestMapping("/getWeeklyCount")
    public ModelAndView getWeeklyCount(Week week)throws Exception{
        ModelAndView mav = new ModelAndView();
        //获取所有的周报区间并组成map
        List<Week> weekList = weekService.getAllWeek();
        Map<Integer,String> weekMap = new HashMap<>();
        if(weekList.size() > 0){
            for(Week singleWeek: weekList){
                weekMap.put(singleWeek.getWeekId(), DateUtils.formatDateToString(singleWeek.getStartDate())+" ~ "+ DateUtils.formatDateToString(singleWeek.getEndDate()));
            }
        }
        mav.addObject("weekMap",weekMap);
        //获取周报列表
        if((week.getSelectWeek() != null && week.getSelectWeek() > 0) ||(week.getSelectMonth() != null && week.getSelectMonth() > 0)){
            List<Week> weeklyList = weekService.getWeeklyList(week);
            if(weeklyList.size() > 0){
                if(weeklyList.size() == 1){
                    mav.addObject("weekly",weeklyList.get(0));
                }else {
                    mav.addObject("weeklyList",weeklyList);
                    mav.addObject("month",week.getSelectMonth());
                }
            }
        }
        mav.setViewName("weekCount");
        return mav;
    }

    /**
     * 根据周id获取该周上传周报的用户列表
     * @param selectWeek 周
     * @return 该周上传周报的用户列表
     */
    @RequestMapping("/getUserListByWeekId")
    @ResponseBody
    public Map<Integer,String> getUserListByWeekId(Integer selectWeek)throws Exception{

        Map<Integer,String> userMap = new HashMap<>();
            //根据周报id查询该周的所有已提交周报的用户
            if(selectWeek != null){
                userMap = weekService.getUserListByWeekId(selectWeek);
            }
        return userMap;
    }

    /**
     * 根据月份获取该月上传周报的用户列表
     * @param selectMonth 月份
     * @return 该月上传周报的用户列表
     * @throws Exception 错误
     */
    @RequestMapping("/getUserListByMonth")
    @ResponseBody
    public Map<Integer,String> getUserListByMonth(Integer selectMonth)throws Exception{
        Map<Integer,String> userMap = new HashMap<>();
        //根据月份查询该月的所有已提交周报的用户
        if(selectMonth != null){
            userMap = weekService.getUserListByMonth(selectMonth);
        }
        return userMap;
    }


    /**
     * 根据周id获取该周上传周报的用户列表
     * @param selectWeekStr 周
     * @return 该周上传周报的用户列表
     */
    @RequestMapping("/getUserListByWeekList")
    @ResponseBody
    public Map<Integer,String> getUserListByWeekList(String selectWeekStr)throws Exception{
        Map<Integer,String> userMap = new HashMap<>();
        //根据周报id查询该周的所有已提交周报的用户
        userMap = weekService.getUserListByWeekList(StringUtils.convertStringToList(selectWeekStr));
        return userMap;
    }

    /**
     * 根据周id获取该周上传周报的用户列表
     * @param selectMonthStr 周
     * @return 该周上传周报的用户列表
     */
    @RequestMapping("/getUserListByMonthList")
    @ResponseBody
    public Map<Integer,String> getUserListByMonthList(String selectMonthStr)throws Exception{
        Map<Integer,String> userMap = new HashMap<>();
        //根据周报id查询该周的所有已提交周报的用户
        userMap = weekService.getUserListByMonthList(StringUtils.convertStringToList(selectMonthStr));
        return userMap;
    }

    /**
     * 获取周报详情信息
     * @param week 周报
     * @return mav
     */
    @RequestMapping("/getWeeklyCompare")
    public ModelAndView getWeeklyCompare(Week week)throws Exception{
        ModelAndView mav = new ModelAndView();
        //获取所有的周报区间并组成map
        List<Week> weeklyList = weekService.getAllWeek();
        Map<Integer,String> weeklyMap = new HashMap<>();
        if(weeklyList.size() > 0){
            for(Week singleWeek:weeklyList){
                weeklyMap.put(singleWeek.getWeekId(),DateUtils.formatDateToString(singleWeek.getStartDate())+" ~ "+DateUtils.formatDateToString(singleWeek.getEndDate()));
            }
        }
        mav.addObject("weeklyMap",weeklyMap);
        mav.setViewName("weekCompare");
        return mav;
    }


    /**
     * 获取周报详情信息
     * @param week 周报
     * @return mav
     */
    @RequestMapping("/getWeekCompare")
    public @ResponseBody Map<Object,Object> getWeekCompare(@RequestBody Week week)throws Exception{
        //获取所有的周报区间并组成map
        Map<Object,Object> weeklyMap = new HashMap<>();

        List<Compare> compareWeekData = weekService.getCompareDataWeek(week);

        if(compareWeekData != null && compareWeekData.size() > 0){
            int count = compareWeekData.size();
            String[] xAxis = new String[count];
            Float[] estimatedTimeData = new Float[count];
            Float[] realTimeData =  new Float[count];
            Integer[] caseData = new Integer[count];
            Integer[] bugData = new Integer[count];
            for(int i=0;i<count;i++){
                xAxis[i] = compareWeekData.get(i).getCompareWeek();
                estimatedTimeData[i] = compareWeekData.get(i).getTotalEstimatedTime();
                realTimeData[i] = compareWeekData.get(i).getTotalRealTime();
                caseData[i] = compareWeekData.get(i).getTotalCase();
                bugData[i] = compareWeekData.get(i).getTotalBug();
            }
            weeklyMap.put("xAxis",xAxis);
            weeklyMap.put("estimatedTimeData",estimatedTimeData);
            weeklyMap.put("realTimeData",realTimeData);
            weeklyMap.put("caseData",caseData);
            weeklyMap.put("bugData",bugData);
        }
        return  weeklyMap;
    }


    /**
     * 获取月详情信息
     * @param week 周报
     * @return mav
     */
    @RequestMapping("/getMonthCompare")
    public @ResponseBody Map<Object,Object> getMonthCompare(@RequestBody Week week)throws Exception{
        //获取所有的周报区间并组成map
        Map<Object,Object> weeklyMap = new HashMap<>();

        List<Compare> compareWeekData = weekService.getCompareDataMonth(week);

        if(compareWeekData != null && compareWeekData.size() > 0){
            int count = compareWeekData.size();
            String[] xAxis = new String[count];
            Float[] estimatedTimeData = new Float[count];
            Float[] realTimeData =  new Float[count];
            Integer[] caseData = new Integer[count];
            Integer[] bugData = new Integer[count];
            for(int i=0;i<count;i++){
                xAxis[i] = compareWeekData.get(i).getCompareMonth().toString()+"月";
                estimatedTimeData[i] = compareWeekData.get(i).getTotalEstimatedTime();
                realTimeData[i] = compareWeekData.get(i).getTotalRealTime();
                caseData[i] = compareWeekData.get(i).getTotalCase();
                bugData[i] = compareWeekData.get(i).getTotalBug();
            }
            weeklyMap.put("xAxis",xAxis);
            weeklyMap.put("estimatedTimeData",estimatedTimeData);
            weeklyMap.put("realTimeData",realTimeData);
            weeklyMap.put("caseData",caseData);
            weeklyMap.put("bugData",bugData);
        }
        return  weeklyMap;
    }
}

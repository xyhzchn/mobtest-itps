package com.yoozoo.controller;

import com.yoozoo.bean.Api;
import com.yoozoo.bean.Param;
import com.yoozoo.service.ApiService;
import com.yoozoo.service.ParamService;
import com.yoozoo.util.CommonParam;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.json.Json;
import javax.json.stream.JsonParser;
import java.sql.Timestamp;
import java.util.*;

/**
 * 参数化相关控制类
 * Created by guoxx on 2018/9/4.
 */
@Controller
@RequestMapping("/param")
public class ParamController {

    @Autowired
    private ApiService apiService;

    @Autowired
    private ParamService paramService;
    /**
     * 获取所有的参数列表
     * @return mav
     */
    @RequestMapping("/getParams")
    public ModelAndView getParams(){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("paramList");
        return mav;
    }

    @RequestMapping("/toAddParamPage")
    public ModelAndView toAddParamPage(){
        ModelAndView mav = new ModelAndView();
        mav.addObject("flag","add");
        mav.setViewName("addParam");
        return mav;
    }

    @RequestMapping("/addParam")
    public String addParam(Param param)throws Exception{

        int paramType = param.getParamType();
        if(paramType > 0){
            switch (paramType){
                case CommonParam.PARAM_TYPE_BY_KEYVALUE:
                    param.setParamCondition("nothing");
                    param.setParamValue(param.getParamValues());break;
                case CommonParam.PARAM_TYPE_BY_SQL:
                    param.setParamCondition(param.getParamSqlStr());
                    param.setParamValue("nothing");break;
                case CommonParam.PARAM_TYPE_BY_API:
                    String apiKeyAndValue = param.getApiKey()+"&"+param.getApiValue();
                    param.setParamCondition(apiKeyAndValue);
                    param.setParamValue("nothing");break;
                case CommonParam.PARAM_TYPE_BY_FILE:
                    param.setParamCondition(param.getParamFile().getAbsolutePath());
                    param.setParamValue("nothing");break;
            }
        }
        param.setApiId(0);
        //设置删除状态
        param.setIsDelete(CommonParam.NOT_DELETE);
        //设置创建和修改时间
        Date date = new Date();
        Timestamp now = new Timestamp(date.getTime());
        param.setCreateTime(now);
        param.setUpdateTime(now);

        int num = paramService.addOneParam(param);
        if (num > 0){
            return "redirect:/param/getParams";
        }else {
            return "error";
        }
    }

    /**
     * 根据用户传递过来的接口ID以及响应字段
     * @param apiId 接口ID
     * @param responseParamName 响应字段
     * @return 获取该响应字段的值
     * @throws Exception
     */
    public String getParamValuesByAPI(int apiId,String responseParamName)throws Exception{
        //定义获取到的参数值
        String paramValue = "";
        //根据接口ID获取到接口信息
        Api api = apiService.getOneApiById(apiId);
        if(api != null && api.getResponseBody() != null){
            //获取接口的返回值数据
            String responseBody = api.getResponseBody();
            //将返回值数据设置为json格式
            JSONObject responseBodyJson = new JSONObject(responseBody);
            //获取对应字段的值
            paramValue = responseBodyJson.get(responseParamName).toString();

        }else {
            System.out.println("接口未执行，无法获取该接口相关的返回值数据");
        }
       return paramValue;
    }
}

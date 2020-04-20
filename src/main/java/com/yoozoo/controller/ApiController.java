package com.yoozoo.controller;

import com.github.pagehelper.PageInfo;
import com.yoozoo.bean.Api;
import com.yoozoo.bean.User;
import com.yoozoo.service.ApiService;
import com.yoozoo.test.ExtentTestNGIReporterListener;
import com.yoozoo.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.testng.TestNG;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.*;

/**
 * 接口控制类
 * Created by guoxx on 2018/7/24.
 */
@Controller
@RequestMapping("/api")
@SessionAttributes("userInfo")
public class ApiController {

    @Autowired
    private ApiService apiService;

    /**
     * 获取所有接口列表，默认按照项目ID
     * @param api 接口
     * @param user  用户信息
     * @return  mav对象
     * @throws Exception 错误
     */
    @RequestMapping("/getAllApis")
    public ModelAndView getAllApis(Api api,@ModelAttribute("userInfo") User user,
                                   @RequestParam(required=true,defaultValue="1") Integer currentPage,
                                   @RequestParam(required=false,defaultValue="10") Integer pageSize)throws Exception{
        ModelAndView mav =  new ModelAndView();
        PageInfo<Api> pageInfo = apiService.getAllApiList(api,currentPage,pageSize);
        mav.addObject("itemId",api.getItemId());
        mav.addObject("page",pageInfo);
        mav.setViewName("apiList");
        return mav;
    }

    /**
     * 跳转到添加接口页面
     * @param itemId 项目id
     * @return ModelAndView
     * @throws Exception 错误
     */
    @RequestMapping("/toApiAddPage")
    public ModelAndView toApiAddPage(Integer itemId)throws Exception{
        ModelAndView mav = new ModelAndView();
        mav.addObject("itemId",itemId);
        mav.setViewName("addAPI");
        return mav;
    }
    /**
     * 添加接口
     * @param user session中的用户信息
     * @param api 接口信息
     * @return mav
     * @throws Exception 错误
     */
    @RequestMapping("/addApi")
    public @ResponseBody Map<String, Object> addApi(@ModelAttribute("userInfo")User user ,@RequestBody Api api)throws Exception{

        Map<String, Object> map = new HashMap<>();
        //设置创建者id和名称
        api.setCreator(user.getUserId());
        api.setCreatorName(user.getUserName());
        //设置创建时间
        Date date = new Date();
        Timestamp now = new Timestamp(date.getTime());
        api.setCreateTime(now);
        api.setUpdateTime(now);

        //当请求头为空时，设置默认的请求头
        if(api.getRequestHeader().equals("[]") ){
            if(api.getParamType() == CommonParam.PARAM_TYPE_FORMDATA){
                api.setRequestHeader("[{\"Content-Type\":\"application/x-www-form-urlencoded\"}]");
            }else if(api.getParamType() == CommonParam.PARAM_TYPE_JSON){
                api.setRequestHeader("[{\"Content-Type\":\"application/json;charset=utf-8\"}]");
            }
        }
        //请求头格式转换
        api.setRequestHeader(StringUtils.convertJsonStrByJsonArrayStr(api.getRequestHeader()));

        //请求体格式转换
        if(api.getParamType() == CommonParam.PARAM_TYPE_FORMDATA){
            api.setRequestOriginBody(StringUtils.convertJsonStrByJsonArrayStr(api.getRequestOriginBody()));
        }
        //若无数据解密，则请求数据的明文和密文一致；
        if(api.getDecodeType() == CommonParam.NO_BODY_DECODE){
            api.setRequestBody(api.getRequestOriginBody());
        }
        //设置默认未删除
        api.setIsDelete(CommonParam.NOT_DELETE);

        int num = apiService.addOneApi(api);

        if(num > 0){
          map.put("code","1");
        }else {
          map.put("code","0");
        }
        return map;
    }

    /**
     * 执行http请求
     * @param apiId 传入参数
     * @return mav
     * @throws Exception 错误
     */
    @RequestMapping("/executeApi")
    public String executeApi(Integer apiId,@ModelAttribute("userInfo")User user,Integer itemId)throws Exception{

        //新建一个报告监听类
        ExtentTestNGIReporterListener myListener = new ExtentTestNGIReporterListener();
        //创建TestNG对象
        TestNG tng = new TestNG();
        //新建一个suite
        XmlSuite suite = new XmlSuite();
        suite.setName("TmpSuite");
        //设置参数
        Map<String,String> map = new HashMap<>();
        map.put("apiId",apiId.toString());
        map.put("userId",user.getUserId().toString());
        suite.setParameters(map);
        //该suite中新增一个test
        XmlTest test = new XmlTest(suite);
        test.setName("TmpTest");
        //test标签下增加多个class
        List<XmlClass> classes = new ArrayList<XmlClass>();
        classes.add(new XmlClass("com.yoozoo.test.BaseTestCaseExe"));
        test.setXmlClasses(classes) ;
        //创建suite列表并添加已创建的suite
        List<XmlSuite> suites = new ArrayList<XmlSuite>();
        suites.add(suite);
        tng.setXmlSuites(suites);

        //添加监听
        tng.addListener(myListener);
        //执行
        tng.run();

        String str = "redirect:/api/getAllApis?itemId="+itemId;


//            //数据解密
//            int decodeType = singleApi.getDecodeType();   //获取需要解密的类型
//            //定义解密后的json数据
//            JSON requestDecodedData = new JSON();
//            JSON responseDecodedData = new JSON();
//            //排除不解密的情况
//            DecodeParams params = DecodeUtils.getDecodeParams(singleApi);
//            if(decodeType > 0){
//                if(decodeType == 1){        //请求数据解密
//                        requestDecodedData = DecodeUtils.inDecode(params);
//                }else{      //响应数据解密
//                        responseDecodedData = DecodeUtils.outDecode(params);
//                }
//            }
//            if(responseDecodedData != null){
//                //返回参数无误并且解密成功
//                if(responseDecodedData.get("success").equals(1) && responseDecodedData.get("passDes").equals(1)){
//                    //设置响应明文
//                    String responseBody = responseDecodedData.get("resultData").toString();
//                    singleApi.setResponseBody(responseBody);
//                }else {
//                    if(responseDecodedData.get("success").equals(0)){
//                        System.out.print("----------响应参数错误-----------");
//                    }
//                    if(responseDecodedData.get("passDes").equals(-1)){
//                        System.out.print("----------未经过解密操作-----------");
//                    }else {
//                        System.out.print("----------解密失败-----------");
//                    }
//                }
//            }
//
//            int num  = apiService.editOneApi(singleApi);
//            if(num>0){
//                str = "redirect:/api/getAllApis?itemId="+singleApi.getItemId();
//            }else {
//                str = "error";
//            }
//        }

        //4、根据检查点对接口进行检查

        //5、将检查结果放入到结果表

        //6、返回执行结果，和响应数据等数据，在列表中显示

        return str;
    }

    /**
     * 根据id删除某一个接口
     * @param api api信息
     * @return 重定向页面
     * @throws Exception 错误信息
     */
    @RequestMapping("/deleteApi")
    public String deleteApi(Api api,Integer itemId)throws Exception{
        System.out.print(api.getApiId());
        //获取当前时间
        Date date = new Date();
        Timestamp now = new Timestamp(date.getTime());
        api.setUpdateTime(now);
        //设置为已删除
        api.setIsDelete(CommonParam.DELETE);

        int num = apiService.deleteApi(api);
        if(num > 0){
            return "redirect:/api/getAllApis?itemId="+itemId;
        }else {
            return "error";
        }
    }

    /**
     * 获取接口详情
     * @param id 接口id
     * @return mav对象
     * @throws Exception 错误信息
     */
    @RequestMapping("/getApiDetail")
    public ModelAndView getApiDetail(Integer id)throws Exception{
        ModelAndView mav = new ModelAndView();
        Api api = apiService.getOneApiById(id);
        //设置请求头map
        mav.addObject("api",api);
        mav.setViewName("editAPI");

        return mav;
    }

    /**
     * 添加接口
     * @param user session中的用户信息
     * @param api 接口信息
     * @return mav
     * @throws Exception 错误
     */
    @RequestMapping("/editApi")
    public @ResponseBody Map<String, Object> editApi(@ModelAttribute("userInfo")User user ,@RequestBody Api api)throws Exception{

        Map<String, Object> map = new HashMap<>();
        //设置创建者id和名称
        api.setCreator(user.getUserId());
        api.setCreatorName(user.getUserName());
        //设置创建时间
        Date date = new Date();
        Timestamp now = new Timestamp(date.getTime());
        api.setCreateTime(now);
        api.setUpdateTime(now);
        //若无数据解密，则请求数据的明文和密文一致；
        if(api.getDecodeType() == CommonParam.NO_BODY_DECODE){
            api.setRequestBody(api.getRequestOriginBody());
        }
        if(api.getParamType() == CommonParam.PARAM_TYPE_JSON && api.getRequestHeader().equals("{\"Content-Type\":\"application/x-www-form-urlencoded\"}")){
            api.setRequestHeader("{\"Content-Type\":\"application/json;charset=utf-8\"}");
        }

        //设置默认未删除
        api.setIsDelete(CommonParam.NOT_DELETE);

        int num = apiService.editApiRequestInfo(api);

        if(num > 0){
            map.put("code","1");
        }else {
            map.put("code","0");
        }
        return map;
    }
}

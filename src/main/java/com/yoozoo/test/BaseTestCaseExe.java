package com.yoozoo.test;

import com.yoozoo.bean.*;
import com.yoozoo.service.ApiService;
import com.yoozoo.service.UserService;
import com.yoozoo.util.*;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.testng.Assert;
import org.testng.annotations.*;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.sql.Timestamp;
import java.util.*;

/**
 * 执行测试用例
 * Created by guoxx on 2018/9/6.
 */
@ContextConfiguration(locations = {"classpath*:config/spring-*.xml","classpath*:config/applicationContext-*.xml","classpath*:config/mybatis-*.xml"})
@WebAppConfiguration("src/main/resources")
public class BaseTestCaseExe extends AbstractTestNGSpringContextTests {

    @Autowired
    private ApiService apiService;
    @Autowired
    private UserService userService;
    //接口对象
    private Api singleApi;
    //定义一个接口检查点运行结果列表
    private List<RunLog> logList;
    //定义接口运行历史对象
    private ApiRunHistory history;
    //响应结果，包括响应头，响应体和状态码
    private JSONObject responseResult = null;
    //响应体
    private JSONObject responseBodyJson = null;
    //用例执行完毕后，影响数据条数
    private int count = 0;
    //定义flag
    public static boolean flag = true;
    //错误列表
    public static List<Error> errors = new ArrayList<Error>();

    //定义发件人
    public final String SEND_MAIL_FROM = "hanruochu@163.com";
    //定义收件人
    public String SEND_MAIL_TO = "";



    @Parameters("apiId")
    @BeforeClass(dependsOnMethods = "springTestContextPrepareTestInstance")
    public void beforeExecuteCase(String apiId)throws Exception{
//        Integer apiId_int = Integer.parseInt(apiId);
//        //1、根据接口id查询出接口的详细信息
//        Api singleApi = apiService.getOneApiById(apiId_int);
//        //如果接口不为空，
//        if(singleApi != null){
//            //查看该接口的请求数据是否需要参数化
//           if(singleApi.getRequestBody() != null){
////               String regex = "^\\$\\{(.*)\\}$";
////               Pattern p = Pattern.compile(regex);
////               Matcher m = p.matcher(singleApi.getRequestBody());
////               while (m.find()){
////                   String param = singleApi.getRequestBody().substring(m.start(),m.end());
////                   System.out.println(param);
////               }
//               String requestBodyStr = singleApi.getRequestBody();
//               JSONObject requestBodyJson = null;
//               String depParam = "";
//               //如果是json格式
//               if(singleApi.getParamType() == CommonParam.PARAM_TYPE_JSON){
//                   requestBodyJson = new JSONObject(requestBodyStr);
//               }else{
//                   JSONArray requestBodyArray = new JSONArray(requestBodyStr);
//                   for(int i=0;i<requestBodyArray.length();i++){
//                       requestBodyJson =(JSONObject) requestBodyArray.get(i);
//                   }
//               }
//               //遍历json中的key
//               Iterator<String> it = requestBodyJson.keys();
//               while (it.hasNext()){
//                   String key = it.next();
//                   String value = requestBodyJson.get(key).toString();
//                   //如果value中含有$符号，证明该参数需要参数化
//                   if(value.contains("$")){
//                       //截取${}中的值，根据该值去已创建的参数类列表中寻找
//                       depParam = value.substring(2,value.lastIndexOf("}"));
//                       //找到对应参数的值后，替换参数值
//                   }
//               }
//           }
//            //如果需要参数化，则根据参数化规则获得参数数据
//
//
//           //将参数化数据替换并保存数据库
//
//        }


        //如果需要参数化，参数化替换
    }

    @BeforeMethod
    public void beforeExecuteTest(){
        System.out.println("beforeExecuteTest-------------------------------");
    }

    @Parameters({"apiId","userId"})
    @Test
    public void executeTest(String apiId,String userId)throws Exception{
        //第一步：执行测试，获取相应结果
        singleApi = new Api();
        //根据apiId获取某条接口信息
        singleApi = apiService.getOneApiById(Integer.parseInt(apiId));

        if(singleApi != null){
            //设置请求时间
            singleApi.setRequestTime(new Timestamp(new Date().getTime()));
            //get请求
            if(singleApi.getMethod().equals(CommonParam.GET)){
                //设置httpgeturl
                String httpGetUrl = "http://"+singleApi.getApiHost()+singleApi.getUrl();
                System.out.println("请求地址为： "+httpGetUrl);
                //2、根据接口信息模拟发送http请求并获取响应数据
                responseResult =  HttpClientUtil.get(httpGetUrl,singleApi.getRequestHeader());
            }else {
                String requestBodyStr = "";
                int paramType = singleApi.getParamType();
                if(paramType == CommonParam.PARAM_TYPE_FORMDATA){
                    requestBodyStr = StringUtils.convertStringParamter(singleApi.getRequestOriginBody());
                }
                if(paramType == CommonParam.PARAM_TYPE_JSON){
                    requestBodyStr = singleApi.getRequestOriginBody();
                }


                String httpGetUrl = "http://"+singleApi.getApiHost()+singleApi.getUrl();
                responseResult = HttpClientUtil.post(requestBodyStr,singleApi.getRequestHeader(),httpGetUrl);
            }
        }

        //第二步：根据相应结果，增加断言判断
        //检查点验证
        List<Point> points = singleApi.getPoints();
        if(points.size() > 0){
            //获得响应体
            responseBodyJson = new JSONObject(responseResult.get("responseOriginBody").toString());
            //遍历响应体中的所有key
            List<String> keys = new ArrayList<>();
            keys = decodeJSONObject(responseBodyJson,keys);
            //遍历响应体中的所有key和value,并组成一个新的json对象
            JSONObject bodyJson = new JSONObject();
            bodyJson = decodeJSONObject(responseBodyJson,bodyJson);
            logList = new ArrayList<>();
            for(Point p:points){
                RunLog log = new RunLog();
                switch (p.getPointType()){
                    case CommonParam.NOT_NULL_CHECK:    //不为空检查点
                        log.setCheckType(CommonParam.NOT_NULL_CHECK);
                        if(p.getIsOpen() == CommonParam.OPEN){
                            try{
                                Assert.assertNotNull(responseResult.get("responseOriginBody"));
                                log.setRunResult(CommonParam.PASS);
                                log.setReason(CommonParam.STR_NULL);
                            }catch(AssertionError e){
                                errors.add(e);
                                flag = false;
                                log.setRunResult(CommonParam.FAIL);
                                log.setReason(StringUtils.convertStringStackTrace(e));
                            }
                        }
                        break;
                    case CommonParam.CONTAIN_CHECK:     //包含检查点
                        log.setCheckType(CommonParam.CONTAIN_CHECK);
                        try{
                            Assert.assertTrue(keys.contains(p.getCheckKey()));
                            log.setRunResult(CommonParam.PASS);
                            log.setReason(CommonParam.STR_NULL);
                        }catch(AssertionError e){
                            errors.add(e);
                            flag = false;
                            log.setRunResult(CommonParam.FAIL);
                            log.setReason(StringUtils.convertStringStackTrace(e));
                        }
                        break;
                    case CommonParam.NOT_CONTAIN_CHECK:     //不包含检查点
                        log.setCheckType(CommonParam.NOT_CONTAIN_CHECK);
                        try{
                            Assert.assertFalse(keys.contains(p.getCheckKey()));
                            log.setRunResult(CommonParam.PASS);
                            log.setReason(CommonParam.STR_NULL);
                        }catch(AssertionError e){
                            errors.add(e);
                            flag = false;
                            log.setRunResult(CommonParam.FAIL);
                            log.setReason(StringUtils.convertStringStackTrace(e));
                        }
                        break;
                    case CommonParam.NUMBER_CHECK:      //数值检查点
                        log.setCheckType(CommonParam.NUMBER_CHECK);
                        //字段的值
                        int value1  = Integer.parseInt(bodyJson.get(p.getCheckKey()).toString());
                        //表达式
                        int expression = p.getExpression();
                        //对比的值
                        int value2 = Integer.parseInt(p.getCheckValue());
                            switch (expression){
                                case CommonParam.EQ:    //等于
                                    try{
                                        Assert.assertTrue(value1 == value2);
                                        log.setRunResult(CommonParam.PASS);
                                        log.setReason(CommonParam.STR_NULL);
                                    }catch(AssertionError e){
                                        errors.add(e);
                                        flag = false;
                                        log.setRunResult(CommonParam.FAIL);
                                        log.setReason(StringUtils.convertStringStackTrace(e));
                                    }
                                    break;
                                case CommonParam.GT:    //大于
                                    try{
                                        Assert.assertTrue(value1 > value2);
                                        log.setRunResult(CommonParam.PASS);
                                        log.setReason(CommonParam.STR_NULL);
                                    }catch(AssertionError e){
                                        errors.add(e);
                                        flag = false;
                                        log.setRunResult(CommonParam.FAIL);
                                        log.setReason(StringUtils.convertStringStackTrace(e));
                                    }
                                    break;
                                case CommonParam.LT:    //小于
                                    try{
                                        Assert.assertTrue(value1 < value2);
                                        log.setRunResult(CommonParam.PASS);
                                        log.setReason(CommonParam.STR_NULL);
                                    }catch(AssertionError e){
                                        errors.add(e);
                                        flag = false;
                                        log.setRunResult(CommonParam.FAIL);
                                        log.setReason(StringUtils.convertStringStackTrace(e));
                                    }
                                    break;
                                case CommonParam.GE:    //大于等于
                                    try{
                                        Assert.assertTrue(value1 >= value2);
                                        log.setRunResult(CommonParam.PASS);
                                        log.setReason(CommonParam.STR_NULL);
                                    }catch(AssertionError e){
                                        errors.add(e);
                                        flag = false;
                                        log.setRunResult(CommonParam.FAIL);
                                        log.setReason(StringUtils.convertStringStackTrace(e));
                                    }
                                    break;
                                case CommonParam.LE:    //小于等于
                                    try{
                                        Assert.assertTrue(value1 <= value2);
                                        log.setRunResult(CommonParam.PASS);
                                        log.setReason(CommonParam.STR_NULL);
                                    }catch(AssertionError e){
                                        errors.add(e);
                                        flag = false;
                                        log.setRunResult(CommonParam.FAIL);
                                        log.setReason(StringUtils.convertStringStackTrace(e));
                                    }
                                    break;
                                case CommonParam.NE:    //不等于
                                    try{
                                        Assert.assertTrue(value1 != value2);
                                        log.setRunResult(CommonParam.PASS);
                                        log.setReason(CommonParam.STR_NULL);
                                    }catch(AssertionError e){
                                        errors.add(e);
                                        flag = false;
                                        log.setRunResult(CommonParam.FAIL);
                                        log.setReason(StringUtils.convertStringStackTrace(e));
                                    }
                                    break;
                            }

                        break;
                    case CommonParam.STR_CHECK:     //文本对比
                        log.setCheckType(CommonParam.STR_CHECK);
                        String value = bodyJson.get(p.getCheckKey()).toString();
                        try{
                            Assert.assertEquals(value,p.getCheckValue());
                            log.setRunResult(CommonParam.PASS);
                            log.setReason(CommonParam.STR_NULL);
                        }catch(AssertionError e){
                            errors.add(e);
                            flag = false;
                            log.setRunResult(CommonParam.FAIL);
                            log.setReason(StringUtils.convertStringStackTrace(e));
                        }
                        break;
                }
                log.setApiId(singleApi.getApiId());
                log.setCreateTime(new Timestamp(new Date().getTime()));
                logList.add(log);
            }
        }
        //第三步：将断言结果，响应结果，运行历史等添加到数据库
            //定义运行历史对象
            history = new ApiRunHistory();
            history.setRunUser(Integer.parseInt(userId));
            history.setApiId(singleApi.getApiId());
            history.setCreateTime(new Timestamp(new Date().getTime()));
        //获取api对象
        if(responseResult != null){
            //设置响应头
            if(responseResult.get("responseHeader") != null){
                singleApi.setResponseHeader(responseResult.get("responseHeader").toString());
            }else {
                singleApi.setResponseHeader("");
            }
            //设置响应体
            if(responseResult.get("responseOriginBody") != null){
                singleApi.setResponseOriginBody(responseResult.get("responseOriginBody").toString());
                if(singleApi.getDecodeType() == CommonParam.NO_BODY_DECODE){
                    singleApi.setResponseBody(responseResult.get("responseOriginBody").toString());
                }
            }else {
                singleApi.setResponseOriginBody("");
            }
            //设置状态码
            if(responseResult.get("status") != null){
                singleApi.setStatusCode(responseResult.get("status").toString());
            }else {
                singleApi.setStatusCode("");
            }
            //设置响应时间
            singleApi.setResponseTime(new Timestamp(new Date().getTime()));

            //修改响应结果
            count = apiService.editOneApiAfterTest(singleApi,logList,history);
        }
            //添加一条用例历史；
//            int num  = apiService.editOneApi(singleApi);

            //数据解密
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

//            if(num>0){
//                str = "redirect:/api/getAllApis?itemId="+singleApi.getItemId();
//            }else {
//                str = "error";
//            }
    }


    @AfterMethod
    public void afterExecuteTest()throws Exception{
        if(count > 0){
            //0.1 确定连接位置
            Properties props = new Properties();
            //获取163邮箱smtp服务器的地址，
            props.setProperty("mail.host", "smtp.163.com");
            //是否进行权限验证。
            props.setProperty("mail.smtp.auth", "true");


            //0.2确定权限（账号和密码）
            Authenticator authenticator = new Authenticator() {
                @Override
                public PasswordAuthentication getPasswordAuthentication() {
                    //填写自己的163邮箱的登录帐号和授权密码，授权密码的获取，在后面会进行讲解。
                    return new PasswordAuthentication(SEND_MAIL_FROM,"Aa123456");
                }
            };

            //1 获得连接
            Session session = Session.getDefaultInstance(props, authenticator);
            //2 创建消息
            Message message = new MimeMessage(session);
            // 2.1 发件人        xxx@163.com 我们自己的邮箱地址，就是名称
            message.setFrom(new InternetAddress(SEND_MAIL_FROM));
            //获取执行者的邮件地址
            int runUser = history.getRunUser();
            User user = userService.getOneUser(runUser);
            SEND_MAIL_TO = user.getEmail();

            message.setRecipient(Message.RecipientType.TO, new InternetAddress(SEND_MAIL_TO));
            // 2.3 主题（标题）
            message.setSubject("接口测试结果报告");

//            String filename = this.getClass().getResource("/").getPath()+"test-output/index.html";
//            String fileName = filename.substring(1,filename.length());
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("您好:"+user.getRealName()+"<br/>");
            stringBuffer.append("<br>");
            if(history.getRunResult() == CommonParam.FAIL){
                stringBuffer.append("&nbsp;&nbsp;本次测试结果为:<br>&nbsp;&nbsp;<font color='red'><H3>FAILURE</H3></font><br/><hr>");
            }
            if(history.getRunResult() == CommonParam.PASS){
                stringBuffer.append("&nbsp;&nbsp;本次测试结果为:<br>&nbsp;&nbsp;<font color='green'><H3>PASS</H3></font><br/><hr>");
            }

            stringBuffer.append("&nbsp;&nbsp;用例ID:"+singleApi.getApiId()+"<br/><hr>");
            stringBuffer.append("&nbsp;&nbsp;用例名称:"+singleApi.getApiName()+"<br/><hr>");
            stringBuffer.append("&nbsp;&nbsp;用例描述:"+singleApi.getApiDesc()+"<br/><hr>");
            stringBuffer.append("&nbsp;&nbsp;请求头:"+singleApi.getRequestHeader()+"<br/><hr>");
            stringBuffer.append("&nbsp;&nbsp;请求体:"+singleApi.getRequestBody()+"<br/><hr>");
            stringBuffer.append("&nbsp;&nbsp;响应头:"+singleApi.getResponseHeader()+"<br/><hr>");
            stringBuffer.append("&nbsp;&nbsp;响应体:"+singleApi.getResponseOriginBody()+"<br/><hr>");
            stringBuffer.append("&nbsp;&nbsp;请求时间:"+singleApi.getRequestTime()+"<br/><hr>");
            stringBuffer.append("&nbsp;&nbsp;响应时间:"+singleApi.getResponseTime()+"<br/><hr>");
            stringBuffer.append("<hr>");
            if(history.getRunResult() == CommonParam.FAIL){
                stringBuffer.append("&nbsp;&nbsp;<h1>错误日志:</h1><br/>");
                if(logList != null){
                    for(RunLog log:logList){
                        switch (log.getCheckType()){
                           case CommonParam.NOT_NULL_CHECK:     //不为空检查点
                               stringBuffer.append("检查点类型：不为空检查；<br>");break;
                           case CommonParam.CONTAIN_CHECK:     //包含检查点
                               stringBuffer.append("检查点类型：包含检查点；<br>");break;
                           case CommonParam.NOT_CONTAIN_CHECK:     //不包含检查点
                               stringBuffer.append("检查点类型：不包含检查点；<br>");break;
                           case CommonParam.NUMBER_CHECK:      //数值检查点
                               stringBuffer.append("检查点类型：数值检查点；<br>");break;
                           case CommonParam.STR_CHECK:     //文本对比
                               stringBuffer.append("检查点类型：文本对比；<br>");break;
                        }
                        stringBuffer.append("日志："+log.getReason());
                        stringBuffer.append("<br><hr>");
                    }
                }

            }
                    ;
//            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filename), "UTF-8"));// 解决读取中文乱码
//            String line = null;
//            StringBuffer sb = new StringBuffer();
//            while ((line = br.readLine()) != null) {
//                sb.append(line);//拼接到stringBuffer
//                sb.append("\n");//按理说可以不用换行都可以解析html
//            }
//            br.close();
//            BodyPart bodyPart = new MimeBodyPart();//BodyPart是邮件内容的承载体，可以是文件，图片，附件等...
//            bodyPart.setContent(sb.toString(), "text/html;charset=UTF-8");//设置页面的编码值
//            Multipart multiPart = new MimeMultipart();//Multipart又是BodyPart的承载体，一个multiPart可以包含多个BodyPart
//            multiPart.addBodyPart(bodyPart);//将bodyPart添加到multiPart
//            message.setContent(multiPart);//将MultiPart设为邮件内容主体msg的
            message.setContent(stringBuffer.toString(),"text/html;charset=UTF-8");
            //3发送消息
            Transport.send(message);
        }else {
            throw new Exception("用例执行失败");
        }

    }
    @AfterClass
    public void afterExecuteCase(){
        System.out.println("afterExecuteCase----------------------------------");
    }

    /**
     * 遍历响应体中的所有json并将key保存到list
     * @param json 响应体中的所有json
     * @param keys key列表
     * @return key列表
     */
    public List<String> decodeJSONObject(JSONObject json,List<String> keys){
        String key = "";
        Iterator iterator = json.keys();
        JSONObject jo;
        JSONArray ja;
        Object value ;
        while (iterator.hasNext()){
            key =(String)iterator.next();
            keys.add(key);
            value = json.get(key);
            if(value instanceof JSONObject){
                jo = (JSONObject)value;
                if(jo.keySet().size() > 0){
                    decodeJSONObject(jo,keys);
                }
            }else if(value instanceof JSONArray){
                ja =(JSONArray) value;
                for(int i=0;i<ja.length();i++){
                    jo = (JSONObject) ja.get(i);
                    if(jo.keySet().size() > 0){
                        decodeJSONObject(jo,keys);
                    }
                }
            }
        }
        return keys;
    }

    /**
     * 遍历响应体中的所有json并将key保存到list
     * @param json 响应体中的所有json
     * @param newJson 生成的新的json对象
     * @return 新的json对象
     */
    public JSONObject decodeJSONObject(JSONObject json,JSONObject newJson){
        String key = "";
        Iterator iterator = json.keys();
        JSONObject jo;
        JSONArray ja;
        Object value ;
        while (iterator.hasNext()){
            key =(String)iterator.next();
            value = json.get(key);
            newJson.put(key,value);
            if(value instanceof JSONObject){
                jo = (JSONObject)value;
                if(jo.keySet().size() > 0){
                    decodeJSONObject(jo,newJson);
                }
            }else if(value instanceof JSONArray){
                ja =(JSONArray) value;
                for(int i=0;i<ja.length();i++){
                    jo = (JSONObject) ja.get(i);
                    if(jo.keySet().size() > 0){
                        decodeJSONObject(jo,newJson);
                    }
                }
            }
        }
        return newJson;
    }


}

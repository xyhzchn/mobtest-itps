package com.yoozoo.util;

/**
 * 公共的参数
 * Created by guoxx on 2018/6/27.
 */
public interface CommonParam {
    //已删除
    public final int DELETE = 1;
    //未删除
    public final int NOT_DELETE = 0;

    //请求方式：get
    public final int GET = 1;
    //请求方式：post
    public final int POST = 2;

    //请求参数传递类型-formdata
    public final int PARAM_TYPE_FORMDATA = 1;
    //请求参数传递类型-json
    public final int PARAM_TYPE_JSON = 2;

    //解密类型：请求数据解密
    public final int REQUEST_BODY_DECODE = 1;
    //解密类型：响应数据解密
    public final int RESPONSE_BODY_DECODE = 2;
    //解密类型：不解密
    public final int NO_BODY_DECODE = 0;


    //参数类型 KEY-VALUE
    public final int PARAM_TYPE_BY_KEYVALUE = 1;
    //参数类型 SQL
    public final int PARAM_TYPE_BY_SQL = 2;
    //参数类型 API
    public final int PARAM_TYPE_BY_API = 3;
    //参数类型 FILE
    public final int PARAM_TYPE_BY_FILE = 4;

    //全局参数
    public final int PARAM_GLOBAL = 1;
    //非全局参数
    public final int PARAM_NOT_GLOBAL = 0;

    //用例检查点类型-不为空
    public final int NOT_NULL_CHECK = 1;
    //用例检查点类型-包含检查
    public final int CONTAIN_CHECK = 2;
    //用例检查点类型-不包含检查
    public final int NOT_CONTAIN_CHECK = 3;
    //用例检查点类型-数值检查
    public final int NUMBER_CHECK = 4;
    //用例检查点类型-字段检查
    public final int STR_CHECK = 5;


    //用例检查点-表达式定义-等于
    public final int EQ = 1;
    //用例检查点-表达式定义-大于
    public final int GT = 2;
    //用例检查点-表达式定义-小于
    public final int LT = 3;
    //用例检查点-表达式定义-大于等于
    public final int GE = 4;
    //用例检查点-表达式定义-小于等于
    public final int LE = 5;
    //用例检查点-表达式定义-不等于
    public final int NE = 6;

    //不为空检查点是否开启-开启
    public final int OPEN = 1;
    //不为空检查点是否开启-未开启
    public final int NOT_OPEN = 0;


    //用例执行结果-成功
    public final int PASS = 1;
    //用例执行结果-失败
    public final int FAIL = 2;

    //空字符串
    public final String STR_NULL = "";


}

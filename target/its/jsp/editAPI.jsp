<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="header.jsp"/>
<jsp:include page="left.jsp"/>
<!-- 主要内容区域-->
<div class="content-wrapper">
    <!-- 面包屑 -->
    <section class="content-header">
        <ol class="breadcrumb">
            <li><a href="<%=request.getContextPath()%>/item/getItems"><i class="fa fa-dashboard"></i> 接口管理</a></li>
            <li class="active">编辑接口</li>
        </ol>
    </section>
    <!-- 主要内容 -->
    <section class="content container-fluid">
        <div class="context">
            <!--面包屑-->
            <input type="hidden" id="itemIdInput" value="${api.itemId}">
            <form id="apiForm">
                <div class="addArea">
                    <div class="realAddArea">
                        <div class="textMar">
                            <label>接口ID</label><br>
                            <input type="text" class="textStyle" id="apiId" style="margin-left: 0px;border:0" value="${api.apiId}" disabled/>
                        </div>
                        <!--接口名称部分-->
                        <div class="textMar">
                            <label>接口名称</label><br>
                            <input type="text" placeholder="请输入接口名称" class="textStyle" id="apiName" style="margin-left: 0px;" value="${api.apiName}" disabled/>
                        </div>
                        <!--接口描述部分-->
                        <div class="textMar">
                            <label>接口描述</label><br>
                            <textarea placeholder="请输入接口描述信息" class="textareaStyle" id="apiDesc" style="margin-left: 0px;" disabled>${api.apiDesc}</textarea>
                        </div>
                        <!--请求方式以及链接部分-->
                        <div class="textMar">
                            <label>请求地址</label><i class="fa fa-fw fa-question-circle" title="host:对应服务器的域名;例如:cms.init.com&#10;URL:&#10;get请求:需要带参(若有,例如:/getList&name=yoozoo);&#10;post请求:不带参(参数在底部参数列表配置,例如:/getList)"></i><br>
                            <select id="method" style="margin:0px;width:5%;" onchange="methodChange()" name="api.method" disabled>
                                    <option value="1" selected>Get</option>
                                    <option value="2" selected>Post</option>
                            </select>
                            <input type="text" id="Host" placeholder="Host…" class="textStyle" style="margin: 0px;" value="${api.apiHost}" disabled/>
                            <input type="text" id="url" placeholder="URL…" class="textStyle" style="margin: 0px;width:42%;" value="${api.url}" disabled/>
                        </div>
                        <!--ip地址和端口部分-->
                        <div class="textMar">
                            <label>IP和Port</label><i class="fa fa-fw fa-question-circle" title="IP:可选项，对应域名的ip地址;例如：192.168.11.11&#10;Port:可选项，对应访问服务器端口号;例如:8888"></i><br>
                            <input type="text" id="ipAddr" placeholder="IP…" class="textStyle" style="margin: 0px;" value="${api.ipAddr}" disabled/>
                            <input type="text" id="port" placeholder="Port…" class="textStyle" style="margin: 0px;" value="${api.apiPort}" disabled/>
                        </div>
                        <!--请求头部分-->
                        <div class="textMar" id="requestHeader">
                            <label>请求头部</label>
                            <i class="fa fa-fw fa-question-circle" title="头部标签：表示请求头中的参数名，例如：Accept-Language:User-Agent。&#10;标签内容：请求头中标签对应的值"></i>
                            <br>
                            <textarea class="textareaStyle" style="margin-left: 0px;width: 65%" id="requestHeaderJson" disabled >${api.requestHeader}</textarea>
                        </div>
                        <!--请求参数部分-->
                        <div class="textMar" id="requestOriginBody">
                            <label >请求参数</label>
                            <i class="fa fa-fw fa-question-circle" title="form-data:是键值对方式参数数据；&#10;Json：Json格式参数数据&#10;参数名:发送请求时,对应的参数名称;例如:name&#10;参数值:发送请求时,参数名对应的参数值;例如:yoozoo&#10;Json:严格的Json格式数据;例如:{name:'yoozoo';age:8;}"></i><br>
                            <br>
                            <textarea class="textareaStyle" style="margin-left: 0px;width: 65%" id="requestBodyJson" disabled >${api.requestOriginBody}</textarea>
                        </div>
                        <!--解密设置部分-->
                        <div id="isDecode" class="textMar">
                            <label>解密设置</label><i class="fa fa-fw fa-question-circle" title="若响应是密文，则务必选择'响应数据解密'，否则无法做检查点验证"></i></br>
                            <div id="decodeDiv">
                                <c:if test="${api.decodeType == 0}">
                                    <span>不解密</span>
                                </c:if>
                                <c:if test="${api.decodeType == 1}">
                                    <span>请求数据解密</span>
                                </c:if>
                                <c:if test="${api.decodeType == 2}">
                                    <span>响应数据解密</span>
                                </c:if>
                            </div>
                            <table style="margin:0px;display: none" id="decodeTab" >
                                <tr>
                                    <td><span>请求数据解密：</span></td>
                                    <td><input type="radio" name="decodeType" value="1" ></td>
                                    <td style="width:4px"></td>
                                    <td><span>响应数据解密：</span></td>
                                    <td><input type="radio" name="decodeType" value="2" ></td>
                                    <td style="width:4px"></td>
                                    <td><span>不解密：</span></td>
                                    <td><input type="radio" name="decodeType" value="0" checked></td>
                                </tr>
                            </table>
                        </div>
                        <!--检查点部分-->
                        <div class="textMar">
                            <label>检查点</label>
                            <div id="checkpointDiv">
                                <table border="0" cellpadding="0" cellspacing="0" style="margin: 0px;">
                                    <tbody>
                                    <tr>
                                        <th>检查点类型</th>
                                        <th>检查字段</th>
                                        <th>判定条件</th>
                                        <th>检查值</th>
                                        <th>是否开启</th>
                                    </tr>
                                    <c:forEach items="${api.points}" var="point">
                                        <tr>
                                            <c:if test="${point.pointType == 1}">
                                                <td>不为空检查点</td>
                                                <td style="text-align: center">*</td>
                                                <td style="text-align: center">*</td>
                                                <td style="text-align: center">*</td>
                                                <c:if test="${point.isOpen == 1}">
                                                    <td>开启</td>
                                                </c:if>
                                                <c:if test="${point.isOpen == 0}">
                                                    <td>不开启</td>
                                                </c:if>
                                            </c:if>
                                            <c:if test="${point.pointType == 2}">
                                                <td>包含检查点</td>
                                                <td style="text-align: center">${point.checkKey}</td>
                                                <td style="text-align: center">*</td>
                                                <td style="text-align: center">*</td>
                                                <td style="text-align: center">*</td>
                                            </c:if>
                                            <c:if test="${point.pointType == 3}">
                                                <td>不包含检查点</td>
                                                <td style="text-align: center">${point.checkKey}</td>
                                                <td style="text-align: center">*</td>
                                                <td style="text-align: center">*</td>
                                                <td style="text-align: center">*</td>
                                            </c:if>
                                            <c:if test="${point.pointType == 4}">
                                                <td>数值检查点</td>
                                                <td style="text-align: center">${point.checkKey}</td>
                                                <c:if test="${point.expression == 1}">
                                                    <td style="text-align: center">==</td>
                                                </c:if>
                                                <c:if test="${point.expression == 2}">
                                                    <td style="text-align: center">&gt;</td>
                                                </c:if>
                                                <c:if test="${point.expression == 3}">
                                                    <td style="text-align: center">&lt;</td>
                                                </c:if>
                                                <c:if test="${point.expression == 4}">
                                                    <td style="text-align: center">&ge;</td>
                                                </c:if>
                                                <c:if test="${point.expression == 5}">
                                                    <td style="text-align: center">&le;</td>
                                                </c:if>
                                                <c:if test="${point.expression == 6}">
                                                    <td style="text-align: center">!=</td>
                                                </c:if>
                                                <td style="text-align: center">${point.checkValue}</td>
                                                <td style="text-align: center">*</td>
                                            </c:if>
                                            <c:if test="${point.pointType == 5}">
                                                <td>字段检查点</td>
                                                <td style="text-align: center">${point.checkKey}</td>
                                                <td style="text-align: center">equals</td>
                                                <td style="text-align: center">${point.checkValue}</td>
                                                <td style="text-align: center">*</td>
                                            </c:if>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                            <div id="checkpointDiv2" style="display: none">
                                <table border="0" cellpadding="0" cellspacing="0" style="margin: 0px;">
                                    <tbody id="checkPointTb">
                                    <tr>
                                        <td><input type="checkbox" name="checkPoint" value="1"></td>
                                        <td>不为空检查点</td>
                                        <td><input type="radio"  name="isOpen" value="0" >不开启<input type="radio" style="margin-left: 20px" name="isOpen" value="1">开启</td>
                                    </tr>
                                    <tr>
                                        <td><input type="checkbox" name="checkPoint" value="2"></td>
                                        <td>包含检查点</td>
                                        <td><input type="text" id="containCheck" placeholder="字段名，多值逗号分隔" class="textStyle"/></td>
                                    </tr>
                                    <tr>
                                        <td><input type="checkbox" name="checkPoint" value="3"></td>
                                        <td>不包含检查点</td>
                                        <td><input type="text" id="notContainCheck" placeholder="字段名，多值逗号分隔" class="textStyle"/></td>
                                    </tr>
                                    <tr>
                                        <td><input type="checkbox" name="checkPoint" value="4"></td>
                                        <td>数值检查点</td>
                                        <td><input type="text" placeholder="字段名" id="numberCheckKey" class="textStyle"/></td>
                                        <td>
                                            <select style="width:50px;" id="numberExpression" >
                                                <option value="1" selected>==</option>
                                                <option value="2">></option>
                                                <option value="3"><</option>
                                                <option value="4">>=</option>
                                                <option value="5"><=</option>
                                                <option value="6">!==</option>
                                            </select>
                                        </td>
                                        <td><input type="text" placeholder="字段值" id="numberCheckValue" class="textStyle"></td>
                                    </tr>
                                    <tr>
                                        <td><input type="checkbox" name="checkPoint" value="5"></td>
                                        <td>字段检查点</td>
                                        <td><input type="text" placeholder="字段名" id="strCheckKey" class="textStyle"/></td>
                                        <td style="text-align: center;">equels</td>
                                        <td><input type="text" placeholder="字段值" id="strCheckValue" class="textStyle"></td>
                                    </tr>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                    <!--按钮部分-->
                    <div class="textMar" style="margin-left:60px">
                        <input type="button" value="取   消" class="btnStyle" style="margin: auto 10% auto 30%;" onclick="self.location='<%=request.getContextPath()%>/api/getAllApis?itemId='+$('#itemIdInput').val()">
                        <input id="editBtn" type="button" value="修   改" class="btnStyle" onclick="updateApi()">
                        <input id="saveBtn" type="button" value="保   存" class="btnStyle" onclick="editApi()" style="display: none"/>
                    </div>
                </div>
            </form>
        </div>
    </section>
</div>
<jsp:include page="footer.jsp"/>

</body>
<script type="text/javascript">
    //当请求方式修改时
    function methodChange() {
        var method = $("#method").val();
        if(method == 2){
            $("#requestOriginBody").removeAttr("style");
        }else {
            $("#requestOriginBody").attr("style","display:none");
        }
    }

    /**
     * 修改接口
     */
    function editApi() {

        //接口名称
        var apiId = $("#apiId").val();
        if(apiId == ""){
            $("#apiId").after("<span class='error'>*接口ID不可为空</span>");
        }

        var apiName = $("#apiName").val();
        if(apiName == ""){
            $("#apiName").after("<span class='error'>*接口名称不可为空</span>");
        }
        //接口描述
        var apiDesc = $("#apiDesc").val();
        //接口方法
        var method = $("#method").val();
        //接口HOST
        var Host = $("#Host").val();
        if(Host == ""){
            $("#Host").after("<span class='error'>*Host不可为空</span>");
        }
        //接口URL
        var url = $("#url").val();
        if(url == ""){
            $("#url").after("<span class='error'>*URL不可为空</span>");
        }
        //接口ip地址
        var ipAddr = $("#ipAddr").val();
        //接口端口号
        var port = $("#port").val();
        //接口所属项目
        var itemId = $("#itemIdInput").val();
        if(itemId == ""){
            $("#itemIdInput").after("<span class='error'>*项目ID不可为空</span>");
        }


        var requestHeader;          //请求头
        var requestOriginBody;      //请求体，只包含参数名：参数值

        var requestHeaderData = $("#requestHeaderJson").val();
        var requestBodyData = $("#requestBodyJson").val();

        if(requestHeaderData == "[]"){
            $("#requestHeader").after("<span class='error'>*请求头不可为空</span>");
        }else{
            //当通过json传输数据时，需要判断json格式是否正确
            try{
                requestHeader = JSON.stringify($.parseJSON(requestHeaderData))
            }catch (err){
                $("#requestHeader").after("<span class='error'>*请求头Json格式不正确，请检查</span>");
                return false;
            }
        }
        if(requestBodyData == "[]" && method == 2){
            $("#requestOriginBody").after("<span class='error'>*请求体不可为空</span>");
        }else{
            try{
                requestOriginBody = JSON.stringify($.parseJSON(requestBodyData));
            }catch (err){
                $("#requestOriginBody").after("<span class='error'>*请求体Json格式不正确，请检查</span>");
                return false;
            }
        }

        //参数类型
        var paramType = 2;

        //解密配置
        var decodeType = $("input[name='decodeType']:checked").val();

        //检查点
        var points = [];

        $("#checkPointTb").find("tr").each(function (i) {
            var tdArr = $(this).children();
            var pointType;
            if(tdArr.eq(0).find("input[name='checkPoint']").is(':checked')) {
                pointType = tdArr.eq(0).find("input").val();
            }
            switch (parseInt(pointType)){
                case 1:
                    var point = new Object();
                    var isOpen = tdArr.find("input[name='isOpen']:checked").val();  //不为空是否开启
                    point.pointType = pointType;
                    point.isOpen = isOpen;
                    points.push(point);
                    break;
                case 2:
                    var point = new Object();
                    var containCheck = tdArr.find("#containCheck").val();   //包含字段
                    point.pointType = pointType;
                    point.checkKey = containCheck;
                    points.push(point);
                    break;
                case 3:
                    var point = new Object();
                    var notContainCheck = tdArr.find("#notContainCheck").val(); //不包含字段
                    point.pointType = pointType;
                    point.checkKey = notContainCheck;
                    points.push(point);
                    break;
                case 4:
                    var point = new Object();
                    var numCheckKey = tdArr.find("#numberCheckKey").val();      //对比字段
                    var numExpression = tdArr.find("#numberExpression").val();  //运算符号
                    var numCheckValue = tdArr.find("#numberCheckValue").val();  //对比字段的值
                    point.pointType = pointType;
                    point.checkKey = numCheckKey;
                    point.expression = numExpression;
                    point.checkValue = numCheckValue;
                    points.push(point);
                    break;
                case 5:
                    var point = new Object();
                    var strCheckKey = tdArr.find("#strCheckKey").val();     //对比字段
                    var strCheckValue = tdArr.find("#strCheckValue").val(); //对比字段的值
                    point.pointType = pointType;
                    point.checkKey = strCheckKey;
                    point.checkValue = strCheckValue;
                    points.push(point);
                    break;
            }
        });

        if(apiName != "" && method != "" && Host != "" && url != "" && itemId != "" && requestHeader != "[]" && paramType != "" && decodeType != ""){
            var api = {
                "apiId":apiId,
                "apiName":apiName,
                "apiDesc":apiDesc,
                "method":method,
                "apiHost":Host,
                "url":url,
                "ipAddr":ipAddr,
                "apiPort":port,
                "paramType":paramType,
                "requestHeader":requestHeader,
                "requestOriginBody":requestOriginBody,
                "decodeType":decodeType,
                "itemId":itemId,
                "points":points
            }
            $.ajax({
                type:"post",
                url:"/api/editApi",
                contentType: "application/json;charset=utf-8",
                data:JSON.stringify(api),
                dataType : 'json',
                success:function (data) {
                    if (data.code == '1') {
                        window.location.href = "/api/getAllApis?itemId="+itemId;
                    }else{
                        window.location.href = "/jsp/error.jsp";
                    }
                },
                error : function(data) {
                    alert("出错：状态码" + data.code+"表示数据库执行错误");
                }
            })
        }else {
            $("#saveBtn").attr("style","display:none");
        }

    }

    /*
     * 修改接口
     */
    function updateApi() {
            //当点击修改按钮时，将输入框设置为可编辑。将按钮变为 保存
        $("#apiName").removeAttr("disabled");
        $("#apiDesc").removeAttr("disabled");
        $("#method").removeAttr("disabled");
        $("#Host").removeAttr("disabled");
        $("#url").removeAttr("disabled");
        $("#ipAddr").removeAttr("disabled");
        $("#port").removeAttr("disabled");
        $("#requestHeaderJson").removeAttr("disabled");
        $("#requestBodyJson").removeAttr("disabled");
        $("#decodeDiv").attr("style","display:none");
        $("#decodeTab").removeAttr("style");
        $("#decodeTab").attr("style","margin:0px");
        $("#checkpointDiv").attr("style","display:none");
        $("#checkpointDiv2").removeAttr("style");

        //保存按钮可见
        $("#saveBtn").removeAttr("style");
        //修改按钮不可见
        $("#editBtn").attr("style","display:none");
    }
</script>
</html>


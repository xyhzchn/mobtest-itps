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
                <li class="active">添加接口</li>
        </ol>
    </section>
    <!-- 主要内容 -->
    <section class="content container-fluid">
        <div class="context">
            <!--面包屑-->

                <input type="hidden" id="itemIdInput" value="${itemId}">
                <form id="apiForm">
                    <div class="addArea">
                        <div class="realAddArea">
                                <div class="textMar">
                                    <label>接口名称</label><br>
                                    <input type="text" placeholder="请输入接口名称" class="textStyle" id="apiName" style="margin-left: 0px;"/>
                                </div>
                                <div class="textMar">
                                    <label>接口描述</label><br>
                                    <textarea placeholder="请输入接口描述信息" class="textareaStyle" id="apiDesc" style="margin-left: 0px;"></textarea>
                                </div>
                            <div class="textMar">
                                <label>请求地址</label><i class="fa fa-fw fa-question-circle" title="host:对应服务器的域名;例如:cms.init.com&#10;URL:&#10;get请求:需要带参(若有,例如:/getList&name=yoozoo);&#10;post请求:不带参(参数在底部参数列表配置,例如:/getList)"></i><br>
                                    <select id="method" style="margin:0px;width:5%;" onchange="methodChange()">
                                        <option value="1">Get</option>
                                        <option value="2">Post</option>
                                    </select>
                                    <input type="text" id="Host" placeholder="Host…" class="textStyle" style="margin: 0px;"/>
                                    <input type="text" id="url" placeholder="URL…" class="textStyle" style="margin: 0px;width:42%;"/>
                            </div>
                            <div class="textMar">
                                <label>IP和Port</label><i class="fa fa-fw fa-question-circle" title="IP:可选项，对应域名的ip地址;例如：192.168.11.11&#10;Port:可选项，对应访问服务器端口号;例如:8888"></i><br>
                                    <input type="text" id="ipAddr" placeholder="IP…" class="textStyle" style="margin: 0px;"/>
                                    <input type="text" id="port" placeholder="Port…" class="textStyle" style="margin: 0px;"/>
                            </div>

                            <div id="requestHeader" class="textMar">
                                <label>请求头部</label>
                                <i class="fa fa-fw fa-question-circle" title="头部标签：表示请求头中的参数名，例如：Accept-Language:User-Agent。&#10;标签内容：请求头中标签对应的值"></i><br>
                                    <table border="0" cellpadding="0" cellspacing="0" style="margin: 0px;">
                                        <tbody id="headerTb">
                                            <tr>
                                                <td>头部</td>
                                                <td>标签</td>
                                                <td style="width:300px">内容</td>
                                                <td colspan="2">操作</td>
                                            </tr>
                                                <tr>
                                                    <td><input type="checkbox" class="checkbox" style="text-align: center" name="headerCheckbox"></td>
                                                    <td><input type="text" placeholder="头部标签" class="textStyle" onchange="addHeader(this.value)"/></td>
                                                    <td><input type="text" placeholder="标签内容" class="textStyle"style="margin: 0px;width:300px"/></td>
                                                    <td><img src="<%=request.getContextPath()%>/img/link.png" class="imgStyle" ></td>
                                                    <td><img src="<%=request.getContextPath()%>/img/del.png" class="imgStyle" name="headerDeleteBtn" style="display:none;" onclick="delHeader(this)"></td>
                                                </tr>
                                        </tbody>
                                    </table>
                            </div>
                            <div id="requestOriginBody" class="textMar" style="display:none">
                                <label >请求参数</label>
                                <i class="fa fa-fw fa-question-circle" title="form-data:是键值对方式参数数据；&#10;Json：Json格式参数数据&#10;参数名:发送请求时,对应的参数名称;例如:name&#10;参数值:发送请求时,参数名对应的参数值;例如:yoozoo&#10;Json:严格的Json格式数据;例如:{name:'yoozoo';age:8;}"></i><br>
                                <br>
                                <div id="tab-demo">
                                    <ul class="tab-title">
                                        <li><a href="#tab01">表单[form-data]</a></li>
                                        <li><a href="#tab02">Json</a></li>
                                    </ul>
                                    <div id="tab01" class="tab-inner">
                                        <table border="0" cellpadding="0" cellspacing="0" style="margin: 0px;">
                                            <tbody id="paramTb">
                                                <tr>
                                                    <td>请求</td>
                                                    <td>参数名</td>
                                                    <td>类型</td>
                                                    <td style="width:250px">参数值</td>
                                                    <td colspan="2">操作</td>
                                                </tr>
                                                <tr>
                                                    <td><input type="checkbox" class="checkbox" style="text-align: center" name='paramCheckbox'></td>
                                                    <td><input type="text" placeholder="参数名" class="textStyle" onchange="addParam(this.value)"/></td>
                                                    <td><select style="width: 75px;">
                                                        <option value="1">Text</option>
                                                        <option value="2">File</option>
                                                    </select></td>
                                                    <td><input type="text" placeholder="参数值" class="textStyle"style="margin: 0px;width:250px"/></td>
                                                    <td><img src="<%=request.getContextPath()%>/img/link.png" class="imgStyle" ></td>
                                                    <td><img  src="<%=request.getContextPath()%>/img/del.png" name="paramDeleteBtn" style="display:none;" class="imgStyle" onclick="delParam(this)"></td>
                                                </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                    <div id="tab02" class="tab-inner">
                                        <textarea  placeholder="{…}"class="textareaStyle" id="requestBodyData" style="margin-left: 0px;width: 80%;height: 100px;border-top: 0;"></textarea>
                                        <br>
                                        <span class="error" id="jsonError" style="display:none;">Json格式错误，请检查格式！</span>
                                    </div>
                                </div>
                            </div>

                            <div id="isDecode" class="textMar">
                                <label>解密设置</label><i class="fa fa-fw fa-question-circle" title="若响应是密文，则务必选择'响应数据解密'，否则无法做检查点验证"></i></br>
                                <table style="margin:0px">
                                    <tr>
                                        <td><span>请求数据解密：</span></td>
                                        <td><input type="radio" name="decodeType" value="1"></td>
                                        <td style="width:4px"></td>
                                        <td><span>响应数据解密：</span></td>
                                        <td><input type="radio" name="decodeType" value="2"></td>
                                        <td style="width:4px"></td>
                                        <td><span>不解密：</span></td>
                                        <td><input type="radio" name="decodeType" value="0" checked></td>
                                    </tr>
                                </table>
                            </div>
                            <div class="textMar">
                                <label>检查点</label>
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
                            <div class="textMar" style="margin-left:60px">
                                <input type="button" value="取   消" class="btnStyle" style="margin: auto 10% auto 30%;" onclick="self.location='<%=request.getContextPath()%>/api/getAllApis?itemId='+$('#itemIdInput').val()">
                                <input type="button" value="保   存" id="saveBtn" class="btnStyle" onclick="addApi()"/>
                            </div>
                    </div>
                </form>
            </div>
    </section>
</div>
<jsp:include page="footer.jsp"/>

</body>
<script type="text/javascript">
    $(function(){
        var $li = $('ul.tab-title li');
        $($li. eq(0) .addClass('active').find('a').attr('href')).siblings('.tab-inner').hide();

        $li.click(function(){
            $($(this).find('a'). attr ('href')).show().siblings ('.tab-inner').hide();
            $(this).addClass('active'). siblings ('.active').removeClass('active');
        });
    });

    //当请求方式修改时
    function methodChange() {
        var method = $("#method").val();
        if(method == 2){
            $("#requestOriginBody").removeAttr("style");
        }else {
            $("#requestOriginBody").attr("style","display:none");
        }
    }
    //添加一行请求头信息
    function addHeader(str) {
        if(str.length > 0){
            //显示删除按钮
            $("img[name='headerDeleteBtn']").removeAttr("style");
            $("input[name='headerCheckbox']").attr("checked","true");
            //新建一行
            var trObj = document.createElement("tr");
            var tdObjs = "<td><input type='checkbox' class='checkbox' style='text-align: center' name='headerCheckbox'></td>"+
                         "<td><input type='text' placeholder='头部标签' class='textStyle' onchange='addHeader(this.value)'/></td>"+
                         "<td><input type='text' placeholder='头部内容' class='textStyle'style='margin: 0px;width:300px'/></td>"+
                         "<td><img src='../img/link.png' class='imgStyle' ></td>"+
                         "<td><img src='../img/del.png' class='imgStyle' name='headerDeleteBtn' style='display:none;' onclick='delHeader(this)'></td>";
            trObj.innerHTML = tdObjs;
            document.getElementById("headerTb").appendChild(trObj);
        }
    }

    //删除某一个请求头参数
    function delHeader(obj) {
        var trObj = obj.parentNode.parentNode;
        document.getElementById("headerTb").removeChild(trObj);
    }

    //添加请求参数。当输入请求参数名时，执行事件
    function addParam(str) {
        if(str.length > 0){
            //显示删除按钮
            $("img[name='paramDeleteBtn']").removeAttr("style");
            $("input[name='paramCheckbox']").attr("checked","true");
            //新建一行
            var trObj = document.createElement("tr");
            var tdObjs = "<td><input type='checkbox'class='checkbox'style='text-align: center' name='paramCheckbox'></td>"+
                    "<td><input type='text' placeholder='参数名' class='textStyle' onchange='addParam(this.value)' /></td>"+
                    "<td>" +
                    "<select style='width: 75px;'>"+
                    "<option value='1'>Text</option>"+
                    "<option value='2'>File</option>"+
                    "</select>" +
                    "</td>"+
                    "<td><input type='text' placeholder='参数值' class='textStyle'style='margin: 0px;width:250px'/></td>"+
                    "<td><img src='../img/link.png' class='imgStyle' ></td>"+
                    "<td><img  src='../img/del.png' name='paramDeleteBtn' style='display:none;' class='imgStyle' onclick='delParam(this)'></td>";
            trObj.innerHTML = tdObjs;
            document.getElementById("paramTb").appendChild(trObj);
        }

    }

    //删除某一个请求参数
    function delParam(obj) {
        var trObj = obj.parentNode.parentNode;
        document.getElementById("paramTb").removeChild(trObj);
    }

    /**
     * 添加接口
     */
    function addApi() {

        //接口名称
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

        //获取请求头信息，以json格式保存
        var headers = [];
        $("#headerTb").find("tr").each(function (i) {
            var tdArr = $(this).children();
            var data = new Object();
            if(tdArr.eq(0).find("input[name='headerCheckbox']").is(':checked')) {
                var header_key = tdArr.eq(1).find("input").val();
                data[header_key] = tdArr.eq(2).find("input").val();
                headers.push(data);
            }
        });
        requestHeader = JSON.stringify(headers);

        //设置参数传输类型
        var paramType;
        //获取选择的tab中的内容
        var tabName = $(".active").children("a").text();

        if(tabName == "表单[form-data]"){
            paramType = 1;
        }else if(tabName == "Json"){
            paramType = 2;
        }

        //当通过form-data类型时，需要对参数的键值对做处理
        if(paramType == 1){
            //获取请求体信息，以json格式保存
            var bodys = [];
            $("#paramTb").find("tr").each(function (i) {
                var tdArr = $(this).children();
                var data = new Object();
                if(tdArr.eq(0).find("input[name='paramCheckbox']").is(':checked')) {
                    var body_key = tdArr.eq(1).find("input").val();
                    data[body_key] = tdArr.eq(3).find("input").val();
                    bodys.push(data);
                }
            });
            requestOriginBody = JSON.stringify(bodys);
        }

        //当通过json传输数据时，需要判断json格式是否正确
        if(paramType == 2){
            var requestBodyData = $("#requestBodyData").val();
            try{
                 requestOriginBody = JSON.stringify($.parseJSON(requestBodyData));
            }catch (err){
                 $("#jsonError").removeAttr("style");
            }
        }

        if(requestOriginBody == "[]" && method == 2){
            $("#requestOriginBody").after("<span class='error'>*请求体不可为空</span>");
        }
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

        if(apiName != "" && method != "" && Host != "" && url != "" && itemId != "" && paramType != "" && decodeType != ""){
            var api = {
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
                url:"/api/addApi",
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
            return false;
        }

    }
</script>
</html>


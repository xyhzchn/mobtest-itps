<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="header.jsp"/>
<jsp:include page="left.jsp"/>
    <!-- 主要内容区域-->
    <div class="content-wrapper">

        <!-- 面包屑 -->
        <section class="content-header">
            <ol class="breadcrumb">
                <li><a href="#"><i class="fa fa-dashboard"></i> 接口管理</a></li>
                <li class="active">接口列表</li>
            </ol>
        </section>

        <!-- 主要内容 -->
        <section class="content container-fluid">
            <div class="context">
                <div id="loadingDiv">
                    <img src="../img/loading.gif">
                </div>
                <form id="apiListForm" method="post" action="<%=request.getContextPath()%>/api/getAllApis">
                    <div class="searchArea">
                        <div class="realSearchArea">
                            <table>
                                <tbody>
                                    <tr>
                                        <td>接口ID:</td>
                                        <td><input type="text" class="textStyle" placeholder="接口ID" name="apiId"/></td>
                                        <td style="width:10px"></td>
                                        <td>接口名称</td>
                                        <td><input type="text" class="textStyle" placeholder="接口名称" name="apiName"/></td>
                                        <td>运行结果</td>
                                        <td>
                                            <select name="runResult">
                                                <option value="0" selected>请选择运行结果</option>
                                                <option value="1">Pass</option>
                                                <option value="2">Failure</option>
                                            </select>
                                        </td>
                                    </tr>
                                </tbody>
                            </table>
                            <div class="textMar">
                                <input type="submit" class="btnStyle addBtnStyle" value="检   索">
                            </div>
                        </div>
                    </div>

                    <div class="dataArea">
                        <div class="textMar" >
                            <input type="button" class="btnStyle addBtnStyle" value="添  加" onclick="toApiAddPage()">
                        </div>

                        <!--用户列表相关-->
                        <div class="textMar">
                            <span style="font-size: 12px;margin-left:67%;margin-right: 10%;color: #ee782d">共计为您搜索到：${page.total}条数据；每页显示：${page.pageSize }条;共${page.pages}页;</span>
                            <table id="apisTable" border="0" cellpadding="0" cellspacing="0" style="margin: 2%;width:95%">
                                <tr>
                                    <th style="width:3%;">ID</th>
                                    <th style="width:7%;">接口名称</th>
                                    <th style="width:4%;">方法</th>
                                    <th style="width:8%;">Host</th>
                                    <th style="width:10%;">URL</th>
                                    <th style="width:10%;">请求头</th>
                                    <th style="width:10%;">请求明文</th>
                                    <th style="width:10%;">请求密文</th>
                                    <th style="width:3%;">状态码</th>
                                    <th style="width:10%;">响应明文</th>
                                    <th style="width:10%;">响应密文</th>
                                    <th style="width:5%;">运行结果</th>
                                    <th style="width:6%;">创建时间</th>
                                    <th colspan="3">操作</th>
                                </tr>
                                <c:forEach items="${page.list}" var="api">
                                    <tr>
                                        <td style="width:2%;">${api.apiId}</td>
                                        <td style="width:8%;">${api.apiName}</td>
                                        <c:if test="${api.method == 1}">
                                            <td style="width:4%;">GET</td>
                                        </c:if>
                                        <c:if test="${api.method == 2}" >
                                            <td style="width:4%;">POST</td>
                                        </c:if>
                                        <td style="width:8%;">${api.apiHost}</td>
                                        <td><textarea class="bodyAreaStyle" style="width:100%;" disabled>${api.url}</textarea></td>
                                        <td><textarea class="bodyAreaStyle" style="width:100%;" disabled>${api.requestHeader}</textarea></td>
                                        <td><textarea class="bodyAreaStyle" style="width:100%;" disabled>${api.requestBody}</textarea></td>
                                        <td><textarea class="bodyAreaStyle" style="width:100%;" disabled>${api.requestOriginBody}</textarea></td>
                                        <td style="width:3%;">${api.statusCode}</td>
                                        <td><textarea class="bodyAreaStyle" style="width:100%;" disabled>${api.responseBody}</textarea></td>
                                        <td><textarea class="bodyAreaStyle" style="width:100%;" disabled>${api.responseOriginBody}</textarea></td>
                                        <c:if test="${api.runResult == 1}">
                                            <td style="text-align: center"><img src="<%=request.getContextPath()%>/img/pass.png" style="width: 80%;height: 20px;"></td>
                                        </c:if>
                                        <c:if test="${api.runResult == 2}">
                                            <td style="text-align: center"><img src="<%=request.getContextPath()%>/img/fail.png" style="width: 80%;height: 20px;"></td>
                                        </c:if>
                                        <c:if test="${api.runResult == null}">
                                            <td style="text-align: center;width: 80%;">暂无结果</td>
                                        </c:if>
                                        <td style="width:6%;">${api.createTime}</td>
                                        <td><img src="<%=request.getContextPath()%>/img/edit.png" class="imgStyle" onclick="getApiDetail(${api.apiId})"></td>
                                        <td><img src="<%=request.getContextPath()%>/img/do.png" class="imgStyle" onclick="executeApi(${api.apiId})"></td>
                                        <td><img src="<%=request.getContextPath()%>/img/del.png" class="imgStyle" onclick="deleteApi(${api.apiId})"></td>
                                    </tr>
                                </c:forEach>
                            </table>

                            <!--分页 -->
                            <div style="width:380px;margin:0 auto;margin-top:10px;">
                                <a href="<%=request.getContextPath()%>/api/getAllApis?itemId=${itemId}&currentPage=${page.firstPage}">首页</a>
                                <a href="<%=request.getContextPath()%>/api/getAllApis?itemId=${itemId}&currentPage=${page.prePage}">上一页</a>
                                <a href="<%=request.getContextPath()%>/api/getAllApis?itemId=${itemId}&currentPage=${page.nextPage}">下一页</a>
                                <a href="<%=request.getContextPath()%>/api/getAllApis?itemId=${itemId}&currentPage=${page.lastPage}">尾页</a>
                            </div>
                            <input type="hidden" id="itemIdInput" value="${itemId}">
                        </div>
                    </div>
                </form>
            </div>
        </section>
    </div>
<jsp:include page="footer.jsp"/>
<script type="text/javascript">
//    $(document).ready(function () {
//        //获取所有的td
//        var name = $("#apisTable tr td");
//        //添加双击时间
//        name.dblclick(function () {
//            var tdObj = $(this);
//            //若是不可编辑，则跳过
//            if(tdObj.attr("disabled") == "disabled"){
//               return true;
//            }
//            //获取当前td的内容
//            var oldText = $(this).text();
//            //获取当前td的class名字
//            var isHaveCssName = $(this).attr("class");
//            //如果td的内容是get或者post，或者class的值不为空
//            if((oldText == "GET" || oldText == "POST") || isHaveCssName){
//                //如果是post或get
//                if(oldText == "GET" || oldText == "POST"){
//                    //定义一个select并将选择的值赋值到对应的td
//                    var selectObj = $("<select style='width:55px;'><option value='1'>GET</option><option value='2'>POST</option></select>");
//                    selectObj.css("background-color","#ee782d");
//                    selectObj.css("margin", 0);
//                    selectObj.css("padding", 0);
//                    tdObj.html(selectObj);
//                    selectObj.blur(function () {
//                        var newText = $(this).val();
//                        if(newText == 1){
//                            tdObj.html("GET");
//                        }else if(newText == 2){
//                            tdObj.html("POST");
//                        }
//                        //当新值和旧值不一样的时候
//                        if(oldText == "GET" && newText == 2){
//                            editApi(tdObj);
//                        }
//                        if(oldText == "POST" && newText == 1){
//                            editApi(tdObj);
//                        }
//                    });
//                }
//                //如果td的class不为空
//                if(isHaveCssName == "areaCss"){
//                    //定义一个textarea，并将修改后的值复制给td
//                    var textareaObj = $("<textarea>"+oldText+"</textarea>");
//                    //去掉文本框的边框
//                    textareaObj.css("border-width", 0);
//                    textareaObj.click(function () {
//                        return false;
//                    });
//                    //使文本框的宽度和td的宽度相同
//                    textareaObj.width(tdObj.width());
//                    textareaObj.height(tdObj.height());
//
//                    //去掉文本框的外边距
//                    textareaObj.css("margin", 0);
//                    textareaObj.css("padding", 0);
//                    textareaObj.css("text-align", "left");
//                    textareaObj.css("font-size", "14px");
//                    textareaObj.css("color", "white");
//                    textareaObj.css("word-break", "break-all");
//                    textareaObj.css("background-color", "#ee782d");
//
//                    tdObj.html(textareaObj);
//                    //文本框失去焦点的时候变为文本
//                    textareaObj.blur(function () {
//                        var newText = $(this).val();
//                        tdObj.html(newText);
//                        if(newText != oldText){
//                            editApi(tdObj);
//                        }
//                    });
//                }
//            }else{
//                //创建一个文本框
//                var inputObj = $("<input type='text' value='" + oldText + "'/>");
//                //去掉文本框的边框
//                inputObj.css("border-width", 0);
//                inputObj.click(function () {
//                    return false;
//                });
//                //使文本框的宽度和td的宽度相同
//                inputObj.width(tdObj.width());
//                inputObj.height(tdObj.height());
//
//                //去掉文本框的外边距
//                inputObj.css("margin", 0);
//                inputObj.css("padding", 0);
//                inputObj.css("text-align", "left");
//                inputObj.css("font-size", "14px");
//                inputObj.css("color", "white");
//                inputObj.css("background-color", "#ee782d");
//                //把文本框放到td中
//                tdObj.html(inputObj);
//                //文本框失去焦点的时候变为文本
//                inputObj.blur(function () {
//                    var newText = $(this).val();
//                    tdObj.html(newText);
//                    if(newText != oldText){
//                        editApi(tdObj);
//                    }
//                });
//            }
//        });
//        function editApi(tdObj) {
//            //获取该单元格所在行下的所有单元格
//            var tds = tdObj.parent("tr").find("td");
//            //设置循环次数-7
//            var tdsLength = (tds.length)-7;
//            //定义对象
//            var apiId,apiName,method,Host,url,ipAddr,port,paramType,requestHeader,requestOriginBody;
//            //对象赋值
//            var itemId = $("#itemIdInput").val();
//            //只获取前9列的数据
//            for(var i=0;i<tdsLength;i++){
//                switch (i){
//                    case 0:apiId = tds.get(i).innerHTML;break;
//                    case 1:apiName = tds.get(i).innerHTML;break;
//                    case 2:method = (tds.get(i).innerHTML)== "GET"?1:2;break;
//                    case 3:Host = tds.get(i).innerHTML;break;
//                    case 4:url = tds.get(i).innerHTML;break;
//                    case 5:ipAddr = tds.get(i).innerHTML;break;
//                    case 6:port = tds.get(i).innerHTML;break;
//                    case 7:requestHeader = tds.get(i).innerHTML;break;
//                    case 8:requestOriginBody = tds.get(i).innerHTML;break;
//                }
//            }
//
//            if(apiName != "" && Host != "" && url != "" && itemId != "" && requestHeader != "" && requestOriginBody != ""){
//                var api = {
//                    "apiId":apiId,
//                    "apiName":apiName,
//                    "method":method,
//                    "host":Host,
//                    "url":url,
//                    "ipAddr":ipAddr,
//                    "port":port,
//                    "paramType":2,  //修改的方式默认为json格式
//                    "requestHeader":requestHeader,
//                    "requestOriginBody":requestOriginBody,
//                    "itemId":itemId
//                }
//                $.ajax({
//                    type:"post",
//                    url:"/api/editApi",
//                    contentType: "application/json;charset=utf-8",
//                    data:JSON.stringify(api),
//                    dataType : 'json',
//                    success:function (data) {
//                        if (data.code == '1') {
//                            window.location.href = "/api/getAllApis?itemId="+itemId;
//                        }else{
//                            window.location.href = "/jsp/error.jsp";
//                        }
//                    },
//                    error : function(data) {
//                        alert("出错：状态码" + data.code+"表示数据库执行错误");
//                    }
//                })
//            }else {
////                if(apiName == ""){
////                    $("#apiName").after("<span class='error'>*接口名称不可为空</span>");
////                }
////                if(Host == ""){
////                    $("#Host").after("<span class='error'>*Host不可为空</span>");
////                }
////                if(url == ""){
////                    $("#url").after("<span class='error'>*URL不可为空</span>");
////                }
////                if(itemId == ""){
////                    $("#itemIdInput").after("<span class='error'>*项目ID不可为空</span>");
////                }
////                if(requestHeader == "[]"){
////                    $("#requestHeader").after("<span class='error'>*请求头不可为空</span>");
////                }
////                if(requestOriginBody == "[]"){
////                    $("#requestOriginBody").after("<span class='error'>*请求体不可为空</span>");
////                }
//            }
//        }
//        //鼠标移动到某一行，某一行的背景颜色变化
//        var trs = $("#apisTable tr");
//        trs.mouseover(function () {
//            $(this).css("background-color","#eee");
//        });
//        trs.mouseout(function () {
//            $(this).css("background-color","#f7f7f7");
//        })
//    });


        $(document).ready(function () {
            $("#loadingDiv").hide();
        })

    /**
     * 跳转到添加接口页面
     */
    function toApiAddPage() {
        var id = $("#itemIdInput").val();
        location.href = "<%=request.getContextPath()%>/api/toApiAddPage?itemId="+id;
    }
    /**
     * 执行接口
     * @param id 接口
     */
    function executeApi(id) {
        $('#loadingDiv').show();

        var itemId = $("#itemIdInput").val();
        $.ajax({
            url: "/api/executeApi?apiId=" + id + "&itemId=" + itemId,
            type: 'POST',
            success: function (data) {
                //成功后 隐藏loading框，
                $('#loadingDiv').hide();
                //to do something
                window.location.reload();//刷新页面
            }
        })
    }
    /**
     * 删除接口
     * @param id 接口id
     */
    function deleteApi(id) {
        var itemId = $("#itemIdInput").val();
        location.href="/api/deleteApi?apiId="+id+ "&itemId=" + itemId;
    }
    /**
     * 获取某个接口的详情
     * @param id 接口id
     */
    function getApiDetail(id) {
        location.href="/api/getApiDetail?id="+id;
    }

</script>
</body>
</html>

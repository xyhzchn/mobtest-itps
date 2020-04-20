<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:include page="header.jsp"/>
<jsp:include page="left.jsp"/>
<!-- 主要内容区域-->
<div class="content-wrapper">
    <!-- 面包屑 -->
    <section class="content-header">
        <ol class="breadcrumb">
            <li><a href="/week/getAllWeek"><i class="fa fa-dashboard"></i> 周报管理</a></li>
            <li class="active">周报列表</li>
        </ol>
    </section>

    <!-- 主要内容 -->
    <section class="content container-fluid">
        <div class="context">
            <form id="weeklyForm" method="post">
                <div class="searchArea">
                    <div class="realSearchArea">
                        <table>
                            <tbody>
                            <tr>
                                <td>周ID:</td>
                                <td><input type="text" class="textStyle" placeholder="周ID" name="weeklyId"/></td>
                                <%--<td style="width:10px"></td>--%>
                                <%--<td>xxxxx</td>--%>
                                <%--<td><input type="text" class="textStyle"/></td>--%>
                            </tr>
                            </tbody>
                        </table>
                        <div class="textMar">
                            <input type="button" class="btnStyle addBtnStyle" value="检   索">
                        </div>
                    </div>
                </div>

                <div class="dataArea">
                    <div class="textMar" >
                        <table class="table" id="queryCondition">
                            <tbody  class="tbd">
                            <tr>
                                <td align="right" style="padding-right: 2px;width:30%">
                                    <input type="file" name="myfiles" id="myfiles" style="display: none;" onchange="document.getElementById('filePath').value=this.value"/>
                                    <div class="input-group">
                                        <input type='text' name='filePath' id='filePath' class='form-control' style="height:30px"/>
                                        <span class="input-group-btn">
                                            <button type="button" class="btn btn-sm btn-info blue" id="btn_check">
                                                <i class="icon-edit">请选择文件</i>
                                            </button>
						                </span>
                                    </div>
                                </td>
                                <td align="left" style="padding-left: 2px">
                                    <button type="button" class="btn btn-sm btn-info" id="upload">
                                        <i class="upload-icon icon-cloud-upload bigger-110">导入文件</i>
                                    </button>
                                </td>
                            </tr>
                            </tbody>
                        </table>
                    </div>

                    <!--周报列表相关-->
                    <div class="textMar">
                        <span style="font-size: 12px;margin-left:67%;margin-right: 10%;color: #ee782d">共计为您搜索到：${page.total}条数据；每页显示：${page.pageSize }条;共${page.pages}页;</span>
                        <table border="0" cellpadding="0" cellspacing="0">
                            <tr>
                                <th>ID</th>
                                <th>周报开始日期</th>
                                <th>周报结束日期</th>
                                <th colspan="2">操作</th>
                            </tr>
                            <c:forEach items="${page.list}"  var="week">
                                <tr>
                                    <td>${week.weekId}</td>
                                    <td><fmt:formatDate value="${week.startDate}" pattern="yyyy-MM-dd" /> </td>
                                    <td><fmt:formatDate value="${week.endDate}" pattern="yyyy-MM-dd"/> </td>
                                    <td><img src="../img/edit.png" class="imgStyle" onclick="weeklyDetail(${week.weekId})"></td>
                                    <td><img src="../img/del.png" class="imgStyle"></td>
                                </tr>
                            </c:forEach>
                        </table>
                        <!--分页 -->
                        <div style="width:380px;margin:0 auto;margin-top:10px;">
                            <a href="<%=request.getContextPath()%>/week/getAllWeek?currentPage=${page.firstPage}">首页</a>
                            <a href="<%=request.getContextPath()%>/week/getAllWeek?currentPage=${page.prePage}">上一页</a>
                            <a href="<%=request.getContextPath()%>/week/getAllWeek?currentPage=${page.nextPage}">下一页</a>
                            <a href="<%=request.getContextPath()%>/week/getAllWeek?currentPage=${page.lastPage}">尾页</a>
                        </div>
                    </div>
                </div>
            </form>
        </div>
    </section>
</div>
<jsp:include page="footer.jsp"/>
<script type="text/javascript">
    $(function() {
        $("#btn_check").click(function() {
            $("#myfiles").trigger('click');
        });

        $("#upload").click(function () {
            var myfiles = $("#filePath").val();
            //如果文件为空
            if (myfiles == ""){
                alert("请选择上传文件");
            }else{
                //获取文件后缀名
                var index1 = myfiles.lastIndexOf(".");
                var index2 = myfiles.length;
                var fileType = myfiles.substring(index1,index2);
                //对文件后缀名进行判断
                if(fileType != ".xls" && fileType != ".xlsx"){
                    alert("不支持该文件类型，请上传后缀名为.xls或.xlsx的文件");
                }else{
                    //实现文件上传操作
                    $("#weeklyForm").attr("action","/week/uploadFile");
                    $("#weeklyForm").attr("enctype","multipart/form-data");
                    $("#weeklyForm").submit();
                }
            }
        });
//        $("#filePath").click(function() {
//            $("#myfiles").trigger('click');
//        });
    });
    function weeklyDetail(weekId) {
        location.href = "<%=request.getContextPath()%>/week/getWeeklyDetail?weekId="+weekId;
    }
</script>
</body>
</html>

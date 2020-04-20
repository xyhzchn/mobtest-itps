<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="header.jsp"/>
<jsp:include page="left.jsp"/>
<!-- 主要内容区域-->
<div class="content-wrapper">
    <!-- 面包屑 -->
    <section class="content-header">
        <ol class="breadcrumb">
            <li><a href="###"><i class="fa fa-dashboard"></i> 参数化配置</a></li>
            <li class="active">参数列表</li>
        </ol>
    </section>

    <!-- 主要内容 -->
    <section class="content container-fluid">
        <div class="context">
            <form id="itemListForm" method="post" action="###">
                <div class="searchArea">
                    <div class="realSearchArea">
                        <table>
                            <tbody>
                            <tr>
                                <td>参数ID:</td>
                                <td><input type="text" class="textStyle" placeholder="参数ID" name="itemId"/></td>
                                <td style="width:10px"></td>
                                <td>参数名称</td>
                                <td><input type="text" class="textStyle" placeholder="参数名称" name="itemName"/></td>
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
                        <input type="button" class="btnStyle addBtnStyle" value="添  加" onclick="toAddParamPage()">
                    </div>

                    <!--项目列表相关-->
                    <div class="textMar">
                        <table border="0" cellpadding="0" cellspacing="0">
                            <tr>
                                <th>ID</th>
                                <th>名称</th>
                                <th>描述</th>
                                <th>创建者</th>
                                <th>创建时间</th>
                                <th>修改时间</th>
                                <th colspan="2">操作</th>
                            </tr>
                            <%--<c:forEach items="${itemList}" var="item" >--%>
                                <%--<tr>--%>
                                    <%--<td>${item.itemId}</td>--%>
                                    <%--<td>${item.itemName}</td>--%>
                                    <%--<td style="width: 400px;">${item.itemDesc}</td>--%>
                                    <%--<td>${item.userName}</td>--%>
                                    <%--<td>${item.createTime}</td>--%>
                                    <%--<td>${item.updateTime}</td>--%>
                                    <%--<td><img src="../img/edit.png" class="imgStyle" onclick="getItemDetail(${item.itemId})"></td>--%>
                                    <%--<td><img src="../img/del.png" class="imgStyle" onclick="deleteItemFun(${item.itemId})"></td>--%>
                                <%--</tr>--%>
                            <%--</c:forEach>--%>

                        </table>
                    </div>
                </div>
            </form>
        </div>
    </section>
</div>
<jsp:include page="footer.jsp"/>
<script type="text/javascript">
    function toAddParamPage() {
        self.location='<%=request.getContextPath()%>/param/toAddParamPage';
    }
   </script>
</body>
</html>

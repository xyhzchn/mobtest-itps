<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="header.jsp"/>
<jsp:include page="left.jsp"/>
<!-- 主要内容区域-->
<div class="content-wrapper">
    <!-- 面包屑 -->
    <section class="content-header">
        <ol class="breadcrumb">
            <li><a href="<%=request.getContextPath()%>/item/getItems"><i class="fa fa-dashboard"></i> 项目管理</a></li>
            <li class="active">项目列表</li>
        </ol>
    </section>

    <!-- 主要内容 -->
    <section class="content container-fluid">
        <div class="context">
            <form id="itemListForm" method="post" action="<%=request.getContextPath()%>/item/getItems">
                <div class="searchArea">
                    <div class="realSearchArea">
                        <table>
                            <tbody>
                            <tr>
                                <td>项目ID:</td>
                                <td><input type="text" class="textStyle" placeholder="项目ID" name="itemId"/></td>
                                <td style="width:10px"></td>
                                <td>项目名称</td>
                                <td><input type="text" class="textStyle" placeholder="项目名称" name="itemName"/></td>
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
                        <input type="button" class="btnStyle addBtnStyle" value="添  加" onclick="toAddItemPage()">
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
                            <c:forEach items="${itemList}" var="item" >
                                <tr>
                                    <td>${item.itemId}</td>
                                    <td>${item.itemName}</td>
                                    <td style="width: 400px;">${item.itemDesc}</td>
                                    <td>${item.userName}</td>
                                    <td>${item.createTime}</td>
                                    <td>${item.updateTime}</td>
                                    <td><img src="<%=request.getContextPath()%>/img/edit.png" class="imgStyle" onclick="getItemDetail(${item.itemId})"></td>
                                    <td><img src="<%=request.getContextPath()%>/img/del.png" class="imgStyle" onclick="deleteItemFun(${item.itemId})"></td>
                                </tr>
                            </c:forEach>

                        </table>
                    </div>
                </div>
            </form>
        </div>
    </section>
</div>
<jsp:include page="footer.jsp"/>
<script type="text/javascript">
    function toAddItemPage() {
        self.location='<%=request.getContextPath()%>/item/toAddItemPage';
    }
    function getItemDetail(id) {
        self.location = "<%=request.getContextPath()%>/item/getItemDetail?id="+id;
    }
    function deleteItemFun(id) {
        //弹出框
        var isDel = confirm("您确定要删除该项目吗？\n \n删除后该项目下的所有接口将都不可用！");
        //判断
        if (isDel){
            self.location = "<%=request.getContextPath()%>/item/deleteItem?id="+id;
        }else{
            return isDel;
        }
    }
</script>
</body>
</html>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="header.jsp"/>
<jsp:include page="left.jsp"/>
    <!-- 主要内容区域-->
    <div class="content-wrapper">
        <!-- 面包屑 -->
        <section class="content-header">
            <ol class="breadcrumb">
                <li><a href="#"><i class="fa fa-dashboard"></i> 用户管理</a></li>
                <li class="active">用户列表</li>
            </ol>
        </section>

        <!-- 主要内容 -->
        <section class="content container-fluid">
            <div class="context">
                <form id="userListForm" method="post" action="<%=request.getContextPath()%>/user/userList">
                    <div class="searchArea">
                        <div class="realSearchArea">
                            <table>
                                <tbody>
                                <tr>
                                    <td>用户ID:</td>
                                    <td><input type="text" class="textStyle" placeholder="用户ID" name="userId"/></td>
                                    <td style="width:10px"></td>
                                    <td>真实姓名</td>
                                    <td><input type="text" class="textStyle" placeholder="真实姓名" name="realName"/></td>
                                </tr>
                                <tr>
                                    <td>昵&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;称:</td>
                                    <td><input type="text" class="textStyle" placeholder="昵称" name="nickName"/></td>
                                    <td style="width:10px"></td>
                                    <td>角&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;色</td>
                                    <td>
                                        <select name="roleId">
                                            <option value="0" selected>请选择角色</option>
                                            <c:forEach items="${roleMap}" var="item" >
                                                <option value="${item.key}">${item.value}</option>
                                            </c:forEach>
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
                            <input type="button" class="btnStyle addBtnStyle" value="添  加" onclick="toUserAddPage()">
                        </div>

                        <!--用户列表相关-->
                        <div class="textMar">
                            <table border="0" cellpadding="0" cellspacing="0">
                                <tr>
                                    <th>ID</th>
                                    <th>用户名</th>
                                    <th>真实姓名</th>
                                    <th>昵称</th>
                                    <th>座位号</th>
                                    <th>电话</th>
                                    <th>邮箱</th>
                                    <th>角色</th>
                                    <th>修改时间</th>
                                    <th colspan="2">操作</th>
                                </tr>
                                <c:forEach items="${userList}" var="user">
                                    <tr>
                                        <td>${user.userId}</td>
                                        <td>${user.userName}</td>
                                        <td>${user.realName}</td>
                                        <td>${user.nickName}</td>
                                        <td>${user.seatNum}</td>
                                        <td>${user.phone}</td>
                                        <td>${user.email}</td>
                                        <td>${user.roleName}</td>
                                        <td>${user.updateTime}</td>
                                        <td><img src="<%=request.getContextPath()%>/img/edit.png" class="imgStyle" onclick="getUserDetail(${user.userId})"></td>
                                        <c:if test="${user.isDelete == 0}">
                                            <td><img src="<%=request.getContextPath()%>/img/enable.png" class="imgStyle" onclick="userDisable(${user.userId})"></td>
                                        </c:if>
                                        <c:if test="${user.isDelete == 1}">
                                            <td><img src="<%=request.getContextPath()%>/img/disable.png" class="imgStyle" onclick="userEnable(${user.userId})"></td>
                                        </c:if>
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
    function toUserAddPage() {
        location.href = "<%=request.getContextPath()%>/user/toUserAddPage"
    }

    function getUserDetail(id) {
        location.href = "<%=request.getContextPath()%>/user/getUserDetail?id="+id;
    }

    function userDisable(id) {
        location.href="<%=request.getContextPath()%>/user/setUserDisable?id="+id;
    }
    function userEnable(id) {
        location.href="<%=request.getContextPath()%>/user/setUserEnable?id="+id;
    }
</script>
</body>
</html>

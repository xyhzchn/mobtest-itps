<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="header.jsp"/>
<jsp:include page="left.jsp"/>
    <!-- 主要内容区域-->
    <div class="content-wrapper">
        <!-- 面包屑 -->
        <section class="content-header">
            <ol class="breadcrumb">
                <li><a href="<%=request.getContextPath()%>/role/getRoles"><i class="fa fa-dashboard"></i> 角色管理</a></li>
                    <li class="active">角色列表</li>
            </ol>
        </section>

        <!-- 主要内容 -->
        <section class="content container-fluid">
            <div class="context">
                <form id="roleListForm" method="post" action="<%=request.getContextPath()%>/role/getRoles">
                    <div class="searchArea">
                      <div class="realSearchArea">
                            <table>
                                <tbody>
                                    <tr>
                                        <td>角色ID:</td>
                                        <td><input type="text" class="textStyle" placeholder="角色ID" name="roleId"/></td>
                                        <td style="width:10px"></td>
                                        <td>角色名称</td>
                                        <td><input type="text" class="textStyle" placeholder="角色名称" name="roleName"/></td>
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
                      <input type="button" class="btnStyle addBtnStyle" value="添  加" onclick="toAddRolePage()">
                    </div>

                    <!--角色列表相关-->
                    <div class="textMar">
                        <table border="0" cellpadding="0" cellspacing="0">
                          <tr>
                            <th>ID</th>
                            <th>名称</th>
                            <th>描述</th>
                            <th>创建时间</th>
                            <th>修改时间</th>
                            <th colspan="2">操作</th>
                          </tr>
                            <c:forEach items="${roleList}" var="role">
                            <tr>
                                <td>${role.roleId}</td>
                                <td>${role.roleName}</td>
                                <td>${role.roleDesc}</td>
                                <td>${role.createTime}</td>
                                <td>${role.updateTime}</td>
                                <td><img src="<%=request.getContextPath()%>/img/edit.png" class="imgStyle" onclick="getRoleDetail(${role.roleId})"></td>
                                <td><img src="<%=request.getContextPath()%>/img/del.png" class="imgStyle" onclick="deleteRoleFun(${role.roleId})"></td>
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
    function toAddRolePage() {
        self.location='<%=request.getContextPath()%>/role/toAddRolePage';
    }
    function getRoleDetail(id) {
        self.location = "<%=request.getContextPath()%>/role/getObjDetail?id="+id;
    }
    function deleteRoleFun(id) {
        //弹出框
        var isDel = confirm("您确定要删除该角色吗？\n \n删除后该角色下的所有用户将都不可用！");
        //判断
        if (isDel){
            self.location = "<%=request.getContextPath()%>/role/deleteRole?id="+id;
        }else{
            return isDel;
        }

    }
</script>
</body>
</html>

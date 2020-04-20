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
                <c:if test="${flag.equals('add')}">
                    <li class="active">添加角色</li>
                </c:if>
                <c:if test="${flag.equals('detail')}">
                    <li class="active">编辑角色</li>
                </c:if>
            </ol>
        </section>
        <!-- 主要内容 -->
        <section class="content container-fluid">
          <div class="context">
          <!--面包屑-->
              <c:if test="${flag.equals('add')}">
                <form id="roleForm" method="post" action="<%=request.getContextPath()%>/role/addRole">
                     <div class="addArea">
                        <div class="realAddArea">
                            <div class="textMar">
                              <label>角色名称：</label>
                              <input type="text" placeholder="请输入角色名称" class="textStyle" name="roleName" />
                            </div>
                            <div class="textMar">
                              <label>角色描述：</label><br>
                              <textarea placeholder="请输入角色描述信息" class="textareaStyle" name="roleDesc"></textarea>
                            </div>
                            <div class="textMar" style="margin-left:60px">
                              <input type="button" value="取   消" class="btnStyle" style="margin-right:30px" onclick="location.href='<%=request.getContextPath()%>/role/getRoles'">
                              <input type="submit" value="保   存" class="btnStyle" />
                            </div>
                          </div>
                      </div>
                </form>
              </c:if>
              <c:if test="${flag.equals('detail')}">
                  <form id="roleForm" method="post" action="<%=request.getContextPath()%>/role/editRole">
                      <div class="addArea">
                          <div class="realAddArea">
                              <div class="textMar">
                                  <label>角色ID：</label>
                                  <input type="text" id="roleIdInput" placeholder="请输入角色名称" class="textStyle" name="roleId" value="${role.roleId}" readonly="readonly" style="border: none;"/>
                              </div>
                              <div class="textMar">
                                  <label>角色名称：</label>
                                  <input type="text" id="roleNameInput" placeholder="请输入角色名称" class="textStyle" name="roleName" value="${role.roleName}" readonly="readonly" style="border: none;"/>
                              </div>
                              <div class="textMar">
                                  <label>角色描述：</label><br>
                                  <textarea  id="roleDescInput" placeholder="请输入角色描述信息" class="textareaStyle" name="roleDesc" readonly="readonly" style="border: none;">${role.roleDesc}</textarea>
                              </div>
                              <div class="textMar" style="margin-left:60px">
                                  <input type="button" value="取   消" class="btnStyle" style="margin-right:30px" onclick="location.href='<%=request.getContextPath()%>/role/getRoles'">
                                  <input id="editBtn" type="button" value="修   改" class="btnStyle" onclick="updateRole()">
                                  <input id="saveBtn" type="submit" value="保   存" class="btnStyle" style="display: none"/>
                              </div>
                          </div>
                      </div>
                  </form>
              </c:if>
          </div>
        </section>
    </div>
<jsp:include page="footer.jsp"/>
<script type="text/javascript">
    $(document).ready(function () {
        //验证用户名和密码
        $('#roleForm').validate({
            rules:{
                "roleName":{required:true},
                "roleDesc":{maxlength:200}
            },
            messages:{
                "roleName":{required:"*角色名不可为空"},
                "roleDesc":{maxlength:"*角色描述最多200个字符"}
            }
        })
    });

    function updateRole() {
        //当点击修改按钮时，将输入框设置为可编辑。将按钮变为 保存
        $("#roleNameInput").removeAttr("readonly");
        $("#roleNameInput").removeAttr("style");
        $("#roleDescInput").removeAttr("readonly");
        $("#roleDescInput").removeAttr("style");
        //保存按钮可见
        $("#saveBtn").removeAttr("style");
        //修改按钮不可见
        $("#editBtn").attr("style","display:none");
    }
</script>
</body>
</html>


<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="header.jsp"/>
<jsp:include page="left.jsp"/>
    <!-- 主要内容区域-->
    <div class="content-wrapper">
        <!-- 面包屑 -->
        <section class="content-header">
            <ol class="breadcrumb">
                <li><a href="<%=request.getContextPath()%>/user/userList"><i class="fa fa-dashboard"></i> 用户管理</a></li>
                <c:if test="${flag.equals('add')}">
                    <li class="active">添加用户</li>
                </c:if>
                <c:if test="${flag.equals('detail')}">
                    <li class="active">编辑用户</li>
                </c:if>
            </ol>
        </section>

        <!-- 主要内容 -->
        <section class="content container-fluid">
          <div class="context">
            <c:if test="${flag.equals('add')}">
                <form id="userForm" method="post" action="<%=request.getContextPath()%>/user/addUser">
                 <div class="addArea">
                    <div class="realAddArea">
                        <div class="textMar">
                          <label>用&nbsp;&nbsp;户&nbsp;&nbsp;名：</label>
                          <input type="text" placeholder="请输用户名" class="textStyle" name="userName"/>
                        </div>
                        <div class="textMar">
                          <label>真实姓名：</label>
                          <input type="text" placeholder="请输入姓名" class="textStyle" name="realName"/>
                        </div>
                         <div class="textMar">
                          <label>昵&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;称：</label>
                          <input type="text" placeholder="请输入昵称" class="textStyle" name="nickName"/>
                        </div>
                         <div class="textMar">
                          <label>密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码：</label>
                          <input type="text" placeholder="请输入密码" class="textStyle" name="password"/>
                        </div>
                         <div class="textMar">
                          <label>座&nbsp;位&nbsp;号：</label>
                          <input type="text" placeholder="请输入座位号" class="textStyle" name="seatNum"/>
                        </div>
                         <div class="textMar">
                          <label>电&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;话：</label>
                          <input type="text" placeholder="请输入电话" class="textStyle" name="phone"/>
                        </div>
                         <div class="textMar">
                          <label>邮箱地址：</label>
                          <input type="text" placeholder="请输入邮箱地址" class="textStyle" name="email"/>
                        </div>
                         <div class="textMar">
                          <label>角&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;色：</label>
                             <select name="roleId">
                                 <option value="0" selected>请选择角色</option>
                                 <c:forEach items="${roleMap}" var="item" >
                                     <option value="${item.key}">${item.value}</option>
                                 </c:forEach>
                             </select>
                        </div>
                        <div class="textMar">
                          <input type="button" value="取   消" class="btnStyle" style="margin-right:30px" onclick="location.href='<%=request.getContextPath()%>/user/userList'">
                          <input type="submit" value="保   存" class="btnStyle"/>
                        </div>
                     </div>
                  </div>
                </form>
            </c:if>
            <c:if test="${flag.equals('detail')}">
                  <form id="userForm" method="post" action="<%=request.getContextPath()%>/user/editUser">
                      <div class="addArea">
                          <div class="realAddArea">
                              <div class="textMar">
                                  <label>用&nbsp;&nbsp;户&nbsp;&nbsp;ID：</label>
                                  <input type="text" id="userIdInput"  placeholder="请输用户名" class="textStyle" name="userId" value="${user.userId}" style="border: none;" readonly="readonly"/>
                              </div>
                              <div class="textMar">
                                  <label>用&nbsp;&nbsp;户&nbsp;&nbsp;名：</label>
                                  <input type="text" id="userNameInput"  placeholder="请输用户名" class="textStyle" name="userName" value="${user.userName}" style="border: none;" readonly="readonly"/>
                              </div>
                              <div class="textMar">
                                  <label>真实姓名：</label>
                                  <input type="text" id="realNameInput"  placeholder="请输入姓名" class="textStyle" name="realName" value="${user.realName}" style="border: none;" readonly="readonly"/>
                              </div>
                              <div class="textMar">
                                  <label>昵&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;称：</label>
                                  <input type="text" id="nickNameInput"  placeholder="请输入昵称" class="textStyle" name="nickName" value="${user.nickName}" style="border: none;" readonly="readonly"/>
                              </div>
                              <div class="textMar">
                                  <label>座&nbsp;&nbsp;位&nbsp;&nbsp;号：</label>
                                  <input type="text" id="seatNumInput"  placeholder="请输入座位号" class="textStyle" name="seatNum" value="${user.seatNum}" style="border: none;" readonly="readonly"/>
                              </div>
                              <div class="textMar">
                                  <label>电&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;话：</label>
                                  <input type="text" id="phoneInput"  placeholder="请输入电话" class="textStyle" name="phone" value="${user.phone}" style="border: none;" readonly="readonly"/>
                              </div>
                              <div class="textMar">
                                  <label>邮箱地址：</label>
                                  <input type="text" id="emailInput"  placeholder="请输入邮箱地址" class="textStyle" name="email" value="${user.email}" style="border: none;" readonly="readonly"/>
                              </div>
                              <div class="textMar">
                                  <label>角&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;色：</label>
                                  <input type="text" id="roleNameInput"  class="textStyle" value="${user.roleName}" style="border: none;" readonly="readonly"/>
                                  <input type="hidden" id="roleIdInput" value="${user.roleId}">
                                  <select name="roleId" id="roleNameSelect" style="display: none;">
                                      <option value="0">请选择角色</option>
                                      <c:forEach items="${roleMap}" var="item" >
                                          <option value="${item.key}">${item.value}</option>
                                      </c:forEach>
                                  </select>
                              </div>
                              <div class="textMar">
                                  <input type="button" value="取   消" class="btnStyle" style="margin-right:30px" onclick="location.href='<%=request.getContextPath()%>/user/userList'">
                                  <input type="button" id="editBtn" value="修   改" class="btnStyle" onclick="updateUser()">
                                  <input type="submit" id="saveBtn" value="保   存" class="btnStyle" style="display: none;"/>
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
        // add the rule here
        $.validator.addMethod("valueNotEquals", function(value, element, arg){
            return arg !== value;
        }, "Value must not equal arg.");

        // configure your validation
//        $("form").validate({
//            rules: {
//                SelectName: { valueNotEquals: "default" }
//            },
//            messages: {
//                SelectName: { valueNotEquals: "Please select an item!" }
//            }
//        });
        //验证用户名和密码
        $('#userForm').validate({
            rules:{
                "userName":{required:true},
                "realName":{
                    required:true,
                    minlength:2,
                    maxlength:5
                },
                "password":{
                    required:true,
                    minlength:6,
                    maxlength:12
                },
                "seatNum":{
                    required:true,
                    number:true,
                    maxlength:8
                },
                "phone":{
                    required:true,
                    number:true,
                    minlength:11,
                    maxlength:11
                },
                "email":{
                    required:true,
                    email:true
                },
                roleId:{ valueNotEquals: "0" }
            },
            messages:{
                "userName":{required:"*用户名不可为空"},
                "realName":{
                    required:"*真实姓名不可为空",
                    minlength:"*真实姓名至少2个字符",
                    maxlength:"*真实姓名最多5个字符"
                },
                "password":{
                    required:"*密码不可为空",
                    minlength:"*密码最少6位",
                    maxlength:"*密码最多12位"
                },
                "seatNum":{
                    required:"*座位号不可为空",
                    number:"*座位号只可为数字",
                    maxlength:"*座位号最多8位"
                },
                "phone":{
                    required:"*电话不可为空",
                    number:"*电话只可为数字",
                    minlength:"*电话最少11位",
                    maxlength:"*电话最多11位"
                },
                "email":{
                    required:"*邮箱地址不可为空",
                    email:"*邮箱格式不正"
                },
                roleId:{ valueNotEquals: "*请选择角色！" }
            }
        })
    })
    /**
     * 点击修改按钮时
     */
    function updateUser() {
        //当点击修改按钮时，将输入框设置为可编辑。将按钮变为 保存
        $("#userNameInput").removeAttr("readonly");
        $("#userNameInput").removeAttr("style");
        $("#realNameInput").removeAttr("readonly");
        $("#realNameInput").removeAttr("style");
        $("#nickNameInput").removeAttr("readonly");
        $("#nickNameInput").removeAttr("style");
        $("#seatNumInput").removeAttr("readonly");
        $("#seatNumInput").removeAttr("style");
        $("#phoneInput").removeAttr("readonly");
        $("#phoneInput").removeAttr("style");
        $("#emailInput").removeAttr("readonly");
        $("#emailInput").removeAttr("style");

        //角色下拉框可见
        $("#roleNameSelect").removeAttr("style");
        var roleId = $('#roleIdInput').val();
        $("#roleNameSelect").val(roleId);
        //角色信息不可见
        $("#roleNameInput").attr("style","display:none");
        //保存按钮可见
        $("#saveBtn").removeAttr("style");
        //修改按钮不可见
        $("#editBtn").attr("style","display:none");
    }
</script>
</body>
</html>

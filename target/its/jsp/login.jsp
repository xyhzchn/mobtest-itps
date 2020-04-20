<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  <title>登录</title>
  <link rel="stylesheet" href="<%=request.getContextPath()%>/css/common.css" type="text/css"/>
  <script src="<%=request.getContextPath()%>/js/jquery-3.2.1.js"></script>
  <script src="<%=request.getContextPath()%>/js/jquery.validate.js"></script>
</head>
<body style="background-color:#003d79">
    <form id="loginForm" method="post" action="<%=request.getContextPath()%>/login">
      <div id="loginDiv">

       <div style="margin-top:50px">
          <img src="<%=request.getContextPath()%>/img/logo.png">
       </div>

        <div style="padding-top: 30px;">
          <input type="text"  placeholder="请输入账号" class="loginNameCss" name="userName"/>
        </div>

        <div style="padding-top: 30px;">
          <input type="password"  placeholder="请输入密码" class="loginPswdCss" name="password"/>
        </div>

        <div id="forgetPswd">
          <a href="<%=request.getContextPath()%>/jsp/findPswd.jsp" style="">忘记密码？</a>
        </div>
        <c:if test="${!(msg.equals(''))}">
            <span class="error">${msg}</span>
        </c:if>
        <div style="padding-top: 20px;">
          <input type="submit" class="loginBtnStyle" value="登   录"/>
        </div>

      </div>
    </form>
    <script type="text/javascript">
        $(document).ready(function () {
            //验证用户名和密码
            $('#loginForm').validate({
                rules:{
                    "userName":{required:true},
                    "password":{required:true}
                },
                messages:{
                    "userName":{required:"*用户名不可为空"},
                    "password":{required:"*密码不可为空"}
                }
            })
        })
    </script>
</body>
</html>


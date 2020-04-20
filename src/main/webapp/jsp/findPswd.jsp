<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>找回密码</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/common.css">
<script src="<%=request.getContextPath()%>/js/jquery-3.2.1.js"></script>
<script src="<%=request.getContextPath()%>/js/jquery.validate.js"></script>
</head>

<body>
    <div class="context">
        <form id="findForm" method="post" action="<%=request.getContextPath()%>/toSetPswd">
            <div class="dataArea" style="margin-top:10%">
                <div class="realAddArea" style="padding:5% 0 0 40%">
                    <div class="forgetPswdDiv">
                        <span><h1 style="margin-left:50px;">找回密码</h1></span>

                         <div style="padding-top: 20px;">
                            <input type="text"  placeholder="请输入账号" class="loginNameCss" name="userName"/>
                         </div>
                        <c:if test="${!(msg.equals(''))}">
                            <span class="error">${msg}</span>
                        </c:if>
                        <div style="padding-top: 20px;">
                          <input type="submit" class="loginBtnStyle" value="找  回  密  码"/>
                        </div>
                     </div>
                </div>
            </div>
        </form>
    </div>
    <script type="text/javascript">
        $(document).ready(function () {
            //验证用户名和密码
            $('#findForm').validate({
                rules:{
                    "userName":{required:true}
                },
                messages:{
                    "userName":{required:"*账号不可为空"}
                }
            })
        })
    </script>
</body>
</html>

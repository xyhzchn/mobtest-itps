<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>重置密码</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/common.css">
<script src="<%=request.getContextPath()%>/js/jquery-3.2.1.js"></script>
<script src="<%=request.getContextPath()%>/js/jquery.validate.js"></script>
</head>

<body>
    <div class="context">
        <form id="resetForm" method="post" action="<%=request.getContextPath()%>/resetPswd">
            <div class="dataArea" style="margin-top:10%">
                <div class="realAddArea" style="padding:5% 0 0 40%">
                    <div class="forgetPswdDiv">
                        <span><h1 style="margin-left:50px;">重置密码</h1></span>

                         <div style="padding-top: 20px;">
                          <input type="password" id="password" placeholder="请输入密码" class="loginPswdCss" name="password"/>
                        </div>
                         <div style="padding-top: 20px;">
                          <input type="password"  placeholder="请输入确认密码" class="loginPswdCss" name="confirm_password"/>
                        </div>
                            <input type="hidden" name="userName" value="${user.userName}">
                        <div style="padding-top: 20px;">
                          <input type="submit" class="loginBtnStyle" value="重  置  密  码"/>
                        </div>
                     </div>
                </div>
            </div>
        </form>
    </div>
</body>
<script type="text/javascript">
    $(document).ready(function () {
        //验证用户名和密码
        $('#resetForm').validate({
            rules:{
                password: {
                    required:true,
                    minlength:6,
                    maxlength:12
                },
                confirm_password: {
                    required:true,
                    minlength:6,
                    maxlength:12,
                    equalTo: "#password"
                }
            },
            messages:{
                password: {
                    required:"*密码不可为空",
                    minlength:"*密码最少6位",
                    maxlength:"*密码最多12位"
                },
                confirm_password: {
                    required:"*密码不可为空",
                    minlength:"*密码最少6位",
                    maxlength:"*密码最多12位",
                    equalTo: "两次密码不一致"
                }
            }
        })
    })
</script>
</html>

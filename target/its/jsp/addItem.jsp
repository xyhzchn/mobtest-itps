<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="header.jsp"/>
<jsp:include page="left.jsp"/>
<!-- 主要内容区域-->
<div class="content-wrapper">
    <!-- 面包屑 -->
    <section class="content-header">
        <ol class="breadcrumb">
            <li><a href="/item/getItems"><i class="fa fa-dashboard"></i> 项目管理</a></li>
            <c:if test="${flag.equals('add')}">
                <li class="active">添加项目</li>
            </c:if>
            <c:if test="${flag.equals('detail')}">
                <li class="active">编辑项目</li>
            </c:if>
        </ol>
    </section>
    <!-- 主要内容 -->
    <section class="content container-fluid">
        <div class="context">
            <!--面包屑-->
            <c:if test="${flag.equals('add')}">
                <form id="itemForm" method="post" action="<%=request.getContextPath()%>/item/addItem">
                    <div class="addArea">
                        <div class="realAddArea">
                            <div class="textMar">
                                <label>项目名称：</label>
                                <input type="text" placeholder="请输入项目名称" class="textStyle" name="itemName" />
                            </div>
                            <div class="textMar">
                                <label>项目描述：</label><br>
                                <textarea placeholder="请输入项目描述信息" class="textareaStyle" name="itemDesc"></textarea>
                            </div>
                            <div class="textMar" style="margin-left:60px">
                                <input type="button" value="取   消" class="btnStyle" style="margin-right:30px" onclick="location.href='/item/getItems'">
                                <input type="submit" value="保   存" class="btnStyle" />
                            </div>
                        </div>
                    </div>
                </form>
            </c:if>
            <c:if test="${flag.equals('detail')}">
                <form id="itemForm" method="post" action="<%=request.getContextPath()%>/item/editItem">
                    <div class="addArea">
                        <div class="realAddArea">
                            <div class="textMar">
                                <label>项目ID：</label>
                                <input type="text" id="itemIdInput"  class="textStyle" name="itemId" value="${item.itemId}" readonly="readonly" style="border: none;"/>
                            </div>
                            <div class="textMar">
                                <label>项目名称：</label>
                                <input type="text" id="itemNameInput" placeholder="请输入项目名称" class="textStyle" name="itemName" value="${item.itemName}" readonly="readonly" style="border: none;"/>
                            </div>
                            <div class="textMar">
                                <label>项目描述：</label><br>
                                <textarea  id="itemDescInput" placeholder="请输入项目描述信息" class="textareaStyle" name="itemDesc" readonly="readonly" style="border: none;">${item.itemDesc}</textarea>
                            </div>
                            <div class="textMar" style="margin-left:60px">
                                <input type="button" value="取   消" class="btnStyle" style="margin-right:30px" onclick="location.href='/item/getItems'">
                                <input id="editBtn" type="button" value="修   改" class="btnStyle" onclick="updateItem()">
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
        $('#itemForm').validate({
            rules:{
                "itemName":{required:true},
                "itemDesc":{
                    required:true,
                    maxlength:200
                }
            },
            messages:{
                "itemName":{required:"*项目名称不可为空"},
                "itemDesc":{
                    required:"*项目描述不可为空",
                    maxlength:"*项目描述最多200个字符"
                }
            }
        })
    });

    function updateItem() {
        //当点击修改按钮时，将输入框设置为可编辑。将按钮变为 保存
        $("#itemNameInput").removeAttr("readonly");
        $("#itemNameInput").removeAttr("style");
        $("#itemDescInput").removeAttr("readonly");
        $("#itemDescInput").removeAttr("style");
        //保存按钮可见
        $("#saveBtn").removeAttr("style");
        //修改按钮不可见
        $("#editBtn").attr("style","display:none");
    }
</script>
</body>
</html>


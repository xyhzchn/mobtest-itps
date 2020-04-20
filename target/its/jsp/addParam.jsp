<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="header.jsp"/>
<jsp:include page="left.jsp"/>
<!-- 主要内容区域-->
<div class="content-wrapper">
    <!-- 面包屑 -->
    <section class="content-header">
        <ol class="breadcrumb">
            <li><a href="/param/getParams"><i class="fa fa-dashboard"></i> 参数化配置</a></li>
            <li class="active">添加参数</li>
        </ol>
    </section>
    <!-- 主要内容 -->
    <section class="content container-fluid">
        <div class="context">
            <!--面包屑-->
            <form id="paramForm" method="post" action="/param/addParam">
                <div class="addArea">
                    <div class="realAddArea">
                        <div class="textMar">
                            <label>参数名称</label><br>
                            <input type="text" placeholder="请输入参数名称" class="textStyle" id="paramName" name="paramName" style="margin-left: 0;"/>
                        </div>
                        <div class="textMar">
                            <label>参数描述</label><br>
                            <textarea placeholder="请输入参数描述信息" class="textareaStyle" id="paramDesc" name="paramDesc" style="margin-left: 0;"></textarea>
                        </div>
                        <div class="textMar">
                            <label >参数类型</label>
                            <i class="fa fa-fw fa-question-circle" title=""></i><br>
                            <br>
                            <div id="tab-demo">
                                <ul class="tab-title">
                                    <li><input type="radio" name="paramType" value="1" >KEY-VALUE</li>
                                    <li><input type="radio" name="paramType" value="2" >SQL类型</li>
                                    <li><input type="radio" name="paramType" value="3" >文件获取</li>
                                </ul>
                                <div id="tab01" class="tab-inner" style="display:none">
                                    <textarea name="paramValues" placeholder="请输入参数值，多个参数用逗号(英文)隔开" class="textareaStyle"  style="margin-left: 0;width: 80%;height: 100px;border-top: 0;"></textarea>
                                </div>
                                <div id="tab02" class="tab-inner" style="display:none">
                                    <textarea name="paramSqlStr" placeholder="请输入SQL语句，仅限查询某一字段" class="textareaStyle" style="margin-left: 0;width: 80%;height: 100px;border-top: 0;"></textarea>
                                </div>
                                <div id="tab03" class="tab-inner" style="display:none">
                                    <div class="textMar">
                                        <input type="file" name="paramFile" onchange="checkImportFile(this)">
                                        <p class="help-block">仅限.txt后缀文件，内容样式为：参数名=参数值1,参数值2,……</p>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="textMar">
                            <label>全局设置</label></br>
                            <table style="margin:0px">
                                <tbody>
                                    <tr>
                                        <td><span>非全局参数：</span></td>
                                        <td><input type="radio" name="isGlobal" value="0" checked></td>
                                        <td style="width:4px"></td>
                                        <td><span>设置为全局参数：</span></td>
                                        <td><input type="radio" name="isGlobal" value="1"></td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                    <div class="textMar" style="margin-left:60px">
                        <input type="button" value="取   消" class="btnStyle" style="margin: auto 10% auto 30%;" onclick="self.location='/api/getAllApis?itemId='+$('#itemIdInput').val()">
                        <input type="submit" value="保   存" class="btnStyle"/>
                    </div>
                </div>
            </form>
        </div>
    </section>
</div>
<jsp:include page="footer.jsp"/>

</body>
<script type="text/javascript">
    $(document).ready(function () {
        $("input[name='paramType']").change(function () {
            var value = this.value;
            var divId = "tab0"+value;
            $("#"+divId).removeAttr("style");
            $("#"+divId).siblings("div").attr("style","display:none");
        })
    });
    //判断上传文件类型
    function checkImportFile(obj) {
        //如果文件为空
        if (obj.value == "") {
            alert("请选择上传文件");
        } else {
            //获取文件后缀名
            var index1 = obj.value.lastIndexOf(".");
            var index2 = obj.value.length;
            var fileType = obj.value.substring(index1, index2);
            //对文件后缀名进行判断
            if (fileType != ".txt") {
                alert("不支持该文件类型，请上传后缀名为.txt的文件");
                return false;
            } else {
                //实现文件上传操作

            }
        }
    }
</script>
</html>


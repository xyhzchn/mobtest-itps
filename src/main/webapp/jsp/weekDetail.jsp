<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:include page="header.jsp"/>
<jsp:include page="left.jsp"/>
<!-- 主要内容区域-->
<div class="content-wrapper">
    <!-- 面包屑 -->
    <section class="content-header">
        <ol class="breadcrumb">
            <li><a href="/week/getAllWeek"><i class="fa fa-dashboard"></i> 周报管理</a></li>
                <li class="active">编辑周报</li>
        </ol>
    </section>
    <!-- 主要内容 -->
    <section class="content container-fluid">
        <div class="context">
            <!--面包屑-->
                <form id="itemForm" method="post" action="<%=request.getContextPath()%>/item/editItem">
                    <div class="addArea">
                        <div class="realAddArea">
                            <div class="textMar">
                                <label>周报开始时间</label>
                                <input type="text" id="weekStartDate"  class="textStyle" name="startDate" value='<fmt:formatDate value="${weekly.startDate}" pattern="yyyy-MM-dd"/>' style="border: none;"/>
                                <label>周报开始时间</label>
                                <input type="text" id="weekEndDate"  class="textStyle" name="endDate" value='<fmt:formatDate value="${weekly.endDate}" pattern="yyyy-MM-dd"/>' style="border: none;"/>
                            </div>
                            <div class="textMar">
                                <label>本周工作内容：</label>
                                <c:if test="${weekly.currentWeekList.size() > 0}">
                                    <table style="margin:2%">
                                        <tr>
                                            <th>汇报者</th>
                                            <th>工作事项</th>
                                            <th>JIRA</th>
                                            <th>项目组</th>
                                            <th width="30%">内容</th>
                                            <th>状态(进度)</th>
                                            <th>是否上线</th>
                                            <th>是否回测</th>
                                            <th>预计测试周期(H)</th>
                                            <th>实际累计耗时(H)</th>
                                            <th width="10%">备注</th>
                                        </tr>
                                            <c:forEach items="${weekly.currentWeekList}" var="info">
                                                <tr>
                                                      <td>${info.userName}</td>
                                                      <c:if test="${info.workType == 1}">
                                                            <td>用例编写</td>
                                                      </c:if>
                                                       <c:if test="${info.workType == 2}">
                                                           <td>测试执行</td>
                                                       </c:if>
                                                       <c:if test="${info.workType == 3}">
                                                             <td>技术改进</td>
                                                       </c:if>
                                                       <c:if test="${info.workType == 4}">
                                                              <td>其他</td>
                                                       </c:if>
                                                       <td>${info.jiraId}</td>
                                                       <td>${info.projectName}</td>
                                                       <td>${info.content}</td>
                                                       <td><fmt:formatNumber value="${info.progress*100}" minFractionDigits="1" maxFractionDigits="1"/>%</td>
                                                       <td>${info.isOnline}</td>
                                                       <td>${info.isRegressionTest}</td>
                                                       <td>${info.estimatedTime}</td>
                                                       <td>${info.realTime}</td>
                                                       <td>${info.otherDesc}</td>
                                                     </tr>
                                                </c:forEach>
                                        </table>
                                    </c:if>
                                </div>
                            <div class="textMar">
                                <label>用例和bug：</label><br>
                                <table style="margin:2%">
                                    <c:if test="${weekly.caseAndBugList.size()>0}">
                                        <tr>
                                            <th colspan="4" style="text-align: center">用例</th>
                                            <th width="40px"></th>
                                            <th colspan="4" style="text-align: center">BUG</th>
                                        </tr>
                                        <tr>
                                            <th>P1</th>
                                            <th>P2</th>
                                            <th>P3</th>
                                            <th>P4</th>
                                            <th width="40px"></th>
                                            <th>P1</th>
                                            <th>P2</th>
                                            <th>P3</th>
                                            <th>P4</th>
                                        </tr>
                                        <c:forEach items="${weekly.caseAndBugList}" var="item">
                                            <tr>
                                                <td>${item.case_P1}</td>
                                                <td>${item.case_P2}</td>
                                                <td>${item.case_P3}</td>
                                                <td>${item.case_P4}</td>
                                                <th width="40px"></th>
                                                <td>${item.bug_P1}</td>
                                                <td>${item.bug_P2}</td>
                                                <td>${item.bug_P3}</td>
                                                <td>${item.bug_P4}</td>
                                            </tr>
                                        </c:forEach>
                                    </c:if>
                                </table>
                            </div>
                            <div class="textMar">
                                <label>遗留问题和建议：</label><br>
                                <table style="margin:2%">
                                    <c:if test="${weekly.caseAndBugList.size()>0}">
                                        <c:forEach items="${weekly.caseAndBugList}" var="item">
                                            <tr>
                                                <td>遗留问题</td>
                                                <c:if test="${item.remaining == ''}">
                                                    <td>无</td>
                                                </c:if>
                                                <c:if test="${item.remaining != ''}">
                                                    <td>${item.remaining}</td>
                                                </c:if>
                                            </tr>
                                            <tr>
                                                <td>建议</td>
                                                <c:if test="${item.suggest == ''}">
                                                    <td>无</td>
                                                </c:if>
                                                <c:if test="${item.suggest != ''}">
                                                    <td>${item.suggest}</td>
                                                </c:if>
                                            </tr>
                                        </c:forEach>
                                    </c:if>
                                </table>
                            </div>
                            <div class="textMar">
                                <label>下周计划：</label><br>
                                <table style="margin:2%">
                                    <tr>
                                        <th>汇报者</th>
                                        <th>工作事项</th>
                                        <th>JIRA</th>
                                        <th>项目组</th>
                                        <th width="30%">工作内容</th>
                                        <th>预计提测时间</th>
                                        <th>计划完成时间</th>
                                        <th style="width:20%">备注</th>
                                    </tr>
                                    <c:if test="${weekly.nextWeekList.size() > 0}">
                                                    <c:forEach items="${weekly.nextWeekList}" var="next">
                                                        <tr>
                                                            <td>${next.userName}</td>
                                                            <c:if test="${next.workType == 1}">
                                                                <td>用例编写</td>
                                                            </c:if>
                                                            <c:if test="${next.workType == 2}">
                                                                <td>测试执行</td>
                                                            </c:if>
                                                            <c:if test="${next.workType == 3}">
                                                                <td>技术改进</td>
                                                            </c:if>
                                                            <c:if test="${next.workType == 4}">
                                                                <td>其他</td>
                                                            </c:if>
                                                            <td>*</td>
                                                            <td>${next.projectName}</td>
                                                            <td>${next.content}</td>
                                                            <td><fmt:formatDate value="${next.yujiTestDate}" pattern="yyyy-MM-dd" /></td>
                                                            <td><fmt:formatDate value="${next.yujiTestDate}" pattern="yyyy-MM-dd"/></td>
                                                            <td>${next.otherDesc}</td>
                                                        </tr>
                                                    </c:forEach>
                                                </c:if>
                                </table>
                            </div>
                            <div class="textMar" style="margin-left:60px">
                                <input type="button" value="取   消" class="btnStyle" style="margin-right:30px" onclick="location.href='/week/getAllWeek'">
                                <input id="editBtn" type="button" value="修   改" class="btnStyle">
                                <input id="saveBtn" type="submit" value="保   存" class="btnStyle" style="display: none"/>
                            </div>
                        </div>
                    </div>
                </form>
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


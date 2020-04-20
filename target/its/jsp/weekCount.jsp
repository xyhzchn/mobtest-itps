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
            <li><a href="#"><i class="fa fa-dashboard"></i> 周报统计</a></li>
        </ol>
    </section>
    <!-- 主要内容 -->
    <section class="content container-fluid">
        <div class="context">
            <!--面包屑-->
            <div class="addArea">
                <input type="hidden" id="hiddenSelectMonth" value="${month}">
                <div class="realAddArea" style="padding-left: 0">
                    <div id="tab-demo">
                        <ul class="tab-title">
                            <li><a href="#tab01">周统计</a></li>
                            <li><a href="#tab02">月统计</a></li>
                        </ul>
                        <div id="tab01" class="tab-inner">
                            <form id="weekForm" method="post" action="/week/getWeeklyCount">
                                <select id="selectWeek" name="selectWeek" style="width:160px" onchange="weekSelected(this.value)">
                                    <option value="0" selected>请选择周</option>
                                    <c:forEach items="${weekMap}" var="week">
                                        <option value="${week.key}">${week.value}</option>
                                    </c:forEach>
                                </select>
                                <div class="textMar">
                                    <input type="submit" class="btnStyle" style="margin-left: 52%" value="检   索">
                                </div>
                                <div class="realAddArea" style="padding-left: 5%">
                                    <label>测试项目统计</label></br>
                                    <table style="margin:2%">
                                        <tr>
                                            <th>汇报者</th>
                                            <th>工作事项</th>
                                            <th>JIRA</th>
                                            <th>项目组</th>
                                            <th>内容</th>
                                            <th>状态(进度)</th>
                                            <th>是否上线</th>
                                            <th>是否回测</th>
                                            <th>预计测试周期(H)</th>
                                            <th>实际累计耗时(H)</th>
                                            <th>备注</th>
                                        </tr>

                                        <c:if test="${weekly != null}">
                                            <c:if test="${weekly.currentWeekList.size() > 0}">
                                                <c:set var="estimatedTimeTotal" value="${0}"/>
                                                <c:set var="realTimeTotal" value="${0}"/>
                                                <c:set var="projectTotal" value="${0}"/>
                                                <c:forEach items="${weekly.currentWeekList}" var="info">
                                                    <c:if test="${info.jiraId != ''}">
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
                                                        <c:set var="estimatedTimeTotal" value="${estimatedTimeTotal + (info.estimatedTime)}"/>
                                                        <c:set var="realTimeTotal" value="${realTimeTotal + (info.realTime)}"/>
                                                        <c:set var="projectTotal" value="${projectTotal+1}"/>
                                                    </c:if>
                                                </c:forEach>
                                                <tr>
                                                    <td style="color:red">合计</td>
                                                    <td>-</td>
                                                    <td style="color:red">共<c:out value="${projectTotal}"/>个项目</td>
                                                    <td>-</td>
                                                    <td>-</td>
                                                    <td>-</td>
                                                    <td>-</td>
                                                    <td>-</td>
                                                    <td style="color:red"><c:out value="${estimatedTimeTotal}"/></td>
                                                    <td style="color:red"><c:out value="${realTimeTotal}"/></td>
                                                    <td>-</td>
                                                </tr>
                                            </c:if>
                                        </c:if>
                                    </table>
                                    <label>非测试项目统计</label></br>
                                    <table style="margin:2%">
                                        <tr>
                                            <th>汇报者</th>
                                            <th>工作事项</th>
                                            <th>JIRA</th>
                                            <th>项目组</th>
                                            <th>内容</th>
                                            <th>状态(进度)</th>
                                            <th>是否上线</th>
                                            <th>是否回测</th>
                                            <th>预计测试周期(H)</th>
                                            <th>实际累计耗时(H)</th>
                                            <th>备注</th>
                                        </tr>
                                        <c:if test="${weekly != null}">
                                            <c:if test="${weekly.currentWeekList.size() > 0}">
                                                <c:set var="estimatedTimeTotal" value="${0}"/>
                                                <c:set var="realTimeTotal" value="${0}"/>
                                                <c:set var="projectTotal" value="${0}"/>
                                                <c:forEach items="${weekly.currentWeekList}" var="info">
                                                    <c:if test="${info.jiraId == ''}">
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
                                                            <td>*</td>
                                                            <td>${info.projectName}</td>
                                                            <td>${info.content}</td>
                                                            <td><fmt:formatNumber value="${info.progress*100}" minFractionDigits="1" maxFractionDigits="1"/>%</td>
                                                            <td>${info.isOnline}</td>
                                                            <td>${info.isRegressionTest}</td>
                                                            <td>${info.estimatedTime}</td>
                                                            <td>${info.realTime}</td>
                                                            <td>${info.otherDesc}</td>
                                                        </tr>
                                                        <c:set var="estimatedTimeTotal" value="${estimatedTimeTotal + (info.estimatedTime)}"/>
                                                        <c:set var="realTimeTotal" value="${realTimeTotal + (info.realTime)}"/>
                                                        <c:set var="projectTotal" value="${projectTotal+1}"/>
                                                    </c:if>
                                                </c:forEach>
                                                <tr>
                                                    <td style="color:red">合计</td>
                                                    <td>-</td>
                                                    <td style="color:red">共<c:out value="${projectTotal}"/>个项目</td>
                                                    <td>-</td>
                                                    <td>-</td>
                                                    <td>-</td>
                                                    <td>-</td>
                                                    <td>-</td>
                                                    <td style="color:red"><c:out value="${estimatedTimeTotal}"/></td>
                                                    <td style="color:red"><c:out value="${realTimeTotal}"/></td>
                                                    <td>-</td>
                                                </tr>
                                            </c:if>
                                        </c:if>
                                    </table>
                                    <label>用例和bug统计</label></br>
                                    <table style="margin:2%">

                                        <c:if test="${weekly != null}">
                                            <c:if test="${weekly.caseAndBug != null}">
                                                <tr>
                                                    <th>类型</th>
                                                    <th>P1</th>
                                                    <th>P2</th>
                                                    <th>P3</th>
                                                    <th>P4</th>
                                                    <th style="color:red;">合计</th>
                                                </tr>
                                                <tr>
                                                    <td>用例</td>
                                                    <td>${weekly.caseAndBug.case_P1}</td>
                                                    <td>${weekly.caseAndBug.case_P2}</td>
                                                    <td>${weekly.caseAndBug.case_P3}</td>
                                                    <td>${weekly.caseAndBug.case_P4}</td>
                                                    <td style="color:red;">${weekly.caseAndBug.case_P1+weekly.caseAndBug.case_P2+weekly.caseAndBug.case_P3+weekly.caseAndBug.case_P4}</td>
                                                </tr>
                                                <tr>
                                                    <td>BUG</td>
                                                    <td>${weekly.caseAndBug.bug_P1}</td>
                                                    <td>${weekly.caseAndBug.bug_P2}</td>
                                                    <td>${weekly.caseAndBug.bug_P3}</td>
                                                    <td>${weekly.caseAndBug.bug_P4}</td>
                                                    <td style="color:red;">${weekly.caseAndBug.bug_P1+weekly.caseAndBug.bug_P2+weekly.caseAndBug.bug_P3+weekly.caseAndBug.bug_P4}</td>
                                                </tr>
                                            </c:if>
                                            <c:if test="${weekly.caseAndBugList.size() > 0}">
                                                <tr>
                                                    <th colspan="5" style="text-align: center">用例</th>
                                                    <th colspan="5" style="text-align: center">BUG</th>
                                                    <th rowspan="2" style="text-align: center">汇报者</th>
                                                </tr>
                                                <tr>
                                                    <th>P1</th>
                                                    <th>P2</th>
                                                    <th>P3</th>
                                                    <th>P4</th>
                                                    <th style="color:red;">合计</th>
                                                    <th>P1</th>
                                                    <th>P2</th>
                                                    <th>P3</th>
                                                    <th>P4</th>
                                                    <th style="color:red;">合计</th>
                                                </tr>
                                                <c:set var="caseP1Total" value="${0}"/>
                                                <c:set var="caseP2Total" value="${0}"/>
                                                <c:set var="caseP3Total" value="${0}"/>
                                                <c:set var="caseP4Total" value="${0}"/>
                                                <c:set var="allCaseTotal" value="${0}"/>
                                                <c:set var="bugP1Total" value="${0}"/>
                                                <c:set var="bugP2Total" value="${0}"/>
                                                <c:set var="bugP3Total" value="${0}"/>
                                                <c:set var="bugP4Total" value="${0}"/>
                                                <c:set var="allBugTotal" value="${0}"/>
                                                <c:forEach items="${weekly.caseAndBugList}" var="item">
                                                    <tr>
                                                        <td>${item.case_P1}</td>
                                                        <td>${item.case_P2}</td>
                                                        <td>${item.case_P3}</td>
                                                        <td>${item.case_P4}</td>
                                                        <td style="color:red;">${item.case_P1+item.case_P2+item.case_P3+item.case_P4}</td>
                                                        <td>${item.bug_P1}</td>
                                                        <td>${item.bug_P2}</td>
                                                        <td>${item.bug_P3}</td>
                                                        <td>${item.bug_P4}</td>
                                                        <td style="color:red;">${item.bug_P1+item.bug_P2+item.bug_P3+item.bug_P4}</td>
                                                        <td>${item.userName}</td>
                                                    </tr>
                                                    <c:set var="caseP1Total" value="${caseP1Total + (item.case_P1)}"/>
                                                    <c:set var="caseP2Total" value="${caseP2Total + (item.case_P2)}"/>
                                                    <c:set var="caseP3Total" value="${caseP3Total + (item.case_P3)}"/>
                                                    <c:set var="caseP4Total" value="${caseP4Total + (item.case_P4)}"/>
                                                    <c:set var="allCaseTotal" value="${allCaseTotal + (item.case_P1+item.case_P2+item.case_P3+item.case_P4)}"/>
                                                    <c:set var="bugP1Total" value="${bugP1Total + (item.bug_P1)}"/>
                                                    <c:set var="bugP2Total" value="${bugP2Total + (item.bug_P2)}"/>
                                                    <c:set var="bugP3Total" value="${bugP3Total + (item.bug_P3)}"/>
                                                    <c:set var="bugP4Total" value="${bugP4Total + (item.bug_P4)}"/>
                                                    <c:set var="allBugTotal" value="${allBugTotal + (item.bug_P1+item.bug_P2+item.bug_P3+item.bug_P4)}"/>
                                                </c:forEach>
                                                <tr>
                                                    <td style="color:red;"><c:out value="${caseP1Total}"/></td>
                                                    <td style="color:red;"><c:out value="${caseP2Total}"/></td>
                                                    <td style="color:red;"><c:out value="${caseP3Total}"/></td>
                                                    <td style="color:red;"><c:out value="${caseP4Total}"/></td>
                                                    <td style="color:red;"><c:out value="${allCaseTotal}"/></td>
                                                    <td style="color:red;"><c:out value="${bugP1Total}"/></td>
                                                    <td style="color:red;"><c:out value="${bugP2Total}"/></td>
                                                    <td style="color:red;"><c:out value="${bugP3Total}"/></td>
                                                    <td style="color:red;"><c:out value="${bugP4Total}"/></td>
                                                    <td style="color:red;"><c:out value="${allBugTotal}"/></td>
                                                    <td>合计</td>
                                                </tr>
                                            </c:if>

                                        </c:if>
                                    </table>
                                    <label>下周计划统计</label></br>
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
                                        <c:if test="${weekly != null}">
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
                                        </c:if>
                                    </table>
                                </div>
                            </form>
                        </div>
                        <div id="tab02" class="tab-inner">
                            <form id="monthForm" method="post" action="/week/getWeeklyCount">
                                <select id="selectMonth" name="selectMonth" style="width:160px" onchange="monthSelected(this.value)">
                                    <option value="0" selected>请选择月</option>
                                    <option value="1">1月</option>
                                    <option value="2">2月</option>
                                    <option value="3">3月</option>
                                    <option value="4">4月</option>
                                    <option value="5">5月</option>
                                    <option value="6">6月</option>
                                    <option value="7">7月</option>
                                    <option value="8">8月</option>
                                    <option value="9">9月</option>
                                    <option value="10">10月</option>
                                    <option value="11">11月</option>
                                    <option value="12">12月</option>
                                </select>

                                <div class="textMar">
                                    <input type="submit" class="btnStyle" style="margin-left: 52%" value="检   索">
                                </div>
                                <div class="realAddArea" style="padding-left: 5%">
                                    <label>测试项目统计</label></br>
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
                                            <th>周报开始日期</th>
                                            <th>周报结束日期</th>
                                        </tr>
                                        <c:if test="${weeklyList.size() > 0}">
                                            <c:set var="estimatedTimeTotal" value="${0}"/>
                                            <c:set var="realTimeTotal" value="${0}"/>
                                            <c:set var="projectTotal" value="${0}"/>
                                            <c:forEach items="${weeklyList}" var="aweek">
                                                <c:if test="${aweek != null}">
                                                    <c:if test="${aweek.currentWeekList.size() > 0}">
                                                        <c:forEach items="${aweek.currentWeekList}" var="info">
                                                            <c:if test="${info.jiraId != ''}">
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
                                                                    <td><fmt:formatDate value="${aweek.startDate}" pattern="yyyy-MM-dd"/> </td>
                                                                    <td><fmt:formatDate value="${aweek.endDate}" pattern="yyyy-MM-dd"/></td>
                                                                </tr>
                                                                <c:set var="estimatedTimeTotal" value="${estimatedTimeTotal + (info.estimatedTime)}"/>
                                                                <c:set var="realTimeTotal" value="${realTimeTotal + (info.realTime)}"/>
                                                                <c:set var="projectTotal" value="${projectTotal+1}"/>
                                                            </c:if>
                                                        </c:forEach>
                                                    </c:if>
                                                </c:if>
                                            </c:forEach>
                                            <tr>
                                                <td style="color:red">合计</td>
                                                <td>-</td>
                                                <td style="color:red">共<c:out value="${projectTotal}"/>个项目</td>
                                                <td>-</td>
                                                <td>-</td>
                                                <td>-</td>
                                                <td>-</td>
                                                <td>-</td>
                                                <td style="color:red"><c:out value="${estimatedTimeTotal}"/></td>
                                                <td style="color:red"><c:out value="${realTimeTotal}"/></td>
                                                <td>-</td>
                                            </tr>
                                        </c:if>
                                    </table>
                                    <label>非测试项目统计</label></br>
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
                                            <th>周报开始日期</th>
                                            <th>周报结束日期</th>
                                        </tr>

                                        <c:if test="${weeklyList.size() > 0}">
                                            <c:set var="estimatedTimeTotal" value="${0}"/>
                                            <c:set var="realTimeTotal" value="${0}"/>
                                            <c:set var="projectTotal" value="${0}"/>
                                            <c:forEach items="${weeklyList}" var="aweek">
                                                <c:if test="${aweek != null}">
                                                    <c:if test="${aweek.currentWeekList.size() > 0}">
                                                        <c:forEach items="${aweek.currentWeekList}" var="info">
                                                            <c:if test="${info.jiraId == ''}">
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
                                                                    <td>*</td>
                                                                    <td>${info.projectName}</td>
                                                                    <td>${info.content}</td>
                                                                    <td><fmt:formatNumber value="${info.progress*100}" minFractionDigits="1" maxFractionDigits="1"/>%</td>
                                                                    <td>${info.isOnline}</td>
                                                                    <td>${info.isRegressionTest}</td>
                                                                    <td>${info.estimatedTime}</td>
                                                                    <td>${info.realTime}</td>
                                                                    <td>${info.otherDesc}</td>
                                                                    <td><fmt:formatDate value="${aweek.startDate}" pattern="yyyy-MM-dd"/></td>
                                                                    <td><fmt:formatDate value="${aweek.endDate}" pattern="yyyy-MM-dd"/></td>
                                                                </tr>
                                                                <c:set var="estimatedTimeTotal" value="${estimatedTimeTotal + (info.estimatedTime)}"/>
                                                                <c:set var="realTimeTotal" value="${realTimeTotal + (info.realTime)}"/>
                                                                <c:set var="projectTotal" value="${projectTotal+1}"/>
                                                            </c:if>
                                                        </c:forEach>
                                                    </c:if>
                                                </c:if>
                                            </c:forEach>
                                                <tr>
                                                    <td style="color:red">合计</td>
                                                    <td>-</td>
                                                    <td style="color:red">共<c:out value="${projectTotal}"/>个项目</td>
                                                    <td>-</td>
                                                    <td>-</td>
                                                    <td>-</td>
                                                    <td>-</td>
                                                    <td>-</td>
                                                    <td style="color:red"><c:out value="${estimatedTimeTotal}"/></td>
                                                    <td style="color:red"><c:out value="${realTimeTotal}"/></td>
                                                    <td>-</td>
                                                </tr>
                                            </c:if>
                                    </table>
                                    <label>用例和bug统计</label></br>
                                    <table style="margin:2%">
                                        <c:if test="${weeklyList.size() > 0}">
                                            <tr>
                                                <th colspan="5" style="text-align: center">用例</th>
                                                <th colspan="5" style="text-align: center">BUG</th>
                                                <th rowspan="2" style="text-align: center">汇报者</th>
                                                <th rowspan="2" style="text-align: center">周报开始时间</th>
                                                <th rowspan="2" style="text-align: center">周报结束时间</th>
                                            </tr>
                                            <tr>
                                                <th>P1</th>
                                                <th>P2</th>
                                                <th>P3</th>
                                                <th>P4</th>
                                                <th style="color:red;">合计</th>
                                                <th>P1</th>
                                                <th>P2</th>
                                                <th>P3</th>
                                                <th>P4</th>
                                                <th style="color:red;">合计</th>
                                            </tr>
                                            <c:set var="caseP1Total" value="${0}"/>
                                            <c:set var="caseP2Total" value="${0}"/>
                                            <c:set var="caseP3Total" value="${0}"/>
                                            <c:set var="caseP4Total" value="${0}"/>
                                            <c:set var="allCaseTotal" value="${0}"/>
                                            <c:set var="bugP1Total" value="${0}"/>
                                            <c:set var="bugP2Total" value="${0}"/>
                                            <c:set var="bugP3Total" value="${0}"/>
                                            <c:set var="bugP4Total" value="${0}"/>
                                            <c:set var="allBugTotal" value="${0}"/>

                                            <c:forEach items="${weeklyList}" var="aweek">
                                                <c:if test="${aweek != null}">
                                                    <c:if test="${aweek.caseAndBug != null}">

                                                        <tr>
                                                            <td>${aweek.caseAndBug.case_P1}</td>
                                                            <td>${aweek.caseAndBug.case_P2}</td>
                                                            <td>${aweek.caseAndBug.case_P3}</td>
                                                            <td>${aweek.caseAndBug.case_P4}</td>
                                                            <td style="color:red;">${aweek.caseAndBug.case_P1+aweek.caseAndBug.case_P2+aweek.caseAndBug.case_P3+aweek.caseAndBug.case_P4}</td>
                                                            <td>${aweek.caseAndBug.bug_P1}</td>
                                                            <td>${aweek.caseAndBug.bug_P2}</td>
                                                            <td>${aweek.caseAndBug.bug_P3}</td>
                                                            <td>${aweek.caseAndBug.bug_P4}</td>
                                                            <td style="color:red;">${aweek.caseAndBug.bug_P1+aweek.caseAndBug.bug_P2+aweek.caseAndBug.bug_P3+aweek.caseAndBug.bug_P4}</td>
                                                            <td>${aweek.caseAndBug.userName}</td>
                                                            <td>${aweek.startDate}</td>
                                                            <td>${aweek.endDate}</td>
                                                            <c:set var="caseP1Total" value="${caseP1Total + (aweek.caseAndBug.case_P1)}"/>
                                                            <c:set var="caseP2Total" value="${caseP2Total + (aweek.caseAndBug.case_P2)}"/>
                                                            <c:set var="caseP3Total" value="${caseP3Total + (aweek.caseAndBug.case_P3)}"/>
                                                            <c:set var="caseP4Total" value="${caseP4Total + (aweek.caseAndBug.case_P4)}"/>
                                                            <c:set var="allCaseTotal" value="${allCaseTotal + (aweek.caseAndBug.case_P1+aweek.caseAndBug.case_P2+aweek.caseAndBug.case_P3+aweek.caseAndBug.case_P4)}"/>
                                                            <c:set var="bugP1Total" value="${bugP1Total + (aweek.caseAndBug.bug_P1)}"/>
                                                            <c:set var="bugP2Total" value="${bugP2Total + (aweek.caseAndBug.bug_P2)}"/>
                                                            <c:set var="bugP3Total" value="${bugP3Total + (aweek.caseAndBug.bug_P3)}"/>
                                                            <c:set var="bugP4Total" value="${bugP4Total + (aweek.caseAndBug.bug_P4)}"/>
                                                            <c:set var="allBugTotal" value="${allBugTotal + (aweek.caseAndBug.bug_P1+aweek.caseAndBug.bug_P2+aweek.caseAndBug.bug_P3+aweek.caseAndBug.bug_P4)}"/>
                                                        </tr>
                                                    </c:if>
                                                    <c:if test="${aweek.caseAndBugList.size() > 0}">
                                                        <c:forEach items="${aweek.caseAndBugList}" var="item">
                                                            <tr>
                                                                <td>${item.case_P1}</td>
                                                                <td>${item.case_P2}</td>
                                                                <td>${item.case_P3}</td>
                                                                <td>${item.case_P4}</td>
                                                                <td style="color:red;">${item.case_P1+item.case_P2+item.case_P3+item.case_P4}</td>
                                                                <td>${item.bug_P1}</td>
                                                                <td>${item.bug_P2}</td>
                                                                <td>${item.bug_P3}</td>
                                                                <td>${item.bug_P4}</td>
                                                                <td style="color:red;">${item.bug_P1+item.bug_P2+item.bug_P3+item.bug_P4}</td>
                                                                <td>${item.userName}</td>
                                                                <td><fmt:formatDate value="${aweek.startDate}" pattern="yyyy-MM-dd"/></td>
                                                                <td><fmt:formatDate value="${aweek.endDate}" pattern="yyyy-MM-dd"/></td>
                                                            </tr>
                                                            <c:set var="caseP1Total" value="${caseP1Total + (item.case_P1)}"/>
                                                            <c:set var="caseP2Total" value="${caseP2Total + (item.case_P2)}"/>
                                                            <c:set var="caseP3Total" value="${caseP3Total + (item.case_P3)}"/>
                                                            <c:set var="caseP4Total" value="${caseP4Total + (item.case_P4)}"/>
                                                            <c:set var="allCaseTotal" value="${allCaseTotal + (item.case_P1+item.case_P2+item.case_P3+item.case_P4)}"/>
                                                            <c:set var="bugP1Total" value="${bugP1Total + (item.bug_P1)}"/>
                                                            <c:set var="bugP2Total" value="${bugP2Total + (item.bug_P2)}"/>
                                                            <c:set var="bugP3Total" value="${bugP3Total + (item.bug_P3)}"/>
                                                            <c:set var="bugP4Total" value="${bugP4Total + (item.bug_P4)}"/>
                                                            <c:set var="allBugTotal" value="${allBugTotal + (item.bug_P1+item.bug_P2+item.bug_P3+item.bug_P4)}"/>
                                                        </c:forEach>
                                                    </c:if>
                                                </c:if>
                                            </c:forEach>
                                            <tr>
                                                <td style="color:red;"><c:out value="${caseP1Total}"/></td>
                                                <td style="color:red;"><c:out value="${caseP2Total}"/></td>
                                                <td style="color:red;"><c:out value="${caseP3Total}"/></td>
                                                <td style="color:red;"><c:out value="${caseP4Total}"/></td>
                                                <td style="color:red;"><c:out value="${allCaseTotal}"/></td>
                                                <td style="color:red;"><c:out value="${bugP1Total}"/></td>
                                                <td style="color:red;"><c:out value="${bugP2Total}"/></td>
                                                <td style="color:red;"><c:out value="${bugP3Total}"/></td>
                                                <td style="color:red;"><c:out value="${bugP4Total}"/></td>
                                                <td style="color:red;"><c:out value="${allBugTotal}"/></td>
                                                <td>合计</td>
                                                <td style="color:red;">-</td>
                                                <td style="color:red;">-</td>
                                            </tr>
                                        </c:if>
                                    </table>
                                    <label>下周计划统计</label></br>
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
                                            <th>本周开始时间</th>
                                            <th>本周结束时间</th>
                                        </tr>
                                        <c:if test="${weeklyList.size() > 0}">
                                            <c:forEach items="${weeklyList}" var="aweek">
                                                <c:if test="${aweek != null}">
                                                    <c:if test="${aweek.nextWeekList.size() > 0}">
                                                        <c:forEach items="${aweek.nextWeekList}" var="next">
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
                                                                <td><fmt:formatDate value="${aweek.startDate}" pattern="yyyy-MM-dd"/></td>
                                                                <td><fmt:formatDate value="${aweek.endDate}" pattern="yyyy-MM-dd"/></td>
                                                            </tr>
                                                        </c:forEach>
                                                    </c:if>
                                                </c:if>
                                            </c:forEach>
                                        </c:if>
                                    </table>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>

        </div>
    </section>
</div>
<jsp:include page="footer.jsp"/>
<script type="text/javascript">

    $(function(){
        var $li = $('ul.tab-title li');
        $($li. eq(0) .addClass('active').find('a').attr('href')).siblings('.tab-inner').hide();

        $li.click(function(){
            $($(this).find('a'). attr ('href')).show().siblings ('.tab-inner').hide();
            $(this).addClass('active'). siblings ('.active').removeClass('active');
        });

        var month = $("#hiddenSelectMonth").val();
        if(month > 0){
            $($li. eq(1) .addClass('active').find('a').attr('href')).siblings('.tab-inner').hide();
            $($li. eq(1).find('a'). attr ('href')).show().siblings ('.tab-inner').hide();
            $li. eq(1).addClass('active'). siblings ('.active').removeClass('active');
        }
    });

    /**
     * 周选择的时候，获取该周中所有已提交周报的用户列表
     * @param weekId
     */
    function weekSelected(weekId) {
        if($("#selectUser").length > 0){
            $("#selectUser").remove();
        }
        $.ajax({
            type: "post",
            url: "/week/getUserListByWeekId",
            data: {"selectWeek": weekId},
            dataType: "json",
            success: function (json) {
                if(json != null){
                        $("#selectWeek").after("<select id='selectUser' name='selectUser'>" +
                                "<option value='0' selected>请选择用户</option>"+
                                "</select></br>");
                        $.each(json,function (key,value) {
                            $("#selectUser").append("<option value='"+key+"'>"+value+"</option>");
                        })
                }
            },
            error: function (json) {
                alert("查询该周提交周报的用户列表失败！")
            }
        })
    }

    /**
     * 月选择的时候，获取该周月中所有已提交周报的用户列表
     * @param month
     */
    function monthSelected(month) {
        if($("#selectUser").length > 0){
            $("#selectUser").remove();
        }
        $.ajax({
            type: "post",
            url: "/week/getUserListByMonth",
            data: {"selectMonth": month},
            dataType: "json",
            success: function (json) {
                if(json != null){
                    $("#selectMonth").after("<select id='selectUser' name='selectUser'>" +
                            "<option value='0' selected>请选择用户</option>"+
                            "</select></br>");
                    $.each(json,function (key,value) {
                        $("#selectUser").append("<option value='"+key+"'>"+value+"</option>");
                    })
                }
            },
            error: function (json) {
                alert("查询本月提交周报的用户列表失败！")
            }
        })
    }
</script>
</body>
</html>


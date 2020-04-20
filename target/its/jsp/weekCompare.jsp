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
                <div class="realAddArea" style="padding-left: 0">
                    <div id="tab-demo">
                        <ul class="tab-title">
                            <li><a href="#tab01">周对比</a></li>
                            <li><a href="#tab02">月对比</a></li>
                        </ul>
                        <div id="tab01" class="tab-inner">
                            <form id="weekForm" method="post">
                                <select id="selectWeekFrom" name="selectWeekFrom" style="width:160px" onchange="weekSelected()">
                                    <option value="0" selected>请选择对比开始周</option>
                                    <c:forEach items="${weeklyMap}" var="week">
                                        <option value="${week.key}">${week.value}</option>
                                    </c:forEach>
                                </select>
                                <select id="selectWeekTo" name="selectWeekTo" style="width:160px" onchange="weekSelected()">
                                    <option value="0" selected>请选择对比结束周</option>
                                    <c:forEach items="${weeklyMap}" var="week">
                                        <option value="${week.key}">${week.value}</option>
                                    </c:forEach>
                                </select>
                                <div class="textMar">
                                    <input type="button" class="btnStyle" style="margin-left: 52%" value="检   索" onclick="weekCompare()">
                                </div>
                                <div class="realAddArea" style="padding-left: 5%">
                                    <div class="box box-success" style="width: 80%">
                                        <div class="box-header with-border">
                                            <h3 class="box-title">预计测试周期对比</h3>
                                            <div class="box-tools pull-right">
                                                <button type="button" class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i>
                                                </button>
                                            </div>
                                        </div>
                                        <div class="box-body">
                                            <div class="chart" id="estimatedTimeWeek" style="height: 171px; width: 561px;">
                                            </div>
                                        </div>
                                        <!-- /.box-body -->
                                    </div>
                                    <div class="box box-success" style="width: 80%">
                                        <div class="box-header with-border">
                                            <h3 class="box-title">实际累计耗时对比</h3>

                                            <div class="box-tools pull-right">
                                                <button type="button" class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i>
                                                </button>
                                            </div>
                                        </div>
                                        <div class="box-body">
                                            <div class="chart" id="realTimeWeek" style="height: 171px; width: 561px;">
                                            </div>
                                        </div>
                                        <!-- /.box-body -->
                                    </div>
                                    <div class="box box-success" style="width: 80%">
                                        <div class="box-header with-border">
                                            <h3 class="box-title">新增用例数对比</h3>

                                            <div class="box-tools pull-right">
                                                <button type="button" class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i>
                                                </button>
                                            </div>
                                        </div>
                                        <div class="box-body">
                                            <div class="chart" id="caseWeek" style="height: 171px; width: 561px;">
                                            </div>
                                        </div>
                                        <!-- /.box-body -->
                                    </div>
                                    <div class="box box-success" style="width: 80%">
                                        <div class="box-header with-border">
                                            <h3 class="box-title">新增bug数对比</h3>

                                            <div class="box-tools pull-right">
                                                <button type="button" class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i>
                                                </button>
                                            </div>
                                        </div>
                                        <div class="box-body">
                                            <div class="chart" id="bugWeek" style="height: 171px; width: 561px;">
                                            </div>
                                        </div>
                                        <!-- /.box-body -->
                                    </div>
                                </div>
                            </form>
                        </div>
                        <div id="tab02" class="tab-inner">
                            <form id="monthForm" method="post">
                                <select id="selectMonthFrom" name="selectMonthFrom" style="width:160px" onchange="monthSelected(this.value)">
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
                                <select id="selectMonthTo" name="selectMonthTo" style="width:160px" onchange="monthSelected(this.value)">
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
                                    <input type="button" class="btnStyle" style="margin-left: 52%" value="检   索" onclick="monthCompare()">
                                </div>
                                <div class="realAddArea" style="padding-left: 5%">
                                    <div class="box box-success" style="width: 80%">
                                        <div class="box-header with-border">
                                            <h3 class="box-title">预计测试周期对比</h3>

                                            <div class="box-tools pull-right">
                                                <button type="button" class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i>
                                                </button>
                                            </div>
                                        </div>
                                        <div class="box-body">
                                            <div class="chart" id="estimatedTimeMonth" style="height: 171px; width: 561px;">
                                            </div>
                                        </div>
                                        <!-- /.box-body -->
                                    </div>
                                    <div class="box box-success" style="width: 80%">
                                        <div class="box-header with-border">
                                            <h3 class="box-title">实际累计耗时对比</h3>

                                            <div class="box-tools pull-right">
                                                <button type="button" class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i>
                                                </button>
                                            </div>
                                        </div>
                                        <div class="box-body">
                                            <div class="chart" id="realTimeMonth"  style="height: 171px; width: 561px;" >
                                            </div>
                                        </div>
                                        <!-- /.box-body -->
                                    </div>
                                    <div class="box box-success" style="width: 80%">
                                        <div class="box-header with-border">
                                            <h3 class="box-title">新增用例数对比</h3>

                                            <div class="box-tools pull-right">
                                                <button type="button" class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i>
                                                </button>
                                            </div>
                                        </div>
                                        <div class="box-body">
                                            <div class="chart" id="caseMonth"  style="height: 171px; width: 561px;" >
                                            </div>
                                        </div>
                                        <!-- /.box-body -->
                                    </div>
                                    <div class="box box-success" style="width: 80%">
                                        <div class="box-header with-border">
                                            <h3 class="box-title">新增bug数对比</h3>

                                            <div class="box-tools pull-right">
                                                <button type="button" class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i>
                                                </button>
                                            </div>
                                        </div>
                                        <div class="box-body">
                                            <div class="chart" id="bugMonth" style="height: 171px; width: 561px;" >
                                            </div>
                                        </div>
                                        <!-- /.box-body -->
                                    </div>
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
    function weekSelected() {
        if($("#selectUser").length > 0){
            $("#selectUser").remove();
        }
        var weekFrom = $("#selectWeekFrom").val();
        var weekTo = $("#selectWeekTo").val();
        if(weekFrom > 0 && weekTo >0){
            var weeks = [weekFrom,weekTo];
            $.ajax({
                type: "post",
                url: "/week/getUserListByWeekList",
                data: {"selectWeekStr":weeks.toString()},
                dataType: "json",
                success: function (json) {
                    if(json != null){
                        $("#selectWeekTo").after("<select id='selectUser' name='selectUser'>" +
                                "<option value='0' selected>请选择用户</option>"+
                                "</select></br>");
                        $.each(json,function (key,value) {
                            $("#selectUser").append("<option value='"+key+"'>"+value+"</option>");
                        })
                    }
                },
                error: function (json) {
                    alert("查询本周提交周报的用户列表失败！")
                }
            })
        }

    }

    /**
     * 月选择的时候，获取该周月中所有已提交周报的用户列表
     * @param month
     */
    function monthSelected(month) {
        if($("#selectUser").length > 0){
            $("#selectUser").remove();
        }
        var monthFrom = $("#selectMonthFrom").val();
        var monthTo = $("#selectMonthTo").val();
        if(monthFrom > 0 && monthTo >0) {
            var months = [monthFrom, monthTo];
            $.ajax({
                type: "post",
                url: "/week/getUserListByMonthList",
                data: {"selectMonthStr": months.toString()},
                dataType: "json",
                success: function (json) {
                    if (json != null) {
                        $("#selectMonthTo").after("<select id='selectUser' name='selectUser'>" +
                                "<option value='0' selected>请选择用户</option>" +
                                "</select></br>");
                        $.each(json, function (key, value) {
                            $("#selectUser").append("<option value='" + key + "'>" + value + "</option>");
                        })
                    }
                },
                error: function (json) {
                    alert("查询本月提交周报的用户列表失败！")
                }
            })
        }
    }
    /**
     * 获取周对比的数据并画出柱状图
     */
    function weekCompare() {
        var estimatedTimeChart = echarts.init(document.getElementById("estimatedTimeWeek"));
        var realTimeChart = echarts.init(document.getElementById("realTimeWeek"));
        var caseChart = echarts.init(document.getElementById("caseWeek"));
        var bugChart = echarts.init(document.getElementById("bugWeek"));

        var weekFrom = $("#selectWeekFrom").val();
        var weekTo = $("#selectWeekTo").val();
        var selectUser = $("#selectUser").val();

        var week = {
            "selectWeekFrom":weekFrom,
            "selectWeekTo":weekTo,
            "selectUser":selectUser
        };
        $.ajax({
            type:"post",
            url:"<%=request.getContextPath()%>/week/getWeekCompare",
            contentType: "application/json;charset=utf-8",
            data:JSON.stringify(week),
            success:function (data) {
                if(data != null){
                    var estimatedTimeOption = {
                        legend:{
                            data:['预计消耗时间']
                        },
                        tooltip:{
                            show:true,
                            trigger:'axis'
                        },
                        toolbox:{
                            show:true,
                            feature:{
                                saveAsImage:{
                                    show:true
                                }
                            }
                        },
                        grid:{
                            top:"50px",
                            left:"50px",
                            right:"15px",
                            bottom:"50px"
                        },
                        xAxis: {
                            type: 'category',
                            data: data["xAxis"]
                        },
                        yAxis: {},
                        series: [{
                            data: data["estimatedTimeData"],
                            type: 'bar'
                        }]
                    };
                    var realTimeOption = {
                        legend:{
                            data:['实际消耗时间']
                        },
                        tooltip:{
                            show:true,
                            trigger:'axis'
                        },
                        toolbox:{
                            show:true,
                            feature:{
                                saveAsImage:{
                                    show:true
                                }
                            }
                        },
                        grid:{
                            top:"50px",
                            left:"50px",
                            right:"15px",
                            bottom:"50px"
                        },
                        xAxis: {
                            type: 'category',
                            data: data["xAxis"]
                        },
                        yAxis: {},
                        series: [{
                            data: data["realTimeData"],
                            type: 'bar'
                        }]
                    };
                    var caseOption = {
                        legend:{
                            data:['实际消耗时间']
                        },
                        tooltip:{
                            show:true,
                            trigger:'axis'
                        },
                        toolbox:{
                            show:true,
                            feature:{
                                saveAsImage:{
                                    show:true
                                }
                            }
                        },
                        grid:{
                            top:"50px",
                            left:"50px",
                            right:"15px",
                            bottom:"50px"
                        },
                        xAxis: {
                            type: 'category',
                            data: data["xAxis"]
                        },
                        yAxis: {},
                        series: [{
                            data: data["caseData"],
                            type: 'bar'
                        }]
                    };
                    var bugOption = {
                        legend:{
                            data:['实际消耗时间']
                        },
                        tooltip:{
                            show:true,
                            trigger:'axis'
                        },
                        toolbox:{
                            show:true,
                            feature:{
                                saveAsImage:{
                                    show:true
                                }
                            }
                        },
                        grid:{
                            top:"50px",
                            left:"50px",
                            right:"15px",
                            bottom:"50px"
                        },
                        xAxis: {
                            type: 'category',
                            data: data["xAxis"]
                        },
                        yAxis: {},
                        series: [{
                            data: data["bugData"],
                            type: 'bar'
                        }]
                    };
                    estimatedTimeChart.setOption(estimatedTimeOption);
                    realTimeChart.setOption(realTimeOption);
                    caseChart.setOption(caseOption);
                    bugChart.setOption(bugOption);
                }
            },
            error:function (data) {
                alert("周对比数据获取失败！")
            }
        })
    }

    /**
     * 获取月对比数据并在柱状图上显示
     */
    function monthCompare() {
        var estimatedTimeChart = echarts.init(document.getElementById("estimatedTimeMonth"));
        var realTimeChart = echarts.init(document.getElementById("realTimeMonth"));
        var caseChart = echarts.init(document.getElementById("caseMonth"));
        var bugChart = echarts.init(document.getElementById("bugMonth"));

        var monthFrom = $("#selectMonthFrom").val();
        var monthTo = $("#selectMonthTo").val();
        var selectUser = $("#selectUser").val();

        var week = {
            "selectMonthFrom":monthFrom,
            "selectMonthTo":monthTo,
            "selectUser":selectUser
        };
        $.ajax({
            type:"post",
            url:"<%=request.getContextPath()%>/week/getMonthCompare",
            contentType: "application/json;charset=utf-8",
            data:JSON.stringify(week),
            success:function (data) {
                if(data != null){
                    var estimatedTimeOption = {
                        legend:{
                            data:['预计消耗时间']
                        },
                        tooltip:{
                            show:true,
                            trigger:'axis'
                        },
                        toolbox:{
                            show:true,
                            feature:{
                                saveAsImage:{
                                    show:true
                                }
                            }
                        },
                        grid:{
                            top:"50px",
                            left:"50px",
                            right:"15px",
                            bottom:"50px"
                        },
                        xAxis: {
                            type: 'category',
                            data: data["xAxis"]
                        },
                        yAxis: {},
                        series: [{
                            data: data["estimatedTimeData"],
                            type: 'bar'
                        }]
                    };
                    var realTimeOption = {
                        legend:{
                            data:['实际消耗时间']
                        },
                        tooltip:{
                            show:true,
                            trigger:'axis'
                        },
                        toolbox:{
                            show:true,
                            feature:{
                                saveAsImage:{
                                    show:true
                                }
                            }
                        },
                        grid:{
                            top:"50px",
                            left:"50px",
                            right:"15px",
                            bottom:"50px"
                        },
                        xAxis: {
                            type: 'category',
                            data: data["xAxis"]
                        },
                        yAxis: {},
                        series: [{
                            data: data["realTimeData"],
                            type: 'bar'
                        }]
                    };
                    var caseOption = {
                        legend:{
                            data:['实际消耗时间']
                        },
                        tooltip:{
                            show:true,
                            trigger:'axis'
                        },
                        toolbox:{
                            show:true,
                            feature:{
                                saveAsImage:{
                                    show:true
                                }
                            }
                        },
                        grid:{
                            top:"50px",
                            left:"50px",
                            right:"15px",
                            bottom:"50px"
                        },
                        xAxis: {
                            type: 'category',
                            data: data["xAxis"]
                        },
                        yAxis: {},
                        series: [{
                            data: data["caseData"],
                            type: 'bar'
                        }]
                    };
                    var bugOption = {
                        legend:{
                            data:['实际消耗时间']
                        },
                        tooltip:{
                            show:true,
                            trigger:'axis'
                        },
                        toolbox:{
                            show:true,
                            feature:{
                                saveAsImage:{
                                    show:true
                                }
                            }
                        },
                        grid:{
                            top:"50px",
                            left:"50px",
                            right:"15px",
                            bottom:"50px"
                        },
                        xAxis: {
                            type: 'category',
                            data: data["xAxis"]
                        },
                        yAxis: {},
                        series: [{
                            data: data["bugData"],
                            type: 'bar'
                        }]
                    };
                    estimatedTimeChart.setOption(estimatedTimeOption);
                    realTimeChart.setOption(realTimeOption);
                    caseChart.setOption(caseOption);
                    bugChart.setOption(bugOption);
                }
            },
            error:function (data) {
                alert("周对比数据获取失败！")
            }
        })
    }
</script>
</body>
</html>


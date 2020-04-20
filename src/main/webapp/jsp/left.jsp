<%--
  Created by IntelliJ IDEA.
  User: guoxx
  Date: 2018/6/29
  Time: 14:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<aside class="main-sidebar">

    <!-- sidebar: style can be found in sidebar.less -->
    <section class="sidebar">

        <!-- 用户登录信息 -->
        <div class="user-panel">
            <div class="pull-left image">
                <img src="<%=request.getContextPath()%>/img/user2-160x160.jpg" class="img-circle" alt="User Image">
            </div>
            <div class="pull-left info">
                <p>Alexander Pierce</p>
                <!-- Status -->
                <a href="#"><i class="fa fa-circle text-success"></i> Online</a>
            </div>
        </div>

        <!-- 搜索框 -->
        <form action="#" method="get" class="sidebar-form">
            <div class="input-group">
                <input type="text" name="q" class="form-control" placeholder="Search...">
                <span class="input-group-btn">
              <button type="submit" name="search" id="search-btn" class="btn btn-flat"><i class="fa fa-search"></i>
              </button>
            </span>
            </div>
        </form>
        <!-- /.search form -->

        <!-- 导航栏 -->
        <ul class="sidebar-menu" data-widget="tree">
            <li class="header">系统菜单</li>
            <!-- Optionally, you can add icons to the links -->
            <li class="treeview">
                <a href="#"><i class="fa fa-link"></i> <span>用例管理</span>
                    <span class="pull-right-container">
                      <i class="fa fa-angle-left pull-right"></i>
                    </span>
                </a>
                <ul class="treeview-menu" id="apiList"></ul>
            </li>
            <li class="treeview">
                <a href="#"><i class="fa fa-fw fa-wrench"></i> <span>用例数据配置</span>
                    <span class="pull-right-container">
                        <i class="fa fa-angle-left pull-right"></i>
                    </span>
                </a>
                <ul class="treeview-menu">
                    <li><a href="<%=request.getContextPath()%>/param/getParams">参数化配置</a></li>
                    <li><a href="###">前后置动作</a></li>
                </ul>
            </li>
            <li class="treeview">
                <a href="#"><i class="fa fa-fw fa-navicon"></i> <span>项目管理</span>
                    <span class="pull-right-container">
                        <i class="fa fa-angle-left pull-right"></i>
                    </span>
                </a>
                <ul class="treeview-menu">
                    <li><a href="<%=request.getContextPath()%>/item/getItems">项目管理</a></li>
                    <li><a href="###">测试计划管理</a></li>
                    <li><a href="###">用例管理</a></li>
                </ul>
            </li>
            <li class="treeview">
                <a href="#"><i class="fa fa-pie-chart"></i> <span>周报管理</span>
                    <span class="pull-right-container">
                  <i class="fa fa-angle-left pull-right"></i>
                </span>
                </a>
                <ul class="treeview-menu">
                    <li><a href="<%=request.getContextPath()%>/week/getAllWeek">周报管理</a></li>
                    <li><a href="<%=request.getContextPath()%>/week/getWeeklyCount">周报统计</a></li>
                    <li><a href="<%=request.getContextPath()%>/week/getWeeklyCompare">对比分析</a></li>
                </ul>
            </li>
            <li class="treeview">
                <a href="#"><i class="fa fa-fw fa-user-plus"></i> <span>用户管理</span>
                    <span class="pull-right-container">
                  <i class="fa fa-angle-left pull-right"></i>
                </span>
                </a>
                <ul class="treeview-menu">
                    <li><a href="<%=request.getContextPath()%>/user/userList">用户列表</a></li>
                    <li><a href="<%=request.getContextPath()%>/role/getRoles">角色列表</a></li>
                </ul>
            </li>
            <li class="treeview">
                <a href="#"><i class="fa fa-fw fa-gear"></i> <span>系统设置</span>
                    <span class="pull-right-container">
                  <i class="fa fa-angle-left pull-right"></i>
                </span>
                </a>
                <ul class="treeview-menu">
                    <li><a href="###">环境配置</a></li>
                </ul>
            </li>
        </ul>
        <!-- /.sidebar-menu -->
    </section>
    <!-- /.sidebar -->
</aside>



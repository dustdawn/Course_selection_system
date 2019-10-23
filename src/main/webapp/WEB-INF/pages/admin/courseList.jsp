<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
  String path = request.getContextPath();
  String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
          + path + "/";
  String flag = request.getParameter("flag");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>Course_selection_system | 界面主页</title>
  <!-- Tell the browser to be responsive to screen width -->
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
  <!-- Bootstrap 3.3.7 -->
  <link rel="stylesheet" href="<%=basePath%>/bower_components/bootstrap/dist/css/bootstrap.min.css">
  <!-- Font Awesome -->
  <link rel="stylesheet" href="<%=basePath%>/bower_components/font-awesome/css/font-awesome.min.css">
  <!-- Ionicons -->
  <link rel="stylesheet" href="<%=basePath%>/bower_components/Ionicons/css/ionicons.min.css">
  <!-- Theme style -->
  <link rel="stylesheet" href="<%=basePath%>/dist/css/AdminLTE.min.css">
  <!-- AdminLTE Skins. Choose a skin from the css/skins
       folder instead of downloading all of them to reduce the load. -->
  <link rel="stylesheet" href="<%=basePath%>/dist/css/skins/_all-skins.min.css">
  <!-- Pace style -->
  <link rel="stylesheet" href="<%=basePath%>/plugins/pace/pace.min.css">

  <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
  <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
  <!--[if lt IE 9]>
  <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
  <![endif]-->

  <!-- Google Font -->
  <link rel="stylesheet"
        href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700,300italic,400italic,600italic">


</head>
<body class="hold-transition skin-blue sidebar-mini">
<!-- Site wrapper -->
<div class="wrapper">

  <header class="main-header">
    <!-- Logo -->
    <a href="<%=basePath%>/pages/admin/index" class="logo">
      <!-- mini logo for sidebar mini 50x50 pixels -->
      <span class="logo-mini"><b>C</b>S</span>
      <!-- logo for regular state and mobile devices -->
      <span class="logo-lg"><b>选课管理系统</b></span>
    </a>
    <!-- Header Navbar: style can be found in header.less -->
    <nav class="navbar navbar-static-top">
      <!-- Sidebar toggle button-->
      <a href="#" class="sidebar-toggle" data-toggle="push-menu" role="button">
        <span class="sr-only">导航菜单</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </a>
      <!--这边是左上角工具栏-->
      <div class="navbar-custom-menu">
        <ul class="nav navbar-nav">

          <!-- 账号注销 -->
          <li class="dropdown user user-menu">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
              <img src="<%=basePath%>/image/avg.jpg" class="user-image" alt="User Image">
              <span class="hidden-xs">${userSession.name}</span>
            </a>
            <ul class="dropdown-menu">
              <!-- User image -->
              <li class="user-header">
                <img src="<%=basePath%>/image/avg.jpg" class="img-circle" alt="User Image">

                <p>
                  当前登录账号：${userSession.no}
                  <small>${currentTime}</small>
                </p>
              </li>
              <!-- Menu Body -->
              <%--<li class="user-body">
                <div class="row">
                  <div class="col-xs-4 text-center">
                    <a href="#">user菜单体修改</a>
                  </div>
                  <div class="col-xs-4 text-center">
                    <a href="#">Sales</a>
                  </div>
                  <div class="col-xs-4 text-center">
                    <a href="#">Friends</a>
                  </div>
                </div>
                <!-- /.row -->
              </li>--%>
              <!-- Menu Footer-->
              <li class="user-footer">

                <div class="pull-right">
                  <a href="<%=basePath%>/admin/logout" class="btn btn-default btn-flat">退出登录</a>
                </div>
              </li>
            </ul>
          </li>

        </ul>
      </div>
    </nav>
  </header>

  <!-- =============================================== -->
  <!--这边是左边列表菜单-->
  <!-- Left side column. contains the sidebar -->
  <aside class="main-sidebar">
    <!-- sidebar: style can be found in sidebar.less -->
    <section class="sidebar">
      <!-- Sidebar user panel -->
      <div class="user-panel">
        <div class="pull-left image">
          <img src="<%=basePath%>/image/avg.jpg" class="img-circle" alt="User Image">
        </div>
        <div class="pull-left info">
          <p>${userSession.name}</p>
          <a href="#"><i class="fa fa-circle text-success"></i> 登录身份：管理员</a>
        </div>
      </div>

      <!-- sidebar menu: : style can be found in sidebar.less -->
      <ul class="sidebar-menu" data-widget="tree">
        <li class="header">导航菜单</li>

        <!--功能菜单都用这个模板-->

        <li>
          <a href="<%=basePath%>/pages/admin/index">
            <i class="fa fa-folder"></i> <span>主页</span>
          </a>
        </li>

        <li class="treeview">
          <a href="#">
            <i class="fa fa-building-o"></i> <span>院系管理</span>
            <span class="pull-right-container">
              <i class="fa fa-angle-left pull-right"></i>
            </span>
          </a>
          <ul class="treeview-menu">
            <li><a href="<%=basePath%>/pages/admin/deptForm"><i class="fa fa-circle-o"></i> 院系录入</a></li>
            <li><a href="<%=basePath%>/admin/deptList"><i class="fa fa-circle-o"></i> 院系列表</a></li>
          </ul>
        </li>


        <li class="treeview">
          <a href="#">
            <i class="fa fa-graduation-cap"></i> <span>教师管理</span>
            <span class="pull-right-container">
              <i class="fa fa-angle-left pull-right"></i>
            </span>
          </a>
          <ul class="treeview-menu">
            <li><a href="<%=basePath%>/pages/admin/teacherForm"><i class="fa fa-circle-o"></i> 教师录入</a></li>
            <li><a href="<%=basePath%>/admin/teacherList"><i class="fa fa-circle-o"></i> 教师列表</a></li>
          </ul>
        </li>

        <li class="treeview">
          <a href="#">
            <i class="fa fa-group"></i> <span>学生管理</span>
            <span class="pull-right-container">
              <i class="fa fa-angle-left pull-right"></i>
            </span>
          </a>
          <ul class="treeview-menu">
            <li><a href="<%=basePath%>/pages/admin/studentForm"><i class="fa fa-circle-o"></i> 学生录入</a></li>
            <li><a href="<%=basePath%>/admin/studentList"><i class="fa fa-circle-o"></i> 学生列表</a></li>
          </ul>
        </li>

        <li class="active treeview">
          <a href="#">
            <i class="fa fa-book"></i> <span>课程管理</span>
            <span class="pull-right-container">
              <i class="fa fa-angle-left pull-right"></i>
            </span>
          </a>
          <ul class="treeview-menu">
            <li><a href="<%=basePath%>/pages/admin/courseForm"><i class="fa fa-circle-o"></i> 课程录入</a></li>
            <li class="active"><a href="<%=basePath%>/admin/courseList"><i class="fa fa-circle-o"></i> 课程列表</a></li>
          </ul>
        </li>

        <li class="treeview">
          <a href="#">
            <i class="fa fa-info"></i> <span>个人信息及密码</span>
            <span class="pull-right-container">
              <i class="fa fa-angle-left pull-right"></i>
            </span>
          </a>
          <ul class="treeview-menu">
            <li><a href="<%=basePath%>/pages/admin/info"><i class="fa fa-circle-o"></i> 个人信息</a></li>
            <li><a href="<%=basePath%>/pages/admin/pswChange"><i class="fa fa-circle-o"></i> 密码修改</a></li>
          </ul>
        </li>

      </ul>
    </section>
    <!-- /.sidebar -->
  </aside>

  <!-- =============================================== -->

  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
      <h1>
        课程列表
        <small>信息显示</small>
      </h1>
      <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> 导航菜单</a></li>
        <li><a href="#">课程管理</a></li>
        <li class="active">课程列表</li>
      </ol>
    </section>

    <!-- Main content -->
    <section class="content">

      <div class="row">
        <div class="col-xs-12">

          <div class="box box-primary">
            <div class="box-header with-border">
              <div class="col">


                <label class="form-inline" for="searchByNo" style="padding-left: 40px"/>课程号查询：
                <input type="text" class="form-control" id="searchByNo" value=""/>
                </label>
                <span style="padding-right: 40px">
                  <button type="button" class="btn btn-info btn-flat" onclick="getList()">筛选</button>
                </span>



                <label class="form-inline" for="searchByName" style="padding-left: 40px"/>课程名查询：
                <input type="text" class="form-control" id="searchByName" value=""/>
                </label>
                <span>
                  <button type="button" class="btn btn-info btn-flat" onclick="getList()">筛选</button>
                </span>

              </div>


            </div>
            <%--/box-head--%>
            <div class="box-body">
              <table id="courseList" class="table table-bordered table-hover">
                <thead>
                <tr>
                  <th>课程类型</th>
                  <th>课程号</th>
                  <th>课程名</th>
                  <th>授课教师</th>
                  <th>所属学院</th>
                  <th>课程周期</th>
                  <th>授课地点</th>
                  <th>学分</th>
                  <th>剩余名额</th>
                  <th>修改</th>
                  <th>删除</th>
                  <th>查看选课学生</th>
                </tr>
                </thead>
                <tbody>

                <c:forEach var="item" items="${courseList}" varStatus="staturs">
                  <tr>
                    <td>${item.type}</td>
                    <td>${item.cno}</td>
                    <td>${item.name}</td>
                    <td>${item.teacher.name}</td>
                    <td>${item.dept.name}</td>
                    <td>${item.date}</td>
                    <td>${item.place}</td>
                    <td>${item.credit}</td>
                    <td>${item.total}</td>
                    <td><a href="<%=basePath%>/admin/courseEdit?cno=${item.cno}">编辑</a></td>
                    <td><a href="#" data-toggle="modal" data-target="#deleteConfirm">删除</a>
                        <a href="<%=basePath%>/admin/courseDelete?cno=${item.cno}" id="yes"></a>
                    </td>
                    <td><a href="javascript:void(0);" onclick="viewList('${item.cno}')">查看</a></td>
                  </tr>
                </c:forEach>

                </tbody>
                <%--<tfoot>
                <tr>
                  <th>工号</th>
                  <th>姓名</th>
                  <th>密码</th>
                  <th>性别</th>
                  <th>手机</th>
                </tr>
                </tfoot>--%>
              </table>

              <%--修改成功窗口--%>

              <div class="modal modal-info fade" id="ifSuccess">
                <div class="modal-dialog">
                  <div class="modal-content">
                    <div class="modal-header">
                      <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span></button>
                      <h4 class="modal-title">提示</h4>
                    </div>
                    <div class="modal-body">
                      <h4 class="modal-title" align="center">修改成功</h4>
                    </div>
                    <div class="modal-footer">
                      <button type="button" class="btn btn-outline pull-right" data-dismiss="modal">关闭</button>
                    </div>
                  </div>
                  <!-- /.modal-content -->
                </div>
                <!-- /.modal-dialog -->
              </div>
              <%--/修改成功窗口--%>

              <%--删除窗口--%>

              <div class="modal fade" id="deleteConfirm">
                <div class="modal-dialog">
                  <div class="modal-content">
                    <div class="modal-header">
                      <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span></button>
                      <h4 class="modal-title">提示</h4>
                    </div>
                    <div class="modal-body">
                      <h4 class="modal-title" align="center">确认要删除吗？</h4>
                    </div>
                    <div class="modal-footer">
                      <button type="button" class="btn btn-default pull-left" data-dismiss="modal">取消</button>
                      <button type="button" class="btn btn-primary" id="delete">确认</button>
                    </div>
                  </div>
                  <!-- /.modal-content -->
                </div>
                <!-- /.modal-dialog -->
              </div>
              <!-- /.modal -->
              <%--/删除窗口--%>


              <%--窗口--%>

              <div class="modal fade" id="showList">
                <div class="modal-dialog">
                  <div class="modal-content">
                    <div class="modal-header">
                      <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span></button>
                      <h4 class="modal-title">学生列表</h4>
                    </div>
                    <div class="modal-body">
                      <%--表格--%>
                      <table id="studentList" class="table">
                        <thead>
                        <tr>
                          <th>学号</th>
                          <th>姓名</th>
                          <th>密码</th>
                          <th>性别</th>
                          <th>院系</th>
                          <th>手机</th>
                          <th>生日</th>
                        </tr>
                        </thead>
                        <tbody>

                        </tbody>
                      </table>
                      <%--/表格--%>

                    </div>
                    <div class="modal-footer">
                      <button type="button" class="btn btn-default pull-right" data-dismiss="modal">取消</button>
                    </div>
                  </div>
                  <!-- /.modal-content -->
                </div>
                <!-- /.modal-dialog -->
              </div>
              <!-- /.modal -->
              <%--/窗口--%>


            </div>
            <%--/box-body--%>
          </div>
          <%--/box--%>
        </div>
        <%--/col-xs-12--%>
      </div>
      <%--/row--%>


    </section>
    <!-- /.content -->
  </div>
  <!-- /.content-wrapper -->


</div>
<!-- ./wrapper -->

<!-- jQuery 3 -->
<script src="<%=basePath%>/bower_components/jquery/dist/jquery.min.js"></script>
<!-- Bootstrap 3.3.7 -->
<script src="<%=basePath%>/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
<!-- DataTables -->
<script src="<%=basePath%>/bower_components/datatables.net/js/jquery.dataTables.min.js"></script>
<script src="<%=basePath%>/bower_components/datatables.net-bs/js/dataTables.bootstrap.min.js"></script>
<!-- PACE -->
<script src="<%=basePath%>/bower_components/PACE/pace.min.js"></script>
<!-- SlimScroll -->
<script src="<%=basePath%>/bower_components/jquery-slimscroll/jquery.slimscroll.min.js"></script>
<!-- FastClick -->
<script src="<%=basePath%>/bower_components/fastclick/lib/fastclick.js"></script>
<!-- AdminLTE App -->
<script src="<%=basePath%>/dist/js/adminlte.min.js"></script>
<!-- AdminLTE for demo purposes -->
<script src="<%=basePath%>/dist/js/demo.js"></script>
<!-- page script -->
<script type="text/javascript">
  $(function () {
    //删除确认事件
    let flag = <%=flag%>;
    console.log(flag);
    if (flag != null && flag=='success') {
      $("#ifSuccess").modal('show')
    }

    //删除事件
    $("#delete").on('click',function () {
      $("#deleteConfirm").modal("hide")
      window.location.href = $('#yes').attr('href');
    })



    $('#courseList').DataTable({
      'paging'      : true,
      'lengthChange': false,
      'searching'   : false,
      'ordering'    : true,
      'info'        : true,
      'autoWidth'   : false
    })

    //关闭列表事件
    $('#showList').on('hide.bs.modal', function () {
      let dom = $("#studentList").find('tbody');
      //清空列表
      dom.html('');
    })

  })

  function viewList(no) {
    $("#showList").modal("show");
    $.ajax({
      //请求方式
      type: "POST",
      //请求的媒体类型
      contentType: "application/x-www-form-urlencoded;charset=UTF-8",
      //请求地址
      url: "<%=basePath%>/course/studentList",
      data: {"cno" : no},
      //返回类型
      // dataType:"json",
      //请求成功
      success: function (result) {
        console.log("获取列表成功", result);
        if (null != result) {
          let dom = $("#studentList").find('tbody');
          let str = '';
          for (let i = 0; i < result.length; i++) {
            let birthday = result[i].birthday;
            let date = new Date(birthday);
            result[i].birthday = date.getFullYear()+'-'+(date.getMonth()+1)+'-'+date.getDate();
            str += "<tr>" +
                      "<td>"+result[i].sno+"</td>" +
                      "<td>"+result[i].name+"</td>" +
                      "<td>"+result[i].password+"</td>" +
                      "<td>"+result[i].sex+"</td>" +
                      "<td>"+result[i].dept.name+"</td>" +
                      "<td>"+result[i].mobile+"</td>" +
                      "<td>"+result[i].birthday+"</td>" +
                    "</tr>"

          }
          console.log("str is",str);
          dom.append(str);
        }

      },
      //请求失败，包含具体的错误信息
      error: function (e) {
        console.log("失败");
        console.log(e.status);
        console.log(e.responseText);
      }
    });
  }
</script>
</body>
</html>

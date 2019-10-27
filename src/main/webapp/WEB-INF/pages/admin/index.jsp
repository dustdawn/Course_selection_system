<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%
  String path = request.getContextPath();
  String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
          + path + "/";
%>


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

        <li class="active">
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

        <li class="treeview">
          <a href="#">
            <i class="fa fa-book"></i> <span>课程管理</span>
            <span class="pull-right-container">
              <i class="fa fa-angle-left pull-right"></i>
            </span>
          </a>
          <ul class="treeview-menu">
            <li><a href="<%=basePath%>/pages/admin/courseForm"><i class="fa fa-circle-o"></i> 课程录入</a></li>
            <li><a href="<%=basePath%>/admin/courseList"><i class="fa fa-circle-o"></i> 课程列表</a></li>
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
            <li><a href="<%=basePath%>/admin/info"><i class="fa fa-circle-o"></i> 个人信息</a></li>
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
        主页
        <small>通知公告</small>
      </h1>
      <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> 导航菜单</a></li>
        <li><a href="<%=basePath%>/pages/admin/index">主页</a></li>
        <li class="active">index</li>
      </ol>
    </section>

    <!-- Main content -->
    <section class="content">

      <div class="callout callout-info">
        <h4>欢迎使用在线选课系统</h4>

        <p>选课过程中出现问题，请联系管理员</p>
      </div>
      <form action="<%=basePath%>/admin/index" method="post">

        <div class="box box-primary">
          <div class="box-header">
            <h3 class="box-title">添加公告</h3>
            <!-- tools box -->
            <div class="pull-right box-tools">
              <button type="button" class="btn btn-default btn-sm" data-widget="collapse" data-toggle="tooltip" title="" data-original-title="Collapse" >
                <i class="fa fa-minus"></i></button>
            </div>
            <!-- /. tools -->
          </div>

          <%--公告体--%>
          <div class="box-body">
            <div class="form-group col-md-12">
              <label >标题</label>
              <input  type="text" class="form-control" id="title" name="title" placeholder="标题">
            </div>
            <div class="form-group col-md-12">
              <label>正文</label>
              <textarea class="form-control " rows="10" name="text" id="text" placeholder="请输入公告"></textarea>
            </div>

            <div class="box-footer" align="center">
              <button type="button" class="btn btn-warning" id="empty">清空</button>
              <button type="submit" class="btn btn-primary" id="save">保存</button>
            </div>

          </div>
          <%--/公告体--%>

      </form>



      <!-- 内容 -->
      <div class="box">
        <div class="box-header with-border">
          <h3 class="box-title">关于2019-2020学年选课通知</h3>

          <div class="box-tools pull-right">
            <button type="button" class="btn btn-box-tool" data-widget="collapse" data-toggle="tooltip"
                    title="Collapse">
              <i class="fa fa-minus"></i></button>

          </div>
        </div>
        <div class="box-body">
          各学院、2019级新同学：<br/>

          2019-2020学年第一学期2019级新生选课工作将于8月21日开始，请学院做好新生选课指导工作，各位同学按照规定时间和选课指南中相关要求选课。<br/><br/>

          选课时间安排如下：<br/><br/>

          第一轮选课：8月21日8:30－8月25日24:00，新生在校外选课。<br/>

          第二轮选课：新生入学教育周内（8月28日－8月30日），学校安排机房分时段由学院（部）指导学生选课。<br/>

          第三轮选课：开学第一周内（9月2日－8日），学生退选和补选。<br/>

          第二轮选课程结束后，教务处将根据选课情况对部分教学班进行调整，请各位同学在选课结束后密切关注各自课表的变化，以开学第二周系统中最终显示个人课表为准。<br/>

          本学期新生教学从2019年9月2日开始。<br/>
          <br/>
          <br/>
          <div class="row">
            <div class="col-xs-12 text-center">

            </div>
          </div>
          <div class="ajax-content">
            <table class="table table-bordered text-center">
              <tr>
                <th><button type="button" class="btn btn-block btn-primary">Primary</button></th>
                <th><button type="button" class="btn btn-block btn-success">Success</button></th>
                <th><button type="button" class="btn btn-block btn-info">Info</button></th>
                <th><button type="button" class="btn btn-block btn-danger">Danger</button></th>
                <th><button type="button" class="btn btn-block btn-warning">Warning</button></th>
              </tr>
            </table>
          </div>
        </div>
        <%--正文结束--%>
        <!-- /.box-body -->
        <!-- /.box-footer-->
      </div>
      <!-- /.box -->




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
<!-- Bootstrap WYSIHTML5 -->
<script src="<%=basePath%>/plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.all.min.js"></script>
<!-- page script -->
<script type="text/javascript">
  // To make Pace works on Ajax calls
  $(document).ajaxStart(function () {
    Pace.restart()
  })
  $('.ajax').click(function () {
    $.ajax({
      url: '#', success: function (result) {
        $('.ajax-content').html('<hr>Ajax Request Completed !')
      }
    })
  })

  // 清空事件
  $(document).ready(function () {
    $("#empty").bind("click", function () {
      //alert(55);
      $("#title").val("");
      $("#text").val("");
    })

</script>
</body>
</html>

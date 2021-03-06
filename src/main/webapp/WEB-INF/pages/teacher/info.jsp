<%@ page import="cn.njit.entity.Teacher" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%
  String path = request.getContextPath();
  String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
          + path + "/";
  Teacher teacher = (Teacher) request.getAttribute("teacher");
  String birthday = "";
  if (null != teacher) {
    birthday = new SimpleDateFormat("yyyy-MM-dd").format(teacher.getBirthday());
  }
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
    <a href="<%=basePath%>/teacher/index" class="logo">
      <!-- mini logo for sidebar mini 50x50 pixels -->
      <span class="logo-mini"><b>C</b>S</span>
      <!-- logo for regular state and mobile devices -->
      <span class="logo-lg"><b>选课系统</b></span>
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
                  当前登录账号：${userSession.tno}
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
                  <a href="<%=basePath%>/teacher/logout" class="btn btn-default btn-flat">退出登录</a>
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
          <a href="#"><i class="fa fa-circle text-success"></i> 登录身份：教师</a>
        </div>
      </div>

      <!-- sidebar menu: : style can be found in sidebar.less -->
      <ul class="sidebar-menu" data-widget="tree">
        <li class="header">导航菜单</li>

        <!--功能菜单都用这个模板-->

        <li>
          <a href="<%=basePath%>/teacher/index">
            <i class="fa fa-folder"></i> <span>主页</span>
          </a>
        </li>


        <li class="treeview">
          <a href="#">
            <i class="fa fa-book"></i> <span>课程管理</span>
            <span class="pull-right-container">
              <i class="fa fa-angle-left pull-right"></i>
            </span>
          </a>
          <ul class="treeview-menu">
            <li><a href="<%=basePath%>/teacher/managePublic"><i class="fa fa-circle-o"></i> 公选课</a></li>
            <li><a href="<%=basePath%>/teacher/manageElective"><i class="fa fa-circle-o"></i> 选修课</a></li>
          </ul>
        </li>

        <li class="active treeview">
          <a href="#">
            <i class="fa fa-info"></i> <span>个人信息及密码</span>
            <span class="pull-right-container">
              <i class="fa fa-angle-left pull-right"></i>
            </span>
          </a>
          <ul class="treeview-menu">
            <li class="active"><a href="<%=basePath%>/teacher/info"><i class="fa fa-circle-o"></i> 个人信息</a></li>
            <li><a href="<%=basePath%>/pages/teacher/pswChange"><i class="fa fa-circle-o"></i> 密码修改</a></li>
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
        个人信息
        <small>信息修改</small>
      </h1>
      <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> 导航菜单</a></li>
        <li><a href="#">个人信息及密码</a></li>
        <li class="active">个人信息</li>
      </ol>
    </section>

    <!-- Main content -->
    <section class="content">

      <!-- Default box -->
      <div class="box box-primary">
        <div class="box-header with-border">
          <h3 class="box-title">修改</h3>

        </div>

        <div class="box-body">

          <form action="<%=basePath%>/teacher/teacherUpdate" method="post">
            <%--表单盒子体--%>
            <div class="box-body">
              <div class="col-md-12">
                <%--工号--%>
                <div class="col-md-6">
                  <div class="form-group">
                    <label for="teacherNo">教师工号</label>
                    <div class="input-group name">
                      <div class="input-group-addon">
                        <i class="fa fa-university"></i>
                      </div>
                      <input type="text" readonly class="form-control" id="teacherNo" name="tno" value="${teacher.tno}" placeholder="请输入教师工号">
                      <span class="input-group-addon"><i class="fa fa-exclamation"></i></span>
                    </div>
                  </div>
                </div>
                <%--姓名--%>
                <div class="col-md-6">
                  <div class="form-group">
                    <label for="teacherName">姓名</label>
                    <div class="input-group name">
                      <div class="input-group-addon">
                        <i class="fa fa-user"></i>
                      </div>
                      <input type="text" class="form-control" id="teacherName" name="name" value="${teacher.name}" placeholder="请输入姓名">
                      <span class="input-group-addon"><i class="fa fa-exclamation"></i></span>
                    </div>
                  </div>
                </div>

              </div>

              <div class="col-md-12">
                <%--性别--%>
                <!-- 可选下拉框 -->
                <div class="col-md-6">
                  <div class="form-group">
                    <label for="teacherSex">性别</label>
                    <div class="input-group sex">
                      <div class="input-group-addon">
                        <i class="fa fa-male"></i>
                        <i class="fa fa-female"></i>
                      </div>
                      <select class="form-control select2" style="width: 100%;" name="sex" id="teacherSex">
                        <option value="">---请选择---</option>
                        <option value="男" <%if(teacher.getSex().equals("男")){%>selected<%}%> >男</option>
                        <option value="女" <%if(teacher.getSex().equals("女")){%>selected<%}%> >女</option>
                      </select>
                    </div>

                  </div>
                </div>

                <%--手机号--%>
                <div class="col-md-6">
                  <div class="form-group">
                    <label for="teacherMobile">手机号码</label>
                    <div class="input-group phone">
                      <div class="input-group-addon">
                        <i class="fa fa-phone"></i>
                      </div>
                      <input type="text" class="form-control" id="teacherMobile" name="mobile" value="${teacher.mobile}" placeholder="请输入手机号">
                    </div>
                  </div>
                </div>

              </div>


              <div class="col-md-12">
                <%--/可选下拉框--%>

                <%--出生日期--%>
                <div class="col-md-6">
                  <div class="form-group">
                    <label for="teacherDate">出生日期</label>
                    <div class="input-group date">
                      <div class="input-group-addon">
                        <i class="fa fa-calendar"></i>
                      </div>
                      <input type="text" class="form-control pull-right" id="teacherDate" name="birthday" value="<%=birthday%>"  placeholder="yyyy-mm-dd">
                    </div>
                  </div>
                </div>

              </div>
            </div>



            <div class="box-footer" align="center">
              <button type="button" class="btn btn-warning" id="empty">清空</button>
              <button type="submit" class="btn btn-primary">保存</button>
            </div>
          </form>
          <%--表单尾--%>


          <div class="row">
            <div class="col-xs-12 text-center">
              <strong style="color: red">请确保添加教师信息未重复</strong>
            </div>
          </div>

        </div>

      </div>


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
<%--bootstrapValidator的cdn--%>
<link href="https://cdn.bootcss.com/bootstrap-validator/0.5.3/css/bootstrapValidator.min.css" rel="stylesheet">
<script src="https://cdn.bootcss.com/bootstrap-validator/0.5.3/js/bootstrapValidator.min.js"></script>
<!-- page script -->
<script type="text/javascript">
  // 清空事件
  $(document).ready(function () {
    $("#empty").bind("click", function () {
      //alert(55);
      $("#teacherName").val("");

      $("#teacherSex").val("");
      $("#teacherMobile").val("");
      $("#teacherDate").val("");
    })
  })

  $(function () {
    $('form').bootstrapValidator({
      message: 'This value is not valid',
      feedbackIcons: {
        valid: 'glyphicon glyphicon-ok',
        invalid: 'glyphicon glyphicon-remove',
        validating: 'glyphicon glyphicon-refresh'
      },
      fields: {
        tno: {
          validators: {
            notEmpty: {
              message: '教师工号不能为空'
            }
          }
        },
        password: {
          validators: {
            notEmpty: {
              message: '密码不能为空'
            }
          }
        },
        name: {
          validators: {
            notEmpty: {
              message: '姓名不能为空'
            }
          }
        },
        mobile: {
          validators: {
            regexp: {
              regexp: /^1(3|4|5|6|7|8|9)\d{9}$/,
              message: '请输入正确的号码格式'
            }
          }
        },
        birthday: {
          validators: {
            regexp: {
              regexp: /^([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8])))$/,
              message: '请输入正确的日期格式'
            }
          }
        }
      }
    });
  });
</script>
</body>
</html>

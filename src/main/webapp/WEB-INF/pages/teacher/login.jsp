<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@page isELIgnored="false" %>
<%
  String path = request.getContextPath();
  String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
          + path + "/";
%>
<%
  String tno = "";
  String tpassword = "";
  //获取当前站点的所有Cookie
  Cookie[] cookies = request.getCookies();
  for (int i = 0;cookies != null && i < cookies.length; i++) {//对cookies中的数据进行遍历，找到用户名、密码的数据
    if ("tno".equals(cookies[i].getName())) {
      tno = cookies[i].getValue();
    } else if ("tpassword".equals(cookies[i].getName())) {
      tpassword = cookies[i].getValue();
    }

  }
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>Course_selection_system | 登录页</title>
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
  <!-- iCheck -->
  <link rel="stylesheet" href="<%=basePath%>/plugins/iCheck/square/blue.css">

  <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
  <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
  <!--[if lt IE 9]>
  <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
  <![endif]-->

  <!-- Google Font -->
  <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700,300italic,400italic,600italic">

  <style type="text/css">
    .bg{
      background: url("<%=basePath%>/image/login.jpg") ;
      /*background-position: center 0;
      background-repeat: no-repeat;
      background-attachment: fixed;
      background-size: cover;
      -webkit-background-size: cover;
      -o-background-size: cover;
      -moz-background-size: cover;
      -ms-background-size: cover;*/

    }
  </style>
</head>
<%--<body class="hold-transition login-page">--%>
<body background="<%=basePath%>/image/login.jpg" width="100%"; height="100%";>
<div class="login-box">
  <div class="login-logo">
    <a href="../../index2.html"><b>Course_selection_system</b><br/>在线选课系统</a>
  </div>
  <!-- /.login-logo -->
  <div class="login-box-body">
    <p class="login-box-msg">教师 登录 / LOGIN</p>

    <form action="<%=basePath%>/teacher/toLogin" method="post">
      <div class="form-group has-feedback">
        <input name="tno" value="<%=tno%>" type="user" class="form-control" placeholder="请输入教师工号">
        <span class="glyphicon glyphicon-user form-control-feedback"></span>
      </div>
      <div class="form-group has-feedback">
        <input name="password" value="<%=tpassword%>" type="password" class="form-control" placeholder="请输入密码">
        <span class="glyphicon glyphicon-lock form-control-feedback"></span>
      </div>


      <div class="form-group">

        <div class="row">
          <div class="col-xs-6">
            <input type="text" class="form-control" name="lastestCode" placeholder="请输入验证码">
          </div>

          <div class="col-xs-6">
            <img src="<%=basePath%>/verify/getGifCode" id="codeImg" onclick="changeCode()"/>
          </div>

        </div>
      </div>


      <div class="form-group message">
        <label id="my_msg" colspan="2" style="text-align: center;color: red;display: none">${errorMsg}</label>
      </div>
      <div class="row">
        <div class="col-xs-8">
          <div class="checkbox icheck">
            <label>
              <input value="true" type="checkbox" name="rememberMe"> 记住
            </label>
          </div>
        </div>
        <!-- /.col -->
        <div class="col-xs-4">
          <button type="submit" class="btn btn-primary btn-block btn-flat">登录</button>
        </div>
        <!-- /.col -->
      </div>
    </form>


    <div class="social-auth-links text-left">
      <a href="<%=basePath%>/pages/student/login" class="text-center">学生登录</a>
      <a href="<%=basePath%>/pages/admin/login" class="text-center">管理员登录</a>

    </div>

  </div>
  <!-- /.login-box-body -->
</div>
<!-- /.login-box -->

<!-- jQuery 3 -->
<script src="<%=basePath%>/bower_components/jquery/dist/jquery.min.js"></script>
<!-- Bootstrap 3.3.7 -->
<script src="<%=basePath%>/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
<%--bootstrapValidator的cdn--%>
<link href="https://cdn.bootcss.com/bootstrap-validator/0.5.3/css/bootstrapValidator.min.css" rel="stylesheet">
<script src="https://cdn.bootcss.com/bootstrap-validator/0.5.3/js/bootstrapValidator.min.js"></script>
<!-- iCheck -->
<script src="<%=basePath%>/plugins/iCheck/icheck.min.js"></script>
<script>
  $(function () {
    if (${errorMsg != null}) {
      $('#my_msg').show();
    }
  })
  $(function () {
    $('input').iCheck({
      checkboxClass: 'icheckbox_square-blue',
      radioClass: 'iradio_square-blue',
      increaseArea: '20%' /* optional */
    });
  });
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
          message: '用户名验证失败',
          validators: {
            notEmpty: {
              message: '用户名不能为空'
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
        lastestCode: {
          validators: {
            notEmpty: {
              message: '验证码不能为空'
            }
          }
        }

      }
    });
  });
</script>
</body>
</html>

<%@ page import="static java.awt.SystemColor.window" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>登录界面</title>
    <!-- Bootstrap Core CSS -->
    <link href="../bootstrap.min.css" rel="stylesheet">
    <!-- MetisMenu CSS -->
    <link href="../metisMenu.min.css" rel="stylesheet">
    <!-- Custom CSS -->
    <link href="../sb-admin-2.css" rel="stylesheet">
    <!-- Custom Fonts -->
    <link href="../font-awesome.min.css" rel="stylesheet" type="text/css">

</head>
<body background="../background.jpg">

<div class="container">
    <div class="row">
        <div class="col-md-4 col-md-offset-4">
            <div class="login-panel panel panel-default">
                <div class="panel-heading">
                    <h2 align="center">人才推荐系统</h2>
                </div>
                <div class="panel-body">
                    <form role="form" action="/login/${job}/" method="post">
                        <fieldset>
                            <div class="form-group">
                                <p>账号：</p> <input class="form-control" placeholder="手机号/邮箱" name="email" type="email" autofocus="">
                            </div>
                            <div class="form-group">
                                <p>密码：</p> <input class="form-control" placeholder="密码" name="password" type="password" value="">
                            </div>
                            <div class="checkbox">
                                <label>
                                    <input name="remember" type="checkbox" value="Remember Me">Remember Me
                                </label>
                                <label>
                                    <p>
                                        <%
                                            String error = request.getParameter("error");
                                            if(error != null) {
                                        %>
                                            <h5><font color="red"><%=error%></font></h5>
                                        <%
                                            }
                                        %>
                                    </p>
                                </label>
                            </div>
                            <!-- Change this to a button or input when using this as a form -->
                            <input type="submit" class="btn btn-lg btn-success btn-block"></a>
                        </fieldset>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>
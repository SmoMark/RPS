<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>错误页面</title>
    </head>

    <body>
        <h1 align="center">对不起，您尚未登陆，请先登录</h1>
        <br><br>
        <h3 align="center">系统将会在3秒钟后自动转向登陆页面，如未自动跳转，请点击<a href="/login/">这里</a></h3>
        <script> window.setInterval("location='/login/'",3000); </script>
    </body>
</html>
<%--
  Created by IntelliJ IDEA.
  User: Yinsel
  Date: 2024/7/7
  Time: 下午7:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>系统登录</title>
</head>
<body>
<form action="/api/login.action" method="post">
    <label>
        用户名:
        <input type="text" name="username">
    </label>
    <label>
        密码:
        <input type="password" name="password">
    </label>
    <input type="submit" value="登录">
    <a href="/api/register.action"><button>注册</button></a>
</form>
</body>
</html>

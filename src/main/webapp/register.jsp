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
    <title>用户注册</title>
</head>
<body>
<form action="/api/register.action" method="post">
    <label>
        用户名:
        <input type="text" name="username">
    </label>
    <label>
        密码:
        <input type="password" name="password">
    </label>
    <input type="submit" value="注册">
</form>
</body>
</html>

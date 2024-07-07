<%@ page import="cn.yinsel.GodzillaCrypto.model.User" %><%--
  Created by IntelliJ IDEA.
  User: Yinsel
  Date: 2024/7/7
  Time: 下午6:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>哥斯拉流量解密系统</title>
</head>
<body>
<%
    User user = (User) session.getAttribute("user");
    if (user == null) {
        out.println("<script>alert('请先登录');window.location='/login.jsp'</script>");
        return;
    }
%>
<div>你好，<%=user.getUsername()%><a href="/api/logout.action"><button>退出登录</button></a></div>
<form action="/api/decrypt.action" method="post">
    <label>
        密文：
       <input type="text" name="text" width="500" height="500">
    </label>
    <label>
        key：
        <input type="text" name="key">
    </label>
    <label>
        pass：
        <input type="text" name="pass">
    </label>
    <input type="submit" value="解密">
</form>
</body>
</html>

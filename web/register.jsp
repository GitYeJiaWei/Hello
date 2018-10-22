<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/10/22 0022
  Time: 11:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>注册</title>
    <link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
    <div>
        <h1>用户注册</h1>
        <form action="/Register" method="get">
            账户：<input type="text" name="account">
            <br/>
            <br/>
            密码：<input type="password" name="password" >
            <br/>
            <br/>
            确认密码：<input type="password" name="repassword">
            <br>
            <br>
            <input type="submit" value="注册">
            <input type="reset" value="重置">
        </form>
    </div>

</body>
</html>

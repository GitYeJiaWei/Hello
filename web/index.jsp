<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/9/17 0017
  Time: 14:36
  To change this template use File | Settings | File Templates.
  <% 代码片段 %>
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>登陆</title>
      <%--link 链接style.css--%>
      <link rel="stylesheet" type="text/css" href="style.css">
  </head>
  <body>
  <%--<%
    out.println("Your IP address is " + request.getRemoteAddr());
  %>--%>
  <div>
      <h1>用户登录</h1>
      <form action="/Hello" method="get">
          账号：<input type="text" name="account">
          <br />
          <br/>
          密码：<input type="password" name="password" />
          <br/>
          <br/>
          <input type="button" value="注册" onclick="jump()" />
          <input type="submit" value="提交" />
          <input type="reset" value="重置">
      </form>

      <script>
          function jump()
          {
              window.location.href="register.jsp";
          }
      </script>
  </div>

  </body>
</html>

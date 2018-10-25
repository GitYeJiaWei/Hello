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

  <script type="text/javascript" src="js/jQuery1.9.1.js"></script>
  <script >
      function reload(){
          document.getElementById("image").src="<%=request.getContextPath() %>/Image?date="+new Date().getTime();
          $("#checkcode").val("");   // 将验证码清空
      }

      function verificationcode(){
          var text=$.trim($("#checkcode").val());
          $.get("${pageContext.request.contextPath}/Hello",{op:text},function(data){
              data=parseInt($.trim(data));
              if(data>0){
                  $("#span").text("验证成功!").css("color","green");
              }else{
                  $("#span").text("验证失败!").css("color","red");
                  reload();  //验证失败后需要更换验证码
              }
          });
          $("#checkcode").val(""); // 将验证码清空
      }

      function jump()
      {
          window.location.href="register.jsp";
      }
  </script>

  <body>
  <%--<%
  //html和jsp的区别--静态页面和动态页面的区别
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
          验证码：<input type="text" name="checkcode"  id="checkcode"/>
          <br>
          <br>
          <img  src="<%=request.getContextPath() %>/Image" alt="验证码" id="image" />
          <span id="span"></span>
          <a href="javascript:reload()"><label>换一张</label></a>
          <br>
          <br>
          <input type="button" value="注册" onclick="jump()" />
          <input type="submit" value="提交" />
          <input type="reset" value="重置">
      </form>

  </div>

  </body>
</html>

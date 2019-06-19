<%@ page import="java.util.List" %>
<%@ page import="com.model.User" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/9/7 0007
  Time: 15:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>home</title>
    <%--三个部分的参数也可以写在这里--%>
    <%--<style type="text/css">
        div#container{width:300px;}
        div#header{background:red;width:100px; height:500px;}
        div#menu{background:yellow;width:100px;height:500px;}
        div#footer{background:grey;width:100px;height:500px;}
    </style>--%>
</head>
<script>
    function myFunction()
    {
        var x=document.getElementById("demo"); // 找到元素
        x.innerHTML="你是个大傻逼!重要不";   //改变语句
        x.style.color="#ff0000";          // 改变样式
    }

    function myDown() {
        window.location.assign("down.jsp");
    }

    function myUpload() {
        window.location.assign("upload.jsp");
    }
    /*这个是jsp中的java表达式表示里面的内容是java语句,加等号相当于out.print('request.getAttribute("message")');*/
    <%
        List<User> userList =(ArrayList)request.getAttribute("message");
    %>


</script>
<body>
<div id="container" style="width: 100%;height:100%">

    <div id="header" style="background-color: aqua;">
        <h1 style="margin-bottom:0; text-align: center">主要的网页标题</h1></div>

    <div id="menu" style="background-color: #6495ed;height:80%;width:20%;float:left;">
        <b>菜单</b><br>
        HTML<br>
        CSS<br>
        JavaScript</div>

    <div id="content" style="background-color:#EEEEEE;height:80%;width:80%;float:left;">
        <h1 style="text-align: center">我的所有用户</h1>
        <table border="1" align="center">
            <tr style="border: 1px solid green;">
                <td style="border: 1px solid green;">账号：</td>
                <%
                    if (userList!=null){
                        for (int i=0;i<userList.size();i++){
                %>
                <td style="border: 1px solid green;"><%=userList.get(i).getAccount()%></td>
                <%
                        }
                    }
                %>
            </tr>

            <tr style="border: 1px solid green;">
                <td style="border: 1px solid green;">密码：</td>
                <%
                    if (userList!=null){
                        for (int i=0;i<userList.size();i++){

                %>
                <td style="border: 1px solid green;"><%=userList.get(i).getPassword()%></td>
                <%
                        }
                    }
                %>
            </tr>
        </table>

        <p id="demo" style="text-align: center">
            点击下方按钮获取重要信息。</p>

        <div align="center" >
            <button type="button"  onclick="myFunction()">点我获取重要信息</button>
            <button type="button"  onclick="myDown()">下载文件</button>
            <button type="button"  onclick="myUpload()">上传文件</button>
        </div>
    </div>

    <div id="footer" style="background-color: aqua;clear:both;text-align:center;">
        版权 © yejiawei.com</div>

</div>
</body>
</html>

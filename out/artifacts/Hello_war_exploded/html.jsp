<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/9/6 0006
  Time: 11:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8"/>
    <link rel="stylesheet" type="text/css" href="style.css">
    <%--链接到外部样式表--%>
    <title>JavaWeb</title>
    <base href="http://static.runoob.com/images/" target="_blank">
</head>
<body>
<h1>Hello Tomcat</h1>
<h2>这是一个标题</h2>
<font size="4" color="#7fff00">font过时了,不赞成使用</font>
<h3>这是另一个标题</h3>
<hr/>
<p id="red">第一次使用Tomcat</p>
<%--标题是通过 <h1> - <h6> 标签进行定义的，段落是通过 <p> 标签定义的--%>
<%--<a href>是一个链接，<br>换行,<<img src="" alt="">图片--%>
<hr/><%--<hr/>下划线--%>
<p>这个段落演示<br>了分行效果</p>
<a href="http://www.baidu.com" target="_self">百度一下,设置target打开新窗口</a><br>
<a href="home.jsp" target="_blank">跳转到home</a><br>
<img src="http://static.runoob.com/images/runoob-logo.png" alt="img图片" width="300" height="120"><br>
<img src="runoob-logo.png" alt="img图片" width="300" height="120">base已经设置了默认的URL地址<br>
<img src="http://u.moimg.net/ueditor/image/20150125/1422187989223363.gif" alt="GIF图片" height="120" width="120"><br>
<b>加粗文本</b><strong>加粗文本</strong><br>
<i>斜体文本</i><em>斜体文本</em><br>
<small>这个文本是缩小的</small><big>这个文本是放大的</big><br>
<code>电脑自动输出</code><br>
这是<sub>下标</sub>和<sup>上标</sup><br>
<var>定义变量</var><br>
<pre>
        定义 预格式
        文本  空格
    </pre>

<table border="1">
    <tr>
        <th>head1</th>
        <th>head2</th>
    </tr>
    <tr>
        <td>1,1</td>
        <td>1,2</td>
    </tr>
    <tr>
        <td>2,1</td>
        <td>2,2</td>
    </tr>
</table>
</body>
<ol>
    <li id="sidebar">有序列表</li>
    <li>1234</li>
</ol>
<ul>
    <li>无序列表</li>
    <li>124</li>
</ul>
<form>
    <%--value：提交数据到服务器的值（后台程序PHP使用）
        name：为控件命名，以备后台程序 ASP、PHP 使用
        checked：当设置 checked="checked" 时，该选项被默认选中--%>
    First name:<input type="text" name="firstname"><br>
    PassWord:<input type="password" name="password"><br>
    <input type="radio" name="radio" value="female">单选框female<br>
    <input type="radio" name="radio" value="male">单选框male<br>
    <input type="submit" value="Submit">提交<br>
    <input type="checkbox" name="vehicle" value="Bike">复选框I have a bike<br>
    <input type="checkbox" name="vehicle" value="Car">复选框I have a car<br>
    <input type="reset" value="重置">
</form>
<form name="input" action="index.jsp" method="get">
    Username: <input type="text" name="user">
    <input type="submit" value="Submit">
</form>
<form>
    <select name="cars">
        <option value="volvo">Volvo</option>
        <option value="saab">Saab</option>
        <option value="fiat" selected>Fiat</option>
        <option value="audi">Audi</option>
    </select>
</form>
<textarea rows="10" cols="30">
        我是一个文本框。
    </textarea>
<form action="">
    <input id="demo" type="button" value="Hello world!" onclick="myfunction()">创建一个按钮
</form>
<button type="button" onclick="date()">点击这里</button><br>

<input type="button" value="返回主界面" onclick="jump()" >

<script>
    document.write("插入一 些脚本!")
    function jump()
    {
        window.location.href="home.jsp";
    }

    function myfunction() {
        var x=document.getElementById("demo") // 找到元素
        x.value="Hello JavaScript!"   //改变语句
        x.style.color="#ff0000";          // 改变样式
    }
    function date() {
        var day =new Date().getDay();
        switch (day) {
            case 0:
                alert("Today it's Sunday");
                break;
            case 1:
                alert("Today it's Monday");
                break;
            case 2:
                alert("Today it's Tuesday")
                break;
            case 3:
                alert("Today it's Wednesday")
                break;
            case 4:
                alert("Today it's Thursday")
                break;
            case 5:
                alert("Today it's Friday")
                break;
            case 6:
                alert("Today it's Saturday")
                break;
        }
    }
</script>
</html>

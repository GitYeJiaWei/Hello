<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/9/18 0018
  Time: 17:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>upload</title>
</head>
<body>
    <h1>文件上传</h1>
    <form method="post" action="/Upload" enctype="multipart/form-data">
        选择一个文件:
        <input type="file" name="uploadFile" />
        <br/><br/>
        <input type="submit" value="上传" />
    </form>
</body>
</html>

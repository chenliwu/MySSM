<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/8/27
  Time: 21:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <p>java读取本地图片并在网页显示</p>
    <p>在项目中的action层读取图片流，并输出到客户响应流中。</p>
    <p>在网页中引用该图片地址</p>
    <img src="<%=request.getContextPath()%>/api/files/getImg" alt="" />
</body>
</html>

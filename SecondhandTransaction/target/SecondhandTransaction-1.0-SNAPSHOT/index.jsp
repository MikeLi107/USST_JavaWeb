<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>首页</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<div class="container" style="text-align: center; padding-top: 50px;">
    <h1>欢迎来到二手交易系统</h1>
    <p style="color: #666; margin-bottom: 40px;">简单、快捷的校园/社区二手交易平台</p>

    <c:if test="${not empty sessionScope.currentUser}">
        <div style="margin-bottom: 20px; color: #27ae60;">
            你好，<strong>${sessionScope.currentUser.username}</strong>
        </div>
    </c:if>

    <ul class="nav-list">
        <li><a href="Stuff">🛒 查看二手物品列表</a></li>

        <c:if test="${empty sessionScope.currentUser}">
            <li><a href="register.jsp">👤 注册新账号</a></li>
        </c:if>
    </ul>
</div>
</body>
</html>
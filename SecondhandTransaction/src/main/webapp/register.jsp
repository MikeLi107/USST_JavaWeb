<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>注册</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<div class="container">
    <h2>用户注册</h2>

    <form action="register" method="post">
        <label>用户名</label>
        <input type="text" name="username" placeholder="请输入用户名" required>

        <label>密码</label>
        <input type="password" name="password" placeholder="请输入密码" required>

        <button type="submit" style="width: 100%; margin-top: 10px;">注册账号</button>
    </form>

    <div style="text-align: center; margin-top: 15px;">
        <a href="index.jsp">返回首页</a>
    </div>
</div>
</body>
</html>
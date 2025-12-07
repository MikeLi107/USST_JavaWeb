<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>二手物品交易</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<div class="container">
    <h1>二手物品交易市场</h1>

    <div style="text-align: right; margin-bottom: 20px;">
        <form action="Stuff" method="get" class="inline-form">
            <input type="text" name="keyword" value="${searchKey}" placeholder="搜索物品..." style="width: 200px;">
            <button type="submit">搜索</button>
            <a href="index.jsp" style="margin-left: 10px;">返回首页</a>
        </form>
    </div>

    <hr/>

    <h3>发布新物品</h3>
    <form action="Stuff?action=add" method="post" class="inline-form">
        <label>名称:</label>
        <input type="text" name="title" required placeholder="如：旧电脑" style="flex: 2;"> <label>描述:</label>
        <input type="text" name="description" placeholder="如：9成新..." style="flex: 3;">

        <label>价格:</label>
        <input type="number" step="0.01" name="price" required style="width: 80px;">

        <button type="submit">发布</button>
    </form>

    <hr/>

    <h3>最新商品</h3>
    <table>
        <thead>
        <tr>
            <th style="width: 50px;">ID</th>
            <th style="width: 150px;">名称</th>
            <th>描述</th>
            <th style="width: 100px;">价格</th>
            <th style="width: 80px;">操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${list}" var="i">
            <tr>
                <td>${i.id}</td>
                <td style="font-weight: bold;">${i.title}</td>
                <td style="color: #666;">${i.description}</td>
                <td style="color: #e74c3c; font-weight: bold;">¥${i.price}</td>
                <td>
                    <a href="Stuff?action=delete&id=${i.id}"
                       onclick="return confirm('确定删除吗？')"
                       style="color: #e74c3c; font-size: 0.9em;">删除</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <c:if test="${empty list}">
        <div style="text-align: center; padding: 40px; color: #999;">
            暂无相关商品信息
        </div>
    </c:if>
</div>
</body>
</html>
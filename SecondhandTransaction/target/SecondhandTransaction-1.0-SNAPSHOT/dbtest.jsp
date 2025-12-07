<%@ page import="java.sql.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head><title>数据库连接测试诊断</title></head>
<body>
<h2>数据库连接测试结果：</h2>
<pre>
<%
    // 这里填你 DBtool.java 里一模一样的配置
    String URL = "jdbc:mysql://localhost:3306/secondhand_db?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC&characterEncoding=utf-8";
    String USER = "root";
    String PASSWORD = "@Mqs419@"; // <--- 必须修改这里！！！

    Connection conn = null;
    try {
        // 1. 测试驱动加载
        Class.forName("com.mysql.cj.jdbc.Driver");
        out.println("1. 驱动加载成功！");

        // 2. 测试连接
        conn = DriverManager.getConnection(URL, USER, PASSWORD);
        out.println("2. 数据库连接成功！对象：" + conn);

        // 3. 测试插入数据
        String sql = "INSERT INTO stuffs(title, description, price) VALUES('测试数据', '测试连接是否可写', 1.00)";
        PreparedStatement ps = conn.prepareStatement(sql);
        int rows = ps.executeUpdate();
        out.println("3. 写入测试成功！影响行数：" + rows);

        // 4. 提交（保险起见）
        // conn.commit(); // 默认是自动提交，如果服务器配置特殊可能需要这就句

    } catch (Exception e) {
        // 如果出错，把红色报错信息直接打印在网页上
        out.println("！！！连接失败！！！");
        out.println("错误类型：" + e.getClass().getName());
        out.println("错误信息：" + e.getMessage());
        e.printStackTrace(new java.io.PrintWriter(out));
    } finally {
        if(conn != null) conn.close();
    }
%>
</pre>
</body>
</html>
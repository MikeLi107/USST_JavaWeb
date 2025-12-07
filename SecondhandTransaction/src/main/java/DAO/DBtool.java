package DAO;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBtool {
    // 根据你的实际数据库修改账号密码
// 注意：allowPublicKeyRetrieval=true 是解决你当前报错的关键
    private static final String URL = "jdbc:mysql://localhost:3306/secondhand_db?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC&characterEncoding=utf-8";
    private static final String USER = "root";
    private static final String PWD = "@Mqs419@";

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PWD);
    }
}

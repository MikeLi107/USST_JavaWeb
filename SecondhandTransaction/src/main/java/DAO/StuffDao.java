package DAO;


import Model.Stuff;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StuffDao {

    // 基础查询方法，供 selectAll 和 selectByKeyword 复用
    private List<Stuff> queryStuffs(String sql, String param) {
        List<Stuff> list = new ArrayList<>();
        try (Connection conn = DBtool.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            if (param != null) {
                ps.setString(1, param);
                ps.setString(2, param);
            }

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    // JDBC 只有手动封装，没有自动映射
                    Stuff Stuff = new Stuff(
                            rs.getInt("id"),
                            rs.getString("title"),
                            rs.getString("description"),
                            rs.getBigDecimal("price")
                    );
                    list.add(Stuff);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Stuff> selectAll() {
        return queryStuffs("SELECT * FROM stuffs", null);
    }

    public List<Stuff> selectByKeyword(String keyword) {
        // SQL 模糊匹配
        String sql = "SELECT * FROM stuffs WHERE title LIKE ? OR description LIKE ?";
        return queryStuffs(sql, "%" + keyword + "%");
    }

    public void addStuff(Stuff Stuff) {
        String sql = "INSERT INTO stuffs(title, description, price) VALUES(?, ?, ?)";
        try (Connection conn = DBtool.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, Stuff.getTitle());
            ps.setString(2, Stuff.getDescription());
            ps.setBigDecimal(3, Stuff.getPrice());
            ps.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }

    public void deleteStuff(int id) {
        try (Connection conn = DBtool.getConnection();
             PreparedStatement ps = conn.prepareStatement("DELETE FROM stuffs WHERE id = ?")) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }
}

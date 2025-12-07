package Controller;

import Model.Stuff;
import Service.StuffService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@WebServlet("/Stuff")
public class StuffServlet extends HttpServlet {
    private StuffService StuffService = new StuffService();

    // 统一处理 POST 和 GET
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String action = req.getParameter("action");

        if ("add".equals(action)) {
            // 处理发布
            String title = req.getParameter("title");
            String desc = req.getParameter("description");
            BigDecimal price = new BigDecimal(req.getParameter("price"));

            Stuff Stuff = new Stuff(null, title, desc, price);
            StuffService.publish(Stuff);
            resp.sendRedirect("Stuff"); // 刷新列表

        } else if ("delete".equals(action)) {
            // 处理删除
            int id = Integer.parseInt(req.getParameter("id"));
            StuffService.remove(id);
            resp.sendRedirect("Stuff");

        } else {
            // 默认处理查询（支持模糊搜索）
            String keyword = req.getParameter("keyword");

            // 调用 Service 获取数据
            List<Stuff> Stuffs = StuffService.search(keyword);

            // 存入 Request 转发给 JSP
            req.setAttribute("list", Stuffs);
            req.setAttribute("searchKey", keyword); // 回显搜索词
            req.getRequestDispatcher("/list.jsp").forward(req, resp);
        }
    }
}
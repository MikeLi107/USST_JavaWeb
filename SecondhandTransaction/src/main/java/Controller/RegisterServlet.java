package Controller;

import Model.User;
import Service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    private UserService userService = new UserService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String u = req.getParameter("username");
        String p = req.getParameter("password");

        // 使用 Lombok 的全参构造
        User user = new User(null, u, p);

        userService.register(user);

        // 1. 把用户对象存入 request 作用域
        req.setAttribute("currentUser", user);
        req.setAttribute("msg", "注册成功，欢迎加入！"); // 顺便带个提示信息

// 2. 必须使用 forward (转发) 才能保留 request 数据
// 注意：转发到的页面必须是 JSP，不能是 Servlet URL
        req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }
}

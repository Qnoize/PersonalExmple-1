package servlet;

import model.User;
import service.UserService;
import service.UserServiceImpl;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/edit")
public class EditServlet extends HttpServlet {
    private UserService userService;

    @Override
    public void init() { this.userService = UserServiceImpl.getInstance(); }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long userId = Long.parseLong(req.getParameter("userId"));
        String edit = req.getParameter("edit");
        if (edit != null && userId != null) {
            User user = userService.getUserById(userId);
            req.setAttribute("user", user);
        }
        RequestDispatcher dispatcher = req.getServletContext().getRequestDispatcher("/jsp/adminEditUser.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long userId = Long.parseLong(req.getParameter("userId"));
        String name = req.getParameter("login");
        String pass = req.getParameter("password");
        String email = req.getParameter("email");
        User user = new User(userId, name, pass, email);
        userService.userEdit(user);
        resp.sendRedirect(req.getContextPath() + "/admin");
    }
}

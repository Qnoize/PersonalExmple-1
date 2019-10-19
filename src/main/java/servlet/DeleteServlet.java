package servlet;

import service.UserService;
import service.UserServiceImpl;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/delete")
public class DeleteServlet extends HttpServlet {
    private UserService userService;
    @Override
    public void init() { this.userService = UserServiceImpl.getInstance(); }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long userId = Long.parseLong(req.getParameter("userId"));
        String delete = req.getParameter("delete");
        if (delete != null && userId != null) {
            userService.deleteUser(userId);
        }
        resp.sendRedirect("/admin");
    }
}

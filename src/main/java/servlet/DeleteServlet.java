package servlet;

import service.UserService;
import service.UserServiceImpl;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/delete")
public class DeleteServlet extends HttpServlet {
    private UserService userService;
    @Override
    public void init() { this.userService = UserServiceImpl.getInstance(); }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getServletContext().getRequestDispatcher("/jsp/adminMainPage.jsp");
        Long user_id = null;
        try {
            user_id = Long.parseLong(req.getParameter("user_id"));
        } catch (NumberFormatException e){
            dispatcher.forward(req, resp);
        }
        String delete = req.getParameter("delete");
        if (delete != null && user_id != null) {
            userService.deleteUser(user_id);
        }
        resp.sendRedirect("/admin");
    }
}

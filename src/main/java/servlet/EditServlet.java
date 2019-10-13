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

@WebServlet("/edit")
public class EditServlet extends HttpServlet {
    private UserService userService;

    @Override
    public void init() throws ServletException { this.userService = UserServiceImpl.getInstance(); }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=windows-1251");
        req.setCharacterEncoding("CP1251");

        Long id = null;
        try {
            id = Long.parseLong(req.getParameter("id"));
        } catch (NumberFormatException e){
            RequestDispatcher dispatcher = req.getServletContext().getRequestDispatcher("/jsp/editUser.jsp");
            dispatcher.forward(req, resp);
        }
        String edit = req.getParameter("edit");
        if (edit != null && id != null) {
            User user = userService.getUserById(id);
            req.setAttribute("user", user);
        }
        RequestDispatcher dispatcher = req.getServletContext().getRequestDispatcher("/jsp/editUser.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=windows-1251");
        req.setCharacterEncoding("CP1251");

        Long id = null;
        try {
            id = Long.parseLong(req.getParameter("id"));
        } catch (NumberFormatException e){
            RequestDispatcher dispatcher = req.getServletContext().getRequestDispatcher("/jsp/editUser.jsp");
            dispatcher.forward(req, resp);
        }
        String name = req.getParameter("login");
        String pass = req.getParameter("password");
        String email = req.getParameter("email");
        User user = new User(id, name, pass, email);
        userService.userEdit(user);
        resp.sendRedirect("/admin");
    }
}

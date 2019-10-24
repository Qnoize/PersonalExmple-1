package servlet;

import service.UserService;
import service.UserServiceImpl;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/")
public class LoginServlet extends HttpServlet {
    private UserService userService;

    @Override
    public void init() {this.userService = UserServiceImpl.getInstance();}

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getServletContext().getRequestDispatcher("/jsp/loginUser.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("login");
        String password = req.getParameter("password");
        HttpSession session = req.getSession();
        session.setAttribute("login", name);

        String role = "user";
        if(userService.getByName(name).getRole().toString().contains("admin")){
            role = "admin";
        }

        session.setAttribute("role", role);
        if(!role.equals("admin")) {
            resp.sendRedirect(req.getContextPath() + "/userHome");
        } else {
            resp.sendRedirect(req.getContextPath() + "/admin");
        }
    }
}


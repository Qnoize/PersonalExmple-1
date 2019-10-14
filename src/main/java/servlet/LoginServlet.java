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
        resp.setContentType("text/html; charset=windows-1251");
        req.setCharacterEncoding("CP1251");

        String name = req.getParameter("login");
        String password = req.getParameter("password");

        if(req.getParameter("register") != null){
            req.getServletContext().getRequestDispatcher("/jsp/registerUser.jsp").forward(req, resp);
        }

        if (userService.userExist(name, password)) {
            HttpSession session = req.getSession();
            session.setAttribute("login", name);
            session.setAttribute("role", userService.getUserRole(name).getRole());
            if(!userService.getUserRole(name).getRole().equals("admin")) {
                req.getServletContext().getRequestDispatcher("/jsp/userHome.jsp").forward(req, resp);
            } else {
                req.getServletContext().getRequestDispatcher("/admin").forward(req, resp);
            }
        } else {
            resp.sendRedirect(req.getContextPath() + "/");
        }
    }
}


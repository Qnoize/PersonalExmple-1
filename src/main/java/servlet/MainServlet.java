package servlet;

import service.UserService;
import service.UserServiceImpl;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/")
public class MainServlet extends HttpServlet {
    private UserService userService;

    @Override
    public void init() throws ServletException {this.userService = UserServiceImpl.getInstance();}

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getServletContext().getRequestDispatcher("/jsp/loginUser.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        String name = req.getParameter("login");
        String password = req.getParameter("password");
        if(req.getParameter("register") != null){
            req.getServletContext().getRequestDispatcher("/jsp/registerUser.jsp").forward(req, resp);
        }

        if (userService.userExist(name, password)) {
            HttpSession session = req.getSession();
            session.setAttribute("login", name);
            req.getServletContext().getRequestDispatcher("/jsp/userHome.jsp").forward(req, resp);
        } else {
            resp.sendRedirect(req.getContextPath() + "/");
        }
    }
}


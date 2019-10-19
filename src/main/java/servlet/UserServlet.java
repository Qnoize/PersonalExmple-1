package servlet;

import service.UserService;
import service.UserServiceImpl;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/userHome")
public class UserServlet extends HttpServlet {
    private UserService userService;

    @Override
    public void init() {this.userService = UserServiceImpl.getInstance();}

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        req.setAttribute("userLogin", session.getAttribute("login"));
        req.setAttribute("userRole", session.getAttribute("role"));
        req.getServletContext().getRequestDispatcher("/jsp/userHome.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getParameter("login") != null){
            resp.sendRedirect(req.getContextPath() + "/");
        }
    }
}

package servlet;

import service.UserService;
import service.UserServiceImpl;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/userHome")
public class UserHomeServlet extends HttpServlet {
    private UserService userService;

    @Override
    public void init() throws ServletException {this.userService = UserServiceImpl.getInstance();}

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        req.getServletContext().getRequestDispatcher("/jsp/userHome.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        if(req.getParameter("login") != null){
            req.getServletContext().getRequestDispatcher("/jsp/loginUser.jsp").forward(req, resp);
        }
    }
}

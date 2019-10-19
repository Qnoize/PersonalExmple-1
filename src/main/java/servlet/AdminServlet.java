package servlet;

import DAO.UserDaoFactory;
import model.User;
import service.UserService;
import service.UserServiceImpl;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/admin")
public class AdminServlet extends HttpServlet {
    private UserService userService;

    @Override
    public void init() {this.userService = UserServiceImpl.getInstance();}

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("with", UserDaoFactory.daoType);
        List<User> users = userService.getAllUsers();
        req.setAttribute("list", users);
        HttpSession session = req.getSession(false);
        req.setAttribute("userRole", session.getAttribute("role"));
        RequestDispatcher dispatcher = req.getServletContext().getRequestDispatcher("/jsp/adminMainPage.jsp");
        dispatcher.forward(req, resp);
        req.getRequestDispatcher("/jsp/adminMainPage.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("login");
        String pass = req.getParameter("password");
        String email = req.getParameter("email");
        if (!name.isEmpty()&& !pass.isEmpty()) {
            User user = new User(name, pass, email);
            userService.addUser(user);
        }
        resp.sendRedirect(req.getContextPath() + "/");
    }
}

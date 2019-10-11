package servlet;

import DAO.UserDaoFactory;
import model.User;
import service.UserService;
import service.UserServiceImpl;
import util.ReadProperties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/")
public class MainServlet extends HttpServlet {
    private UserService userService;

    @Override
    public void init() throws ServletException {this.userService = UserServiceImpl.getInstance();}

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("with", UserDaoFactory.daoType);
        List<User> users = userService.getAllUsers();
        req.setAttribute("list", users);
        RequestDispatcher dispatcher = req.getServletContext().getRequestDispatcher("/jsp/signUp.jsp");
        dispatcher.forward(req, resp);
        resp.setContentType("text/html");
        List<User> list = userService.getAllUsers();
        req.setAttribute("list", list);
        req.getRequestDispatcher("/jsp/signUp.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String pass = req.getParameter("password");
        String email = req.getParameter("email");
        if (!name.equals("")&& !pass.equals("")) {
            User user = new User(name, pass, email);
            userService.addUser(user);
            doGet(req, resp);
        }
        doGet(req, resp);
    }
}

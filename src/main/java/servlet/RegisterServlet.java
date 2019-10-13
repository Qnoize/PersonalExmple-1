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
import java.util.List;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    private UserService userService;

    @Override
    public void init() throws ServletException {this.userService = UserServiceImpl.getInstance();}

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> users = userService.getAllUsers();
        resp.setContentType("text/html");
        req.setAttribute("list", users);
        RequestDispatcher dispatcher = req.getServletContext().getRequestDispatcher("/jsp/registerUser.jsp");
        dispatcher.forward(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        String name = req.getParameter("login");
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

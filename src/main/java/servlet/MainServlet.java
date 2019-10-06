package servlet;

import exception.DBException;
import model.User;
import service.UserRepository;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/")
public class MainServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserRepository userRepository = UserRepository.getInstance();
        List<User> users = userRepository.getAllUsers();
        req.setAttribute("list", users);
        RequestDispatcher dispatcher = req.getServletContext().getRequestDispatcher("/jsp/signUp.jsp");
        dispatcher.forward(req, resp);

        resp.setContentType("text/html");
        Long id = null;
        try {
            id = Long.parseLong(req.getParameter("id"));
        } catch (Exception e){
            resp.sendRedirect("http://localhost:8080/");
        }
        String delete = req.getParameter("delete");

        if (delete != null && id != null) {
            User user = null;
            try {
                user = userRepository.getUserById(id);
            } catch (DBException e) {
                e.printStackTrace();
            }
            if (user != null) {
                try {
                    userRepository.deleteUser(id);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        String edit = req.getParameter("edit");

        if (edit != null && id != null) {
            User user = null;
            try {
                user = userRepository.getUserById(id);
                req.setAttribute("user", user);
            } catch (DBException e) {
                e.printStackTrace();
            }
        }

        List<User> list = userRepository.getAllUsers();

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
            UserRepository userRepository = UserRepository.getInstance();
            try {
                if (!userRepository.userExist(name)) {
                    try {
                        userRepository.addUser(user);
                    } catch (SQLException e) {
                        doGet(req, resp);
                    }
                    doGet(req, resp);
                } else {
                    doGet(req, resp);
                }
            } catch (SQLException e) {
                doGet(req, resp);
            }
        }
        doGet(req, resp);
    }
}

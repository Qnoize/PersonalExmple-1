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
        UserRepository userRepository = new UserRepository();
        List<User> users = userRepository.getAllUsers();
        req.setAttribute("listU", users);
        RequestDispatcher dispatcher = req.getServletContext().getRequestDispatcher("/jsp/signUp.jsp");
        dispatcher.forward(req, resp);
        long id = Long.parseLong(req.getParameter("id"));
        String delete = req.getParameter("delete");
        if (delete != null){
            try {
                userRepository.deleteUser(id);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        String edit = req.getParameter("edit");
        if(edit != null){
            try {
                User user = userRepository.getUserById(id);
                req.setAttribute("listE", user);
                userRepository.userEdit(user);
                }
                 catch (DBException e) {
                    e.printStackTrace();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
         }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String pass = req.getParameter("password");
        String email = req.getParameter("email");

        if (!name.equals("")&& !pass.equals("") && !email.equals("")) {
            User user = new User(name, pass, email);
            UserRepository userRepository = new UserRepository();
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
        } else {
            doGet(req, resp);
        }
    }
}

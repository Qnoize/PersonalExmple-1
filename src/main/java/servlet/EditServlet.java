package servlet;

import exception.DBException;
import model.User;
import service.UserRepository;

import javax.jws.WebService;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/edit")
public class EditServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserRepository userRepository = UserRepository.getInstance();
        Long id = null;
        try {
            id = Long.parseLong(req.getParameter("id"));
        } catch (Exception e){
            resp.sendRedirect("http://localhost:8080/");
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
        RequestDispatcher dispatcher = req.getServletContext().getRequestDispatcher("/jsp/editUser.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = null;
        try {
            id = Long.parseLong(req.getParameter("id"));
        } catch (Exception e){
            resp.sendRedirect("http://localhost:8080/");
        }
        String name = req.getParameter("name");
        String pass = req.getParameter("password");
        String email = req.getParameter("email");

        User user = new User(id, name, pass, email);
        UserRepository userRepository = UserRepository.getInstance();
        try {
            userRepository.userEdit(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        resp.sendRedirect("http://localhost:8080/");
    }
}

package servlet;

import DAO.DAO;
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
    private DAO dao;
    @Override
    public void init() throws ServletException {
        this.dao = new UserRepository();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> users = null;
        try {
            users = dao.getAllUsers();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        req.setAttribute("list", users);
        RequestDispatcher dispatcher = req.getServletContext().getRequestDispatcher("/jsp/signUp.jsp");
        dispatcher.forward(req, resp);

        resp.setContentType("text/html");
        Long id = null;
        try {
            id = Long.parseLong(req.getParameter("id"));
        } catch (NumberFormatException e){
            dispatcher.forward(req, resp);
        }
        String delete = req.getParameter("delete");

        if (delete != null && id != null) {
            User user = null;
            try {
                user = dao.getUserById(id);
            } catch (DBException | SQLException e) {
                e.printStackTrace();
            }
            if (user != null) {
                try {
                    dao.deleteUser(id);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        String edit = req.getParameter("edit");

        if (edit != null && id != null) {
            User user = null;
            try {
                user = dao.getUserById(id);
                req.setAttribute("user", user);
            } catch (DBException | SQLException e) {
                e.printStackTrace();
            }
        }

        List<User> list = null;
        try {
            list = dao.getAllUsers();
        } catch (SQLException e) {
            e.printStackTrace();
        }

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
            try {
                if (!dao.userExist(name)) {
                    try {
                        dao.addUser(user);
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

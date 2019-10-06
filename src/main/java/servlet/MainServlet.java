package servlet;

import DAO.DAOService;
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

    private DAOService dao;
    @Override
    public void init() throws ServletException {
        this.dao = new UserRepository();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> users = dao.getAllUsers();
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
            user = dao.getUserById(id);
            if (user != null) {
                dao.deleteUser(id);
            }
        }
        String edit = req.getParameter("edit");
        if (edit != null && id != null) {
            User user = null;
            user = dao.getUserById(id);
            req.setAttribute("user", user);
        }
        List<User> list = dao.getAllUsers();
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
            if (!dao.userExist(name)) {
                dao.addUser(user);
                doGet(req, resp);
            } else {
                doGet(req, resp);
            }
        }
        doGet(req, resp);
    }
}

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

@WebServlet("/edit")
public class EditServlet extends HttpServlet {
    private DAO dao;
    @Override
    public void init() throws ServletException {
        this.dao = new UserRepository();
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = null;
        try {
            id = Long.parseLong(req.getParameter("id"));
        } catch (NumberFormatException e){
            RequestDispatcher dispatcher = req.getServletContext().getRequestDispatcher("/jsp/editUser.jsp");
            dispatcher.forward(req, resp);
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
        RequestDispatcher dispatcher = req.getServletContext().getRequestDispatcher("/jsp/editUser.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = null;
        try {
            id = Long.parseLong(req.getParameter("id"));
        } catch (NumberFormatException e){
            RequestDispatcher dispatcher = req.getServletContext().getRequestDispatcher("/jsp/editUser.jsp");
            dispatcher.forward(req, resp);
        }
        String name = req.getParameter("name");
        String pass = req.getParameter("password");
        String email = req.getParameter("email");

        User user = new User(id, name, pass, email);
        try {
            dao.userEdit(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        resp.sendRedirect("http://localhost:8080/");
    }
}

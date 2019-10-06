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

@WebServlet("/edit")
public class EditServlet extends HttpServlet {
    private DAOService dao;
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
            user = dao.getUserById(id);
            req.setAttribute("user", user);
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
        dao.userEdit(user);
        resp.sendRedirect("http://localhost:8080/");
    }
}

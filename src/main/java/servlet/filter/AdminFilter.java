package servlet.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/admin/*")
public class AdminFilter implements Filter{

    @Override
    public void init(FilterConfig filterConfig) { }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        response.setContentType("text/html; charset=windows-1251");
        request.setCharacterEncoding("CP1251");

        try {
            HttpSession session = request.getSession(false);
            if (session != null && session.getAttribute("role").equals("admin")) {
                chain.doFilter(request, response);
            } else {
                response.sendRedirect(request.getContextPath() + "/");
            }
            return;
        } catch (Exception e){
            e.printStackTrace();
            RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/jsp/loginUser.jsp");
            dispatcher.forward(request, response);
        }
    }
    @Override
    public void destroy() { }
}
package servlet.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter ("/edit")
public class EditFilter implements Filter{

    @Override
    public void init(FilterConfig filterConfig) throws ServletException { }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        try {
            HttpSession session = request.getSession(false);
            if (session == null || !session.getAttribute("role").equals("admin")) {
                servletRequest.getServletContext().getRequestDispatcher("/").forward(request, response);
            }
            chain.doFilter(request, response);
        } catch (NullPointerException e){
            servletRequest.getServletContext().getRequestDispatcher("/").forward(request, response);
        }
    }

    @Override
    public void destroy() { }
}
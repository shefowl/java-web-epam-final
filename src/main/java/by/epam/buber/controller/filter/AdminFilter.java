package by.epam.buber.controller.filter;

import by.epam.buber.entity.participant.Role;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/admin")
public class AdminFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession(false);
        String userURI = request.getContextPath() + "/hello?action=main";

        if(session.getAttribute("userRole") == Role.ADMIN) {
            filterChain.doFilter(request, response);
        }
        else{
            response.sendRedirect(userURI);
        }
    }

    @Override
    public void destroy() {

    }
}

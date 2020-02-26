package by.epam.buber.controller.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/*")
public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession(false);
        String signUp = request.getContextPath() + "/hello?action=sign_Up";
        String signIn = request.getContextPath() + "/hello?action=sign_In";
        String main = request.getContextPath() + "/hello?action=main";
        String index = request.getContextPath() + "/";
        String hello = request.getContextPath() + "/hello";
        String css = "/css";
        String img = "/img";
        String js = "/js";

        boolean loggedIn = session != null && session.getAttribute("userName") != null
                && session.getAttribute("userRole") != null;
        boolean signInRequest = request.getRequestURI().equals(signIn);
        boolean mainRequest = request.getRequestURI().equals(main);
        boolean signUpRequest = request.getRequestURI().equals(signUp);
        boolean helloRequest = request.getRequestURI().equals(hello);
        boolean resourcesRequest = request.getRequestURI().contains(css) || request.getRequestURI().contains(img) ||
                request.getRequestURI().contains(js);

        if (loggedIn || mainRequest || request.getRequestURI().equals(index)|| signInRequest || signUpRequest ||
                helloRequest || resourcesRequest) {
            filterChain.doFilter(request, response);
        } else {
            response.sendRedirect(signIn);
        }
    }

    @Override
    public void destroy() {
    }
}


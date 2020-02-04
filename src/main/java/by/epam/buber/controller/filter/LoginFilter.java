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
        //URL Запроса/переадресации на Servlet входа
        String loginURI = request.getContextPath() + "/hello?action=signIn";
        String mainURI = request.getContextPath() + "/hello?action=main";
        String indexURI = request.getContextPath() + "/";
        String helloURI = request.getContextPath() + "/hello";
        //Если сессия ранее создана
        boolean loggedIn = session != null && session.getAttribute("userName") != null
                && session.getAttribute("userRole") != null;
        boolean loginRequest = request.getRequestURI().equals(helloURI);
        boolean mainRequest = request.getRequestURI().equals(helloURI);
        String s = request.getRequestURI();
        if(request.getParameter("action") != null) {
            loginRequest = loginRequest && (request.getParameter("action").equals("signUp") ||
                    request.getParameter("action").equals("signIn") ||
                    request.getParameter("action").equals("registration"));
        }

        //Если запрос пришел со страницы с входом или сессия не пуста даем добро следовать дальше
        //Если нет ридерект на страницу входа
        if (loggedIn || loginRequest || request.getRequestURI().equals(indexURI)|| mainRequest) {
            filterChain.doFilter(request, response);
        } else {
            response.sendRedirect(loginURI);
        }
    }

    @Override
    public void destroy() {
    }
}


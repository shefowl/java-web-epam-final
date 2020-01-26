package by.epam.buber.servlet;

import by.epam.buber.entity.Order;
import by.epam.buber.entity.User;
import by.epam.buber.service.UserService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/hello")
public class UserController extends HttpServlet {

    private Order order;
    private UserService userService = new UserService();

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init();
        order = new Order(0, 0, "NONE");
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        String action = request.getParameter("action");
        request.setAttribute("order", order);
        switch (action == null ? "info" : action) {
            case "save":
                request.getRequestDispatcher("/app?action=save").forward(request, response);
                break;
            case "login":
                request.getRequestDispatcher("/login.jsp").forward(request, response);
                break;
            case "userPage":
                request.getRequestDispatcher("/userPage.jsp").forward(request, response);
                break;
            case "registration":
                request.getRequestDispatcher("/registration.jsp").forward(request, response);
                break;
            case "logout":
                session.invalidate();
                request.getRequestDispatcher("/index.jsp").forward(request, response);
                break;
            case "orders":
            case "info":
            default:
                request.getRequestDispatcher("/main.jsp").forward(request, response);
                break;
        }
    }

    @Override
    protected void doPost (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");

        if(action.equals("signUp")){
            userService.signUp(request.getParameter("name"),request.getParameter("password"));
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }

        if(action.equals("login")) {
            User user = userService.login(request.getParameter("name"),request.getParameter("password"));
            session.setAttribute("userName", user.getName());
            session.setAttribute("userRole", user.getRole());
            session.setAttribute("userId", user.getId());
            request.setAttribute("user", user);
            request.getRequestDispatcher("/userPage.jsp").forward(request, response);
        }

    }

}

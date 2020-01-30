package by.epam.buber.servlet;

import by.epam.buber.entity.Order;
import by.epam.buber.entity.participant.Role;
import by.epam.buber.entity.participant.TaxiParticipant;
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
            userService.signUp(request.getParameter("name"),request.getParameter("password"),
                    request.getParameter("email"), request.getParameter("phone"));
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }

        if(action.equals("login")) {
            TaxiParticipant taxiParticipant = userService.login(request.getParameter("name"),request.getParameter("password"));
            session.setAttribute("userName", taxiParticipant.getName());
            session.setAttribute("userRole", taxiParticipant.getRole());
            session.setAttribute("userId", taxiParticipant.getId());
            request.setAttribute("taxiParticipant", taxiParticipant);
            if(taxiParticipant.getRole() == Role.USER) {
                request.getRequestDispatcher("/userPage.jsp").forward(request, response);
            }
            else {
                request.getRequestDispatcher("/driverPage.jsp").forward(request, response);
            }
        }

    }

}

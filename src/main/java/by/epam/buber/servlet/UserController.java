package by.epam.buber.servlet;

import by.epam.buber.entity.Order;
import by.epam.buber.entity.participant.Role;
import by.epam.buber.entity.participant.TaxiParticipant;
import by.epam.buber.service.UserService;
import by.epam.buber.service.impl.UserServiceImpl;

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

    private UserService userService = new UserServiceImpl();

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        String action = request.getParameter("action");
        switch (action == null ? "info" : action) {
            case "save":
                request.getRequestDispatcher("/app?action=save").forward(request, response);
                break;
            case "signIn":
                request.getRequestDispatcher("/signIn.jsp").forward(request, response);
                break;
            case "userPage":
                request.getRequestDispatcher("/userPage.jsp").forward(request, response);
                break;
            case "registration":
                request.getRequestDispatcher("/registration.jsp").forward(request, response);
                break;
            case "logout":
                if(session.getAttribute("userRole") == Role.DRIVER){
                    userService.setDriverUnactive((Integer) session.getAttribute("userId"));
                }
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
            request.getRequestDispatcher("/signIn.jsp").forward(request, response);
        }

        if(action.equals("signIn")) {
            TaxiParticipant taxiParticipant = userService.signIn(request.getParameter("name"),request.getParameter("password"));
            session.setAttribute("userName", taxiParticipant.getName());
            session.setAttribute("userRole", taxiParticipant.getRole());
            session.setAttribute("userId", taxiParticipant.getId());
            request.setAttribute("taxiParticipant", taxiParticipant);
            if(taxiParticipant.getRole() == Role.USER) {
                request.getRequestDispatcher("/userPage.jsp").forward(request, response);
            }
            else {
                if(taxiParticipant.getRole() == Role.ADMIN) {
                    request.getRequestDispatcher("/adminPage.jsp").forward(request, response);
                }
                else {
                    request.getRequestDispatcher("/driverPage.jsp").forward(request, response);
                }
            }
        }

    }

}

package by.epam.buber.servlet;

import by.epam.buber.entity.Car;
import by.epam.buber.entity.CarClass;
import by.epam.buber.entity.participant.Driver;
import by.epam.buber.entity.participant.Role;
import by.epam.buber.entity.participant.TaxiParticipant;
import by.epam.buber.service.AdminService;
import by.epam.buber.service.UserService;
import by.epam.buber.service.impl.AdminServiceImpl;
import by.epam.buber.service.impl.UserServiceImpl;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/admin")
public class AdminController extends HttpServlet {
    private UserService userService = new UserServiceImpl();
    private AdminService adminService = new AdminServiceImpl();

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
            case "getAll":
                request.setAttribute("participants", adminService.getAllParticipants());
                request.getRequestDispatcher("/adminParticipants.jsp").forward(request, response);
                break;
            case "getAllByName":
                request.getRequestDispatcher("/adminEnterName.jsp").forward(request, response);
                break;
            case "participantId":
                request.getRequestDispatcher("/adminEnterId.jsp").forward(request, response);
                break;
            case "adminPage":
                request.getRequestDispatcher("/adminPage.jsp").forward(request, response);
                break;
            case "discount":
                request.setAttribute("participants", adminService.getUsersForDiscount());
                request.getRequestDispatcher("/adminDiscount.jsp").forward(request, response);
                break;
            case "driver":
                request.getRequestDispatcher("/adminNewDriver.jsp").forward(request, response);
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
        if(action.equals("ban")) {
            if(Boolean.getBoolean(request.getParameter("isBanned"))){
                adminService.unban((Integer.valueOf(request.getParameter("participant"))));
            }
            else {
                adminService.ban((Integer.valueOf(request.getParameter("participant"))));
            }
        }

        if(action.equals("findByName")) {
            request.setAttribute("participants", adminService.getUsersByName(request.getParameter("name")));
            request.getRequestDispatcher("/adminFoundByName.jsp").forward(request, response);
        }

        if(action.equals("findById")) {
            List<TaxiParticipant> participants = new ArrayList<>();
            participants.add(adminService.getParticipantById(Integer.valueOf(request.getParameter("id"))));
            request.setAttribute("participants", participants);
            request.getRequestDispatcher("/adminFoundByName.jsp").forward(request, response);
        }

        if(action.equals("discount")) {
            adminService.setDiscount(Integer.valueOf(request.getParameter("participant")), 10);
            request.getRequestDispatcher("/adminPage.jsp").forward(request, response);
        }

        if(action.equals("driver")) {
            Driver driver = new Driver();
            driver.setName(request.getParameter("name"));
            driver.setPassword(request.getParameter("password"));
            driver.setEmail(request.getParameter("email"));
            driver.setPhoneNumber(request.getParameter("phone"));
            double d = Double.valueOf(request.getParameter("price"));
            driver.setPricePerKm(BigDecimal.valueOf(d));
            Car car = new Car();

            String s =request.getParameter("carClass").toUpperCase();
            car.setCarClass(CarClass.valueOf(request.getParameter("carClass").toUpperCase()));
            car.setMark(request.getParameter("mark"));
            car.setModel(request.getParameter("model"));
            driver.setCar(car);
            adminService.registrateDriver(driver);
            request.getRequestDispatcher("/adminPage.jsp").forward(request, response);
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
                request.getRequestDispatcher("/driverPage.jsp").forward(request, response);
            }
        }

    }
}

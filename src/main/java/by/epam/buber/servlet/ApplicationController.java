package by.epam.buber.servlet;

import by.epam.buber.entity.Order;
import by.epam.buber.entity.participant.Driver;
import by.epam.buber.service.UserService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@WebServlet("/app")
public class ApplicationController extends HttpServlet {

    private UserService userService = new UserService();


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
            case "drivers":
                Order order = userService.takeCurrentOrder((Integer) session.getAttribute("userId"));
                List<Driver> drivers = userService.showAbleDrivers(order.getCoordinates(), order.getCarClass());
                request.setAttribute("currentOrder", order);
                request.setAttribute("drivers", drivers);
                request.setAttribute("prices", userService.calculateOrderPricePerDriver(drivers, order));
                request.setAttribute("driverRequested", userService.driverRequested(order.getId()));
                request.getRequestDispatcher("/ableDrivers.jsp").forward(request, response);
                break;
            case "users":
                request.getRequestDispatcher("/userList.jsp").forward(request, response);
                break;
            case "userPage":
                request.getRequestDispatcher("/userPage.jsp").forward(request, response);
                break;
            case "changeName":
                request.getRequestDispatcher("/account.jsp").forward(request, response);
                break;
            case "changePassword":
                request.getRequestDispatcher("/password.jsp").forward(request, response);
                break;
            case "newOrder":
                boolean ordered = userService.orderMade((Integer) session.getAttribute("userId"));
                request.setAttribute("ordered", ordered);
                request.getRequestDispatcher("/newOrder.jsp").forward(request, response);
                break;
            case "orders":
                request.getRequestDispatcher("/orders.jsp").forward(request, response);
                break;
            case "userOrder":
                Order currentOrder = userService.takeCurrentOrder((Integer) session.getAttribute("userId"));
                request.setAttribute("currentOrder", currentOrder);
                request.setAttribute("driverRequested", userService.driverRequested(currentOrder.getId()));
                request.getRequestDispatcher("/userOrder.jsp").forward(request, response);
                break;
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

            if ("newOrder".equals(action)) {
                Order order = userService.makeOrder((Integer) session.getAttribute("userId"),
                        request.getParameter("address"),
                        request.getParameter("class"),
                        request.getParameter("comment"));
                response.sendRedirect("app?action=drivers");
                //request.getRequestDispatcher("/ableDrivers.jsp").forward(request, response);
            }

            if(action.equals("changeName")){
                String newName;
                newName = userService.changeName((Integer) session.getAttribute("userId"),
                        request.getParameter("name")).getName();
                session.setAttribute("userName", newName);
                request.getRequestDispatcher("/userPage.jsp").forward(request, response);
            }

            if(action.equals("drivers")){
                 userService.sendDriverRequest(Integer.valueOf(request.getParameter("acceptedDriver")),
                         (Integer)session.getAttribute("userId"));
                 userService.setOrderPrice(BigDecimal.valueOf(Double.valueOf(request.getParameter("orderPrice"))),
                         Integer.valueOf(request.getParameter("orderId")));
                request.getRequestDispatcher("/userPage.jsp").forward(request, response);
            }

            if(action.equals("changePassword")){
                userService.changePassword((Integer) session.getAttribute("userId"),
                        request.getParameter("current"), request.getParameter("new"),
                        request.getParameter("repeatNew"));
                request.getRequestDispatcher("/userPage.jsp").forward(request, response);
            }

            if(action.equals("cancelOrder")){
                userService.cancelOrder(Integer.valueOf(request.getParameter("canceledOrder")));
                response.sendRedirect("app?action=userOrder");
            }

        }



}

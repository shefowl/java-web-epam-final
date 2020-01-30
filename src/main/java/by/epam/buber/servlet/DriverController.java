package by.epam.buber.servlet;

import by.epam.buber.entity.Order;
import by.epam.buber.service.DriverService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/driver")
public class DriverController extends HttpServlet {
    private DriverService driverService = new DriverService();


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
            case "accept":
                request.getRequestDispatcher("/newOrder.jsp").forward(request, response);
                break;
            case "driverOrders":
                List<Order> orders = driverService.seeOrders((Integer)session.getAttribute("userId"));
                request.setAttribute("orders", orders);
                request.getRequestDispatcher("/driverOrders.jsp").forward(request, response);
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
                request.getRequestDispatcher("/newOrder.jsp").forward(request, response);
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

        if (action.equals("acceptOrder")) {
            //String s = request.getParameter("orderId");
            //int i = Integer.valueOf(request.getParameter("orderId"));
            //int j = (Integer) session.getAttribute("userId");
            driverService.acceptOrder(Integer.valueOf(request.getParameter("acceptedOrder")),
                    (Integer) session.getAttribute("userId"));
            request.getRequestDispatcher("/driverPage.jsp").forward(request, response);
        }

        if(action.equals("driverPage")){

        }

//        if(action.equals("changeName")){
//            String newName;
//            newName = driverService.changeName((Integer) session.getAttribute("userId"),
//                    request.getParameter("name")).getName();
//            session.setAttribute("userName", newName);
//            request.getRequestDispatcher("/userPage.jsp").forward(request, response);
//        }
//
//        if(action.equals("changePassword")){
//            driverService.changePassword((Integer) session.getAttribute("userId"),
//                    request.getParameter("current"), request.getParameter("new"),
//                    request.getParameter("repeatNew"));
//            request.getRequestDispatcher("/userPage.jsp").forward(request, response);
//        }

    }

}

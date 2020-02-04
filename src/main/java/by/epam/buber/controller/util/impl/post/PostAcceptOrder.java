package by.epam.buber.controller.util.impl.post;

import by.epam.buber.controller.util.Command;
import by.epam.buber.service.OrderService;
import by.epam.buber.service.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class PostAcceptOrder implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        OrderService orderService = serviceFactory.getOrderService();
        HttpSession session = request.getSession();

        orderService.acceptOrder(Integer.valueOf(request.getParameter("acceptedOrder")),
                (Integer) session.getAttribute("userId"));
        request.getRequestDispatcher("resources/page/driver/driverPage.jsp").forward(request, response);
        //return "resources/page/driver/driverPage.jsp";
    }
}

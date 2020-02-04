package by.epam.buber.controller.util.impl.get;

import by.epam.buber.controller.util.Command;
import by.epam.buber.entity.Order;
import by.epam.buber.service.OrderService;
import by.epam.buber.service.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class GetCurrentOrder implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        OrderService orderService = serviceFactory.getOrderService();
        HttpSession session =request.getSession();

        Order currentOrder = orderService.takeCurrentOrderForDriver((Integer) session.getAttribute("userId"));
        request.setAttribute("currentOrder", currentOrder);
        request.getRequestDispatcher("resources/page/driver/driverCurrentOrder.jsp").forward(request, response);
    }
}

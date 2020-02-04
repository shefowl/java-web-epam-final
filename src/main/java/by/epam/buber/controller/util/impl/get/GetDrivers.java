package by.epam.buber.controller.util.impl.get;

import by.epam.buber.controller.util.Command;
import by.epam.buber.entity.Order;
import by.epam.buber.entity.participant.Driver;
import by.epam.buber.service.OrderService;
import by.epam.buber.service.ServiceFactory;
import by.epam.buber.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class GetDrivers implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        OrderService orderService = serviceFactory.getOrderService();
        UserService userService = serviceFactory.getUserService();
        HttpSession session = request.getSession();

        Order order = orderService.takeCurrentOrderForUser((Integer) session.getAttribute("userId"));
        List<Driver> drivers = userService.showAbleDrivers(order.getCoordinates(), order.getCarClass());
        request.setAttribute("currentOrder", order);
        request.setAttribute("drivers", drivers);
        request.setAttribute("prices", orderService.calculateOrderPricePerDriver(drivers, order));
        request.setAttribute("driverRequested", userService.driverRequested(order.getId()));
        request.getRequestDispatcher("resources/page/user/ableDrivers.jsp").forward(request, response);
    }
}

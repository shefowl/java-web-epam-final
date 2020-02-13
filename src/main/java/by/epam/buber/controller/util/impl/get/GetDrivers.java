package by.epam.buber.controller.util.impl.get;

import by.epam.buber.controller.util.Command;
import by.epam.buber.entity.Order;
import by.epam.buber.entity.participant.Driver;
import by.epam.buber.exception.ControllerException;
import by.epam.buber.exception.ServiceException;
import by.epam.buber.service.OrderService;
import by.epam.buber.service.ServiceFactory;
import by.epam.buber.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static by.epam.buber.controller.util.Pages.ABLE_DRIVERS;

public class GetDrivers implements Command {
    private static final Logger logger = LogManager.getLogger(GetDrivers.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ControllerException {
        try{
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        OrderService orderService = serviceFactory.getOrderService();
        UserService userService = serviceFactory.getUserService();
        HttpSession session = request.getSession();
        Order order = orderService.takeCurrentOrderForUser((Integer) session.getAttribute("userId"));
        if(order != null) {
            List<Driver> drivers = userService.showAbleDrivers(order.getCoordinates(), order.getCarClass());
            request.setAttribute("ordered", true);
            request.setAttribute("drivers", drivers);
            request.setAttribute("currentOrder", order);
            request.setAttribute("prices", orderService.calculateOrderPricePerDriver(drivers, order));
            request.setAttribute("driverRequested", userService.driverRequested(order.getId()));
        }
        else {
            request.setAttribute("ordered", false);
        }
        request.getRequestDispatcher(ABLE_DRIVERS).forward(request, response);
        }catch (ServiceException e){
            logger.error(e);
            throw new ControllerException(e);
        }
    }
}

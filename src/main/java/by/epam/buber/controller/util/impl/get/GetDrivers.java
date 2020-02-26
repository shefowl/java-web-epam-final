package by.epam.buber.controller.util.impl.get;

import by.epam.buber.controller.util.Command;
import by.epam.buber.controller.util.Page;
import by.epam.buber.controller.util.RequestAttribute;
import by.epam.buber.controller.util.SessionAttribute;
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
import java.util.List;

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
        Order order = orderService.takeCurrentOrderForUser((Integer) session.getAttribute(SessionAttribute.USER_ID_ATTRIBUTE));
        if(order != null) {
            List<Driver> drivers = userService.showAbleDrivers(order.getCoordinates(), order.getCarClass());
            request.setAttribute(RequestAttribute.ORDERED, true);
            request.setAttribute(RequestAttribute.DRIVERS, drivers);
            request.setAttribute(RequestAttribute.CURRENT_ORDER, order);
            request.setAttribute(RequestAttribute.PRICES, orderService.calculateOrderPricePerDriver(drivers, order));
            request.setAttribute(RequestAttribute.DRIVER_REQUESTED, userService.driverRequested(order.getId()));
        }
        else {
            request.setAttribute(RequestAttribute.ORDERED, false);
        }
        request.getRequestDispatcher(Page.ABLE_DRIVERS).forward(request, response);
        }catch (ServiceException e){
            logger.error("error during command GetDrivers", e);
            throw new ControllerException(e);
        }
    }
}

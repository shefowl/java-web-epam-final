package by.epam.buber.controller.util.impl.get;

import by.epam.buber.controller.util.Command;
import by.epam.buber.controller.util.Page;
import by.epam.buber.controller.util.RequestAttribute;
import by.epam.buber.controller.util.SessionAttribute;
import by.epam.buber.entity.Order;
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

public class GetUserOrder implements Command {
    private static final Logger logger = LogManager.getLogger(GetUserOrder.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ControllerException {
        try{
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        OrderService orderService = serviceFactory.getOrderService();
        UserService userService = serviceFactory.getUserService();
        HttpSession session = request.getSession();

        Order currentOrder = orderService.takeCurrentOrderForUser((Integer) session.getAttribute(SessionAttribute.USER_ID_ATTRIBUTE));
        boolean ordered = currentOrder != null;
        request.setAttribute(RequestAttribute.ORDERED, ordered);
        if(ordered) {
            request.setAttribute(RequestAttribute.DRIVER_REQUESTED, userService.driverRequested(currentOrder.getId()));
            request.setAttribute(RequestAttribute.CURRENT_ORDER, currentOrder);
        }
        else {
            request.setAttribute(RequestAttribute.DRIVER_REQUESTED, false);
        }
        request.getRequestDispatcher(Page.USER_ORDER).forward(request, response);
        }catch (ServiceException e){
            logger.error("error during command GetUserOrder", e);
            throw new ControllerException(e);
        }
    }
}

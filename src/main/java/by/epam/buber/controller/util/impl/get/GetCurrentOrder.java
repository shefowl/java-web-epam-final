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
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static by.epam.buber.controller.util.Page.DRIVER_CURRENT_ORDER;

public class GetCurrentOrder implements Command {
    private static final Logger logger = LogManager.getLogger(GetCurrentOrder.class);
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ControllerException {
        try{
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        OrderService orderService = serviceFactory.getOrderService();
        HttpSession session =request.getSession();

        Order currentOrder = orderService.takeCurrentOrderForDriver((Integer) session.
                getAttribute(SessionAttribute.USER_ID_ATTRIBUTE));
        boolean ordered = currentOrder != null;
        request.setAttribute(RequestAttribute.CURRENT_ORDER, currentOrder);
        request.setAttribute(RequestAttribute.ORDERED, ordered);
        request.getRequestDispatcher(Page.DRIVER_CURRENT_ORDER).forward(request, response);
        }catch (ServiceException e){
            logger.error("error during command GetCurrentOrder", e);
            throw new ControllerException(e);
        }
    }
}

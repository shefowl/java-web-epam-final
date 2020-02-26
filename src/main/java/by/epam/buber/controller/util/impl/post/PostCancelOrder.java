package by.epam.buber.controller.util.impl.post;

import by.epam.buber.controller.util.Command;
import by.epam.buber.controller.util.Page;
import by.epam.buber.controller.util.RequestAttribute;
import by.epam.buber.exception.ControllerException;
import by.epam.buber.exception.ServiceException;
import by.epam.buber.service.OrderService;
import by.epam.buber.service.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class PostCancelOrder implements Command {
    private static final Logger logger = LogManager.getLogger(PostCancelOrder.class);
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ControllerException {
        try {
            ServiceFactory serviceFactory = ServiceFactory.getInstance();
            OrderService orderService = serviceFactory.getOrderService();
            int cancelOrderId = Integer.valueOf(request.getParameter(RequestAttribute.CANCELED_ORDER));
            if(orderService.startedOrder(cancelOrderId)) {
                orderService.cancelOrder(cancelOrderId);
            }

            request.getRequestDispatcher(Page.USER_ORDER);
        } catch (ServiceException e){
            logger.error("error during command PostCancelOrder", e);
            throw new ControllerException(e);
        }
    }
}

package by.epam.buber.controller.util.impl.post;

import by.epam.buber.controller.util.Command;
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

import static by.epam.buber.controller.util.Pages.USER_ORDER;
import static by.epam.buber.controller.util.Pages.USER_PAGE;

public class PostCancelOrder implements Command {
    private static final Logger logger = LogManager.getLogger(PostCancelOrder.class);
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ControllerException {
        try {
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        OrderService orderService = serviceFactory.getOrderService();

        int cancelOrderId = Integer.valueOf(request.getParameter("canceledOrder"));
        if(orderService.startedOrder(cancelOrderId)) {
            orderService.cancelOrder(cancelOrderId);
        }
        request.getRequestDispatcher(USER_ORDER);
        } catch (ServiceException e){
            logger.error(e);
            throw new ControllerException(e);
        }
    }
}

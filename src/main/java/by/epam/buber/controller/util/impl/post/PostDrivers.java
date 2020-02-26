package by.epam.buber.controller.util.impl.post;

import by.epam.buber.controller.util.Command;
import by.epam.buber.controller.util.Page;
import by.epam.buber.controller.util.RequestAttribute;
import by.epam.buber.controller.util.SessionAttribute;
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
import java.math.BigDecimal;

public class PostDrivers implements Command {
    private static final Logger logger = LogManager.getLogger(PostDrivers.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ControllerException {
        try{
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        OrderService orderService = serviceFactory.getOrderService();
        UserService userService = serviceFactory.getUserService();
        HttpSession session = request.getSession();

        userService.sendDriverRequest(Integer.valueOf(request.getParameter(RequestAttribute.ACCEPTED_DRIVER)),
                (Integer)session.getAttribute(SessionAttribute.USER_ID_ATTRIBUTE));
        orderService.setOrderPrice(BigDecimal.valueOf(Double.valueOf(request.getParameter(RequestAttribute.ORDER_PRICE))),
                Integer.valueOf(request.getParameter(RequestAttribute.ORDER_ID)));
        request.getRequestDispatcher(Page.USER_PAGE).forward(request, response);
        }catch (ServiceException e){
            logger.error("error during command PostDrivers", e);
            throw new ControllerException(e);
        }
    }
}

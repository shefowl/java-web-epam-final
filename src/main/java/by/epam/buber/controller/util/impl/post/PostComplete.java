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
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static by.epam.buber.controller.util.PostFormRedirection.DRIVER_CURRENT_ORDER_REDIRECT;

public class PostComplete implements Command {
    private static final Logger logger = LogManager.getLogger(PostComplete.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ControllerException {
        try{
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        OrderService orderService = serviceFactory.getOrderService();
        HttpSession session = request.getSession();

        orderService.completeOrder(Integer.valueOf(request.getParameter("startedOrder")),
                (Integer) session.getAttribute("userId"));
        response.sendRedirect(DRIVER_CURRENT_ORDER_REDIRECT);
        //request.getRequestDispatcher(DRIVER_PAGE).forward(request, response);
        //return "resources/page/driver/driverPage.jsp";
        }catch (ServiceException e){
            logger.error(e);
            throw new ControllerException(e);
        }
    }
}

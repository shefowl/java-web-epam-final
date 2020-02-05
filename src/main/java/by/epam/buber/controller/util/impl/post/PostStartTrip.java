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

import static by.epam.buber.controller.util.Pages.DRIVER_PAGE;

public class PostStartTrip implements Command {
    private static final Logger logger = LogManager.getLogger(PostStartTrip.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ControllerException {
        try{
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        OrderService orderService = serviceFactory.getOrderService();
        orderService.startTrip(Integer.valueOf(request.getParameter("startedOrder")));
        request.getRequestDispatcher(DRIVER_PAGE).forward(request, response);
        logger.info("trip started");
        }catch (ServiceException e){
            logger.error("error during start trip", e);
            throw new ControllerException(e);
        }
    }
}

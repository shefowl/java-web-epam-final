package by.epam.buber.controller.util.impl.post;

import by.epam.buber.controller.util.Command;
import by.epam.buber.exception.ControllerException;
import by.epam.buber.exception.ServiceException;
import by.epam.buber.service.DriverService;
import by.epam.buber.service.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static by.epam.buber.controller.util.Pages.DRIVER_BUSY;

public class PostFree implements Command {
    private static final Logger logger = LogManager.getLogger(PostFree.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ControllerException {
        try{
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        DriverService driverService = serviceFactory.getDriverService();
        HttpSession session = request.getSession();

        driverService.setFree((Integer)session.getAttribute("userId"));
        request.setAttribute("busy", false);
        request.getRequestDispatcher(DRIVER_BUSY).forward(request, response);
        //return "resources/page/driver/driverBusy.jsp";
        }catch (ServiceException e){
            logger.error(e);
            throw new ControllerException(e);
        }
    }
}

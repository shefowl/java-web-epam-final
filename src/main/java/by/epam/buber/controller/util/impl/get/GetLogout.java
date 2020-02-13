package by.epam.buber.controller.util.impl.get;

import by.epam.buber.controller.util.Command;
import by.epam.buber.entity.participant.Role;
import by.epam.buber.exception.ControllerException;
import by.epam.buber.exception.ServiceException;
import by.epam.buber.service.DriverService;
import by.epam.buber.service.ServiceFactory;
import by.epam.buber.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class GetLogout implements Command {
    private static final Logger logger = LogManager.getLogger(GetLogout.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ControllerException {
        try{
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        DriverService driverService = serviceFactory.getDriverService();
        HttpSession session = request.getSession();

        if(session.getAttribute("userRole") == Role.DRIVER){
            driverService.setDriverUnactive((Integer) session.getAttribute("userId"));
        }
        session.invalidate();
        request.getRequestDispatcher("/index.jsp").forward(request, response);
        }catch (ServiceException e){
            logger.error(e);
            throw new ControllerException(e);
        }
    }
}

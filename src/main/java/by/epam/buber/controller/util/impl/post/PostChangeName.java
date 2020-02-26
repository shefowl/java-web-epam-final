package by.epam.buber.controller.util.impl.post;

import by.epam.buber.controller.util.*;
import by.epam.buber.entity.participant.TaxiParticipant;
import by.epam.buber.exception.ControllerException;
import by.epam.buber.exception.ServiceException;
import by.epam.buber.service.ServiceFactory;
import by.epam.buber.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class PostChangeName implements Command {
    private static final Logger logger = LogManager.getLogger(PostChangeName.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ControllerException {
        try{
        HttpSession session = request.getSession();
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        UserService userService = serviceFactory.getUserService();

        TaxiParticipant participant = userService.changeName((Integer) session.getAttribute(SessionAttribute.USER_ID_ATTRIBUTE),
                request.getParameter(RequestAttribute.NAME));
        if (participant != null) {
            String newName = participant.getName();

            session.setAttribute(SessionAttribute.USER_NAME_ATTRIBUTE, newName);
            response.sendRedirect(Redirect.MAIN_REDIRECT);
        }
        else {
            request.setAttribute(RequestAttribute.ERROR, true);
            request.getRequestDispatcher(Page.USER_NAME).forward(request, response);
        }
        }catch (ServiceException e){
            logger.error("error during command PostChangeName", e);
            throw new ControllerException(e);
        }
    }
}

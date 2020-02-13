package by.epam.buber.controller.util.impl.post;

import by.epam.buber.controller.util.Command;
import by.epam.buber.entity.participant.TaxiParticipant;
import by.epam.buber.entity.participant.User;
import by.epam.buber.exception.ControllerException;
import by.epam.buber.exception.ServiceException;
import by.epam.buber.service.ServiceFactory;
import by.epam.buber.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.epam.buber.controller.util.Pages.SIGN_IN;
import static by.epam.buber.controller.util.Pages.SIGN_UP;

public class PostSignUp implements Command {
    private static final Logger logger = LogManager.getLogger(PostSignUp.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ControllerException {
        try{
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        UserService userService = serviceFactory.getUserService();
        TaxiParticipant participant = userService.signUp(request.getParameter("name"),request.getParameter("password"),
                request.getParameter("email"), request.getParameter("phone"));
        if(participant != null) {
            request.setAttribute("banned", false);
            request.setAttribute("invalid", false);
            request.getRequestDispatcher(SIGN_IN).forward(request, response);
            logger.info("signed up");
        }
        else {
            request.setAttribute("exist", true);
            request.getRequestDispatcher(SIGN_UP).forward(request, response);
        }
        }catch (ServiceException e){
            logger.error("error during sign up", e);
            throw new ControllerException(e);
        }
    }
}

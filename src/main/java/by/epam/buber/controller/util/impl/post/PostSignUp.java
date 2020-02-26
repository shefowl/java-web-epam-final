package by.epam.buber.controller.util.impl.post;

import by.epam.buber.controller.util.Command;
import by.epam.buber.controller.util.Page;
import by.epam.buber.controller.util.RequestAttribute;
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
import java.io.IOException;

import static by.epam.buber.controller.util.Page.SIGN_UP;

public class PostSignUp implements Command {
    private static final Logger logger = LogManager.getLogger(PostSignUp.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ControllerException {
        try{
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        UserService userService = serviceFactory.getUserService();
        TaxiParticipant participant = userService.signUp(request.getParameter(RequestAttribute.NAME),
                request.getParameter(RequestAttribute.PASSWORD),
                request.getParameter(RequestAttribute.EMAIL), request.getParameter(RequestAttribute.PHONE));

        if(participant != null) {
            request.setAttribute(RequestAttribute.BANNED, false);
            request.setAttribute(RequestAttribute.INVALID, false);
            request.getRequestDispatcher(Page.SIGN_IN).forward(request, response);
            logger.info("signed up");
        }
        else {
            request.setAttribute(RequestAttribute.EXIST, true);
            request.getRequestDispatcher(Page.SIGN_UP).forward(request, response);
        }
        }catch (ServiceException e){
            logger.error("error during sign up", e);
            throw new ControllerException(e);
        }
    }
}

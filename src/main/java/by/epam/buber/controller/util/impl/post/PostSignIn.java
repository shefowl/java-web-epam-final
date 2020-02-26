package by.epam.buber.controller.util.impl.post;

import by.epam.buber.controller.util.Command;
import by.epam.buber.controller.util.Page;
import by.epam.buber.controller.util.RequestAttribute;
import by.epam.buber.controller.util.SessionAttribute;
import by.epam.buber.entity.participant.Role;
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

import static by.epam.buber.controller.util.Page.*;

public class PostSignIn implements Command {
    private static final Logger logger = LogManager.getLogger(PostSignIn.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ControllerException {
        try{
        HttpSession session = request.getSession();
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        UserService userService = serviceFactory.getUserService();

        TaxiParticipant taxiParticipant = userService.signIn(request.getParameter(RequestAttribute.NAME),
                request.getParameter(RequestAttribute.PASSWORD));
        if(taxiParticipant != null) {
                request.setAttribute(RequestAttribute.INVALID, false);
            if(!taxiParticipant.isBanned()) {
                session.setAttribute(SessionAttribute.USER_NAME_ATTRIBUTE, taxiParticipant.getName());
                session.setAttribute(SessionAttribute.USER_ROLE_ATTRIBUTE, taxiParticipant.getRole());
                session.setAttribute(SessionAttribute.USER_ID_ATTRIBUTE, taxiParticipant.getId());
                //request.setAttribute("taxiParticipant", taxiParticipant);
                if (taxiParticipant.getRole() == Role.USER) {
                    request.getRequestDispatcher(Page.USER_PAGE).forward(request, response);
                    logger.info("user signed in");
                } else {
                    if (taxiParticipant.getRole() == Role.ADMIN) {
                        request.getRequestDispatcher(Page.ADMIN_PAGE).forward(request, response);
                        logger.info("admin signed in");
                    } else {
                        request.getRequestDispatcher(Page.DRIVER_PAGE).forward(request, response);
                        logger.info("driver signed in");
                    }
                }
            }
            else {
                request.setAttribute(RequestAttribute.BANNED, true);
                request.getRequestDispatcher(SIGN_IN).forward(request, response);
            }
        }
        else {
            request.setAttribute(RequestAttribute.INVALID, true);
            request.setAttribute(RequestAttribute.BANNED, false);
            request.getRequestDispatcher(Page.SIGN_IN).forward(request, response);
        }
        }catch (ServiceException e){
            logger.error("error during sign in", e);
            throw new ControllerException(e);
        }
    }
}

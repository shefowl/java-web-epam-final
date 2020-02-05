package by.epam.buber.controller.util.impl.post;

import by.epam.buber.controller.util.Command;
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

import static by.epam.buber.controller.util.Pages.*;

public class PostSignIn implements Command {
    private static final Logger logger = LogManager.getLogger(PostSignIn.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ControllerException {
        try{
        HttpSession session = request.getSession();
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        UserService userService = serviceFactory.getUserService();

        TaxiParticipant taxiParticipant = userService.signIn(request.getParameter("name"),
                request.getParameter("password"));
        if(taxiParticipant != null) {
                request.setAttribute("invalid", false);
            if(!taxiParticipant.isBanned()) {
                session.setAttribute("userName", taxiParticipant.getName());
                session.setAttribute("userRole", taxiParticipant.getRole());
                session.setAttribute("userId", taxiParticipant.getId());
                request.setAttribute("taxiParticipant", taxiParticipant);
                if (taxiParticipant.getRole() == Role.USER) {
                    //return "resources/page/user/userPage.jsp";
                    request.getRequestDispatcher(USER_PAGE).forward(request, response);
                    logger.info("user signed in");
                } else {
                    if (taxiParticipant.getRole() == Role.ADMIN) {
                        //return "resources/page/admin/adminPage.jsp";
                        request.getRequestDispatcher(ADMIN_PAGE).forward(request, response);
                        logger.info("admin signed in");
                    } else {
                        //return "resources/page/driver/driverPage.jsp";
                        request.getRequestDispatcher(DRIVER_PAGE).forward(request, response);
                        logger.info("driver signed in");
                    }
                }
            }
            else {
                request.setAttribute("banned", true);
                request.getRequestDispatcher(SIGN_IN).forward(request, response);
            }
        }
        else {
            request.setAttribute("invalid", true);
            request.setAttribute("banned", false);
            request.getRequestDispatcher(SIGN_IN).forward(request, response);
        }
        }catch (ServiceException e){
            logger.error("error during sign in", e);
            throw new ControllerException(e);
        }
    }
}

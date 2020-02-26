package by.epam.buber.controller.util.impl.get;

import by.epam.buber.controller.util.Command;
import by.epam.buber.controller.util.Redirect;
import by.epam.buber.controller.util.SessionAttribute;
import by.epam.buber.entity.participant.Role;
import by.epam.buber.exception.ControllerException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class GetParticipantPage implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ControllerException {
        HttpSession session = request.getSession();
        Role role = (Role)session.getAttribute(SessionAttribute.USER_ROLE_ATTRIBUTE);
        if(role != null) {
            switch (role) {
                case USER:
                    response.sendRedirect(Redirect.USER_PAGE_REDIRECT);
                    break;
                case DRIVER:
                    response.sendRedirect(Redirect.DRIVER_PAGE_REDIRECT);
                    break;
                case ADMIN:
                    response.sendRedirect(Redirect.ADMIN_PAGE_REDIRECT);
                    break;
            }
        }
        else {
            response.sendRedirect(Redirect.SIGN_IN_REDIRECT);
        }
    }
}

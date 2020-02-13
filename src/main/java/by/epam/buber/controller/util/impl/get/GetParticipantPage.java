package by.epam.buber.controller.util.impl.get;

import by.epam.buber.controller.util.Command;
import by.epam.buber.entity.participant.Role;
import by.epam.buber.exception.ControllerException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static by.epam.buber.controller.util.Pages.*;

public class GetParticipantPage implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ControllerException {
        HttpSession session = request.getSession();
        Role role = (Role)session.getAttribute("userRole");
        if(role != null) {
            switch (role) {
                case USER:
                    response.sendRedirect("app?action=user_Page");
                    break;
                case DRIVER:
                    response.sendRedirect("driver?action=driver_Page");
                    break;
                case ADMIN:
                    response.sendRedirect("admin?action=admin_Page");
                    break;
            }
        }
        else {
            response.sendRedirect("hello?action=sign_In");
        }
    }
}

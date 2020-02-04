package by.epam.buber.controller.util.impl.post;

import by.epam.buber.controller.util.Command;
import by.epam.buber.entity.participant.Role;
import by.epam.buber.entity.participant.TaxiParticipant;
import by.epam.buber.service.ServiceFactory;
import by.epam.buber.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class PostSignIn implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        UserService userService = serviceFactory.getUserService();

        TaxiParticipant taxiParticipant = userService.signIn(request.getParameter("name"),
                request.getParameter("password"));
        session.setAttribute("userName", taxiParticipant.getName());
        session.setAttribute("userRole", taxiParticipant.getRole());
        session.setAttribute("userId", taxiParticipant.getId());
        request.setAttribute("taxiParticipant", taxiParticipant);
        if(taxiParticipant.getRole() == Role.USER) {
            //return "resources/page/user/userPage.jsp";
            request.getRequestDispatcher("resources/page/user/userPage.jsp").forward(request, response);
        }
        else {
            if(taxiParticipant.getRole() == Role.ADMIN) {
                //return "resources/page/admin/adminPage.jsp";
                request.getRequestDispatcher("resources/page/admin/adminPage.jsp").forward(request, response);
            }
            else {
                //return "resources/page/driver/driverPage.jsp";
                request.getRequestDispatcher("resources/page/driver/driverPage.jsp").forward(request, response);
            }
        }
    }
}

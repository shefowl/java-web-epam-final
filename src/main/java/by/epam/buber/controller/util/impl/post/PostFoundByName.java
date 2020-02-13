package by.epam.buber.controller.util.impl.post;

import by.epam.buber.controller.util.Command;
import by.epam.buber.entity.participant.TaxiParticipant;
import by.epam.buber.exception.ControllerException;
import by.epam.buber.exception.ServiceException;
import by.epam.buber.service.AdminService;
import by.epam.buber.service.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static by.epam.buber.controller.util.Pages.ADMIN_FOUND_BY_NAME;

public class PostFoundByName implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ControllerException {
        try{
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        AdminService adminService = serviceFactory.getAdminService();

        List<TaxiParticipant> participants = adminService.getUsersByName(request.getParameter("name"));
        boolean found = !participants.isEmpty();
        request.setAttribute("found", found);
        if(found) {
            request.setAttribute("participants", participants);
            request.getRequestDispatcher(ADMIN_FOUND_BY_NAME).forward(request, response);
        }
        else {
            request.getRequestDispatcher(ADMIN_FOUND_BY_NAME).forward(request, response);
        }
        }catch (ServiceException e){
            throw new ControllerException(e);
        }
    }
}

package by.epam.buber.controller.util.impl.get;

import by.epam.buber.controller.util.Command;
import by.epam.buber.entity.participant.TaxiParticipant;
import by.epam.buber.exception.ControllerException;
import by.epam.buber.exception.ServiceException;
import by.epam.buber.service.AdminService;
import by.epam.buber.service.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static by.epam.buber.controller.util.Pages.ADMIN_PARTICIPANTS;
import static by.epam.buber.controller.util.Pages.ADMIN_SEARCH_RESULTS;

public class GetGetAll implements Command {
    private static final Logger logger = LogManager.getLogger(GetGetAll.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ControllerException {
        try{
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        AdminService adminService = serviceFactory.getAdminService();
        final int NOTES_PER_PAGE = 5;

        List<TaxiParticipant> participants = adminService.getAllParticipants();
        int size = participants.size();

        if(request.getParameter("begin") != null){
            Integer begin = Integer.valueOf(request.getParameter("begin"));
            request.setAttribute("begin", begin);
        }
        else {
            request.setAttribute("begin", 0);
        }
        request.setAttribute("participants", participants);
        request.setAttribute("numberOfNotes", size);
        request.setAttribute("notesPerPage", NOTES_PER_PAGE);
            request.getRequestDispatcher(ADMIN_SEARCH_RESULTS).forward(request, response);
        }catch (ServiceException e){
            logger.error(e);
            throw new ControllerException(e);
        }
    }
}

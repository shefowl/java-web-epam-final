package by.epam.buber.controller.util.impl.post;

import by.epam.buber.controller.util.Command;
import by.epam.buber.controller.util.Page;
import by.epam.buber.controller.util.RequestAttribute;
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
import java.util.ArrayList;
import java.util.List;

import static by.epam.buber.controller.util.Page.ADMIN_SEARCH_RESULTS;

public class PostFindById implements Command {
    private static final Logger logger = LogManager.getLogger(PostFindById.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ControllerException {
        try{
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        AdminService adminService = serviceFactory.getAdminService();
        final int NOTES_PER_PAGE = 5;

        List<TaxiParticipant> participants = new ArrayList<>();
        participants.add(adminService.getParticipantById(Integer.valueOf(request.getParameter(RequestAttribute.ID))));

        boolean found = !participants.isEmpty();
        request.setAttribute(RequestAttribute.FOUND, found);
        if(found) {
            if(request.getParameter(RequestAttribute.BEGIN) != null){
                Integer begin = Integer.valueOf(request.getParameter(RequestAttribute.BEGIN));
                request.setAttribute(RequestAttribute.BEGIN, begin);
            }
            else {
                request.setAttribute(RequestAttribute.BEGIN, 0);
            }
            int size = participants.size();
            request.setAttribute(RequestAttribute.PARTICIPANTS, participants);
            request.setAttribute(RequestAttribute.NUMBER_OF_NOTES, size);
            request.setAttribute(RequestAttribute.NOTES_PER_PAGE, NOTES_PER_PAGE);
            request.getRequestDispatcher(Page.ADMIN_SEARCH_RESULTS).forward(request, response);
        }
        else {
            request.getRequestDispatcher(Page.ADMIN_SEARCH_RESULTS).forward(request, response);
        }
        }catch (ServiceException e){
            logger.error("error during command PostFindById", e);
            throw new ControllerException(e);
        }
    }
}

package by.epam.buber.controller.util.impl.post;

import by.epam.buber.controller.util.Command;
import by.epam.buber.entity.participant.TaxiParticipant;
import by.epam.buber.service.AdminService;
import by.epam.buber.service.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PostFindById implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        AdminService adminService = serviceFactory.getAdminService();

        List<TaxiParticipant> participants = new ArrayList<>();
        participants.add(adminService.getParticipantById(Integer.valueOf(request.getParameter("id"))));
        request.setAttribute("participants", participants);
        request.getRequestDispatcher("resources/page/admin/adminFoundByName.jsp").forward(request, response);
    }
}

package by.epam.buber.controller.util.impl.get;

import by.epam.buber.controller.util.Command;
import by.epam.buber.entity.participant.TaxiParticipant;
import by.epam.buber.exception.ControllerException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.epam.buber.controller.util.Pages.ADMIN_FOUND_BY_ID;
import static by.epam.buber.controller.util.Pages.ADMIN_SEARCH_RESULTS;

public class GetFoundById implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException, ControllerException {
        request.getRequestDispatcher(ADMIN_SEARCH_RESULTS);
    }
}

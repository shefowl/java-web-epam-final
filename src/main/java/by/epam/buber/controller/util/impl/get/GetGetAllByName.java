package by.epam.buber.controller.util.impl.get;

import by.epam.buber.controller.util.Command;
import by.epam.buber.exception.ControllerException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.epam.buber.controller.util.Pages.ADMIN_ENTER_NAME;

public class GetGetAllByName implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ControllerException {
        request.getRequestDispatcher(ADMIN_ENTER_NAME).forward(request, response);
    }
}

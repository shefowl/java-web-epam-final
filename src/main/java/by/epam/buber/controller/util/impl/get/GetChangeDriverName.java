package by.epam.buber.controller.util.impl.get;

import by.epam.buber.controller.util.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.epam.buber.controller.util.Pages.DRIVER_NAME;

public class GetChangeDriverName implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(DRIVER_NAME).forward(request, response);
    }
}

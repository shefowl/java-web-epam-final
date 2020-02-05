package by.epam.buber.controller.util.impl.get;

import by.epam.buber.controller.util.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.epam.buber.controller.util.Pages.USER_PAGE;

public class GetUserPage implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(USER_PAGE).forward(request, response);
    }
}

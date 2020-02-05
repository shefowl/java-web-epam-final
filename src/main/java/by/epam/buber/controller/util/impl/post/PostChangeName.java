package by.epam.buber.controller.util.impl.post;

import by.epam.buber.controller.util.Command;
import by.epam.buber.exception.ControllerException;
import by.epam.buber.exception.ServiceException;
import by.epam.buber.service.ServiceFactory;
import by.epam.buber.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static by.epam.buber.controller.util.Pages.MAIN;
import static by.epam.buber.controller.util.Pages.USER_PAGE;

public class PostChangeName implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ControllerException {
        try{
        HttpSession session = request.getSession();
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        UserService userService = serviceFactory.getUserService();

        String newName;
        newName = userService.changeName((Integer) session.getAttribute("userId"),
                request.getParameter("name")).getName();
        session.setAttribute("userName", newName);
        request.getRequestDispatcher(MAIN).forward(request, response);
        }catch (ServiceException e){
            throw new ControllerException(e);
        }
    }
}

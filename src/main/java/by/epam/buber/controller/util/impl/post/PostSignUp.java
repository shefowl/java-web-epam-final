package by.epam.buber.controller.util.impl.post;

import by.epam.buber.controller.util.Command;
import by.epam.buber.service.ServiceFactory;
import by.epam.buber.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class PostSignUp implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        UserService userService = serviceFactory.getUserService();
        userService.signUp(request.getParameter("name"),request.getParameter("password"),
                request.getParameter("email"), request.getParameter("phone"));
        request.getRequestDispatcher("resources/page/main/signIn.jsp").forward(request, response);
        //return "resources/page/main/signIn.jsp";
    }
}

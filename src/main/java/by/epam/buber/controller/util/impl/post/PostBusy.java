package by.epam.buber.controller.util.impl.post;

import by.epam.buber.controller.util.Command;
import by.epam.buber.service.DriverService;
import by.epam.buber.service.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class PostBusy implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        DriverService driverService = serviceFactory.getDriverService();
        HttpSession session = request.getSession();

        driverService.setBusy((Integer)session.getAttribute("userId"));
        request.getRequestDispatcher("resources/page/driver/driverBusy.jsp").forward(request, response);
        //return "resources/page/driver/driverBusy.jsp";
    }
}

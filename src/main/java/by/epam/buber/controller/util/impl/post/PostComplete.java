package by.epam.buber.controller.util.impl.post;

import by.epam.buber.controller.util.Command;
import by.epam.buber.exception.ControllerException;
import by.epam.buber.exception.ServiceException;
import by.epam.buber.service.OrderService;
import by.epam.buber.service.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static by.epam.buber.controller.util.Pages.DRIVER_PAGE;

public class PostComplete implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ControllerException {
        try{
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        OrderService orderService = serviceFactory.getOrderService();
        HttpSession session = request.getSession();

        orderService.completeOrder(Integer.valueOf(request.getParameter("startedOrder")),
                (Integer) session.getAttribute("userId"));
        request.getRequestDispatcher(DRIVER_PAGE).forward(request, response);
        //return "resources/page/driver/driverPage.jsp";
        }catch (ServiceException e){
            throw new ControllerException(e);
        }
    }
}

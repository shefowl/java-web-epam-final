package by.epam.buber.controller.util.impl.get;

import by.epam.buber.controller.util.Command;
import by.epam.buber.entity.Order;
import by.epam.buber.exception.ControllerException;
import by.epam.buber.exception.ServiceException;
import by.epam.buber.service.OrderService;
import by.epam.buber.service.ServiceFactory;
import by.epam.buber.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static by.epam.buber.controller.util.Pages.USER_ORDER;

public class GetUserOrder implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ControllerException {
        try{
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        OrderService orderService = serviceFactory.getOrderService();
        UserService userService = serviceFactory.getUserService();
        HttpSession session = request.getSession();

        Order currentOrder = orderService.takeCurrentOrderForUser((Integer) session.getAttribute("userId"));
        boolean ordered = currentOrder != null;
        request.setAttribute("ordered", ordered);
        if(ordered) {
            request.setAttribute("driverRequested", userService.driverRequested(currentOrder.getId()));
            request.setAttribute("currentOrder", currentOrder);
        }
        else {
            request.setAttribute("driverRequested", false);
        }
        request.getRequestDispatcher(USER_ORDER).forward(request, response);
        }catch (ServiceException e){
            throw new ControllerException(e);
        }
    }
}

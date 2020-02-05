package by.epam.buber.controller.util.impl.get;

import by.epam.buber.controller.util.Command;
import by.epam.buber.entity.Order;
import by.epam.buber.exception.ControllerException;
import by.epam.buber.exception.ServiceException;
import by.epam.buber.service.OrderService;
import by.epam.buber.service.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

import static by.epam.buber.controller.util.Pages.DRIVER_ORDERS;

public class GetDriverOrders implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ControllerException {
        try{
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        OrderService orderService = serviceFactory.getOrderService();
        HttpSession session =request.getSession();

        List<Order> orders = orderService.seeDriverOrders((Integer)session.getAttribute("userId"));
            request.setAttribute("noOrders", orders.isEmpty());
            request.setAttribute("orderAccepted",
                    orderService.acceptedOrder((Integer) session.getAttribute("userId")));
            request.setAttribute("orders", orders);
            request.getRequestDispatcher(DRIVER_ORDERS).forward(request, response);
        }catch (ServiceException e){
            throw new ControllerException(e);
        }
    }
}

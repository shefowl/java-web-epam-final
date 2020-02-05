package by.epam.buber.controller.util.impl.post;

import by.epam.buber.controller.util.Command;
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
import java.math.BigDecimal;

import static by.epam.buber.controller.util.Pages.USER_PAGE;

public class PostDrivers implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ControllerException {
        try{
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        OrderService orderService = serviceFactory.getOrderService();
        UserService userService = serviceFactory.getUserService();
        HttpSession session = request.getSession();

        userService.sendDriverRequest(Integer.valueOf(request.getParameter("acceptedDriver")),
                (Integer)session.getAttribute("userId"));
        orderService.setOrderPrice(BigDecimal.valueOf(Double.valueOf(request.getParameter("orderPrice"))),
                Integer.valueOf(request.getParameter("orderId")));
        request.getRequestDispatcher(USER_PAGE).forward(request, response);
        }catch (ServiceException e){
            throw new ControllerException(e);
        }
    }
}

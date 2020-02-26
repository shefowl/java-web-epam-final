package by.epam.buber.controller.util.impl.post;

import by.epam.buber.controller.util.Command;
import by.epam.buber.controller.util.Page;
import by.epam.buber.controller.util.RequestAttribute;
import by.epam.buber.exception.ControllerException;
import by.epam.buber.exception.ServiceException;
import by.epam.buber.service.AdminService;
import by.epam.buber.service.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class PostDiscount implements Command {
    private static final Logger logger = LogManager.getLogger(PostDiscount.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ControllerException {
        final int DEFAULT_DISCOUNT = 10;
        try{
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        AdminService adminService = serviceFactory.getAdminService();

        adminService.setDiscount(Integer.valueOf(request.getParameter(RequestAttribute.PARTICIPANT)), DEFAULT_DISCOUNT);
        request.getRequestDispatcher(Page.ADMIN_PAGE).forward(request, response);
        }catch (ServiceException e){
            logger.error("error during command PostDiscount", e);
            throw new ControllerException(e);
        }
    }
}

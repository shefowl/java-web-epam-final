package by.epam.buber.controller.util.impl.post;

import by.epam.buber.controller.util.Command;
import by.epam.buber.service.AdminService;
import by.epam.buber.service.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class PostBan implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        AdminService adminService = serviceFactory.getAdminService();

        if(Boolean.getBoolean(request.getParameter("isBanned"))){
            adminService.unban((Integer.valueOf(request.getParameter("participant"))));
            request.getRequestDispatcher("resources/page/admin/adminPage.jsp").forward(request, response);
        }
        else {
            adminService.ban((Integer.valueOf(request.getParameter("participant"))));
            request.getRequestDispatcher("resources/page/admin/adminPage.jsp").forward(request, response);
        }
    }
}

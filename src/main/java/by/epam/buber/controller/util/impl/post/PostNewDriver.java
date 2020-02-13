package by.epam.buber.controller.util.impl.post;

import by.epam.buber.controller.util.Command;
import by.epam.buber.entity.Car;
import by.epam.buber.entity.CarClass;
import by.epam.buber.entity.participant.Driver;
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
import java.math.BigDecimal;

import static by.epam.buber.controller.util.Pages.ADMIN_PAGE;

public class PostNewDriver implements Command {
    private static final Logger logger = LogManager.getLogger(PostNewDriver.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ControllerException {
        try{
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        AdminService adminService = serviceFactory.getAdminService();

        Driver driver = new Driver();
        driver.setName(request.getParameter("name"));
        driver.setPassword(request.getParameter("password"));
        driver.setEmail(request.getParameter("email"));
        driver.setPhoneNumber(request.getParameter("phone"));
        double d = Double.valueOf(request.getParameter("pricePerKm"));
        driver.setPricePerKm(BigDecimal.valueOf(d));
        Car car = new Car();

        car.setCarClass(CarClass.valueOf(request.getParameter("carClass").toUpperCase()));
        car.setMark(request.getParameter("mark"));
        car.setModel(request.getParameter("model"));
        driver.setCar(car);
        adminService.registrateDriver(driver);
        request.getRequestDispatcher(ADMIN_PAGE).forward(request, response);
        }catch (ServiceException e){
            logger.error(e);
            throw new ControllerException(e);
        }
    }
}

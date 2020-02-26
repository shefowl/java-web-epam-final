package by.epam.buber.controller.util.impl.post;

import by.epam.buber.controller.util.Command;
import by.epam.buber.controller.util.Page;
import by.epam.buber.controller.util.RequestAttribute;
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

public class PostNewDriver implements Command {
    private static final Logger logger = LogManager.getLogger(PostNewDriver.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ControllerException {
        try{
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        AdminService adminService = serviceFactory.getAdminService();

        Driver driver = new Driver();
        driver.setName(request.getParameter(RequestAttribute.NAME));
        driver.setPassword(request.getParameter(RequestAttribute.PASSWORD));
        driver.setEmail(request.getParameter(RequestAttribute.EMAIL));
        driver.setPhoneNumber(request.getParameter(RequestAttribute.PHONE));
        double price = Double.valueOf(request.getParameter(RequestAttribute.PRICE_PER_KM));
        driver.setPricePerKm(BigDecimal.valueOf(price));

        Car car = new Car();
        car.setCarClass(CarClass.valueOf(request.getParameter(RequestAttribute.CAR_CLASS).toUpperCase()));
        car.setMark(request.getParameter(RequestAttribute.MARK));
        car.setModel(request.getParameter(RequestAttribute.MODEL));
        driver.setCar(car);
        adminService.signUpDriver(driver);
        request.getRequestDispatcher(Page.ADMIN_PAGE).forward(request, response);
        }catch (ServiceException e){
            logger.error("error during command PostNewDriver", e);
            throw new ControllerException(e);
        }
    }
}

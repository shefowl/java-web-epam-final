package by.epam.buber.controller.util.impl.post;

import by.epam.buber.controller.util.Command;
import by.epam.buber.entity.Car;
import by.epam.buber.entity.CarClass;
import by.epam.buber.entity.participant.Driver;
import by.epam.buber.service.AdminService;
import by.epam.buber.service.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;

public class PostNewDriver implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        AdminService adminService = serviceFactory.getAdminService();

        Driver driver = new Driver();
        driver.setName(request.getParameter("name"));
        driver.setPassword(request.getParameter("password"));
        driver.setEmail(request.getParameter("email"));
        driver.setPhoneNumber(request.getParameter("phone"));
        double d = Double.valueOf(request.getParameter("price"));
        driver.setPricePerKm(BigDecimal.valueOf(d));
        Car car = new Car();

        car.setCarClass(CarClass.valueOf(request.getParameter("carClass").toUpperCase()));
        car.setMark(request.getParameter("mark"));
        car.setModel(request.getParameter("model"));
        driver.setCar(car);
        adminService.registrateDriver(driver);
        request.getRequestDispatcher("resources/page/admin/adminPage.jsp").forward(request, response);
    }
}

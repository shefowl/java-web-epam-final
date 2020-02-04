package by.epam.buber.service;

import by.epam.buber.service.impl.AdminServiceImpl;
import by.epam.buber.service.impl.DriverServiceImpl;
import by.epam.buber.service.impl.OrderServiceImpl;
import by.epam.buber.service.impl.UserServiceImpl;

public class ServiceFactory {
    public static final ServiceFactory instance = new ServiceFactory();

    private final UserService userService = new UserServiceImpl();
    private final DriverService driverService = new DriverServiceImpl();
    private final OrderService orderService = new OrderServiceImpl();
    private final AdminService adminService = new AdminServiceImpl();

    private ServiceFactory(){

    }

    public static ServiceFactory getInstance(){
        return instance;
    }

    public UserService getUserService() {
        return userService;
    }

    public DriverService getDriverService() {
        return driverService;
    }

    public OrderService getOrderService() {
        return orderService;
    }

    public AdminService getAdminService() {
        return adminService;
    }
}

package by.epam.taxibuber.service;

import by.epam.buber.entity.participant.Role;
import by.epam.buber.entity.participant.User;
import by.epam.buber.exception.ServiceException;
import by.epam.buber.repository.DriverCrudRepository;
import by.epam.buber.repository.OrderCrudRepository;
import by.epam.buber.repository.RepositoryFactory;
import by.epam.buber.repository.UserCrudRepository;
import by.epam.buber.service.ServiceFactory;
import by.epam.buber.service.UserService;
import by.epam.buber.service.impl.util.PasswordEncoder;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class UserServiceTest {
    private RepositoryFactory repositoryFactory = RepositoryFactory.getInstance();
    private UserCrudRepository userCrudRepository = repositoryFactory.getUserCrudRepository();
    private OrderCrudRepository orderCrudRepository = repositoryFactory.getOrderCrudRepository();
    private DriverCrudRepository driverCrudRepository = repositoryFactory.getDriverCrudRepository();
    private PasswordEncoder passwordEncoder = new PasswordEncoder();
    private static UserService userService = ServiceFactory.getInstance().getUserService();
    private static User user = new User();

    @BeforeClass
    public static void initializeUser(){
        user.setName("nagibator");
        user.setPassword("qwer");
        user.setEmail("qwer@mail.ru");
        user.setRole(Role.USER);
        user.setPhoneNumber("0");
        user.setBanned(false);
        user.setId(3);
        user.setDiscount(10);
    }

    @Test
    public void signInTestTrue() throws ServiceException {
        Assert.assertEquals(user.getId(), userService.signIn(user.getName(), user.getPassword()).getId());
    }

    @Test
    public void signInTestFalse() throws ServiceException {
        Assert.assertNull(userService.signIn(user.getName(),"wrongPassword"));
    }

    @Test
    public void signUpTestFalse() throws ServiceException {
        Assert.assertNull(userService.signUp(user.getName(), user.getPassword(), user.getEmail(), user.getPhoneNumber()));
    }
}

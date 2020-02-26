package by.epam.taxibuber.service;

import by.epam.buber.entity.CarClass;
import by.epam.buber.entity.Order;
import by.epam.buber.entity.participant.Role;
import by.epam.buber.entity.participant.TaxiParticipant;
import by.epam.buber.entity.participant.User;
import by.epam.buber.exception.DaoException;
import by.epam.buber.exception.ServiceException;
import by.epam.buber.repository.DriverCrudRepository;
import by.epam.buber.repository.OrderCrudRepository;
import by.epam.buber.repository.RepositoryFactory;
import by.epam.buber.repository.UserCrudRepository;
import by.epam.buber.repository.impl.UserCrudRepositoryImpl;
import by.epam.buber.service.ServiceFactory;
import by.epam.buber.service.UserService;
import by.epam.buber.service.impl.UserServiceImpl;
import by.epam.buber.service.impl.util.PasswordEncoder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {
    private RepositoryFactory repositoryFactory = RepositoryFactory.getInstance();
    //@InjectMocks
    @Mock
    private UserCrudRepository userCrudRepository;
    @Mock
    private OrderCrudRepository orderCrudRepository;
    @Mock
    private DriverCrudRepository driverCrudRepository;
    private PasswordEncoder passwordEncoder = new PasswordEncoder();
    @InjectMocks
    private static UserService userService = ServiceFactory.getInstance().getUserService();
    private static User user = new User();

    @BeforeClass
    public static void initializeUser(){
        user.setName("test");
        user.setPassword("test");
        user.setEmail("test@test.com");
        user.setRole(Role.USER);
        user.setPhoneNumber("123456789012");
        user.setBanned(false);
        user.setDiscount(0);
    }
    @Before
    public void initMocks(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void signInTestTrue() throws ServiceException, DaoException {
        Assert.assertEquals(user.getId(), userService.signIn("admin", "admin").getId());
//        userService.signIn(user.getName(), user.getPassword());
//        verify(userCrudRepository, atLeastOnce()).getByName(anyString());
    }

    @Test
    public void signInTestFalse() throws ServiceException {
        Assert.assertNull(userService.signIn(user.getName(),"wrongPassword"));
    }

    @Test
    public void signUpTestFalse() throws ServiceException {
        Assert.assertNull(userService.signUp(user.getName(), user.getPassword(), user.getEmail(), user.getPhoneNumber()));
    }

    @Test
    public void signUpTestTrue() throws ServiceException, DaoException {
        user = (User)userService.signUp(user.getName(), user.getPassword(), user.getEmail(), user.getPhoneNumber());
        verify(userCrudRepository, atLeastOnce()).save(user);
    }

    @Test
    public void showAbleDriversTestTrue() throws ServiceException, DaoException {
        userService.showAbleDrivers(0, CarClass.BUDGET);
        verify(driverCrudRepository, atLeastOnce()).getAbleDriversByCarClass(any());
    }

    @Test
    public void driverRequestedTestTrue() throws ServiceException, DaoException {
        userService.driverRequested(0);
        verify(orderCrudRepository, atLeastOnce()).driverRequested(Mockito.anyInt());
    }

    @Test
    public void sendDriverRequestTestTrue() throws ServiceException, DaoException {
        userService.sendDriverRequest(0, 0);
        verify(driverCrudRepository, atLeastOnce()).setOrderToDriver(Mockito.anyObject(), Mockito.anyInt());
    }

    @Test
    public void changeNameTestFalse() throws ServiceException, DaoException {
        userService.changeName(0, "test");
        verify(userCrudRepository, atLeastOnce()).getById(anyInt());
        verify(userCrudRepository, never()).save(anyInt(), any(TaxiParticipant.class));
    }

//    @Test
//    public void changeNameTestTrue() throws ServiceException, DaoException {
//        userService.changeName(28, "newName");
//        verify(userCrudRepository, atLeastOnce()).getById(28);
//        verify(userCrudRepository, atLeastOnce()).save(anyInt(), any(TaxiParticipant.class));
//    }

    @Test
    public void changePasswordTest() throws ServiceException, DaoException {
        userService.sendDriverRequest(0, 0);
        verify(driverCrudRepository, atLeastOnce()).setOrderToDriver(Mockito.anyObject(), Mockito.anyInt());
    }
}

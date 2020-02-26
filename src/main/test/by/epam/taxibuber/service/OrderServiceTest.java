package by.epam.taxibuber.service;

import by.epam.buber.entity.Order;
import by.epam.buber.exception.DaoException;
import by.epam.buber.exception.ServiceException;
import by.epam.buber.repository.DriverCrudRepository;
import by.epam.buber.repository.OrderCrudRepository;
import by.epam.buber.repository.UserCrudRepository;
import by.epam.buber.service.OrderService;
import by.epam.buber.service.ServiceFactory;
import by.epam.buber.service.impl.util.PasswordEncoder;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.runners.MockitoJUnitRunner;

import java.math.BigDecimal;

import static org.mockito.Matchers.*;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class OrderServiceTest {
    @Mock
    private UserCrudRepository userCrudRepository;
    @Mock
    private OrderCrudRepository orderCrudRepository;
    @Mock
    private DriverCrudRepository driverCrudRepository;
    private PasswordEncoder passwordEncoder = new PasswordEncoder();
    @InjectMocks
    private static OrderService orderService = ServiceFactory.getInstance().getOrderService();

    @Before
    public void initMocks(){
        MockitoAnnotations.initMocks(this);
    }

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void seeDriverOrdersTest() throws ServiceException, DaoException{
        orderService.seeDriverOrders(0);
        verify(orderCrudRepository, atLeastOnce()).getCurrentFromDriverList(anyInt());
    }

    @Test
    public void acceptedOrderTest() throws ServiceException, DaoException{
        orderService.acceptedOrder(0);
        verify(orderCrudRepository, atLeastOnce()).driverAccepted(anyInt());
    }

    @Test
    public void takeCurrentOrderForDriverTest() throws ServiceException, DaoException{
        orderService.takeCurrentOrderForDriver(0);
        verify(orderCrudRepository, atLeastOnce()).getCurrentByDriverId(anyInt());
    }

    @Test
    public void startTripTest() throws ServiceException, DaoException{
        orderService.startTrip(0);
        verify(orderCrudRepository, atLeastOnce()).setStartedById(anyInt(), anyBoolean());
    }

    @Test
    public void acceptOrderTest() throws ServiceException, DaoException{
        InOrder inOrderOrder = Mockito.inOrder(orderCrudRepository);
        InOrder inOrderDriver = Mockito.inOrder(driverCrudRepository);
        orderService.acceptOrder(0, 0);
        inOrderOrder.verify(orderCrudRepository, atLeastOnce()).setAccepted(anyInt(), anyInt());
        inOrderDriver.verify(driverCrudRepository, atLeastOnce()).setBusyById(anyInt(), anyBoolean());
        inOrderOrder.verify(orderCrudRepository, atLeastOnce()).clearDriverOrderListExceptAccepted(anyInt());
    }

    @Test//(expected = NullPointerException.class)
    public void completeOrderTest() throws ServiceException, DaoException{
        InOrder inOrderOrder = Mockito.inOrder(orderCrudRepository);
        InOrder inOrderDriver = Mockito.inOrder(driverCrudRepository);
        InOrder inOrderUser = Mockito.inOrder(userCrudRepository);
        orderService.completeOrder(0, 0);
        inOrderOrder.verify(orderCrudRepository, atLeastOnce()).setCompleted(anyBoolean(), anyInt());
        inOrderDriver.verify(driverCrudRepository, atLeastOnce()).setBusyById(anyInt(), anyBoolean());
        inOrderOrder.verify(orderCrudRepository, atLeastOnce()).deleteFromDriverList(anyInt());
        inOrderOrder.verify(orderCrudRepository, atLeastOnce()).getById(anyInt());
        //inOrderDriver.verify(driverCrudRepository, atLeastOnce()).setDriverCoordinates(anyInt(), anyInt());
        //inOrderUser.verify(userCrudRepository, atLeastOnce()).setDiscount(anyInt(), anyInt());
       // inOrderOrder.verify(orderCrudRepository, atLeastOnce()).orderStarted(anyInt());
    }

    @Test
    public void makeOrderTest() throws ServiceException, DaoException{
        orderService.makeOrder(0, "", "budget", "");
        verify(orderCrudRepository, atLeastOnce()).save(any(Order.class));
    }

    @Test
    public void startedOrderTest() throws ServiceException, DaoException{
        orderService.startedOrder(0);
        verify(orderCrudRepository, atLeastOnce()).orderStarted(anyInt());
    }

    @Test
    public void takeCurrentOrderForUserTest() throws ServiceException, DaoException{
        orderService.takeCurrentOrderForUser(0);
        verify(orderCrudRepository, atLeastOnce()).getCurrentByUserId(anyInt());
    }

    @Test
    public void orderMadeTest() throws ServiceException, DaoException{
        orderService.takeCurrentOrderForDriver(0);
        verify(orderCrudRepository, atLeastOnce()).getCurrentByUserId(anyInt());
    }

    @Test
    public void cancelOrderTest() throws ServiceException, DaoException{
        InOrder inOrder = Mockito.inOrder(orderCrudRepository);
        orderService.cancelOrder(0);
        inOrder.verify(orderCrudRepository, atLeastOnce()).orderStarted(anyInt());
        inOrder.verify(orderCrudRepository, atLeastOnce()).delete(anyInt());
        inOrder.verify(orderCrudRepository, atLeastOnce()).deleteFromDriverList(anyInt());
    }

    @Test
    public void setOrderPriceTest() throws ServiceException, DaoException{
        orderService.setOrderPrice(BigDecimal.valueOf(0), 0);
        verify(orderCrudRepository, atLeastOnce()).setPrice(any(BigDecimal.class), anyInt());
    }
}

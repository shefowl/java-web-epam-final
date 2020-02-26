package by.epam.taxibuber.service;

import by.epam.buber.entity.participant.Driver;
import by.epam.buber.exception.DaoException;
import by.epam.buber.exception.ServiceException;
import by.epam.buber.repository.DriverCrudRepository;
import by.epam.buber.repository.UserCrudRepository;
import by.epam.buber.service.AdminService;
import by.epam.buber.service.ServiceFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class AdminServiceTest {
    @Mock
    private UserCrudRepository userCrudRepository;
    @Mock
    private DriverCrudRepository driverCrudRepository;
    @InjectMocks
    private static AdminService adminService = ServiceFactory.getInstance().getAdminService();

    @Before
    public void initMocks(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void banTest() throws ServiceException, DaoException {
        adminService.ban(0, true);
        verify(userCrudRepository, atLeastOnce()).ban(anyInt(), eq(true));
    }

    @Test
    public void getAllParticipantsTest() throws ServiceException, DaoException {
        adminService.getAllParticipants();
        verify(userCrudRepository, atLeastOnce()).getAll();
    }

    @Test
    public void getUsersByNameTest() throws ServiceException, DaoException {
        adminService.getUsersByName("");
        verify(userCrudRepository, atLeastOnce()).getAll();
    }

    @Test
    public void getUsersForDiscountTest() throws ServiceException, DaoException {
        adminService.getUsersForDiscount();
        verify(userCrudRepository, atLeastOnce()).getAllUsers();
    }

    @Test
    public void setDiscountTest() throws ServiceException, DaoException {
        adminService.setDiscount(0, 0);
        verify(userCrudRepository, atLeastOnce()).setDiscount(anyInt(), anyInt());
    }

    @Test
    public void getParticipantByIdTest() throws ServiceException, DaoException {
        adminService.getParticipantById(0);
        verify(userCrudRepository, atLeastOnce()).getById(anyInt());
    }

    @Test
    public void signUpDriverTest() throws ServiceException, DaoException {
        Driver driver = mock(Driver.class);
        adminService.signUpDriver(driver);
        verify(driverCrudRepository, atLeastOnce()).save(driver);
    }
}

package by.epam.taxibuber.service;

import by.epam.buber.exception.DaoException;
import by.epam.buber.exception.ServiceException;
import by.epam.buber.repository.DriverCrudRepository;
import by.epam.buber.service.DriverService;
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
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class DriverServiceTest {
    @Mock
    private DriverCrudRepository driverCrudRepository;
    @InjectMocks
    private static DriverService driverService = ServiceFactory.getInstance().getDriverService();

    @Before
    public void initMocks(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void setBusyTest() throws ServiceException, DaoException {
        driverService.setBusy(0);
        verify(driverCrudRepository, atLeastOnce()).setBusyById(anyInt(), eq(true));
    }

    @Test
    public void setFreeTest() throws ServiceException, DaoException {
        driverService.setFree(0);
        verify(driverCrudRepository, atLeastOnce()).setBusyById(anyInt(), eq(false));
    }

    @Test
    public void setActiveTest() throws ServiceException, DaoException {
        driverService.setActive(0);
        verify(driverCrudRepository, atLeastOnce()).setDriverActive(anyInt(), eq(true));
    }

    @Test
    public void setUnactiveTest() throws ServiceException, DaoException {
        driverService.setUnactive(0);
        verify(driverCrudRepository, atLeastOnce()).setDriverActive(anyInt(), eq(false));
    }

    @Test
    public void isBusyTest() throws ServiceException, DaoException {
        driverService.isBusy(0);
        verify(driverCrudRepository, atLeastOnce()).isDriverBusy(anyInt());
    }
}

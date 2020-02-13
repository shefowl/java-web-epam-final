package by.epam.taxibuber.repository;

import by.epam.buber.entity.participant.Role;
import by.epam.buber.entity.participant.User;
import by.epam.buber.exception.DaoException;
import by.epam.buber.exception.ServiceException;
import by.epam.buber.repository.RepositoryFactory;
import by.epam.buber.repository.UserCrudRepository;
import by.epam.buber.repository.pool.ConnectionPool;
import by.epam.buber.repository.pool.ConnectionPoolException;
import org.junit.*;

import java.sql.Connection;
import java.sql.SQLException;

public class UserCrudRepositoryTest {
    private static User user;
    private static User takenUser;

    private RepositoryFactory repositoryFactory = RepositoryFactory.getInstance();
    private UserCrudRepository userCrudRepository = repositoryFactory.getUserCrudRepository();
    //private static Connection connection;



    @BeforeClass
    public static void initializeUser() throws ConnectionPoolException, SQLException {
        user = new User();
        user.setName("test");
        user.setPassword("test");
        user.setEmail("test");
        user.setRole(Role.USER);
        user.setPhoneNumber("test");
        user.setBanned(false);
        user.setDiscount(0);
        //connection = ConnectionPool.getInstance().getConnection();
//        connection.createStatement().executeQuery("START TRANSACTION");
//        connection.createStatement().executeQuery("SET autocommit=0");
        ///connection.setAutoCommit(false);
    }

    @Test
    public void save() throws DaoException {
        userCrudRepository.save(user);
    }

    @Test
    public void getByNameTrue() throws DaoException {
        takenUser = (User)userCrudRepository.getByName(user.getName());
        Assert.assertEquals(user, takenUser);
    }

    @Test
    public void getByIdTrue() throws DaoException {
        Assert.assertEquals(user.getId(), userCrudRepository.getById(3).getId());
    }

    @Test
    public void update() throws DaoException {
    }

    @Test
    public void delete() throws DaoException {
        userCrudRepository.delete(takenUser.getId());
    }

//    @After
//    public void rollback() throws SQLException{
//        connection.rollback();
//    }
}

//package by.epam.taxibuber.repository;
//
//import by.epam.buber.entity.participant.Role;
//import by.epam.buber.entity.participant.User;
//import by.epam.buber.exception.DaoException;
//import by.epam.buber.repository.RepositoryFactory;
//import by.epam.buber.repository.UserCrudRepository;
//import by.epam.buber.repository.pool.ConnectionPool;
//import by.epam.buber.repository.pool.ConnectionPoolException;
//import org.junit.After;
//import org.junit.Assert;
//import org.junit.BeforeClass;
//import org.junit.Test;
//
//import java.sql.Connection;
//import java.sql.SQLException;
//import java.sql.Statement;
//
////@FixMethodOrder(MethodSorters.)
//public class UserCrudRepositoryTest {
//    private static User user;
//    private static User takenUser;
//
//    private RepositoryFactory repositoryFactory = RepositoryFactory.getInstance();
//    private UserCrudRepository userCrudRepository = repositoryFactory.getUserCrudRepository();
//    private static Connection connection;
////    public static final String SQL_USER_INSERT = "INSERT INTO participant(name, password, email, role, phone, banned) " +
////            "VALUES(?,?,?,?,?,?); SELECT LAST_INSERT_ID() INTO @mysql_variable_here; " +
////            "INSERT INTO user(participantId, discount) VALUES(@mysql_variable_here, 0);";
//    public static final int TESTING_USER_ID = 28;
//
//    @BeforeClass
//    public static void initializeUser() throws ConnectionPoolException, SQLException {
//        user = new User();
//        user.setName("test");
//        user.setPassword("test");
//        user.setEmail("test");
//        user.setRole(Role.USER);
//        user.setPhoneNumber("test");
//        user.setBanned(false);
//        user.setDiscount(0);
//        connection = ConnectionPool.getInstance().getConnection();
//        Statement statement = connection.createStatement();
//        statement.executeUpdate("START TRANSACTION ");
////        connection.prepareStatement(SQL_USER_INSERT);
//    }
//
//    @Test
//    public void save() throws DaoException {
//        userCrudRepository.save(user);
//    }
//
//    @Test
//    public void getByNameTrue() throws DaoException {
//        takenUser = (User)userCrudRepository.getByName(user.getName());
//        Assert.assertEquals(user, takenUser);
//    }
//
//    @Test
//    public void getByIdTrue() throws DaoException {
//        Assert.assertEquals(user.getId(), userCrudRepository.getById(3).getId());
//    }
//
//    @Test
//    public void update() throws DaoException {
//        userCrudRepository.save(TESTING_USER_ID, user);
//    }
//
//    @Test
//    public void delete() throws DaoException {
//        userCrudRepository.delete(TESTING_USER_ID);
//    }
//
//    @After
//    public void rollback() throws SQLException{
//        Statement statement = connection.createStatement();
//        statement.executeUpdate("ROLLBACK");
//    }
//}

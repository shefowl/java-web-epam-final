package by.epam.buber.repository.impl;

import by.epam.buber.exception.DaoException;
import by.epam.buber.repository.pool.ConnectionPool;
import by.epam.buber.repository.pool.ConnectionPoolException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

//package-private access
abstract class AbstractRepository {
    private final static Logger logger = LogManager.getLogger(AbstractRepository.class);

    //package-private access
    void deleteById(Integer id, String sqlRequest) throws DaoException{
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(sqlRequest)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        }
        catch (ConnectionPoolException | SQLException e) {
            logger.error(e);
            throw new DaoException(e);
        }
    }

    //package-private access
    void setBooleanById(boolean bool, Integer id, String sqlRequest) throws DaoException{
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(sqlRequest)) {
            statement.setBoolean(1, bool);
            statement.setInt(2, id);
            statement.executeUpdate();
        }
        catch (ConnectionPoolException | SQLException e) {
            logger.error(e);
            throw new DaoException(e);
        }
    }

    //package-private access
    void setByTwoIntegers(Integer firstId, Integer secondId, String sqlRequest) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(sqlRequest)) {
            statement.setInt(1, firstId);
            statement.setInt(2, secondId);
            statement.executeUpdate();
        }
        catch (ConnectionPoolException | SQLException e) {
            logger.error(e);
            throw new DaoException(e);
        }
    }
}

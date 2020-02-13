package by.epam.buber.repository.impl;

import by.epam.buber.entity.participant.Driver;
import by.epam.buber.entity.participant.*;
import by.epam.buber.exception.DaoException;
import by.epam.buber.repository.UserCrudRepository;
import by.epam.buber.repository.impl.util.ResultSetConverter;
import by.epam.buber.repository.pool.ConnectionPool;
import by.epam.buber.repository.pool.ConnectionPoolException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserCrudRepositoryImpl extends AbstractRepository implements UserCrudRepository {

    private final static Logger logger = LogManager.getLogger(UserCrudRepositoryImpl.class);

    private ResultSetConverter converter = ResultSetConverter.getInstance();

    public static final String SQL_USER_REQUEST = "SELECT  * FROM participant";
    public static final String SQL_USER_REQUEST_BY_ID = "SELECT  * FROM participant WHERE id=?";
    public static final String SQL_USER_REQUEST_BY_NAME = "SELECT  * FROM participant WHERE name=?";
    public static final String SQL_USER_INSERT = "INSERT INTO participant(name, password, role, email, phone, banned) " +
            "VALUES(?, ?, 'USER', ?,?,?); SELECT LAST_INSERT_ID() INTO @mysql_variable_here; " +
            "INSERT INTO user(participantId, discount) VALUES(@mysql_variable_here, 0);";
    public static final String SQL_USER_UPDATE_BY_ID = "UPDATE participant SET name=?, password=?, email=?, role=?," +
            " phone=? WHERE id=?";
    public static final String SQL_USER_UPDATE_BANNED_BY_ID = "UPDATE participant SET banned=? WHERE id=?";
    public static final String SQL_USER_DELETE_BY_ID = "SET FOREIGN_KEY_CHECKS=0; DELETE p, u FROM participant p JOIN user u " +
            "ON p.id=u.participantId WHERE p.id=? SET FOREIGN_KEY_CHECKS=1;";
    public static final String SQL_USER_JOIN_BY_ID = "SELECT * FROM participant p" +
            " inner join user u ON u.participantId = p.id WHERE p.id =?";
    public static final String SQL_USER_UPDATE_DISCOUNT_BY_ID = "UPDATE user SET discount=? WHERE participantId=?";




    @Override
    public List<TaxiParticipant> getAll() throws DaoException {
        List<TaxiParticipant> participants = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SQL_USER_REQUEST)) {
            while(resultSet.next()) {
                participants.add(createParticipantByRole(resultSet));
            }
        } catch (ConnectionPoolException | SQLException e) {
            logger.error(e);
            throw new DaoException(e);
        }
        return participants;
    }

    @Override
    public TaxiParticipant getById(Integer id) throws DaoException {
        TaxiParticipant participant;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement  = connection.prepareStatement(SQL_USER_REQUEST_BY_ID)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if(resultSet.next()) {
                    return createParticipantByRole(resultSet);
                }
            }
        } catch (ConnectionPoolException | SQLException e) {
            logger.error(e);
            throw new DaoException(e);
        }
        return null;
    }

    @Override
    public TaxiParticipant getByName(String name) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement  = connection.prepareStatement(SQL_USER_REQUEST_BY_NAME)) {
            statement.setString(1, name);
            try (ResultSet resultSet = statement.executeQuery()) {
                if(resultSet.next()) {
                    return createParticipantByRole(resultSet);
                }
            }
        } catch (ConnectionPoolException | SQLException e) {
            logger.error(e);
            throw new DaoException(e);
        }
        return null;
    }

    private TaxiParticipant createParticipantByRole(ResultSet resultSet)
            throws DaoException {
        try {
            TaxiParticipant participant;

            if (Role.valueOf(resultSet.getString("role")) == Role.USER) {
                participant = converter.convertParticipantFromResultSet(resultSet, new User());
                participant = joinUser((User) participant);
                return participant;
            } else {
                if (Role.valueOf(resultSet.getString("role")) == Role.ADMIN) {
                    participant = converter.convertParticipantFromResultSet(resultSet, new Admin());
                    return participant;
                } else {
                    participant = converter.convertParticipantFromResultSet(resultSet, new Driver());
                    return participant;
                }
            }

        }catch (SQLException e){
            logger.error(e);
            throw new DaoException(e);
        }
    }

    @Override
    public void setDiscount(Integer userId, int discount) throws DaoException {
        setByTwoIntegers(discount, userId, SQL_USER_UPDATE_DISCOUNT_BY_ID);
    }

    private User joinUser(User user) throws DaoException{
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement  = connection.prepareStatement(SQL_USER_JOIN_BY_ID)) {
            statement.setInt(1, user.getId());
            try(ResultSet resultSet = statement.executeQuery()){
                if(resultSet.next()) {
                    user.setDiscount(resultSet.getInt("discount"));
                }
            }
        } catch (ConnectionPoolException | SQLException e) {
            logger.error(e);
            throw new DaoException(e);
        }
        return user;
    }

    @Override
    public void ban(Integer participantId, boolean ban) throws DaoException {
        setBooleanById(ban, participantId, SQL_USER_UPDATE_BANNED_BY_ID);
    }

    @Override
    public void save(TaxiParticipant user) throws DaoException{
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_USER_INSERT)) {
            converter.statementSetParticipant(statement, user);
            statement.executeUpdate();
        }
        catch (ConnectionPoolException | SQLException e) {
            logger.error(e);
            throw new DaoException(e);
        }
    }

    @Override
    public void save(Integer id, TaxiParticipant user) throws DaoException{
        try (Connection connection = ConnectionPool.getInstance().getConnection()){
            connection.setAutoCommit(false);
            try (PreparedStatement statement = connection.prepareStatement(SQL_USER_UPDATE_BY_ID)){
                converter.statementSetParticipant(statement, user);
                statement.setInt(6, id);
                statement.executeUpdate();
                connection.commit();
                connection.setAutoCommit(true);
            }
            catch (SQLException e){
                connection.rollback();
                logger.error(e);
                throw new DaoException(e);
            }
        }
        catch (ConnectionPoolException | SQLException e) {
            logger.error(e);
            throw new DaoException(e);
        }
    }

    @Override
    public void delete(Integer id) throws DaoException {
        deleteById(id, SQL_USER_DELETE_BY_ID);
    }
}

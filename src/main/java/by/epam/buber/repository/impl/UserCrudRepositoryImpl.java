package by.epam.buber.repository.impl;

import by.epam.buber.entity.participant.Driver;
import by.epam.buber.entity.participant.*;
import by.epam.buber.repository.UserCrudRepository;
import by.epam.buber.repository.impl.util.ResultSetConverter;
import by.epam.buber.repository.pool.ConnectionPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserCrudRepositoryImpl implements UserCrudRepository {

    private ResultSetConverter converter = ResultSetConverter.getInstance();

    public static final String SQL_USER_REQUEST = "SELECT  * FROM participant";
    public static final String SQL_USER_REQUEST_BY_ID = "SELECT  * FROM participant WHERE id=?";
    public static final String SQL_USER_REQUEST_BY_NAME = "SELECT  * FROM participant WHERE name=?";
    public static final String SQL_USER_UPDATE = "INSERT INTO participant(name, password, email, role, phone)" +
            "VALUES (?,?,?,?,?)";
    public static final String SQL_USER_UPDATE_BY_ID = "UPDATE participant SET name=?, password=?, email=?, role=?," +
            " phone=? WHERE id=?";
    public static final String SQL_USER_UPDATE_BANNED_BY_ID = "UPDATE participant SET banned=? WHERE id=?";
    public static final String SQL_USER_DELETE_BY_ID = "DELETE FROM participant WHERE id=?";
    public static final String SQL_USER_JOIN_BY_ID = "SELECT * FROM participant p" +
            " inner join user u ON u.participantId = p.id WHERE p.id =?";
    public static final String SQL_USER_UPDATE_DISCOUNT_BY_ID = "UPDATE user SET discount=? WHERE participantId=?";



    @Override
    public List<TaxiParticipant> getAll(){
        List<TaxiParticipant> participants = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SQL_USER_REQUEST)) {
            //User user = new User();
            TaxiParticipant participant;
            while(resultSet.next()) {
                //if(resultSet.getString("role").equals(Role.USER))
                if(Role.valueOf(resultSet.getString("role")) == Role.USER){
                    participant = converter.convertParticipantFromResultSet(resultSet, new User());
                    participants.add(participant);
                }
                else {
                    if(Role.valueOf(resultSet.getString("role")) == Role.ADMIN){
                        participant = converter.convertParticipantFromResultSet(resultSet, new Admin());
                        participants.add(participant);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return participants;
    }

    @Override
    public TaxiParticipant getById(Integer id) {
        TaxiParticipant participant;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement  = connection.prepareStatement(SQL_USER_REQUEST_BY_ID)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if(resultSet.next()) {
                    if (Role.valueOf(resultSet.getString("role")) == Role.USER) {
                        participant = converter.convertParticipantFromResultSet(resultSet, new User());
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
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public TaxiParticipant getByName(String name) {
        TaxiParticipant participant;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement  = connection.prepareStatement(SQL_USER_REQUEST_BY_NAME)) {
            statement.setString(1, name);
            try (ResultSet resultSet = statement.executeQuery()) {
                if(resultSet.next()) {
                    if (Role.valueOf(resultSet.getString("role")) == Role.USER) {
                        participant = converter.convertParticipantFromResultSet(resultSet, new User());
                        participant = joinUser((User) participant);
                        return participant;
                    } else {
                        if (Role.valueOf(resultSet.getString("role")) == Role.ADMIN) {
                            participant = converter.convertParticipantFromResultSet(resultSet, new Admin());
                            return participant;
                        }
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void setDiscount(Integer userId, int discount) {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_USER_UPDATE_DISCOUNT_BY_ID)) {
            statement.setInt(1, discount);
            statement.setInt(2, userId);
            statement.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private User joinUser(User user){
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement  = connection.prepareStatement(SQL_USER_JOIN_BY_ID)) {
            statement.setInt(1, user.getId());
            try(ResultSet resultSet = statement.executeQuery()){
                if(resultSet.next()) {
                    user.setDiscount(resultSet.getInt("discount"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public void ban(Integer participantId, boolean ban) {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_USER_UPDATE_BANNED_BY_ID)) {
            converter.statementSetBooleanById(statement, ban, participantId);
            statement.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void save(TaxiParticipant user){
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_USER_UPDATE)) {
            converter.statementSetParticipant(statement, user);
            statement.executeUpdate();
        }
        catch (SQLException e) {
                e.printStackTrace();
        }
    }

    @Override
    public void save(Integer id, TaxiParticipant user){
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_USER_UPDATE_BY_ID)) {
            converter.statementSetParticipant(statement, user);
            statement.setInt(6, id);
            statement.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Integer id) {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_USER_DELETE_BY_ID)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

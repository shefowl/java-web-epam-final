package by.epam.buber.repository.impl;

import by.epam.buber.entity.participant.Admin;
import by.epam.buber.entity.participant.Driver;
import by.epam.buber.entity.participant.Role;
import by.epam.buber.entity.participant.TaxiParticipant;
import by.epam.buber.entity.participant.User;
import by.epam.buber.repository.UserCrudRepository;
import by.epam.buber.repository.pool.ConnectionPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserCrudRepositoryImpl implements UserCrudRepository {
    public static final String SQL_USER_REQUEST = "SELECT  * FROM participant";
    public static final String SQL_USER_REQUEST_BY_ID = "SELECT  * FROM participant WHERE id=?";
    public static final String SQL_USER_REQUEST_BY_NAME = "SELECT  * FROM participant WHERE id=?";
    public static final String SQL_USER_UPDATE = "INSERT INTO participant(name, password)VALUES (?,?)";
    public static final String SQL_USER_UPDATE_BY_ID = "UPDATE participant SET name=?, password=? WHERE id=?";
    public static final String SQL_USER_DELETE_BY_ID = "DELETE FROM participant WHERE id=?";

    @Override
    public List<TaxiParticipant> getAll(){
        List<TaxiParticipant> users = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SQL_USER_REQUEST)) {
            User user = new User();
            while(resultSet.next()) {
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setPassword(resultSet.getString("password"));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public TaxiParticipant getById(int id) {
        TaxiParticipant participant;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement  = connection.prepareStatement(SQL_USER_REQUEST_BY_ID)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if(Role.valueOf(resultSet.getString("role")) == Role.USER){
                    participant = convertFromResultSet(resultSet, new User());
                    return participant;
                }
                else {
                    if(Role.valueOf(resultSet.getString("role")) == Role.ADMIN){
                        participant = convertFromResultSet(resultSet, new Admin());
                        return participant;
                    }
                    else{
                        participant = convertFromResultSet(resultSet, new Driver());
                        return participant;
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
                if(Role.valueOf(resultSet.getString("role")) == Role.USER){
                    participant = convertFromResultSet(resultSet, new User());
                    return participant;
                }
                else {
                    if(Role.valueOf(resultSet.getString("role")) == Role.ADMIN){
                        participant = convertFromResultSet(resultSet, new Admin());
                        return participant;
                    }
                    else{
                        participant = convertFromResultSet(resultSet, new Driver());
                        return participant;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private TaxiParticipant convertFromResultSet(ResultSet resultSet, TaxiParticipant participant){
        try {
            participant.setId(resultSet.getInt("id"));
            participant.setName(resultSet.getString("name"));
            participant.setEmail(resultSet.getString("email"));
            participant.setRole(Role.valueOf(resultSet.getString("role")));
            participant.setPhoneNumber(resultSet.getString("phone"));
            participant.setPassword(resultSet.getString("password"));
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return participant;
    }

    @Override
    public void save(TaxiParticipant user){
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_USER_UPDATE)) {
            statement.setString(1, user.getName());
            statement.setString(2, user.getPassword());
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
            statement.setString(1, user.getName());
            statement.setString(2, user.getPassword());
            statement.setInt(3, id);
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

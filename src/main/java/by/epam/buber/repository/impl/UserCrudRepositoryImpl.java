package by.epam.buber.repository.impl;

import by.epam.buber.entity.Role;
import by.epam.buber.entity.User;
import by.epam.buber.repository.UserCrudRepository;
import by.epam.buber.repository.pool.ConnectionPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserCrudRepositoryImpl implements UserCrudRepository {
    public static final String SQL_USER_REQUEST = "SELECT  * FROM user";
    public static final String SQL_USER_UPDATE = "INSERT INTO user(name, password)VALUES (?,?)";
    public static final String SQL_USER_UPDATE_BY_ID = "UPDATE user SET name=?, password=? WHERE id=?";
    public static final String SQL_USER_DELETE_BY_ID = "DELETE FROM user WHERE id=?";

    @Override
    public List<User> getAll(){
        List<User> users = new ArrayList<>();
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
    public User getById(int id) {
        User user = new User();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SQL_USER_REQUEST)) {

            int userId;
            while(resultSet.next()) {
                userId = resultSet.getInt("id");
                if(userId == id) {
                    user.setId(resultSet.getInt("id"));
                    user.setName(resultSet.getString("name"));
                    user.setPassword(resultSet.getString("password"));
                }
            }
        } catch (SQLException e) {
                e.printStackTrace();
        }
        return user;
    }

    @Override
    public User getByName(String name) {
        User user = null;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SQL_USER_REQUEST)) {

            String userName;
            while(resultSet.next()) {
                userName = resultSet.getString("name");
                if(userName.equals(name)) {
                    user = new User();
                    user.setId(resultSet.getInt("id"));
                    user.setName(resultSet.getString("name"));
                    user.setPassword(resultSet.getString("password"));
                    user.setRole(Role.valueOf(resultSet.getString("role").toUpperCase()));
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        return user;
    }

    @Override
    public void save(User user){
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_USER_UPDATE)) {
            statement.setString(1, user.getName());
            statement.setString(2,user.getPassword());
            statement.executeUpdate();
        }
        catch (SQLException e) {
                e.printStackTrace();
            }
    }

    @Override
    public void save(Integer id, User user){
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_USER_UPDATE_BY_ID)) {
            statement.setString(1, user.getName());
            statement.setString(2,user.getPassword());
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

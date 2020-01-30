package by.epam.buber.repository.impl;

import by.epam.buber.entity.Car;
import by.epam.buber.entity.CarClass;
import by.epam.buber.entity.Order;
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
    public static final String SQL_USER_REQUEST_BY_NAME = "SELECT  * FROM participant WHERE name=?";
    public static final String SQL_USER_UPDATE = "INSERT INTO participant(name, password)VALUES (?,?)";
    public static final String SQL_DRIVER_UPDATE_ORDERS = "INSERT INTO driverOrderList(orderId, driverId)VALUES (?,?)";
    public static final String SQL_USER_UPDATE_BY_ID = "UPDATE participant SET name=?, password=? WHERE id=?";
    public static final String SQL_USER_DELETE_BY_ID = "DELETE FROM participant WHERE id=?";
    public static final String SQL_USER_JOIN_BY_ID = "SELECT * FROM participant p" +
            " inner join user u ON u.participantId = p.id WHERE p.id =?";
    public static final String SQL_DRIVER_JOIN_CAR_JOIN_BY_ID = "SELECT * FROM participant JOIN driver ON " +
            "participant.id=driver.participantId JOIN car ON participant.id=car.driverId WHERE id=?;";
    public static final String SQL_DRIVER_UPDATE_BUSY_BY_ID = "UPDATE driver SET busy=? WHERE id=?";
    public static final String SQL_DRIVER_GET_ALL_BY_ID = "SELECT * FROM participant WHERE role='DRIVER'";


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

    //@Override
    public List<Driver> getAllDrivers(){
        List<Driver> drivers = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SQL_DRIVER_GET_ALL_BY_ID)) {
            while(resultSet.next()) {
                Driver driver = new Driver();
                driver.setId(resultSet.getInt("id"));
                driver.setName(resultSet.getString("name"));
                driver.setEmail(resultSet.getString("email"));
                drivers.add(driver);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return drivers;
    }

    @Override
    public TaxiParticipant getById(int id) {
        TaxiParticipant participant;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement  = connection.prepareStatement(SQL_USER_REQUEST_BY_ID)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if(Role.valueOf(resultSet.getString("role")) == Role.USER){
                    participant = convertParticipantFromResultSet(resultSet, new User());
                    return participant;
                }
                else {
                    if(Role.valueOf(resultSet.getString("role")) == Role.ADMIN){
                        participant = convertParticipantFromResultSet(resultSet, new Admin());
                        return participant;
                    }
                    else{
                        participant = convertParticipantFromResultSet(resultSet, new Driver());
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
                if(resultSet.next()) {
                    if (Role.valueOf(resultSet.getString("role")) == Role.USER) {
                        participant = convertParticipantFromResultSet(resultSet, new User());
                        participant = joinUser((User) participant);
                        return participant;
                    } else {
                        if (Role.valueOf(resultSet.getString("role")) == Role.ADMIN) {
                            participant = convertParticipantFromResultSet(resultSet, new Admin());
                            return participant;
                        } else {
                            participant = convertParticipantFromResultSet(resultSet, new Driver());
                            participant = joinDriver((Driver) participant);
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

    private User joinUser(User user){
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement  = connection.prepareStatement(SQL_USER_JOIN_BY_ID)) {
            statement.setInt(1, user.getId());
            try(ResultSet resultSet = statement.executeQuery()){
                if(resultSet.next()) {
                    user.setBill(resultSet.getBigDecimal("bill"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    private Driver joinDriver(Driver driver){
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement  = connection.prepareStatement(SQL_DRIVER_JOIN_CAR_JOIN_BY_ID)) {
            statement.setInt(1, driver.getId());
            try(ResultSet resultSet = statement.executeQuery()){
                if(resultSet.next()) {
                    driver.setActive(resultSet.getBoolean("active"));
                    driver.setBusy(resultSet.getBoolean("busy"));
                    Car car = new Car(resultSet.getString("mark"), resultSet.getString("model"),
                            CarClass.valueOf(resultSet.getString("carClass").toUpperCase()));
                    driver.setCar(car);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return driver;
    }

    @Override
    public void setBusyById(int id, boolean busy) {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_DRIVER_UPDATE_BUSY_BY_ID)) {
            statement.setBoolean(1, busy);
            statement.setInt(2, id);
            statement.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setOrderToDriver(Order order, int id){
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_DRIVER_UPDATE_ORDERS)) {
            statement.setInt(1, order.getId());
            statement.setInt(2, id);
            statement.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //    public Car joinCar(Driver driver){
//        try (Connection connection = ConnectionPool.getInstance().getConnection();
//             PreparedStatement statement  = connection.prepareStatement(SQL_DRIVER_JOIN_BY_ID)) {
//            statement.setInt(1, driver.getId());
//            try(ResultSet resultSet = statement.executeQuery()){
//                driver.setActive(resultSet.getBoolean("active"));
//                driver.setBusy(resultSet.getBoolean("busy"));
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return driver;
//    }

    private TaxiParticipant convertParticipantFromResultSet(ResultSet resultSet, TaxiParticipant participant){
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

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
    public static final String SQL_DRIVER_IS_BUSY_BY_ID = "SELECT  busy FROM driver WHERE participantId=?";
    public static final String SQL_USER_REQUEST_BY_ID = "SELECT  * FROM participant WHERE id=?";
    public static final String SQL_USER_REQUEST_BY_NAME = "SELECT  * FROM participant WHERE name=?";
    public static final String SQL_USER_UPDATE = "INSERT INTO participant(name, password, email, role, phone)" +
            "VALUES (?,?,?,?,?)";
    public static final String SQL_DRIVER_UPDATE = "INSERT INTO driver(participantId, active, busy, coordinates, pricePerKm)" +
            "VALUES (?,?,?,?,?)";
    public static final String SQL_CAR_UPDATE = "INSERT INTO car(driverId, mark, model, carClass)" +
            "VALUES (?,?,?,?)";
    public static final String SQL_DRIVER_UPDATE_ORDERS = "INSERT INTO driverOrderList(orderId, driverId)VALUES (?,?)";
    public static final String SQL_USER_UPDATE_BY_ID = "UPDATE participant SET name=?, password=?, email=?, role=?," +
            " phone=? WHERE id=?";
    public static final String SQL_USER_UPDATE_BANNED_BY_ID = "UPDATE participant SET banned=? WHERE id=?";
    public static final String SQL_USER_DELETE_BY_ID = "DELETE FROM participant WHERE id=?";
    public static final String SQL_USER_JOIN_BY_ID = "SELECT * FROM participant p" +
            " inner join user u ON u.participantId = p.id WHERE p.id =?";
    public static final String SQL_DRIVER_JOIN_CAR_JOIN_BY_ID = "SELECT * FROM participant JOIN driver ON " +
            "participant.id=driver.participantId JOIN car ON participant.id=car.driverId WHERE id=?;";
    public static final String SQL_DRIVER_UPDATE_BUSY_BY_ID = "UPDATE driver SET busy=? WHERE participantId=?";
    public static final String SQL_USER_UPDATE_DISCOUNT_BY_ID = "UPDATE user SET discount=? WHERE participantId=?";
    public static final String SQL_DRIVER_UPDATE_ACTIVE_BY_ID = "UPDATE driver SET active=? WHERE participantId=?";
    public static final String SQL_DRIVER_GET_ALL_BY_ID = "SELECT * FROM participant WHERE role='DRIVER'";
    public static final String SQL_DRIVER_GET_ABLE_BY_COORDINATES_AND_CAR_CLASS =
            " SELECT * FROM (SELECT * FROM (SELECT * FROM (SELECT * FROM driver JOIN car ON " +
                    "participantId=driverId WHERE carClass=?) AS T WHERE active=1)" +
                    " AS D WHERE busy=0) AS P JOIN participant ON id=driverId;";
    public static final String SET_FOREIGN_KEY_CHECKS_0 = "SET FOREIGN_KEY_CHECKS = 0";
    public static final String SET_FOREIGN_KEY_CHECKS_1 = "SET FOREIGN_KEY_CHECKS = 1";


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
                    participant = convertParticipantFromResultSet(resultSet, new User());
                    participants.add(participant);
                }
                else {
                    if(Role.valueOf(resultSet.getString("role")) == Role.ADMIN){
                        participant = convertParticipantFromResultSet(resultSet, new Admin());
                        participants.add(participant);
                    }
                    else{
                        participant = convertParticipantFromResultSet(resultSet, new Driver());
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
                driver = joinDriver(driver);
                drivers.add(driver);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return drivers;
    }

    @Override
    public List<Driver> getAbleDriversByCarClass(CarClass carClass){
        List<Driver> drivers = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_DRIVER_GET_ABLE_BY_COORDINATES_AND_CAR_CLASS)) {
            statement.setString(1, carClass.name());
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()) {
                Driver driver = new Driver();
                driver.setId(resultSet.getInt("id"));
                driver.setName(resultSet.getString("name"));
                driver.setEmail(resultSet.getString("email"));
                driver = joinDriver(driver);
                drivers.add(driver);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return drivers;
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
                        participant = convertParticipantFromResultSet(resultSet, new User());
                        return participant;
                    } else {
                        if (Role.valueOf(resultSet.getString("role")) == Role.ADMIN) {
                            participant = convertParticipantFromResultSet(resultSet, new Admin());
                            return participant;
                        } else {
                            participant = convertParticipantFromResultSet(resultSet, new Driver());
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
                    driver.setCoordinates(resultSet.getLong("coordinates"));
                    driver.setPricePerKm(resultSet.getBigDecimal("pricePerKm"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return driver;
    }

    @Override
    public boolean isDriverBusy(Integer driverId){
        boolean busy = false;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement  = connection.prepareStatement(SQL_DRIVER_IS_BUSY_BY_ID)) {
            statement.setInt(1, driverId);
            try(ResultSet resultSet = statement.executeQuery()){
                if(resultSet.next()) {
                    busy = resultSet.getBoolean("busy");
                }
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return busy;
    }

    @Override
    public void setBusyById(Integer id, boolean busy) {
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

    @Override
    public void setDriverActive(Integer driverId, boolean active) {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_DRIVER_UPDATE_ACTIVE_BY_ID)) {
            statement.setBoolean(1, active);
            statement.setInt(2, driverId);
            statement.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void ban(Integer participantId, boolean ban) {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_USER_UPDATE_BANNED_BY_ID)) {
            statement.setBoolean(1, ban);
            statement.setInt(2, participantId);
            statement.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setOrderToDriver(Order order, Integer id){
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

    @Override
    public void setDriverCoordinates(Integer driverId, long coordinates) {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_DRIVER_UPDATE_ORDERS)) {
            statement.setLong(1, coordinates);
            statement.setInt(2, driverId);
            statement.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private TaxiParticipant convertParticipantFromResultSet(ResultSet resultSet, TaxiParticipant participant){
        try {
            participant.setId(resultSet.getInt("id"));
            participant.setName(resultSet.getString("name"));
            participant.setEmail(resultSet.getString("email"));
            participant.setRole(Role.valueOf(resultSet.getString("role")));
            participant.setPhoneNumber(resultSet.getString("phone"));
            participant.setPassword(resultSet.getString("password"));
            participant.setBanned(resultSet.getBoolean("banned"));
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
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getRole().name());
            statement.setString(5, user.getPhoneNumber());
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
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getRole().name());
            statement.setString(5, user.getPhoneNumber());
            statement.setInt(6, id);
            statement.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveDriver(Driver driver){
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_DRIVER_UPDATE)) {
            statement.executeUpdate(SET_FOREIGN_KEY_CHECKS_0);
            statement.setInt(1, driver.getId());
            statement.setBoolean(2, driver.isActive());
            statement.setBoolean(3, driver.isBusy());
            statement.setLong(4, driver.getCoordinates());
            statement.setBigDecimal(5, driver.getPricePerKm());
            statement.executeUpdate();
            statement.executeUpdate(SET_FOREIGN_KEY_CHECKS_1);
            saveCar(driver.getCar(), driver.getId());
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void saveCar(Car car, Integer driverId){
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_CAR_UPDATE)) {
            statement.executeUpdate(SET_FOREIGN_KEY_CHECKS_0);
            statement.setInt(1, driverId);
            statement.setString(2, car.getMark());
            statement.setString(3, car.getModel());
            statement.setString(4, car.getCarClass().name());
            statement.executeUpdate();
            statement.executeUpdate(SET_FOREIGN_KEY_CHECKS_1);
        }
        catch (SQLException e){
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

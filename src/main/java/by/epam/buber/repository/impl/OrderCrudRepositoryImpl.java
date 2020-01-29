package by.epam.buber.repository.impl;

import by.epam.buber.entity.CarClass;
import by.epam.buber.entity.Order;
import by.epam.buber.repository.OrderCrudRepository;
import by.epam.buber.repository.pool.ConnectionPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderCrudRepositoryImpl implements OrderCrudRepository {
    public static final String SQL_ORDER_REQUEST = "SELECT  * FROM carOrder";
    public static final String SQL_ORDER_REQUEST_BY_ID = "SELECT  * FROM carOrder WHERE id=?";
    public static final String SQL_ORDER_REQUEST_BY_USER_ID = "SELECT  * FROM carOrder WHERE userId=?";
    public static final String SQL_ORDER_UPDATE = "INSERT INTO carOrder(userId, coordinates, destinationPoint," +
            " price, orderComment, carClass, completed, destinationCoordinates," +
            " driverId)VALUES (?,?,?,?,?,?,?,?,?)";
    public static final String SQL_ORDER_UPDATE_BY_ID = "UPDATE carOrder SET price=?, completed=? WHERE id=?";
    public static final String SQL_ORDER_UPDATE_ACCEPTED_BY_ID = "UPDATE carOrder SET driverId=? WHERE id=?";
    public static final String SQL_ORDER_DELETE_BY_ID = "DELETE FROM carOrder WHERE id=?";
    public static final String SQL_FOREIGN_KEY_CHEKCS_0 = "SET FOREIGN_KEY_CHECKS = 0";
    public static final String SQL_FOREIGN_KEY_CHEKCS_1 = "SET FOREIGN_KEY_CHECKS = 1";

    @Override
    public List<Order> getOrdersByParticipantId(int id) {
        List<Order> orders = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement  = connection.prepareStatement(SQL_ORDER_REQUEST_BY_ID)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                Order order;
                while (resultSet.next()) {
                    order = convertFromResultSet(resultSet);
                    orders.add(order);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }

    @Override
    public List<Order> getAll() {
        List<Order> orders = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SQL_ORDER_REQUEST)) {
            Order order;
            while(resultSet.next()) {
                order = convertFromResultSet(resultSet);
                orders.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }

    @Override
    public Order getById(int id) {
        Order order = null;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement  = connection.prepareStatement(SQL_ORDER_REQUEST_BY_ID)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                order = convertFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return order;
    }

    public Order getByUserId(int id) {
        Order order = null;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement  = connection.prepareStatement(SQL_ORDER_REQUEST_BY_USER_ID)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                order = convertFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return order;
    }


    private Order convertFromResultSet(ResultSet resultSet){
        Order order = new Order();
        try {
            order.setId(resultSet.getInt("id"));
            order.setCoordinates(resultSet.getInt("coordinates"));
            order.setDestinationPoint(resultSet.getString("destinationPoint"));
            order.setDestinationCoordinates(resultSet.getInt("destinationCoordinates"));
            order.setPrice(resultSet.getBigDecimal("price"));
            order.setDestinationPoint(resultSet.getString("orderComment"));
            order.setCarClass(CarClass.valueOf(resultSet.getString("carClass")));
            order.setCompleted(resultSet.getBoolean("completed"));
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return order;
    }

    @Override
    public void save(Integer id, Order order) { // вынести принятие в отдельный метод
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_ORDER_UPDATE_BY_ID)) {
            //statement.setString(1, order.get());
            statement.setBigDecimal(1, order.getPrice());
            statement.setBoolean(2, order.isCompleted());
            statement.setInt(3, id);
            statement.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void save(Order order) {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             Statement statementCheks0 = connection.createStatement();
             Statement statementCheks1 = connection.createStatement();
             PreparedStatement statement = connection.prepareStatement(SQL_ORDER_UPDATE)) {
            statementCheks0.executeQuery(SQL_FOREIGN_KEY_CHEKCS_0); // поместить в очередь?
            statement.setInt(1, order.getUserId());
            statement.setInt(2, order.getCoordinates());
            statement.setString(3, order.getDestinationPoint());
            statement.setBigDecimal(4, order.getPrice());
            statement.setString(5, order.getComment());
            statement.setString(6, order.getCarClass().name());
            statement.setBoolean(7, order.isCompleted());
            statement.setInt(8, order.getDestinationCoordinates());
            statement.setInt(9, order.getDriverId());
            statement.executeUpdate();
            statementCheks0.executeQuery(SQL_FOREIGN_KEY_CHEKCS_1);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setAccepted(int driverId, int orderId) {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_ORDER_UPDATE_ACCEPTED_BY_ID)) {
            statement.setInt(1, driverId);
            statement.setInt(2, orderId);
            statement.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Integer id) {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_ORDER_DELETE_BY_ID)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

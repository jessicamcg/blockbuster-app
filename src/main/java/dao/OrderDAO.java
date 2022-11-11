package dao;

import model.Customer;
import model.Movie;
import model.Order;
import model.Payment;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class OrderDAO {
  public OrderDAO() { }
  private static final String SELECT_ORDER_BY_ID = "select * from order_details where id=?;";
  private static final String INSERT_ORDER = "insert into order_details (id,customer_id,total) values (?,?,?)";
  private static final String INSERT_ORDER_ITEMS = "insert into order_item (order_id, movie_id) values (?,?);";
  private static final String SELECT_ALL_ORDERS = "select * from order_details;";

  public void insertOrder(Customer customer, List<Movie> cart, double cartTotal, int cardNumber) {
    try (java.sql.Connection connection = dao.Connection.getConnection();
         PreparedStatement ps = connection.prepareStatement(INSERT_ORDER);) {
      UUID uuid = UUID.randomUUID();
      ps.setString(1, String.valueOf(uuid));
      ps.setInt(2, customer.getId());
      ps.setDouble(3,cartTotal);
      ps.executeUpdate();
      Order order = selectOrder(String.valueOf(uuid));
      insertOrderItems(order, cart);
      Payment payment = new Payment(String.valueOf(uuid),cartTotal, "New Order", cardNumber);
      PaymentDAO PDAO = new PaymentDAO();
      PDAO.insertPayment(payment);
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  private void insertOrderItems(Order order, List<Movie> cart) {
    try (java.sql.Connection connection = dao.Connection.getConnection();
         PreparedStatement ps = connection.prepareStatement(INSERT_ORDER_ITEMS);) {
      cart.forEach((item) -> {
        try {
          ps.setString(1, order.getId());
          ps.setInt(2, item.getId());
          ps.executeUpdate();
        } catch (SQLException e) {
          throw new RuntimeException(e);
        }
      });
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public Order selectOrder(String id) {
    Order order = null;
    try (java.sql.Connection connection = dao.Connection.getConnection();
         PreparedStatement ps = connection.prepareStatement(SELECT_ORDER_BY_ID);) {
      ps.setString(1,id);
      ResultSet rs = ps.executeQuery();

      while (rs.next()) {
//        int id = rs.getInt("id");
        int customerID = rs.getInt("customer_id");
        double total= rs.getDouble("total");
        order = new Order(id,customerID,total);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return order;
  }

  // doesnt work yet
  public List<Order> selectAllOrders() {

    List<Order> orders = new ArrayList< >();
    try (java.sql.Connection connection = dao.Connection.getConnection();
         PreparedStatement ps = connection.prepareStatement(SELECT_ALL_ORDERS);) {
      ResultSet rs = ps.executeQuery();

      while (rs.next()) {
        int customerID = rs.getInt("customer_id");
        String id = rs.getString("id");
        double total = rs.getFloat("total");
        orders.add(new Order(id,customerID,total));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return orders;
  }

}

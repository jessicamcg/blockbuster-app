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
import java.util.Map;
import java.util.UUID;

public class OrderDAO {
  public OrderDAO() { }
  private static final String INSERT_ORDER = "insert into order_details (id,customer_id,total) values (?,?,?)";
  private static final String INSERT_ORDER_ITEMS = "insert into order_item (order_id, movie_id, quantity) values (?,?,?);";
  private static final String SELECT_ALL_ORDERS = "select * from order_details join payment on order_details.id=payment.order_id;";
  private static final String SELECT_ALL_ORDERS_BY_CUSTOMER_ID = "select * from order_details join payment on order_details.id=payment.order_id where customer_id=?;";
  private static final String SELECT_ORDER_BY_ID = "select * from order_details \n" +
          "join payment \n" +
          "on order_details.id=payment.order_id\n" +
          "join customer\n" +
          "on order_details.customer_id=customer.id\n" +
          "join order_item\n" +
          "on order_details.id=order_item.order_id\n" +
          "join movie\n" +
          "on order_item.movie_id=movie.id\n" +
          "where order_details.id=?";
  private static final String SELECT_ORDER_BY_CUSTOMER_ID = "select * from order_details \n" +
          "join payment \n" +
          "on order_details.id=payment.order_id\n" +
          "join customer\n" +
          "on order_details.customer_id=customer.id\n" +
          "join order_item\n" +
          "on order_details.id=order_item.order_id\n" +
          "join movie\n" +
          "on order_item.movie_id=movie.id\n" +
          "where order_details.customer_id=?";

  public void insertOrder(Customer customer, Map<Movie,Integer> cart, double cartTotal, String cardNumber) {
    try (java.sql.Connection connection = dao.Connection.getConnection();
         PreparedStatement ps = connection.prepareStatement(INSERT_ORDER)) {
      UUID uuid = UUID.randomUUID();
      ps.setString(1, String.valueOf(uuid));
      ps.setInt(2, customer.getId());
      ps.setDouble(3,cartTotal);
      ps.executeUpdate();
      Order order = selectOrderByID(String.valueOf(uuid));
      insertOrderItems(order, cart);
      Payment payment = new Payment(String.valueOf(uuid),cartTotal, "New Order", cardNumber);
      PaymentDAO paymentDAO = new PaymentDAO();
      paymentDAO.insertPayment(payment);
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public void insertOrderItems(Order order, Map<Movie,Integer> cart) {
    try (java.sql.Connection connection = dao.Connection.getConnection();
         PreparedStatement ps = connection.prepareStatement(INSERT_ORDER_ITEMS)) {
      cart.forEach((movie,quantity) -> {
        try {
          ps.setString(1, order.getId());
          ps.setInt(2, movie.getId());
          ps.setInt(3,quantity);
          ps.executeUpdate();
        } catch (SQLException e) {
          throw new RuntimeException(e);
        }
      });
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public Order selectOrderByID(String id) {
    Order order = null;
    try (java.sql.Connection connection = dao.Connection.getConnection();
         PreparedStatement ps = connection.prepareStatement(SELECT_ORDER_BY_ID)) {
      ps.setString(1,id);
      ResultSet rs = ps.executeQuery();
      int customerID = 0;
      double total = 0;
      String paymentStatus = null;
      String cardNumber = null;
      String firstName = null;
      String lastName = null;
      String phone = null;
      String address = null;
      String email = null;
      List<Movie> orderItems = new ArrayList<>();
      while (rs.next()) {
        customerID = rs.getInt("customer_id");
        total= rs.getDouble("total");
        paymentStatus = rs.getString("payment_status");
        cardNumber = rs.getString("card_number");
        firstName = rs.getString("first_name");
        lastName = rs.getString("last_name");
        phone = rs.getString("phone");
        address = rs.getString("address");
        email = rs.getString("email");
        MovieDAO movieDAO = new MovieDAO();
        int movieID = rs.getInt("movie_id");
        Movie movie = movieDAO.selectMovie(movieID);
        orderItems.add(movie);
      }
      int quantity = orderItems.size();
      order = new Order(id,customerID,total,paymentStatus,cardNumber,firstName,lastName,phone,address,email,quantity,orderItems);
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return order;
  }

  public Order selectOrderByCustomerID(int customerID) {
    Order order = null;
    try (java.sql.Connection connection = dao.Connection.getConnection();
         PreparedStatement ps = connection.prepareStatement(SELECT_ORDER_BY_CUSTOMER_ID)) {
      ps.setInt(1,customerID);
      ResultSet rs = ps.executeQuery();
      String orderID = null;
      double total = 0;
      String paymentStatus = null;
      String cardNumber = null;
      String firstName = null;
      String lastName = null;
      String phone = null;
      String address = null;
      String email = null;
      List<Movie> orderItems = new ArrayList<>();
      while (rs.next()) {
        orderID = rs.getString("order_id");
        total= rs.getDouble("total");
        paymentStatus = rs.getString("payment_status");
        cardNumber = rs.getString("card_number");
        firstName = rs.getString("first_name");
        lastName = rs.getString("last_name");
        phone = rs.getString("phone");
        address = rs.getString("address");
        email = rs.getString("email");
        MovieDAO MDAO = new MovieDAO();
        int movieID = rs.getInt("movie_id");
        Movie movie = MDAO.selectMovie(movieID);
        orderItems.add(movie);
      }
      int quantity = orderItems.size();
      order = new Order(orderID,customerID,total,paymentStatus,cardNumber,firstName,lastName,phone,address,email,quantity,orderItems);
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return order;
  }

  public List<Order> selectAllOrdersByCustomerID(int customerID) {
    List<Order> orders = new ArrayList< >();
    try (java.sql.Connection connection = dao.Connection.getConnection();
         PreparedStatement ps = connection.prepareStatement(SELECT_ALL_ORDERS_BY_CUSTOMER_ID)) {
      ps.setInt(1,customerID);

      ResultSet rs = ps.executeQuery();

      while (rs.next()) {
        String orderID = rs.getString("order_id");
        double total = (double) Math.round(rs.getFloat("total") * 100) / 100;
        String paymentStatus = rs.getString("payment_status");
        orders.add(new Order(orderID,customerID,total,paymentStatus));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return orders;
  }

  public List<Order> selectAllOrders() {
    List<Order> orders = new ArrayList< >();
    try (java.sql.Connection connection = dao.Connection.getConnection();
         PreparedStatement ps = connection.prepareStatement(SELECT_ALL_ORDERS)) {
      ResultSet rs = ps.executeQuery();

      while (rs.next()) {
        int customerID = rs.getInt("customer_id");
        String id = rs.getString("id");
        double total = (double) Math.round(rs.getFloat("total") * 100) / 100;
        String paymentStatus = rs.getString("payment_status");
        orders.add(new Order(id,customerID,total,paymentStatus));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return orders;
  }

}

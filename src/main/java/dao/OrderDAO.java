package dao;

import model.Category;
import model.Customer;
import model.Movie;
import model.Order;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class OrderDAO {
  public OrderDAO() { }
  private static final String SELECT_ORDER_BY_ID = "select * from order_details where id=?;";
  private static final String INSERT_ORDER = "insert into order_details (id,customer_id,total) values (?,?.?)";
  private static final String INSERT_ORDER_ITEMS = "insert into order_item (order_id, movie_id) values (?,?);";

  public void insertOrder(Customer customer, List<Movie> cart) {
    try (java.sql.Connection connection = dao.Connection.getConnection();
         PreparedStatement ps = connection.prepareStatement(INSERT_ORDER);) {
      UUID uuid = UUID.randomUUID();
      ps.setString(1, String.valueOf(uuid));
      ps.setInt(2, customer.getId());
      ps.setDouble(3,cart);
      ps.executeUpdate();
      Order order = selectOrder(String.valueOf(uuid));
      insertOrderItems(order, cart);
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
  public List<Movie> selectAllOrders() {

    List<Movie> movies = new ArrayList< >();
    try (java.sql.Connection connection = dao.Connection.getConnection();
         PreparedStatement ps = connection.prepareStatement(SELECT_ORDER_BY_ID);) {
      ResultSet rs = ps.executeQuery();

      while (rs.next()) {
        int id = rs.getInt("id");
        String title = rs.getString("title");
        String summary = rs.getString("summary");
        float price = rs.getFloat("price");
        int stock = rs.getInt("stock");
        String imageURL = rs.getString("image_url");
        int categoryID = rs.getInt("category_id");
        movies.add(new Movie(title, summary, price, stock, imageURL,categoryID));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return movies;
  }

}

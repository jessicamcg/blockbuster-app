package dao;

import model.Movie;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO {
  public OrderDAO() { }
  private static final String SELECT_ORDER_BY_ID = "select * from order_details where id=?;";
// doesnt work yet
  public List<Movie> getOrder() {

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

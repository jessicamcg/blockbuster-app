package dao;

import model.Movie;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MovieDAO {
  public MovieDAO() { }
  private static final String SELECT_ALL_MOVIES = "select * from movie;";
  private static final String SELECT_MOVIE_BY_ID = "select * from movie where id =?;";
  private static final String INSERT_MOVIE = "insert into movie (title,summary,price,stock,image_url,category_id) values (?,?,?,?,?,?);";
  private static final String DELETE_MOVIE = "delete from movie where id=?;";
  private static final String UPDATE_MOVIE_BY_ID = "update movie set title=?, summary=?, price=?, stock=?, image_url=?, category_id=? where id = ?;";
  private static final String SELECT_MOVIE_BY_NAME = "select * from movie where title LIKE ?" ;
  private static final String UPDATE_MOVIE_STOCK = "update movie set stock=? where id=?";

  public void insertMovie(Movie movie) throws SQLException {
    try (java.sql.Connection connection = dao.Connection.getConnection();
         PreparedStatement ps = connection.prepareStatement(INSERT_MOVIE);) {
      ps.setString(1, movie.getTitle());
      ps.setString(2, movie.getSummary());
      ps.setFloat(3, movie.getPrice());
      ps.setInt(4,movie.getStock());
      ps.setString(5,movie.getImageURL());
      ps.setInt(6,movie.getCategoryID());
      ps.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public boolean deleteMovie(int id) throws SQLException {
    boolean rowDeleted;
    try (java.sql.Connection connection = dao.Connection.getConnection();
         PreparedStatement ps = connection.prepareStatement(DELETE_MOVIE);) {
      ps.setInt(1, id);
      rowDeleted = ps.executeUpdate() > 0;
    }
    return rowDeleted;
  }

  public void updateMovieStock(Movie movie) throws SQLException {
    try (java.sql.Connection connection = dao.Connection.getConnection();
         PreparedStatement ps = connection.prepareStatement(UPDATE_MOVIE_STOCK);) {
      ps.setInt(1, movie.getStock()-1);
      ps.setInt(2, movie.getId());

      ps.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public void updateMovie(Movie movie) throws SQLException {
    try (java.sql.Connection connection = dao.Connection.getConnection();
         PreparedStatement ps = connection.prepareStatement(UPDATE_MOVIE_BY_ID);) {
      ps.setString(1, movie.getTitle());
      ps.setString(2, movie.getSummary());
      ps.setFloat(3, movie.getPrice());
      ps.setInt(4, movie.getStock());
      ps.setString(5, movie.getImageURL());
      ps.setInt(6, movie.getCategoryID());
      ps.setInt(7, movie.getId());

      ps.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public Movie selectMovie(int id) {
    Movie movie = null;
    try (java.sql.Connection connection = dao.Connection.getConnection();
         PreparedStatement ps = connection.prepareStatement(SELECT_MOVIE_BY_ID);) {
      ps.setInt(1, id);
      ResultSet rs = ps.executeQuery();

      while (rs.next()) {
        String title = rs.getString("title");
        String summary = rs.getString("summary");
        float price = rs.getFloat("price");
        int stock = rs.getInt("stock");
        String imageURL = rs.getString("image_url");
        int categoryID = rs.getInt("category_id");

        movie = new Movie(id,title,summary,price,stock,imageURL,categoryID);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return movie;
  }

  public List<Movie> selectMovieByName(String title) {
    List<Movie> movies = new ArrayList< >();
    try (java.sql.Connection connection = dao.Connection.getConnection();
         PreparedStatement ps = connection.prepareStatement(SELECT_MOVIE_BY_NAME);) {
      ps.setString(1, "%" + title + "%");
      ResultSet rs = ps.executeQuery();
      System.out.println("test" + ps);
      while (rs.next()) {
        int id = rs.getInt("id");
        String movieTitle = rs.getString("title");
        String summary = rs.getString("summary");
        float price = rs.getFloat("price");
        int stock = rs.getInt("stock");
        String imageURL = rs.getString("image_url");
        int categoryID = rs.getInt("category_id");
        movies.add(new Movie(id,movieTitle, summary, price, stock, imageURL,categoryID));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    System.out.println("console text" + movies);
    return movies;
  }

  public List<Movie> selectAllMovies() {
    List<Movie> movies = new ArrayList< >();
    try (java.sql.Connection connection = dao.Connection.getConnection();
         PreparedStatement ps = connection.prepareStatement(SELECT_ALL_MOVIES);) {
      ResultSet rs = ps.executeQuery();

      while (rs.next()) {
        int id = rs.getInt("id");
        String title = rs.getString("title");
        String summary = rs.getString("summary");
        float price = rs.getFloat("price");
        int stock = rs.getInt("stock");
        String imageURL = rs.getString("image_url");
        int categoryID = rs.getInt("category_id");
        movies.add(new Movie(id,title, summary, price, stock, imageURL,categoryID));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return movies;
  }
}

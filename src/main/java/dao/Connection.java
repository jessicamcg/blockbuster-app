package dao;

import lombok.NoArgsConstructor;

import java.sql.DriverManager;
import java.sql.SQLException;

public class Connection {
  private Connection() {
    throw new IllegalStateException("Utility class");
  }

  private static final String URL = "jdbc:mysql://blockbuster-app-server.mysql.database.azure.com:3306/blockbuster?useSSL=true";
  private static final String USER = "jessica";
  private static final String PW = "Capstone!";

  protected static java.sql.Connection getConnection() {
    java.sql.Connection connection = null;
    try {
      Class.forName("com.mysql.cj.jdbc.Driver");//MySQL database version 8.0
      connection = DriverManager.getConnection(URL, USER, PW);
    } catch (SQLException | ClassNotFoundException e) {
      e.printStackTrace();
    }
    return connection;
  }

}

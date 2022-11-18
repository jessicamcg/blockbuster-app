package dao;

import lombok.NoArgsConstructor;

import java.sql.DriverManager;
import java.sql.SQLException;

@NoArgsConstructor
public class Connection {
  private static String jdbcURL = "jdbc:mysql://blockbuster-app-server.mysql.database.azure.com:3306/blockbuster?useSSL=true";
  private static String jdbcUsername = "jessica";
  private static String jdbcPassword = "Capstone!";

  protected static java.sql.Connection getConnection() {
    java.sql.Connection connection = null;
    try {
      Class.forName("com.mysql.cj.jdbc.Driver");//MySQL database version 8.0
      connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
    } catch (SQLException e) {
      e.printStackTrace();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
    return connection;
  }

}

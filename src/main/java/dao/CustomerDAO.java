package dao;

import model.Admin;
import model.Customer;
import model.Movie;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO {
  public CustomerDAO() { }

  private static final String SELECT_ALL_CUSTOMERS = "select * from customer";
  private static final String SELECT_CUSTOMER_BY_EMAIL = "select * from customer where email=?;";
  private static final String INSERT_NEW_CUSTOMER = "insert into customer (first_name, last_name, phone, address, email, password) values (?,?,?,?,?,?)";

  public Customer getCustomer(String help) {

    Customer customer = null;
    try (java.sql.Connection connection = dao.Connection.getConnection();
         PreparedStatement ps = connection.prepareStatement(SELECT_CUSTOMER_BY_EMAIL);) {

      ps.setString(1, help);
      ResultSet rs = ps.executeQuery();

      while (rs.next()) {
        int id = rs.getInt("id");

        String firstName = rs.getString("first_name");
        String lastName = rs.getString("last_name");
        String phone = rs.getString("phone");
        String address = rs.getString("address");
        String email = rs.getString("email");
        String password = rs.getString("password");
        customer = new Customer(id, firstName, lastName, phone, address, email, password);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      return customer;
    }

  }

  public void insertCustomer(Customer customer) throws SQLException {
    try (java.sql.Connection connection = dao.Connection.getConnection();
         PreparedStatement ps = connection.prepareStatement(INSERT_NEW_CUSTOMER);) {
      ps.setString(1, customer.getFirstName());
      ps.setString(2, customer.getLastName());
      ps.setString(3, customer.getPhone());
      ps.setString(4, customer.getAddress());
      ps.setString(5, customer.getEmail());
      ps.setString(6, customer.getPassword());
      ps.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public List<Customer> selectAllCustomers() {
    List<Customer> customers = new ArrayList< >();
    try (java.sql.Connection connection = dao.Connection.getConnection();
         PreparedStatement ps = connection.prepareStatement(SELECT_ALL_CUSTOMERS);) {
      ResultSet rs = ps.executeQuery();

      while (rs.next()) {
        int id = rs.getInt("id");
        String firstName = rs.getString("first_name");
        String lastName = rs.getString("last_name");
        String phone = rs.getString("phone");
        String address = rs.getString("address");
        String email = rs.getString("email");
        customers.add(new Customer(id, firstName, lastName, phone, address, email));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return customers;
  }
}

package dao;

import model.Payment;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PaymentDAO {
  public PaymentDAO() {  }
  private static final String SELECT_PAYMENT_ID = "select * from payment where id=?;";
  private static final String INSERT_PAYMENT = "insert into payment (order_id,amount,payment_status, card_number) values (?,?,?,?)";
  private static final String UPDATE_PAYMENT_STATUS = "update payment set payment_status=? where order_id=?;";

  public void insertPayment(Payment payment) {
    try (java.sql.Connection connection = dao.Connection.getConnection();
         PreparedStatement ps = connection.prepareStatement(INSERT_PAYMENT);) {
      ps.setString(1, payment.getOrderID());
      ps.setDouble(2, payment.getAmount());
      ps.setString(3, payment.getPaymentStatus());
      ps.setString(4, payment.getCardNumber());
      ps.executeUpdate();
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  public void updatePaymentStatusById(String newPaymentStatus, String orderID) {
    try (java.sql.Connection connection = dao.Connection.getConnection();
         PreparedStatement ps = connection.prepareStatement(UPDATE_PAYMENT_STATUS)) {
      ps.setString(1, newPaymentStatus);
      ps.setString(2, orderID);
      ps.executeUpdate();
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }

  }

}

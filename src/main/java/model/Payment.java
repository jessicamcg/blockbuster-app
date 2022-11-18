package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Payment {
  private int id;
  private String orderID;
  private double amount;
  private String paymentStatus;
  private String cardNumber;

  public Payment(String orderID, double cartTotal, String paymentStatus, String cardNumber) {
    this.orderID = orderID;
    this.amount = cartTotal;
    this.paymentStatus = paymentStatus;
    this.cardNumber = cardNumber;
  }
}

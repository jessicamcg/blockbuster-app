package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
  private String id;
  private int customerID;
  private double total = 0;
  private String paymentStatus;
  private String cardNumber;
  private String firstName;
  private String lastName;
  private String phone;
  private String address;
  private String email;
  private int quantity;
  private List<Movie> orderItems;

  public Order(String id, int customerID, double total) {
    this.id = id;
    this.customerID = customerID;
    this.total = total;
  }

  public Order(String id, int customerID, double total, String paymentStatus) {
    this.id = id;
    this.customerID = customerID;
    this.total = total;
    this.paymentStatus = paymentStatus;
  }

}

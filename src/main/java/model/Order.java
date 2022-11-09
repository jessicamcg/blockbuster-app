package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
  private int id;
  private int customerID;
  private Set<Movie> orderItems;

  public Order(int id, int customerID) {
    this.id = id;
    this.customerID = customerID;
  }

}

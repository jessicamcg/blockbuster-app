package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
  private int id;
  private String firstName;
  private String lastName;
  private String phone;
  private String address;
  private String email;
  private String password;

  public Customer(String firstName, String lastName, String phone, String address, String email, String password) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.phone = phone;
    this.address = address;
    this.email = email;
    this.password = password;
  }

  public Customer(int id, String firstName, String lastName, String phone, String address, String email) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.phone = phone;
    this.address = address;
    this.email = email;
  }

  public int getId() {
    return id;
  }
}

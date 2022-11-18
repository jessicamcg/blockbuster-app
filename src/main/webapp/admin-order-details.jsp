<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <html>

    <head>
      <title>Blockbuster</title>
      <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
        integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    </head>

    <body>
      <header>
        <nav class="navbar navbar-expand-md navbar-dark" style="background-color: darkblue">
          <div>
            <a href="<%=request.getContextPath()%>/" class="navbar-brand text-warning font-weight-bold"> Blockbuster
            </a>
          </div>

          <ul class="navbar-nav">
            <li><a href="<%=request.getContextPath()%>/admincategories" class="nav-link">Categories</a></li>
            <li><a href="<%=request.getContextPath()%>/adminmovies" class="nav-link">Movies</a></li>
            <c:if test="${auth != null}">
              <li><a href="<%=request.getContextPath()%>/adminorders" class="nav-link">Orders</a></li>
              <li><a href="<%=request.getContextPath()%>/logout" class="nav-link">Logout</a></li>
            </c:if>
          </ul>
        </nav>
      </header>
      <br>

      <div class="row">
        <div class="container col-10">
          <h3 class="text-center">Order Details</h3>
          <hr>
          <br>
          <div class="container col-12 d-flex flex-wrap justify-content-center">
            <h4 class="font-weight-light m-0">Order ID:
              <c:out value="${order.id}" />
            </h4>
          </div>
          <br>
          <div class="row">
            <form action="adminupdateorder" method="post" class="container col-5 d-flex flex-wrap justify-content-center">
              <input type="hidden" name="id" value="<c:out value='${order.id}' />" />
              <h4 class="font-weight-light m-0">Status:</h4>
              <select type="text" class="form-control col-12" name="paymentStatus">
                <option value="New Order" <c:if test='${order.paymentStatus.equals("New Order")}'>
                  selected
                  </c:if>
                  >
                  New Order
                </option>
                <option value="Order Received" <c:if test='${order.paymentStatus.equals("Order Received")}'>
                  selected
                  </c:if>
                  >
                  Order Received
                </option>
                <option value="Shipped" <c:if test='${order.paymentStatus.equals("Shipped")}'>
                  selected
                  </c:if>
                  >
                  Shipped
                </option>
                <option value="Out for Delivery" <c:if test='${order.paymentStatus.equals("Out for Delivery")}'>
                  selected
                  </c:if>
                  >
                  Out for Delivery
                </option>
                <option value="Delivered" <c:if test='${order.paymentStatus.equals("Delivered")}'>
                  selected
                  </c:if>
                  >
                  Delivered
                </option>
              </select>
              <div class="justify-content-between">
                <br>
                <button type="submit" class="btn btn-success">Update</button>
              </div>
            </form>

            <div class="container col-12 d-flex flex-wrap justify-content-center">
              <div class="row">
                <div class="container col-6">
                  <div class="card-deck">
                    <div class="card my-1">
                      <p class="card-header">Shipping Address:</p>
                      <div class="p-2">
                        <h6 class="font-weight-light m-0">Name:
                          <c:out value="${order.firstName}" />
                          <c:out value="${order.lastName}" />
                        </h6>
                        <h6 class="font-weight-light m-0">Address:
                          <c:out value="${order.address}" />
                        </h6>
                        <h6 class="font-weight-light m-0">Email:
                          <c:out value="${order.email}" />
                        </h6>
                        <h6 class="font-weight-light m-0">Phone:
                          <c:out value="${order.phone}" />
                        </h6>
                      </div>
                    </div>
                  </div>
                </div>


                <div class="container col-6">
                  <ul class="list-group mb-3">
                    <div class="card my-1">
                      <p class="card-header">Details:</p>
                      <div class="p-2">
                        <c:forEach var="movie" items="${order.orderItems}">
                          <li class="list-group-item d-flex justify-content-between lh-sm">
                            <div>
                              <h6 class="my-0">
                                <c:out value='${movie.title}' />
                              </h6>
                            </div>
                            <span class="text-muted">
                              <c:out value='${movie.price}' />
                            </span>
                          </li>
                        </c:forEach>
                        <li class="list-group-item d-flex justify-content-between bg-light">
                        </li>
                        <li class="list-group-item d-flex justify-content-between">
                          <span>Total (USD)</span>
                          <strong>
                            <c:out value='${order.total}' />
                          </strong>
                        </li>
                  </ul>
                </div>
              </div>
            </div>
          </div>
        </div>
    </body>

    </html>
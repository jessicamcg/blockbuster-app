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
            <li><a href="<%=request.getContextPath()%>/movies" class="nav-link">Movies</a></li>
            <li><a href="<%=request.getContextPath()%>/cart" class="nav-link">Cart</a></li>
            <c:if test="${auth != null}">
              <li><a href="<%=request.getContextPath()%>/vieworders" class="nav-link">Orders</a></li>
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
          <div class="row">
            <div class="container col-10">
              <div class="card-deck">
                <div class="card my-1">
                  <p class="card-header">Order Summary</p>
                  <div class="p-2">
                    <h6 class="font-weight-light m-0">Status:
                      <c:out value="${order.paymentStatus}" />
                    </h6>
                    <h6 class="font-weight-light m-0">Order ID:
                      <c:out value="${order.id}" />
                    </h6>
                    <h6 class="font-weight-light m-0">Order Total: $
                      <c:out value="${order.total}" />
                    </h6>
                  </div>
                </div>
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
          </div>
          <br>

          <div class="card text-center">
            <div class="card-header">
              Ordered Movies
            </div>
            <div class="card-body">
              <div class="container col-12 d-flex flex-wrap justify-content-center">
                <c:forEach var="movie" items="${order.orderItems}">
                  <div class="card" style="width: 10rem;">
                    <img src=<c:out value='${movie.imageURL}' /> alt=<c:out value='${movie.title}' /> />
                    <div class="card-body">
                      <h5 class="card-title"><c:out value="${movie.title}" /></h5>
                      <p class="card-text">$<c:out value="${movie.price}" /></p>
                    </div>
                  </div>
                </c:forEach>
              </div>
            </div>
          </div>
        </div>
    </body>
    </html>

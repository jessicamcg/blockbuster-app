<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>

<head>
    <title>Blockbuster</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>

<body>
    <header>
      <nav class="navbar navbar-expand-md navbar-dark" style="background-color: darkblue">
          <div>
              <a href="<%=request.getContextPath()%>/" class="navbar-brand text-warning font-weight-bold"> Blockbuster </a>
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
            <h3 class="text-center">Your Cart</h3>
            <hr>
            <br>
            <div class="row">
              <c:if test="${cart.size() > 0}">
                <div class="container col-6">
                  <ul>
                    <c:forEach var="movie" items="${cart}">
                      <li class="list-group-item d-flex justify-content-between p-0 my-2">
                        <img class="float-left col-4 p-0" src=<c:out value='${movie.imageURL}'/> alt=<c:out value='${movie.title}'/> />
                        <div class="col-8 p-0 d-flex flex-column align-items-between">
                          <div class="card-body">
                            <h5 class="card-title"><c:out value="${movie.title}" /></h5>
                            <p class="card-text font-weight-light">$<c:out value="${movie.price}" /></p>
                          </div>
                          <div class="card-footer d-flex justify-content-center">
                            <a href="removefromcart?id=<c:out value='${movie.id}' />" class="btn btn-alert">Remove</a>
                          </div>
                        </div>
                      </li>
                    </c:forEach>
                  </ul>
                </div>
                <div class="col-4">
                  <h5>Cart Details</h5>
                  <p>Number of Items: <c:out value='${cart.size()}' /></p>
                  <p>Total: $<c:out value='${cartTotal}' /></p>
                  <a href="order" class="btn btn-primary">Place Order</a>
                </div>
              </div>
            </c:if>
            <c:if test="${cart.size() == 0}">
              <div class="mx-auto">
                <h4>Cart is empty!</h4>
                <h5>Visit <a href="<%=request.getContextPath()%>/movies">movies</a> to browse our catalogue</h5>
              </div>
            </c:if>
            <c:if test="${cart == null}">
              <div class="mx-auto">
                <h4>Cart is empty!</h4>
                <h5>Visit <a href="<%=request.getContextPath()%>/movies">movies</a> to browse our catalogue</h5>
              </div>
            </c:if>
        </div>
    </div>
</body>

</html>



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
    <div class="container col-9">
      <h3 class="text-center">Order Details</h3>
                  <hr>
      <div class="row justify-content-center py-3">
        <div class="col-md-5 col-lg-4 order-md-last">
          <h3 class="d-flex justify-content-between align-items-center mb-3">
            <span class="text-primary">Your cart</span>
            <span class="badge bg-primary rounded-pill"><c:out value="${cartQuantity}"/></span>
          </h3>
          <ul class="list-group mb-3">
          <c:forEach var="movie" items="${cart.keySet()}">
            <li class="list-group-item d-flex justify-content-between lh-sm">
              <div>
                <h6 class="my-0"><c:out value='${movie.title}'/></h6>
              </div>
              <div>
                <span class="text-muted"><c:out value='${movie.price}'/></span>
                <span class="badge bg-primary rounded-pill"><c:out value="${cart.get(movie)}" /></span>
              </div>
            </li>
          </c:forEach>
            <li class="list-group-item d-flex justify-content-between bg-light">
            </li>
            <li class="list-group-item d-flex justify-content-between">
              <span>Total (USD)</span>
              <strong><c:out value='${cartTotal}'/></strong>
            </li>
          </ul>
        </div>
        <div class="card col-6 p-3">
          <form action="placeorder" method="post">
            <div class="row">
              <div class="col-sm-6 py-3">
                <label for="firstName" class="form-label">First name</label>
                <input type="text" class="form-control" name="firstName" value="<c:out value='${auth.firstName}'/>" required>
              </div>

              <div class="col-sm-6 py-3">
                <label for="lastName" class="form-label">Last name</label>
                <input type="text" class="form-control" name="lastName" value="<c:out value='${auth.lastName}'/>" required>
              </div>

              <div class="col-sm-6 py-3">
                <label for="email" class="form-label">Email</label>
                <input type="email" class="form-control" name="email" value="<c:out value='${auth.email}'/>" required>
                <div class="invalid-feedback">
                  Please enter a valid email address for shipping updates.
                </div>
              </div>

               <div class="col-sm-6 py-3">
                <label for="phone" class="form-label">Phone</label>
                <input type="text" class="form-control" name="phone" value="<c:out value='${auth.phone}'/>" required>
              </div>

              <div class="col-12 py-3">
                <label for="address" class="form-label">Address</label>
                <input type="text" class="form-control" name="address" value="<c:out value='${auth.address}'/>" required>
              </div>

              <div class="col-md-12 py-3">
                <label for="cc-number" class="form-label">Credit Card Number</label>
                <input type="text" class="form-control" name="cc-number" placeholder="Enter Card Number" required>
              </div>

              <div class="col-12 d-flex justify-content-center pt-3">
                 <button class="btn btn-primary" onclick="openPopup()"> Confirm Order</button>


                  </div>
              </div>
          </form>
              <div class="confirmation" id="popup" style="width:400px; border-style:solid; border-color:blue; border-radius: 15px; position:absolute; top:15%; left:50%;
                            text-align:center; padding: 30px; color:blue; background:white; visibility:hidden ">
                              <img src="https://bit.ly/3GB8sBW" style="width: 25%; height:auto;">
                              <h2> Thank you!</h2>
                              <h4>Check your inbox for the details of your order!</h4>
                              <input class="btn btn-primary" type="submit" value="Home" >
        </div>
      </div>
    </div>
    <script>
        function openPopup(){
        document.getElementById("popup").style.visibility = "visible";
        }
    </script>
    </body>
    </html>
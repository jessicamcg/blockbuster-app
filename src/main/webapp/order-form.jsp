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
                <li><a href="<%=request.getContextPath()%>/logout" class="nav-link">Logout</a></li>
              </c:if>
            </ul>
        </nav>
    </header>
    <br>
    <div class="row">
        <div class="container col-md-6">
            <div class="card"
                <div class="card-body p-2">
                <h3 class="mx-auto mt-3">Order Details</h3>
                    <form class="p-3" action="placeorder" method="post">
                       <fieldset class="form-group">
                           <label>First Name: </label>
                           <input type="text" value="<c:out value='${auth.firstName}' />" class="form-control" name="firstname">
                       </fieldset>
                       <fieldset class="form-group">
                           <label>Last Name: </label>
                           <input type="text" value="<c:out value='${auth.lastName}' />" class="form-control" name="lastname">
                       </fieldset>
                       <fieldset class="form-group">
                           <label>Phone: </label>
                           <input type="text" value="<c:out value='${auth.phone}' />" class="form-control" name="phone">
                       </fieldset>
                       <fieldset class="form-group">
                           <label>Address: </label>
                           <input type="text" value="<c:out value='${auth.address}' />" class="form-control" name="address">
                       </fieldset>
                       <fieldset class="form-group">
                           <label>Email: </label>
                           <input type="text" value="<c:out value='${auth.email}' />" class="form-control" name="email">
                       </fieldset>

                       <fieldset class="form-group">
                           <label>Card Number: </label>
                           <input type="test" placeholder="Enter card number" class="form-control" name="cardNumber">
                       </fieldset>

                       <input class="btn btn-primary" type="submit" value="Place Order">
                    </form>
                </div>
            </div>
        </div>
    </div>
</body>

</html>



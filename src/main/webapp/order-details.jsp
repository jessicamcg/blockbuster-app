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
            <h3 class="text-center">Orders</h3>
            <hr>
            <br>
            <div class="row">
              <div class="container col-12">
                <div class="card my-1">
                  <div class="card-body d-flex justify-content-between ">
                    <div class="">
                      <p class="font-weight-light m-0">Status:</p>
                      <h6 class="card-text"><c:out value="${order.paymentStatus}" /></h6>
                    </div>
                    <div>
                      <h6 class="card-text"><c:out value="${order.id}" /></h6>
                      <p class="card-text">$<c:out value="${order.total}" /></p>
                    </div>
                    <div class="d-flex align-items-center">
                      <p>something</p>
                    </div>
                  </div>
                </div>
              </div>
            </div>
        </div>
    </div>
</body>

</html>



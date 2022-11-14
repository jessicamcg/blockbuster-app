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

    <div class="container col-9">
      <h3 class="text-center">Movie Details</h3>
      <hr>
      <br>
      <div class="row justify-content-center">
        <div class="col-4">
            <div class="card" style="width: 200px, max-height: 200px">
              <img src=<c:out value='${movie.imageURL}'/> alt=<c:out value='${movie.title}'/> />
            </div>
        </div>
        <div class="col-5 card p-0">
          <div class="card-body">
            <h5 class="card-title"><c:out value="${movie.title}"/> - $<c:out value="${movie.price}" /></h5>
            <p class="card-text"><c:out value="${movie.summary}" /></p>
          </div>
          <div class="card-footer d-flex justify-content-center">
            <a href="addtocart?id=<c:out value='${movie.id}' />" class="btn btn-primary">Add to Cart</a>
          </div>
        </div>
      </div>
    </div>
  </body>
  </html>

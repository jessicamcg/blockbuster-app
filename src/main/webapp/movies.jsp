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

        <div class="container col-9">
            <h3 class="text-center">Available Movies</h3>
            <hr>
            <div class="">
                <form class="d-flex justify-content-center align-items-center my-2" action="searchmovies" method="GET">
                  <label class="m-0 p-0" for="title">Search By Title: </label>
                  <input class="mx-2 col-4 form-control" type="text" name="title"/>
                  <input class="btn btn-primary btn-sm" type="submit" value="Search"/>
                </form>
            </div>
            <br>
            <div class="card-columns">
              <c:forEach var="movie" items="${movies}">
                <div class="card" style="width: 200px, max-height: 200px">
                  <img class="card-img-top" src=<c:out value='${movie.imageURL}'/> alt=<c:out value='${movie.title}'/> />
                  <div class="card-body">
                    <h5 class="card-title"><c:out value="${movie.title}" /></h5>
                    <p class="card-text text-truncate"><c:out value="${movie.summary}" /></p>
                    <p class="card-text font-weight-light">$<c:out value="${movie.price}" /></p>
                  </div>
                  <div class="card-footer">
                    <c:if test="${movie.stock <= 5 && movie.stock > 0}">
                      <div class="card text-white bg-warning text-center">Low in stock! Order soon</div>
                    </c:if>
                    <div class="d-flex justify-content-center">
                      <c:if test="${movie.stock == 0}">
                        <button href="" class="btn btn-primary" disabled>Not Available</button>
                      </c:if>
                      <c:if test="${movie.stock != 0}">
                        <a href="addtocart?id=<c:out value='${movie.id}' />" class="btn btn-primary">Add to Cart</a>
                      </c:if>
                      <a href="movie-details?id=<c:out value='${movie.id}' />"class="btn btn-primary" style="margin-left: 20px"> View Details</a>
                    </div>
                  </div>
                </div>
              </c:forEach>
            </div>
        </div>
    </div>
</body>

</html>



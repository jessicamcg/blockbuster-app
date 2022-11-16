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

        <div class="container col-9">
            <h3 class="text-center">Available Movies</h3>
            <hr>

            <div class="d-flex justify-content-around">
                                <form action="searchmovies" method="GET">
                                    Search By Title: <input type="text" name="title">
                                    <input type="submit" value="Search"></form>
                                    <% String title = (String)session.getAttribute("title"); %>
                            </div>
            <br>
            <div class="card-columns">
            <c:if test="${movies != null}">
              <c:forEach var="movie" items="${movies}">
                <div class="card" style="max-width: 200px, max-height: 200px">
                  <img class="card-img-top" src=<c:out value='${movie.imageURL}'/> alt=<c:out value='${movie.title}'/> />
                  <div class="card-body">
                    <h5 class="card-title"><c:out value="${movie.title}" /></h5>
                    <p class="card-text"><c:out value="${movie.summary}" /></p>
                    <p class="card-text font-weight-light">$<c:out value="${movie.price}" /></p>
                  </div>
                  <div class="card-footer d-flex justify-content-center">
                    <a href="addtocart?id=<c:out value='${movie.id}' />" class="btn btn-primary">Add to Cart</a>
                    <a href="movie-details?id=<c:out value='${movie.id}' />"class="btn btn-primary" style="margin-left: 20px"> View Details</a>
                  </div>
                </div>
              </c:forEach>
              </c:if>
              <c:if test="${movies.size() == 0}">
                <b>Movie Not Found</b>
              </c:if>
            </div>
        </div>
    </div>
</body>

</html>

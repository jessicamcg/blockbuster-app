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
              <li><a href="<%=request.getContextPath()%>/admincategories" class="nav-link">Categories</a></li>
              <li><a href="<%=request.getContextPath()%>/adminmovies" class="nav-link">Movies</a></li>
              <c:if test="${auth != null}">
                <li><a href="<%=request.getContextPath()%>/logout" class="nav-link">Logout</a></li>
              </c:if>
            </ul>
        </nav>
    </header>
    <br>

    <div class="row">

        <div class="container col-9">
            <h3 class="text-center">Users</h3>
            <hr>

            <br>
            <div class="card-columns">
              <c:forEach var="cust" items="${customer}">
                <div class="card p-0" style="">
                  <div class="card-body">
                    <p class="card-title"><c:out value="${cust.firstName}" /> <c:out value="${cust.lastName}" /></p>

                    <p class="card-text font-weight-light"> Address: <c:out value="${cust.address}" /></p>
                    <p class="card-text font-weight-light"> Phone: <c:out value="${cust.phone}" /></p>
                    <p class="card-text font-weight-light"> Email: <c:out value="${cust.email}" /></p>
                  </div>

                </div>
              </c:forEach>
            </div>
        </div>
    </div>
</body>

</html>
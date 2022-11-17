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

              <c:if test="${auth != null}">
                <li><a href="<%=request.getContextPath()%>/vieworders" class="nav-link">Orders</a></li>
                <li><a href="<%=request.getContextPath()%>/logout" class="nav-link">Logout</a></li>
              </c:if>
            </ul>
        </nav>
    </header>
    <br>
    <div class="row">
        <div class="container col-md-5">
            <div class="card"
                <div class="card-body p-2">
                  <a href="<%=request.getContextPath()%>/login">Click here to login</a>
                </div>
            </div>
        </div>
        <p style="text-align:center;"><img src="http://bit.ly/3EBjHJe" alt="Logo"></p>
    </div>
</body>

</html>



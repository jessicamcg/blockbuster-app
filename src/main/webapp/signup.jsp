<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Blockbuster</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<style type="text/css">
  .bgimg {
      background-image: url('https://live.staticflickr.com/65535/50717141847_72460eeca9_b.jpg');
      background-size: cover;
      min-height: 100vh;
  }
</style>
<body>
    <header>
        <nav class="navbar navbar-expand-md navbar-dark" style="background-color: darkblue">
            <div>
                <a href="<%=request.getContextPath()%>/" class="navbar-brand text-warning font-weight-bold"> Blockbuster </a>
            </div>
            <ul class="navbar-nav">
                <li><a href="<%=request.getContextPath()%>/movies" class="nav-link">Movies</a></li>
            </ul>
        </nav>
    </header>
    <div class="row align-content-center bgimg">
        <div class="container col-md-6 my-3">
            <div class="card shadow-lg"
                <div class="card-body p-2">
                <h3 class="mx-auto mt-3">Sign up</h3>
                    <form class="p-3" action="newcustomer" method="post">

                    <label>First Name: </label>
                    <input type="text" class="form-control" name="firstname" required>

                    <label>Last Name: </label>
                    <input type="text" class="form-control" name="lastname" required>

                    <label>Phone: </label>
                    <input type="text" class="form-control" name="phone" required>

                    <label>Address: </label>
                    <input type="text" class="form-control" name="address" required>

                    <label>Email: </label>
                    <input type="text" class="form-control" name="email" required>

                    <label>Password: </label>
                    <input type="password" class="form-control" name="password" required>
                    <br>
                    <input class="btn btn-primary" type="submit" value="Sign Up">
                    <a href="<%=request.getContextPath()%>/login" class="float-right">Already have an account? Login instead</a>
                    </form>
                </div>
            </div>
        </div>
    </div>
</body>

</html>



<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Blockbuster</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<style type="text/css">

  .hero {
      position: relative;
      height: 100vh;
      width: 100%;
      display: flex;
      align-items: center;
      justify-content: center;
  }

  .hero::before {
    content: "";
    background-image: url('https://ei.marketwatch.com/Multimedia/2020/05/21/Photos/ZH/MW-IG985_Movies_20200521113911_ZH.jpg?uuid=378c769c-9b79-11ea-bd9d-9c8e992d421e');
    background-size: cover;
    position: absolute;
    top: 0px;
    right: 0px;
    bottom: 0px;
    left: 0px;
    opacity: 0.65;
  }

  .hero-text {
    position: relative;
  }
</style>

<body>
    <header>
        <nav class="navbar navbar-expand-md navbar-dark" style="background-color: darkblue">
            <a class="navbar-brand" href="<%=request.getContextPath()%>/">
                <img src="http://bit.ly/3EBjHJe" width="55" height="30" alt="">
            </a>
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

    <div class="jumbotron jumbotron-fluid hero vh-100">
      <div class="container text-right hero-text">
        <h1 class="">Your favorite titles</h1>
        <p class="">just a click away</p>
      </div>
    </div>
    <div class="row justify-content-center mb-3">
      <div class="card-deck col-10">
        <div class="card shadow">
          <img src="https://ventsmagazine.com/wp-content/uploads/2019/12/best-strains-high-movie-watching.jpg" class="card-img-top" alt="...">
          <div class="card-body">
            <h5 class="card-title">Card title</h5>
            <p class="card-text">This is a wider card with supporting text below as a natural lead-in to additional content. This content is a little bit longer.</p>
          </div>
          <div class="card-footer">
            <small class="text-muted">Last updated 3 mins ago</small>
          </div>
        </div>
        <div class="card shadow">
          <img src="https://img.buzzfeed.com/buzzfeed-static/static/2015-09/4/19/campaign_images/webdr07/watching-movies-at-home-poll-2-23462-1441407636-3_dblbig.jpg" class="card-img-top" alt="...">
          <div class="card-body">
            <h5 class="card-title">Card title</h5>
            <p class="card-text">This card has supporting text below as a natural lead-in to additional content.</p>
          </div>
          <div class="card-footer">
            <small class="text-muted">Last updated 3 mins ago</small>
          </div>
        </div>
        <div class="card shadow">
          <img src="https://images.indianexpress.com/2020/04/binge-watching.jpg" class="card-img-top" alt="...">
          <div class="card-body">
            <h5 class="card-title">Card title</h5>
            <p class="card-text">This is a wider card with supporting text below as a natural lead-in to additional content. This card has even longer content than the first to show that equal height action.</p>
          </div>
          <div class="card-footer">
            <small class="text-muted">Last updated 3 mins ago</small>
          </div>
        </div>
      </div>
    </div>
    <br>
    <div class="row">
        <div class="container">
            <div class="card"
                <div class="card-body p-2">
                  <a href="<%=request.getContextPath()%>/login">Click here to login</a>
                </div>
            </div>
        </div>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-Fy6S3B9q64WdZWQUiU+q4/2Lc9npb8tCaSX9FK7E8HnRr0Jz8D6OP9dO5Vg3Q9ct" crossorigin="anonymous"></script>
</body>

</html>



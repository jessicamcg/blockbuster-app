package controllers;

import dao.*;
import model.*;

import javax.mail.*;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.Map;


@WebServlet("/")
public class AppServlet extends HttpServlet {
  private CustomerDAO custDAO;
  private CategoryDAO catDAO;
  private AdminDAO adminDAO;
  private MovieDAO movieDAO;
  private OrderDAO orderDAO;
  private PaymentDAO paymentDAO;

  @Override
  public void init() {
    custDAO = new CustomerDAO();
    catDAO = new CategoryDAO();
    adminDAO = new AdminDAO();
    movieDAO = new MovieDAO();
    orderDAO = new OrderDAO();
    paymentDAO = new PaymentDAO();
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    doGet(request, response);
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    String action = request.getServletPath();
    System.out.println(action);
    try {
      switch (action) {
        case "/login":
          renderLogin(request, response);
          break;
        case "/signup":
          renderSignup(request, response);
          break;
        case "/auth" :
          auth(request, response);
          break;
        case "/logout" :
          logout(request, response);
          break;
        case "/newcustomer" :
          insertNewCustomer(request, response);
          break;
        case "/movies" :
          renderMovies(request, response);
          break;
        case "/searchmovies" :
          searchMovies(request, response);
          break;
        case "/addtocart" :
          addToCart(request,response);
          break;
        case "/cart" :
          renderCart(request,response);
          break;
        case "/removefromcart" :
          removeFromCart(request,response);
          break;
        case "/admineditcategoryform":
          renderEditCategoryForm(request, response);
          break;
        case "/adminusers":
          renderAdminUsers(request, response);
          break;
        case "/adminnewcategoryform":
          renderNewCategoryForm(request, response);
          break;
        case "/admincategories":
          renderCategories(request, response);
          break;
        case "/admininsertcategory":
          insertCategory(request, response);
          break;
        case "/adminupdatecategory":
          updateCategory(request, response);
          break;
        case "/admindeletecategory":
          deleteCategory(request,response);
          break;
        case "/adminmovies":
          renderAdminMovies(request, response);
          break;
        case "/admininsertmovie":
          insertMovie(request, response);
          break;
        case "/adminnewmovieform":
          renderNewMovieForm(request, response);
          break;
        case "/admineditmovieform":
          renderEditMovieForm(request, response);
          break;
        case "/adminupdatemovie":
          updateMovie(request, response);
          break;
        case "/admindeletemovie":
          deleteMovie(request, response);
          break;
        case "/adminorders":
          renderAdminOrders(request, response);
          break;
        case "/order" :
          renderOrderForm(request,response);
          break;
        case "/placeorder" :
          placeOrder(request,response);
          break;
        case "/vieworders" :
          renderOrders(request,response);
          break;
        case "/adminorderdetails" :
          renderAdminOrderDetails(request,response);
          break;
        case "/adminupdateorder" :
          updateOrder(request,response);
          break;
        case "/orderdetails" :
          renderOrderDetails(request,response);
          break;
        case "/movie-details" :
          renderMovieDetails(request,response);
          break;
        default:
          renderHome(request, response);
          break;
      }
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  private void updateOrder(HttpServletRequest request, HttpServletResponse response) {
    HttpSession session=request.getSession();
    String paymentStatus = request.getParameter("paymentStatus");
    String orderID = request.getParameter("id");
    try {
      paymentDAO.updatePaymentStatusById(paymentStatus,orderID);
      Order order = orderDAO.selectOrderByID(orderID);
      session.setAttribute("order",order);
      response.sendRedirect("admin-order-details.jsp");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private void renderOrderDetails(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    HttpSession session=request.getSession();
    if (session.getAttribute("auth") instanceof Customer) {
      String id = request.getParameter("id");
      Order order = orderDAO.selectOrderByID(id);
      request.setAttribute("order", order);
      RequestDispatcher dispatcher = request.getRequestDispatcher("order-details.jsp");
      dispatcher.forward(request, response);

    } else {
      response.sendRedirect("index.jsp");
    }}

  private void renderAdminOrderDetails(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    HttpSession session=request.getSession();
    if (session.getAttribute("auth") instanceof Admin) {
      String id = request.getParameter("id");
      Order order = orderDAO.selectOrderByID(id);
      request.setAttribute("order", order);
      RequestDispatcher dispatcher = request.getRequestDispatcher("admin-order-details.jsp");
      dispatcher.forward(request, response);
    } else {
      response.sendRedirect("index.jsp");
    }
  }

  private void renderAdminOrders(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    HttpSession session=request.getSession();
    if (session.getAttribute("auth") instanceof Admin) {
      List<Order> orders = orderDAO.selectAllOrders();
      request.setAttribute("orders", orders);
      RequestDispatcher dispatcher = request.getRequestDispatcher("admin-orders.jsp");
      dispatcher.forward(request, response);
    } else {
      response.sendRedirect("index.jsp");
    }
  }

  private void renderOrders(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    HttpSession session=request.getSession();
    if (session.getAttribute("auth") instanceof Customer) {
      int customerID = ((Customer) session.getAttribute("auth")).getId();
      List<Order> orders = orderDAO.selectAllOrdersByCustomerID(customerID);
      request.setAttribute("orders", orders);
      RequestDispatcher dispatcher = request.getRequestDispatcher("order-view.jsp");
      dispatcher.forward(request, response);

    } else {
      response.sendRedirect("index.jsp");
    }  }

  private void renderMovieDetails(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    HttpSession session = request.getSession();
    int id = Integer.parseInt(request.getParameter("id"));
    if (session.getAttribute("auth") == null) {
      renderLogin(request, response);
    } else {
      Movie movies = movieDAO.selectMovie(id);
      request.setAttribute("movie", movies);
      RequestDispatcher dispatcher = request.getRequestDispatcher("movie-details.jsp");
      dispatcher.forward(request, response);
    }
  }

  private void renderOrderForm(HttpServletRequest request, HttpServletResponse response) throws IOException {
    response.sendRedirect("order-form.jsp");
  }

  private void renderAdminUsers(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    HttpSession session = request.getSession();
    if (session.getAttribute("auth") instanceof Admin) {
      if (session.getAttribute("auth") == null) {
        renderLogin(request, response);
      } else {
        List<Customer> customers = custDAO.selectAllCustomers();
        request.setAttribute("customer", customers);
        RequestDispatcher dispatcher = request.getRequestDispatcher("admin-users.jsp");
        dispatcher.forward(request, response);
      }
    } else {
      response.sendRedirect("index.jsp");
    }
  }

  private void searchMovies(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    HttpSession session=request.getSession();
    String name = request.getParameter("title");
    List<Movie> movies = movieDAO.selectMovieByName(name);
    request.setAttribute("movies", movies);
    RequestDispatcher dispatcher;
    if (session.getAttribute("auth") instanceof Admin) {
      dispatcher = request.getRequestDispatcher("admin-movies.jsp");
    } else {
      dispatcher = request.getRequestDispatcher("movies.jsp");
    }
    dispatcher.forward(request, response);
  }
  
  private void removeFromCart(HttpServletRequest request, HttpServletResponse response) throws IOException {
    int movieID = Integer.parseInt(request.getParameter("id"));
    Movie movieToRemove = movieDAO.selectMovie(movieID);
    HttpSession session=request.getSession();
    Map<Movie,Integer> cartItems = (Map<Movie, Integer>) session.getAttribute("cart");
    double moviePrice = (double) Math.round(((movieToRemove.getPrice()*((double) cartItems.get(movieToRemove))) * 100)) / 100;
    cartItems.remove(movieToRemove);
    double cartTotal = (double) Math.round((double) session.getAttribute("cartTotal") * 100) / 100;
    double newCartTotal = cartTotal - moviePrice;
    List<Movie> cartMovies = new ArrayList<>();
    final int[] cartQuantity = {0};
    cartItems.forEach( (movie,quantity) -> {
      cartMovies.add(movie);
      cartQuantity[0] += quantity;
    });
    session.setAttribute("cartQuantity", cartQuantity[0]);
    session.setAttribute("cartTotal", newCartTotal);
    response.sendRedirect("cart");
  }

  private void placeOrder(HttpServletRequest request, HttpServletResponse response) throws IOException {
    String cardNumber = request.getParameter("cc-number");
    HttpSession session=request.getSession();
    Customer customer = (Customer) session.getAttribute("auth");
    Map<Movie,Integer> cartItems = (Map<Movie, Integer>) session.getAttribute("cart");
    double cartTotal = (double) session.getAttribute("cartTotal");
    orderDAO.insertOrder(customer, cartItems, cartTotal,cardNumber);
    cartItems.forEach((movie,quantity) -> {
      try {

        movieDAO.updateMovieStock(movie, quantity);
        if (movie.getStock() < 5){
          String to = "jessica@admin.com";
          String from = "order@blockbuster.com";
          final String username = "ae00cd271c09de";
          final String pass = "72dcf21228e5fa";
          String host = "smtp.mailtrap.io";
          Properties props = new Properties();
          props.put("mail.smtp.auth", "true");
          props.put("mail.smtp.starttls.enable", "true");
          props.put("mail.smtp.host", host);
          props.put("mail.smtp.port", "2525");
          props.put("mail.smtp.connectiontimeout", "t1");
          props.put("mail.smtp.timeout", "t2");
          props.put("mail.smtp.ssl.protocols", "TLSv1.2");

          Session ses = Session.getInstance(props,
                  new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                      return new PasswordAuthentication(username, pass);
                    }
                  });
          Message message = null;
          try {
            message = new MimeMessage(ses);
            message.setFrom(new InternetAddress(from));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(to));
            message.setSubject("Low Stock " + movie.getTitle());
            message.setContent("We are low in " + movie.getTitle(), "text/html");
            Transport.send(message);
          } catch (MessagingException e) {
            throw new RuntimeException(e);
          }
        }

      } catch (SQLException e) {
        throw new RuntimeException(e);
      }
    });
    session.setAttribute("cart",null);
    session.setAttribute("cartTotal",null);

    String to = customer.getEmail();
    String from = "order@blockbuster.com";
    final String username = "ae00cd271c09de";
    final String pass = "72dcf21228e5fa";
    String host = "smtp.mailtrap.io";
    Properties props = new Properties();
    props.put("mail.smtp.auth", "true");
    props.put("mail.smtp.starttls.enable", "true");
    props.put("mail.smtp.host", host);
    props.put("mail.smtp.port", "2525");
    props.put("mail.smtp.connectiontimeout", "t1");
    props.put("mail.smtp.timeout", "t2");
    props.put("mail.smtp.ssl.protocols", "TLSv1.2");

    Session ses = Session.getInstance(props,
            new Authenticator() {
              protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, pass);
              }
            });
    Message message = null;
    try {
      message = new MimeMessage(ses);
      message.setFrom(new InternetAddress(from));
      message.setRecipients(Message.RecipientType.TO,
              InternetAddress.parse(to));
      message.setSubject("Thank You For your order!!");
      message.setContent("We got your order.", "text/html");
      Transport.send(message);
    } catch (MessagingException e) {
      throw new RuntimeException(e);
    }
    response.sendRedirect("home");
  }

  private void renderAdminMovies(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    HttpSession session = request.getSession();
    if (session.getAttribute("auth") instanceof Admin) {
      List<Movie> movies = movieDAO.selectAllMovies();
      request.setAttribute("movies", movies);
      RequestDispatcher dispatcher = request.getRequestDispatcher("admin-movies.jsp");
      dispatcher.forward(request, response);
    } else {
      response.sendRedirect("index.jsp");
    }
  }

  private void renderNewMovieForm(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    HttpSession session=request.getSession();
    if (session.getAttribute("auth") instanceof Admin) {
        List<Category> cats = catDAO.selectAllCats();
        request.setAttribute("categories", cats);
        RequestDispatcher dispatcher = request.getRequestDispatcher("movie-form.jsp");
        dispatcher.forward(request, response);
      }
    else {
      response.sendRedirect("index.jsp");
    }
  }

  private void updateMovie(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
    int id = Integer.parseInt(request.getParameter("id"));
    String title = request.getParameter("title");
    String summary =request.getParameter("summary");
    float price = Float.parseFloat(request.getParameter("price"));
    int stock = Integer.parseInt(request.getParameter("stock"));
    String imageURL = request.getParameter("imageURL");
    int categoryID = Integer.parseInt(request.getParameter("categoryID")) ;
    Movie movie = new Movie(id,title,summary,price,stock,imageURL,categoryID);
    movieDAO.updateMovie(movie);
    response.sendRedirect("adminmovies");
  }

  private void deleteMovie(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
    int id = Integer.parseInt(request.getParameter("id"));
    movieDAO.deleteMovie(id);
    response.sendRedirect("adminmovies");
  }

  private void renderEditMovieForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    int id = Integer.parseInt(request.getParameter("id"));
    Movie existingMovie = movieDAO.selectMovie(id);
    request.setAttribute("movie", existingMovie);
    List<Category> cats = catDAO.selectAllCats();
    request.setAttribute("categories", cats);
    RequestDispatcher dispatcher = request.getRequestDispatcher("movie-form.jsp");
    dispatcher.forward(request, response);
  }

  private void deleteCategory(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
    int id = Integer.parseInt(request.getParameter("id"));
    catDAO.deleteCat(id);
    response.sendRedirect("admincategories");
  }

  private void updateCategory(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
    int id = Integer.parseInt(request.getParameter("id"));
    String name = request.getParameter("name");
    Category cat = new Category(id, name);
    catDAO.updateCat(cat);
    response.sendRedirect("admincategories");
  }

  private void renderNewCategoryForm(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    HttpSession session=request.getSession();
    if (session.getAttribute("auth") instanceof Admin) {
        RequestDispatcher dispatcher = request.getRequestDispatcher("category-form.jsp");
        dispatcher.forward(request, response);
    } else {
      response.sendRedirect("index.jsp");
    }
  }

  private void renderEditCategoryForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    int id = Integer.parseInt(request.getParameter("id"));
    Category existingCat = catDAO.selectCat(id);
    request.setAttribute("category", existingCat);
    RequestDispatcher dispatcher = request.getRequestDispatcher("category-form.jsp");
    dispatcher.forward(request, response);
  }

  private void renderCart(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    HttpSession session=request.getSession();
    if (session.getAttribute("auth") == null) {
      renderLogin(request, response);
    } else {
      Map<Movie,Integer> cart = (Map<Movie, Integer>) session.getAttribute("cart");
      RequestDispatcher dispatcher = request.getRequestDispatcher("cart.jsp");
      dispatcher.forward(request, response);
    }
  }

  private void addToCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    int movieID = Integer.parseInt(request.getParameter("id"));
    Movie movieToAdd = movieDAO.selectMovie(movieID);
    HttpSession session=request.getSession();
    if (session.getAttribute("cart") == null) {
      List<Movie> cartMovies = new ArrayList<>();
      cartMovies.add(movieToAdd);
      double cartTotal = (double) Math.round(cartMovies.stream().mapToDouble(Movie::getPrice).sum() * 100) / 100;
      Map<Movie, Integer> cartItems = new HashMap<>();
      cartItems.put(movieToAdd, 1);
      session.setAttribute("cartQuantity",1);
      session.setAttribute("cartTotal", cartTotal);
      session.setAttribute("cart", cartItems);
    } else {
      Map<Movie, Integer> cartItems = (Map<Movie, Integer>) session.getAttribute("cart");

      if (cartItems.containsKey(movieToAdd)) {
        if (movieToAdd.getStock() <= cartItems.get(movieToAdd)+1) {
          System.out.println(movieToAdd.getStock());
          PrintWriter pw=response.getWriter();
          pw.print("<div class=\"alert alert-danger\" role=\"alert\">\n" +
                  "  Error adding movie to cart: Not enough in stock\n" +
                  "</div>");
          RequestDispatcher rd =request.getRequestDispatcher("index.jsp");
          rd.include(request, response);
          return;
        }
        cartItems.put(movieToAdd, cartItems.get(movieToAdd)+1);
      } else {
        cartItems.put(movieToAdd, 1);
      }

      List<Movie> cartMovies = new ArrayList<>();
      final int[] cartQuantity = {0};
      cartItems.forEach( (movie,quantity) -> {
        cartMovies.add(movie);
        cartQuantity[0] += quantity;
      });
      final double[] cartTotal = {0.00};
      cartItems.forEach((movie, quantity) -> {
        cartTotal[0] += movie.getPrice()*quantity;
      });
      double cartTotalAdjusted = (double) Math.round(cartTotal[0] * 100) / 100;
      session.setAttribute("cartQuantity", cartQuantity[0]);
      session.setAttribute("cartTotal", cartTotalAdjusted);
      session.setAttribute("cart", cartItems);
    }
    response.sendRedirect("cart");
  }

  private void renderMovies(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    HttpSession session=request.getSession();
    if (session.getAttribute("auth") == null) {
      renderLogin(request, response);
    } else {
      List<Movie> movies = movieDAO.selectAllMovies();
      request.setAttribute("movies", movies);
      RequestDispatcher dispatcher = request.getRequestDispatcher("movies.jsp");
      dispatcher.forward(request, response);
    }
  }

  private void renderSignup(HttpServletRequest request, HttpServletResponse response) throws IOException {
    response.sendRedirect("signup.jsp");
  }

  private void renderLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
    response.sendRedirect("login.jsp");
  }

  private void renderHome(HttpServletRequest request, HttpServletResponse response) throws IOException {
    HttpSession session=request.getSession();
    if (session.getAttribute("auth") instanceof Admin) {
      response.sendRedirect("admin-dashboard.jsp");
    } else {
      response.sendRedirect("index.jsp");
    }
  }

  private void auth (HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {

    String email=request.getParameter("email");
    String password=request.getParameter("password");

    if (adminDAO.getAdmin(email) != null) {
      Admin admin = adminDAO.getAdmin(email);
      if(email.equals(admin.getEmail())&& password.equals(admin.getPassword())) {
        HttpSession session=request.getSession();
        session.setAttribute("auth", admin);
        response.sendRedirect("admin");
      } else {
        loginError(request,response);
      }
    } else if (custDAO.getCustomer(email) != null){
      Customer customer = custDAO.getCustomer(email);
      if (email.equals(customer.getEmail())&& password.equals(customer.getPassword())){
        HttpSession session=request.getSession();
        session.setAttribute("auth", customer);
        response.sendRedirect("movies");
      } else {
        loginError(request,response);
      }
    } else {
      loginError(request,response);
    }
  }

  private void logout(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    response.setContentType("text/html");
    PrintWriter pw=response.getWriter();

    HttpSession session=request.getSession();
    session.invalidate();
    pw.print("<div class=\"alert alert-success\" role=\"alert\">\n" +
            "  You are successfully logged out\n" +
            "</div>");
    RequestDispatcher rd=request.getRequestDispatcher("/index.jsp");
    rd.include(request, response);
  }

  private void loginError(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    response.setContentType("text/html");
    PrintWriter pw=response.getWriter();

    pw.print("<div class=\"alert alert-danger\" role=\"alert\">\n" +
            "  Invalid Email/Password\n" +
            "</div>");

    RequestDispatcher rd=request.getRequestDispatcher("/login.jsp");
    rd.include(request, response);
  }

  private void insertNewCustomer(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
    String firstName = request.getParameter("firstname");
    String lastName = request.getParameter("lastname");
    String phone = request.getParameter("phone");
    String address = request.getParameter("address");
    String email = request.getParameter("email");
    String password = request.getParameter("password");
    Customer newCustomer = new Customer(firstName,lastName,phone,address,email,password);
    custDAO.insertCustomer(newCustomer);

    String to = newCustomer.getEmail();
    String from = "welcome@blockbuster.com";
    final String username = "ae00cd271c09de";
    final String pass = "72dcf21228e5fa";
    String host = "smtp.mailtrap.io";
    Properties props = new Properties();
    props.put("mail.smtp.auth", "true");
    props.put("mail.smtp.starttls.enable", "true");
    props.put("mail.smtp.host", host);
    props.put("mail.smtp.port", "2525");
    props.put("mail.smtp.connectiontimeout", "t1");
    props.put("mail.smtp.timeout", "t2");
    props.put("mail.smtp.ssl.protocols", "TLSv1.2");

    Session ses = Session.getInstance(props,
            new Authenticator() {
              protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, pass);
              }
            });

    Message message = null;
    try {
      message = new MimeMessage(ses);
      message.setFrom(new InternetAddress(from));
      message.setRecipients(Message.RecipientType.TO,
              InternetAddress.parse(to));
      message.setSubject("Welcome to Blockbuster");
      message.setContent("Thank you for registering. Now you can enjoy your movies.", "text/html");
      Transport.send(message);
    } catch (MessagingException e) {
      throw new RuntimeException(e);
    }

    Customer customer = custDAO.getCustomer(email);
    HttpSession session=request.getSession();
    session.setAttribute("auth", customer);
    response.sendRedirect("movies");

  }

  private void renderCategories(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    HttpSession session=request.getSession();
    if (session.getAttribute("auth") instanceof Admin) {
      List<Category> cats = catDAO.selectAllCats();
      request.setAttribute("categories", cats);
      RequestDispatcher dispatcher = request.getRequestDispatcher("categories.jsp");
      dispatcher.forward(request, response);
    } else {
      response.sendRedirect("login");
    }
  }

  private void insertCategory(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
    String name = request.getParameter("name");
    Category newCat = new Category(name);
    catDAO.insertCategory(newCat);
    response.sendRedirect("admincategories");
  }

  private void insertMovie(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
    String title = request.getParameter("title");
    String summary =request.getParameter("summary");
    float price = Float.parseFloat(request.getParameter("price"));
    int stock = Integer.parseInt(request.getParameter("stock"));
    String imageURL = request.getParameter("imageURL");
    int categoryID = Integer.parseInt(request.getParameter("categoryID")) ;
    Movie movie = new Movie(title,summary,price,stock,imageURL,categoryID);
    movieDAO.insertMovie(movie);
    response.sendRedirect("adminmovies");
  }

}

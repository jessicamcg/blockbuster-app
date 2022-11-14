package controllers;

import dao.*;
import model.*;

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
import java.util.List;

@WebServlet("/")
public class AppServlet extends HttpServlet {
  private CustomerDAO custDAO;
  private CategoryDAO catDAO;
  private AdminDAO ADAO;
  private MovieDAO MDAO;
  private OrderDAO ODAO;
  private PaymentDAO PDAO;

  public void init() {
    custDAO = new CustomerDAO();
    catDAO = new CategoryDAO();
    ADAO = new AdminDAO();
    MDAO = new MovieDAO();
    ODAO = new OrderDAO();
    PDAO = new PaymentDAO();
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    doGet(request, response);
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    String action = request.getServletPath();
    System.out.println(action);
    try {
      switch (action) {
        case "/":
          renderHome(request, response);
          break;
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
        case "/admin":
          renderDashboard(request, response);
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
        case "/searchadminmovies":
          searchAdminMovies(request, response);
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
        case "/order" :
          renderOrderForm(request,response);
          break;
        case "/placeorder" :
          placeOrder(request,response);
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

  private void renderMovieDetails(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    HttpSession session = request.getSession();
    int id = Integer.parseInt(request.getParameter("id"));
    if (session.getAttribute("auth") == null) {
      renderLogin(request, response);
    } else {
      Movie movies = MDAO.selectMovie(id);
      request.setAttribute("movie", movies);
      RequestDispatcher dispatcher = request.getRequestDispatcher("movie-details.jsp");
      dispatcher.forward(request, response);
    }
  }

  private void renderOrderForm(HttpServletRequest request, HttpServletResponse response) throws IOException {
    System.out.println(request.getSession().getAttribute("cartTotal"));
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


  private void searchAdminMovies(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    String name = request.getParameter("title");
    List<Movie> movies = MDAO.selectMovieByName(name);
    request.setAttribute("movies", movies);
    RequestDispatcher dispatcher = request.getRequestDispatcher("admin-search.jsp");
    dispatcher.forward(request, response);
  }
  
  private void searchMovies(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    String name = request.getParameter("title");
    List<Movie> movies = MDAO.selectMovieByName(name);
    System.out.println(movies);
    System.out.println(name);
    request.setAttribute("movies", movies);
    RequestDispatcher dispatcher = request.getRequestDispatcher("customer-search.jsp");
    dispatcher.forward(request, response);
  }
  
  private void removeFromCart(HttpServletRequest request, HttpServletResponse response) throws IOException {
    int movieID = Integer.parseInt(request.getParameter("id"));
    Movie movie = MDAO.selectMovie(movieID);
    HttpSession session=request.getSession();
    List<Movie> cartMovies = (List<Movie>) session.getAttribute("cart");
    cartMovies.remove(movie);
    response.sendRedirect("cart");
  }

  private void placeOrder(HttpServletRequest request, HttpServletResponse response) throws IOException {
    int cardNumber = Integer.parseInt(request.getParameter("cc-number"));
    HttpSession session=request.getSession();
    Customer customer = (Customer) session.getAttribute("auth");
    List<Movie> cartMovies = (List<Movie>) session.getAttribute("cart");
    double cartTotal = (double) session.getAttribute("cartTotal");
    ODAO.insertOrder(customer,cartMovies, cartTotal,cardNumber);
    cartMovies.forEach((movie) -> {
      try {
        MDAO.updateMovieStock(movie);
      } catch (SQLException e) {
        throw new RuntimeException(e);
      }
    });
    response.sendRedirect("home");

  }

  private void renderAdminMovies(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    HttpSession session = request.getSession();
    if (session.getAttribute("auth") instanceof Admin) {
      List<Movie> movies = MDAO.selectAllMovies();
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
      if (session == null) {
        renderLogin(request, response);
      } else {
        List<Category> cats = catDAO.selectAllCats();
        request.setAttribute("categories", cats);
        RequestDispatcher dispatcher = request.getRequestDispatcher("movie-form.jsp");
        dispatcher.forward(request, response);
      }
    } else {
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
    MDAO.updateMovie(movie);
    response.sendRedirect("adminmovies");
  }

  private void deleteMovie(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
    int id = Integer.parseInt(request.getParameter("id"));
    MDAO.deleteMovie(id);
    response.sendRedirect("admincategories");
  }

  private void renderEditMovieForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    int id = Integer.parseInt(request.getParameter("id"));
    Movie existingMovie = MDAO.selectMovie(id);
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
      if (session == null) {
        renderLogin(request, response);
      } else {
        RequestDispatcher dispatcher = request.getRequestDispatcher("category-form.jsp");
        dispatcher.forward(request, response);
      }
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
      RequestDispatcher dispatcher = request.getRequestDispatcher("cart.jsp");
      dispatcher.forward(request, response);
    }
  }

  private void addToCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    int movieID = Integer.parseInt(request.getParameter("id"));
    HttpSession session=request.getSession();
    if (session.getAttribute("cart") == null) {
      List<Movie> cartMovies = new ArrayList<>();
      cartMovies.add(MDAO.selectMovie(movieID));
      double cartTotal = (double) Math.round(cartMovies.stream().mapToDouble(Movie::getPrice).sum() * 100) / 100;
      session.setAttribute("cartTotal", cartTotal);
      session.setAttribute("cart", cartMovies);
    } else {
      List<Movie> cartMovies = (List<Movie>) session.getAttribute("cart");
      cartMovies.add(MDAO.selectMovie(movieID));
      double cartTotal = (double) Math.round(cartMovies.stream().mapToDouble(Movie::getPrice).sum() * 100) / 100;
      session.setAttribute("cartTotal", cartTotal);
    }
    response.sendRedirect("cart");
  }

  private void renderMovies(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    HttpSession session=request.getSession();
    if (session.getAttribute("auth") == null) {
      renderLogin(request, response);
    } else {
      List<Movie> movies = MDAO.selectAllMovies();
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
    response.sendRedirect("index.jsp");
  }

  private void auth (HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {

    String email=request.getParameter("email");
    String password=request.getParameter("password");

    if (ADAO.getAdmin(email) != null) {
      Admin admin = ADAO.getAdmin(email);
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
    int phone = Integer.parseInt(request.getParameter("phone"));
    String address = request.getParameter("address");
    String email = request.getParameter("email");
    String password = request.getParameter("password");
    Customer newCustomer = new Customer(firstName,lastName,phone,address,email,password);
    custDAO.insertCustomer(newCustomer);

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
    MDAO.insertMovie(movie);
    response.sendRedirect("adminmovies");
  }

  private void renderDashboard(HttpServletRequest request, HttpServletResponse response) throws IOException {
    HttpSession session=request.getSession();
    if (session.getAttribute("auth") instanceof Admin) {
      response.sendRedirect("admin-dashboard.jsp");
    } else {
      response.sendRedirect("index.jsp");
    }
  }
}
package test;

import controllers.AppServlet;
import dao.CustomerDAO;
import model.Customer;
import junit.framework.TestCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class LoginServletTest extends TestCase {
    @InjectMocks
    AppServlet AppServlet;
    @Mock
    CustomerDAO CustomerDAO;
    @Mock
    Customer Customer;
    @Mock
    HttpServletRequest request;
    @Mock
    HttpServletResponse response;
    @Mock
    RequestDispatcher dispatcher;
    @Mock
    HttpSession session;
    @BeforeEach
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        dispatcher = mock(RequestDispatcher.class);
        session = mock(HttpSession.class);
        CustomerDAO = mock(CustomerDAO.class);

        when(request.getSession(true)).thenReturn(session);
        when(request.getSession()).thenReturn(session);
    }

    @Test
    void testLoginServletTrue() throws IOException, ServletException, SQLException {
        when(request.getParameter("email")).thenReturn("dsikes313@gmail.com");
        when(request.getParameter("password")).thenReturn("BeastMode");

        AppServlet = new AppServlet();
        AppServlet.auth(request,response);

        verify(response,atLeast(1)).sendRedirect("Customer-search.jsp");
    }

//    @Test
//    void testLoginServletTrueClient() throws IOException, ServletException, SQLException {
//        when(request.getParameter("email")).thenReturn("jasonjohn333@yahoo.com");
//        when(request.getParameter("password")).thenReturn("jasonjj33");
//        when(Customer.getId()).thenReturn(Customer.getId());
//
//        AppServlet = new AppServlet();
//        AppServlet.auth(request,response);
//
//        verify(response,atLeast(1)).sendRedirect("index.jsp");
//    }
//
//    @Test
//    void testLoginServletFalse() throws IOException, ServletException, SQLException {
//        when(request.getParameter("email")).thenReturn("asf");
//        when(request.getParameter("password")).thenReturn("asfs");
//        when(Customer.getId()).thenReturn(Customer.getId());;
//
//        AppServlet = new AppServlet();
//        AppServlet.auth(request,response);
//
//        verify(response,atLeast(1)).sendRedirect("login.jsp");
//    }

}

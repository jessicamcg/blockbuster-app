package test;

import controllers.AppServlet;
import dao.AdminDAO;
import model.Admin;
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

import static org.mockito.Mockito.*;

class LoginServletTest extends TestCase {
    @InjectMocks
    AppServlet appServlet;
    @Mock
    AdminDAO adminDAO;
    @Mock
    Admin admin;
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
        adminDAO = mock(dao.AdminDAO.class);

        when(request.getSession()).thenReturn(session);
    }

    @Test
    void testLoginServletTrue() throws IOException, ServletException, SQLException {

        when(request.getParameter("email")).thenReturn("jessica@admin.com");
        when(request.getParameter("password")).thenReturn("jessica");

        appServlet = new AppServlet();
        appServlet.auth(request,response);


        verify(response,atLeast(1)).sendRedirect("admin");
    }

//    @Test
//    void testLoginServletTrueClient() throws IOException, ServletException, SQLException {
//        when(request.getParameter("email")).thenReturn("jessica@admin.com");
//        when(request.getParameter("password")).thenReturn("jessica");
//
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
//
//
//        AppServlet = new AppServlet();
//        AppServlet.auth(request,response);
//
//        verify(response,atLeast(1)).sendRedirect("login.jsp");
//    }

}

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

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;

import static org.mockito.Mockito.*;

class SignUpTest extends TestCase {
    @InjectMocks
    AppServlet appServlet;
    @Mock
    CustomerDAO customerDAO;
    @Mock
    HttpServletRequest request;
    @Mock
    HttpServletResponse response;
    @Mock
    RequestDispatcher dispatcher;
    @Mock
    HttpSession session;
    @Mock
    ServletContext servletContext;
    @Mock
    ServletConfig servletConfig;
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        dispatcher = mock(RequestDispatcher.class);
        session = mock(HttpSession.class);
        customerDAO = mock(CustomerDAO.class);
        servletContext = mock(ServletContext.class);
        servletConfig = mock(ServletConfig.class);

        when(request.getSession()).thenReturn(session);
        when(session.getServletContext()).thenReturn(servletContext);
        when(servletConfig.getServletContext()).thenReturn(servletContext);
        when(servletContext.getRequestDispatcher("/newcustomer")).thenReturn(dispatcher);

        when(request.getParameter("firstname")).thenReturn("John");
        when(request.getParameter("lastname")).thenReturn("Smith");
        when(request.getParameter("phone")).thenReturn("42932123");
        when(request.getParameter("address")).thenReturn("1824 Auburn Dr.");
        when(request.getParameter("email")).thenReturn("johnsmith@yahoo.com");
        when(request.getParameter("password")).thenReturn("johnsmith");
    }

    @Test
    public void testSignUpServlet() throws ServletException, IOException, SQLException, ClassNotFoundException {
        appServlet = new AppServlet();
        appServlet.init(servletConfig);
        appServlet.insertNewCustomer(request,response);

        verify(response,atLeast(1)).sendRedirect("movies");
    }


}

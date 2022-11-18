package test;

import controllers.AppServlet;
import dao.CustomerDAO;
import model.Customer;
import junit.framework.TestCase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static org.mockito.Mockito.*;

public class LogoutServletTest extends TestCase {
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
        public void setUp() {
            MockitoAnnotations.initMocks(this);
            request = mock(HttpServletRequest.class);
            response = mock(HttpServletResponse.class);
            dispatcher = mock(RequestDispatcher.class);
            session = mock(HttpSession.class);
            CustomerDAO = mock(dao.CustomerDAO.class);

            when(request.getSession()).thenReturn(session);
            when(session.getId()).thenReturn("123");
        }

        @Test
        public void testLogoutServletTrue() throws ServletException, IOException {
            when(session.getAttribute("auth")).thenReturn("true");

            AppServlet = new AppServlet();
            AppServlet.logout(request, response);

            verify(response,atLeast(1)).sendRedirect("login.jsp");
        }

        @Test
        public void testLogoutServletFalse() throws ServletException, IOException {
            when(session.getAttribute("auth")).thenReturn(false);

            AppServlet = new AppServlet();
            AppServlet.logout(request, response);

            verify(response,atLeast(1)).sendRedirect("index.jsp");
        }
}

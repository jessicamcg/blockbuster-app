package test;

import controllers.AppServlet;
import dao.CustomerDAO;
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
        @BeforeEach
        public void setUp() {
            MockitoAnnotations.initMocks(this);
            request = mock(HttpServletRequest.class);
            response = mock(HttpServletResponse.class);
            dispatcher = mock(RequestDispatcher.class);
            session = mock(HttpSession.class);
            customerDAO = mock(dao.CustomerDAO.class);

            when(request.getSession()).thenReturn(session);
            when(session.getId()).thenReturn("123");
        }

        @Test
        public void testLogoutServletTrue() throws ServletException, IOException {
            when(session.getAttribute("auth")).thenReturn("true");

            appServlet = new AppServlet();
            appServlet.logout(request, response);

            verify(response,atLeast(1)).sendRedirect("login.jsp");
        }

        @Test
        public void testLogoutServletFalse() throws ServletException, IOException {
            when(session.getAttribute("auth")).thenReturn(false);

            appServlet = new AppServlet();
            appServlet.logout(request, response);

            verify(response,atLeast(1)).sendRedirect("index.jsp");
        }
}

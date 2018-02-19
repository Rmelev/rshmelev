package ru.job4j.sepoperservlet;

import org.junit.Test;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;

/**
 * Log out servlet test.
 */
public class QuitServletTest {
    /**
     * path to jsp file.
     */
    private static final String PATH = "/WEB-INF/views/LoginView.jsp";
    /**
     * test.
     * @throws ServletException - exc.
     * @throws IOException - exc.
     */
    @Test
    public void whenInvokeDoGetThenQuiteServletRun() throws ServletException, IOException {
        QuitServlet servlet = new QuitServlet();
        servlet.init();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        HttpSession session = mock(HttpSession.class);
        when(request.getRequestDispatcher(PATH)).thenReturn(dispatcher);
        when(request.getSession()).thenReturn(session);

        servlet.doGet(request, response);

        verify(request).getRequestDispatcher(PATH);
        verify(session).invalidate();
        verify(request, times(1)).getSession();
        verify(dispatcher).forward(request, response);
    }

}
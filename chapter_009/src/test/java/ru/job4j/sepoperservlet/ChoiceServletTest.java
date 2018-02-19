package ru.job4j.sepoperservlet;

import org.junit.Test;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.never;

/**
 * Test choice servlet.
 */
public class ChoiceServletTest {
    /**
     * dispatch path.
     */
    private static final String PATH = "WEB-INF/views/choice.jsp";

    /**
     * test.
     * @throws ServletException - exc.
     * @throws IOException - exc.
     */
    @Test
    public void whenInvokeDoGetThenChoiceServletRun() throws ServletException, IOException {
        ChoiceServlet servlet = new ChoiceServlet();
        servlet.init();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        when(request.getRequestDispatcher(PATH)).thenReturn(dispatcher);

        servlet.doGet(request, response);

        verify(request).getRequestDispatcher(PATH);
        verify(request, never()).getSession();
        verify(dispatcher).forward(request, response);
    }
}
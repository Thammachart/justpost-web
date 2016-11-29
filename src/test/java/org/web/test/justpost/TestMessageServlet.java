package org.web.test.justpost;

import java.io.IOException;
import java.io.StringWriter;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.web.justpost.MessageServlet;

public class TestMessageServlet {

    @Test
    public void testGetRequest() throws IOException, ServletException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        StringWriter writer = new StringWriter();
        when(response.getWriter()).thenReturn(new PrintWriter(writer));

        MessageServlet servlet = new MessageServlet();
        servlet.doGet(request, response);

        verify(response).setContentType("text/html");
        verify(response).getWriter();

        assertEquals("Hello\n", writer.toString());
    }
}

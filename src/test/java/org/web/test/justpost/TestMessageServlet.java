package org.web.test.justpost;

import java.io.IOException;
import java.io.StringWriter;
import java.io.PrintWriter;
import java.util.Arrays;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.mockito.ArgumentMatchers.isA;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.justpost.entities.Message;
import org.justpost.adapters.repositories.MessageRepositoryInterface;

import org.web.justpost.MessageServlet;

public class TestMessageServlet {
    private HttpServletRequest request;
    private HttpServletResponse response;
    private MessageRepositoryInterface repository;
    private StringWriter responseBody;

    @Before
    public void setUp() throws IOException {
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        repository = mock(MessageRepositoryInterface.class);
        responseBody = new StringWriter();

        when(response.getWriter()).thenReturn(new PrintWriter(responseBody));
        when(repository.get(null)).thenReturn(Arrays.asList(new Message("T1", "M1"), new Message("T2", "M2")));
    }

    @Test
    public void testGetRequest() throws IOException, ServletException {
        when(request.getMethod()).thenReturn("GET");

        HttpServlet servlet = new MessageServlet(repository);
        servlet.service(request, response);

        verify(response).setContentType("application/json");
        verify(response).getWriter();
        verify(repository).get(null);

        StringWriter expectedResult = new StringWriter();
        Gson gson = new GsonBuilder().create();
        gson.toJson(Arrays.asList(new Message("T1", "M1"), new Message("T2", "M2")), expectedResult);
        expectedResult.close();

        assertEquals(expectedResult.toString(), responseBody.toString());
    }

    @Test
    public void testPostRequest() throws IOException, ServletException {
        when(request.getMethod()).thenReturn("POST");

        HttpServlet servlet = new MessageServlet(repository);
        servlet.service(request, response);

        verify(response).setContentType("text/plain");
        verify(response).getWriter();
        verify(repository).add(isA(Message.class));

        assertEquals("OK", responseBody.toString());
    }
}

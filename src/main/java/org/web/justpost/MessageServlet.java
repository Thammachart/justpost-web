package org.web.justpost;

import java.io.PrintWriter;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.justpost.entities.Message;
import org.justpost.adapters.repositories.MessageRepositoryInterface;

public class MessageServlet extends HttpServlet {
    private MessageRepositoryInterface repository;

    public MessageServlet(MessageRepositoryInterface repository) {
        this.repository = repository;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();

        Gson gson = new GsonBuilder().create();
        gson.toJson(repository.get(null), out);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/plain");
        String title = request.getParameter("title");
        String message = request.getParameter("message");

        repository.add(new Message(title, message));

        PrintWriter out = response.getWriter();
        out.print("OK");
    }
}

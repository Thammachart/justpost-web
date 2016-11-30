package org.web.justpost;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.web.justpost.repositories.MessageRepository;

@WebListener
public class Application implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent event) {
        ServletContext context = event.getServletContext();
        context.addServlet(
            "MessageServlet",
            new MessageServlet(new MessageRepository())).addMapping("/message");
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {}
}

package com.example.rislaba4;

import java.io.*;

import com.example.rislaba4.jms.Producer;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "messageServlet", value = "/message")
public class MessageServlet extends HttpServlet {

    @EJB
    private Producer producer;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if (request.getParameter("message") != null) {
            producer.produce(request.getParameter("message"));
        }

        getServletContext().getRequestDispatcher("/message.jsp").forward(request, response);
    }

}
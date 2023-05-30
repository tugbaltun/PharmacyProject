package com.example.pharmacyproject.web;

import com.example.pharmacyproject.connection.DatabaseConnection;
import com.example.pharmacyproject.dao.LoggerDaoImpl;
import com.example.pharmacyproject.model.Logger;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet(name = "LoggerServlet", value = "/logger-servlet")
public class LoggerServlet extends HttpServlet {

    private static final int serialVersionUID = 1;
    private LoggerDaoImpl loggerDao;
    DatabaseConnection connection = new DatabaseConnection();
    Connection con = connection.getConnection();

    public void init() {
        loggerDao = new LoggerDaoImpl(con);
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("admin-home-page.jsp");
        String value = request.getParameter("value");
        Logger logger = new Logger();
        logger.setValue(value);
        try {
            System.out.println("Logger value:"+value);
            loggerDao.create(logger);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

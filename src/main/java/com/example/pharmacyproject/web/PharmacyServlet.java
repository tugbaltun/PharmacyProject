package com.example.pharmacyproject.web;

import com.example.pharmacyproject.connection.DatabaseConnection;
import com.example.pharmacyproject.dao.BaseDaoImpl;
import com.example.pharmacyproject.dao.LoggerDaoImpl;
import com.example.pharmacyproject.dao.PharmacyDaoImpl;
import com.example.pharmacyproject.model.Logger;
import com.example.pharmacyproject.model.Pharmacy;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;


@WebServlet("/")
public class PharmacyServlet extends HttpServlet {
    private static final int serialVersionUID = 1;

    DatabaseConnection databaseConnection = new DatabaseConnection();
    PharmacyDaoImpl pharmacyDao = new PharmacyDaoImpl(databaseConnection.getConnection());
    LoggerDaoImpl loggerDao = new LoggerDaoImpl(databaseConnection.getConnection());
     BaseDaoImpl baseDaoImpl ;

    public void init() {
        pharmacyDao = new PharmacyDaoImpl(databaseConnection.getConnection());
        loggerDao = new LoggerDaoImpl(databaseConnection.getConnection());
        baseDaoImpl = new BaseDaoImpl(pharmacyDao);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();

        try {
            switch (action) {
                case "/new":
                    showNewForm(request, response);
                    break;
                case "/insert":
                    insertPharmacy(request, response);
                    break;
                case "/delete":
                    deletePharmacy(request, response);
                    break;
                case "/edit":
                    showEditForm(request, response);
                    break;
                case "/update":
                    updatePharmacy(request, response);
                    break;
                case "/logList":
                    logList(request, response);
                    break;
                default:
                    listPharmacy(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void listPharmacy(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<Pharmacy> listPharmacy = baseDaoImpl.getAll();
        request.setAttribute("listPharmacy", listPharmacy);

        String value = request.getParameter( "value");
        Logger logger = new Logger();
        logger.setValue(value);

        try {
            System.out.println("Logger value:"+value);
            loggerDao.create(logger);
            response.sendRedirect("admin-home-page.jsp");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("admin-home-page.jsp");
        dispatcher.forward(request, response);
    }

    private void logList(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("user-log-page.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("pharmacy-form.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Pharmacy existingUser = (Pharmacy) baseDaoImpl.getById(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("pharmacy-form.jsp");
        request.setAttribute("pharmacy", existingUser);
        dispatcher.forward(request, response);

    }

    private void insertPharmacy(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String name = request.getParameter("name");
        String phone_number = request.getParameter("phone_number");
        String address = request.getParameter("address");
        String working_hours = request.getParameter("working_hours");
        Pharmacy newUser = new Pharmacy(name, phone_number, address, working_hours);
        baseDaoImpl.create(newUser);
        response.sendRedirect("list");
    }

    private void updatePharmacy(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String phone_number = request.getParameter("phone_number");
        String address = request.getParameter("address");
        String working_hours = request.getParameter("working_hours");

        Pharmacy updatedPharmacy = new Pharmacy(id, name, phone_number, address, working_hours);
        baseDaoImpl.update(updatedPharmacy);
        response.sendRedirect("list");
    }

    private void deletePharmacy(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        baseDaoImpl.delete(id);
        response.sendRedirect("list");

    }

}
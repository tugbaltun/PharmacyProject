package com.example.pharmacyproject.web;

import com.example.pharmacyproject.connection.DatabaseConnection;
import com.example.pharmacyproject.dao.LoginDaoImpl;
import com.example.pharmacyproject.model.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Connection;

import static java.lang.System.out;

@WebServlet("/user-login")
public class LoginServlet extends HttpServlet {

    private static final int serialVersionUID = 1;
    private LoginDaoImpl loginDaoImpl;
    DatabaseConnection connection = new DatabaseConnection();
    Connection con = connection.getConnection();

    public void init() {
        loginDaoImpl = new LoginDaoImpl(con);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String identity_number = request.getParameter("identity_number");
        String password = request.getParameter("password");
        User user = new User();
        user.setIdentity_number(identity_number);
        user.setPassword(password);

        try {
            if (loginDaoImpl.validate(user).equals("admin")) {
                request.getSession().setAttribute("auth", user);
                response.sendRedirect("admin-home-page.jsp");
                out.println("User role:"+loginDaoImpl.validate(user));
            } else if(loginDaoImpl.validate(user).equals("user")){
                request.getSession().setAttribute("auth", user);
                out.println("User role:"+loginDaoImpl.validate(user));
                response.sendRedirect("user-home-page.jsp");
                //response.sendRedirect("user-home-page.jsp");
            }
            else {
                out.println("There is no user");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


}

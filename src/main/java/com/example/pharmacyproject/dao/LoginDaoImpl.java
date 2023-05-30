package com.example.pharmacyproject.dao;

import com.example.pharmacyproject.exception.BaseException;
import com.example.pharmacyproject.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDaoImpl {
    private Connection con;
    BaseException exception = new BaseException();
    private static final String SELECT_USER = "select * from users where identity_number = ? and password = ?";

    public LoginDaoImpl(Connection con) {
        this.con = con;
    }
    public String validate(User user) throws ClassNotFoundException {

        String role = null;
        try (// Create a statement using connection object
             PreparedStatement preparedStatement = con
                     .prepareStatement(SELECT_USER)) {
            preparedStatement.setString(1, user.getIdentity_number());
            preparedStatement.setString(2, user.getPassword());


            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                role = rs.getString("user_role");
            }
        } catch (SQLException e) {
            // process sql exception
            exception.printSQLException(e);
        }
        return role;
    }
}

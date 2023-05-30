package com.example.pharmacyproject.dao;

import com.example.pharmacyproject.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl {

    private Connection con;
    private static final String SELECT_ALL_USER = "select * from users";

    public UserDaoImpl(Connection con) {
        this.con = con;
    }

    public void getUser(int user_id) {
        try {
            String sql = "SELECT * FROM users WHERE id = ?";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setInt(1, 1);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                int id = result.getInt("id");
                String identity_number = result.getString("identity_number");
                String password = result.getString("password");
                String user_role = result.getString("user_role");
                System.out.println("Column 1: " + id);
                System.out.println("Column 2: " + identity_number);
                System.out.println("Column 3: " + password);
                System.out.println("Column 3: " + user_role);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAll() {
        List<User> users = new ArrayList<>();

        try( PreparedStatement preparedStatement = con.prepareStatement(SELECT_ALL_USER)){
            // Step 2:Create a statement using connection object
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                int id = rs.getInt("id");
                String identity_number = rs.getString("identity_number");
                String password = rs.getString("password");
                String user_role = rs.getString("user_role");
                users.add(new User(id, identity_number, password, user_role));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

}

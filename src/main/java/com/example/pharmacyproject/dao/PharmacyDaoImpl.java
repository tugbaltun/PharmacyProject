package com.example.pharmacyproject.dao;



import com.example.pharmacyproject.model.Pharmacy;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PharmacyDaoImpl implements BaseDao<Pharmacy>{

    private Connection connection;
    public PharmacyDaoImpl(Connection con) {
        this.connection = con;
    }

    private static final String INSERT_PHARMACY_SQL = "INSERT INTO pharmacy" + "  (name, phone_number, address, working_hours) VALUES "
            + " (?, ?, ?, ?);";

    private static final String SELECT_ALL_PHARMACY = "select * from pharmacy";
    private static final String DELETE_PHARMACY_SQL = "delete from pharmacy where id = ?;";
    private static final String UPDATE_PHARMACY_SQL = "update pharmacy set name = ?, phone_number = ?, address = ?, working_hours = ? where id = ?;";

    private static final String SELECT_PHARMACY = "select * from pharmacy where id = ?";
    @Override
    public void create(Pharmacy pharmacy) throws SQLException {
        System.out.println(INSERT_PHARMACY_SQL);
        // try-with-resource statement will auto close the connection.
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PHARMACY_SQL)) {
            preparedStatement.setString(1, pharmacy.getName());
            preparedStatement.setString(2, pharmacy.getPhone_number());
            preparedStatement.setString(3, pharmacy.getAddress());
            preparedStatement.setString(4, pharmacy.getWorking_hours());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
            System.out.println("Record created.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Pharmacy pharmacy) throws SQLException {
        System.out.print(pharmacy.getId()+"\t");
        System.out.print(pharmacy.getName()+"\t");
        System.out.print(pharmacy.getPhone_number()+"\t");
        System.out.print(pharmacy.getAddress()+"\t");
        System.out.print(pharmacy.getWorking_hours()+"\n");
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_PHARMACY_SQL);) {

            System.out.println("updated USer:"+statement);
            statement.setString(1, pharmacy.getName());
            statement.setString(2, pharmacy.getPhone_number());
            statement.setString(3, pharmacy.getAddress());
            statement.setString(4, pharmacy.getWorking_hours());
            statement.setInt(5, pharmacy.getId());
            statement.executeUpdate();
            System.out.println("Record updated.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) throws SQLException {
        try {
            PreparedStatement statement = connection.prepareStatement(DELETE_PHARMACY_SQL);
            statement.setInt(1, id);
            statement.executeUpdate();
            System.out.println("Record deleted.");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<Pharmacy> getAll() {
        List<Pharmacy> pharmacy = new ArrayList<>();

        try( PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_PHARMACY)){
             // Step 2:Create a statement using connection object
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String phone_number = rs.getString("phone_number");
                String address = rs.getString("address");
                String working_hours = rs.getString("working_hours");
                pharmacy.add(new Pharmacy(id, name, phone_number, address, working_hours));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pharmacy;
    }

    @Override
    public Pharmacy getById(int pharmacy_id) {
        Pharmacy pharmacy = new Pharmacy();
        try {

            PreparedStatement statement = connection.prepareStatement(SELECT_PHARMACY);
            statement.setInt(1, pharmacy_id);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                int id = result.getInt("id");
                String name = result.getString("name");
                String phone_number = result.getString("phone_number");
                String address = result.getString("address");
                String working_hours = result.getString("working_hours");
                pharmacy.setId(id);
                pharmacy.setName(name);
                pharmacy.setPhone_number(phone_number);
                pharmacy.setAddress(address);
                pharmacy.setWorking_hours(working_hours);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pharmacy;
    }

}

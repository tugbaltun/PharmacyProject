package com.example.pharmacyproject.dao;

import com.example.pharmacyproject.model.Logger;
import com.example.pharmacyproject.model.Pharmacy;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LoggerDaoImpl implements BaseDao<Logger> {
    private Connection connection;
    public LoggerDaoImpl(Connection con) {
        this.connection = con;
    }

    private static final String INSERT_LOGGER_SQL = "INSERT INTO logger  (value) VALUES  (?);";
    private static final String SELECT_ALL_LOGGER = "select * from logger";
    private static final String DELETE_LOGGER_SQL = "delete from logger where id = ?;";
    private static final String UPDATE_LOGGER_SQL = "update logger set value = ?;";
    private static final String SELECT_LOGGER = "select * from logger where id = ?";

    @Override
    public void create(Logger logger) throws SQLException {
        System.out.println(INSERT_LOGGER_SQL);
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_LOGGER_SQL)) {
            preparedStatement.setString(1, logger.getValue());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
            System.out.println("Record created.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Logger logger) throws SQLException {
        System.out.print(logger.getId()+"\t");
        System.out.print(logger.getValue()+"\t");
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_LOGGER_SQL);) {
            System.out.println("Updated log:"+statement);
            statement.setString(1, logger.getValue());
            statement.setInt(2, logger.getId());
            statement.executeUpdate();
            System.out.println("Record updated.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) throws SQLException {
        try {
            PreparedStatement statement = connection.prepareStatement(DELETE_LOGGER_SQL);
            statement.setInt(1, id);
            statement.executeUpdate();
            System.out.println("Record deleted.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Logger> getAll() {
        List<Logger> logger = new ArrayList<>();

        try( PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_LOGGER)){
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String value = rs.getString("value");
                logger.add(new Logger(id, value));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return logger;
    }

    @Override
    public Logger getById(int logger_id) {
        Logger logger = new Logger();
        try {

            PreparedStatement statement = connection.prepareStatement(SELECT_LOGGER);
            statement.setInt(1, logger_id);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                int id = result.getInt("id");
                String value = result.getString("value");
                logger.setId(id);
                logger.setValue(value);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return logger;
    }
}

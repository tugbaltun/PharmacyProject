package com.example.pharmacyproject.dao;

import com.example.pharmacyproject.model.Pharmacy;

import java.sql.SQLException;
import java.util.List;

public interface BaseDao<T> {

    void create(T t) throws SQLException;

    void update(T t) throws SQLException;


    void delete(int id) throws SQLException;
    List<T> getAll();

    T getById(int id);

}

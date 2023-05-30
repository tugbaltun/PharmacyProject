package com.example.pharmacyproject.dao;

import com.example.pharmacyproject.model.Pharmacy;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;


public class BaseDaoImpl<T> implements BaseDao<T> {

    private final BaseDao baseDao;
    private Connection con;
    public BaseDaoImpl(BaseDao baseDao) {
        this.baseDao = baseDao;
    }


    @Override
    public void create(T t) throws SQLException {
        baseDao.create(t);
    }

    @Override
    public void update(T t) throws SQLException{
        baseDao.update(t);
    }

    @Override
    public void delete(int id) throws SQLException {
        baseDao.delete(id);
    }

    @Override
    public List<T> getAll() {
        return baseDao.getAll();
    }

    @Override
    public T getById(int id) {
        return (T) baseDao.getById(id);
    }

}

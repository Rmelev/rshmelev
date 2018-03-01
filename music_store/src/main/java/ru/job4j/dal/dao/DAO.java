package ru.job4j.dal.dao;

import ru.job4j.dal.StoreDataSource;
import ru.job4j.models.Entity;

import java.sql.Connection;
import java.util.List;

public interface DAO<T extends Entity> {
    static final Connection conn = StoreDataSource.DATA_SOURCE.dbConnection();

    void create(T entity);

    List<T> getAll();

    T getById(int id);

    void edit(T entity);

    void delete(int id);
}

package ru.job4j.controllers;

import ru.job4j.dal.repository.StoreRepository;

import javax.servlet.http.HttpServlet;

public class UserServletBase extends HttpServlet {
    protected StoreRepository repository;

    public UserServletBase() {
        this(StoreRepository.INSTANCE);
    }
    public UserServletBase(StoreRepository repository) {
        this.repository = StoreRepository.INSTANCE;
    }
}

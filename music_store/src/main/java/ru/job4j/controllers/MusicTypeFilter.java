package ru.job4j.controllers;

import ru.job4j.dal.repository.StoreRepository;
import ru.job4j.models.User;

import javax.servlet.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MusicTypeFilter implements Filter {

    protected StoreRepository repository;

    public MusicTypeFilter() {
        this(StoreRepository.INSTANCE);
    }
    public MusicTypeFilter(StoreRepository repository) {
        this.repository = StoreRepository.INSTANCE;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {


    }

    @Override
    public void destroy() {
    }
}

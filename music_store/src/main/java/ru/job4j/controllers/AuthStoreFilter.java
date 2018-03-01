package ru.job4j.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AuthStoreFilter implements Filter {
    /**
     * Logger.
     */
    private static final Logger LOG = LoggerFactory.getLogger(AuthStoreFilter.class);

    /**
     * init.
     * @param filterConfig - filterConfig
     * @throws ServletException - exc.
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    /**
     * doFilter.
     * @param req - request.
     * @param resp - responce.
     * @param chain - filter chain.
     * @throws IOException - exc.
     * @throws ServletException - exc.
     */
    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        if (request.getRequestURI().contains("/logIn")) {
        } else {
            HttpSession session = request.getSession();
            synchronized (session) {
                if (session.getAttribute("login") == null) {
                    ((HttpServletResponse) resp).sendRedirect(String.format("%s/logIn", request.getContextPath()));
                    return;
                }
            }

        }
        chain.doFilter(req, resp);
    }

    /**
     * destroy.
     */
    @Override
    public void destroy() {

    }
}

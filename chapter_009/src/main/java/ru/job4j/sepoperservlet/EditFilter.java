//package ru.job4j.sepoperservlet;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import javax.servlet.*;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//import java.io.IOException;
//
//public class EditFilter implements Filter {
//    private static final Logger LOG = LoggerFactory.getLogger(EditFilter.class);
//
//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//
//    }
//
//    @Override
//    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
//        HttpServletRequest request = (HttpServletRequest) req;
//        HttpSession session = request.getSession();
//        if (session.getAttribute("role").equals("admin")) {
//            ((HttpServletResponse) resp).sendRedirect(String.format("%s/adminEdit", request.getContextPath()));
//        } else {
//            ((HttpServletResponse) resp).sendRedirect(String.format("%s/userEdit", request.getContextPath()));
//        }
//        chain.doFilter(req, resp);
//    }
//
//    @Override
//    public void destroy() {
//
//    }
//}

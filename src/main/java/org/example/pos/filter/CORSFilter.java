package org.example.pos.filter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "CORSFilter", urlPatterns = "/*")
public class CORSFilter extends HttpFilter {

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        /*if (req.getHeader("Origin")!=null && req.getHeader("Origin").contains("http://localhost")) {
            String origin = req.getHeader("Origin");
            System.out.println(origin);
            res.setHeader("Access-Control-Allow-Origin", origin);
            res.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
            res.setHeader("Access-Control-Allow-Headers", "Content-Type, Origin, Authorization, X-Requested-With, Access-Control-Request-Method, Access-Control-Request-Headers");
            res.setHeader("Access-Control-Allow-Credentials", "true");

        }*/
        System.out.println("cat");
        chain.doFilter(req, res);
    }
}

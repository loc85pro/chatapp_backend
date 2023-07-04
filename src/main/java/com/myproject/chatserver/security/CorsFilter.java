package com.myproject.chatserver.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class CorsFilter extends OncePerRequestFilter {
        @Override
        protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                        FilterChain filterChain)
                        throws ServletException, IOException {
                System.out.println("Fillter is working");
                System.out.println(request.getRequestURI());
                System.out.println(request.getMethod());
                System.out.println(request.getPathInfo());

                response.addHeader("Access-Control-Allow-Origin", "http://localhost:4200");
                response.addHeader("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT, PATCH, HEAD");
                response.addHeader("Access-Control-Allow-Headers",
                                "Origin, Accept, X-Requested-With, Content-Type,Access-Control-Request-Method, Access-Control-Request-Headers");
                response.addHeader("Access-Control-Expose-Headers",
                                "Access-Control-Allow-Origin, Access-Control-Allow-Credentials");
                response.addHeader("Access-Control-Allow-Credentials", "true");
                if ("OPTIONS".equals(request.getMethod())) {
                        response.setStatus(200);
                } else {
                        filterChain.doFilter(request, response);
                }
        }
}

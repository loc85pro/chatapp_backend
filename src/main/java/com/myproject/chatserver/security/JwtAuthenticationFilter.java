package com.myproject.chatserver.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    JwtProvider jwtProvider;
    @Autowired
    UserDetailService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers",
                "Content-Type, Accept, X-Requested-With, remember-me, Token");

        try {

            // ----------------------------

            System.out.println("Filtering" + request.getRequestURI());
            String token = getJwtFromRequest(request);
            System.out.println("This is request token: " + token);
            if (token != "" && jwtProvider.validateToken(token)) {
                String username = jwtProvider.getUsernameFromToken(token);
                UserDetails userDetail = userService.loadUserByUsername(username);
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetail,
                        null, userDetail.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
                filterChain.doFilter(request, response);
            } else
                throw new MalformedJwtException("");
        } catch (Exception ex) {
            response.sendError(401);
        }
    }

    private String getJwtFromRequest(HttpServletRequest request) {
        String token = request.getHeader("token");
        if (token != null && token.startsWith("Bearer "))
            return token.replace("Bearer ", "");
        else {
            Cookie[] myCookies = request.getCookies();
            for (Cookie cookie : myCookies) {
                System.out.println(cookie.getName());
                if (cookie.getName().equals("token"))
                    return cookie.getValue();
            }
        }
        return "";
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String requestPath = request.getServletPath();
        String ignoredURL[] = { "/api/login", "/api/login/", "/api/signup", "/api/signup",
                "/validation/user_existing", "/token/refresh", "/data", "/hello", };
        for (String path : ignoredURL) {
            if (path.equals(requestPath))
                return true;
        }
        return false;
    }
}

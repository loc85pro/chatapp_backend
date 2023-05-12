package com.myproject.chatserver.security;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class UserContext {
    static public Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

    static public String getUsername() {
        if (principal instanceof UserDetails)
            return ((UserDetails) principal).getUsername();
        else
            return principal.toString();
    }
}

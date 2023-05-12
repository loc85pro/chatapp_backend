package com.myproject.chatserver.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.myproject.chatserver.entity.UserEntity;
import com.myproject.chatserver.repository.UserRepository;

@Service
public class UserDetailService implements UserDetailsService {
    @Autowired
    UserRepository repo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = repo.getByUsername(username);
        if (user == null)
            throw new UsernameNotFoundException(username);
        return new CustomUserDetails(user);
    }
}

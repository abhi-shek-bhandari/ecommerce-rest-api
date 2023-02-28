package com.masai.demo.security;

import com.masai.demo.exception.UserException;
import com.masai.demo.model.User;
import com.masai.demo.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        //loading user from db by email

        User user = this.userDao.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("User Not Found with User Name "+username));

        return user;
    }
}

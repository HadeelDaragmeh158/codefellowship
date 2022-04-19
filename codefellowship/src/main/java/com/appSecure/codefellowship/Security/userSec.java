package com.appSecure.codefellowship.Security;


import com.appSecure.codefellowship.Repository.UserinputRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class userSec implements UserDetailsService {
    @Autowired
    UserinputRepository userinputRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userinputRepository.findByUsername(username);
    }
}


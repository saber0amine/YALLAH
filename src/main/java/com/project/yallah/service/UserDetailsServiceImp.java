package com.project.yallah.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.project.yallah.model.Users;
import com.project.yallah.repository.UsersRepository;

import java.util.Collection;
import java.util.Collections;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
@Service
public class UserDetailsServiceImp implements UserDetailsService{
    @Autowired
    private UsersRepository userRepository ;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Users user = userRepository.findByEmail(email);
        // returning userDetail object ( is a regular user )  ---> from this vd :  https://youtu.be/q3gT4198RKU
        return new org.springframework.security.core.userdetails.User(user.getName(),
                user.getPassword(),
                true, true, true, true,
                getAuthorities(user.getRole().name()));	}
    private Collection<? extends GrantedAuthority> getAuthorities(String role) {
        return Collections.singletonList(new SimpleGrantedAuthority(role));
    }

}
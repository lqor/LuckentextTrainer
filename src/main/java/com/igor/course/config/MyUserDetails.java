package com.igor.course.config;

import com.igor.course.entity.User;
import com.igor.course.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;


public class MyUserDetails implements UserDetails {
    @Autowired
    private UserService userService;
    private User user;

    public MyUserDetails(String s) {
        if (userService != null) {
            this.user = userService.get(s);
        }
    }

    public MyUserDetails() {
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        try {
            return Collections.singletonList(
                    new SimpleGrantedAuthority(userService.getRoleByUser(user.getUsername()).getAuthority())
            );
        } catch (Exception e) {
            e.printStackTrace();

            return null;
        }
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return !user.isExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return !user.isLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return user.isEnabled();
    }
}

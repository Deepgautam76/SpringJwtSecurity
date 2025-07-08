package com.jwt_security.JwtSpringSecurity.config;

import com.jwt_security.JwtSpringSecurity.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MyUserDetails implements UserDetails {
    private final String username;
    private final String  password;
    private final List<GrantedAuthority> roles=new ArrayList<>();
    public MyUserDetails(User user) {
        this.username=user.getUsername();
        this.password=user.getPassword();
        //Set the role array
        if(user.getRole()!=null && !user.getRole().isEmpty()){
            String[] rolesArray=user.getRole().split(",");
            for (String role:rolesArray){
                GrantedAuthority authority=new SimpleGrantedAuthority(role.trim().toUpperCase());
                roles.add(authority);
            }
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

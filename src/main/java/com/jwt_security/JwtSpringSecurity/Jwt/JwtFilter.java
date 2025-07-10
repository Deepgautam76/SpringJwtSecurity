package com.jwt_security.JwtSpringSecurity.Jwt;

import com.jwt_security.JwtSpringSecurity.config.MyUserDetailsService;
import com.jwt_security.JwtSpringSecurity.model.User;
import com.jwt_security.JwtSpringSecurity.repository.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
public class JwtFilter extends OncePerRequestFilter {
    @Autowired
    private JwtUtilities jwtUtilities;
    @Autowired
    private MyUserDetailsService myUserDetailsService;
    /**
     * In this method, apply two-step
     * Step 1: - get the header from request and check header has token or not extract the username
     * Step 2: - Here we check securityContext isEmpty after that set AuthToken into securityContext as authenticated
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
       String authHeader=request.getHeader("Authorization");
       String jwtToken=null;
       String username=null;

        // Step 1: Extract token
       if(authHeader!=null &&authHeader.startsWith("Bearer ")){
           jwtToken=authHeader.substring(7);
           username=jwtUtilities.extractUsername(jwtToken);
       }
        // Step 2: Validate and authenticate
       if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null){
           UserDetails userDetails=myUserDetailsService.loadUserByUsername(username);

           if(jwtUtilities.validateToken(jwtToken,userDetails)){
               UsernamePasswordAuthenticationToken authToken=
                       new UsernamePasswordAuthenticationToken(username,null,userDetails.getAuthorities());

               authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
               SecurityContextHolder.getContext().setAuthentication(authToken);
           }
       }
       filterChain.doFilter(request,response);
    }
}

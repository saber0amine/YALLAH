package com.project.yallah.security;

import com.nimbusds.jwt.JWT;
import com.project.yallah.config.SecurityConfig;
import com.project.yallah.service.UserDetailsServiceImp;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class JwtAuthenticationFilter extends OncePerRequestFilter {




    private TokenService tokenService;

 private UserDetailsServiceImp  userDetailsServiceImp  ;


    public JwtAuthenticationFilter(TokenService tokenService) {
        this.tokenService = tokenService;


    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String header = request.getHeader("Authorization");

        if (header != null && header.startsWith("Bearer ")) {
            String token = header.substring(7);
            try {
                 if (tokenService.validateToken(token)) {
                    JWT jwt =(com.nimbusds.jwt.JWT) tokenService.jwtDecoder(token);
                    UserDetails userDetails = userDetailsServiceImp.loadUserByUsername(jwt.getJWTClaimsSet().getStringClaim("email"));
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities());
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            } catch (Exception e) {
                 SecurityContextHolder.clearContext();
            }
        }

        filterChain.doFilter(request, response);
    }
}
package com.project.yallah.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.*;

@Configuration
 @EnableWebSecurity
public class SecurityConfig  {

 @Bean
 public SecurityFilterChain securityFilterChain(HttpSecurity http ) throws Exception{
  http.csrf(csrf->csrf.disable()).authorizeRequests(authorizeRequests ->
                  authorizeRequests
                          .requestMatchers("/**" ).permitAll()
                          .anyRequest().authenticated()
          ).oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt )
  .sessionManagement(session ->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // we dont need for session we will use jwt
          .httpBasic(withDefaults());
return http.build() ;
 }
}

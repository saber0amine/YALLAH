package com.project.yallah.config;


import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import com.project.yallah.model.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
 import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.*;

@Configuration
 @EnableWebSecurity
 public class SecurityConfig  {
 private final RsaKeyProperties rsaKeys;
 @Autowired
 public SecurityConfig(RsaKeyProperties rsaKeys) {
  this.rsaKeys = rsaKeys ;
 }
 @Bean
 public SecurityFilterChain securityFilterChain(HttpSecurity http ) throws Exception{
  http.csrf(csrf->csrf.disable()).authorizeHttpRequests(authorizeRequests ->
                  authorizeRequests
                          .requestMatchers("/" , "/register" , "/login" , "/swagger-ui/**"  , "/v3/api-docs" , "/swagger-ui/index.html/**"  ).permitAll()
                         // .requestMatchers("/book-activity").hasAnyAuthority("ROLE_ORGANISATEUR")
                          .anyRequest().authenticated()
          ).oauth2ResourceServer((oauth2) -> oauth2
                  .jwt(Customizer.withDefaults())
          )
  .sessionManagement(session ->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // we dont need for session we will use jwt
          .httpBasic(withDefaults());
return http.build() ;
 }


 @Bean
 public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)  throws Exception {
  return authenticationConfiguration.getAuthenticationManager();
 }




 @Bean
 JwtDecoder jwtDecoder() {
  return NimbusJwtDecoder.withPublicKey(rsaKeys.publicKey()).build();
 }

 @Bean
 JwtEncoder jwtEncoder() {
  JWK jwk = new RSAKey.Builder(rsaKeys.publicKey()).privateKey(rsaKeys.privateKey()).build();
     JWKSource<SecurityContext> jwks = new ImmutableJWKSet<>(new JWKSet(jwk));
     return new NimbusJwtEncoder(jwks);
 }

 @Bean
 public PasswordEncoder passwordEncoder() {
  return new BCryptPasswordEncoder() ;
 }

}

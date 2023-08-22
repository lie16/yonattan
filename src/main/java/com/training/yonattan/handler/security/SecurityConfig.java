package com.training.yonattan.handler.security;

import com.training.yonattan.handler.security.exception.UserAuthenticationErrorHandler;
import com.training.yonattan.handler.security.exception.UserForbiddenErrorHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {
  @Autowired
  private JwtFilter jwtFilter;

  private static final String[] ENDPOINTS_WHITELIST = {
          "/api/users/register",
          "/api/users/sign_in"
  };

  @Bean
  public AuthenticationManager authenticationManager(
      AuthenticationConfiguration authenticationConfiguration) throws Exception {
    return authenticationConfiguration.getAuthenticationManager();
  }

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http.authorizeHttpRequests(request -> request
          .requestMatchers(ENDPOINTS_WHITELIST).permitAll()
//          .requestMatchers("/api/users/register").permitAll()
//          .requestMatchers("/api/users/sign_in").permitAll()
          .anyRequest().authenticated())
    .csrf(AbstractHttpConfigurer::disable)
    .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .exceptionHandling(exception -> exception.authenticationEntryPoint(userAuthenticationErrorHandler())
                    .accessDeniedHandler(new UserForbiddenErrorHandler()));


    http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
    return http.build();
  }

  @Bean
  public AuthenticationEntryPoint userAuthenticationErrorHandler() {
    UserAuthenticationErrorHandler userAuthenticationErrorHandler =
            new UserAuthenticationErrorHandler();
    userAuthenticationErrorHandler.setRealmName("Basic Authentication");
    return userAuthenticationErrorHandler;
  }

}

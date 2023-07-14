package com.projecto.baseapi.config;

import com.projecto.baseapi.constant.Role;
import com.projecto.baseapi.service.UserService;
import com.projecto.baseapi.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

    @Autowired
    private UserServiceImpl userServiceImpl;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(userServiceImpl).passwordEncoder(passwordEncoder);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeHttpRequests((requests)-> requests
                        .requestMatchers(new String[]{"/api/v1/user/register","/swagger-ui","/v1/api-docs"})
                        .permitAll()
                        .requestMatchers("/api/v1/user/users")
                        .hasAuthority(Role.ADMIN.toString())
                        .anyRequest()
                        .authenticated()
                )
                .httpBasic();
        return http.build();
    }


}

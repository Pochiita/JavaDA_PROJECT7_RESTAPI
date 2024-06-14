package com.nnk.springboot.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;



@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    CustomUserDetailsService customUserDetailsService;

    /**
     * Configure SpringSecurity login system
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(request ->
                        request
                                .requestMatchers("/login","/register").anonymous() //Allowing only non-logged users to reach this page
                                .requestMatchers("/").permitAll() // Allow every user to reach homepage or login page
                                .requestMatchers("/bidList/**", "/rating/**", "/ruleName/**", "/trade/**", "/curvePoint/**", "/app/error", "/app-logout").hasAnyAuthority("ADMIN", "USER") // Allow every logged user that has 'USER' or 'ADMIN' role to reach every page related to the entities
                                .requestMatchers("/user/**", "/admin/**").hasAuthority("ADMIN") // Allowing some page for only "ADMIN" role
                                .anyRequest().authenticated()) // Any other request you need to be authenticated
                .formLogin(login ->
                        login
                                .defaultSuccessUrl("/bidList/list") // Redirect to /dashboard after successful login
                                .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                )
                .csrf().disable();
        return http.build();
    }

    /**
     * Method to return a password encoder
     * @return PasswordEncoder
     */
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http, BCryptPasswordEncoder bCryptPasswordEncoder) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(customUserDetailsService).passwordEncoder(bCryptPasswordEncoder);
        return authenticationManagerBuilder.build();
    }
}


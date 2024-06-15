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

     Creates a SecurityFilterChain for handling security configurations in the application.
     @param http the HttpSecurity object to configure security settings
     @return the SecurityFilterChain with specified security configurations
     @throws Exception if unable to set security configurations */
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
                        .logoutUrl("/app-logout")
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


    /**

     This method creates an AuthenticationManager bean and configures it with the provided HttpSecurity object and BCryptPasswordEncoder.
     It sets the userDetailsService with customUserDetailsService and passwordEncoder with bCryptPasswordEncoder.
     @param http the HttpSecurity object used to configure the AuthenticationManagerBuilder
     @param bCryptPasswordEncoder the BCryptPasswordEncoder used to encode passwords
     @return the configured AuthenticationManager object
     @throws Exception if an error occurs during the configuration process */
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http, BCryptPasswordEncoder bCryptPasswordEncoder) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(customUserDetailsService).passwordEncoder(bCryptPasswordEncoder);
        return authenticationManagerBuilder.build();
    }
}


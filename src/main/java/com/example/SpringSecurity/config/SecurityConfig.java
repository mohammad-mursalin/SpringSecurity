package com.example.SpringSecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

//        httpSecurity.csrf(customizer -> customizer.disable());   // disables the csrf
//        httpSecurity.authorizeHttpRequests(request -> request.anyRequest().authenticated());   //secures the page, now authentication is needed for acces
//        httpSecurity.formLogin(Customizer.withDefaults());   // enables the form
//        httpSecurity.httpBasic(Customizer.withDefaults());   // for rest api client
////        httpSecurity.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));  //creates a new session for every request. Now we need to disable the form action unless we form will appear all time. Now it will give a popup for authentication
//
//        return httpSecurity.build();

        //builder pattern

        return httpSecurity
                            .csrf(customizer -> customizer.disable())
                            .authorizeHttpRequests(request -> request.anyRequest().authenticated())
                            .formLogin(Customizer.withDefaults())
                            .httpBasic(Customizer.withDefaults())
                            .build();
    }
}

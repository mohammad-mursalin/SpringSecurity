package com.example.SpringSecurity.config;

import com.example.SpringSecurity.service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
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

//    @Bean
//    public UserDetailsService userDetailsService() {
//
//        UserDetails user1 = User
//                                .withDefaultPasswordEncoder()
//                                .username("jubayer")
//                                .password("j@123")
//                                .roles("USER")
//                                .build();
//
//        UserDetails user2 = User
//                                .withDefaultPasswordEncoder()
//                                .username("lamon")
//                                .password("l@123")
//                                .roles("USER")
//                                .build();
//
//        return new InMemoryUserDetailsManager(user1, user2);
//    }

    @Autowired
    private MyUserDetailsService userDetailsService;

    @Bean
    public AuthenticationProvider authenticationProvider() {

        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(new BCryptPasswordEncoder(12));
        provider.setUserDetailsService(userDetailsService);

        return provider;
    }
}

package com.binary.carDealer.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Lazy
    @Autowired
    MemberDetailsService memberDetailsService;

    @Lazy
    @Autowired
    JwtTokenFilter jwtTokenFilter;

    @Bean
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http, JwtTokenFilter jwtTokenFilter) throws Exception {
//        http.authorizeHttpRequests(
//                        authorize -> authorize.requestMatchers("/api/cars/create").authenticated()
//                                .anyRequest().permitAll())
//                .formLogin(Customizer.withDefaults())
//                .httpBasic((Customizer.withDefaults()))
//                .csrf(csrf -> csrf.disable());

        http.authorizeHttpRequests(authorize -> authorize.requestMatchers("/api/register", "/api/login").permitAll()
                         .anyRequest().authenticated())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .csrf(csrf->csrf.disable());
        // JWT token impl
        http.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        return http.
                getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(memberDetailsService)
              .passwordEncoder(passwordEncoder())
                .and().build();
    }


    // Approach 1 = > using withDefaultPasswordEncoder
//    @Bean
//    public UserDetailsService userDetailsService() {
//        User admin = (User) User.withDefaultPasswordEncoder()
//                .username("admin")
//                .password("1234")
//                .authorities("ADMIN")
//                .build();
//
//        User user = (User) User.withDefaultPasswordEncoder()
//                .username("user1")
//                .password("123456")
//                .authorities("USER")
//                .build();
//
//
//        UserDetailsService userDetailsService = new InMemoryUserDetailsManager(admin, user);
//
//        return userDetailsService;
//    }



//    // Approach =2 = > using passwordEncoder (NoOPPasswordEncoder => no encoding)
//    @Bean
//    public UserDetailsService userDetailsService() {
//        User admin = (User) User.withUsername("admin")
//                .password("1234")
//                .authorities("ADMIN")
//                .build();
//
//        User user = (User) User. withUsername("user1")
//                .password("123456")
//                .authorities("USER")
//                .build();
//
//
//        UserDetailsService userDetailsService = new InMemoryUserDetailsManager(admin, user);
//
//        return userDetailsService;
//    }

    @Bean
    public PasswordEncoder passwordEncoder() {
//        return NoOpPasswordEncoder.getInstance();
        return new BCryptPasswordEncoder();
    }



}

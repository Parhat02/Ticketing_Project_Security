package com.cydeo.config;

import com.cydeo.service.SecurityService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Configuration
public class SecurityConfig {

    private final SecurityService securityService;
    private final AuthSuccessHandler authSuccessHandler;

    public SecurityConfig(SecurityService securityService, AuthSuccessHandler authSuccessHandler) {
        this.securityService = securityService;
        this.authSuccessHandler = authSuccessHandler;
    }

//    @Bean
//    public UserDetailsService userDetailsService(PasswordEncoder encoder){
//
//        List<UserDetails> userList = new ArrayList<>();
//        userList.add(
//                new User("mike", encoder.encode("password"), Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN")))
//        );
//        userList.add(new User("ozzy", encoder.encode("password"), Arrays.asList(new SimpleGrantedAuthority("ROLE_MANAGER"))));
//
//        return new InMemoryUserDetailsManager(userList);
//    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{

        return http
                .authorizeHttpRequests(authorize -> authorize
                        //.requestMatchers("/user/**").hasRole("ADMIN")
                        .requestMatchers("/user/**").hasAuthority("Admin")
                                .requestMatchers("/project/**").hasAuthority("Manager")
                                .requestMatchers("/task/employee/**").hasAuthority("Employee")
                                .requestMatchers("/task/**").hasAuthority("Manager")
//                        .requestMatchers("/project/**").hasRole("MANAGER")
//                        .requestMatchers("/task/employee/**").hasRole("EMPLOYEE")
//                        .requestMatchers("/task/**").hasRole("MANAGER")
//                        .requestMatchers("/task/**").hasAuthority("ROLE_EMPLOYEE")
                        .requestMatchers(
                                "/",
                                "/login",
                                "/fragments/**",
                                "/assets/**",
                                "/images/**"
                        ).permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin( form -> form
                        .loginPage("/login")
//                        .defaultSuccessUrl("/welcome")
                                .successHandler(authSuccessHandler)
                        .failureUrl("/login?error=true")
                        .permitAll()
                )
                .logout( logout-> logout
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                        .logoutSuccessUrl("/login")
                )
                .rememberMe(httpSecurityRememberMeConfigurer -> httpSecurityRememberMeConfigurer
                                .tokenValiditySeconds(120)
                                .key("cydeo")
                                .userDetailsService(securityService)
                )
                .build();

    }

}

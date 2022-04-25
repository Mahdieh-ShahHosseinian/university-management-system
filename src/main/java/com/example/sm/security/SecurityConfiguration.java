package com.example.sm.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import static com.example.sm.model.ApplicationUserRole.ADMIN;
import static com.example.sm.model.ApplicationUserRole.MANAGER;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    public SecurityConfiguration(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Authentication
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(CustomAuthenticationProvider());
    }

    /**
     * Authorization
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
//                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/ums.com/api/v6/faculties/**", "/ums.com/api/v6/courses/**").hasAnyRole(MANAGER.name(), ADMIN.name())
                .antMatchers("/ums.com/api/v6/**").hasRole(ADMIN.name())
                .anyRequest().authenticated()
                .and()
                .httpBasic();
    }

    @Bean
    public AbstractUserDetailsAuthenticationProvider CustomAuthenticationProvider() {
        CustomAuthenticationProvider provider = new CustomAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder);
        provider.setUserDetailsService(userDetailsService);
        return provider;
    }
}
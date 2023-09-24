package com.javaguides.scalable.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig{
    @Bean
    public static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder builder) throws Exception {
            builder.inMemoryAuthentication() //<.>
                    .withUser("user") //<.>
                    .password(passwordEncoder().encode("123456")) //<.>
                    .roles("USER"); //<.>
    }
    @Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("user")
                .password(passwordEncoder().encode("verysecure"))
                .roles("USER")
                .and()
                .withUser("admin")
                .password(passwordEncoder().encode("evenmoresecure"))
                .roles("USER", "ADMIN");
    }
    @Autowired
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests() // <.>
                .antMatchers("/users/create").hasRole("ADMIN") // <.>
                .antMatchers("/users/*/delete").hasRole("ADMIN") // <.>
                .antMatchers(HttpMethod.GET, "/users/*").hasRole("USER") // <.>
                .antMatchers(HttpMethod.POST, "/users/*").hasRole("ADMIN") // <.>
                .and()
                .formLogin().permitAll() // <.>
                .and()
                .logout().permitAll(); // <.>
    }
}

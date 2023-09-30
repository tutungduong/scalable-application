package com.javaguides.scalable.config;




//@Configuration
//@EnableWebSecurity
//public class WebSecurityConfig {
//
//    @Autowired
//    private UserDetailsService userDetailsService;
//    @Bean
//    public static PasswordEncoder passwordEncoder(){
//        return new BCryptPasswordEncoder();
//    }
//    @Bean
//    public InMemoryUserDetailsManager userDetailsService(){
//        PasswordEncoder encoder = passwordEncoder();
//        UserDetails user = User.builder()
//                .username("user")
//                .password(encoder.encode("123456"))
//                .roles("USER")
//                .build();
//
//        UserDetails admin = User.builder()
//                .username("admin")
//                .password(encoder.encode("admin1"))
//                .roles("USER", "ADMIN")
//                .build();
//
//        return new InMemoryUserDetailsManager(user, admin);
//    }
//
////    @Bean
////    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
//////        http.authorizeRequests() // <.>
//////                .antMatchers("/users/create").hasRole("ADMIN") // <.>
//////                .antMatchers("/users/*/delete").hasRole("ADMIN") // <.>
//////                .antMatchers(HttpMethod.GET, "/users/*").hasRole("USER") // <.>
//////                .antMatchers(HttpMethod.POST, "/users/*").hasRole("ADMIN") // <.>
//////                .and()
//////                .formLogin().permitAll() // <.>
//////                .and()
//////                .logout().permitAll(); // <.>
////        httpSecurity.csrf(csrf -> csrf.disable())
////                .authorizeRequests()
////                .requestMatchers("/users/create").hasRole("ADMIN")
////                .requestMatchers("/users/*/delete").hasRole("ADMIN")
////                .requestMatchers(HttpMethod.GET,"/users/*").hasRole("USER")
////                .requestMatchers(HttpMethod.POST,"/users/*").hasRole("ADMIN")
////                .and()
////                .formLogin(login -> login.permitAll()) // <.>
////                .logout(logout -> logout.permitAll()); // <.>
//////                .and()
//////                .formLogin(form -> form
//////                        .loginPage("/login")
//////                        .loginProcessingUrl("/login")
//////                        .defaultSuccessUrl("/users")
//////                        .permitAll()
//////                )
//////                .logout(
//////                        logout -> logout
//////                                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//////                                .permitAll()
//////                );
////
////        return httpSecurity.build();
////    }
//            @Bean
//        public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
//                http.csrf(csrf -> csrf.disable())
//                        .authorizeHttpRequests(auth -> auth
//                                .requestMatchers("/users/create").hasRole("ADMIN")
//                                .requestMatchers("/users/*/delete").hasRole("ADMIN")
//                                .requestMatchers(HttpMethod.GET,"/users/*").hasRole("USER")
//                                .requestMatchers(HttpMethod.POST,"/users/*").hasRole("ADMIN")
//                                .anyRequest().authenticated())
//                        .formLogin(withDefaults())
//                        .logout(logout -> logout
//                                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//                                .logoutSuccessUrl("/login")
//                        );
//            return http.build();
//        }
//
//        @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder builder) throws Exception {
//        builder.userDetailsService(userDetailsService)
//                .passwordEncoder(passwordEncoder());
//    }
//}


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

//    @Autowired
//    private UserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    @Bean
//    public UserDetailsService inMemoryUserDetailsService() {
//        PasswordEncoder encoder = passwordEncoder();
//
//        UserDetails user = User.builder()
//                .username("user")
//                .password(encoder.encode("123456"))
////                .password("user")
//                .roles("USER")
//                .build();
//
//        UserDetails admin = User.builder()
//                .username("admin")
//                .password(encoder.encode("admin"))
////                .password("admin")
//                .roles("ADMIN")
//                .build();
//        return new InMemoryUserDetailsManager(user, admin);
//    }
    @Bean
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/users/create").hasRole("ADMIN")
                        .requestMatchers("/users/*/delete").hasRole("ADMIN")
                        .requestMatchers("/img/*").permitAll()
                        .requestMatchers("/css/*").permitAll()
                        .requestMatchers(HttpMethod.GET, "/users/*").hasRole("USER")
                        .requestMatchers(HttpMethod.POST, "/users/*").hasRole("ADMIN")
                        .anyRequest().authenticated())
                .formLogin(login -> login
                        .loginPage("/login")
//                        .loginProcessingUrl("/login")
//                        .defaultSuccessUrl("/users")
                        .permitAll())
                .logout(logout -> logout
//                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//                        .logoutSuccessUrl("/login")
                        .permitAll()
                );

        return http.build();
    }

//        @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder builder) throws Exception {
//        builder.userDetailsService(userDetailsService)
//                .passwordEncoder(passwordEncoder());
//    }
}